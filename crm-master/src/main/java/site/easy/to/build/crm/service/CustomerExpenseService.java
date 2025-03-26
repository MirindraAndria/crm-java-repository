package site.easy.to.build.crm.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import lombok.val;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.model.CustomerExpense;
import site.easy.to.build.crm.repository.CustomerExpenseRepository;
import site.easy.to.build.crm.service.customer.CustomerService;

@Service 
public class CustomerExpenseService {
    @Autowired 
    CustomerExpenseRepository customerExpRepo ; 
    @Autowired 
    CustomerService customerService ; 

    public double replaceSyntax( String number) {
        String newNumber = number.replace(" ", "");
        newNumber = number.replace(".", "");
        newNumber = number.replace(",", ".");
        double value = Double.valueOf(newNumber) ; 
        System.out.println("value: " + value);
        return value ;
    }
    public List<CustomerExpense> getAll() { 
        return customerExpRepo.getAll();
    }

    public void importCsvCustomerTicketLead(String csvFile) throws Exception { 
         try {
             // Définir un parser avec le séparateur ';'
             CSVParser parser = new CSVParserBuilder().withSeparator('~').build();
             // Construire le CSVReader avec ce parser
             CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                     .withCSVParser(parser)
                     .build();

             List<String[]> allData = reader.readAll(); // Lire toutes les lignes
             reader.close();

             // Ignorer la première ligne (en-têtes)
             allData.remove(0);
             int lineNumber = 1 ; 
             List<CustomerExpense> customerTicketLead  = new ArrayList<CustomerExpense>(); 
            //  List<CustomerExpense> customerTicketLead2  = customerExpRepo.getAll() ; 
             
             for (String[] line : allData) {
                CustomerExpense customerExpense = new CustomerExpense() ; 
                if ( customerService.findByEmail(line[0].trim()) == null ) { 
                    throw new Exception("error line : " + lineNumber + " customer email not foud : " + line[0]) ;
                }
                customerExpense.setCustomerEmail(line[0]);
                customerExpense.setSubjectOrName(line[1]);
                customerExpense.setType(line[2] , String.valueOf(lineNumber));
                customerExpense.setStatus(line[3], String.valueOf(lineNumber));
                customerExpense.setExpense( this.replaceSyntax(line[4]), String.valueOf(lineNumber));
                lineNumber ++ ;  
                customerTicketLead.add(customerExpense);       
            }

            for ( CustomerExpense customerExpense : customerTicketLead ) { 
                System.out.println("CustomerExpense save : " + customerExpense.getCustomerEmail() ) ; 
                customerExpRepo.save(customerExpense);   
            }
             
            
         } catch (Exception e) {
             e.printStackTrace();
             throw new Exception( e.getMessage());
         }
     }
}
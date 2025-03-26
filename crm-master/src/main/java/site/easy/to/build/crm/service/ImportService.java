package site.easy.to.build.crm.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.model.Budget;
import site.easy.to.build.crm.model.CustomerExpense;
import site.easy.to.build.crm.service.customer.CustomerService;

@Service 
public class ImportService {
    @Autowired
    CustomerService customerService ; 
    @Autowired 
    BudgetService budgetService ;
    @Autowired 
    CustomerExpenseService customerExpenseService ;
    
    public double replaceSyntax( String number) {
        String newNumber = number.replace(" ", "");
        newNumber = number.replace(".", "");
        newNumber = number.replace(",", ".");
        double value = Double.valueOf(newNumber) ; 
        System.out.println("value: " + value);
        return value ;
    }

    @Transactional(rollbackFor = Exception.class) 
    public void importAllData(String fullPath1, String fullPath2, String fullPath3, User user) throws Exception {
        importCsvCustomer(fullPath1, user);
        importCsvBudget(fullPath2); 
        customerExpenseService.importCsvCustomerTicketLead(fullPath3); 
    }

    public void importCsvCustomer(String csvFile , User user) {
        try {
            // Définir un parser avec le séparateur ';'Exception {
            CSVParser parser = new CSVParserBuilder().withSeparator('~').build();
            // Construire le CSVReader avec ce parser
            CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                    .withCSVParser(parser)
                    .build();

            List<String[]> allData = reader.readAll(); // Lire toutes les lignes
            reader.close();

            // Ignorer la première ligne (en-têtes)
            allData.remove(0);
            for (String[] line : allData) {
                Customer customer = new Customer();
                customer.setName(line[1]);
                customer.setEmail(line[0]);
                customer.setCountry("Mada");
                customer.setPhone("03211003195");
                customer.setUser(user);

                customerService.save(customer);
            }

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    public void importCsvBudget(String csvFile ) throws Exception { 
        try {
            // Définir un parser avec le séparateur ';'
            CSVParser parser = new CSVParserBuilder().withSeparator('~').build();
            // Construire le CSVReader avec ce parser
            CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                    .withCSVParser(parser)
                    .build();

            List<String[]> allData = reader.readAll(); // Lire toutes les lignes
            reader.close();
            allData.remove(0);
        
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
          
            List<Budget> budget_list =  new ArrayList<Budget>(); 
            int lineNumber = 1 ; 
            for (String[] line : allData) {
                Budget budget = new Budget() ; 
                budget.setLibelle("import budget ");
                budget.setDateBudget(timestamp);
                budget.setAmount( replaceSyntax(line[1]) , String.valueOf(lineNumber) );
                if ( customerService.findByEmail(line[0].trim()) == null ) { 
                    throw new Exception("error line : " + lineNumber + " customer email not foud : " + line[0]) ;
                }
                budget.setIdCustomer( customerService.findByEmail( line[0]).getCustomerId() ) ; 
                lineNumber ++ ; 
                budget_list.add(budget);
            }
            for ( Budget budget : budget_list) {
                System.out.println(" budget save : "  + budget.getIdBudget());
                budgetService.save(budget);
            } 
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage()) ; 
        }
    }

    
}

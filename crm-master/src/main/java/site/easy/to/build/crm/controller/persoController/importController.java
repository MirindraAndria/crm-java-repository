package site.easy.to.build.crm.controller.persoController;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import com.google.common.base.Ticker;
import com.opencsv.exceptions.CsvValidationException;

import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.model.CustomerExpense;
import site.easy.to.build.crm.model.DepenseTicketLead;
import site.easy.to.build.crm.service.CustomerExpenseService;
import site.easy.to.build.crm.service.DepenseTicketLeadService;
import site.easy.to.build.crm.service.ImportService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.ticket.TicketService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;


@Controller 
public class importController {
    @Autowired 
    ImportService importservice ;
    @Autowired  
    CustomerExpenseService customerExpenseService ; 
    @Autowired 
    TicketService ticketService ; 
    @Autowired 
    LeadService leadService ; 
    @Autowired 
    CustomerService customerService;
    @Autowired 
    AuthenticationUtils authUtils ; 
    @Autowired 
    UserService userService ; 
    @Autowired 
    DepenseTicketLeadService depenseTicketLeadService ; 

    
   @PostMapping("/importPage")
   public String importPage() {
       return "importData";
   }
   @PostMapping("/importCSVFile")
    public String importCSV(@RequestParam MultipartFile fileCustomer , 
                            @RequestParam MultipartFile fileBudget , 
                            @RequestParam MultipartFile fileTicketLead, 
                            Model model ,
                            Authentication authentication ){
        try { 
            String absolute = "C:/Users/Mixan/Downloads/";
            String csvFile1 = fileCustomer.getOriginalFilename();
            String csvFile2 = fileBudget.getOriginalFilename() ; 
            String csvFile3 = fileTicketLead.getOriginalFilename() ;   
            String fullPath1 = absolute + csvFile1;  
            String fullPath2 =  absolute + csvFile2 ;
            String fullPath3 = absolute + csvFile3 ;
            
            int idUser = authUtils.getLoggedInUserId(authentication);
            User user = userService.findById(idUser); 
         

            importservice.importAllData(fullPath1, fullPath2, fullPath3, user);
            
            List<Customer> list_Customers = customerService.findAll() ; 
            List<CustomerExpense> listCustomerExpenses = customerExpenseService.getAll() ;
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            DepenseTicketLead depense = new DepenseTicketLead(); 
            
            for (CustomerExpense customerExpense : listCustomerExpenses) {
                if( customerExpense.getType().equals("ticket")) { 
                    Ticket ticket = new Ticket() ; 
                    ticket.setSubject(customerExpense.getSubjectOrName() )  ; 
                    ticket.setDescription("import data ticket");
                    ticket.setStatus( customerExpense.getStatus() ) ; 
                    ticket.setPriority( "medium") ;
                    ticket.setCustomer( customerService.findByEmail( customerExpense.getCustomerEmail()) ) ;
                    ticket.setManager(user);
                    ticket.setEmployee(user);
                    ticketService.save(ticket); 
                    
                    depense.setLibelle("new depense ticket");
                    depense.setDateDepense(timestamp);
                    depense.setAmount( customerExpense.getExpense());
                    depense.setIdTicket( ticket.getTicketId());
                    System.out.println("ticketId : " + ticket.getTicketId());  
                    depense.setIdLead(0);
                    depenseTicketLeadService.saveDepenseTicket(depense);
                }
                if (customerExpense.getType().equals("lead") ) { 
                    Lead lead = new Lead() ;  
                    lead.setName(customerExpense.getSubjectOrName()) ;
                    lead.setStatus(customerExpense.getStatus() ) ; 
                    lead.setCustomer( customerService.findByEmail( customerExpense.getCustomerEmail() )) ;
                    lead.setManager(user);
                    lead.setEmployee(user);
                    lead.setPhone("038145340888") ; 
                    leadService.save(lead);
                    
                    depense.setLibelle("new depense lead");
                    depense.setDateDepense(timestamp);
                    depense.setAmount( customerExpense.getExpense());
                    depense.setIdTicket(0);
                    depense.setIdLead(lead.getLeadId());
                    System.out.println("leadId : " + lead.getLeadId());
                    depenseTicketLeadService.saveDepenseLead(depense);
                }
            }
            
            model.addAttribute("success", "Data imported successfully");
            return "importData";
        }catch ( Exception e ) { 
            e.printStackTrace();
            if ( e.getMessage() != null ) { 
                model.addAttribute("error", e.getMessage());
            }
        } 
        return "importData";
        
    }    
}


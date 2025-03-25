package site.easy.to.build.crm.controller.persoController;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.easy.to.build.crm.model.Budget;
import site.easy.to.build.crm.model.DepenseTicketLead;
import site.easy.to.build.crm.service.BudgetService;
import site.easy.to.build.crm.service.DepenseTicketLeadService;
import site.easy.to.build.crm.service.TauxService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.ticket.TicketService;

@RestController 
@RequestMapping("/api")
public class apiController {
    @Autowired 
    BudgetService budgetService;
    @Autowired 
    DepenseTicketLeadService depenseService ;  
    @Autowired  
    CustomerService customerService ; 
    @Autowired 
    TicketService ticketService ;
    @Autowired  
    LeadService leadService ; 
    @Autowired 
    TauxService tauxService ; 

    @GetMapping("/allBudgets")
    public ResponseEntity<List<Budget>> getAllBudget() { 
        try { 
            List<Budget> allBudget = budgetService.getAll() ; 
            for ( int i = 0; i < allBudget.size(); i++ ) { 
                allBudget.get(i).setCustomer( customerService.findByCustomerId( allBudget.get(i).getIdCustomer()).getName() );  
            }
            return ResponseEntity.ok(allBudget);
        }catch ( Exception e ) { e.printStackTrace(); }
        return null ; 
    }
    @GetMapping("/allDepense")
    public ResponseEntity<List<DepenseTicketLead>> getAllDepense() { 
        try { 
          
           List<DepenseTicketLead> allDepense = depenseService.getAll() ;
           for ( int i = 0 ; i < allDepense.size() ; i++ ) { 
            
                if( allDepense.get(i).getIdLead() != 0 ) { 
                    allDepense.get(i).setIdCustomer( leadService.findByLeadId( allDepense.get(i).getIdLead()).getCustomer().getCustomerId());   
                }if (allDepense.get(i).getIdTicket() != 0 ){ 
                    allDepense.get(i).setIdCustomer( ticketService.findByTicketId( allDepense.get(i).getIdTicket()).getCustomer().getCustomerId());   ;  
                }
            }
           for ( int i = 0 ; i < allDepense.size() ; i++ ) {  
                if ( allDepense.get(i).getIdCustomer() != 0) { 
                    allDepense.get(i).setCustomer( customerService.findByCustomerId( allDepense.get(i).getIdCustomer()).getName() );  
                }
            }
           return ResponseEntity.ok(allDepense); 
        }catch ( Exception e ) { e.printStackTrace(); }
        return null ; 
    }


    @PostMapping("/delete-ticket-depense/{idTicket}") 
    public ResponseEntity<String> deleteTicketDepense(@PathVariable int idTicket) {
        try { 
            depenseService.deleteTicket(idTicket);
            return ResponseEntity.ok("delete ticket successful"); 
        }catch ( Exception e ) { e.printStackTrace(); }
        return  ResponseEntity.ok("delete ticket error"); 
    }

    @PostMapping("/delete-lead-depense/{idLead}") 
    public ResponseEntity<String> deleteLeadDepense(@PathVariable int idLead ) {
        try {  
            depenseService.deleteLead(idLead);
            return ResponseEntity.ok("delete lead successful"); 
        }catch ( Exception e ) { e.printStackTrace(); } 
        return ResponseEntity.ok("delete lead error");     
    }
    
    @PostMapping("/update-lead/{idLead}/{amount}")
    public ResponseEntity<String> updateLead(@PathVariable int idLead , @PathVariable String amount ) {
       try { 
            depenseService.updateLead(idLead, Double.valueOf(amount));
            return ResponseEntity.ok("update lead successful"); 
       }catch ( Exception e ) { e.printStackTrace(); }
       return ResponseEntity.ok("update lead error"); 
        
    }
    @PostMapping("/update-ticket/{idTicket}/{amount}")
    public ResponseEntity<String> updateTicket(@PathVariable int idTicket , @PathVariable String amount ) {
       try { 
            System.out.println("idTecket : "  + idTicket ) ; 
            System.out.println("amount : "  + amount ) ;

            depenseService.updateTicket(idTicket , Double.valueOf(amount));
            return ResponseEntity.ok("update ticket successful"); 
       }catch ( Exception e ) { e.printStackTrace(); }
       return ResponseEntity.ok("update ticket error"); 
        
    }

    @PostMapping("/update-taux/{taux}")
    public ResponseEntity<String> updateTaux(@PathVariable String taux ) {
       try { 
            tauxService.updateTaux(Double.valueOf(taux));
            return ResponseEntity.ok("update taux successful"); 
       }catch ( Exception e ) { e.printStackTrace(); }
       return ResponseEntity.ok("update taux error"); 
        
    }
    
    



}

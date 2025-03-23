package site.easy.to.build.crm.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.easy.to.build.crm.repository.BudgetRepository;
import site.easy.to.build.crm.repository.TauxAlertRepository;
import site.easy.to.build.crm.model.Budget;
import site.easy.to.build.crm.model.DepenseTicketLead;
import site.easy.to.build.crm.model.TauxAlert;

@Service 
public class TauxService {

    @Autowired
    private TauxAlertRepository tauxAlertRepository ;  

    public TauxAlert getTauxAlert() { 
        return tauxAlertRepository.getTaux() ;   
    } 
    
    public String checkTauxAlert( double tauxAlertPercent  , List<Budget> budgetCustomer , List<DepenseTicketLead> allDepenseCustomer , double depenseActu ) {  
        double sommeBudget = 0 ; 
        double sommeDepense = 0 ; 
        for ( Budget budget : budgetCustomer ) { sommeBudget += budget.getAmount() ; }
        for ( DepenseTicketLead depense : allDepenseCustomer ) { sommeDepense += depense.getAmount() ; }
        double sommeDepenseFinal = sommeDepense + depenseActu ;
        double valueBudgetPercent = ( sommeBudget * tauxAlertPercent ) / 100 ;  
        System.out.println( "sommeBudget: " + sommeBudget ) ;
        System.out.println( "valueBudgetPercent: " + valueBudgetPercent) ; 
        System.out.println( "sommeDepenseFinal: " + sommeDepenseFinal ) ; 

        if ( valueBudgetPercent > sommeDepenseFinal ) { return "aucune alert"; } 
        return "taux alert : " + tauxAlertPercent + " atteint" ;   
    }


    public String checkDepassement(List<Budget> budgetCustomer , List<DepenseTicketLead> allDepenseCustomer , double depenseActu){ 
        double sommeBudget = 0 ; 
        double sommeDepense = 0 ; 
        for ( Budget budget : budgetCustomer ) { sommeBudget += budget.getAmount() ; }
        for ( DepenseTicketLead depense : allDepenseCustomer ) { sommeDepense += depense.getAmount() ; }
        double sommeDepenseFinal= sommeDepense + depenseActu ;
      
        System.out.println( "sommeBudget: " + sommeBudget ) ;
        System.out.println( "sommeDepense: " + sommeDepenseFinal ) ; 
        if( sommeDepenseFinal > sommeBudget ) { return "depassement du budget" ;  } 
        return null  ; 
    }
}

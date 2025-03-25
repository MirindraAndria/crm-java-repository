package site.easy.to.build.crm.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.easy.to.build.crm.repository.BudgetRepository;
import site.easy.to.build.crm.model.Budget;

@Service 
public class BudgetService {
    @Autowired 
    BudgetRepository budgetRepository; 
    public void addBudget( int idCustomer , String libelle , double amount , String dateBudget )
    {
        Timestamp dateBudgetTimestamp = this.convertDateString(dateBudget) ; 
        budgetRepository.addBudget(new Budget(libelle , dateBudgetTimestamp , amount , idCustomer ));
    }

    public void updateBudget( int idCustomer , String libelle , double amount , String dateBudget )
    {
        Timestamp dateBudgetTimestamp = this.convertDateString(dateBudget) ; 
        budgetRepository.updateBudget(new Budget(libelle , dateBudgetTimestamp , amount , idCustomer ));
    }
    public Timestamp convertDateString ( String datBudget ){ 
        try { 
            String dateString = "2025-03-23 15:30:45"; // Format YYYY-MM-DD HH:MI:SS
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(dateString);
            Timestamp timestamp = new Timestamp(parsedDate.getTime()) ; 
            return timestamp ; 
        }catch ( Exception e ) { 
            e.printStackTrace();
        }
        return null ; 
    }

    public List<Budget> getAll( int idCustomer ){
        return budgetRepository.getByIdCustomer(idCustomer) ; 
    }
    public List<Budget> getAll(){
        return budgetRepository.getAll() ; 
    }
    
}

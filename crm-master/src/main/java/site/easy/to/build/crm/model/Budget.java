package site.easy.to.build.crm.model;

import java.sql.Timestamp;

public class Budget {
    int idBudget ; 
    String libelle ; 
    Timestamp dateBudget ;
    double amount ;  
    int idCustomer; 
    String Customer ; 
   
    public Budget() {}

    public  Budget ( int idBudget , String libelle , Timestamp dateBudget ,double amount ,  int idCustomer ){
        this.idBudget = idBudget ;
        this.libelle = libelle  ;
        this.dateBudget = dateBudget ;
        this.amount = amount ;  
        this.idCustomer = idCustomer ;
       
    }
    public  Budget (  String libelle , Timestamp dateBudget ,double amount ,  int idCustomer  ){
        this.libelle = libelle  ;
        this.dateBudget = dateBudget ;
        this.amount = amount ;  
        this.idCustomer = idCustomer ;
       
    }
    public String getCustomer() {
        return Customer;
    }
    public void setCustomer(String customer) {
        Customer = customer;
    }
    // getters & setters   
  
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Timestamp getDateBudget() {
        return dateBudget;
    }
    public int getIdBudget() {
        return idBudget;
    }
    public int getIdCustomer() {
        return idCustomer;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setDateBudget(Timestamp dateBudget) {
        this.dateBudget = dateBudget;
    }
    public void setIdBudget(int idBudget) {
        this.idBudget = idBudget;
    }
    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
}

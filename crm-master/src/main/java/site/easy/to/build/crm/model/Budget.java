package site.easy.to.build.crm.model;

import java.sql.Timestamp;

public class Budget {
    int idBudget ; 
    String libelle ; 
    Timestamp dateBudget ;
    double amount ;  
    int idCustormer; 
   
    public Budget() {}

    public  Budget ( int idBudget , String libelle , Timestamp dateBudget ,double amount ,  int idCustormer ){
        this.idBudget = idBudget ;
        this.libelle = libelle  ;
        this.dateBudget = dateBudget ;
        this.amount = amount ;  
        this.idCustormer = idCustormer ;
       
    }
    public  Budget (  String libelle , Timestamp dateBudget ,double amount ,  int idCustormer  ){
        this.libelle = libelle  ;
        this.dateBudget = dateBudget ;
        this.amount = amount ;  
        this.idCustormer = idCustormer ;
       
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
    public int getIdCustormer() {
        return idCustormer;
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
    public void setIdCustormer(int idCustormer) {
        this.idCustormer = idCustormer;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
}

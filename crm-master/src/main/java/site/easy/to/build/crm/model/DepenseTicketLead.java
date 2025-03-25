package site.easy.to.build.crm.model;

import java.sql.Timestamp;

public class DepenseTicketLead {
    int idDepense;
    String libelle;
    Timestamp dateDepense;
    double amount;
    int idTicket;
    int idLead;   
    int idCustomer; 
    String customer;      
    
    public DepenseTicketLead() {}

    public DepenseTicketLead(int idDepense, String libelle, Timestamp dateDepense, double amount, int idTicket, int idLead , int idCustomer) { 
        this.idDepense = idDepense;
        this.libelle = libelle;
        this.dateDepense = dateDepense;
        this.amount = amount;
        this.idTicket = idTicket;
        this.idLead = idLead;
        this.idCustomer = idCustomer;
    }
    public DepenseTicketLead(String libelle, Timestamp dateDepense, double amount, int idTicket, int idLead) { 
        this.libelle = libelle;
        this.dateDepense = dateDepense;
        this.amount = amount;
        this.idTicket = idTicket;
        this.idLead = idLead;
    }

    // Getters et Setters

    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public int getIdCustomer() {
        return idCustomer;
    }
    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }
    public int getIdDepense() {
        return idDepense;
    }

    public void setIdDepense(int idDepense) {
        this.idDepense = idDepense;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Timestamp getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(Timestamp dateDepense) {
        this.dateDepense = dateDepense;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdLead() {
        return idLead;
    }

    public void setIdLead(int idLead) {
        this.idLead = idLead;
    }
}

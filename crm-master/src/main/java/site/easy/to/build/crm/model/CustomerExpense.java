package site.easy.to.build.crm.model;

import java.util.Arrays;
import java.util.List;



public class CustomerExpense {
        private String customerEmail;
        private String subjectOrName;
        private String type;
        private String status;
        private double expense;

        public CustomerExpense () { }
    
        // Constructeur
        public CustomerExpense(String customerEmail, String subjectOrName, String type, String status, double expense) {
            this.customerEmail = customerEmail;
            this.subjectOrName = subjectOrName;
            this.type = type;
            this.status = status;
            this.expense = expense;
        }
    
        // Getters et Setters
        public String getCustomerEmail() {
            return customerEmail;
        }
    
        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }
        // public void setCustomerEmail(String customerEmail , String line) {
        //     this.customerEmail = customerEmail;

        // }
    
        public String getSubjectOrName() {
            return subjectOrName;
        }
    
        public void setSubjectOrName(String subjectOrName) {
            this.subjectOrName = subjectOrName;
        }
    
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }

        public void setType(String type , String line ) throws Exception {
            List<String> statusTicket = Arrays.asList(
                "ticket", "lead" );
            String newType = type.toLowerCase().trim() ; 
            if (!statusTicket.contains(newType) ) {
                throw new Exception("error line: " + line + " invalid type: " + type);
            }
            this.type = newType;
        }
    
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public void setStatus(String status , String line ) throws Exception { 
            this.checkStatus(status, line);
            this.status = status.toLowerCase().trim() ; 
        }
    
        public double getExpense() {
            return expense;
        }
        public void setExpense(double expense) {
            this.expense = expense;
        }
        public void setExpense(double expense , String line )throws Exception {
            try {
                if (expense < 0) {
                    throw new Exception("error line : " + line + " expense negtive : " + expense) ;
                }
                this.expense = expense;
            } catch (NumberFormatException e) {
                throw new Exception("error line : " + line + " invalide value expense: " + expense) ;
            }
         
        }
        

        public void checkStatus(String status , String line)throws Exception {  
            List<String> statusTicketLead = Arrays.asList(
                "open", 
                "assigned", 
                "on-hold", 
                "in-progress", 
                "resolved", 
                "closed", 
                "reopened", 
                "pending-customer-response", 
                "escalated", 
                "archived" , 
                "meeting-to-schedule",
                "assign-to-sales", 
                "archived", 
                "success"
            );
            String newStatus = status.toLowerCase().trim() ; 
            // if (!statusTicket.contains(newStatus) && !statusLead.contains(newStatus)) {
            //     throw new Exception("error line: " + line + " invalid status: " + status);
            // }
            System.out.println("new Status : "  + newStatus);
            if (!statusTicketLead.contains(newStatus)) {
                throw new Exception("error line: " + line + " invalid status: " + status);
            }
        }

    }
    
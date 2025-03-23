package site.easy.to.build.crm.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.easy.to.build.crm.model.DepenseTicketLead;
import site.easy.to.build.crm.repository.DepenseTicketLeadRepository;

@Service
public class DepenseTicketLeadService {
    @Autowired
    DepenseTicketLeadRepository depenseTicketLeadRepository ; 

    public List<DepenseTicketLead> getAllDepenseTicketLead(  int idCustomer  ) {
        return depenseTicketLeadRepository.getByIdCustomer( idCustomer );   
    }
    public void insertDepenseLead( String libelle , Timestamp date_depense , double amount , int idLead ) {
        DepenseTicketLead depense = new DepenseTicketLead(libelle, date_depense, amount, 0 , idLead); 
        depenseTicketLeadRepository.insertDepenseLead(depense); 
    }
    public void insertDepenseTicket( String libelle , Timestamp date_depense , double amount , int idTicket ) {
        DepenseTicketLead depense = new DepenseTicketLead(libelle, date_depense, amount, idTicket , 0); 
        depenseTicketLeadRepository.insertDepenseTicket(depense); 
    }
}

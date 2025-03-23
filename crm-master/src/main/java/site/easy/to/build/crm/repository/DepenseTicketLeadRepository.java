package site.easy.to.build.crm.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.model.DepenseTicketLead;

@Repository 
public class DepenseTicketLeadRepository {
    JdbcTemplate jdbcTemplate ; 
    public DepenseTicketLeadRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
     public List<DepenseTicketLead> getByIdCustomer(int idCustomer) {
        String sql = "SELECT * FROM  depenseTicketLeadCustomer WHERE customer_id = ? ";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            DepenseTicketLead depense = new DepenseTicketLead();
            depense.setIdDepense(rs.getInt("idDepense"));
            depense.setLibelle(rs.getString("libelle"));
            depense.setDateDepense(rs.getTimestamp("date_depense"));
            depense.setAmount(rs.getDouble("amount"));
            depense.setIdTicket(rs.getInt("idTicket"));
            depense.setIdLead(rs.getInt("idLead"));
            depense.setIdCustomer(rs.getInt("customer_id"));
            return depense;
        }, idCustomer);
    }

    public void insertDepenseLead(DepenseTicketLead depense) {
        String sql = "INSERT INTO depenseTicketLead (libelle, date_depense, amount, idTicket, idLead) " +
                     "VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, depense.getLibelle(), depense.getDateDepense(),
            depense.getAmount() ,  null , depense.getIdLead() );
    }
    public void insertDepenseTicket(DepenseTicketLead depense) {
        String sql = "INSERT INTO depenseTicketLead (libelle, date_depense, amount, idTicket, idLead) " +
                     "VALUES (?, ?, ?, ?, ?)";  
            jdbcTemplate.update(sql, depense.getLibelle(), depense.getDateDepense(),
            depense.getAmount(), depense.getIdTicket() , null );
    }

}

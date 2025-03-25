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
    public List<DepenseTicketLead> getByIdCustomerTicket(int idCustomer) {
        String sql = "SELECT * FROM  depenseTicketCustomer WHERE customer_id = ? ";
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

    public List<DepenseTicketLead> getByIdCustomerLead(int idCustomer) {
        String sql = "SELECT * FROM  depenseLeadCustomer WHERE customer_id = ? ";
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

    public List<DepenseTicketLead> getAll() {
        String sql = "SELECT * FROM  depenseTicketLead";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            DepenseTicketLead depense = new DepenseTicketLead();
            depense.setIdDepense(rs.getInt("idDepense"));
            depense.setLibelle(rs.getString("libelle"));
            depense.setDateDepense(rs.getTimestamp("date_depense"));
            depense.setAmount(rs.getDouble("amount"));
            depense.setIdTicket(rs.getInt("idTicket"));
            depense.setIdLead(rs.getInt("idLead"));
            return depense;
        });
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

    public void deleteDepenseLead( int idLead ) {
        String sql = "DELETE FROM depenseTicketLead WHERE idLead = ?";
            jdbcTemplate.update(sql, idLead  );
    }
    public void deleteDepenseTicket( int idTicket ) {
        String sql = "DELETE FROM depenseTicketLead WHERE idTicket = ?";
            jdbcTemplate.update(sql, idTicket  );
    }

    public void updateDepenseLead( double amount ,  int idLead ) {
        String sql = "UPDATE depenseTicketLead set amount = ? WHERE idLead = ?";
            jdbcTemplate.update(sql, amount , idLead );
    }
    public void updateDepenseTicket( double amount ,  int idTicket ) {
        String sql = "UPDATE depenseTicketLead set amount = ? WHERE idTicket = ?";
            jdbcTemplate.update(sql, amount , idTicket);
    }
}

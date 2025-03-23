package site.easy.to.build.crm.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.model.Budget;

@Repository 
public class BudgetRepository {
    private JdbcTemplate jdbcTemplate;

    public BudgetRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void addBudget(Budget budget) {
        String sql = "INSERT INTO budget (libelle , date_budget , amount ,idCustomer ) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, budget.getLibelle(), budget.getDateBudget(), budget.getAmount(), budget.getIdCustormer());
    }
    public void updateBudget(Budget budget) {
        String sql = "UPDATE budget SET amount = ? WHERE idCustomer = ? ";
        jdbcTemplate.update(sql, budget.getAmount(), budget.getIdCustormer());
    }
    
    public List<Budget> getByIdCustomer(int idCustomer) {  
        String sql = "SELECT * FROM budget WHERE idCustomer = ?";  
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Budget(
            rs.getInt("idBudget"),
            rs.getString("libelle"),
            rs.getTimestamp("date_budget"),
            rs.getDouble("amount"),
            rs.getInt("idCustomer")
        ), idCustomer);
    }
    
}

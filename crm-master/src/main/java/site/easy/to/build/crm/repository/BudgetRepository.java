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
        jdbcTemplate.update(sql, budget.getLibelle(), budget.getDateBudget(), budget.getAmount(), budget.getIdCustomer());
    }
    public void updateBudget(Budget budget) {
        String sql = """
            UPDATE budget 
            SET amount = ? 
            WHERE idCustomer = ? 
            AND date_budget = (
                SELECT MAX(date_budget) FROM budget 
                WHERE idCustomer = ?
            )
        """;
        jdbcTemplate.update(sql, budget.getAmount(), budget.getIdCustomer(), budget.getIdCustomer());
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
    public List<Budget> getAll() {  
        String sql = "SELECT * FROM budget";  
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Budget(
            rs.getInt("idBudget"),
            rs.getString("libelle"),
            rs.getTimestamp("date_budget"),
            rs.getDouble("amount"),
            rs.getInt("idCustomer")
        ));
    }
    
}

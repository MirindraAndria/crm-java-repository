package site.easy.to.build.crm.repository;

import java.security.PublicKey;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.model.CustomerExpense;
@Repository 
public class CustomerExpenseRepository { 
    private JdbcTemplate jdbcTemplate;
    public CustomerExpenseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void save(CustomerExpense customerExpense) {
        String sql = "INSERT INTO CustomerExpense (customerEmail, subjectOrName, type, status, expense) " +
                     "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            customerExpense.getCustomerEmail(),
            customerExpense.getSubjectOrName(),
            customerExpense.getType(),
            customerExpense.getStatus(),
            customerExpense.getExpense()
        );
    }
    public List<CustomerExpense> getAll() { 
        String sql = "SELECT * FROM CustomerExpense";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new CustomerExpense(
                rs.getString("customerEmail"),
                rs.getString("subjectOrName"),
                rs.getString("type"),
                rs.getString("status"),
                rs.getDouble("expense")
            );
        });
    }
}

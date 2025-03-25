package site.easy.to.build.crm.repository;

import java.util.List;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataRepository {
    private JdbcTemplate jdbcTemplate;
    
    public DataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // public void clearDatabase(Set<String> tablesToExclude) {
    //     jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0;");
    //     List<String> tables = jdbcTemplate.queryForList("SHOW TABLES;", String.class);
    //     for (String table : tables) {
    //         if (!tablesToExclude.contains(table)) {
    //             jdbcTemplate.execute("TRUNCATE TABLE " + table + ";"); 
    //         }
    //     }
    //     jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1;");
    // }

    public void clearDatabase(Set<String> tablesToExclude) {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0;");
        
        // Filtrer uniquement les tables (exclure les vues)
        List<String> tables = jdbcTemplate.queryForList(
            "SELECT table_name FROM information_schema.tables WHERE table_schema = DATABASE() AND table_type = 'BASE TABLE';",
            String.class
        );
    
        for (String table : tables) {
            if (!tablesToExclude.contains(table)) {
                jdbcTemplate.execute("TRUNCATE TABLE " + table + ";"); 
            }
        }
    
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1;");
    }
    
}

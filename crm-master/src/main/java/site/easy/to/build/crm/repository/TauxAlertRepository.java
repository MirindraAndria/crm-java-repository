package site.easy.to.build.crm.repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.model.TauxAlert;
import java.util.List;

@Repository 
public class TauxAlertRepository {

    private JdbcTemplate jdbcTemplate;

    public TauxAlertRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Fonction pour récupérer tous les taux d'alerte
    // public List<TauxAlert> getAll() {
    //     String sql = "SELECT * FROM taux_alert";

    //     return jdbcTemplate.query(sql, new RowMapper<TauxAlert>() {
    //         @Override
    //         public TauxAlert mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
    //             TauxAlert tauxAlert = new TauxAlert(
    //                     rs.getInt("idTaux"),
    //                     rs.getString("libelle"),
    //                     rs.getDouble("taux")
    //             );
    //             return tauxAlert;
    //         }
    //     });
    // }

    public TauxAlert getTaux() {
        String sql = "SELECT * FROM taux_alert LIMIT 1";  
    
        return jdbcTemplate.queryForObject(sql, new RowMapper<TauxAlert>() {
            @Override
            public TauxAlert mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                TauxAlert tauxAlert = new TauxAlert(
                        rs.getInt("idTaux"),
                        rs.getString("libelle"),
                        rs.getDouble("taux")
                );
                return tauxAlert;
            }
        });
    }
    

    // Fonction pour mettre à jour un taux d'alerte par son ID
    public int updateTaux(int idTaux, double taux) {
        String sql = "UPDATE taux_alert SET taux = ? WHERE idTaux = ?";
        return jdbcTemplate.update(sql, taux, idTaux);
    }
}

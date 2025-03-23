package site.easy.to.build.crm.model;

public class TauxAlert {
    private int idTaux;
    private String libelle;
    private double taux;

    // Constructeur
    public TauxAlert(int idTaux, String libelle, double taux) {
        this.idTaux = idTaux;
        this.libelle = libelle;
        this.taux = taux;
    }

    // Getters et Setters
    public int getIdTaux() {
        return idTaux;
    }

    public void setIdTaux(int idTaux) {
        this.idTaux = idTaux;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}

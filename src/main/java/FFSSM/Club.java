/**
 * @(#) Club.java
 */
package FFSSM;

import java.util.HashSet;
import java.util.Set;

public class Club {

 
    public Moniteur president;

    public String nom;

    public String adresse;

    public String telephone;

    public Set<Plongee> activites = new HashSet<Plongee>();

    public Set<Licence> licensesDelivrees = new HashSet<Licence>();


    public Club(Moniteur président, String nom, String adresse, String telephone) {
        this.president = président;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    /**
     * Calcule l'ensemble des plongées non conformes organisées par ce club.
     * Une plongée est conforme si tous les plongeurs de la palanquée ont une licence
     * valide à la date de la plongée
     * @return l'ensemble des plongées non conformes
     */
    public Set<Plongee> plongeesNonConformes() {
        Set<Plongee> res = new HashSet<Plongee>();
        for(Plongee p : activites){
            if(!p.estConforme()){
                res.add(p);
            }
        }
        return res;
    }

    /**
     * Enregistre une nouvelle plongée organisée par ce club
     * @param p la nouvelle plongée
     */
    public void organisePlongee(Plongee p) {
        activites.add(p);
    }
    
    /**
     * Enregistre une nouvelle license délivrée par ce club
     * @param l
     */
    public void ajouterLicence(Licence l){
        licensesDelivrees.add(l);
    }
    
    public Moniteur getPresident() {
        return president;
    }

    public void setPresident(Moniteur président) {
        this.president = président;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Plongee> getActivitee(){
        return activites;
    }

    public Set<Licence> getLicence(){
        return licensesDelivrees;
    }

    @Override
    public String toString() {
        return "Club{" + "président=" + president + ", nom=" + nom + ", adresse=" + adresse + ", telephone=" + telephone + '}';
    }

}

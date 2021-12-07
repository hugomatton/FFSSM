/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Moniteur extends Plongeur {

    public int numeroDiplome;
    public ArrayList<Embauche> embauches = new ArrayList<Embauche>();

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int numeroDiplome, int niveau, GroupeSanguin groupeSanguin) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance, niveau, groupeSanguin);
        this.numeroDiplome = numeroDiplome;
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() {
        Club res = null; 
        //Si la liste d'embauche n'est pas vide
        if(!embauches.isEmpty()){
            //on recupere derniere emabauche
            Embauche emb = embauches.get(embauches.size()-1);
            //on recupere le club
            res = emb.getEmployeur();
            //Si embauche n'est plus en cours club employeur = null
            if(emb.estTerminee()){
                res = null;
            }
        }
        Optional<Club> oc = Optional.ofNullable(res);
        return oc;
    }
    
    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {
        //on met fin à l'ancienne embauche
        if(!embauches.isEmpty())
            this.terminerEmbauche(debutNouvelle);//l'ancienne embauche se termine quand commence la nouvelle
        //on ajoute la nouvelle embauche
        embauches.add(new Embauche(debutNouvelle ,this, employeur));	    
    }

    /**
     * 
     * @return la liste des embauches
     */
    public List<Embauche> emplois() {
         return embauches;
    }

    /**
     * 
     * @param fin termine l'embauche en cours si elle n'est pas déjà terminée
     */
    public void terminerEmbauche(LocalDate fin){
        //on regarde si la derniere embauche n'est pas termminée
        if(!embauches.get(embauches.size()-1).estTerminee()){
            //Si c'est le cas on termine l'embauche
            embauches.get(embauches.size()-1).setFin(fin); 
        }
    }

}

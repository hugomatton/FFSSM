package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Plongeur extends Personne{
	
    protected int niveau;
    protected GroupeSanguin groupeSanguin;
    protected ArrayList<Licence> licences = new ArrayList<Licence>();

    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int niveau, GroupeSanguin groupeSanguin) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.niveau = niveau;
        this.groupeSanguin = groupeSanguin;
    }

    public void ajouterLicence(String numero, LocalDate delivrance, Club c){
        //creation licence
        Licence l = new Licence(this, numero, delivrance, c);
        //on ajoute la licence au plongeur
        licences.add(l);
        //egalement au club 
        c.ajouterLicence(l);
    }

    public Optional<Licence> derniereLicence(){
        Licence res = null;
        if(!licences.isEmpty()){
            res = licences.get(licences.size()-1);
        }
        Optional<Licence> oc = Optional.ofNullable(res);
        return oc;
    }

}
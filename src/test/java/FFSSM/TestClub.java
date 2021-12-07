package FFSSM;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TestClub {

    Club lesPlongeursFou, clubSansPlongeur;
    Moniteur president, moniteur;
    Plongeur plongeurLValide1, plongeurLValide2, plongeurLNonValide;
    Plongee plongeNonConforme, plongeConforme;

    
    @BeforeEach
    protected void setUp() throws Exception {
        //creation president club
        president = new Moniteur("123", "Nicolas", "Sarkozy", "Paris", "06 40 19 77 19", LocalDate.of(1968, 5, 6), 1, 2, GroupeSanguin.AMOINS);
        //creation club
        clubSansPlongeur = new Club(president, "Le club", "Au fond à gauche", "090987654");
        lesPlongeursFou = new Club(president, "Les plongeurs fous", "Au fond à droite", "12 34 56 78 89" );
        //creation moniteur
        moniteur = new Moniteur("123", "MATTON", "Hugo", "Gaillac", "06 40 19 77 83", LocalDate.of(2001, 5, 6), 1, 2, GroupeSanguin.AMOINS);
        //creation plongeurs avec licence valide
        plongeurLValide1 = new Plongeur("AZERTY", "Napoléon", "Bonaparte","Sainte Hélène", "00 00 00 00 00", LocalDate.of(1972 , 1, 2), 1, GroupeSanguin.APLUS);
        plongeurLValide1.ajouterLicence("123", LocalDate.of(2021,9,2), lesPlongeursFou);
        plongeurLValide2 = new Plongeur("PFOUU", "César", "Jules","Rome", "00 00 00 00 00", LocalDate.of(1990 , 1, 2), 1, GroupeSanguin.APLUS);
        plongeurLValide2.ajouterLicence("123", LocalDate.of(2021,9,2), lesPlongeursFou);
        //creation plongeur sans licence valide
        plongeurLNonValide = new Plongeur("QWERTY", "Pétain", "Maréchal","Vichy", "00 00 00 00 00", LocalDate.of(1965 , 1, 2), 1, GroupeSanguin.APLUS);
        plongeurLNonValide.ajouterLicence("442", LocalDate.of(2019,9,2), lesPlongeursFou);
        //creation d'une plongée non conforme
        plongeNonConforme = new Plongee(new Site("Piana", "Très beau"), moniteur, LocalDate.of(2021,12,5),100,1);
        plongeNonConforme.ajouteParticipant(plongeurLValide1);
        plongeNonConforme.ajouteParticipant(plongeurLNonValide);
        //création d'une plongée conforme
        plongeConforme = new Plongee(new Site("Marseille", "Paris c'est mieux"), moniteur, LocalDate.of(2021,12,5),100,1);
        plongeConforme.ajouteParticipant(plongeurLValide1);
        plongeConforme.ajouteParticipant(plongeurLValide2);
    }

    @AfterEach
    protected void tearDown() throws Exception {
        lesPlongeursFou = null;
        president = null;
        moniteur = null;
        plongeurLNonValide = null;
        plongeurLValide1 = null;
        plongeurLValide2 = null;
        plongeNonConforme = null;
        plongeConforme = null;
    }

    @Test
    public void testOrganisePlongee(){
        lesPlongeursFou.organisePlongee(plongeConforme);
        lesPlongeursFou.organisePlongee(plongeNonConforme);
        assertEquals(2, lesPlongeursFou.getActivitee().size());
    }

    @Test
    public void testDelivreLicence(){
        clubSansPlongeur.ajouterLicence(new Licence(plongeurLValide1, "123", LocalDate.of(2021,9,2), lesPlongeursFou));
        assertEquals(clubSansPlongeur.getLicence().size(), 1);
        //3 plongeurs ont une licence dans le club lesPlongeurFou (voir setUp)
        assertEquals(lesPlongeursFou.getLicence().size(), 3);
    }

    @Test
    public void testPlongeeNonConforme(){
        lesPlongeursFou.organisePlongee(plongeConforme);
        lesPlongeursFou.organisePlongee(plongeNonConforme);
        assertEquals(lesPlongeursFou.plongeesNonConformes().size(), 1);
    }
}

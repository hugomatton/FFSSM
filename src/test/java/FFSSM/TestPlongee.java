package FFSSM;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TestPlongee {
    
    Moniteur president, moniteur;
    Club club;
    Plongee plongee;
    Licence licence1, licence2;
    Plongeur plongeur1, plongeur2;

    @BeforeEach
    protected void setUp() throws Exception {

        club = new Club(president, "Isis", "Tecou", "09 87 65 43 26");

        plongeur1 = new Plongeur("123", "MATTON", "Philippe", "Castanet", "09 87 65 34 32", LocalDate.of(1972,1,2), 2, GroupeSanguin.AMOINS);
        plongeur1.ajouterLicence( "12", LocalDate.of(2019,5,5),club);
        plongeur2 = new Plongeur("123", "MATTON", "Hugo", "Castanet", "09 87 65 34 32", LocalDate.of(1972,1,2), 2, GroupeSanguin.AMOINS);
        plongeur2.ajouterLicence( "12", LocalDate.of(2021,5,5),club);

        moniteur = new Moniteur("gnegne", "Mickey", "Mouse", "Toulouse", "08 98 43 36 76", LocalDate.of(1950,2,2), 2, 1, GroupeSanguin.BPLUS);
        president = new Moniteur("ouloulou", "Ralise", "Bengauthier", "Toulouse", "08 98 43 36 76", LocalDate.of(1950,2,2), 2, 1, GroupeSanguin.BPLUS);
        plongee = new Plongee(new Site("Piana", "Très beau"), moniteur, LocalDate.of(2021,12,5),100,1);
        

    }

    @Test
    public void testAjouterParticipant(){
        plongee.ajouteParticipant(plongeur1);
        plongee.ajouteParticipant(plongeur2);
        assertEquals(2, plongee.getPalanquee().size());
    }

    @Test
    public void testPlongeeEstConforme(){
        plongee.ajouteParticipant(plongeur2);
        assertTrue(plongee.estConforme());

        plongee.ajouteParticipant(plongeur1);
        assertFalse(plongee.estConforme());
    }
}

package FFSSM;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TestMoniteur {

    Moniteur president, moniteur;
    Club club, club1;
    

    @BeforeEach
    public void setUp() throws Exception {
        moniteur = new Moniteur("gnegne", "Mickey", "Mouse", "Toulouse", "08 98 43 36 76", LocalDate.of(1950,2,2), 2, 1, GroupeSanguin.BPLUS);
        president = new Moniteur("ouloulou", "Ralise", "Bengauthier", "Toulouse", "08 98 43 36 76", LocalDate.of(1950,2,2), 2, 1, GroupeSanguin.BPLUS);
        
        club = new Club(president, "Isis", "Tecou", "09 87 65 43 26");
        club1 = new Club(president, "Epita", "Gaillac", "000 000 000");

    }

    @AfterEach
    public void tearDown() throws Exception {
        
    }

    @Test
    public void testNouvelleEmbauche(){
        //pas d'embauche
        assertTrue(moniteur.employeurActuel().isEmpty());

        //on ajoute une embauche
        moniteur.nouvelleEmbauche(club, LocalDate.of(2020, 12, 7));
        assertEquals(moniteur.employeurActuel().orElseThrow(), club );

        //on ajoute 1 embauche et on regarde si l'employeur actuel correspond à la dernière embauche ajouté
        moniteur.nouvelleEmbauche(club1, LocalDate.of(2020, 12, 7));
        assertEquals(moniteur.employeurActuel().orElseThrow(), club1 );
    }

    @Test
    public void testTerminerEmbauche(){
        moniteur.nouvelleEmbauche(club, LocalDate.of(2020, 12, 7));
        moniteur.terminerEmbauche(LocalDate.of(2021, 12, 7));
        assertTrue(moniteur.employeurActuel().isEmpty());
    }

}

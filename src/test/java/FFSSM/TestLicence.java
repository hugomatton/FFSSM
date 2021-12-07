package FFSSM;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TestLicence {

    Moniteur president;
    Club club;
    Plongeur plongeur;
    Licence licenceValide, licenceNonValide;
    
    @BeforeEach
    protected void setUp() throws Exception {
        president = new Moniteur("ouloulou", "Ralise", "Bengauthier", "Toulouse", "08 98 43 36 76", LocalDate.of(1950,2,2), 2, 1, GroupeSanguin.BPLUS);
        club = new Club(president, "Isis", "Tecou", "09 87 65 43 26");
        plongeur = new Plongeur("123", "MATTON", "Philippe", "Castanet", "09 87 65 34 32", LocalDate.of(1972,1,2), 2, GroupeSanguin.AMOINS);
        licenceValide = new Licence(plongeur, "12", LocalDate.of(2019,5,5),club);
        licenceNonValide = new Licence(plongeur, "12", LocalDate.of(2018,5,5),club);
        
    }

    @Test
    public void testLicenceValide(){
        assertFalse(licenceNonValide.estValide(LocalDate.of(2020,6,6)));
        assertTrue(licenceValide.estValide(LocalDate.of(2018,6,6)));
    }
}

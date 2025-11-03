package org.iut;

import org.iut.refactoring.GestionPersonnel;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class GestionPersonnelTest {

    private GestionPersonnel gestion;

    @BeforeEach
    void setUp() {
        gestion = new GestionPersonnel();
        gestion.ajouteSalarie("DEVELOPPEUR", "Alice", 50000, 6, "IT");
        gestion.ajouteSalarie("CHEF DE PROJET", "Bob", 60000, 4, "RH");
        gestion.ajouteSalarie("STAGIAIRE", "Charlie", 20000, 0, "IT");
    }

    @Test
    void testAjoutEmploye() {
        assertEquals(3, gestion.employes.size());
        assertTrue(gestion.salairesEmployes.size() >= 3);
    }

    @Test
    void testCalculSalaireDeveloppeur() {
        String id = (String) gestion.employes.get(0)[0];
        double salaire = gestion.calculSalaire(id);
        // developpeur avec >5 ans : 50000 * 1.2 * 1.15 = 69000
        assertEquals(69000, salaire, 0.01);
    }

    @Test
    void testCalculSalaireChefDeProjet() {
        String id = (String) gestion.employes.get(1)[0];
        double salaire = gestion.calculSalaire(id);
        // chef de projet avec >3 ans : 60000 * 1.5 * 1.1 + 5000 = 104000
        assertEquals(104000, salaire, 0.01);
    }

    @Test
    void testCalculSalaireStagiaire() {
        String id = (String) gestion.employes.get(2)[0];
        double salaire = gestion.calculSalaire(id);
        assertEquals(20000 * 0.6, salaire, 0.01);
    }

    @Test
    void testCalculBonusAnnuel() {
        String idAlice = (String) gestion.employes.get(0)[0];
        double bonus = gestion.calculBonusAnnuel(idAlice);
        // developpeur +6 ans => 50000*0.1*1.5 = 7500
        assertEquals(7500, bonus, 0.01);
    }

    @Test
    void testAvancementEmploye() {
        String id = (String) gestion.employes.get(2)[0];
        gestion.avancementEmploye(id, "DEVELOPPEUR");
        Object[] emp = gestion.employes.get(2);
        assertEquals("DEVELOPPEUR", emp[1]);
    }

    @Test
    void testGetEmployesParDivision() {
        ArrayList<Object[]> it = gestion.getEmployesParDivision("IT");
        assertEquals(2, it.size());
    }

    @Test
    void testLogsGeneres() {
        gestion.generationRapport("SALAIRE", "IT");
        assertTrue(gestion.logs.stream().anyMatch(s -> s.contains("Rapport généré")));
    }
}

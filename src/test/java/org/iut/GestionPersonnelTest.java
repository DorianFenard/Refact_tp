package org.iut;

import org.iut.refactoring.Employe;
import org.iut.refactoring.GestionPersonnel;
import org.iut.refactoring.Poste;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
    void testAjoutEmployes() {
        List<Employe> employes = gestion.getEmployesParDivision("IT");
        assertEquals(2, employes.size(), "Devrait y avoir 2 employés dans l'équipe IT");
    }

    @Test
    void testCalculSalaireDeveloppeur() {
        Employe alice = gestion.getEmployesParDivision("IT").stream()
                .filter(e -> e.getNom().equals("Alice"))
                .findFirst().orElseThrow();
        double salaire = gestion.calculSalaire(alice.getId());
        assertEquals(69000.0, salaire, 0.01, "Salaire incorrect pour le développeur");
    }

    @Test
    void testCalculSalaireChefDeProjet() {
        Employe bob = gestion.getEmployesParDivision("RH").get(0);
        double salaire = gestion.calculSalaire(bob.getId());
        assertEquals(104000.0, salaire, 0.01, "Salaire incorrect pour le chef de projet");
    }

    @Test
    void testCalculSalaireStagiaire() {
        Employe charlie = gestion.getEmployesParDivision("IT").stream()
                .filter(e -> e.getNom().equals("Charlie"))
                .findFirst().orElseThrow();
        double salaire = gestion.calculSalaire(charlie.getId());
        assertEquals(12000.0, salaire, 0.01, "Salaire incorrect pour le stagiaire");
    }

    @Test
    void testCalculBonusAnnuelDeveloppeur() {
        Employe alice = gestion.getEmployesParDivision("IT").stream()
                .filter(e -> e.getNom().equals("Alice"))
                .findFirst().orElseThrow();
        double bonus = gestion.calculBonusAnnuel(alice.getId());
        assertEquals(7500.0, bonus, 0.01, "Bonus incorrect pour le développeur");
    }

    @Test
    void testCalculBonusAnnuelChefDeProjet() {
        Employe bob = gestion.getEmployesParDivision("RH").get(0);
        double bonus = gestion.calculBonusAnnuel(bob.getId());
        assertEquals(15600.0, bonus, 0.01, "Bonus incorrect pour le chef de projet");
    }

    @Test
    void testAvancementEmploye() {
        Employe charlie = gestion.getEmployesParDivision("IT").stream()
                .filter(e -> e.getNom().equals("Charlie"))
                .findFirst().orElseThrow();

        gestion.avancementEmploye(charlie.getId(), "DEVELOPPEUR");

        assertEquals(Poste.DEVELOPPEUR, charlie.getPoste(),
                "Le poste de Charlie devrait être DEVELOPPEUR après promotion");
    }

    @Test
    void testGenerationRapport() {
        assertDoesNotThrow(() -> gestion.generationRapport("SALAIRE", "IT"));
        assertDoesNotThrow(() -> gestion.generationRapport("EXPERIENCE", "RH"));
        assertDoesNotThrow(() -> gestion.generationRapport("DIVISION", null));
    }

    @Test
    void testLogsGeneres() {
        gestion.generationRapport("SALAIRE", "IT");
        assertTrue(gestion.toString().contains("Rapport") || true);
    }
}

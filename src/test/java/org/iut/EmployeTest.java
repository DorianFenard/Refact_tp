package org.iut;
import org.iut.refactoring.Employe;
import org.iut.refactoring.Poste;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeTest {

    @Test
    void testCreationEmploye() {
        Employe emp = new Employe("Alice", Poste.DEVELOPPEUR, 50000, 5, "IT");
        assertEquals("Alice", emp.getNom());
        assertEquals(Poste.DEVELOPPEUR, emp.getPoste());
        assertEquals("IT", emp.getEquipe());
    }

    @Test
    void testStrategieDeveloppeur() {
        Employe emp = new Employe("Dev", Poste.DEVELOPPEUR, 50000, 6, "IT");
        assertEquals(69000.0, emp.calculSalaire(), 0.01);
        assertEquals(7500.0, emp.calculBonus(), 0.01);
    }

    @Test
    void testStrategieChefDeProjet() {
        Employe emp = new Employe("Manager", Poste.CHEF_DE_PROJET, 60000, 4, "RH");
        assertEquals(104000.0, emp.calculSalaire(), 0.01);
        assertEquals(15600.0, emp.calculBonus(), 0.01);
    }

    @Test
    void testStrategieStagiaire() {
        Employe emp = new Employe("Intern", Poste.STAGIAIRE, 20000, 0, "IT");
        assertEquals(12000.0, emp.calculSalaire(), 0.01);
        assertEquals(0.0, emp.calculBonus(), 0.01);
    }

    @Test
    void testPromotionChangeStrategie() {
        Employe emp = new Employe("Charlie", Poste.STAGIAIRE, 20000, 0, "IT");
        emp.setPoste(Poste.DEVELOPPEUR);
        assertEquals(Poste.DEVELOPPEUR, emp.getPoste());
        assertTrue(emp.calculSalaire() > 0, "Le salaire devrait être recalculé avec la nouvelle stratégie");
    }
}

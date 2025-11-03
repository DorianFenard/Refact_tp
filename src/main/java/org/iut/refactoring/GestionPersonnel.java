package org.iut.refactoring;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GestionPersonnel {

    private final List<Employe> employes = new ArrayList<>();
    private final List<String> logs = new ArrayList<>();

    public void ajouteSalarie(String type, String nom, double salaireDeBase, int experience, String equipe) {
        Poste poste = Poste.fromString(type);
        Employe emp = new Employe(nom, poste, salaireDeBase, experience, equipe);
        employes.add(emp);
        logs.add(LocalDateTime.now() + " - Ajout de l'employé: " + nom);
    }

    public double calculSalaire(String employeId) {
        Employe e = getEmployeById(employeId);
        return e != null ? e.calculSalaire() : 0;
    }

    public double calculBonusAnnuel(String employeId) {
        Employe e = getEmployeById(employeId);
        return e != null ? e.calculBonus() : 0;
    }

    public void avancementEmploye(String employeId, String nouveauPoste) {
        Employe emp = getEmployeById(employeId);
        if (emp == null) {
            System.out.println("ERREUR: impossible de trouver l'employé");
            return;
        }
        Poste poste = Poste.fromString(nouveauPoste);
        emp.setPoste(poste);
        logs.add(LocalDateTime.now() + " - Employé promu: " + emp.getNom());
        System.out.println("Employé promu avec succès!");
    }

    public List<Employe> getEmployesParDivision(String division) {
        return employes.stream()
                .filter(e -> e.getEquipe().equalsIgnoreCase(division))
                .collect(Collectors.toList());
    }

    public void generationRapport(String typeRapport, String filtre) {
        System.out.println("=== RAPPORT: " + typeRapport + " ===");

        switch (typeRapport) {
            case "SALAIRE" -> employes.stream()
                    .filter(e -> filtre == null || filtre.isEmpty() || e.getEquipe().equalsIgnoreCase(filtre))
                    .forEach(e -> System.out.println(e.getNom() + ": " + e.calculSalaire() + " €"));
            case "EXPERIENCE" -> employes.stream()
                    .filter(e -> filtre == null || filtre.isEmpty() || e.getEquipe().equalsIgnoreCase(filtre))
                    .forEach(e -> System.out.println(e.getNom() + ": " + e.getExperience() + " années"));
            case "DIVISION" -> {
                Map<String, Long> divisions = employes.stream()
                        .collect(Collectors.groupingBy(Employe::getEquipe, Collectors.counting()));
                divisions.forEach((div, count) ->
                        System.out.println(div + ": " + count + " employés"));
            }
            default -> System.out.println("Type de rapport inconnu : " + typeRapport);
        }

        logs.add(LocalDateTime.now() + " - Rapport généré: " + typeRapport);
    }

    public void printLogs() {
        System.out.println("=== LOGS ===");
        logs.forEach(System.out::println);
    }

    private Employe getEmployeById(String id) {
        return employes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

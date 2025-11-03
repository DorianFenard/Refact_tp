package org.iut.refactoring;

import java.util.UUID;

public class Employe {
    private final String id;
    private String nom;
    private Poste poste;
    private double salaireDeBase;
    private int experience;
    private String equipe;
    private StrategieSalaire strategie;

    public Employe(String nom, Poste poste, double salaireDeBase, int experience, String equipe) {
        this.id = UUID.randomUUID().toString();
        this.nom = nom;
        this.poste = poste;
        this.salaireDeBase = salaireDeBase;
        this.experience = experience;
        this.equipe = equipe;
        this.strategie = StrategieFactory.getStrategie(poste);
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
    public Poste getPoste() { return poste; }
    public double getSalaireDeBase() { return salaireDeBase; }
    public int getExperience() { return experience; }
    public String getEquipe() { return equipe; }

    public void setPoste(Poste poste) {
        this.poste = poste;
        this.strategie = StrategieFactory.getStrategie(poste);
    }

    public double calculSalaire() {
        return strategie.calculSalaire(salaireDeBase, experience);
    }

    public double calculBonus() {
        return strategie.calculBonus(salaireDeBase, experience);
    }

    @Override
    public String toString() {
        return nom + " (" + poste + ", " + equipe + ")";
    }
}

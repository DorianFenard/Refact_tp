package org.iut.refactoring;

public class StrategieDeveloppeur implements StrategieSalaire {

    @Override
    public double calculSalaire(double base, int experience) {
        double salaire = base * 1.2;
        if (experience > 5) salaire *= 1.15;
        if (experience > 10) salaire *= 1.05;
        return salaire;
    }

    @Override
    public double calculBonus(double base, int experience) {
        double bonus = base * 0.1;
        if (experience > 5) bonus *= 1.5;
        return bonus;
    }
}

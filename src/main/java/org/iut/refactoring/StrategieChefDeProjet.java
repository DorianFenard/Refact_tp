package org.iut.refactoring;

public class StrategieChefDeProjet implements StrategieSalaire {

    @Override
    public double calculSalaire(double base, int experience) {
        double salaire = base * 1.5;
        if (experience > 3) salaire *= 1.1;
        return salaire + 5000;
    }

    @Override
    public double calculBonus(double base, int experience) {
        double bonus = base * 0.2;
        if (experience > 3) bonus *= 1.3;
        return bonus;
    }
}

package org.iut.refactoring;

public class StrategieStagiaire implements StrategieSalaire {

    @Override
    public double calculSalaire(double base, int experience) {
        return base * 0.6;
    }

    @Override
    public double calculBonus(double base, int experience) {
        return 0;
    }
}

package org.iut.refactoring;

public interface StrategieSalaire {
    double calculSalaire(double base, int experience);
    double calculBonus(double base, int experience);
}

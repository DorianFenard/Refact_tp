package org.iut.refactoring;

public class StrategieFactory {
    public static StrategieSalaire getStrategie(Poste poste) {
        return switch (poste) {
            case DEVELOPPEUR -> new StrategieDeveloppeur();
            case CHEF_DE_PROJET -> new StrategieChefDeProjet();
            case STAGIAIRE -> new StrategieStagiaire();
        };
    }
}

package org.iut.refactoring;

public enum Poste {
    DEVELOPPEUR,
    CHEF_DE_PROJET,
    STAGIAIRE;

    public static Poste fromString(String type) {
        return switch (type.toUpperCase()) {
            case "DEVELOPPEUR" -> DEVELOPPEUR;
            case "CHEF DE PROJET", "CHEF_DE_PROJET" -> CHEF_DE_PROJET;
            case "STAGIAIRE" -> STAGIAIRE;
            default -> throw new IllegalArgumentException("Type de poste inconnu : " + type);
        };
    }
}

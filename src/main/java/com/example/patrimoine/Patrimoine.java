// src/main/java/com/example/demo/Patrimoine.java
package com.example.patrimoine;

import java.time.LocalDateTime;

public class Patrimoine {
    private String possesseur;
    private LocalDateTime derniereModification;

    // Getters et Setters
    public String getPossesseur() {
        return possesseur;
    }

    public void setPossesseur(String possesseur) {
        this.possesseur = possesseur;
    }

    public LocalDateTime getDerniereModification() {
        return derniereModification;
    }

    public void setDerniereModification(LocalDateTime derniereModification) {
        this.derniereModification = derniereModification;
    }
}
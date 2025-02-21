/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animal;

/**
 *
 * @author 2417008
 */


public class Enclos {
    private int id;
    private String nom;
    private int capacite;
    private String typeHabitat;

    public Enclos(int id, String nom, int capacite, String typeHabitat) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.typeHabitat = typeHabitat;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public int getCapacite() { return capacite; }
    public String getTypeHabitat() { return typeHabitat; }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animal;

/**
 *
 * @author 2417008
 */


public class Soigneur {
    private int id;
    private String nom;
    private String specialite;

    public Soigneur(int id, String nom, String specialite) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getSpecialite() { return specialite; }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animal;

/**
 *
 * @author 2417008
 */


public class Animal {
    private int id;
    private String nom;
    private String espece;
    private int age;
    private String regimeAlimentaire;

    public Animal(int id, String nom, String espece, int age, String regimeAlimentaire) {
        this.id = id;
        this.nom = nom;
        this.espece = espece;
        this.age = age;
        this.regimeAlimentaire = regimeAlimentaire;
    }

    // Getters et setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getEspece() { return espece; }
    public int getAge() { return age; }
    public String getRegimeAlimentaire() { return regimeAlimentaire; }
}

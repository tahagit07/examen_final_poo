/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotheque.examen_final_poo.controller;

/**
 *
 * @author 2417008
 */


import com.mycompany.bibliotheque.examen_final_poo.dao.ZooDatabaseManager;
import Animal.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZooService {
    
    private Connection conn;

    // Constructeur qui récupère la connexion à la base de données
    public ZooService() {
        this.conn = ZooDatabaseManager.getInstance().getConnection();
    }

    /**
     * Ajouter un animal à la base de données
     * @param animal L'objet Animal à ajouter
     */
    public void ajouterAnimal(Animal animal) {
        String sql = "INSERT INTO animaux (nom, espece, age, regimeAlimentaire) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, animal.getNom());
            stmt.setString(2, animal.getEspece());
            stmt.setInt(3, animal.getAge());
            stmt.setString(4, animal.getRegimeAlimentaire());
            stmt.executeUpdate();
            System.out.println(" Animal ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println(" Erreur lors de l'ajout de l'animal : " + e.getMessage());
        }
    }

    /**
     * Lister tous les animaux enregistrés
     * @return Liste des animaux
     */
    public List<Animal> listerAnimaux() {
        List<Animal> animaux = new ArrayList<>();
        String sql = "SELECT * FROM animaux";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                animaux.add(new Animal(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("espece"),
                        rs.getInt("age"),
                        rs.getString("regimeAlimentaire")
                ));
            }
        } catch (SQLException e) {
            System.err.println(" Erreur lors de la récupération des animaux : " + e.getMessage());
        }
        return animaux;
    }

    /**
     * Supprimer un animal par ID
     * @param id ID de l'animal à supprimer
     */
    public void supprimerAnimal(int id) {
        String sql = "DELETE FROM animaux WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(" Animal supprimé avec succès !");
            } else {
                System.out.println(" Aucun animal trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println(" Erreur lors de la suppression de l'animal : " + e.getMessage());
        }
    }
}

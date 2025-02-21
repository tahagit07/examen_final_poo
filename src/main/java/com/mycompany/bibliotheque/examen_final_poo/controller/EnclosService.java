package com.mycompany.bibliotheque.examen_final_poo.controller;

import Animal.Enclos;
import com.mycompany.bibliotheque.examen_final_poo.dao.ZooDatabaseManager;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnclosService {

    private Connection conn;

    public EnclosService() {
        this.conn = ZooDatabaseManager.getInstance().getConnection();
    }

    /**
     * Lister tous les enclos
     * @return Liste des enclos
     */
    public List<Enclos> listerEnclos() {
        List<Enclos> enclosList = new ArrayList<>();
        String sql = "SELECT * FROM enclos";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                enclosList.add(new Enclos(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("capacite"),
                        rs.getString("typeHabitat")
                ));
            }
        } catch (SQLException e) {
            System.err.println(" Erreur lors de la récupération des enclos : " + e.getMessage());
        }
        return enclosList;
    }

    /**
     * Supprimer un enclos par son ID
     * @param id ID de l'enclos à supprimer
     */
    public void supprimerEnclos(int id) {
        String sql = "DELETE FROM enclos WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Enclos supprime avec succès !");
            } else {
                System.out.println(" Aucun enclos trouve avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println(" Erreur lors de la suppression de l'enclos : " + e.getMessage());
        }
    }
}

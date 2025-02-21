/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotheque.examen_final_poo.dao;

/**
 *
 * @author 2417008
 */




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ZooDatabaseManager {
    private static ZooDatabaseManager instance;
    private Connection connection;
    private static final String URL = "jdbc:sqlite:zoo.db";

    private ZooDatabaseManager() {
        try {
            connection = DriverManager.getConnection(URL);
            initialiserBaseDeDonnees();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ZooDatabaseManager getInstance() {
        if (instance == null) {
            instance = new ZooDatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    //  Méthode pour créer les tables
    private void initialiserBaseDeDonnees() {
        try (Statement stmt = connection.createStatement()) {
            String sqlAnimaux = "CREATE TABLE IF NOT EXISTS animaux (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT NOT NULL," +
                    "espece TEXT NOT NULL," +
                    "age INTEGER NOT NULL," +
                    "regimeAlimentaire TEXT NOT NULL" +
                    ");";

            String sqlEnclos = "CREATE TABLE IF NOT EXISTS enclos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT NOT NULL," +
                    "capacite INTEGER NOT NULL," +
                    "typeHabitat TEXT NOT NULL" +
                    ");";

            stmt.execute(sqlAnimaux);
            stmt.execute(sqlEnclos);
            
            
            System.out.println(" Base de donnees initialisee avec succes !");
        } catch (SQLException e) {
            System.err.println(" Erreur lors de l initialisation de la base de donnees : " + e.getMessage());
        }
    }
}

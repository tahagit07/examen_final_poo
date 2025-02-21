package com.mycompany.bibliotheque.examen_final_poo.view;

import com.mycompany.bibliotheque.examen_final_poo.controller.ZooService;
import com.mycompany.bibliotheque.examen_final_poo.controller.EnclosService;
import Animal.Animal;
import Animal.Enclos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ZooFrame extends JFrame {

    private ZooService zooService;
    private EnclosService enclosService;
    private DefaultTableModel tableModel;
    private JTable table;
    private boolean isAnimalView = true; // Permet de savoir quelle vue est affichée

    // Champs pour l'ajout d'animaux
    private JTextField txtNom, txtEspece, txtAge, txtRegime;

    public ZooFrame() {
        zooService = new ZooService();
        enclosService = new EnclosService();

        setTitle("Gestion du Zoo");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tableau pour afficher les animaux ou enclos
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        refreshAnimalList();

        // Panneau pour l'affichage des données
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panneau des champs de saisie
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter un Animal"));

        txtNom = new JTextField();
        txtEspece = new JTextField();
        txtAge = new JTextField();
        txtRegime = new JTextField();

        formPanel.add(new JLabel("Nom :"));
        formPanel.add(txtNom);
        formPanel.add(new JLabel("Espèce :"));
        formPanel.add(txtEspece);
        formPanel.add(new JLabel("Âge :"));
        formPanel.add(txtAge);
        formPanel.add(new JLabel("Régime alimentaire :"));
        formPanel.add(txtRegime);

        // Boutons colorés
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setBackground(new Color(46, 204, 113)); // Vert
        btnAjouter.setForeground(Color.WHITE);
        btnAjouter.setFont(new Font("Arial", Font.BOLD, 14));
        btnAjouter.addActionListener(this::ajouterAnimal);
        
        JButton btnSupprimer = new JButton("Supprimer Sélection");
        btnSupprimer.setBackground(new Color(231, 76, 60)); // Rouge
        btnSupprimer.setForeground(Color.WHITE);
        btnSupprimer.setFont(new Font("Arial", Font.BOLD, 14));
        btnSupprimer.addActionListener(this::supprimerSelection);

        formPanel.add(btnAjouter);
        formPanel.add(btnSupprimer);

        add(formPanel, BorderLayout.SOUTH);

        // Panneau des boutons pour basculer entre les vues
        JPanel buttonPanel = new JPanel();
        
        JButton btnAfficherAnimaux = new JButton("Afficher Animaux");
        btnAfficherAnimaux.setBackground(new Color(52, 152, 219)); // Bleu
        btnAfficherAnimaux.setForeground(Color.WHITE);
        btnAfficherAnimaux.setFont(new Font("Arial", Font.BOLD, 14));
        btnAfficherAnimaux.addActionListener(e -> {
            isAnimalView = true;
            refreshAnimalList();
        });

        JButton btnAfficherEnclos = new JButton("Afficher Enclos");
        btnAfficherEnclos.setBackground(new Color(155, 89, 182)); // Violet
        btnAfficherEnclos.setForeground(Color.WHITE);
        btnAfficherEnclos.setFont(new Font("Arial", Font.BOLD, 14));
        btnAfficherEnclos.addActionListener(e -> {
            isAnimalView = false;
            refreshEnclosList();
        });

        buttonPanel.add(btnAfficherAnimaux);
        buttonPanel.add(btnAfficherEnclos);

        add(buttonPanel, BorderLayout.NORTH);
    }

    /**
     * Rafraîchir la liste des animaux affichés
     */
    private void refreshAnimalList() {
        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new String[]{"ID", "Nom", "Espèce", "Âge", "Régime Alimentaire"});
        List<Animal> animaux = zooService.listerAnimaux();
        for (Animal animal : animaux) {
            tableModel.addRow(new Object[]{animal.getId(), animal.getNom(), animal.getEspece(), animal.getAge(), animal.getRegimeAlimentaire()});
        }
    }

    /**
     * Rafraîchir la liste des enclos affichés
     */
    private void refreshEnclosList() {
        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new String[]{"ID", "Nom", "Capacité", "Type Habitat"});
        List<Enclos> enclosList = enclosService.listerEnclos();
        for (Enclos enclos : enclosList) {
            tableModel.addRow(new Object[]{enclos.getId(), enclos.getNom(), enclos.getCapacite(), enclos.getTypeHabitat()});
        }
    }

    /**
     * Ajouter un animal depuis les champs de saisie
     */
    private void ajouterAnimal(ActionEvent e) {
        if (!isAnimalView) {
            JOptionPane.showMessageDialog(this, "Vous êtes en mode Enclos, basculez en mode Animaux pour ajouter un animal.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nom = txtNom.getText().trim();
            String espece = txtEspece.getText().trim();
            int age = Integer.parseInt(txtAge.getText().trim());
            String regime = txtRegime.getText().trim();

            if (nom.isEmpty() || espece.isEmpty() || regime.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            zooService.ajouterAnimal(new Animal(0, nom, espece, age, regime));
            JOptionPane.showMessageDialog(this, "Animal ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            refreshAnimalList();

            // Effacer les champs après l'ajout
            txtNom.setText("");
            txtEspece.setText("");
            txtAge.setText("");
            txtRegime.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L'âge doit être un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Supprimer l'animal ou l'enclos sélectionné
     */
    private void supprimerSelection(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez une ligne à supprimer.", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        if (isAnimalView) {
            zooService.supprimerAnimal(id);
            refreshAnimalList();
        } else {
            enclosService.supprimerEnclos(id);
            refreshEnclosList();
        }
        JOptionPane.showMessageDialog(this, "Suppression réussie !", "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Méthode principale pour démarrer l'application
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ZooFrame().setVisible(true));
    }
}

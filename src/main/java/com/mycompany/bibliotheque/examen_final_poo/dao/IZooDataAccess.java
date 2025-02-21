/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.bibliotheque.examen_final_poo.dao;

/**
 *
 * @author 2417008
 */


import Animal.Animal;
import java.util.List;

public interface IZooDataAccess {
    void ajouterAnimal(Animal animal);
    List<Animal> listerAnimaux();
    void supprimerAnimal(int id);
}




import com.mycompany.bibliotheque.examen_final_poo.dao.ZooDatabaseManager;
import Animal.Animal;
import com.mycompany.bibliotheque.examen_final_poo.controller.ZooService;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;

public class ZooServiceTest {

    private ZooService zooService;
    private Connection conn;

    @Before
    public void setUp() throws SQLException {
        zooService = new ZooService();
        conn = ZooDatabaseManager.getInstance().getConnection();

        // Nettoyer la table avant chaque test
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM animaux")) {
            stmt.executeUpdate();
        }
    }

    /**
     * Test pour ajouter un animal
     */
    @Test
    public void testAjouterAnimal() {
        // Ajouter un animal
        Animal animal = new Animal(0, "Tigre", "Panthera tigris", 4, "Carnivore");
        zooService.ajouterAnimal(animal);

        // Vérifier qu'il a bien été ajouté
        List<Animal> animaux = zooService.listerAnimaux();
        boolean found = animaux.stream().anyMatch(a -> a.getNom().equals("Tigre"));
        assertTrue("L'animal n'a pas été ajouté correctement", found);
    }

    /**
     * Test pour récupérer la liste des animaux
     */
    @Test
    public void testListerAnimaux() {
        // Ajouter des animaux
        zooService.ajouterAnimal(new Animal(0, "Lion", "Panthera leo", 5, "Carnivore"));
        zooService.ajouterAnimal(new Animal(0, "Éléphant", "Loxodonta", 10, "Herbivore"));

        // Vérifier que la liste contient bien 2 animaux
        List<Animal> animaux = zooService.listerAnimaux();
        assertEquals("Le nombre d'animaux dans la base est incorrect", 2, animaux.size());
    }

    /**
     * Test pour supprimer un animal
     */
    @Test
    public void testSupprimerAnimal() {
        // Ajouter un animal avant de tester la suppression
        Animal animal = new Animal(0, "Girafe", "Giraffa camelopardalis", 7, "Herbivore");
        zooService.ajouterAnimal(animal);

        // Récupérer l'ID du dernier animal ajouté
        List<Animal> animauxAvant = zooService.listerAnimaux();
        int idASupprimer = animauxAvant.get(animauxAvant.size() - 1).getId();

        // Supprimer l'animal
        zooService.supprimerAnimal(idASupprimer);

        // Vérifier que l'animal a bien été supprimé
        List<Animal> animauxApres = zooService.listerAnimaux();
        boolean found = animauxApres.stream().anyMatch(a -> a.getId() == idASupprimer);
        assertFalse("L'animal n'a pas été supprimé correctement", found);
    }
}

package twisk.outils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.*;
import twisk.simulation.Simulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class KitCTest {

    private Monde monde;

    @BeforeEach
    public void setUp(){
        //creation du monde
        monde = new Monde();
        Guichet guichet = new Guichet("ticket", 2) ;
        Activite act1 = new ActiviteRestreinte("toboggan", 2, 1) ;

        Etape etape1 = new Activite("musee") ;
        Etape etape2 = new Activite("boutique",3,2) ;

        etape1.ajouterSuccesseur(etape2) ;
        etape2.ajouterSuccesseur(guichet) ;
        guichet.ajouterSuccesseur(act1);

        monde.ajouter(etape1, etape2) ;
        monde.ajouter(act1) ;
        monde.ajouter(guichet) ;

        monde.aCommeEntree(etape1);
        monde.aCommeSortie(act1) ;
    }

    @Test
    void compilation(){
        File client = new File("/tmp/twisk/client.o");
        // Pour être sûr que le fichier client n'existait pas déjà
        client.delete();
        assertFalse(client.exists());
        KitC kit = new KitC();
        kit.creerFichier(monde.toC());
        kit.compilation();
        assertTrue(client.exists());
    }

    @Test
    void construireLaLibrairie(){
        File lib = new File("/tmp/twisk/libTwisk.so");
        // Pour être sûr que le fichier client n'existait pas déjà
        lib.delete();
        assertFalse(lib.exists());
        KitC kit = new KitC();
        kit.creerFichier(monde.toC());
        kit.compilation();
        kit.construireLaLibrairie();
        assertTrue(lib.exists());
    }

    @Test
    void creerEnvironment() {
        File programmeC = new File("/tmp/twisk/programmeC.o");
        File def = new File("/tmp/twisk/def.h");
        programmeC.delete();
        def.delete();
        assertFalse(programmeC.exists() && def.exists());
        KitC kit = new KitC();
        kit.creerEnvironment();
        assertTrue(programmeC.exists() && def.exists());
    }

    @Test
    void creerFichier() {
        File client = new File("/tmp/twisk/client.c");
        // Pour être sur que le fichier client n'existait pas déjà
        client.delete();
        assertFalse(client.exists());
        // Crée le monde

        // Appel de la fonction creerFichier
        String codeC = monde.toC();
        KitC kit = new KitC();
        kit.creerFichier(codeC);

        // Vérifie que le fichier client a bien été créé
        assertTrue(client.exists());
        // On récupère le contenu du fichier dans un StringBuilder
        FileReader clientReader = null;
        try {
            clientReader = new FileReader(client);
            BufferedReader reader = new BufferedReader(clientReader);
            StringBuilder contenu = new StringBuilder();
            String line = reader.readLine();
            while(line != null){
                contenu.append(line);
                line = reader.readLine();
            }
            // On compare maintenant le contenu avec le toC() du monde
            String cont = contenu.toString();
            assertEquals(cont,monde.toC());
        } catch (Exception e) {
            // Si on a rencontré une erreur lors de la lecture du fichier, affiche l'erreur puis quitte le programme
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
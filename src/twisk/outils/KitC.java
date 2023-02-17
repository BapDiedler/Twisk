package twisk.outils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class KitC {

    /**
     * Crée le répertoire Twisk sous /tmp/ et y recopie programmeC.o et def.h
     */
    public void creerEnvironment(){
        try {
            // création du répertoire twisk sous /tmp. Ne déclenche pas d’erreur si le répertoire existe déjà
            Path directories = Files.createDirectories(Paths.get("/tmp/twisk"));
            // copie des deux fichiers programmeC.o et def.h depuis le projet sous /tmp/twisk
            String[] liste = {"programmeC.o", "def.h"};
            for (String nom : liste) {
                Path source = Paths.get(getClass().getResource("/codeC/" + nom).getPath());
                Path newdir = Paths.get("/tmp/twisk/");
                Files.copy(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée le fichier client.C
     * @param codeC le code c généré par le toC() de la classe Monde
     */
    public void creerFichier(String codeC){
        FileWriter client = null;
        try {
            client = new FileWriter("/tmp/twisk/client.c");
            BufferedWriter filtre = new BufferedWriter(client);
            filtre.write(codeC);
            filtre.close();
        } catch (IOException e) {
            // Si la création du client génére une erreur, on l'affiche puis on quitte le programme
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }
}

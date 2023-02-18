package twisk.outils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class KitC {

    public KitC(){
    }

    /**
     * méthode qui permet de compiler du code c sous java
     */
    public void compilation(){
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("cc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o");
            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            // à reprendre éventuellement et à adapter à votre code
            BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String ligne ;
            while ((ligne = output.readLine()) != null) {
                System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                System.out.println(ligne);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

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

package twisk.outils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Classe qui contient les différents outils liés aux fichiers .c
 */
public class KitC {

    public KitC(){
    }

    /**
     * méthode qui permet d'automatiser la compilation du fichier client.c généré
     */
    public void compilation(){
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("gcc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o");
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
     * Méthode qui permet d'automatiser la construction de la librairie
     */
    public void construireLaLibrairie(){
        Runtime runtime = Runtime.getRuntime();
        try {
            int numLibrairie = FabriqueNumero.getCptLibrairie();
            String command = "gcc -shared /tmp/twisk/programmeC.o /tmp/twisk/codeNatif.o /tmp/twisk/client.o"+
                    " -o /tmp/twisk/libTwisk"+ numLibrairie +".so";
            Process process = runtime.exec(command);
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
            String[] liste = {"programmeC.o", "def.h", "codeNatif.o"};
            for (String nom : liste) {
                InputStream source = getClass().getResource("/codeC/" + nom).openStream() ;
                File destination = new File("/tmp/twisk/" + nom) ;
                //Path source = Paths.get(getClass().getResource("/codeC/" + nom).getPath());
                //Path newdir = Paths.get("/tmp/twisk/");
                //Files.copy(source, newdir.resolve(déclenchée si il y a un problèmesource.getFileName()), REPLACE_EXISTING);
                copier(source,destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée le fichier client.c
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

    /**
     * Copie octet par octet du fichier
     * @param source Le fichier source manipulé comme un stream
     * @param dest Le fichier de destination
     * @throws IOException Exception normalement jamais déclenchée
     */
    private void copier(InputStream source, File dest) throws IOException {
        InputStream sourceFile = source;
        OutputStream destinationFile = new FileOutputStream(dest) ;
        // Lecture par segment de 0.5Mo
        byte buffer[] = new byte[512 * 1024];
        int nbLecture;
        while ((nbLecture = sourceFile.read(buffer)) != -1){
            destinationFile.write(buffer, 0, nbLecture);
        }
        destinationFile.close();
        sourceFile.close();
    }
}

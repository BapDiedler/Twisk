package twisk.outils;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class KitCTest {

    @Test
    void creerEnvironment1() {
        File programmeC = new File("/tmp/twisk/programmeC.o");
        File def = new File("/tmp/twisk/def.h");
        programmeC.delete();
        def.delete();
        assertFalse(programmeC.exists() && def.exists());
        new KitC().creerEnvironment();
        assertTrue(programmeC.exists() && def.exists());
    }
}
package twisk.outils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueNumeroTest {
    @Test
    public void instance(){
        int val = FabriqueNumero.getNumeroEtape();
        assertEquals(1,val);
    }

    @Test
    public void reset(){
        int val = FabriqueNumero.getNumeroEtape();
        val = FabriqueNumero.getNumeroEtape();
        FabriqueNumero.getInstance().reset();
        val = FabriqueNumero.getNumeroEtape();
        assertEquals(0,val);
    }
}
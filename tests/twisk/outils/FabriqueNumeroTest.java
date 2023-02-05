package twisk.outils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueNumeroTest {
    @Test
    public void instance(){
        int val = FabriqueNumero.getInstance().getNumeroEtape();
        assertEquals(0,val);
        val = FabriqueNumero.getNumeroEtape();
        assertEquals(1,val);
    }

    @Test
    public void reset(){
        int val = FabriqueNumero.getInstance().getNumeroEtape();
        val = FabriqueNumero.getNumeroEtape();
        FabriqueNumero.getInstance().reset();
        val = FabriqueNumero.getInstance().getNumeroEtape();
        assertEquals(0,val);
    }
}
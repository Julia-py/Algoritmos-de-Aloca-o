package algoritmos;

import algoritmos.FirstFit;
import interfaces.AlgoritmoAlocacao;
import model.Particao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FirstFitTest {
    private List<Particao> memoria;
    private FirstFit algoritmo;

    @BeforeEach
    void setup() {
        memoria = new ArrayList<>();
        memoria.add(new Particao(0, 200));
        memoria.add(new Particao(200, 300));
        memoria.add(new Particao(500, 500));
        algoritmo = new FirstFit();

       /* algoritmo.alocar(memoria, "P1", 150);
        algoritmo.alocar(memoria, "P2", 200);
        algoritmo.alocar(memoria, "P3", 100);*/
    }

    @Test
    void deveAlocarNaPrimeiraParticaoLivreSuficiente() {
        boolean sucesso1 = algoritmo.alocar(memoria, "P1", 150); //1 partição
        Assertions.assertTrue(sucesso1);
        Assertions.assertEquals("P1", memoria.get(0).getProcesso());

        boolean sucesso2 = algoritmo.alocar(memoria, "P2", 50); // 2 partição
        Assertions.assertTrue(sucesso2);
        Assertions.assertEquals("P2", memoria.get(1).getProcesso());
    }
    @Test
    void deveFalharQuandoNaoHaEspacoSuficiente() {
        boolean sucesso = algoritmo.alocar(memoria, "P1", 1000);
        Assertions.assertFalse(sucesso);
    }


}







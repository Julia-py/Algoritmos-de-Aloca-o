package org.example.algoritmos;

import org.example.algoritmos.NextFitTest;
import org.example.model.Particao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class NextFitTest {

    private List<Particao> memoria;
    private NextFit algoritmo;

    @BeforeEach
    void setup() {
        memoria = new ArrayList<>();
        memoria.add(new Particao(0, 100));
        memoria.add(new Particao(100, 200));
        memoria.add(new Particao(300, 300));
        memoria.add(new Particao(600, 150));
        algoritmo = new NextFit();
    }

    @Test
    void deveAlocarComecandoDaUltimaPosicaoUsada() {
        // 1ª alocação (deve começar no início)
        boolean sucesso1 = algoritmo.alocar(memoria, "P1", 200);
        Assertions.assertTrue(sucesso1);
        Assertions.assertEquals("P1", memoria.get(1).getProcesso());

        // 2ª alocação (deve continuar da posição onde parou, ou seja, 1)
        boolean sucesso2 = algoritmo.alocar(memoria, "P2", 100);
        Assertions.assertTrue(sucesso2);
        // A próxima livre após a posição 1 é a posição 2 (300, 300)
        Assertions.assertEquals("P2", memoria.get(2).getProcesso());
    }

    @Test
    void deveVoltarAoInicioSeChegarAoFim() {
        // Preenche as partições 2 e 3 para forçar o retorno ao início
        algoritmo.alocar(memoria, "P1", 300); // ocupa posição 2
        algoritmo.alocar(memoria, "P2", 150); // ocupa posição 3

        // Agora o próximo processo deve voltar ao início
        boolean sucesso = algoritmo.alocar(memoria, "P3", 100);
        Assertions.assertTrue(sucesso);
        Assertions.assertEquals("P3", memoria.get(0).getProcesso());
    }

    @Test
    void deveFalharQuandoNaoHaEspacoSuficiente() {
        boolean sucesso = algoritmo.alocar(memoria, "P1", 1000);
        Assertions.assertFalse(sucesso);
    }
}

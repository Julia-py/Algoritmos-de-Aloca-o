package algoritmos;

import model.Particao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorstFitTest {
    private WorstFit algoritmo;
    private List<Particao> memoria;

    @BeforeEach
    void setup(){
        // Apenas inicializa o algoritmo, pois a memória deve ser configurada em CADA teste.
        algoritmo = new WorstFit();
    }

    // --- TESTE 1: Alocação em Memória Vazia ---
    @Test
    @DisplayName("Deve alocar processo em memória inicialmente vazia")
    void deveAlocarEmMemoriaVazia() {
        memoria = new ArrayList<>(); // Inicializa a memória LIMPA para este teste

        // Memória inicial de 1000u livre
        Particao particaoInicial = new Particao(0, 1000);
        // Garante que o objeto Particao seja tratado como livre se seu construtor não definir explicitamente
        // Se o construtor for (int inicio, int tamanho), a particao DEVE começar livre.
        memoria.add(particaoInicial);

        // Tenta alocar um processo de 300u
        boolean alocado = algoritmo.alocar(memoria, "P1", 300);

        // Verifica se a alocação foi bem-sucedida
        assertTrue(alocado, "P1 deve ser alocado.");

        // A memória deve ter 2 partições: P1 (300u) e uma livre (700u)
        assertEquals(2, memoria.size(), "Deve haver 2 partições (P1 e a sobra).");
        // O método correto deve ser getNomeProcesso() ou getProcesso()
        assertEquals("P1", memoria.get(0).getProcesso(), "A primeira partição deve ser P1.");
        assertEquals(300, memoria.get(0).getTamanho(), "P1 deve ter tamanho 300.");
        assertTrue(memoria.get(1).isLivre(), "A segunda partição deve ser a sobra livre.");
        assertEquals(700, memoria.get(1).getTamanho(), "A sobra deve ter tamanho 700.");
    }

    // --- TESTE 2: Escolha da PIOR PARTIÇÃO (Maior) ---
    @Test
    @DisplayName("Deve escolher a maior partição livre disponível (Worst Fit)")
    void deveEscolherPiorParticao() {
        memoria = new ArrayList<>(); // Inicializa a memória LIMPA para este teste

        // Estrutura de memória com buracos livres (Livre/Ocupado):
        // 100u(L), 200u(O), 500u(L), 200u(L)
        memoria.add(new Particao(0, 100));     // Partição A (Índice 0, Livre)
        memoria.add(new Particao(100, 200));   // Partição B (Índice 1, Ocupada para teste)
        memoria.get(1).ocupar("Temp", 200);     // <--- Ocupa a partição B
        memoria.add(new Particao(300, 500));   // Partição C (Índice 2, Livre, MAIOR)
        memoria.add(new Particao(800, 200));   // Partição D (Índice 3, Livre)

        // O processo P1 (150u) deve ser alocado na partição de 500u (Índice 2), pois é a maior.

        boolean alocado = algoritmo.alocar(memoria, "P1", 150);

        // Verifica a alocação
        assertTrue(alocado, "P1 deve ser alocado.");

        // Verifica a partição C (Índice 2)
        assertEquals("P1", memoria.get(2).getProcesso(), "P1 deve ter sido alocado na partição de 500u.");
        assertEquals(150, memoria.get(2).getTamanho(), "P1 deve ter tamanho 150.");

        // Verifica se houve a partição da sobra (500u - 150u = 350u) no índice 3 (movendo a antiga Partição D para o índice 4)
        assertEquals(5, memoria.size(), "Deve haver 5 partições (a partição de 500u foi dividida).");
        assertTrue(memoria.get(3).isLivre(), "A nova partição criada (sobra) deve estar no índice 3.");
        assertEquals(350, memoria.get(3).getTamanho(), "A sobra deve ter tamanho 350.");
        assertEquals(200, memoria.get(4).getTamanho(), "A última partição deve ser a de 200u.");
    }

    // --- TESTE 3: Falha na Alocação ---
    @Test
    @DisplayName("Deve falhar ao alocar um processo maior que qualquer partição livre")
    void deveFalharNaAlocacao() {
        memoria = new ArrayList<>(); // Inicializa a memória LIMPA para este teste

        // Partições livres: 100u e 200u
        memoria.add(new Particao(0, 100));     // Livre (100u)
        memoria.add(new Particao(100, 700));   // Ocupada (700u)
        memoria.get(1).ocupar("Ocupado", 700); // <--- Ocupa a partição
        memoria.add(new Particao(800, 200));   // Livre (200u)

        // Tenta alocar P1 (300u), que não cabe em nenhuma partição livre.
        boolean alocado = algoritmo.alocar(memoria, "P1", 300);

        // Verifica se a alocação falhou
        assertFalse(alocado, "A alocação deve falhar.");
        assertEquals(3, memoria.size(), "A memória não deve ter sido alterada.");
        assertTrue(memoria.get(0).isLivre() && memoria.get(2).isLivre(), "As partições livres devem permanecer livres.");
    }
}
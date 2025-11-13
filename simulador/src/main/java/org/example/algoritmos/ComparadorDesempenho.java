package algoritmos;

import interfaces.AlgoritmoAlocacao;
import model.Particao;
import java.util.ArrayList;
import java.util.List;

public class ComparadorDesempenho {

    private static int calcularFragmentacaoInterna(List<Particao> memoria) {
        int fragmentacao = 0;
        for (Particao p : memoria) {
            if (p.isLivre()) {
                fragmentacao += p.getTamanho();
            }
        }
        return fragmentacao;
    }

    private static long testarAlgoritmo(AlgoritmoAlocacao algoritmo, String nomeAlgoritmo) {
        // Cria uma nova memória de 1000 unidades
        List<Particao> memoria = new ArrayList<>();
        memoria.add(new Particao(0, 1000));

        // Cria cópia de tamanhos de processo para garantir o mesmo teste
        int[] tamanhos = {200, 300, 100, 250, 50};

        long inicio = System.nanoTime();

        for (int i = 0; i < tamanhos.length; i++) {
            boolean sucesso = algoritmo.alocar(memoria, "P" + (i + 1), tamanhos[i]);
            if (sucesso) {
                System.out.println("✅ " + nomeAlgoritmo + " alocou P" + (i + 1) + " (" + tamanhos[i] + ")");
            } else {
                System.out.println("❌ " + nomeAlgoritmo + " não conseguiu alocar P" + (i + 1));
            }
        }

        long fim = System.nanoTime();
        long tempo = (fim - inicio) / 1_000_000; // milissegundos

        int fragmentacao = calcularFragmentacaoInterna(memoria);

        System.out.printf("%s → Tempo: %d ms | Fragmentação: %d%n",
                nomeAlgoritmo, tempo, fragmentacao);

        return tempo;
    }

    public static void main(String[] args) {
        System.out.println("=== COMPARATIVO DE DESEMPENHO ===\n");

        System.out.println("=== FIRST FIT ===");
        testarAlgoritmo(new FirstFit(), "First Fit");
        System.out.println("\n");

        System.out.println("=== NEXT FIT ===");
        testarAlgoritmo(new NextFit(), "Next Fit");
        System.out.println("\n");

        System.out.println("=== WORST FIT ===");
        testarAlgoritmo(new WorstFit(), "Worst Fit");
    }
}

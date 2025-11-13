package algoritmos;



import interfaces.AlgoritmoAlocacao;
import model.Particao;

import java.util.List;

public class WorstFit implements AlgoritmoAlocacao {

    @Override
    public boolean alocar(List<Particao> memoria, String nomeProcesso, int tamanho) {
        int indicePiorParticao = -1;
        int maiorTamanhoLivre = -1;

        // 1. Percorre toda a memória para encontrar a MAIOR partição livre que cabe o processo
        for (int i = 0; i < memoria.size(); i++) {
            Particao p = memoria.get(i);

            // Verifica se a partição está livre e se cabe o processo
            if (p.isLivre() && p.getTamanho() >= tamanho) {
                // Se esta partição for maior que a maior encontrada até agora
                if (p.getTamanho() > maiorTamanhoLivre) {
                    maiorTamanhoLivre = p.getTamanho();
                    indicePiorParticao = i;
                }
            }
        }

        // 2. Se nenhuma partição foi encontrada, retorna false
        if (indicePiorParticao == -1) {
            return false;
        }

        // 3. Aloca o processo na partição Worst Fit (maior)
        Particao p = memoria.get(indicePiorParticao);
        int sobra = p.getTamanho() - tamanho;

        p.ocupar(nomeProcesso, tamanho); // Ocupa a partição

        // 4. Cria uma nova partição para a sobra, se houver
        if (sobra > 0) {
            Particao nova = new Particao(p.getInicio() + tamanho, sobra);
            memoria.add(indicePiorParticao + 1, nova);
        }

        return true; // Alocação bem-sucedida
    }
}
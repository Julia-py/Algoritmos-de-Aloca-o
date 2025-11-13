package org.example.algoritmos;



import org.example.interfaces.AlgoritmoAlocacao;
import org.example.model.Particao;

import java.util.List;

public class NextFit implements AlgoritmoAlocacao {
    private int ultimaPosicao = 0;

    @Override
    public boolean alocar(List<Particao> memoria, String nomeProcesso, int tamanho) {
        int n = memoria.size();
        int pos = ultimaPosicao;

        // Percorre todas as partições uma vez, começando da última posição
        for (int i = 0; i < n; i++) {
            Particao p = memoria.get(pos);
            if (p.isLivre() && p.getTamanho() >= tamanho) {
                int sobra = p.getTamanho() - tamanho;
                p.ocupar(nomeProcesso, tamanho);
                if (sobra > 0) {
                    Particao nova = new Particao(p.getInicio() + tamanho, sobra);
                    memoria.add(pos + 1, nova);
                }
                ultimaPosicao = pos;
                return true;
            }
            pos = (pos + 1) % n; // volta ao início se chegar ao fim
        }

        return false; // não encontrou espaço
    }
}

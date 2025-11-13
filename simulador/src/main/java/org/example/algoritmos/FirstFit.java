package algoritmos;

import interfaces.AlgoritmoAlocacao;
import model.Particao;

import java.util.List;

public class FirstFit implements AlgoritmoAlocacao {

    @Override
    public boolean alocar(List<Particao> memoria, String nomeProcesso, int tamanho) {
        // Percorre todas as partições na ordem
        for (int i = 0; i < memoria.size(); i++) {
            Particao p = memoria.get(i);

            if (p.isLivre() && p.getTamanho() >= tamanho) {
                // Ocupa a partição
                p.ocupar(nomeProcesso, tamanho);

                // Se houver sobra, cria nova partição
                int sobra = p.getTamanho() - tamanho;
                if (sobra > 0) {
                    Particao nova = new Particao(p.getInicio() + tamanho, sobra);
                    memoria.add(i + 1, nova);
                }

                System.out.println("✅ Processo " + nomeProcesso + " alocado (" + tamanho + " KB)");
                return true;
            }
        }

        System.out.println("❌ Falha: não há espaço para " + nomeProcesso);
        return false;
    }
}
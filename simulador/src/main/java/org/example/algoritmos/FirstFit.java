package algoritmos;

import interfaces.AlgoritmoAlocacao;
import model.Particao;

import java.util.List;

public class FirstFit implements AlgoritmoAlocacao {

    @Override
    public boolean alocar(List<Particao> memoria, String nomeprocesso, int tamanho) {
        // faz a verifica as partições e se ela está livre com o tamanho adequado.
        for (int i = 0; i < memoria.size(); i++) {
            Particao p = memoria.get(i);

            if (p.isLivre() && p.getTamanho() >= tamanho) {
                int sobra = p.getTamanho() - tamanho;
                p.ocupar(nomeprocesso, tamanho);

                if (sobra > 0){
                    Particao nova = new Particao (p.getInicio() + tamanho, sobra);
                    memoria.add(i+1, nova);
                }
                return true;
            }

        }
        return false;
    }
}
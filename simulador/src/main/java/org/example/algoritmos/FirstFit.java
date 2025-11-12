package algoritmos;

import interfaces.AlgoritmoAlocacao;
import model.Particao;

import java.util.List;

public class FirstFit implements AlgoritmoAlocacao {

    @Override
    public boolean alocar(List<Particao> memoria, String processo, int tamanho) {
        // faz a verifica as partições e se ela está livre com o tamanho adequado.
        for (Particao p: memoria) {
            if (p.isLivre()&& p.getTamanho() >= tamanho){
                if (p.getTamanho() > tamanho){
                    Particao nova = new Particao(p.getInicio() + tamanho, p.getTamanho() - tamanho);
                    memoria.add(memoria.indexOf(p) +1, nova);
                    p.setTamanho(tamanho);
                }
                p.setLivre(false);
                p.setTamanho(processo);
                System.out.println("✅ Processo " + processo + " alocado (" + tamanho + " KB)");
                return true;
            }
        }
        System.out.println("❌ Falha: não há espaço para " + processo);
        return false;
    }


}

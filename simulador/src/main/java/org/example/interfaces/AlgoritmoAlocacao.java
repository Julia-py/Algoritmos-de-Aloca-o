package interfaces;


import model.Particao;

import java.util.List;


public interface AlgoritmoAlocacao {
    boolean alocar(List<Particao> memoria, String nomeProcesso, int tamanho);
}

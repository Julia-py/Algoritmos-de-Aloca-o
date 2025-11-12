package main.java.org.example.interfaces;

import main.java.org.example.model.Particao;

import java.util.List;


public interface AlgoritmoAlocacao {
    boolean alocar(List<Particao> memoria, String nomeProcesso, int tamanho);
}

package org.example;

import org.example.interfaces.AlgoritmoAlocacao;
import org.example.model.Particao;

import java.util.ArrayList;
import java.util.List;

public class SimuladorMemoria {
    private List<Particao> memoria;
    private AlgoritmoAlocacao algoritmo;

    public SimuladorMemoria(int tamanhoTotal, AlgoritmoAlocacao algoritmo) {
        this.memoria = new ArrayList<>();
        memoria.add(new Particao(0, tamanhoTotal));
        this.algoritmo = algoritmo;
    }

    public boolean alocar(String processo, int tamanho) {
        return algoritmo.alocar(memoria, processo, tamanho);
    }

    public void mostrarEstado() {
        System.out.println("\nEstado da Memória:");
        for (Particao p : memoria) {
            String status = p.isLivre() ? "Livre" : "Ocupado (" + p.getProcesso() + ")";
            System.out.printf("Início: %4d | Tamanho: %4d | %s%n", p.getInicio(), p.getTamanho(), status);
        }
    }

    public List<Particao> getMemoria() {
        return memoria;
    }
}

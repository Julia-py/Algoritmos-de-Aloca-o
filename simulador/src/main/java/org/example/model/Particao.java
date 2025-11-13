package org.example.model;

public class Particao {
    private int inicio;
    private int tamanho;
    private boolean livre;
    private String processo;

    public Particao(int inicio, int tamanho) {
        this.inicio = inicio;
        this.tamanho = tamanho;
        this.livre = true;
        this.processo = null;
    }

    public int getInicio() {
        return inicio;
    }

    public int getTamanho() {
        return tamanho;
    }

    public boolean isLivre() {
        return livre;
    }

    public String getProcesso() {
        return processo;
    }

    public void ocupar(String processo, int tamanho) {
        this.livre = false;
        this.processo = processo;
        this.tamanho = tamanho;
    }

    public void liberar() {
        this.livre = true;
        this.processo = null;
    }
}

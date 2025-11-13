package org.example;

import org.example.algoritmos.WorstFit;
import org.example.algoritmos.NextFit;
import org.example.interfaces.AlgoritmoAlocacao;

public class Main {
    public static void main(String[] args) {
        // Cria o algoritmo de alocação que será usado
        AlgoritmoAlocacao algoritmo = new NextFit();

        AlgoritmoAlocacao algoritmoWorst = new WorstFit();

        // Cria o simulador de memória com 1000 unidades de tamanho total
        SimuladorMemoria simulador = new SimuladorMemoria(1000, algoritmo);
        SimuladorMemoria simuladorWorst = new SimuladorMemoria(1000, algoritmoWorst);

        // Aloca alguns processos
            //NextFit
        simulador.alocar("P1", 200);
        simulador.alocar("P2", 300);
            //WorstFit
        simuladorWorst.alocar("P1",200);
        simuladorWorst.alocar("P2",300);

        // Mostra o estado da memória após as alocações
            //NextFit
            simulador.mostrarEstado();
            //WorstFit
            simuladorWorst.mostrarEstado();
    }
}

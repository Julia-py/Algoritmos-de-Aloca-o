

import algoritmos.FirstFit;
import interfaces.AlgoritmoAlocacao;

public class FirstFitTest {

    public static void main(String [] args) {
        AlgoritmoAlocacao algoritmo = new FirstFit();
        SimuladorMemoria simulador = new SimuladorMemoria(1000,algoritmo);

        simulador.alocar("P1", 200);
        simulador.alocar("P2", 300);
        simulador.alocar("P3", 100);
        simulador.mostrarEstado();
    }



}







import algoritmos.NextFit;
import interfaces.AlgoritmoAlocacao;

public class Main {
    public static void main(String[] args) {
        // Cria o algoritmo de alocação que será usado
        AlgoritmoAlocacao algoritmo = new NextFit();

        // Cria o simulador de memória com 1000 unidades de tamanho total
        SimuladorMemoria simulador = new SimuladorMemoria(1000, algoritmo);

        // Aloca alguns processos
        simulador.alocar("P1", 200);
        simulador.alocar("P2", 300);

        // Mostra o estado da memória após as alocações
        simulador.mostrarEstado();
    }
}

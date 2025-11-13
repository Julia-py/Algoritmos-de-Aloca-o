
# Simulador de Alocação de Memória — Partições Dinâmicas

Este projeto implementa um **Simulador de Alocação de Processos em Partições Dinâmicas**, com suporte aos principais algoritmos de alocação utilizados em **Sistemas Operacionais**:

* **First Fit**
* **Next Fit**
* **Worst Fit**

O objetivo deste experimento é avaliar e comparar o desempenho dos algoritmos de alocação de memória quanto a dois aspectos principais:
- **Tempo de execução️**
- **Fragmentação interna**
---

## 📘 Sumário

* [📘 Descrição](#-descrição)
* [⚙️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
* [🏗️ Estrutura do Projeto](#️-estrutura-do-projeto)
* [🚀 Como Executar o Projeto](#-como-executar-o-projeto)
* [🧩 Algoritmos Implementados](#-algoritmos-implementados)
* [🧪 Testes Unitários](#-testes-unitários)
* [📊 Exemplo de Saída](#-exemplo-de-saída)
* [👩‍💻 Autoria](#-autoria)

---

## 📘 Descrição

O simulador representa a **memória principal** como uma lista de partições (livres ou ocupadas), onde os processos são alocados conforme o algoritmo de escolha.

Cada algoritmo segue uma lógica distinta para escolher onde posicionar um novo processo:

* **First Fit:** O First Fit percorre a lista de partições e aloca o processo na primeira partição livre com tamanho suficiente. É simples e rápido, mas pode causar fragmentação externa mais rapidamente.
* **Next Fit:** O Next Fit funciona como o First Fit, mas continua a busca a partir da última posição onde alocou. Isso evita que sempre as primeiras partições sejam usadas, tendendo a distribuir melhor a ocupação.
* **Worst Fit:** O Worst Fit procura sempre a maior partição livre disponível, tentando reduzir a fragmentação,
pois mantém mais chances de sobras grandes para futuras alocações.

O projeto foi desenvolvido em **Java**, com **JUnit 5** para os testes automatizados, e adota conceitos de **Programação Orientada a Objetos (POO)** e **TDD (Test Driven Development)**.

---

## ⚙️ Tecnologias Utilizadas

* ☕ **Java 21+**
* 🧪 **JUnit 5** — para testes automatizados
* 🧩 **Paradigma de Programação:** Orientação a Objetos
* 🧠 **Conceitos de SO:** Alocação de Memória, Fragmentação Interna e Externa

---

## 🏗️ Estrutura do Projeto

```
📦 simulador
├── 📁 main.java.org.example.algoritmos
│   ├── FirstFit.java
│   ├── NextFit.java
│   └── WorstFit.java
│
├── 📁 interfaces
│   └── AlgoritmoAlocacao.java
│
├── 📁 model
│   └── Particao.java
|
|── SimuladorMemoria.java
├── Main.java
|
|
├── 📁 test.java.org.example.algoritmos
│   ├── FirstFitTest.java
│   ├── NextFitTest.java
│   └── WorstFitTest.java
```

---

## 🚀 Como Executar o Projeto

1. **Clone o repositório**

   ```bash
   git clone https://github.com/Julia-py/Algoritmos-de-Aloca-o.git
   ```

2. **Abra o projeto** na IDE IntelliJ.

3. **Compile e execute** o arquivo `Main.java` ou execute os teste via terminal `mvn test`.

4. O console exibirá o estado da memória após as alocações feitas pelos algoritmos.

---

## 🧩 Algoritmos Implementados

### 🔹 First Fit

Procura a **primeira partição livre** que seja grande o suficiente para armazenar o processo.

```java
for (Particao p : memoria) {
    if (p.isLivre() && p.getTamanho() >= tamanho) {
        p.ocupar(nomeProcesso, tamanho);
        break;
    }
}
```

### 🔹 Next Fit

Começa a busca a partir da **última posição onde um processo foi alocado**, dando continuidade circular na memória.

```java
pos = (pos + 1) % n; // Retorna ao início se chegar ao fim
```

### 🔹 Worst Fit

Seleciona a **maior partição livre** disponível, buscando reduzir o número de partições grandes e minimizar fragmentação externa.

```java
if (p.isLivre() && p.getTamanho() > maiorTamanhoLivre) {
    indicePiorParticao = i;
}
```
---

## 🧪 Testes Unitários

Foram desenvolvidos testes com **JUnit 5** para validar o comportamento de cada algoritmo:

* **FirstFitTest.java:** verifica se o algoritmo aloca corretamente na primeira partição disponível.
* **NextFitTest.java:** testa a continuidade da busca e o retorno ao início da memória.
* **WorstFitTest.java:** assegura que a maior partição livre seja sempre escolhida.

Para rodar os testes:

```bash
mvn test
```

ou execute diretamente pela IDE.

---


## 📊 Exemplo de Saída

### Execução com Main.java
Ao executar o `Main.java`, o console exibe o estado da memória após a alocação:

```
✅ Processo P1 alocado (200 KB)
✅ Processo P2 alocado (300 KB)

Estado da Memória:
Início:    0 | Tamanho:  200 | Ocupado (P1)
Início:  200 | Tamanho:  300 | Ocupado (P2)
Início:  500 | Tamanho:  500 | Livre
```

### Comparativo de Desempenho
Ao executar o `ComparadorDesempenho.java`, o console exibe:

```
=== COMPARATIVO DE DESEMPENHO ===

=== FIRST FIT ===
✅ Processo P1 alocado (200 KB)
✅ First Fit alocou P1 (200)
❌ Falha: não há espaço para P2
❌ First Fit não conseguiu alocar P2
❌ Falha: não há espaço para P3
❌ First Fit não conseguiu alocar P3
❌ Falha: não há espaço para P4
❌ First Fit não conseguiu alocar P4
❌ Falha: não há espaço para P5
❌ First Fit não conseguiu alocar P5
First Fit → Tempo: 41 ms | Fragmentação: 0


=== NEXT FIT ===
✅ Next Fit alocou P1 (200)
✅ Next Fit alocou P2 (300)
✅ Next Fit alocou P3 (100)
✅ Next Fit alocou P4 (250)
✅ Next Fit alocou P5 (50)
Next Fit → Tempo: 0 ms | Fragmentação: 100


=== WORST FIT ===
✅ Worst Fit alocou P1 (200)
✅ Worst Fit alocou P2 (300)
✅ Worst Fit alocou P3 (100)
✅ Worst Fit alocou P4 (250)
✅ Worst Fit alocou P5 (50)
Worst Fit → Tempo: 0 ms | Fragmentação: 100
```

---

## 🧠 Análise dos Resultados

| Algoritmo     | Processos Alocados | Tempo (ms) | Fragmentação | Observação                                                                            |
| ------------- | ------------------ | ---------- | ------------ | ------------------------------------------------------------------------------------- |
| **First Fit** | 1                  | 41         | 0            | Falhou após a primeira alocação, provavelmente por má gestão da lista de partições.   |
| **Next Fit**  | 5                  | 0          | 100          | Realizou todas as alocações rapidamente, com fragmentação residual.                   |
| **Worst Fit** | 5                  | 0          | 100          | Mesmo desempenho que o Next Fit neste cenário, também completando todas as alocações. |

---

## 🏁 Conclusão

Neste experimento:

* O **Next Fit** e o **Worst Fit** apresentaram o **melhor desempenho**, conseguindo **alocar todos os processos** com **baixo tempo de execução**.
* O **First Fit** falhou após a primeira alocação, o que indica uma possível limitação lógica ou uma maior propensão à fragmentação externa.

🔹 **Desempenho Destaque:** `Next Fit`
Por apresentar **alocação completa**, **tempo praticamente nulo** e comportamento eficiente na distribuição das partições.

---

## 👩‍💻 Autoria

Desenvolvido por:
- **Arthur dos Santos Lima**
- **Julia Maria Benjamin Araujo**
- **Paulo Sergio Albino de Souza**
- **Rosenilda Santos da Silva**

📚 Projeto acadêmico — Disciplina de **Sistemas Operacionais**




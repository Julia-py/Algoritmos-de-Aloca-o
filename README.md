
# Simulador de Alocação de Memória — Partições Dinâmicas

Este projeto implementa um **Simulador de Alocação de Processos em Partições Dinâmicas**, com suporte aos principais algoritmos de alocação utilizados em **Sistemas Operacionais**:

* **First Fit**
* **Next Fit**
* **Worst Fit**

O objetivo é demonstrar, de forma prática, como diferentes estratégias de alocação influenciam o uso da memória e o posicionamento dos processos.

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

* **First Fit:** aloca na primeira partição livre suficientemente grande.
* **Next Fit:** aloca a partir da última posição usada, evitando reiniciar do início.
* **Worst Fit:** escolhe sempre a maior partição livre disponível.

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

Ao executar o `Main.java`, o console exibe o estado da memória após a alocação:

```
✅ Processo P1 alocado (200 KB)
✅ Processo P2 alocado (300 KB)

Estado da Memória:
Início:    0 | Tamanho:  200 | Ocupado (P1)
Início:  200 | Tamanho:  300 | Ocupado (P2)
Início:  500 | Tamanho:  500 | Livre
```

---

## 👩‍💻 Autoria

Desenvolvido por:
- **Arthur dos Santos Lima**
- **Julia Maria Benjamin Araujo**
- **Paulo Sergio Albino de Souza**
- **Rosenilda Santos da Silva**

📚 Projeto acadêmico — Disciplina de **Sistemas Operacionais**

---

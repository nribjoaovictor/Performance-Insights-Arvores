# 🌳 Performance Insights - Árvores Binárias e AVL

Este repositório contém o desenvolvimento de estruturas de dados genéricas em Java, com foco em:

- Árvore Binária de Busca
- Árvore AVL
- Comparação de desempenho entre estruturas balanceadas e não balanceadas

O projeto também mantém as implementações do trabalho anterior de listas encadeadas para fins de comparação e evolução do estudo.

---

# 🎯 Objetivos Técnicos

1. **Abstração e Polimorfismo**

   Uso da interface `IColecao<T>` para desacoplar a lógica da aplicação da estrutura de armazenamento.

2. **Estruturas de Dados em Árvores**

   Implementação manual de:
  - Árvore Binária de Busca
  - Árvore AVL auto-balanceada

3. **Análise de Performance**

   Comparação prática entre árvores comuns e balanceadas utilizando grandes volumes de dados.

4. **Análise de Complexidade**

   Observação prática do impacto da altura da árvore nas operações de:
  - inserção
  - pesquisa
  - remoção

---

# 🌲 Funcionamento das Estruturas

---

## 1. Árvore Binária de Busca

A Árvore Binária organiza os elementos de forma hierárica.

Regras:

- valores menores ficam à esquerda
- valores maiores ficam à direita

Exemplo:

```text
        50
       /  \
     30    70
    / \    / \
   20 40 60 80
```

### Características

✅ Inserção simples  
✅ Pesquisa eficiente quando balanceada  
❌ Pode degenerar dependendo da ordem de inserção

Quando muitos valores são inseridos em ordem crescente, a árvore pode virar algo parecido com uma lista:

```text
10
  \
   20
     \
      30
        \
         40
```

Nesse caso, a complexidade passa de:

```text
O(log n)
```

para:

```text
O(n)
```

---

## 2. Árvore AVL

A Árvore AVL é uma árvore binária de busca auto-balanceada.

Após cada inserção, ela calcula o fator de balanceamento:

```text
fator = altura(esquerda) - altura(direita)
```

A árvore permanece balanceada quando o fator está entre:

```text
-1, 0 ou 1
```

Quando esse limite é ultrapassado, a AVL realiza rotações automaticamente.

---

## 🔄 Rotações Implementadas

### Rotação simples à direita

```text
        30                  20
       /                   /  \
      20        ->        10   30
     /
    10
```

### Rotação simples à esquerda

```text
    10                       20
      \                     /  \
       20       ->         10   30
         \
          30
```

### Rotação dupla esquerda-direita

### Rotação dupla direita-esquerda

---

## ✅ Benefícios da AVL

A AVL mantém a árvore muito mais baixa.

Isso reduz drasticamente a quantidade de comparações durante:
- busca
- inserção
- remoção

Complexidade média:

```text
O(log n)
```

mesmo com grandes volumes de dados.

---

# 📁 Organização do Projeto

```text
src/
├── arvorebinaria/
│   ├── AVL/
│   │   └── ArvoreAVL.java
│   │
│   ├── ArvoreBinaria.java
│   ├── ArvoreBinariaBase.java
│   └── NoArvore.java
│
├── colecao/
│   └── IColecao.java
│
├── dominio/
│   ├── ComparadorPersonagemPorClasse.java
│   ├── ComparadorPersonagemPorNome.java
│   ├── Main.java
│   ├── Personagem.java
│   ├── TesteDesempenho.java
│   └── TesteDesempenhoArvore.java
│
├── listaencadeada/
│   ├── ListaEncadeada.java
│   ├── ListaEncadeadaArrayList.java
│   ├── ListaEncadeadaLinkedList.java
│   └── No.java
│
└── util/
    └── GeradorArquivosPersonagem.java
```

---

# 📂 Descrição dos Arquivos

---

## `colecao/IColecao.java`

Interface genérica usada para padronizar as estruturas do projeto.

Define operações como:

```java
adicionar()
pesquisar()
remover()
quantidadeNos()
```

Isso permite trocar a estrutura usada sem alterar o restante do sistema.

---

## `arvorebinaria/ArvoreBinariaBase.java`

Classe abstrata base das árvores.

Responsável por:

- armazenar o `Comparator<T>`
- implementar `IColecao<T>`
- definir métodos obrigatórios para as árvores

Métodos principais:

```java
altura()
caminharEmOrdem()
caminharEmNivel()
```

---

## `arvorebinaria/NoArvore.java`

Representa cada nó da árvore.

Atributos:

```java
private T valor;
private NoArvore<T> esquerda;
private NoArvore<T> direita;
private int altura;
```

O atributo `altura` é utilizado principalmente pela AVL para balanceamento.

---

## `arvorebinaria/ArvoreBinaria.java`

Implementação da Árvore Binária de Busca comum.

Implementa:

- adicionar
- pesquisar
- remover
- quantidadeNos
- altura
- caminhamentos

### Remoção

Trata os três casos clássicos:

- nó folha
- nó com um filho
- nó com dois filhos

Quando o nó possui dois filhos:
- ele é substituído pelo menor elemento da subárvore direita

---

## `arvorebinaria/AVL/ArvoreAVL.java`

Implementação da Árvore AVL.

Herança:

```java
public class ArvoreAVL<T> extends ArvoreBinaria<T>
```

Sobrescreve a inserção para realizar balanceamento automático.

Métodos principais:

```java
adicionar()
balancear()
rotacaoDireita()
rotacaoEsquerda()
fatorBalanceamento()
atualizarAltura()
```

---

## `dominio/Personagem.java`

Objeto utilizado nos testes e estruturas.

Atributos principais:

```java
nome
classe
raca
sexo
```

Possui:
- getters
- equals()
- construtor

---

## `dominio/ComparadorPersonagemPorNome.java`

Comparador responsável por ordenar os personagens alfabeticamente pelo nome.

Exemplo:

```java
new ArvoreAVL<>(new ComparadorPersonagemPorNome());
```

---

## `dominio/ComparadorPersonagemPorClasse.java`

Comparador responsável por ordenar os personagens pela classe.

Exemplo:

```java
new ArvoreBinaria<>(new ComparadorPersonagemPorClasse());
```

---

## `dominio/Main.java`

Classe principal do sistema.

Possui menu interativo no terminal.

Operações disponíveis:

```text
1. Adicionar personagem
2. Remover personagem
3. Pesquisar personagem
4. Ver todos os personagens
5. Ver quantidade de nós
6. Gerar arquivo com personagens
7. Carregar personagens para a árvore
8. Ver altura da árvore
0. Sair
```

---

## `dominio/TesteDesempenhoArvore.java`

Classe responsável pelos benchmarks das árvores.

Compara:

- Árvore Binária
- Árvore AVL

Utiliza arquivos com:

- 100 mil registros
- 200 mil registros
- 400 mil registros

Mede:
- tempo de inserção
- altura da árvore

---

## `dominio/TesteDesempenho.java`

Classe do trabalho anterior.

Mantida para comparação entre:
- listas
- árvores

Compara:
- Lista Encadeada
- ArrayList
- LinkedList

---

## `util/GeradorArquivosPersonagem.java`

Responsável por gerar os arquivos `.txt` usados nos testes.

Formato:

```text
nome;classe;raca;sexo
```

Exemplo:

```text
Joao;Mago;Humano;Masculino
```

---

# ⏱️ Metodologia de Benchmark

Os testes foram feitos utilizando arquivos grandes contendo personagens.

Arquivos utilizados:

```text
personagens100k.txt
personagens200k.txt
personagens400k.txt
```

---

# 📊 Resultados Coletados

## 🔤 Ordenação por Nome

```text
Testes de desempenho - Árvores

Implementação             | 100k       | 200k       | 400k
-----------------------------------------------------------------------
Árvore Binária            | 513 ms (h=131) | 371 ms (h=139) | 495 ms (h=150)
Árvore AVL                | 133 ms (h=18)  | 177 ms (h=19)  | 370 ms (h=20)
```

---

## 🏷️ Ordenação por Classe

```text
Testes de desempenho - Árvores

Implementação             | 100k       | 200k       | 400k
-----------------------------------------------------------------------
Árvore Binária            | 349 ms (h=4) | 239 ms (h=4) | 373 ms (h=5)
Árvore AVL                | 97 ms (h=3)  | 161 ms (h=3) | 265 ms (h=3)
```

---

# 🔍 Conclusões da Análise

A AVL apresentou alturas extremamente menores.

Exemplo:

| Estrutura | Altura |
|---|---|
| Árvore Binária | 150 |
| AVL | 20 |

Isso significa:

✅ Menos comparações  
✅ Menos níveis percorridos  
✅ Operações mais rápidas

Mesmo realizando rotações e atualizações de altura, a AVL manteve excelente desempenho.

---

# 🧮 Complexidade Esperada

| Operação | Árvore Binária Balanceada | Árvore Degenerada | AVL |
|---|---|---|---|
| Inserção | O(log n) | O(n) | O(log n) |
| Pesquisa | O(log n) | O(n) | O(log n) |
| Remoção | O(log n) | O(n) | O(log n) |
| Quantidade de nós | O(1) | O(1) | O(1) |

---

# 🚀 Como Executar o Projeto

---

# 1. Clonar o Repositório

## Via HTTPS

```bash
git clone https://github.com/nribjoaovictor/Performance-Insights-Arvores.git
```

## Via SSH

```bash
git clone git@github.com:nribjoaovictor/Performance-Insights-Arvores.git
```

Depois:

```bash
cd Performance-Insights-Arvores
```

---

# 2. Abrir no IntelliJ IDEA

1. Abra o IntelliJ
2. Vá em:

```text
File > Open
```

3. Selecione a pasta do projeto

4. Configure o JDK:

```text
File > Project Structure
```

Recomendado:

```text
JDK 21
```

5. Caso necessário:

```text
Clique com botão direito em src
→ Mark Directory as
→ Sources Root
```

---

# 3. Abrir no VS Code

Instale:

```text
Extension Pack for Java
```

Depois:
- abra a pasta do projeto
- aguarde o carregamento
- execute as classes com `main`

---

# ▶️ Ordem Recomendada de Execução

---

## Passo 1 — Gerar os Arquivos

Execute:

```text
src/util/GeradorArquivosPersonagem.java
```

ou rode o `Main.java` e escolha:

```text
6. Gerar arquivo com personagens
```

Isso criará:

```text
personagens100k.txt
personagens200k.txt
personagens400k.txt
```

---

## Passo 2 — Rodar o Sistema Principal

Execute:

```text
src/dominio/Main.java
```

---

## Passo 3 — Rodar o Benchmark das Árvores

Execute:

```text
src/dominio/TesteDesempenhoArvore.java
```

Esse teste compara:

- Árvore Binária
- AVL

e exibe:
- tempo
- altura

---

## Passo 4 — Rodar Benchmark das Listas

Execute:

```text
src/dominio/TesteDesempenho.java
```

---

# 🔄 Como Trocar a Estrutura no Main

---

## AVL por Nome

```java
IColecao<Personagem> arvore =
        new ArvoreAVL<>(new ComparadorPersonagemPorNome());
```

---

## Árvore Binária por Nome

```java
IColecao<Personagem> arvore =
        new ArvoreBinaria<>(new ComparadorPersonagemPorNome());
```

---

## AVL por Classe

```java
IColecao<Personagem> arvore =
        new ArvoreAVL<>(new ComparadorPersonagemPorClasse());
```

---

## Árvore Binária por Classe

```java
IColecao<Personagem> arvore =
        new ArvoreBinaria<>(new ComparadorPersonagemPorClasse());
```

---

# 🧾 Repositório

## HTTPS

```text
https://github.com/nribjoaovictor/Performance-Insights-Arvores.git
```

## SSH

```text
git@github.com:nribjoaovictor/Performance-Insights-Arvores.git
```

---

# ✅ Status do Projeto

- ✅ Árvore Binária implementada
- ✅ Árvore AVL implementada
- ✅ Comparadores implementados
- ✅ Sistema principal funcional
- ✅ Benchmark das árvores criado
- ✅ Benchmark das listas mantido
- ✅ Análise de altura implementada
- ✅ Projeto preparado para apresentação e relatório
package arvorebinaria;

import java.util.Comparator;

public class ArvoreBinaria<T> extends ArvoreBinariaBase<T> {
    protected NoArvore<T> raiz;
    protected int quantidadeNos;

    public ArvoreBinaria(Comparator<T> comparator) {
        super(comparator);
        this.raiz = null;
        this.quantidadeNos = 0;
    }

    @Override
    public void adicionar(T valor) {
        raiz = adicionarRecursivo(raiz, valor);
    }

    protected NoArvore<T> adicionarRecursivo(NoArvore<T> atual, T valor) {
        if (atual == null) {
            quantidadeNos++;
            return new NoArvore<>(valor);
        }

        int comparacao = comparador.compare(valor, atual.getValor());

        if (comparacao < 0) {
            atual.setEsquerda(
                    adicionarRecursivo(atual.getEsquerda(), valor)
            );
        } else if (comparacao > 0) {
            atual.setDireita(
                    adicionarRecursivo(atual.getDireita(), valor)
            );
        }

        return atual;
    }

    /*
     * Forma manual de percorrer para adicionar um elemento:
     * if (raiz == null) {
     *     raiz = new NoArvore<>(novoValor);
     * } else {
     *     NoArvore<T> atual = raiz;
     *     while (true) {
     *         int comparacao =
     *                 comparador.compare(
     *                         novoValor,
     *                         atual.getValor()
     *                 );
     *         // esquerda
     *         if (comparacao < 0) {
     *             if (atual.getEsquerda() != null) {
     *                 atual = atual.getEsquerda();
     *             } else {
     *                 atual.setEsquerda(
     *                         new NoArvore<>(novoValor)
     *                 );
     *                 break;
     *             }
     *         }
     *         // direita
     *         else if (comparacao > 0) {
     *             if (atual.getDireita() != null) {
     *                 atual = atual.getDireita();
     *             } else {
     *                 atual.setDireita(
     *                         new NoArvore<>(novoValor)
     *                 );
     *                 break;
     *             }
     *         }
     *     }
     * }
     */
    @Override
    public T pesquisar(T valor) {
        return pesquisarRecursivo(raiz, valor);
    }

    private T pesquisarRecursivo(NoArvore<T> atual, T valor) {
        if (atual == null) {
            return null;
        }
        int comparacao = comparador.compare(valor, atual.getValor());

        // encontrou
        if (comparacao == 0) {
            return atual.getValor();
        }
        // esquerda
        if (comparacao < 0) {
            return pesquisarRecursivo(atual.getEsquerda(), valor);
        }
        // direita
        return pesquisarRecursivo(atual.getDireita(), valor);
    }

    @Override
    public boolean remover(T valor) {
        if (pesquisar(valor) == null) {
            return false;
        }
        raiz = removerRecursivo(raiz, valor);
        quantidadeNos--;
        return true;
    }

    private NoArvore<T> removerRecursivo(NoArvore<T> atual, T valor) {
        if (atual == null) {
            return null;
        }
        int comparacao = comparador.compare(valor, atual.getValor());
        // esquerda
        if (comparacao < 0) {
            atual.setEsquerda(removerRecursivo(atual.getEsquerda(), valor));
        }

        // direita
        else if (comparacao > 0) {
            atual.setDireita(removerRecursivo(atual.getDireita(), valor));
        }
        // encontrou o nó
        else {

            // nó folha
            if (atual.getEsquerda() == null && atual.getDireita() == null) {
                return null;
            }

            // só tem filho direito
            if (atual.getEsquerda() == null) {
                return atual.getDireita();
            }

            // só tem filho esquerdo
            if (atual.getDireita() == null) {
                return atual.getEsquerda();
            }

            // dois filhos
            NoArvore<T> menorDireita = encontrarMenor(atual.getDireita());
            atual.setValor(menorDireita.getValor());
            atual.setDireita(removerRecursivo(atual.getDireita(), menorDireita.getValor()));
        }
        return atual;
    }

    private NoArvore<T> encontrarMenor(NoArvore<T> atual) {
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }
        return atual;
    }

    @Override
    public int quantidadeNos() {
        return quantidadeNos;
    }

    @Override
    public int altura() {
        return calcularAltura(raiz);
    }

    protected int calcularAltura(NoArvore<T> atual) {
        if (atual == null) {
            return -1;
        }
        int esquerda = calcularAltura(atual.getEsquerda());
        int direita = calcularAltura(atual.getDireita());
        return 1 + Math.max(esquerda, direita);
    }

    @Override
    public String caminharEmOrdem() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        caminharEmOrdemRecursivo(raiz, sb);
        sb.append("]");
        return sb.toString();
    }

    private void caminharEmOrdemRecursivo(NoArvore<T> atual, StringBuilder sb) {
        if (atual != null) {
            caminharEmOrdemRecursivo(atual.getEsquerda(), sb);
            sb.append(atual.getValor()).append(" ");
            caminharEmOrdemRecursivo(atual.getDireita(), sb);
        }
    }

    @Override
    public String caminharEmNivel() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int altura = altura();
        for (int i = 0; i <= altura; i++) {
            caminharNivelRecursivo(raiz, i, sb);
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    private void caminharNivelRecursivo(NoArvore<T> atual, int nivel, StringBuilder sb) {
        if (atual == null) {
            return;
        }

        if (nivel == 0) {
            sb.append(atual.getValor()).append(" ");
        } else {
            caminharNivelRecursivo(atual.getEsquerda(), nivel - 1, sb);
            caminharNivelRecursivo(atual.getDireita(), nivel - 1, sb);
        }
    }
}




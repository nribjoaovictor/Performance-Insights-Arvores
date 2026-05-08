package arvorebinaria.AVL;

import arvorebinaria.ArvoreBinaria;
import arvorebinaria.NoArvore;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T>{
    //Se auto-balanceia
    public ArvoreAVL(Comparator<T> comparador){
        super(comparador);
    }

    @Override
    public void adicionar(T valor){
        raiz = adicionarRecursivoAVL(raiz, valor);
    }

    private NoArvore<T> adicionarRecursivoAVL(NoArvore<T> atual, T valor){
        if (atual == null) {
            quantidadeNos++;
            return new NoArvore<>(valor);
        }

        int comparacao = comparador.compare(valor, atual.getValor());

        if (comparacao < 0) {
            atual.setEsquerda(adicionarRecursivoAVL(atual.getEsquerda(), valor));
        } else if (comparacao > 0) {
            atual.setDireita(adicionarRecursivoAVL(atual.getDireita(), valor));
        } else {
            return atual; // não insere repetido
        }

        atualizarAltura(atual);

        return balancear(atual);
    }

    private void atualizarAltura(NoArvore<T> no){
        no.setAltura(
                1 + Math.max(
                        alturaDoNo(no.getEsquerda()),
                        alturaDoNo(no.getDireita())
        ));
    }

    private int alturaDoNo(NoArvore<T> no){
        if (no == null){
            return -1;
        } return no.getAltura();
    }

    private int fatorBalanceamento(NoArvore<T> no){
        if (no == null){
            return 0;
        }
        return (alturaDoNo(no.getEsquerda()) - alturaDoNo(no.getDireita()));
    }

    //balancear, rotação direita e rotação esquerda.

    private NoArvore<T> balancear (NoArvore<T> atual){
        int fator = fatorBalanceamento(atual);

        // Caso 1: esquerda-esquerda
        if (fator > 1 && fatorBalanceamento(atual.getEsquerda()) >= 0) {
            return rotacaoDireita(atual);
        }

        // Caso 2: esquerda-direita
        if (fator > 1 && fatorBalanceamento(atual.getEsquerda()) < 0) {
            atual.setEsquerda(
                    rotacaoEsquerda(atual.getEsquerda())
            );
            return rotacaoDireita(atual);
        }

        // Caso 3: direita-direita
        if (fator < -1 && fatorBalanceamento(atual.getDireita()) <= 0) {
            return rotacaoEsquerda(atual);
        }

        // Caso 4: direita-esquerda
        if (fator < -1 && fatorBalanceamento(atual.getDireita()) > 0) {
            atual.setDireita(
                    rotacaoDireita(atual.getDireita())
            );
            return rotacaoEsquerda(atual);
        }

        return atual;
    }

    private NoArvore<T> rotacaoDireita(NoArvore<T> noDesbalanceado) {

        NoArvore<T> novoPai = noDesbalanceado.getEsquerda();
        NoArvore<T> subArvoreDireitaDoNovoPai = novoPai.getDireita();

        novoPai.setDireita(noDesbalanceado);
        noDesbalanceado.setEsquerda(subArvoreDireitaDoNovoPai);

        atualizarAltura(noDesbalanceado);
        atualizarAltura(novoPai);

        return novoPai;
    }

    private NoArvore<T> rotacaoEsquerda(NoArvore<T> noDesbalanceado) {

        NoArvore<T> novoPai = noDesbalanceado.getDireita();
        NoArvore<T> subArvoreEsquerdaDoNovoPai = novoPai.getEsquerda();

        novoPai.setEsquerda(noDesbalanceado);
        noDesbalanceado.setDireita(subArvoreEsquerdaDoNovoPai);

        atualizarAltura(noDesbalanceado);
        atualizarAltura(novoPai);

        return novoPai;
    }
}


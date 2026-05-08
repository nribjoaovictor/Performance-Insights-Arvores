package arvorebinaria.AVL;

import arvorebinaria.ArvoreBinaria;
import arvorebinaria.NoArvore;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T>{
    //Se auto-balanceia
    public ArvoreAVL(comparator<T> comparador){
        super(comparador);
    }

    @Override
    public void adicionar(T valor){
        raiz = adicionarRecursivoAVL(raiz, valor);
    }

    private adicionarRecursivoAVL(NoArvore<T> atual, T valor){
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

    private alturaDoNo(NoArvore<T> no){
        if (no == null){
            return -1;
        } return no.getEsquerda();
    }

    private int fatorBalanceamento(NoArvore<T> no){
        if (no == null){
            return 0;
        }
        return (alturaDoNo(no.getEsquerda()) - alturaDoNo(no.getDireita()));
    }

    //balancear, rotação direita e rotação esquerda.

}

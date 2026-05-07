package dominio;

import java.util.Comparator;

public class ComparadorPersonagemPorNome
        implements Comparator<Personagem> {

    @Override
    public int compare(Personagem p1, Personagem p2) {
        return p1.getNome().compareToIgnoreCase(p2.getNome());
    }
}

/*
* Essa parte do comparador vai retornar <0, =0 ou >0 na comparação dos nós.
*  * Retorna:
 * < 0 -> p1 vem antes de p2, então na árvore vai para a esquerda
 * = 0 -> são considerados iguais
 * > 0 -> p1 vem depois de p2, então na árvore vai para a direita
* */
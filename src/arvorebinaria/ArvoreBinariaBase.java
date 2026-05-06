package arvorebinaria;

import java.util.Comparator;

import colecao.IColecao;

public abstract class ArvoreBinariaBase<T> implements IColecao<T>{
    protected final Comparator<T> comparador;

    protected ArvoreBinariaBase(Comparator<T> comparador) {
        this.comparador = comparador;
    }

    //Métodos abstratos a serem implementados em adição aos métodos da Interface
    /**
     * Método que retorna a altura da árvore
     * @return Retorna a altura da árvore. Árvores só com raiz tem altura zero(0). Se raiz for nula retorne -1.
     */
    public abstract int altura();

    /**
     * Metódo que retona o resultado do caminhamento em nível na árvore.
     * @return String contendo os toString dos valores armazenados nos nós, percorridos por nível.
     * A String deve iniciar com "[" e finalizar com "]". Os elementos de cada nível devem ficar em uma linha da String
     */
    public abstract String caminharEmNivel();

    /**
     * Metódo que retona o resultado do caminhamento em ordem na árvore.
     * @return String contendo os toString dos valores armazenados nos nós, percorridos em ordem. A String deve iniciar com "[" e finalizar com "]"
     */
    public abstract String caminharEmOrdem();

    @Override
    public String toString(){
        return caminharEmOrdem();
    }

}
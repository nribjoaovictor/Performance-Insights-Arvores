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
* Essa parte do comparador vai retornar <0 (p1 direita), =0 ou >0 (p1 esquerda) na comparação dos nós.
* */
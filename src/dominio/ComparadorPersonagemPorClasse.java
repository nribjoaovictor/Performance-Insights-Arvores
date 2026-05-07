package dominio;

import java.util.Comparator;

public class ComparadorPersonagemPorClasse implements Comparator<Personagem> {

    @Override
    public int compare(Personagem p1, Personagem p2){
        return p1.getClasse().compareToIgnoreCase(p2.getClasse());
    }
}

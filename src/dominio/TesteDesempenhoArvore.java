package dominio;

import arvorebinaria.ArvoreBinaria;
import arvorebinaria.ArvoreBinariaBase;
import arvorebinaria.AVL.ArvoreAVL;
import colecao.IColecao;

import java.io.File;
import java.util.Scanner;
import java.util.function.Supplier;

public class TesteDesempenhoArvore {

    public static void main(String[] args) {

        String[] arquivos = {
                "personagens100k.txt",
                "personagens200k.txt",
                "personagens400k.txt"
        };

        System.out.println("Testes de desempenho - Árvores\n");
        System.out.printf(
                "%-25s | %-10s | %-10s | %-10s%n",
                "Implementação", "100k", "200k", "400k"
        );
        System.out.println("-----------------------------------------------------------------------");

        executarBateria(
                "Árvore Binária",
                arquivos,
                () -> new ArvoreBinaria<>(
                        new ComparadorPersonagemPorNome()
                        //new ComparadorPersonagemPorClasse()
                )
        );

        executarBateria(
                "Árvore AVL",
                arquivos,
                () -> new ArvoreAVL<>(
                        new ComparadorPersonagemPorNome()
                        //new ComparadorPersonagemPorClasse()
                )
        );
    }

    private static void executarBateria(
            String nomeTeste,
            String[] arquivos,
            Supplier<IColecao<Personagem>> fabrica
    ) {
        System.out.printf("%-25s", nomeTeste);

        for (String arq : arquivos) {
            IColecao<Personagem> arvore = fabrica.get();
            long tempo = medirTempoCarregamento(arq, arvore);
            int altura =
                    ((ArvoreBinariaBase<Personagem>) arvore)
                            .altura();

            System.out.printf(
                    " | %-10d (h=%d)",
                    tempo,
                    altura
            );
        }

        System.out.println();
    }

    private static long medirTempoCarregamento(
            String caminho,
            IColecao<Personagem> arvore
    ) {
        File file = new File(caminho);

        if (!file.exists()) {
            return -1;
        }

        long inicio = System.currentTimeMillis();

        try (Scanner leitor = new Scanner(file)) {

            while (leitor.hasNextLine()) {

                String[] partes = leitor.nextLine().split(";");

                if (partes.length == 4) {
                    arvore.adicionar(
                            new Personagem(
                                    partes[0],
                                    partes[1],
                                    partes[2],
                                    partes[3]
                            )
                    );
                }
            }

        } catch (Exception e) {
            return -2;
        }

        return System.currentTimeMillis() - inicio;
    }
}
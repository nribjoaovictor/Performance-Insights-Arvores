package dominio;

import java.util.Scanner;
import colecao.IColecao;
import listaencadeada.ListaEncadeada;
import util.GeradorArquivosPersonagem;
import listaencadeada.ListaEncadeadaArrayList;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IColecao<Personagem> lista = new ListaEncadeadaArrayList<>();

        int opcao;
        do {
            System.out.println("\n========= MENU PERSONAGENS =========");
            System.out.println("1. Adicionar personagem");
            System.out.println("2. Remover personagem (por nome)");
            System.out.println("3. Pesquisar personagem (por nome)");
            System.out.println("4. Ver todos os personagens");
            System.out.println("5. Ver quantidade de nós");
            System.out.println("6. Gerar arquivo com personagens");
            System.out.println("7. Carregar personagens para a lista");
            System.out.println("0. Sair");
            System.out.println("====================================");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Classe: ");
                    String classe = sc.nextLine();
                    System.out.print("Raça: ");
                    String raca = sc.nextLine();
                    System.out.print("Sexo: ");
                    String sexo = sc.nextLine();

                    lista.adicionar(new Personagem(nome, classe, raca, sexo));
                    System.out.println("Personagem adicionado com sucesso!");
                }
                case 2 -> {
                    System.out.print("Digite o nome do personagem para remover: ");
                    String nome = sc.nextLine();
                    boolean removido = lista.remover(new Personagem(nome, "", "", ""));
                    if (removido) System.out.println("Personagem removido!");
                    else System.out.println("Não encontrado.");
                }
                case 3 -> {
                    System.out.print("Digite o nome para pesquisar: ");
                    String nome = sc.nextLine();
                    Personagem encontrado = lista.pesquisar(new Personagem(nome, "", "", ""));
                    if (encontrado != null) System.out.println("Encontrado: " + encontrado);
                    else System.out.println("Personagem não existe na lista.");
                }
                case 4 -> {
                    System.out.println("Lista Atual:");
                    System.out.println(lista.toString());
                }
                case 5 -> System.out.println("Total de elementos: " + lista.quantidadeNos());
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
                case 6 -> {
                    System.out.println("\nQual arquivo deseja gerar?");
                    System.out.println("1. 100.000 personagens");
                    System.out.println("2. 200.000 personagens");
                    System.out.println("3. 400.000 personagens");
                    System.out.print("Escolha: ");

                    int escolha = sc.nextInt();
                    sc.nextLine();

                    try {
                        switch (escolha) {

                            case 1 -> {
                                GeradorArquivosPersonagem.gerarArquivo(
                                        "personagens100k.txt",
                                        100000
                                );
                            }
                            case 2 -> {
                                GeradorArquivosPersonagem.gerarArquivo(
                                        "personagens200k.txt",
                                        200000
                                );
                            }
                            case 3 -> {
                                GeradorArquivosPersonagem.gerarArquivo(
                                        "personagens400k.txt",
                                        400000
                                );
                            }
                            default -> System.out.println("Opção inválida.");
                        }

                    } catch (Exception e) {
                        System.out.println("Erro ao gerar arquivo.");
                    }
                }

                case 7 -> {

                    lista = new ListaEncadeada<>();

                    System.out.println("\nQual arquivo deseja carregar?");
                    System.out.println("1. 100.000 personagens");
                    System.out.println("2. 200.000 personagens");
                    System.out.println("3. 400.000 personagens");

                    int escolha = sc.nextInt();
                    sc.nextLine();

                    switch (escolha) {

                        case 1 ->
                                carregarArquivo(
                                        "personagens100k.txt",
                                        lista
                                );

                        case 2 ->
                                carregarArquivo(
                                        "personagens200k.txt",
                                        lista
                                );

                        case 3 ->
                                carregarArquivo(
                                        "personagens400k.txt",
                                        lista
                                );

                        default ->
                                System.out.println("Opção inválida.");

                    }

                }
            }
        } while (opcao != 0);
        sc.close();
    }
    private static void carregarArquivo(
            String caminho,
            IColecao<Personagem> lista
    ) {

        int contador = 0;

        try {

            Scanner leitor = new Scanner(new java.io.File(caminho));

            while (leitor.hasNextLine()) {

                String linha = leitor.nextLine();

                String[] partes = linha.split(";");

                String nome = partes[0];
                String classe = partes[1];
                String raca = partes[2];
                String sexo = partes[3];

                Personagem p = new Personagem(nome, classe, raca, sexo);
                lista.adicionar(p);
                contador++;
            }

            leitor.close();
            System.out.println("Arquivo carregado com sucesso!");
            System.out.println("Total de registros carregados: " + contador);

        } catch (Exception e) {
            System.out.println("Erro ao carregar arquivo.");
        }

    }

}
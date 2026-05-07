package dominio;



public class Personagem {
    //Atributos
    private String nome;
    private String classe;
    private String raca;
    private String sexo;

    public Personagem(String nome, String classe, String raca, String sexo) {
        this.nome = nome;
        this.classe = classe;
        this.raca = raca;
        this.sexo = sexo;
    }

    @Override
    public String toString(){
        return "Personagem{nome='" + nome + "', classe='" + classe + "', raça ='" + raca + "', sexo='" + sexo + "'}\n";
    }


    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Personagem that = (Personagem) o;
        return nome.equals(that.nome);
    }

    public String getNome() {
        return nome;
    }

    public String getClasse() {
        return classe;
    }
}

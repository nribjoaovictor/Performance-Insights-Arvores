package arvorebinaria;

public class NoArvore<T> {
    private T valor;
    private NoArvore<T> esquerda;
    private NoArvore<T> direita;
    private int Altura;


    public NoArvore(T valor){
       this.valor = valor;
       this.Altura = 0;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public NoArvore<T> getDireita() {
        return direita;
    }

    public void setDireita(NoArvore<T> direita) {
        this.direita = direita;
    }

    public NoArvore<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoArvore<T> esquerda) {
        this.esquerda = esquerda;
    }

    public int getAltura() {
        return Altura;
    }

    public void setAltura(int altura) {
        Altura = altura;
    }
}

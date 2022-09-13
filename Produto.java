package modelo;

public class Produto {

    private int codigo;
    private String nome;
    private int qtd;
    private double preco;

    //GETTERS AND SETTERS

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    //METODOS
    void entrada(int valor){
        if(valor>0){
            qtd += valor;
        }else {
            System.out.println("Valor invalido! Insira entrada MAIOR que ZERO.");
        }
    }

}

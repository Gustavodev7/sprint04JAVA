package entidades;

public class Produto {
    private String codigo;
    private String descricao;
    private double preco;
    private String localizacao;

    // Construtor
    public Produto(String codigo, String descricao, double preco, String localizacao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.localizacao = localizacao;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    @Override
    public String toString() {
        return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + ", localizacao=" + localizacao + "]";
    }
}

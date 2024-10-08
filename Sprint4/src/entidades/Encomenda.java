package entidades;

public class Encomenda {
    private String clienteID;
    private String nomeArquivo;

    // Construtor com parâmetros
    public Encomenda(String clienteID, String nomeArquivo) {
        this.clienteID = clienteID;
        this.nomeArquivo = nomeArquivo;
    }

    // Getters e Setters
    public String getClienteID() {
        return clienteID;
    }

    public void setClienteID(String clienteID) {
        this.clienteID = clienteID;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public String toString() {
        return "Encomenda [clienteID=" + clienteID + ", nomeArquivo=" + nomeArquivo + "]";
    }
}

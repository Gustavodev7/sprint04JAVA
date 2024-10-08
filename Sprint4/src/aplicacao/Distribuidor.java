package aplicacao;

import entidades.Encomenda;
import entidades.Produto;
import filas.FilaEncomendas;
import filas.FilaProdutos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Distribuidor {
    public static Scanner le = new Scanner(System.in);
    public static FilaEncomendas filaEncomendas = new FilaEncomendas();

    public static void main(String[] args) {
        // Declara e inicia as filas
        geraFilaEncomendas(filaEncomendas);

        int opcao;
        do {
            System.out.println("0 - Encerrar atendimento");
            System.out.println("1 - Inserir encomenda na fila para aguardar atendimento");
            System.out.println("2 - Atender uma encomenda");
            System.out.print("Opcao: ");
            opcao = le.nextInt();
            le.nextLine(); // Consome o '\n' após a leitura de um número

            switch (opcao) {
                case 0:
                    System.out.println("Encerrando o atendimento...");
                    break;
                case 1:
                    inserirEncomenda();
                    break;
                case 2:
                    atenderEncomenda();
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }

        } while (opcao != 0);

        le.close();
    }

    // Método para gerar a fila de encomendas a partir do arquivo "ListaEncomendas.txt"
    public static void geraFilaEncomendas(FilaEncomendas fila) {
        String caminhoDoArquivo = "src/arquivos/ListaEncomendas.txt";

        try {
            // Criar um objeto File com o caminho do arquivo
            File arquivo = new File(caminhoDoArquivo);

            // Criar um Scanner para ler o arquivo
            Scanner leArq = new Scanner(arquivo);

            // Loop para ler linha por linha até o final do arquivo
            while (leArq.hasNextLine()) {
                String linha = leArq.nextLine();
                String[] partes = linha.split(",");
                String clienteID = partes[0].trim();
                String nomeArquivo = partes[1].trim();

                Encomenda obj = new Encomenda(clienteID, nomeArquivo);
                fila.enqueue(obj);
            }

            // Fechar o objeto da classe Scanner le
            leArq.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }

    // Método para inserir uma nova encomenda na fila
    private static void inserirEncomenda() {
        System.out.print("Informe o ID do cliente: ");
        String clienteID = le.nextLine();
        System.out.print("Nome do arquivo de produtos encomendados: ");
        String nomeArquivo = le.nextLine();

        Encomenda encomenda = new Encomenda(clienteID, nomeArquivo);
        filaEncomendas.enqueue(encomenda);
        System.out.println("Encomenda inserida na fila com sucesso.");
    }

 // Método para atender uma encomenda
    private static void atenderEncomenda() {
        if (filaEncomendas.isEmpty()) {
            System.out.println("Não há encomendas para serem atendidas.");
            return;
        }

        Encomenda encomenda = filaEncomendas.dequeue();
        System.out.println("Atendimento do pedido do cliente " + encomenda.getClienteID() + " está iniciando.");

        FilaProdutos filaProdutos = new FilaProdutos();
        geraFilaProdutos(filaProdutos, encomenda.getNomeArquivo());

        if (filaProdutos.isEmpty()) {
            System.out.println("Arquivo de produtos não encontrado ou vazio: " + encomenda.getNomeArquivo());
            return;
        }

        double valorTotal = 0.0;

        while (!filaProdutos.isEmpty()) {
            Produto produto = filaProdutos.dequeue();
            System.out.println("Produto [codigo=" + produto.getCodigo() + ", descricao=" + produto.getDescricao() +
                    ", preco=" + produto.getPreco() + ", localizacao=" + produto.getLocalizacao() + "]");
            System.out.print("O produto foi encontrado na prateleira? (1-sim): ");
            int encontrado = le.nextInt();
            le.nextLine(); // Consome o '\n'

            if (encontrado == 1) {
                valorTotal += produto.getPreco();
            } else {
                filaProdutos.enqueue(produto); // Reinsere o produto no final da fila
                System.out.println("Voltar depois para colocar no carrinho");
            }
        }

        System.out.println("Atendimento da encomenda foi finalizado com sucesso");
        System.out.printf("Valor total da compra: R$%.2f%n", valorTotal);
    }


    // Método para gerar a fila de produtos a partir do arquivo de encomenda
    public static void geraFilaProdutos(FilaProdutos fila, String nomeArquivo) {
        String caminhoDoArquivo = "src/arquivos/" + nomeArquivo;

        try {
            File arquivo = new File(caminhoDoArquivo);
            Scanner leArq = new Scanner(arquivo);

            while (leArq.hasNextLine()) {
                String linha = leArq.nextLine();
                String[] partes = linha.split(",");
                String codigo = partes[0].trim();
                String descricao = partes[1].trim();
                double preco = Double.parseDouble(partes[2].trim());
                String localizacao = partes[3].trim();

                Produto produto = new Produto(codigo, descricao, preco, localizacao);
                fila.enqueue(produto);
            }

            leArq.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter preço para número: " + e.getMessage());
        }
    }
}

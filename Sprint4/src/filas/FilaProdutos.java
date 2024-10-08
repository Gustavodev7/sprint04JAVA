package filas;

import entidades.Produto;

public class FilaProdutos {

    // Nó da fila que armazena o produto
    private static class Node {
        Produto produto;
        Node next;

        Node(Produto produto) {
            this.produto = produto;
            this.next = null;
        }
    }

    private Node front; // Início da fila (primeiro produto)
    private Node rear;  // Fim da fila (último produto)
    private int size;   // Tamanho da fila

    // Construtor da fila
    public FilaProdutos() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Método para verificar se a fila está vazia
    public boolean isEmpty() {
        return front == null;
    }

    // Método para inserir um produto na fila
    public void enqueue(Produto produto) {
        Node newNode = new Node(produto);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = newNode;
        }
        size++;
    }

    // Método para remover um produto da fila
    public Produto dequeue() {
        if (isEmpty()) {
            return null;
        }
        Produto produto = front.produto;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return produto;
    }

    // Método para obter o tamanho da fila
    public int getSize() {
        return size;
    }

    // Método para visualizar o produto na frente da fila
    public Produto peek() {
        if (isEmpty()) {
            return null;
        }
        return front.produto;
    }

    // Método para imprimir a fila (opcional, para fins de depuração)
    public void printQueue() {
        Node current = front;
        while (current != null) {
            System.out.println(current.produto);
            current = current.next;
        }
    }
}

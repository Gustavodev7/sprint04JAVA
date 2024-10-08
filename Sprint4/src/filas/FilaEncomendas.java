package filas;

import entidades.Encomenda;

public class FilaEncomendas {

    // Classe interna para representar um nó da fila que armazena a encomenda
    private static class Node {
        Encomenda encomenda;
        Node next;

        Node(Encomenda encomenda) {
            this.encomenda = encomenda;
            this.next = null;
        }
    }

    private Node front; // Início da fila (primeira encomenda)
    private Node rear;  // Fim da fila (última encomenda)
    private int size;   // Tamanho da fila

    // Construtor da fila
    public FilaEncomendas() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Método para verificar se a fila está vazia
    public boolean isEmpty() {
        return front == null;
    }

    // Método para inserir uma encomenda na fila
    public void enqueue(Encomenda encomenda) {
        Node newNode = new Node(encomenda);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = newNode;
        }
        size++;
    }

    // Método para remover uma encomenda da fila
    public Encomenda dequeue() {
        if (isEmpty()) {
            return null;
        }
        Encomenda encomenda = front.encomenda;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return encomenda;
    }

    // Método para obter o tamanho da fila
    public int getSize() {
        return size;
    }

    // Método para visualizar a encomenda na frente da fila sem removê-la
    public Encomenda peek() {
        if (isEmpty()) {
            return null;
        }
        return front.encomenda;
    }

    // Método para imprimir a fila (opcional, para fins de depuração)
    public void printQueue() {
        Node current = front;
        while (current != null) {
            System.out.println(current.encomenda);
            current = current.next;
        }
    }
}

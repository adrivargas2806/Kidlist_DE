package co.edu.umanizales.kids_list.model;

import co.edu.umanizales.kids_list.KidsListApplication;
import lombok.Data;

import java.util.logging.Formatter;


@Data
public class ListSE {
    private Node head;
    private int size;

    // Método para agregar un niño al final de la lista
    public void add(Kid kid) {
        if (head == null) {
            head = new Node(kid);
        } else {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node(kid));
        }
        size++;
    }

    // Método para agregar un niño al inicio de la lista
    public void addToStart(Kid kid) {
        if (head == null) {
            head = new Node(kid);
        } else {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    // Método para agregar un niño en una posición específica
    public void addInPosition(Kid kid, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Posición incorrecta");
        }
        if (position == 0) {
            addToStart(kid);
            return;
        }
        Node newNode = new Node(kid);
        Node temp = head;

        for (int i = 0; i < position - 1; i++) {
            temp = temp.getNext();
        }
        newNode.setNext(temp.getNext());
        temp.setNext(newNode);

        size++;
    }

    // Método para invertir la lista
    public void invert() {
        if (head == null || head.getNext() == null) {
            return;
        }

        Node prev = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }

    // Método para eliminar un nodo
    public void deleteByPosition(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }

        if (position == 0) {
            head = head.getNext();
        } else {
            Node temp = head;
            for (int i = 0; i < position - 1; i++) {
                temp = temp.getNext();
            }
            temp.setNext(temp.getNext().getNext());
        }
        size--;
    }

    // Método para eliminar un nodo por ID
    public void deleteByID(String id) {
        if (head == null) {
            return;
        }

        if (head.getData().getId().equals(id)) {
            head = head.getNext();
            size--;
            return;
        }

        Node temp = head;
        while (temp.getNext() != null) {
            if (temp.getNext().getData().getId().equals(id)) {
                temp.setNext(temp.getNext().getNext());
                size--;
                return;
            }
            temp = temp.getNext();
        }
    }

    // Método para intercalar niños por género
    public void mixByGender() {
        if (size > 2) {
            int posB = 1;
            int posG = 2;
            ListSE listCp = new ListSE();
            Node temp = head;
            while (temp != null) {
                if (temp.getData().getGender().equalsIgnoreCase("F")) {
                    listCp.addInPosition(temp.getData(), posG);
                    posG += 2;
                } else {
                    listCp.addInPosition(temp.getData(), posB);
                    posB += 2;
                }
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }
}


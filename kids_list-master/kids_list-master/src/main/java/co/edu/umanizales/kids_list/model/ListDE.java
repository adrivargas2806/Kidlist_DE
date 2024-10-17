package co.edu.umanizales.kids_list.model;

import lombok.Data;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    public void add(Kid kid) {
        if (head == null) {
            head = new NodeDE(kid);
        } else {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new NodeDE(kid));
            temp.getNext().setPrevious(temp);
        }
        size++;
    }
    //Método para agregar a un niño al principio de la lista
    public void addToStart(Kid kid) {
        NodeDE newNode = new NodeDE(kid);
        if (head == null) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    // Método para agregar un niño en una posición específica
    public void addInPosition(Kid kid, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Posición incorrecta");
        }
        NodeDE newNode = new NodeDE(kid);
        if (position == 0) {
            addToStart(kid);
        } else {
            NodeDE temp = head;
            for (int i = 0; i < position - 1; i++) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            newNode.setPrevious(temp);
            if (temp.getNext() != null) {
                temp.getNext().setPrevious(newNode);
            }
            temp.setNext(newNode);
        }
        size++;
    }

    // Método para invertir la lista
    public void invert() {
        if (head == null || head.getNext() == null) {
            return;
        }
        NodeDE current = head;
        NodeDE temp = null;
        while (current != null) {
            temp = current.getPrevious();
            current.setPrevious(current.getNext());
            current.setNext(temp);
            current = current.getPrevious();
        }
        if (temp != null) {
            head = temp.getPrevious();
        }
    }

    // Método para eliminar un nodo por posición
    public void deleteByPosition(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }
        if (position == 0) {
            head = head.getNext();
            if (head != null) {
                head.setPrevious(null);
            }
        } else {
            NodeDE temp = head;
            for (int i = 0; i < position; i++) {
                temp = temp.getNext();
            }
            temp.getPrevious().setNext(temp.getNext());
            if (temp.getNext() != null) {
                temp.getNext().setPrevious(temp.getPrevious());
            }
        }
        size--;
    }

    // Método para eliminar un nodo por ID
    public void deleteByID(String id) {
        NodeDE temp = head;
        while (temp != null) {
            if (temp.getData().getId().equals(id)) {
                if (temp == head) {
                    head = head.getNext();
                    if (head != null) {
                        head.setPrevious(null);
                    }
                } else {
                    temp.getPrevious().setNext(temp.getNext());
                    if (temp.getNext() != null) {
                        temp.getNext().setPrevious(temp.getPrevious());
                    }
                }
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
            ListDE listCp = new ListDE();
            NodeDE temp = head;
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
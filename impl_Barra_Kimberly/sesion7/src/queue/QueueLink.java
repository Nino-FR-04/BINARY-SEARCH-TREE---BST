package queue;

import exceptions.ExceptionIsEmpty;
import nodo.QueueNodo;

public class QueueLink<E> implements Queue<E> {
	private QueueNodo<E> first;
    private QueueNodo<E> last;

    public QueueLink() {
        this.first = null;
        this.last = null;
    }

    public void enqueue(E x) {
        QueueNodo<E> aux = new QueueNodo<>(x);
        if (this.isEmpty()) {
            this.first = aux;
        } else {
            this.last.setNext(aux);
        }
        this.last = aux;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        E data = this.first.getData();
        this.first = this.first.getNext();
        if (this.first == null) {
            this.last = null;
        }
        return data;
    }

    public E front() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        return this.first.getData();
    }

    public E back() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        return this.last.getData();
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public int size() {
        int count = 0;
        QueueNodo<E> current = this.first;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Cola vacía";
        }

        StringBuilder sb = new StringBuilder();
        QueueNodo<E> current = this.first;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(" -> ");
            }
            current = current.getNext();
        }
        return sb.toString();
    }
}
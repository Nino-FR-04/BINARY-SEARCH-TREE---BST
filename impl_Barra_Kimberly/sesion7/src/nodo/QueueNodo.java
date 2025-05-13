package nodo;

public class QueueNodo<E> {
	 private E data;
	    private QueueNodo<E> next;

	    public QueueNodo(E data) {
	        this.data = data;
	        this.next = null;
	    }

	    public E getData() {
	        return data;
	    }

	    public QueueNodo<E> getNext() {
	        return next;
	    }

	    public void setNext(QueueNodo<E> next) {
	        this.next = next;
	    }
}

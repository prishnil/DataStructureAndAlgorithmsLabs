public class SinglyLinkedList<E> {

	private static class Node<E> {
		private E data;
		private Node<E> next;

		public Node(E e, Node<E> n) {
			data = e;
			next = n;
		}

		public E getElement() {
			return data;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> n) {
			next = n;
		}
	}

	private Node<E> first;
	private Node<E> last;
	private int size;

	public SinglyLinkedList() {
		first = null;
		last = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public E first() {
		return first.getElement();
	}

	public E last() {
		return last.getElement();
	}

	public void addFirst(E element) {
		Node<E> a = new Node<E>(element, null);
		if (isEmpty() == true) {
			first = last = a;
		} else {
			a.setNext(first);
			first = a;
		}
		size++;
	}

	public void addLast(E element) {
		Node<E> b = new Node<E>(element, null);
		if (isEmpty() == true) {
			first = last = b;
		} else {
			last.setNext(b);
			last = b;
		}
		size++;
	}

	public E removeFirst() {
		if (first == null) {
			return null;
		} else {
			Node<E> c = first;
			first = first.getNext();
			size--;
			return c.getElement();
		}
	}
}
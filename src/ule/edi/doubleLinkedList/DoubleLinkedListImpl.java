package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.w3c.dom.Node;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {


	//	referencia al primer aux de la lista
	private DoubleNode<T> front;

	//	referencia al Último aux de la lista
	private DoubleNode<T> last;


	private class DoubleNode<T> {

		DoubleNode(T element) {
			this.elem = element;
			this.next = null;
			this.prev = null;
		}

		T elem;

		DoubleNode<T> next;
		DoubleNode<T> prev;
	}

	///// ITERADOR normal //////////

	@SuppressWarnings("hiding")
	private class DoubleLinkedListIterator<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIterator(DoubleNode<T> aux) {
			node = aux;
		}

		@Override
		public boolean hasNext() {
			return node != null;
		}
	

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T element = node.elem;
			node = node.next;
			return element;
		}
	}

	////// FIN ITERATOR



	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorReverse<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIteratorReverse(DoubleNode<T> aux) {
			node = aux;
			}

		@Override
		public boolean hasNext() {
			return node != null;
			}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T element = node.elem;
			node = node.prev;
			return element;
		}
	}

	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorProgress<T> implements Iterator<T> {
		DoubleNode<T> node;
		private int saltar = 1;
		public DoubleLinkedListIteratorProgress(DoubleNode<T> aux) {
			node = aux;
			}

		@Override
		public boolean hasNext() {
			return node != null;
			}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T element = node.elem;
			for(int i = 0; i < saltar && node != null; i++) {
				node = node.next;
			}
			saltar++;
			return element;
		}
	}

	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorProgressReverse<T> implements Iterator<T> {
		DoubleNode<T> node;
		private int saltar = 1;
		public DoubleLinkedListIteratorProgressReverse(DoubleNode<T> aux) {
			node = aux;
			}

		@Override
		public boolean hasNext() {
			return node != null;
			}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T element = node.elem;
			for(int i = 0; i < saltar && node != null; i++) {
				node = node.prev;
			}
			saltar++;
			return element;
		}
	}
	
	// TODO: añadir clases para el resto de iteradores

	/////

	@SafeVarargs
	public DoubleLinkedListImpl(T...v ) {
		// permite añadir varios elementos a la lista en el constructor
		for (T elem:v) {
			this.addLast(elem);
		}
	}


	@Override
	public boolean isEmpty() {
		return size() == 0;
	}


	@Override
	public void clear() {
		if(isEmpty()) {
			return;
		} else {
			front = null;
			last = null;
		}
	}

	@Override
	public void addFirst(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		DoubleNode<T> nuevo = new DoubleNode<T>(elem);
		if(isEmpty()) {
			front = nuevo;
			last = nuevo;
		} else {
			nuevo.next = front;
			front.prev = nuevo;
			front = nuevo;
		}
		
	}


	@Override
	public void addLast(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		DoubleNode<T> nuevo = new DoubleNode<T>(elem);
		if(isEmpty()) {
			front = nuevo;
			last = nuevo;
		} else {
			nuevo.prev = last;
			last.next = nuevo;
			last = nuevo;
		}
		
	}


	@Override
	public void addPos(T elem, int position) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(position <= 0) {
			throw new IllegalArgumentException();
		}
		if(position == 1) {
			addFirst(elem);
			return;
		}
		DoubleNode<T> current = front;
		for(int i = 0; i < position - 2 && current != null; i++) {
			current = current.next;
		}
		if(current == null) {
			addLast(elem);
			return;
		}
		DoubleNode<T> nuevo = new DoubleNode<T>(elem);
		nuevo.next = current.next;
		nuevo.prev = current;
		if(current.next != null) {
			current.next.prev = nuevo;
		}
		current.next = nuevo;
		if(nuevo.next == null) {
			last = nuevo;
		}
		
	}

	
	@Override
	public T getElemPos(int position) {
		if(position < 1 || position > size()) {
			throw new IllegalArgumentException();
		}
		DoubleNode<T> current = front;
		for(int i = 0; i < position -1; i++) {
			current = current.next;
		}
		return current.elem;
	}


	@Override
	public int getPosFirst(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		DoubleNode<T> current = front;
		int pos = 1;
		for(int i = 0; current != null; i++) {
			if(current.elem.equals(elem)) {
				pos = i + 1;
				return pos;
			}
			current = current.next;
		}
		if(pos == 1) {
			throw new NoSuchElementException();
		}
		return pos;
	}


	@Override
	public int getPosLast(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		DoubleNode<T> current = front;
		int pos = 1;
		for(int i = 0; current != null; i++) {
			if(current.elem.equals(elem)) {
				pos = i + 1;
			}
			current = current.next;
		}
		if(pos == 1) {
			throw new NoSuchElementException();
		}	
		return pos;
	}

	
	@Override
	public T removeLast()  throws EmptyCollectionException{
		if(isEmpty()) {
			throw new EmptyCollectionException("La lista está vacía.");
		}
		T removeElement = null;
		if(size() == 1) {
			removeElement = front.elem;
			front = null;
			last = null;
		} else {
			DoubleNode<T> current = front;
			while(current.next.next != null) {
				current = current.next;
			}
			removeElement = current.next.elem;
			current.next = null; 
			last = current;
		}
		return removeElement;
	}
	

	@Override
	public T removePos(int pos)  throws EmptyCollectionException{
		if(isEmpty()) {
			throw new EmptyCollectionException("La lista está vacía.");
		}
		if(pos < 1 || pos > size()) {
			throw new IllegalArgumentException();
		}
		T removeElement = null;
		if(pos ==  1) {
			removeElement = front.elem;
			front = front.next;
			if(front != null) {
				front.prev = null;
			} else {
				last = null;
			}
		} else {
			DoubleNode<T> current = front;
			for(int i = 0; i < pos - 2; i++) {
				current = current.next;
			}
			removeElement = current.next.elem;
			current.next = current.next.next;
			if(current.next != null) {
				current.next.prev = current;
			} else {
				last = current;
			}
		}
		return removeElement;
	}


	@Override
	public int removeN(T elem, int times) throws EmptyCollectionException {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(times < 1) {
			throw new IllegalArgumentException();
		}
		if(isEmpty()) {
			throw new EmptyCollectionException("La lista está vacía");
		}
		DoubleNode<T> current = front;
		int count = 0;
		while(current != null) {
			if(current.elem.equals(elem)) {
				if(current.prev == null) {
					front = current.next;
					if(front != null) {
						front.prev = null;
					} else {
						last = null;
					}
				}else if(current.next == null) {
					current.prev.next = null;
					last = current.prev;
				} else {
					current.prev.next = current.next;
					current.next.prev = current.prev;
				}
				count++;
				if(count == times) {
					break;
				}
			}
			current = current.next;
		}
		if(count == 0) {
			throw new NoSuchElementException();
		}
		return count;
	}



	@Override
	public DoubleList<T> copy() {
		DoubleList<T> copyList = new DoubleLinkedListImpl<>();
		DoubleNode<T> current = front;
		while(current != null) {
			copyList.addLast(current.elem);
			current = current.next;
		}
		return copyList;
	}


	@Override
	public int size() {
		int count = 0;
		DoubleNode<T> current = front;
		while(current != null){
			current = current.next;
			count++;
		}
		return count;
	}


	
	@Override
	public int maxRepeated() {
	DoubleNode<T> current = front;
	DoubleNode<T> aux = current;;
	int maxRepeated = 0;
	int max = 0;
	while(current != null) {
		while(aux != null) {
			if(aux.elem.equals(current.elem)) {
				maxRepeated++;
			}
			aux = aux.next;
		}
		if(maxRepeated > max) {
			max = maxRepeated;
		}
		current = current.next;
	}
		return max;
	}


	@Override
	public void addAfter(T elem, T target) {
		if(elem == null || target == null) {
			throw new NullPointerException();
		}
		DoubleNode<T> current = front;
		while(current != null) {
			if(current.elem.equals(target)) {
				DoubleNode<T> aux = new DoubleNode<T>(elem);
				aux.prev = current;
				aux.next = current.next;
				if(current.next == null) {
					last = aux;
				} else {
					current.next.prev = aux;
				}
			current.next = aux;
			return;
			}
			current = current.next;
		}
		addLast(elem);
	}


	@Override
	public void addAfterAll(T elem, T target) {
		if(elem == null || target == null) {
			throw new NullPointerException();
		}
		DoubleNode<T> current = front;
		int count = 0;
		while(current != null) {
			if(current.elem.equals(target)) {
				DoubleNode<T> aux = new DoubleNode<T>(elem);
				aux.prev = current;
				aux.next = current.next;
				if(current.next == null) {
					last = aux;
				} else {
					current.next.prev = aux;
				}
				current.next = aux;
				count ++;
				current = aux.next;
			} else {
				current = current.next;
			}
		}
		if(count == 0) {
			addLast(elem);
		}	
	}


	@Override
	public T removePenul() throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("La lista está vacía.");
		}
		if(size() == 1) {
			throw new NoSuchElementException();
		}
		T removeElement = null;
		if(size() == 2) {
			removeElement = front.elem;
			front = front.next;
		} else {
			DoubleNode<T> current = front;
			while(current.next.next.next != null) {
				current = current.next;
			}
			removeElement = current.next.elem;
			current.next = last;
		}
		return removeElement;
	}


	@Override
	public int countElem(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		DoubleNode<T> current = front;
		int count = 0;
		while (current != null) {
			if(current.elem.equals(elem)) {
				count++;
			}
			current = current.next;
		}
		return count;
	}

	@Override
	public boolean sameElems(DoubleList<T> other) {
		if(other == null) {
			throw new NullPointerException();
		}
		boolean contains = true;
		for(int i = 1; i <= other.size(); i++) {
			if(this.countElem(other.getElemPos(i)) == 0) {
				contains = false;
			}
		}
		for(int i = 1; i <= size(); i++) {
			if(other.countElem(this.getElemPos(i)) == 0) {
				contains = false;
			}
		}
		return contains;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("(");
		DoubleNode<T> current = front;
		while (current != null) {
			result.append(current.elem + " ");
			current = current.next;
		}
		result.append(")");
		return result.toString();
	}
	
	@Override
	public String toStringReverse() {
		StringBuilder result = new StringBuilder();
		result.append("(");
		DoubleNode<T> current = last;
		while (current != null) {
			result.append(current.elem + " ");
			current = current.prev;
		}
		result.append(")");
		return result.toString();
	}


	@Override
	public String toStringFromUntil(int from, int until) {
		if(from <= 0 || until <= 0 || until < from) {
			throw new IllegalArgumentException();
		}
		StringBuilder result = new StringBuilder();
		result.append("(");
		DoubleNode<T> current = front;
		if(from > size() && until > size()) {
			result.append(")");
			return result.toString();
		}
		for(int i = 1; i < from; i++) {
			current = current.next;
		}
		int count = from;
		while (current != null && count <= until) {
			result.append(current.elem.toString());
			if(current.next != null && count != until) {
				result.append(" ");
			}
			current = current.next;
			count++;
		}
		result.append(" )");
		return result.toString();
	}
	
	@Override
	public String toStringFromUntilReverse(int from, int until) {
		if (from <= 0 || until <= 0 || from < until) {
			throw new IllegalArgumentException();
		}
		StringBuilder result = new StringBuilder();
		result.append("(");
		if (isEmpty()) {
			result.append(")");
			return result.toString();
		}
		DoubleNode<T> current = last;
		if (from > size()) {
			current = last;
			from = size();
		} else {
			current = last;
			for (int i = 0; i < from && current != null; i++) {
				current = current.prev;
			}
		}
		while (current != null && from >= until) {
			result.append(current.elem.toString());
			current = current.prev;
			if (from > until) {
				result.append(" ");
			}
			from--;
		}
		result.append(" )");
		return result.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new DoubleLinkedListIterator<>(front);
	}

	@Override
	public Iterator<T> reverseIterator() {
		return new DoubleLinkedListIteratorReverse<>(last);
	}
	

	@Override
	public Iterator<T> progressIterator() {
		return new DoubleLinkedListIteratorProgress<>(front);
	}

	@Override
	public Iterator<T> progressReverseIterator() {
		return new DoubleLinkedListIteratorProgressReverse<>(last);
	}


}
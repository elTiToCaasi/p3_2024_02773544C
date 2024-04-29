package ule.ed.recursivelist;


public class LinkedEDList<T> implements EDList<T> {

	//	referencia al primer  de la lista
	private Node<T> front;

	

	private class Node<T> {

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

		T elem;

		Node<T> next;
	}

	@Override
	public boolean isEmpty() {
		// TODO 
		return false;
	}


	@Override
	public int size() {
		// TODO RECURSIVAMENTE
		return 0;
	}


	@Override
	public void addLast(T elem) {
		// TODO RECURSIVAMENTE
		
	}

	
	@Override
	public void addPos(T elem, int position) {
		// TODO RECURSIVAMENTE
		
	}

	@Override
	public boolean addBefore(T elem, T target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T getElemPos(int position) {
		// TODO RECURSIVAMENTE
		return null;
	}



	@Override
	public int getPosFirst(T elem) {
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public int getPosLast(T elem) {
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO RECURSIVAMENTE
		return null;
	}



	

	@Override
	public T removeLastElem(T elem) {
		// TODO RECURSIVAMENTE
		return null;
	}



	@Override
	public EDList<T> reverse() {
		// TODO RECURSIVAMENTE
		return null;
	}



	@Override
	public int removeOddElements(){
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public int removeConsecDuplicates() {
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public String toSringExceptFromUntilReverse(int from, int until) {
		// TODO RECURSIVAMENTE
		return null;
	}


	@Override
	public boolean lengthEqualsTo(int n) {
		// TODO RECURSIVAMENTE
		return false;
	}

	@Override
	public int removeEvenElements() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int removeFirstElem(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO RECURSIVAMENTE
	
		return null;
	}


	

	
	
}

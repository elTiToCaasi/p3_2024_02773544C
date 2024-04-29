package ule.ed.recursivelist;

import static org.junit.Assert.*;

import org.junit.*;

import java.util.NoSuchElementException;


public class LinkedEDListTest {
	private LinkedEDList<String> lista;
	
	@Before
	public void test() {
		 lista= new LinkedEDList<String>();
	}

	@Test
	public void test_Vacia() {
		assertEquals(0,lista.size());
	}
	@Test
	public void test_Vacia_addlast_mytest(){
		assertEquals(0,lista.size());
		lista.addLast("5");
		assertEquals(1,lista.size());
		assertEquals("(5 )", lista.toString());
	}
	@Test
	public void test_AddLast() {
		lista.addLast("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
	}
	@Test(expected = NullPointerException.class)
	 public void test_addlast_null(){
		lista.addLast(null);
	}
	@Test(expected = NullPointerException.class)
	public void test_addpos_null(){
		lista.addPos(null,1);
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_addpos_illegal(){
		lista.addPos("3",-1);
	}
	@Test
	public void test_addpos_posmayorqsize(){
		lista.addPos("3",9);
		lista.addPos("3",1);
		lista.addPos("3",1);
		lista.addPos("3",1);
		assertEquals("(3 3 3 3 )",lista.toString());
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_getelem_illegal1(){

		lista.getElemPos(-1);
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_getelem_illegal2(){
		assertEquals(0,lista.size());
		lista.getElemPos(9);
	}
	@Test
	public void test_getelempos() {
		lista.addLast("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("7");
		Assert.assertEquals("(2 3 7 )", lista.toString());
		assertEquals("3",lista.getElemPos(2));
	}
	@Test(expected = NullPointerException.class)
	public void test_getposlas_illegal(){
		assertEquals(0,lista.size());
		lista.getPosLast(null);
	}
	@Test
	public void test_getposlast() {
		lista.addLast("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("2");
		Assert.assertEquals("(2 3 2 )", lista.toString());
		Assert.assertEquals(3,lista.getPosLast("2"));
	}
	@Test(expected = NoSuchElementException.class)
	public void test_getposlast_nosucheleme() {
		lista.addLast("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("2");
		Assert.assertEquals(0,lista.getPosLast("9"));
	}
	@Test(expected = NullPointerException.class)
	public void test_getposfirs_illegal(){
		assertEquals(0,lista.size());
		lista.getPosFirst(null);
	}
	@Test
	public void test_getposfirst() {
		lista.addLast("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("2");
		Assert.assertEquals("(2 3 2 )", lista.toString());
		Assert.assertEquals(1,lista.getPosFirst("2"));
	}
	@Test(expected = NoSuchElementException.class)
	public void test_getposfirst_nosucheleme() {
		lista.addLast("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addLast("3");
		Assert.assertEquals("(2 3 )", lista.toString());
		lista.addLast("2");
		Assert.assertEquals(0,lista.getPosFirst("9"));
	}
	@Test
	public void removeoddelem(){
		lista.addLast("1");
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("5");
		lista.addLast("6");
		lista.removeOddElements();
		Assert.assertEquals("(1 3 5 )",lista.toString());
	}
	@Test
	public void removeoddelemvacia(){
		Assert.assertTrue(lista.isEmpty());
		lista.removeEvenElements();
		Assert.assertEquals(0,lista.toString());
	}
	@Test
	public void removeevenelem(){
		lista.addLast("1");
		lista.addLast("2");
		lista.addLast("3");
		lista.addLast("4");
		lista.addLast("5");
		lista.addLast("6");
		lista.removeEvenElements();
		Assert.assertEquals("(2 4 6 )",lista.toString());
	}
	@Test
	public void removeevenelemvacia(){
		Assert.assertTrue(lista.isEmpty());
		lista.removeOddElements();
		Assert.assertEquals(0,lista.toString());
	}














	@Test(expected=EmptyCollectionException.class)
	public void test_RemoveLastElem_Vacia() throws EmptyCollectionException{
		lista.removeLastElem("A");
	}

	@Test(expected=NullPointerException.class)
	public void test_addLast_ElementoNulo() {
			lista.addLast(null);

	}
	
	@Test
	public void linkedtestAddPos_Varios() {
		lista.addPos("2",1);
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addPos("3",1);
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addPos("7",2);
		Assert.assertEquals("(3 7 2 )", lista.toString());
		lista.addPos("10",3);
		Assert.assertEquals("(3 7 10 2 )", lista.toString());
		
	}
	
	// TODO  AÑADIR RESTO DE METODOS DE TESTS
	
	
}

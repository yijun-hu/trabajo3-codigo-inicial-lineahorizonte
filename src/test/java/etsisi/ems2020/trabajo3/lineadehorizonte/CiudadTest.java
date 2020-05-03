package etsisi.ems2020.trabajo3.lineadehorizonte;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import junit.framework.TestCase;

public class CiudadTest extends TestCase{

	public CiudadTest(String sTestName) {
		super(sTestName);
	}

	@Before
	public void setUp() throws Exception {		
		
	}

	@Test
	public void testGetLineaHorizonte1() {
		
		LineaHorizonte linea;
		Ciudad c;
		
		try {			
			
			c = new Ciudad();
			Edificio e1 = new Edificio(new Punto(1,4),3);
			c.addEdificio(e1);
			Edificio e2 = new Edificio(new Punto(2,7),9);
			c.addEdificio(e2);
			Edificio e3 = new Edificio(new Punto(4,4),12);
			c.addEdificio(e3);	
			Edificio e4 = new Edificio(new Punto(6,9),8);
			c.addEdificio(e4);
			Edificio e5 = new Edificio(new Punto(11,6),13);
			c.addEdificio(e5);
			Edificio e6 = new Edificio(new Punto(14,2),15);
			c.addEdificio(e6);		

			
			linea = c.getLineaHorizonte();	
			int x[]= {1,2,6,8,9,11,13,14,15};
			int y[]= {4,7,9,7,4,6,0,2,0};
			
			for(int i=0;i<linea.size();i++) {
			assertTrue(
					linea.getPunto(i).getX()== x[i]  && linea.getPunto(i).getY()==y[i]);
			}
		} catch (Exception e) {			
			fail("Test failed");
		}
	}
	
	@Test
	public void testGetLineaHorizonte2() {
		LineaHorizonte linea;
		Ciudad c;
		
		try {			
			
			c = new Ciudad();
			Edificio e1 = new Edificio(new Punto(3,5), 6);
			c.addEdificio(e1);
			Edificio e2 = new Edificio(new Punto(4,3),9);
			c.addEdificio(e2);
			
			linea = c.getLineaHorizonte();		
			int x[]= {3,6,9};
			int y[]= {5,3,0};
			for(int i=0;i<linea.size();i++) {
				assertTrue(
						linea.getPunto(i).getX()== x[i]  && linea.getPunto(i).getY()==y[i]);
				}
			
		} catch (Exception e) {			
			fail("Test failed");
		}
	}


	@After
	public void tearDown() throws Exception {
	}
	
	public static void main(String args[]) {
		Result result = JUnitCore.runClasses(CiudadTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

	}


}

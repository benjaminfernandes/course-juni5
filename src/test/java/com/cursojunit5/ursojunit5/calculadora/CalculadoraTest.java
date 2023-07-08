package com.cursojunit5.ursojunit5.calculadora;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cursojunit5.cursojunit5.Calculadora;

public class CalculadoraTest {

	@Test
	public void testSomar() {
		Calculadora calc = new Calculadora();
		Assertions.assertTrue(calc.soma(2, 3) == 5);
		Assertions.assertEquals(calc.soma(2, 3), 5);
	}
	
	@Test
	public void assertivas() {
		Assertions.assertEquals("Casa", "Casa");
		Assertions.assertNotEquals("Casa", "casa");
		Assertions.assertTrue("casa".equalsIgnoreCase("CASA"));
		Assertions.assertTrue("casa".endsWith("sa"));
		Assertions.assertTrue("casa".startsWith("ca"));
		
		List<String> s1 = new ArrayList<>();
		List<String> s2 = new ArrayList<>();
		List<String> s3 = null;
		
		Assertions.assertEquals(s1, s2); // verifica se possuem o mesmo conteúdo
		Assertions.assertSame(s1, s1);//verifica se é a mesma referência
		Assertions.assertNotEquals(s1, s3);
		Assertions.assertNull(s3);
		Assertions.assertNotNull(s1);
		
	}
	
	
	
}

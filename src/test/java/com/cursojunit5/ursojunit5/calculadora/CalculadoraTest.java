package com.cursojunit5.ursojunit5.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.cursojunit5.cursojunit5.Calculadora;

public class CalculadoraTest {

	Calculadora calc;
	
	@BeforeEach
	public void setup() {
		System.out.println("---- Before Each ----");
		calc = new Calculadora();
	}
	
	@BeforeAll
	public static void setupAll() {
		System.out.println("---- Before All ----");
	}
	
	@AfterEach
	public void setupdown() {
		System.out.println("---- After Each ----");
		calc = new Calculadora();
	}
	
	@AfterAll
	public static void setupdownAll() {
		System.out.println("---- After All ----");
	}

	@Test
	public void testSomar() {
		Assertions.assertTrue(calc.soma(2, 3) == 5);
		Assertions.assertEquals(calc.soma(2, 3), 5);
	}
	
	@Test
	public void deveRetornarNumeroInteiroNaDivisao() {
		float resultado = calc.dividir(6, 2);
		Assertions.assertEquals(3, resultado);
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
		Assertions.assertSame(s1, s1);// verifica se é a mesma referência
		Assertions.assertNotEquals(s1, s3);
		Assertions.assertNull(s3);
		Assertions.assertNotNull(s1);

	}

	@Test
	public void deveLancarExcecaoQuandoDividirPorZero() {
		ArithmeticException assertThrows = Assertions.assertThrows(ArithmeticException.class, () -> {
			float resultado = 10 / 0;
		});
		Assertions.assertEquals("/ by zero", assertThrows.getMessage());
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = {"Teste1", "Teste2"})
	public void testStrings(String param) {
		System.out.println(param);
		assertNotNull(param);
	}
	
	@ParameterizedTest
	@CsvSource(value = {
			"6, 2, 3",
			"6, -2, -3",
			"10 , 5, 2",
			"0, 2, 0"
	})
	public void deveDividirCorretamente(int num, int den, int res) {
		float resultado = calc.dividir(num, den);
		assertEquals(res, resultado);
	}
	

}

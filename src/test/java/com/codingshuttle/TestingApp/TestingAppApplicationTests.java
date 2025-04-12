package com.codingshuttle.TestingApp;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//@SpringBootTest
@Slf4j
class TestingAppApplicationTests {

	@BeforeEach
	void setUp(){
		log.info("set up the method");
	}

	@AfterEach
	void tearDown(){
		log.info("tearing down of the method");
	}

	@BeforeAll
	static void setUpOnce(){
		log.info("set up once ...");
	}

	@AfterAll
	static void tearDownOnce(){
		log.info("tearing down once ...");
	}

	@Test
//	@DisplayName(("displayTestNameTwo"))
	void testTwoNumber(){
		log.info("test two is run");
		int a = 5;
		int b = 3;
		int result = addTwoNumbers(a,b);
//		Assertions.assertEquals(8,result);
		Assertions.assertThat(result)
				.isEqualTo(8)
				.isCloseTo(7, Offset.offset(1));

		assertThat("Apple")
				.isEqualTo("Apple")
				.startsWith("App")
				.endsWith("le")
				.hasSize(5);
	}


	int addTwoNumbers(int a,int b){
		return a+b;
	}

	@Test
//	@Disabled
	void testTwoNumbers_whenDenominatorsIsZero_thenArithmeticException() {
		int a = 5;
		int b = 0;

		assertThatThrownBy(()->divideTwoNumbers(a,b))
				.isInstanceOf(ArithmeticException.class)
				.hasMessage("Tried to divide by zero");

	}
	double divideTwoNumbers(int a,int b){
		try{
			return  a/b;
		}catch (ArithmeticException e){
			log.error("Arithmetic exception occured: "+e.getLocalizedMessage());
			throw new ArithmeticException("Tried to divide by zero");
		}
	}

}

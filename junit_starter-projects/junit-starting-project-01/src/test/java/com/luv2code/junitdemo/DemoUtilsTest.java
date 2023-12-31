package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
//@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //Run tests in specific order
class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeAll
    static void setupBeforeEachClass(){
        System.out.println("@BeforeAll executes only once before all test methods");
    }

    @AfterAll
    static void tearDownAfterEachClass(){
        System.out.println("@AfterAll executes only once after all test methods");
    }


    @BeforeEach
    void setupBeforeEach(){
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach executes before the execution of each test method");
    }

    @AfterEach
    void tearDownAfterEach(){
        System.out.println("Running @AfterEach");
        System.out.println();
    }

    @Test
   @DisplayName("Test Equals and Not Equals") // To print customize test name for each test
    void test_Equals_And_Not_Equals(){
        System.out.println("Running test : testEqualsAndNotEquals");
        assertEquals(6, demoUtils.add(2,4), "2+4 must be 6");
        assertNotEquals(6, demoUtils.add(1,9), "1+9 must not be 6");
        assertEquals(6,demoUtils.multiply(2,3), "3*2 must be 6");
    }
    @Test
    @Order(1)
    @DisplayName("Test Null and Not Null")
    void test_Null_And_Not_Null(){
        System.out.println("Running test : testNullAndNotNull");
        String str1 = null;
        String str2 = "luv2code";
        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }

    @Test
    @Order(1)
    @DisplayName("Test Same and Not Same")
    void testSameAndNotSame(){
        String str1 = "luv2code";
        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Objects should refer to the same object");
        assertNotSame(str1, demoUtils.getAcademy(), "objects should not refer to the same object");
    }

    @Test
    @DisplayName("Test True and False")
    void testTrueAndFalse(){
        int grade1= 10;
        int grade2 = 5;
        assertTrue(demoUtils.isGreater(
                grade1,grade2
        ), "This should return true");
        assertFalse(demoUtils.isGreater(
                grade2,grade1
        ), "This should return false");
    }

    @Test
    @DisplayName("Test Array Equals")
    void testArrayEquals(){
        String[] stringArray = {"A", "B", "C"};
        assertArrayEquals(stringArray, demoUtils.getFirstThreeLettersOfAlphabet(), "String array should match");
    }

    @Test
    @DisplayName("Test Iterable Equals")
    void testIterableEquals(){
        List<String> list = List.of("luv", "2", "code");
        assertIterableEquals(list, demoUtils.getAcademyInList(), "List array should match");
    }

    @Test
    @DisplayName("Test Lines Equals")
    void testLinesEquals(){
        List<String> line = List.of("luv", "2", "code");
        assertLinesMatch(line, demoUtils.getAcademyInList(), "lines should match");
    }

    @Test
    @DisplayName("Throws and does not throw")
    void testThrowAndDoesNotThrow(){
        assertThrows(Exception.class, () -> { demoUtils.throwException(-1);}, "Should Throw Exception");
        assertDoesNotThrow(() -> { demoUtils.throwException(1);}, "Should Not Throw Exception");
    }

    @Test
    @DisplayName("TimeOut")
    void testTimeout(){
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {demoUtils.checkTimeout();}, "method should execute in 3 seconds");
    }
}
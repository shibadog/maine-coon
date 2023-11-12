package net.shibadog.mainecoon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QuestionServiceTests {

    @Test
    void test_additionCarry() {
        var service = new QuestionService();
        var actual = service.additionCarry(1);

        assertEquals(1, actual.siki().x1().length());
        assertEquals(1, actual.siki().x2().length());
        System.out.println(actual);
    }

    @Test
    void testSubtractionBorrow() {
        var service = new QuestionService();

        var actual = service.subtractionBorrow(1);
        System.out.println(actual);
        actual = service.subtractionBorrow(1);
        System.out.println(actual);
        actual = service.subtractionBorrow(1);
        System.out.println(actual);
        actual = service.subtractionBorrow(1);
        System.out.println(actual);

    }

    @Test
    void testMultiplication() {
        var service = new QuestionService();

        var actual = service.multiplication();
        System.out.println(actual);

        actual = service.multiplication();
        System.out.println(actual);
        actual = service.multiplication();
        System.out.println(actual);
        actual = service.multiplication();
        System.out.println(actual);
    }

    @Test
    void testDivisionRemainder() {
        var service = new QuestionService();

        var actual = service.divisionRemainder();
        System.out.println(actual);

        actual = service.divisionRemainder();
        System.out.println(actual);
        actual = service.divisionRemainder();
        System.out.println(actual);
        actual = service.divisionRemainder();
        System.out.println(actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"1", "2", "3", "4", "10"})
    void testGenerateNumber(int digit) {
        var service = new QuestionService();

        var actual = service.generateNumber(digit);
        assertEquals(digit, actual.length());
        System.out.println(actual);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "  1,  1, false",
        " 10,  5,  true",
        " 15,  5, false",
        " 20,  5,  true",
        "100,  1,  true", // 繰り下がり＆繰り下がりの場合もOK
        "121, 31,  true", // 二桁目だけが繰り下がりの場合でもOK
        "  3,  4,  true" // 結果がマイナスの場合はOKになる
    })
    void testHasBorrow(String x1, String x2, boolean expected) {
        var service = new QuestionService();

        var actual = service.hasBorrow(x1, x2);
        assertEquals(expected, actual);
    }
}

package net.shibadog.mainecoon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
    }

    @Test
    void testMultiplication() {
        var service = new QuestionService();

        var actual = service.multiplication();

        System.out.println(actual);
    }

    @Test
    void testDivisionRemainder() {
        var service = new QuestionService();

        var actual = service.divisionRemainder();

        System.out.println(actual);
    }
}

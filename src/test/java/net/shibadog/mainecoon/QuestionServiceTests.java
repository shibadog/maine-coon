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
    }
}

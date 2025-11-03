package org.iut;
import org.iut.refactoring.GestionApp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GestionAppTest {

    @Test
    void testMainExecution() {
        assertDoesNotThrow(() -> GestionApp.main(new String[]{}));
    }
}

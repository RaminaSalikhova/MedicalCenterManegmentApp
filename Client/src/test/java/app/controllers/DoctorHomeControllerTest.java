package app.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorHomeControllerTest {

    @Test
    void percentCount() {
        int doneCounter=2;
        int initialCount=4;

        int actual=0;
        if (doneCounter == 0) {
            actual = 100;
        } else {
            if (initialCount != 0) {
                actual = doneCounter * 100 / initialCount;
            } else {
                actual = 100;
            }
        }

        int expected=50;
        Assertions.assertEquals(expected, actual);
    }
}
package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    @DisplayName("Validate a floating point number")
    void validateFloat() throws InterruptedException
    {
        // simulate input
        String simulatedInput = "12";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        float result = App.validateFloat("Price of set: ");

        // run test
        assertEquals(12.0f, result);
    }

    @Test
    @DisplayName("Validate an integer")
    void validateInt() throws InterruptedException
    {
        // simulate input
        String simulatedInput = "12";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        float result = App.validateInt("Set ID: ");

        // run test
        assertEquals(12, result);
    }
}

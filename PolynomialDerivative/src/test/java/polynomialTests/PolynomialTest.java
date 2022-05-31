package polynomialTests;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.polsl.polynomialderivative.model.Polynomial;
import pl.polsl.polynomialderivative.exceptions.NotEnoughDataInArray;

/**
 * Class for testing the Polynomial class
 *
 * @author Bartosz Matusik
 */
public class PolynomialTest {

    private Polynomial polynomial = new Polynomial(0);

    /**
     * Mathod for testing the derivative method of the Polynomial class
     */
    @Test
    public void testDerivative() {
        //test with correct degrees and sizes of lists containing positive numbers, negative numbers and zero
        int degree = 5;
        polynomial.setDegree(degree);
        ArrayList<Double> factors = new ArrayList<>(List.of(6.0, -2.0, 3.0, 0.0, 2.0, 6.0));
        polynomial.setFactors(factors);
        int derivedDegree = 4;
        ArrayList<Double> derivedFactors = new ArrayList<>(List.of(30.0, -8.0, 9.0, 0.0, 2.0));

        try {
            assertEquals(derivedDegree, polynomial.derivative().getDegree());
            assertIterableEquals(derivedFactors, polynomial.derivative().getFactors());
        } catch (NotEnoughDataInArray e) {
            fail("Test failed: " + e);
        }
        //test with wrong sizes of lists
        factors.clear();
        polynomial.setFactors(factors);

        try {
            polynomial.derivative();
            fail("An exception should be thrown when there are not enough factors");
        } catch (NotEnoughDataInArray e) {
        }
        //test with polynomial of 0 degree
        polynomial.setDegree(0);
        derivedFactors.clear();
        derivedFactors.add(0.0);
        derivedDegree = 0;

        try {
            assertEquals(derivedDegree, polynomial.derivative().getDegree());
            assertIterableEquals(derivedFactors, polynomial.derivative().getFactors());
        } catch (NotEnoughDataInArray e) {
            fail("Test failed: " + e);
        }

    }
}

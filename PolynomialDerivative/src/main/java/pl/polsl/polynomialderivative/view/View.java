package pl.polsl.polynomialderivative.view;

import java.util.ArrayList;
import pl.polsl.polynomialderivative.exceptions.NotEnoughDataInArray;
import java.util.Scanner;
import java.util.InputMismatchException;
import pl.polsl.polynomialderivative.model.Polynomial;

/**
 * View(MVC pattern) responsible for comunication with user
 *
 * @author Bartosz Matusik
 * @version 1.0
 */
public class View {

    /**
     * Prints polynomial
     *
     * @param polynomial
     * @throws NotEnoughDataInArray - factors array is missing zeroes as factors
     * for Xes with the smallest exponents
     */
    public void printPolynomial(Polynomial polynomial) throws NotEnoughDataInArray {
        ArrayList<Double> factors = polynomial.getFactors();
        if (factors.size() < polynomial.getDegree() + 1) {
            throw new NotEnoughDataInArray("No data for Xes with the smallest exponents in factors array");
        } else {
            if (polynomial.getDegree() == 0) {
                System.out.println("polynomial: ");
                System.out.println(polynomial.getFactors().get(0));
            } else {
                System.out.println("polynomial: ");
                for (int i = 0; i < factors.size(); i++) {

                    if (i != factors.size() - 1) {
                        if (factors.get(i) != 0) {
                            System.out.print(((i == 0) ? " " : ((factors.get(i) < 0)) ? " " : " +") + factors.get(i) + "*x^" + (factors.size() - i - 1));
                        }
                    } else {
                        if (factors.get(i) != 0) {
                            System.out.print(((factors.get(i) < 0) ? " " : " +") + factors.get(i));
                        }
                    }

                }
            }
        }
    }

    /**
     * Prints degree input request
     */
    public void printDegreeInput() {
        System.out.println("Enter the polynomial degree: ");
    }

    /**
     * Prints factor input request
     *
     * @param exponent - a quantity representing the power to which X is to be
     * raised
     */
    public void printFactorInput(int exponent) {
        System.out.println("Enter factor for x^" + exponent);
    }
}

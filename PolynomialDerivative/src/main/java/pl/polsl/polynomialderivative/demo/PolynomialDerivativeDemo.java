package pl.polsl.polynomialderivative.demo;

import pl.polsl.polynomialderivative.model.Polynomial;
import pl.polsl.polynomialderivative.view.View;
import pl.polsl.polynomialderivative.view.ViewGUI;
import pl.polsl.polynomialderivative.controller.Controller;

/**
 * Main class of the aplication realizing the operation of derivative of
 * polynomian
 *
 * @author Bartosz Matusik
 * @version 1.0
 */
public class PolynomialDerivativeDemo {

    /**
     * Main method of application
     *
     * @param arg first arg - degree, from second to n - factors
     */
    public static void main(String[] arg) {
        Polynomial polynomial = new Polynomial(0);
        View view = new View();
        ViewGUI viewGUI = new ViewGUI();
        Controller controller = new Controller(polynomial,viewGUI);
        controller.runGUI();
       // controller.dataInput(arg);
       // controller.display();
       // controller.derivePolynomial();
       // System.out.print("derived ");
       //controller.display();

    }

}

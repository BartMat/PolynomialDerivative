package pl.polsl.polynomialderivative.controller;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import pl.polsl.polynomialderivative.exceptions.NegatvieNumber;
import pl.polsl.polynomialderivative.exceptions.NotEnoughDataInArray;
import pl.polsl.polynomialderivative.model.Polynomial;
import pl.polsl.polynomialderivative.view.View;
import pl.polsl.polynomialderivative.view.ViewGUI;

/**
 * Controller(MVC pattern) controls data flow
 *
 * @author Bartosz Matusik
 * @version 1.0
 */
public class Controller {

    /**
     * model class object
     */
    private Polynomial polynomial;
    /**
     * view class object
     */
    private View view;
    private ViewGUI viewGUI;

    /**
     * Constructor with two parameters for console view
     *
     * @param polynomial - model class object
     * @param view - view class object
     */
    public Controller(Polynomial polynomial, View view) {
        this.polynomial = polynomial;
        this.view = view;
    }

    /**
     * Constructor with two parameters for GUI view
     *
     * @param polynomial - model class object
     * @param viewGUI - view class object
     */
    public Controller(Polynomial polynomial, ViewGUI viewGUI) {
        this.polynomial = polynomial;
        this.viewGUI = viewGUI;
    }

    /**
     * 
     * @param controller
     */
    public Controller(Controller controller) {
        this.polynomial = controller.polynomial;
        this.viewGUI = controller.viewGUI;
    }
    
    
    /**
     * Sets degree of polynomial
     *
     * @param degree - degree of polynomial
     */
    public void setPolynomialDegree(int degree) {
        polynomial.setDegree(degree);
    }

    /**
     * Returns degree of polynomial
     *
     * @return degree of polynomial
     */
    public int getPolynomialDegree() {
        return polynomial.getDegree();
    }

    /**
     * Sets factors of polynomial
     *
     * @param factors - array of factors of polynomial
     */
    public void setPolynomialArray(ArrayList<Double> factors) {
        polynomial.setFactors(factors);
    }

    /**
     * Returns factors of polynomial
     *
     * @return array of factors of polynomial
     */
    public ArrayList<Double> getPolynomialArray() {
        return polynomial.getFactors();
    }

    /**
     * Prints polynomial
     */
    public void display() {
        try {
            view.printPolynomial(polynomial);
        } //zeroes as factors for Xes with lowest ecponents not in the array
        catch (NotEnoughDataInArray e) {

            handleMissingFactors(e);
            display();
        }
    }

    /**
     * Calculates derivative of polynomial
     *
     * @return derived polynomial
     */
    public Polynomial derivePolynomial() {
        Polynomial derivedPolynomial;
        try {
            derivedPolynomial = polynomial.derivative();
        } //zeroes as factors for Xes with lowest ecponents not in the array
        catch (NotEnoughDataInArray e) {
            handleMissingFactors(e);
            derivedPolynomial = derivePolynomial();
        }
        setPolynomialDegree(derivedPolynomial.getDegree());
        setPolynomialArray(derivedPolynomial.getFactors());
        return derivedPolynomial;
    }

    /**
     * Fixing factors array with missing zeroes as factors for Xes with the
     * smallest exponents
     *
     * @param e - error message
     */
    private void handleMissingFactors(NotEnoughDataInArray e) {
        System.out.println(e);
        ArrayList<Double> tempArray = new ArrayList<>(polynomial.getFactors()); //creating new array 

        for (int i = polynomial.getFactors().size(); i < polynomial.getDegree() + 1; i++) {
            tempArray.add(0.0);//filling rest of the array with zeroes
        }

        polynomial.setFactors(tempArray);//setting fixed array
        System.out.println("added zeroes as factors for Xes with the smallest exponents in factors array");
    }

    /**
     * Prints degree input request
     */
    public void viewPrintDegreeInput() {
        view.printDegreeInput();
    }

    /**
     * Asks user for input and reads value of degree of polynomial from user
     * input
     *
     * @return read value of degree of polynomial
     */
    public int degreeInput() {
        Scanner reader = new Scanner(System.in);
        int degree;
        try {
            viewPrintDegreeInput();
            degree = reader.nextInt();
            if (degree < 0) {
                throw new NegatvieNumber("degree cannot be negative");
            }
        } //zeroes as factors for Xes with lowest ecponents not in the array
        catch (InputMismatchException e) {
            //System.out.println(e);
            degree = degreeInput();
        } catch (NegatvieNumber e) {
            System.out.println(e);
            degree = degreeInput();
        }
        return degree;
        // return view.inputDegree();
    }

    /**
     * Asks user for input and reads values of factors of polynomial from user
     * input
     *
     * @param size - number of factors
     * @return read values of factors of polynomial
     */
    public ArrayList<Double> factorsInput(int size) {
        ArrayList<Double> factors = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            factors.add(readDouble(size - i - 1));
        }
        return factors;
    }

    /**
     * Prints factor input request
     *
     * @param exponent - a quantity representing the power to which X is to be
     * raised
     */
    public void viewPrintFactorInput(int exponent) {
        view.printFactorInput(exponent);
    }

    /**
     * Asks user for input and reads double
     *
     * @param exponent
     * @return read double
     */
    private double readDouble(int exponent) {
        try {
            Scanner reader = new Scanner(System.in);
            viewPrintFactorInput(exponent);
            return reader.nextDouble();
        } //zeroes as factors for Xes with lowest ecponents not in the array
        catch (InputMismatchException e) {
            return readDouble(exponent);
        }

    }

    /**
     * Tries to read data from parameters if it fails reads user input
     *
     * @param arg - string array of main method parameters
     */
    public void dataInput(String[] arg) {
        if (arg.length != 0) //checking if there are any arguments 
        {
            try {
                int degree = Integer.parseInt(arg[0]); //parsing number of degree from argument

                if (arg.length - 1 > degree + 1) //checking if provided number of factors is valid 
                {

                    ArrayList<Double> factors = new ArrayList<>(factorsInput(degree + 1)); //using user input to acquire factors
                    setPolynomialDegree(degree);
                    setPolynomialArray(factors);
                } else {
                    //parsing factors from arguments
                    ArrayList<Double> factors = new ArrayList<>();
                    for (int i = 0; i < arg.length - 1; i++) {
                        factors.add(Double.parseDouble(arg[i + 1]));
                    }
                    setPolynomialDegree(degree);
                    setPolynomialArray(factors);
                }
            } catch (NumberFormatException e) // error in the conversion, which means not being provided with degree or factors or both 
            {
                //using user input to acquire necessary data
                //System.out.println(e);
                int degree = degreeInput();
                ArrayList<Double> factors = new ArrayList<>(factorsInput(degree + 1));
                setPolynomialDegree(degree);
                setPolynomialArray(factors);
            }
        } else//no arguments using user input to acquire necessary data
        {
            int degree = degreeInput();
            ArrayList<Double> factors = new ArrayList<>(factorsInput(degree + 1));
            setPolynomialDegree(degree);
            setPolynomialArray(factors);
        }

    }
    
    class calculateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            
            int degree = viewGUI.getDegree();
            ArrayList<Double> factors = new ArrayList<>(viewGUI.getFactors());
            setPolynomialArray(factors);
            setPolynomialDegree(degree);
            ArrayList<Double> derivedFactors = new ArrayList<>(derivePolynomial().getFactors());
            int derivedDegree = polynomial.getDegree();
            String result = "";

            if (derivedDegree == 0 || derivedFactors.stream().filter(f -> f != 0.0).count() == 0) {
                result = "0.0";
            } else {
                for (int i = 0; i < derivedFactors.size(); i++) {

                    if (i != derivedFactors.size() - 1) {
                        if (derivedFactors.get(i) != 0) {
                            result += (((i == 0) ? " " : ((derivedFactors.get(i) < 0)) ? " " : " +") + derivedFactors.get(i) + "*x^" + (derivedFactors.size() - i - 1));
                        }
                    } else {
                        if (derivedFactors.get(i) != 0) {
                            result += (((derivedFactors.get(i) < 0) ? " " : " +") + derivedFactors.get(i));
                        }
                    }

                }
            }

            viewGUI.setResultTxt(result);
        
        }
    }

    /**
     *Starts GUI
     */
    public void runGUI()
    {
        viewGUI.addCalculateListener(new calculateListener());
        viewGUI.setVisible(true);
        
    }
    
    
   
}



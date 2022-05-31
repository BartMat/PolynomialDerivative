package pl.polsl.polynomialderivative.exceptions;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Custom exception class, used in catching factor arrays with missing zeroes as
 * factors for Xes with the smallest exponents
 *
 * @author Bartosz Matusik
 */
public class NotEnoughDataInArray extends Exception {

    /**
     * Constructor
     *
     * @param errorMessage
     */
    public NotEnoughDataInArray(String errorMessage) {
        super(errorMessage);
    }

}

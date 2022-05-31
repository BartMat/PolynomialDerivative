/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.polynomialderivative.exceptions;

/**
 * Custom exception class, used in catching negative numbers as polynomial degree
 * @author Bartosz Matusik
 */
public class NegatvieNumber extends Exception{
     /**
     * Constructor
     * @param errorMessage
     */
    public NegatvieNumber(String errorMessage)
    {
        super(errorMessage);
    }
    
}

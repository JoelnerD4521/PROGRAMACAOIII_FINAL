/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.DecimalFormat;
/**
 *
 * @author denez
 */
public class Formatacao {
    
     private static final DecimalFormat FORMATADOR = new DecimalFormat("#.###");

    public static String formatarDecimal(double numero) {
        return FORMATADOR.format(numero);
    }
    
}

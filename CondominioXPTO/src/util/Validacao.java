/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author denez
 */
public class Validacao {
    
      public static boolean validarTexto(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean validarNumero(double numero, double minimo, double maximo) {
        return numero >= minimo && numero <= maximo;
    }

    public static boolean validarPercentagem(double percentagem) {
        return validarNumero(percentagem, 0, 100);
    }
}

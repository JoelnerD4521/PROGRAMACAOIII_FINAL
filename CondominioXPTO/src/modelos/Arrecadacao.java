/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author denez
 */
public class Arrecadacao extends Fracao {
    
     private boolean temPortaBlindada;

    public Arrecadacao(String identificador, double area, double percentagem, String localizacao, boolean temPortaBlindada, Proprietario proprietario) {
        super(identificador, area, percentagem, localizacao, proprietario);
        this.temPortaBlindada = temPortaBlindada;
    }

    public boolean isTemPortaBlindada() {
        return temPortaBlindada;
    }

    public void setTemPortaBlindada(boolean temPortaBlindada) {
        this.temPortaBlindada = temPortaBlindada;
    }

    @Override
    public double calcularQuotaMensal(double despesaGeral, double despesaElevadores) {
        return (getPercentagem() / 100) * (despesaGeral + despesaElevadores);
    }
    
}

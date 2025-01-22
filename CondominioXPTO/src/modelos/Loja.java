/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author denez
 */
public class Loja extends Fracao {
  
      public Loja(String identificador, double area, double percentagem, String localizacao, Proprietario proprietario) {
        super(identificador, area, percentagem, localizacao, proprietario);
    }
    @Override
    public double calcularQuotaMensal(double despesaGeral, double despesaElevadores) {
        return (getPercentagem() / 100) * despesaGeral; // Não participa nas despesas de elevadores
    }
    
}

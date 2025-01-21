/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;
import java.io.Serializable;


/**
 *
 * @author denez
 */
public abstract class Fracao implements Serializable {
    
  private String identificador;
    private double area;
    private double percentagem;
    private String localizacao;

    public Fracao(String identificador, double area, double percentagem, String localizacao) {
        this.identificador = identificador;
        this.area = area;
        this.percentagem = percentagem;
        this.localizacao = localizacao;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPercentagem() {
        return percentagem;
    }

    public void setPercentagem(double percentagem) {
        this.percentagem = percentagem;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public abstract double calcularQuotaMensal(double despesaGeral, double despesaElevadores);
    
}
    


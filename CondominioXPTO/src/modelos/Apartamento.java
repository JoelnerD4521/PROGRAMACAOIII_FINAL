/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author denez
 */
public class Apartamento extends Fracao {
   
    private String tipo;
    private int numeroCasasBanho;
    private int numeroVarandas;
    private boolean temTerraco;

     public Apartamento(String identificador, double area, double percentagem, String localizacao,
                       String tipo, int numeroCasasBanho, int numeroVarandas, boolean temTerraco, Proprietario proprietario) {
        super(identificador, area, percentagem, localizacao, proprietario);
        this.tipo = tipo;
        this.numeroCasasBanho = numeroCasasBanho;
        this.numeroVarandas = numeroVarandas;
        this.temTerraco = temTerraco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumeroCasasBanho() {
        return numeroCasasBanho;
    }

    public void setNumeroCasasBanho(int numeroCasasBanho) {
        this.numeroCasasBanho = numeroCasasBanho;
    }

    public int getNumeroVarandas() {
        return numeroVarandas;
    }

    public void setNumeroVarandas(int numeroVarandas) {
        this.numeroVarandas = numeroVarandas;
    }

    public boolean isTemTerraco() {
        return temTerraco;
    }

    public void setTemTerraco(boolean temTerraco) {
        this.temTerraco = temTerraco;
    }

    @Override
    public double calcularQuotaMensal(double despesaGeral, double despesaElevadores) {
        return (getPercentagem() / 100) * (despesaGeral + despesaElevadores);
    }
}

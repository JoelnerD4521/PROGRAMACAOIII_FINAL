/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author denez
 */
public class Garagem extends Fracao {
    
    private int capacidadeViaturas;
    private boolean temLavagem;

   public Garagem(String identificador, double area, double percentagem, String localizacao,
                   int capacidadeViaturas, boolean temLavagem, Proprietario proprietario) {
        super(identificador, area, percentagem, localizacao, proprietario);
        this.capacidadeViaturas = capacidadeViaturas;
        this.temLavagem = temLavagem;
    }

    public int getCapacidadeViaturas() {
        return capacidadeViaturas;
    }

    public void setCapacidadeViaturas(int capacidadeViaturas) {
        this.capacidadeViaturas = capacidadeViaturas;
    }

    public boolean isTemLavagem() {
        return temLavagem;
    }

    public void setTemLavagem(boolean temLavagem) {
        this.temLavagem = temLavagem;
    }

    @Override
    public double calcularQuotaMensal(double despesaGeral, double despesaElevadores) {
        return (getPercentagem() / 100) * (despesaGeral + despesaElevadores);
    }
}

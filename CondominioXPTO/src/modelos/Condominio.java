/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author denez
 */
public class Condominio {
    
    private String identificador;
    private String morada;
    private double despesaGeral;
    private double despesaElevadores;
    private List<Fracao> fracoes;

    public Condominio(String identificador, String morada, double despesaGeral, double despesaElevadores) {
        this.identificador = identificador;
        this.morada = morada;
        this.despesaGeral = despesaGeral;
        this.despesaElevadores = despesaElevadores;
        this.fracoes = new ArrayList<>();
    }

    public void adicionarFracao(Fracao fracao) {
        fracoes.add(fracao);
    }

    public void removerFracao(String identificador) {
        fracoes.removeIf(f -> f.getIdentificador().equals(identificador));
    }

    public double calcularSomaQuotasMensais() {
        return fracoes.stream()
                .mapToDouble(f -> f.calcularQuotaMensal(despesaGeral, despesaElevadores))
                .sum();
    }

    public boolean verificarPercentagens() {
        double somaPercentagens = fracoes.stream().mapToDouble(Fracao::getPercentagem).sum();
        return Math.abs(somaPercentagens - 100.0) < 0.001; // Tolerância para precisão
    }

    public List<Fracao> listarFracoes() {
        return fracoes;
    }
    
    public void recalcularPercentagens() {
    double areaTotal = fracoes.stream().mapToDouble(Fracao::getArea).sum();
    for (Fracao fracao : fracoes) {
        fracao.setPercentagem((fracao.getArea() / areaTotal) * 100);
    }
}
}

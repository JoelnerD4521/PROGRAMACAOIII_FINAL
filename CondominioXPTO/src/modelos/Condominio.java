package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author denez
 */
public class Condominio {
    
    private int identificador;
    private String nome;
    private String endereco;
    private double areaTotal;
    private double areaDisponivel;
    private double despesaGeral;
    private double despesaElevadores;
    private LocalDate dataConstrucao;
    private List<Fracao> fracoes;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Condominio(int identificador, String nome, String endereco, double areaTotal, double despesaGeral, double despesaElevadores, LocalDate dataConstrucao) {
        this.identificador = identificador;
        this.endereco = endereco;
        this.areaTotal = areaTotal;
        this.areaDisponivel = areaTotal;
        this.despesaGeral = despesaGeral;
        this.despesaElevadores = despesaElevadores;
        this.dataConstrucao = dataConstrucao;
        this.fracoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getMorada() {
        return endereco;
    }

    public void setMorada(String morada) {
        this.endereco = morada;
    }

    public double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public double getAreaDisponivel() {
        return areaDisponivel;
    }

    public void setAreaDisponivel(double areaDisponivel) {
        this.areaDisponivel = areaDisponivel;
    }

    public double getDespesaGeral() {
        return despesaGeral;
    }

    public void setDespesaGeral(double despesaGeral) {
        this.despesaGeral = despesaGeral;
    }

    public double getDespesaElevadores() {
        return despesaElevadores;
    }

    public void setDespesaElevadores(double despesaElevadores) {
        this.despesaElevadores = despesaElevadores;
    }

    public List<Fracao> getFracoes() {
        return fracoes;
    }

    public void setFracoes(List<Fracao> fracoes) {
        this.fracoes = fracoes;
    }
    

    public boolean adicionarFracao(Fracao fracao) {
        if (fracao.getArea() > areaDisponivel) {
            System.out.println("Erro: A área disponível do condomínio é insuficiente!");
            return false;
        }
        fracoes.add(fracao);
        this.areaDisponivel -= fracao.getArea();
        recalcularPercentagens();
        return true;
    }

    public boolean removerFracao(String identificador) {
        for (Fracao fracao : fracoes) {
            if (fracao.getIdentificador().equals(identificador)) {
                this.areaDisponivel += fracao.getArea();
                fracoes.remove(fracao);
                recalcularPercentagens();
                return true;
            }
        }
        System.out.println("Erro: Fração não encontrada.");
        return false;
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
        double areaTotalAtual = fracoes.stream().mapToDouble(Fracao::getArea).sum();
        for (Fracao fracao : fracoes) {
            fracao.setPercentagem((fracao.getArea() / areaTotal) * 100);
        }
    }
    
    public void mostrarInformacoes() {
        System.out.println("\n--- Informações do Condomínio ---");
        System.out.println("ID: " + identificador);
        System.out.println("Nome: " + nome);
        System.out.println("Localização: " + endereco);
        System.out.println("Área Total: " + areaTotal + " m²");
        System.out.println("Área Disponível: " + areaDisponivel + " m²");
        System.out.println("Data de Construção: " + dataConstrucao.format(FORMATTER));
        System.out.println("Total de Despesas Gerais: " + despesaGeral);
        System.out.println("Total de Despesas com Elevadores: " + despesaElevadores);
        System.out.println("Número de Frações: " + fracoes.size());
    }
    
    public LocalDate getDataConstrucao() {
        return dataConstrucao;
    }
}

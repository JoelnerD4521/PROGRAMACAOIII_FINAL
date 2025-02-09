/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicos;

import java.io.*;
import java.util.List;
import modelos.Fracao;
import modelos.Condominio;
import modelos.Apartamento;
import modelos.Arrecadacao;
import modelos.Garagem;
import modelos.Loja;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



/**
 *
 * @author denez
 */
public class GestorFicheiros {
    private static final String FICHEIRO_FRACOES = "src/ficheiros/fracoes.txt";
    private static final String FICHEIRO_CONDOMINIO = "src/ficheiros/condominio.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
 // Salvar dados do condomínio
    public static void salvarCondominio(Condominio condominio) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_CONDOMINIO))) {
            writer.write(condominio.getNome() + "\n");
            writer.write(condominio.getEndereco() + "\n");
            writer.write(condominio.getDespesaGeral() + "\n");
            writer.write(condominio.getDespesaElevadores() + "\n");
            System.out.println("Condominio salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar condominio: " + e.getMessage());
        }
    }

    // Carregar dados do condomínio
   public static Condominio carregarCondominio() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_CONDOMINIO))) {
            int identificador = Integer.parseInt(  reader.readLine());
            String nome = reader.readLine();
            String endereco = reader.readLine();
            double areaTotal = Double.parseDouble(reader.readLine());
            double despesasGerais = Double.parseDouble(reader.readLine());
            double despesasElevadores = Double.parseDouble(reader.readLine());
            LocalDate dataConstrucao = LocalDate.parse(reader.readLine(), FORMATTER);

            return new Condominio(identificador, nome, endereco, areaTotal, despesasGerais, despesasElevadores, dataConstrucao);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar condominio, iniciando novo: " + e.getMessage());
            return new Condominio( 1, "Condominio XPTO", "Talatona", 10000.00, 100000.00, 300000.00, LocalDate.of(2020, 1, 31));
        }
    }
    
    public static void salvarFracoes(List<Fracao> fracoes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_FRACOES))) {
            for (Fracao f : fracoes) {
                if (f instanceof Apartamento a) {
                    writer.write("Apartamento;" + f.getIdentificador() + ";" + f.getArea() + ";" + f.getPercentagem() +
                            ";" + f.getLocalizacao() + ";" + a.getTipo() + ";" + a.getNumeroCasasBanho() +
                            ";" + a.getNumeroVarandas()+ ";" + a.isTemTerraco()+ "\n");
                } else if (f instanceof Loja) {
                    writer.write("Loja;" + f.getIdentificador() + ";" + f.getArea() + ";" + f.getPercentagem() + 
                            ";" + f.getLocalizacao() + "\n");
                } else if (f instanceof Garagem g) {
                    writer.write("Garagem;" + f.getIdentificador() + ";" + f.getArea() + ";" + f.getPercentagem() +
                            ";" + f.getLocalizacao() + ";" + g.getCapacidadeViaturas() + ";" + g.isTemLavagem() + "\n");
                } else if (f instanceof Arrecadacao a) {
                    writer.write("Arrecadacao;" + f.getIdentificador() + ";" + f.getArea() + ";" + f.getPercentagem() +
                            ";" + f.getLocalizacao() + ";" + a.isTemPortaBlindada() + "\n");
                }
            }
            System.out.println("Frações salvas com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar frações: " + e.getMessage());
        }
    }


    public static void carregarFracoes(Condominio condominio) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_FRACOES))) {
            String linha;
            while((linha = reader.readLine()) != null){
             String[] dados = linha.split(";");
             String tipo = dados[0];
             String id = dados[2];
             double area = Double.parseDouble(dados[2]);
             double percentagem = Double.parseDouble(dados[3]);
             String localizacao = dados[4];
             
            Fracao fracao = null;

            switch (tipo) {
                case "Apartamento" -> {
                    String tipoApartamento = dados[5];
                    int casasBanho = Integer.parseInt(dados[6]);
                    int varandas = Integer.parseInt(dados[7]);
                    boolean terraco = Boolean.parseBoolean(dados[8]);
                    fracao = new Apartamento(id, area, percentagem, localizacao, tipoApartamento, casasBanho, varandas, terraco, null);
                }
                case "Loja" -> fracao = new Loja(id, area, percentagem, localizacao, null);
                case "Garagem" -> {
                    int capacidade = Integer.parseInt(dados[5]);
                    boolean lavagem = Boolean.parseBoolean(dados[6]);
                    fracao = new Garagem(id, area, percentagem, localizacao, capacidade, lavagem, null);
                }
                case "Arrecadacao" -> {
                    boolean portaBlindada = Boolean.parseBoolean(dados[5]);
                    fracao = new Arrecadacao(id, area, percentagem, localizacao, portaBlindada, null);
                }
            }

            if (fracao != null) {
                condominio.adicionarFracao(fracao);
            }
        }
        System.out.println("Frações carregadas com sucesso!");
    } catch (IOException | NumberFormatException e) {
        System.out.println("Erro ao carregar frações: " + e.getMessage());
    }
    }
    
   }


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacao;

import modelos.*;
import util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 *
 * @author denez
 */
public class MenuCondominio {
    
private static Condominio condominio; // Instância global do condomínio
    private static final String CAMINHO_FICHEIRO = "src/ficheiros/condominio.dat"; // Caminho para salvar os dados

    public static void main(String[] args) {
        inicializarCondominio();
        exibirMenu();
        salvarDados(); // Salva os dados ao encerrar o programa
    }

    // Inicializa os dados do condomínio (carrega de ficheiro ou cria um novo)
    private static void inicializarCondominio() {
        List<Fracao> fracoes = Ficheiros.carregarDados(CAMINHO_FICHEIRO);
        if (fracoes == null) {
            condominio = new Condominio("XPTO", "Rua Principal, Luanda", 0, 0);
            System.out.println("Condominio criado com sucesso!");
        } else {
            condominio = new Condominio("XPTO", "Rua Principal, Luanda", 0, 0);
            fracoes.forEach(condominio::adicionarFracao);
            System.out.println("Dados do condomínio carregados com sucesso!");
        }
    }

    // Exibe o menu principal e gerencia as opções do usuário
    private static void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n========== Gestao do Condominio XPTO ==========");
            System.out.println("1. Adicionar Fracao");
            System.out.println("2. Listar Fracoes");
            System.out.println("3. Calcular Quotas Mensais");
            System.out.println("4. Verificar Percentagens");
            System.out.println("5. Atualizar Despesas do Condominio");
            System.out.println("6. Salvar e Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> adicionarFracao(scanner);
                case 2 -> listarFracoes();
                case 3 -> calcularQuotasMensais();
                case 4 -> verificarPercentagens();
                case 5 -> atualizarDespesas(scanner);
                case 6 -> System.out.println("Saindo... Dados salvos!");
                default -> System.out.println("Opcao invalida! Tente novamente.");
            }
        } while (opcao != 6);
    }

    // Adiciona uma nova fração ao condomínio
    private static void adicionarFracao(Scanner scanner) {
        System.out.println("\n--- Adicionar Fracao ---");
        System.out.print("Tipo (1-Apartamento, 2-Loja, 3-Garagem, 4-Arrecadacao): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Identificador: ");
        String identificador = scanner.nextLine();

        System.out.print("Area: ");
        double area = scanner.nextDouble();

        System.out.print("Percentagem: ");
        double percentagem = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Localizacao: ");
        String localizacao = scanner.nextLine();

        if (!Validacao.validarTexto(identificador) || !Validacao.validarPercentagem(percentagem)) {
            System.out.println("Dados inválidos! Fracao nao adicionada.");
            return;
        }

        Fracao novaFracao = switch (tipo) {
            case 1 -> {
                System.out.print("Tipo de Apartamento (T0-T5): ");
                String tipoApartamento = scanner.nextLine();
                System.out.print("Numero de Casas de Banho: ");
                int casasBanho = scanner.nextInt();
                System.out.print("Numero de Varandas: ");
                int varandas = scanner.nextInt();
                System.out.print("Tem terraço? (true/false): ");
                boolean terraço = scanner.nextBoolean();
                yield new Apartamento(identificador, area, percentagem, localizacao, tipoApartamento, casasBanho, varandas, terraço);
            }
            case 2 -> new Loja(identificador, area, percentagem, localizacao);
            case 3 -> {
                System.out.print("Capacidade de viaturas: ");
                int capacidade = scanner.nextInt();
                System.out.print("Tem lavagem? (true/false): ");
                boolean lavagem = scanner.nextBoolean();
                yield new Garagem(identificador, area, percentagem, localizacao, capacidade, lavagem);
            }
            case 4 -> {
                System.out.print("Tem porta blindada? (true/false): ");
                boolean portaBlindada = scanner.nextBoolean();
                yield new Arrecadacao(identificador, area, percentagem, localizacao, portaBlindada);
            }
            default -> null;
        };

        if (novaFracao != null) {
            condominio.adicionarFracao(novaFracao);
            condominio.recalcularPercentagens();
            System.out.println("Fracao adicionada com sucesso!");
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    // Lista todas as frações do condomínio
    private static void listarFracoes() {
        System.out.println("\n--- Listar Fracoes ---");
        List<Fracao> fracoes = condominio.listarFracoes();
        if (fracoes.isEmpty()) {
            System.out.println("Nenhuma fracao registrada.");
        } else {
            fracoes.forEach(System.out::println);
        }
    }

    // Calcula as quotas mensais
    private static void calcularQuotasMensais() {
        double totalQuotas = condominio.calcularSomaQuotasMensais();
        System.out.println("\n--- Quotas Mensais ---");
        System.out.println("Soma das Quotas Mensais: " + Formatacao.formatarDecimal(totalQuotas) + " Kz");
    }

    // Verifica se as percentagens somam 100%
    private static void verificarPercentagens() {
        boolean validas = condominio.verificarPercentagens();
        System.out.println("\n--- Verificar Percentagens ---");
        System.out.println("As percentagens estao corretas? " + (validas ? "Sim" : "Não"));
    }

    // Atualiza as despesas gerais e dos elevadores
    private static void atualizarDespesas(Scanner scanner) {
        System.out.print("\n--- Atualizar Despesas ---\nDespesa Geral: ");
        double despesaGeral = scanner.nextDouble();
        System.out.print("Despesa com Elevadores: ");
        double despesaElevadores = scanner.nextDouble();

        condominio.recalcularPercentagens(); // Recalcula com novos valores
        System.out.println("Despesas atualizadas com sucesso!");
    }

    // Salva os dados do condomínio em um ficheiro
    private static void salvarDados() {
        Ficheiros.salvarDados(CAMINHO_FICHEIRO, condominio.listarFracoes());
    }
}

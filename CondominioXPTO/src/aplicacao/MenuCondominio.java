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
    private static final String CAMINHO_FICHEIRO = "condominio.dat"; // Caminho para salvar os dados
    private static List<Proprietario> proprietarios = new ArrayList<>(); // Lista de proprietários registrados

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
            System.out.println("Condomínio criado com sucesso!");
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
            System.out.println("\n========== Gestão do Condomínio XPTO ==========");
            System.out.println("1. Adicionar Proprietário");
            System.out.println("2. Adicionar Fração");
            System.out.println("3. Listar Frações");
            System.out.println("4. Calcular Quotas Mensais");
            System.out.println("5. Verificar Percentagens");
            System.out.println("6. Atualizar Despesas do Condomínio");
            System.out.println("7. Salvar e Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> adicionarProprietario(scanner);
                case 2 -> adicionarFracao(scanner);
                case 3 -> listarFracoes();
                case 4 -> calcularQuotasMensais();
                case 5 -> verificarPercentagens();
                case 6 -> atualizarDespesas(scanner);
                case 7 -> System.out.println("Saindo... Dados salvos!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 7);
    }

    // Adiciona um novo proprietário
    private static void adicionarProprietario(Scanner scanner) {
        System.out.println("\n--- Adicionar Proprietário ---");
        System.out.print("Identificador: ");
        String identificador = scanner.next();

        System.out.print("Nome: ");
        String nome = scanner.next();

        System.out.print("Morada: ");
        String morada = scanner.next();

        System.out.print("Telefone: ");
        String telefone = scanner.next();

        System.out.print("Email: ");
        String email = scanner.next();

        System.out.print("Data de Nascimento (dd/mm/yyyy): ");
        String dataNascimento = scanner.next();

        Proprietario proprietario = new Proprietario(identificador, nome, morada, telefone, email, dataNascimento);
        proprietarios.add(proprietario);
        System.out.println("Proprietário adicionado com sucesso!");
    }

    // Adiciona uma nova fração ao condomínio
    private static void adicionarFracao(Scanner scanner) {
        System.out.println("\n--- Adicionar Fração ---");
        System.out.print("Tipo (1-Apartamento, 2-Loja, 3-Garagem, 4-Arrecadação): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Identificador: ");
        String identificador = scanner.nextLine();

        System.out.print("Área: ");
        double area = scanner.nextDouble();

        System.out.print("Percentagem: ");
        double percentagem = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Localização: ");
        String localizacao = scanner.nextLine();

        Proprietario proprietario = selecionarProprietario(scanner);

        if (!Validacao.validarTexto(identificador) || !Validacao.validarPercentagem(percentagem)) {
            System.out.println("Dados inválidos! Fração não adicionada.");
            return;
        }

        Fracao novaFracao = switch (tipo) {
            case 1 -> {
                System.out.print("Tipo de Apartamento (T0-T5): ");
                String tipoApartamento = scanner.nextLine();
                System.out.print("Número de Casas de Banho: ");
                int casasBanho = scanner.nextInt();
                System.out.print("Número de Varandas: ");
                int varandas = scanner.nextInt();
                System.out.print("Tem terraço? (true/false): ");
                boolean terraço = scanner.nextBoolean();
                yield new Apartamento(identificador, area, percentagem, localizacao, tipoApartamento, casasBanho, varandas, terraço, proprietario);
            }
            case 2 -> new Loja(identificador, area, percentagem, localizacao, proprietario);
            case 3 -> {
                System.out.print("Capacidade de viaturas: ");
                int capacidade = scanner.nextInt();
                System.out.print("Tem lavagem? (true/false): ");
                boolean lavagem = scanner.nextBoolean();
                yield new Garagem(identificador, area, percentagem, localizacao, capacidade, lavagem, proprietario);
            }
            case 4 -> {
                System.out.print("Tem porta blindada? (true/false): ");
                boolean portaBlindada = scanner.nextBoolean();
                yield new Arrecadacao(identificador, area, percentagem, localizacao, portaBlindada, proprietario);
            }
            default -> null;
        };

        if (novaFracao != null) {
            condominio.adicionarFracao(novaFracao);
            condominio.recalcularPercentagens();
            System.out.println("Fração adicionada com sucesso!");
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    // Lista todos os proprietários e permite selecionar um
    private static Proprietario selecionarProprietario(Scanner scanner) {
        System.out.println("\n--- Selecionar Proprietário ---");
        for (int i = 0; i < proprietarios.size(); i++) {
            System.out.println((i + 1) + ". " + proprietarios.get(i).getNome());
        }
        System.out.print("Escolha o número do proprietário: ");
        int escolha = scanner.nextInt();
        return proprietarios.get(escolha - 1);
    }

    // Lista todas as frações do condomínio
    private static void listarFracoes() {
        System.out.println("\n--- Listar Frações ---");
        List<Fracao> fracoes = condominio.listarFracoes();
        if (fracoes.isEmpty()) {
            System.out.println("Nenhuma fração registrada.");
        } else {
            fracoes.forEach(fracao -> {
                System.out.println(fracao);
                System.out.println("Proprietário: " + fracao.getProprietario());
            });
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
        System.out.println("As percentagens estão corretas? " + (validas ? "Sim" : "Não"));
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

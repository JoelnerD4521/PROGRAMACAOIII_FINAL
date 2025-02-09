 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacao;

import modelos.*;
import util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import servicos.GestorFicheiros;



/**
 *
 * @author denez
 */
public class MenuCondominio {
    
   private static Condominio condominio;
    private static List<Proprietario> proprietarios = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
      condominio = GestorFicheiros.carregarCondominio();
      GestorFicheiros.carregarFracoes(condominio);
        exibirMenu();
        
    }

    // Inicializa os dados do condomínio (carrega de ficheiro ou cria um novo)
    
    

    // Exibe o menu principal e gerencia as opções do usuário
    private static void exibirMenu() {
       try (Scanner scanner = new Scanner(System.in)) {
           int opcao = -1;
           do {
               try {
                   System.out.println("\n========== Gestao do Condominio XPTO ==========");
                   System.out.println("1. Adicionar Proprietario");
                   System.out.println("2. Adicionar Fracao");
                   System.out.println("3. Listar Fracoess");
                   System.out.println("4. Calcular Quotas Mensais");
                   System.out.println("5. Verificar Percentagens");
                   System.out.println("6. Atualizar Despesas do Condominio");
                   System.out.println("7. Salvar e Sair");
                   System.out.print("Escolha uma opcao: ");
                   opcao = scanner.nextInt();
               } catch (InputMismatchException e) {
                   System.out.println("Erro: Entrada invalida! Use apenas números para selecionar as opcoes.");
                   scanner.next();
                   continue;
               }
               
               switch (opcao) {
                   case 1 -> adicionarProprietario(scanner);
                   case 2 -> adicionarFracao(scanner);
                   case 3 -> listarFracoes();
                   case 4 -> calcularQuotasMensais();
                   case 5 -> verificarPercentagens(scanner);
                   case 6 -> atualizarDespesas(scanner);
                   case 7 -> {
                    GestorFicheiros.salvarCondominio(condominio);
                    GestorFicheiros.salvarFracoes(condominio.getFracoes());
                    System.out.println("Dados salvos. Saindo...");
                }
                   default -> System.out.println("Opcao invalida! Tente novamente.");
               }
           } while (opcao != 7);
       }
    }

    // Adiciona um novo proprietário
private static void adicionarProprietario(Scanner scanner) {
    System.out.println("\n--- Adicionar Proprietario ---");
    
    int identificador = 0;
    while (true) {
        System.out.print("Identificador (número inteiro): ");
        try {
            identificador = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            break;
        } catch (InputMismatchException e) {
            System.out.println("Erro: Digite um número inteiro válido!");
            scanner.nextLine(); // Limpa o buffer do scanner
        }
    }

    String nome;
    do {
        System.out.print("Nome: ");
        nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Erro: O nome não pode estar vazio.");
        }
    } while (nome.isEmpty());

    String morada;
    do {
        System.out.print("Morada: ");
        morada = scanner.nextLine().trim();
        if (morada.isEmpty()) {
            System.out.println("Erro: A morada não pode estar vazia.");
        }
    } while (morada.isEmpty());

    String telefone;
    do {
        System.out.print("Telefone: ");
        telefone = scanner.next().trim();
        if (!telefone.matches("\\d{9}")) {
            System.out.println("Erro: O telefone deve ter 9 dígitos numéricos.");
        }
    } while (!telefone.matches("\\d{9}"));

    String telemovel;
    do {
        System.out.print("Telemóvel: ");
        telemovel = scanner.next().trim();
        if (!telemovel.matches("\\d{9}")) {
            System.out.println("Erro: O telemóvel deve ter 9 dígitos numéricos.");
        }
    } while (!telemovel.matches("\\d{9}"));

    scanner.nextLine(); // Consumir a quebra de linha após next()

    String email;
    do {
        System.out.print("Email: ");
        email = scanner.nextLine().trim();
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.out.println("Erro: Email inválido. Tente novamente.");
        }
    } while (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$"));

        LocalDate dataNascimento = null;
        while (dataNascimento == null) {
            System.out.print("Data de Nascimento (dd/MM/yyyy): ");
            String dataInput = scanner.next();
            try {
                dataNascimento = LocalDate.parse(dataInput, FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Erro: Formato de data invalido! Use dd/MM/yyyy.");
            }
        }

        Proprietario proprietario = new Proprietario(identificador, nome, morada, telefone, telemovel, email, dataNascimento);
        proprietarios.add(proprietario);
        System.out.println("Proprietario adicionado com sucesso!");
    }


    // Adiciona uma nova fração ao condomínio
  private static void adicionarFracao(Scanner scanner) {
  System.out.println("\n--- Adicionar Fração ---");

    int tipo = 0;
    while (true) {
        System.out.print("Tipo (1-Apartamento, 2-Loja, 3-Garagem, 4-Arrecadação): ");
        try {
            tipo = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            if (tipo >= 1 && tipo <= 4) break;
            System.out.println("Erro: Escolha um tipo válido (1 a 4).");
        } catch (InputMismatchException e) {
            System.out.println("Erro: Insira um número válido.");
            scanner.nextLine(); // Limpa o buffer do scanner
        }
    }

    String identificador;
    do {
        System.out.print("Identificador: ");
        identificador = scanner.nextLine().trim();
        if (identificador.isEmpty()) {
            System.out.println("Erro: O identificador não pode estar vazio.");
        }
    } while (identificador.isEmpty());

    double area = 0;
    while (true) {
        System.out.print("Área: ");
        try {
            area = scanner.nextDouble();
            scanner.nextLine(); // Consumir a quebra de linha
            if (area > 0) break;
            System.out.println("Erro: A área deve ser maior que zero.");
        } catch (InputMismatchException e) {
            System.out.println("Erro: Insira um valor numérico válido.");
            scanner.nextLine();
        }
    }

    double percentagem = 0;
    while (true) {
        System.out.print("Percentagem: ");
        try {
            percentagem = scanner.nextDouble();
            scanner.nextLine(); // Consumir a quebra de linha
            if (percentagem > 0) break;
            System.out.println("Erro: A percentagem deve ser maior que zero.");
        } catch (InputMismatchException e) {
            System.out.println("Erro: Insira um valor numérico válido.");
            scanner.nextLine();
        }
    }

    String localizacao;
    do {
        System.out.print("Localização: ");
        localizacao = scanner.nextLine().trim();
        if (localizacao.isEmpty()) {
            System.out.println("Erro: A localização não pode estar vazia.");
        }
    } while (localizacao.isEmpty());

    Proprietario proprietario = selecionarProprietario(scanner);
    Fracao novaFracao = null;

    switch (tipo) {
        case 1 -> {
            System.out.print("Tipo de Apartamento (T0-T5): ");
            String tipoApartamento = scanner.nextLine();

            int casasBanho = 0;
            while (true) {
                System.out.print("Número de Casas de Banho: ");
                try {
                    casasBanho = scanner.nextInt();
                    scanner.nextLine();
                    if (casasBanho >= 0) break;
                    System.out.println("Erro: O número deve ser positivo.");
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Insira um número válido.");
                    scanner.nextLine();
                }
            }

            int varandas = 0;
            while (true) {
                System.out.print("Número de Varandas: ");
                try {
                    varandas = scanner.nextInt();
                    scanner.nextLine();
                    if (varandas >= 0) break;
                    System.out.println("Erro: O número deve ser positivo.");
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Insira um número válido.");
                    scanner.nextLine();
                }
            }

            boolean terraco = solicitarBooleano(scanner, "Tem terraço? (true/false): ");

            novaFracao = new Apartamento(identificador, area, percentagem, localizacao, tipoApartamento, casasBanho, varandas, terraco, proprietario);
        }
        case 2 -> novaFracao = new Loja(identificador, area, percentagem, localizacao, proprietario);
        case 3 -> {
            int capacidade = 0;
            while (true) {
                System.out.print("Capacidade de viaturas: ");
                try {
                    capacidade = scanner.nextInt();
                    scanner.nextLine();
                    if (capacidade >= 0) break;
                    System.out.println("Erro: A capacidade deve ser positiva.");
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Insira um número válido.");
                    scanner.nextLine();
                }
            }

            boolean lavagem = solicitarBooleano(scanner, "Tem lavagem? (true/false): ");
            novaFracao = new Garagem(identificador, area, percentagem, localizacao, capacidade, lavagem, proprietario);
        }
        case 4 -> {
            boolean portaBlindada = solicitarBooleano(scanner, "Tem porta blindada? (true/false): ");
            novaFracao = new Arrecadacao(identificador, area, percentagem, localizacao, portaBlindada, proprietario);
        }
    }

    if (novaFracao != null) {
        condominio.adicionarFracao(novaFracao);
        condominio.recalcularPercentagens();
        System.out.println("Fração adicionada com sucesso!");
    } else {
        System.out.println("Erro ao adicionar fração.");
    }
}

// Método auxiliar para capturar valores booleanos de forma segura
private static boolean solicitarBooleano(Scanner scanner, String mensagem) {
    while (true) {
        System.out.print(mensagem);
        String entrada = scanner.next().trim().toLowerCase();
        if (entrada.equals("true") || entrada.equals("false")) {
            scanner.nextLine(); // Consumir a quebra de linha
            return Boolean.parseBoolean(entrada);
        }
        System.out.println("Erro: Insira 'true' ou 'false'.");
        scanner.nextLine();
    }
}


    // Lista todos os proprietários e permite selecionar um
    private static Proprietario selecionarProprietario(Scanner scanner) {
        if (proprietarios.isEmpty()) {
        System.out.println("Erro: Nenhum proprietario registrado! Cadastre um proprietario antes de adicionar uma fracao.");
        return null; // Retorna null para indicar que não há proprietário disponível
    }
         System.out.println("\n--- Selecionar Proprietario ---");
    for (int i = 0; i < proprietarios.size(); i++) {
        System.out.println((i + 1) + ". " + proprietarios.get(i).getNome());
    }

    int escolha = -1;
    while (escolha < 1 || escolha > proprietarios.size()) {
        System.out.print("Escolha o numero do proprietario: ");
        if (scanner.hasNextInt()) {
            escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            if (escolha < 1 || escolha > proprietarios.size()) {
                System.out.println("Erro: Numero invalido. Escolha um numero entre 1 e " + proprietarios.size());
            }
        } else {
            System.out.println("Erro: Entrada invalida! Digite um numero.");
            scanner.next(); // Consumir entrada inválida
        }
    }
        return proprietarios.get(escolha - 1);
    }
    
    

    // Lista todas as frações do condomínio
    private static void listarFracoes() {
        System.out.println("\n--- Listar Fracoes ---");
        List<Fracao> fracoes = condominio.listarFracoes();
        if (fracoes.isEmpty()) {
            System.out.println("Nenhuma fracao registrada.");
        } else {
            fracoes.forEach(fracao -> {
                System.out.println(fracao);
                System.out.println("Proprietario: " + fracao.getProprietario());
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
    private static void verificarPercentagens(Scanner scanner) {
         boolean validas = condominio.verificarPercentagens();
    System.out.println("\n--- Verificar Percentagens ---");
    System.out.println("As percentagens estao corretas? " + (validas ? "Sim" : "Nao"));

    if (!validas) {
        System.out.print("Deseja recalcular as percentagens? (Sim/Nao): ");
        String resposta = scanner.next().trim().toLowerCase();

        if (resposta.equals("sim")) {
            condominio.recalcularPercentagens();
            System.out.println("Percentagens recalculadas com sucesso!");
        } else {
            System.out.println("Atencao: As percentagens estao incorretas!");
        }
    }
    }

    // Atualiza as despesas gerais e dos elevadores
     private static void atualizarDespesas(Scanner scanner) {
        try {
            System.out.print("\n--- Atualizar Despesas ---\nDespesa Geral: ");
            double despesaGeral = scanner.nextDouble();
            
            System.out.print("Despesa com Elevadores: ");
            double despesaElevadores = scanner.nextDouble();
            
            if (condominio != null) {
                condominio.setDespesaGeral(despesaGeral);
                condominio.setDespesaElevadores(despesaElevadores);
                System.out.println("Despesas atualizadas com sucesso!");
            } else {
                System.out.println("Erro: O condominio nao foi inicializado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada invalida! Insira valores numericos corretos.");
            scanner.next();
        }
    }

    
}

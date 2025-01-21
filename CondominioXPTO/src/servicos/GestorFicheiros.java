/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicos;

import java.io.*;
import java.util.List;
import modelos.Fracao;

/**
 *
 * @author denez
 */
public class GestorFicheiros {
    private static final String FICHEIRO_FRACOES = "src/ficheiros/fracoes.dat";

    public static void salvarFracoes(List<Fracao> fracoes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHEIRO_FRACOES))) {
            oos.writeObject(fracoes);
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static List<Fracao> carregarFracoes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_FRACOES))) {
            return (List<Fracao>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            return null;
        }
    }
}

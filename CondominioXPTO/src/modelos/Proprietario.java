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
public class Proprietario implements Serializable {
    private String identificador;
    private String nome;
    private String morada;
    private String telefone;
    private String telemovel;
    private String email;
    private String dataNascimento;

    public Proprietario(String identificador, String nome, String morada, String telefone,String telemovel, String email, String dataNascimento) {
        this.identificador = identificador;
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.telemovel = telemovel;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Proprietario{" +
                "identificador='" + identificador + '\'' +
                ", nome='" + nome + '\'' +
                ", morada='" + morada + '\'' +
                ", telefone='" + telefone + '\'' +
                ", telemovel='" + telemovel + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                '}';
    }
}

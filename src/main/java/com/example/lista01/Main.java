package com.example.lista01;

import java.util.Scanner;
import persistencia.Persistencia;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String ARQUIVO_XML = "central.xml";
        CentralDeInformacoes central = Persistencia.carregarCentral(ARQUIVO_XML);

        System.out.println("Bem vindo ao sistema de gerenciamento de passageiros!");
        String opcao;

        System.out.println("\n=== MENU ===");
        System.out.println("1 - Novo passageiro");
        System.out.println("2 - Listar todos os passageiros");
        System.out.println("3 - Exibir informações de um passageiro específico");
        System.out.println("S - Sair");
        System.out.print("Escolha uma opção: ");

        switch (opcao) {
            case "1":
                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.println("Data de Nascimento (dd/mm/aaaa): ");
                String dataNascimento = sc.nextLine();

                System.out.println("Email");
                String email = sc.nextLine();

                System.out.println("Sexo: (Masculino/Feminino)");
                String sexo = sc.nextLine();

                if (adicionarPassageiro) {
                    Persistencia.adicionarPassageiro(String nome, Date dataNascimento, String email, String sexo);
                }
                break;

            default:
                break;
        }

    }
}
package com.example.lista01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Main {
    private static final String ARQUIVO_XML = "central.xml";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CentralDeInformacoes central = Persistencia.carregarCentral(ARQUIVO_XML);

        String opcao;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Novo passageiro");
            System.out.println("2 - Listar todos os passageiros");
            System.out.println("3 - Exibir informações de um passageiro específico");
            System.out.println("3 - Exibir informações de um passageiro específico");
            System.out.println("4 - Nova corrida");
            System.out.println("5 - Listar todas as corridas");
            System.out.println("6 - Listar corridas de um passageiro");

            System.out.println("S - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    try {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();

                        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
                        String dataStr = sc.nextLine();
                        Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);

                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        System.out.print("Sexo (M/F): ");
                        String sexoStr = sc.nextLine();
                        Sexo sexo = sexoStr.equalsIgnoreCase("M") ? Sexo.MASCULINO : Sexo.FEMININO;

                        Passageiro passageiro = new Passageiro(nome, sexo, dataNascimento, email);

                        if (central.adicionarPassageiro(passageiro)) {
                            System.out.println("Passageiro adicionado com sucesso!");
                            Persistencia.salvarCentral(central, ARQUIVO_XML);
                        } else {
                            System.out.println("Erro: já existe passageiro com esse email ou idade < 18.");
                        }
                    } catch (ParseException e) {
                        System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
                    }
                    break;

                case "2":
                    if (central.getTodosOsPassageiros().isEmpty()) {
                        System.out.println("Nenhum passageiro encontrado!");
                    } else {
                        System.out.println("=== Lista de passageiros ===");
                        for (Passageiro p : central.getTodosOsPassageiros()) {
                            System.out.println(p.getNome() + " - " + p.getEmail());
                        }
                    }
                    break;

                case "3":
                    System.out.print("Digite o email do passageiro: ");
                    String emailBusca = sc.nextLine();
                    Passageiro encontrado = central.recuperarPassageiroEmail(emailBusca);

                    if (encontrado != null) {
                        System.out.println("=== Passageiro encontrado ===");
                        System.out.println("Nome: " + encontrado.getNome());
                        System.out.println("Email: " + encontrado.getEmail());
                        System.out.println("Sexo: " + encontrado.getSexo());
                        System.out.println("Data Nasc.: " + encontrado.getDataNascimento());
                    } else {
                        System.out.println("Passageiro não encontrado!");
                    }
                    break;
                case "4":
                    System.out.print("Digite o email do passageiro: ");
                    String emailPassageiro = sc.nextLine();
                    Passageiro passageiroCorrida = central.recuperarPassageiroEmail(emailPassageiro);
                    if (passageiroCorrida != null) {
                        System.out.print("Endereço de partida: ");
                        String partida = sc.nextLine();
                        System.out.print("Endereço de destino: ");
                        String destino = sc.nextLine();
                        Corrida corrida = new Corrida(partida, destino, passageiroCorrida);
                        if (central.adicionarCorrida(corrida)) {
                            System.out.println("Corrida cadastrada com sucesso! ID: " + corrida.getId());
                        } else {
                            System.out.println("Erro, corrida com ID duplicado.");
                        }
                    } else {
                        System.out.println("Passageiro não encontrado.");
                    }
                    break;

                case "5":
                    if (central.getTodasAsCorridas().isEmpty()) {
                        System.out.println("Nenhuma corrida cadastrada.");
                    } else {
                        for (Corrida c : central.getTodasAsCorridas()) {
                            System.out.println(c);
                        }
                    }
                    break;

                case "6":
                    System.out.print("Digite o email do passageiro: ");
                    String emailBuscaCorridas = sc.nextLine();
                    ArrayList<Corrida> corridas = central.recuperarCorridasDeUmPassageiro(emailBuscaCorridas);
                    if (corridas == null) {
                        System.out.println("Passageiro não encontrado.");
                    } else if (corridas.isEmpty()) {
                        System.out.println("O passageiro não possui corridas cadastradas.");
                    } else {
                        for (Corrida c : corridas) {
                            System.out.println(c);
                        }
                    }
                    break;

                case "S":
                case "s":
                    System.out.println("Saindo....");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (!opcao.equalsIgnoreCase("S"));

        sc.close();
    }
}

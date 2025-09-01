package com.example.lista01;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CentralDeInformacoes {
    private ArrayList<Passageiro> todosOsPassageiros;
    private ArrayList<Corrida> todasAsCorridas;

    // Construtor padrão: inicializa listas
    public CentralDeInformacoes() {
        todosOsPassageiros = new ArrayList<>();
        todasAsCorridas = new ArrayList<>();
    }

    // Método para garantir que listas não sejam nulas (para quando carregar do XML)
    public void inicializarListas() {
        if (todosOsPassageiros == null) todosOsPassageiros = new ArrayList<>();
        if (todasAsCorridas == null) todasAsCorridas = new ArrayList<>();
    }

    public boolean adicionarPassageiro(Passageiro passageiro) {
        // Verificar email repetido
        for (Passageiro p : todosOsPassageiros) {
            if (p.getEmail().equals(passageiro.getEmail())) {
                return false;
            }
        }

        // Verificar idade >= 18
        if (calcularIdade(passageiro.getDataNascimento()) < 18) {
            return false;
        }

        todosOsPassageiros.add(passageiro);
        return true;
    }

    public ArrayList<Passageiro> getTodosOsPassageiros() {
        return todosOsPassageiros;
    }

    public Passageiro recuperarPassageiroEmail(String email) {
        for (Passageiro p : todosOsPassageiros) {
            if (p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
    }

    private int calcularIdade(Date dataNascimento) {
        Calendar nascimento = Calendar.getInstance();
        nascimento.setTime(dataNascimento);

        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
        if (hoje.get(Calendar.DAY_OF_YEAR) < nascimento.get(Calendar.DAY_OF_YEAR)) {
            idade--; // ainda não fez aniversário este ano
        }
        return idade;
    }

    public boolean adicionarCorrida(Corrida corrida) {
        for (Corrida c : todasAsCorridas) {
            if (c.getId() == corrida.getId()) {
                return false; // ID duplicado
            }
        }
        todasAsCorridas.add(corrida);
        return true;
    }

    public Corrida recuperarCorridaPeloId(long id) {
        for (Corrida c : todasAsCorridas) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Corrida> recuperarCorridasDeUmPassageiro(String emailPassageiro) {
        Passageiro passageiro = recuperarPassageiroEmail(emailPassageiro);
        if (passageiro == null) return null; // Passageiro não existe

        ArrayList<Corrida> lista = new ArrayList<>();
        for (Corrida c : todasAsCorridas) {
            if (c.getPassageiro().getEmail().equals(emailPassageiro)) {
                lista.add(c);
            }
        }
        return lista; // pode ser lista vazia
    }

    public ArrayList<Corrida> getTodasAsCorridas() {
        return todasAsCorridas;
    }
}

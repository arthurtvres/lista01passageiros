package com.example.lista01;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CentralDeInformacoes {
    private ArrayList<Passageiro> todosOsPassageiros = new ArrayList<>();

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

    // Calcula idade do passagiero
    private int calcularIdade(Date dataNascimento) {
        Calendar nascimento = Calendar.getInstance();
        nascimento.setTime(dataNascimento);

        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
        return idade;
    }

}

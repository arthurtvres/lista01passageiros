package com.example.lista01;

import com.thoughtworks.xstream.XStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

public class Persistencia {

    /**
     * Salva um objeto CentralDeInformacoes em um arquivo XML.
     *
     * @param central     O objeto a ser salvo
     * @param nomeArquivo Nome do arquivo XML
     */
    public static void salvarCentral(CentralDeInformacoes central, String nomeArquivo) {
        XStream xstream = new XStream();

        // Configura segurança e permite explicitamente as classes que vão aparecer no XML
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[] {
                CentralDeInformacoes.class,
                Passageiro.class,
                Sexo.class,
                Corrida.class
        });

        String xml = xstream.toXML(central);
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recupera um objeto CentralDeInformacoes de um arquivo XML.
     *
     * @param nomeArquivo Nome do arquivo XML
     * @return Objeto CentralDeInformacoes recuperado ou novo se o arquivo não existir
     */
    public static CentralDeInformacoes carregarCentral(String nomeArquivo) {
        XStream xstream = new XStream();

        // Configura segurança e permite explicitamente as classes que vão aparecer no XML
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[] {
                CentralDeInformacoes.class,
                Passageiro.class,
                Sexo.class,
                Corrida.class
        });

        try {
            CentralDeInformacoes central = (CentralDeInformacoes) xstream.fromXML(new FileReader(nomeArquivo));
            central.inicializarListas(); // Garante que listas não fiquem nulas
            return central;
        } catch (IOException e) {
            e.printStackTrace();
            return new CentralDeInformacoes();
        }
    }
}

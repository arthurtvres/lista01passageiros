package com.example.lista01;

import com.thoughtworks.xstream.XStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

/**
 * Salva um objeto CentralDeInformacoes em um arquivo XML.
 *
 * @param central     O objeto a ser salvo
 * @param nomeArquivo Nome do arquivo XML
 */
public class Persistencia {

    public static void salvarCentral(CentralDeInformacoes central, String nomeArquivo) {
        XStream xstream = new XStream();
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
     * @return Objeto CentralDeInformacoes recuperado ou novo se o arquivo não
     *         existir
     */
    public static CentralDeInformacoes carregarCentral(String nomeArquivo) {
        XStream xstream = new XStream();
        try {
            return (CentralDeInformacoes) xstream.fromXML(new FileReader(nomeArquivo));
        } catch (IOException e) {
            e.printStackTrace();
            return new CentralDeInformacoes();
        }
    }

}

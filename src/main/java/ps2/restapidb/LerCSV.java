/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ps2.restapidb;

/**
 *
 * @author danie
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LerCSV {

    private BufferedReader lerGenerico(InputStream is) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }

        return br;
    }

    public ArrayList<Usuario> lerUsuario(InputStream is) {

        BufferedReader br = lerGenerico(is);
        String linha = "";

        ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();
        try {            
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int matricula;
                try {   
                    matricula = Integer.parseInt(dados[6].replace("\"", "").trim());
                } catch (NumberFormatException nfe) {
                    matricula = 0;
                }
                
                listUsuarios.add(new Usuario(
                    0L,
                    dados[0].replace("\"", "").trim(), 
                    dados[1].replace("\"", "").trim(), 
                    "", 
                    dados[2].replace("\"", "").trim(), 
                    dados[3].replace("\"", "").trim(), 
                    dados[4].replace("\"", "").trim(), 
                    Long.parseLong(dados[5].replace("\"", "").trim()), 
                    matricula
                ));
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return listUsuarios;
    }

}

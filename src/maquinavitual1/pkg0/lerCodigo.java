/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinavitual1.pkg0;

/**
 *
 * @author puc
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class lerCodigo {

    /**
     *
     * @param nameArquivo
     * @return
     */
    public List<String> arquivoRead(String nameArquivo)
   {
       List<String> texto;   // lista do texto , cada posição é uma linha
       texto = new ArrayList();
        try
        {
            FileInputStream arquivo = new FileInputStream(nameArquivo);
            InputStreamReader input = new InputStreamReader(arquivo);
            BufferedReader br       = new BufferedReader(input);
            String linha;
            do
            {
                linha = br.readLine();
                texto.add(linha);          
            }while(linha!=null);

        }catch(IOException e)
        { 
            System.err.printf("Erro na abertura do arquivo: %s.\n",e.getMessage());
        }
        
                                        //for(String s : lista) {  
                                        // codigo pra imprimir ou fazer o diabo com a string.
                                        //}
   return texto;
   }  
          
}





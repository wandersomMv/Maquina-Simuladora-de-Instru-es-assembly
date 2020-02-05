/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinavitual1.pkg0;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Wemerson
 */
public class MaquinaVitual10 {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
      try{  
         simuladora maquina = new simuladora(10);
         //String comando = new Scanner(System.in).nextLine();
        
        //maquina.memoriaInstrucoes[0] = comando;
        //maquina.buscarInstrucao();
        //maquina.DecodificarInstrucao();
        lerCodigo Lcodigo = new lerCodigo(); // classe para ler o arquivo 
        List<String> codigoTxt = new ArrayList(Lcodigo.arquivoRead("codigo.txt")); // lista/linhas do arquivo
        run executar = new run();
        executar.exe(maquina, codigoTxt);
      }catch(Exception e)
      {
          System.out.println(e);
      }
        
        
        
    }
    
}

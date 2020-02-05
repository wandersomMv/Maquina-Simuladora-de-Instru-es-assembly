/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinavitual1.pkg0;

import java.util.List;

/**
 *
 * @author puc
 */
public class run {
    
    public void exe( simuladora maquina, List<String> codigoTxt) throws Exception // xecutar a maquina
    {
       
        try{   
               maquina.carregarInstrucoes(codigoTxt); // carrega todas as intruções para memoria
               System.out.println("Tamnho codigo" + codigoTxt.size());
               while(maquina.getPc()<(codigoTxt.size()-2)) // equanto o pc não chegar no fim do programa
               {
                    maquina.buscarInstrucao(); // busca
                    maquina.DecodificarInstrucao(); // resolve
               }

         }
        catch(Exception e){
             System.out.println("_"+e);
         }
    }
}

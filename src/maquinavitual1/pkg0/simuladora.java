/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinavitual1.pkg0;




import static java.lang.Math.pow;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

import static jdk.nashorn.internal.runtime.JSType.isNumber;

/**
 *
 * @author Wemerson
 */
public class simuladora {
    
    
    private ArrayList<String> opAritLog = new ArrayList<>(Arrays.asList("add","sub",
            "mul","div","mod","pot","and","or","xor","not"));
    private ArrayList<String> opDesv = new ArrayList<>(Arrays.asList("jump", "jeq",
            "jne", "jlt", "jle", "jgt", "jge"));
    private ArrayList<String> opMovDados = new ArrayList<>(Arrays.asList("load", "store",
            "move", "push", "pop"));
    private ArrayList<String> opES = new ArrayList<>(Arrays.asList("read", "write"));
    
    private HashMap registrador; // quantidade de registradores 
    final private int[] memoriaDados;
    public String[] memoriaInstrucoes;
    private String instrucaoAtual;
    final private Stack<Integer> pilha;
    private int pc;
    final private int flag;
    public int getPc(){return pc;}
  public  simuladora(int qtdRegistrador) // iniciar maquina
  {
    
      String aux = new String(); // auxiliar para colocar o nome do registrador
      registrador = new HashMap<String, Integer>(); // declarar um hashmap , primeira posicao chave seundo valor
      for(int i=0;i<qtdRegistrador;i++)
      {
          aux = "R"+ Integer.toString(i); // colocar o nome como R0, R1 ...
          registrador.put(aux,0); // todos registradores comecam zerados 
      }
      memoriaDados = new int[1024]; // tamanho da memoria de dados 
      memoriaInstrucoes = new String[1024]; // tamanho da memoria de instrucoes
      pilha = new Stack();  // pilha do asssmebly
      pc = 0; // contador de programa
      flag = 0;
      /*registrador.put("R1",0);
      registrador.put("R2",10);
      registrador.put("R3",10);*/
    }
    public void buscarInstrucao(){
        instrucaoAtual = memoriaInstrucoes[pc]; // pega a instrução que o pc aponta
        pc++;
    }
    public boolean verificaRegistrador(String registrador1,String registrador2,String registrador3){
      // veriifca se rodos estão no banco
        System.out.println("Registrador1 : "+ registrador1);
      if(!registrador.containsKey(registrador1)){System.out.println("Regsitrador 1 errado"); return false;} // lançar exeção 
      if(!registrador.containsKey(registrador2)){System.out.println("Regsitrador 2 errado");return false;}
      if(!registrador.containsKey(registrador3)&&(!isNumeric(registrador3))){System.out.println("Regsitrador 3 errado");return false;}
      return true;
    } 
   
    void carregarInstrucoes(List<String> codigoTxt)  // caregar as intruções para memoria de instruções
    {
        int i=0; // posição de memoria
        for(String s:codigoTxt) // percorrer a lista
        {
            if(s!=null)
            {
               memoriaInstrucoes[i] = s; // instrução colocada no vetor de instruções
                System.out.println("Intruções: "+  memoriaInstrucoes[i]);
               i++;
            }
        }
    }
    
    
    void buscaResgistradores(StringTokenizer lexer,String registrador1, String registrador2, String registrador3){
         registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES
         registrador2 = lexer.nextToken();
         registrador3 = lexer.nextToken();
    }
    
    public void add(StringTokenizer lexer) throws Exception
    {
        
        String registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES
        String registrador2 = lexer.nextToken();
        String registrador3 = lexer.nextToken();
         System.out.println("Registradores:"+ registrador1+registrador2+registrador3);
        int resultado;
          if(!verificaRegistrador(registrador1,registrador2,registrador3)) // lançar exeção  // verifica se tem os registradores
         {
             throw new Exception("ERRO: sub está com erro nos registradores");
         }
           System.out.println("VALOR: "+ (int)registrador.get(registrador2));
         if (registrador.containsKey(registrador3)) 
         {
             System.out.println("VALOR: "+ (int)registrador.get(registrador2));
             resultado = (int)registrador.get(registrador2) + (int)registrador.get(registrador3);
            
         } else {
             resultado = (int)registrador.get(registrador2) + Integer.parseInt(registrador3);
            
         }
         registrador.put(registrador1, resultado);
         System.out.println("RESULTADO : " + Integer.toString(resultado));
    }
    public void sub(StringTokenizer lexer) throws Exception{
        System.out.println("sub");
        String registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES
        String registrador2 = lexer.nextToken();
        String registrador3 = lexer.nextToken();
        int resultado;
          if(!verificaRegistrador(registrador1,registrador2,registrador3)) // lançar exeção  // verifica se tem os registradores
         {
             throw new Exception("ERRO: sub está com erro nos registradores");
         }
         if (registrador.containsKey(registrador3)) 
         {
             resultado = (int) ((long)registrador.get(registrador2) - (long)registrador.get(registrador3));
             registrador.put(registrador1, resultado);
         } else {
             resultado = (int) ((int)registrador.get(registrador2) - Long.parseLong(registrador3));
             registrador.put(registrador1, resultado);
         }
         registrador.put(registrador1, resultado);
         System.out.println("RESULTADO : " + Integer.toString(resultado));
        
    }
    public void mult(StringTokenizer lexer) throws Exception{
        System.out.println("mult");
        String registrador1,registrador2,registrador3;
        registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES
         registrador2 = lexer.nextToken();
         registrador3 = lexer.nextToken();
         int resultado;
          if(verificaRegistrador(registrador1,registrador2,registrador3)) // lançar exeção  // verifica se tem os registradores
         {
            throw new Exception("ERRO: sub está com erro nos registradores");
         }
         if (registrador.containsKey(registrador3)) 
         {
             resultado = (int)registrador.get(registrador2)*(int)registrador.get(registrador3);
             registrador.put(registrador1, resultado);
         } else {
             resultado = (int)registrador.get(registrador2)*Integer.parseInt(registrador3);
             registrador.put(registrador1, resultado);
         }
         registrador.put(registrador1, resultado);
         System.out.println("RESULTADO : " + Integer.toString(resultado));
    
    
    }
    public void or(StringTokenizer lexer) throws Exception{
        System.out.println("or");
        String registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES
        String registrador2 = lexer.nextToken();  // operando 1
        String registrador3 = lexer.nextToken(); // operando 2
        int resultado;
          if(!verificaRegistrador(registrador1,registrador2,registrador3)) // lançar exeção  // verifica se tem os registradores
         {
            throw new Exception("ERRO: or está com erro nos registradores");
         }
         if (registrador.containsKey(registrador3)) 
         {
             resultado = (int)registrador.get(registrador2)|(int)registrador.get(registrador3);
             registrador.put(registrador1, resultado);
         } else {
             resultado = (int)registrador.get(registrador2)|Integer.parseInt(registrador3);
             registrador.put(registrador1, resultado);
         }
         registrador.put(registrador1, resultado);
         System.out.println("RESULTADO : " + Integer.toString(resultado));
        
        
    }
    public static boolean isNumeric (String s) {
    try {
        long parseLong = Long.parseLong(s); 
        return true;
    } catch (NumberFormatException ex) {
        return false;
    }
}
    public void div(StringTokenizer lexer) throws Exception{
        String registrador1,registrador2,registrador3; 
        int resultado;
        System.out.println("div");
        registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES // resultado
        registrador2 = lexer.nextToken();  // opernado 1
        registrador3 = lexer.nextToken();   // operando 2
         if(!verificaRegistrador(registrador1,registrador2,registrador3)) // lançar exeção  // verifica se tem os registradores
        {
            throw new Exception("ERRO: div está com erro nos registradores");
        }
          // verificar se é zxero
         if((int)registrador.get(registrador3)==0){  // se o divisor for zero
            throw new Exception("ERRO: Divisão por zero");
        }  
        if (registrador.containsKey(registrador3)) 
        {
            resultado = (int)registrador.get(registrador2)/(int)registrador.get(registrador3);
            registrador.put(registrador1, resultado);
        } else {
            resultado = (int)registrador.get(registrador2)/Integer.parseInt(registrador3);
            registrador.put(registrador1, resultado);
        }
        registrador.put(registrador1, resultado);
        System.out.println("RESULTADO : " + Integer.toString(resultado));
    }
    public void mod(StringTokenizer lexer) throws Exception{
        String registrador1,registrador2,registrador3; 
        int resultado;
        System.out.println("mod");
        registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES
        registrador2 = lexer.nextToken();
        registrador3 = lexer.nextToken();
         if(!verificaRegistrador(registrador1,registrador2,registrador3)) // lançar exeção  // verifica se tem os registradores
        {
            throw new Exception("ERRO: mod está com erro nos registradores");
        }
          // verificar se é zxero
        if((int)registrador.get(registrador3)==0){  // se o divisor for zero
            throw new Exception("ERRO: Mod por zero");
        }   
        if (registrador.containsKey(registrador3)) 
        {
            resultado = (int)registrador.get(registrador2)%(int)registrador.get(registrador3);  
        } else {
            resultado = (int)registrador.get(registrador2)%Integer.parseInt(registrador3);
           
        }
        registrador.put(registrador1, resultado);
        System.out.println("RESULTADO : " + Integer.toString(resultado));
    }
    public void pot(StringTokenizer lexer) throws Exception{
        String registrador1,registrador2,registrador3; 
        float resultado;
        System.out.println("pot");
        registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES
        registrador2 = lexer.nextToken();
        registrador3 = lexer.nextToken();
         if(!verificaRegistrador(registrador1,registrador2,registrador3)) // lançar exeção  // verifica se tem os registradores
        {
             throw new Exception("ERRO: pot está com erro nos registradores");
        }
          // verificar se é zxero

        if (registrador.containsKey(registrador3)) 
        {

            resultado =  (float) Math.pow((double)registrador.get(registrador2),(double)registrador.get(registrador3));
            
        } else {
            resultado =  (float)Math.pow((double)registrador.get(registrador2),(double)Integer.parseInt(registrador3));
           
        }
        registrador.put(registrador1, (int)resultado);
                        
    }
    public void move(StringTokenizer lexer) throws Exception{
        String registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES
        String registrador2 = lexer.nextToken();
        if(!registrador.containsKey(registrador1)){ // verifica se o primeiro registrador esta no banco // lançar exeção
            throw new Exception("Primeiro registrador Invalido!!");
        }
        if(!isNumeric(registrador2)&& (! registrador.containsKey(registrador2))){ // não e numero e nem estano banco de registradores
            throw new Exception("Resgistrador2 não é numero nem esta no banco de registradores");
        }
        if(registrador.containsKey(registrador2)){ // movimentação de registradores
            registrador.put(registrador1,registrador.get(registrador2));
        }else{

         registrador.put(registrador1, Long.parseLong(registrador2));
        }
        System.out.println("CHAVE: " + registrador.containsKey(registrador1)+ "Valor" + registrador.get(registrador1).toString());

    }
    public void load(StringTokenizer lexer) throws Exception{
        String registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES // conteudo
        String registrador2 = lexer.nextToken(); // local
        if(!registrador.containsKey(registrador1)){ // verifica se o primeiro registrador esta no banco // lançar exeção
             throw new Exception("Primeiro registrador Invalido!!");
        }
        if((registrador.containsKey(registrador2))) { // valor esta no registrador x
           int carregar = (int) registrador.get(registrador2); // valor na posicao de memoria
           if(carregar>=0 && carregar<1024){
               registrador.put(registrador1,memoriaDados[carregar]);  

           }else{
               throw new Exception("Posicao de memoria invalida!1");
           }
        }

        if(isNumeric(registrador2))
        { // não e numero e nem estano banco de registradores

            int carregar = (int)Long.parseLong(registrador2); // valor na posicao de memoria
            if(carregar>=0 && carregar<1024){
               registrador.put(registrador1,memoriaDados[carregar]);  

           }else{
               throw new Exception("Posicao de memoria invalida!2");
           }

        }else
        {
             throw new Exception("Possição de memoria invalida!2");
        }

        System.out.println("Regsitrador: "+ registrador1+ " Valor: "+ registrador.get(registrador1).toString());
    
    
    }
    public void store(StringTokenizer lexer) throws Exception{
     
        String registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES // conteudo
        String registrador2 = lexer.nextToken(); // local na memoria de dados
        if(!registrador.containsKey(registrador1)){ // verifica se o primeiro registrador esta no banco // lançar exeção
             throw new Exception("Primeiro registrador Invalido!!");
        }
        if((registrador.containsKey(registrador2))) 
        { // valor esta no registrador x
            int carregar = (int) registrador.get(registrador2); // valor na posicao de memoria
            if(carregar>=0 && carregar<1024){ // local esta na memoria
                  // se o conteudo for numerico
                     memoriaDados[carregar] = (int)registrador.get(registrador1);


            }else{
                throw new Exception("Posicao de memoria invalida!1");
            }
        }
        
        if(isNumeric(registrador2))
        { // não e numero e nem estano banco de registradores
             
            int carregar = (int)Long.parseLong(registrador2); // valor na posicao de memoria
            if(carregar>=0 && carregar<1024){

                    memoriaDados[carregar] = (int)registrador.get(registrador1);

           }else{
               throw new Exception("Posicao de memoria invalida!2");
           }
            
        }else
        {
             throw new Exception("Possição de memoria invalida!2");
        }
      
        System.out.println("Regsitrador: "+ registrador1+ " Valor: "+memoriaDados[(int)Long.parseLong(registrador2)]);
    }
    public void and(StringTokenizer lexer) throws Exception{
        String registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES
        String registrador2 = lexer.nextToken();
        String registrador3 = lexer.nextToken();
        int resultado;
          if(!verificaRegistrador(registrador1,registrador2,registrador3)) // lançar exeção  // verifica se tem os registradores
         {
              throw new Exception("ERRO: sub está com erro nos registradores");
         }
         if (registrador.containsKey(registrador3)) 
         {
             resultado = (int)registrador.get(registrador2) & (int)registrador.get(registrador3);
             
         } else {
             resultado = (int)registrador.get(registrador2) & Integer.parseInt(registrador3);
            
         }
         registrador.put(registrador1, resultado);
         System.out.println("RESULTADO : " + Integer.toString(resultado));
    }
    public void not(StringTokenizer lexer) throws Exception{
    
        String registrador1 = lexer.nextToken(); // pegando primero perador
        if(registrador.containsKey(registrador1))
        {
           registrador.put(registrador1,~((int)registrador.get(registrador1)));
        }else
        {
            throw new Exception("Resgitrador errado");
        }    
    }
    public void xor(StringTokenizer lexer) throws Exception{
        String registrador1 = lexer.nextToken();  // PEGANDO OS TRS REGISTRADORES
        String registrador2 = lexer.nextToken();
        String registrador3 = lexer.nextToken();
        int resultado;
          if(!verificaRegistrador(registrador1,registrador2,registrador3)) // lançar exeção  // verifica se tem os registradores
         {
              throw new Exception("ERRO: xor está com erro nos registradores");
         }
         if (registrador.containsKey(registrador3)) 
         {
             resultado = (int)registrador.get(registrador2)^(int)registrador.get(registrador3);
            
         } else {
             resultado = (int)registrador.get(registrador2)^Integer.parseInt(registrador3);
             
         }
         registrador.put(registrador1, resultado);
         System.out.println("RESULTADO : " + Integer.toString(resultado));
    }
    public void push(StringTokenizer lexer) throws Exception{
        String registrador1 = lexer.nextToken();
        if(registrador.containsKey(registrador1))
        {
            pilha.push((int)registrador.get(registrador1));
        }else{ throw new Exception("Registrador nao encontrado!");}
    }
    public void pop(StringTokenizer lexer) throws Exception{
        String registrador1 = lexer.nextToken();
        if(registrador.containsKey(registrador1))
        {
            if(!pilha.isEmpty()){
                registrador.put(registrador1, pilha.pop());
            }else{ throw new Exception("A pilha está vazia!!");
            }
            
        }else{ throw new Exception("Registrador nao encontrado!");}
    }
    public void read(StringTokenizer lexer) throws Exception{
      String registrador1 = lexer.nextToken();
      if(registrador.containsKey(registrador1)){
          Scanner entrada = new Scanner(in);
          int tela = entrada.nextInt();
          registrador.put(registrador1,tela);
          System.out.println("Valor LIDO: "+tela);
      }else{ throw new Exception("Registrador não encontrado");}
    }
    public void write(StringTokenizer lexer) throws Exception{
        String registrador1 = lexer.nextToken();
        if(registrador.containsKey(registrador1))
        {
            System.out.println(registrador.get(registrador1));
        }else{ throw new Exception("Resgistrador nao encontrado");}
    }
    public boolean se(StringTokenizer lexer, String op) throws Exception{
        String registrador1 = lexer.nextToken();
        String registrador2 = lexer.nextToken();
        if(!registrador.containsKey(registrador1)&&!registrador.containsKey(registrador2))
        { throw new Exception("Registrador de comparação do desvio incorreto!");}
        boolean resultado = false;
        if(null != op)switch (op) {
            case "jeq":
                // se for igual
                resultado = (registrador.get(registrador1)==registrador.get(registrador2));
                break;
            case "jne":
                // se for diferente
                resultado = (registrador.get(registrador1)!=registrador.get(registrador2));
                break;
            case "jlt":
                // menor
                resultado = ((int)(registrador.get(registrador1) )< (int)( registrador.get(registrador2)));
                break;
            case "jle":
                // menor igual
                resultado = ((int)(registrador.get(registrador1) )<= (int)( registrador.get(registrador2)));
                break;
            case "jgt":
                // maior
                resultado = ((int)(registrador.get(registrador1) ) > (int)( registrador.get(registrador2)));
                break;
            case "jge":
                // maior igual
                resultado = ((int)(registrador.get(registrador1) ) >= (int)( registrador.get(registrador2)));
                break;
            default:
                 throw new Exception("Registrador Para comparação não encotrado!");
              
        }
       return resultado;
    }
    public void jump(StringTokenizer lexer, String jump) throws Exception
    {
         String registrador1 = lexer.nextToken(); // destino se a confição for verdadeira
         if(!registrador.containsKey(registrador1)){  // registrador de destino não é válido
          throw new Exception("Registrador de destino do desvio incorreto!");
         }
         if(!verificarPosInstrucoes(registrador1)){ // verifica se existe essa intrucao na memoria de instrucoes
             throw new Exception("Registrador de destino do desvio está fora da memória de INSTRUÇÕES!");
         }
         if( se(lexer,jump))  // se o desvio der certo aponta para posição que quer pular
         {
             pc =  (int)registrador.get(registrador1);
             System.out.println("DEU BOM: " + pc);
         }
         
    }
    
    public boolean verificarPosInstrucoes(String registradorDestino) // verifica se o valor do registrador esta no intevalo da memoria de instrucoes
    {
       
        return ((int)registrador.get(registradorDestino)>=0&&(int)registrador.get(registradorDestino)<memoriaInstrucoes.length);
        // 0<=Registrador<tam
    }
    
    public void DecodificarInstrucao() throws Exception
    {
        StringTokenizer lexer = new StringTokenizer(instrucaoAtual); // classe para separar os tokens,  split  e etc
        
        System.out.println("INTRUÇÃO ATUAL: "+ instrucaoAtual);
        if (lexer.countTokens() <= 4&& lexer.countTokens()>=2) // instrução de tamanho maxaimo add r1 r1 r2 => r1 = r1+r2
        {
            String operacao = lexer.nextToken();
            if(opAritLog.contains(operacao)) // berificar se o codigo é logico aritmetico
            { 
                String registrador1 = new String(); String registrador2 = new String();String registrador3= new String();
                int resultado=0;
                switch (operacao) // verificar qual operador é
                {
                    case "add": // se a operação foor de adicionar

                        if(lexer.countTokens()==3)
                        {
                            add(lexer);
                        }else
                        {System.out.println("registradores do ADD incompativeis");}
                    break;
                    case "sub":

                         if(lexer.countTokens()==3)
                         {
                            sub(lexer);
                         }else
                         {System.out.println("registradores do SUB incompativeis");}
                     break;
                    case "mul":
                      if(lexer.countTokens()==3){
                        mult(lexer);
                      }else{System.out.println("registradores do MUL incompativeis");}

                    break;
                    case  "div":
                       if(lexer.countTokens()==3){
                       div(lexer);}else{System.out.println("registradores do DIV incompativeis");}
                    break;
                    case "mod":
                        if(lexer.countTokens()==3){
                       mod(lexer);}else{System.out.println("registradores do MOD incompativeis");}
                    break;
                    case "pot": // r1 = r2^r3
                        if(lexer.countTokens()==3){
                       pot(lexer);}else{System.out.println("registradores do POT incompativeis");}   
                    case "or":    
                        if(lexer.countTokens()==3)
                        {
                            or(lexer);
                        }else{System.out.println("registradores do OR incompativeis");}
                    break;
                    case "not":
                        if(lexer.countTokens()==1){
                            not(lexer);
                        }
                        else
                        {
                            System.out.println("Erro no operador not");
                        }
                        
                    break;
                    case "xor":
                        if(lexer.countTokens()==3)
                        {
                            xor(lexer);
                        }else{ throw new Exception("registradores do OR incompativeis");}
                    break;
                }
            
            }
            else if (opDesv.contains(operacao)) { // tipo compracao desino operando1 operando2
                
                if(lexer.countTokens() ==3){  // verifica se tem tres operadore R1 destino , r2 r3 comprar operação o tipo de comparacao
                    jump(lexer,operacao);
                }else{
                    throw new Exception("numero de operandos invalidos para comparação");
                }
                
                System.out.println("Operador de desvio");

            }
            else if (opMovDados.contains(operacao)) {
                System.out.println("Operador de Movimentação de dados : " + operacao);
                
                
                switch(operacao)
                {
                    case "move":
                     if(lexer.countTokens()==2){// dois registradores
                         move(lexer);
                     }else
                     {   throw new Exception("move com registradores errados");
                     }
                    break;
                    case "load": // "carregar da memoria"
                        if(lexer.countTokens()==2){// dois registradores
                         load(lexer);
                        }else
                        {   throw new Exception("load com registradores errados");
                        }
                     break;
                    case "store": // tipo: store R1 posicao de memoria // "escrever na memoria"
                        if(lexer.countTokens()==2){// dois registradores
                         store(lexer);
                        }else
                        {   throw new Exception("store com registradores errados");
                        }
                        
                    break;
                    case "push":
                        if(lexer.countTokens()==1){
                            push(lexer);
                        }else{ throw new Exception("Resgistradores para PUSH invalido!");}
                    break;
                    case "pop":
                        if(lexer.countTokens()==1){
                            pop(lexer);
                        }else{ throw new Exception("Resgistradores para PUSH invalido!");}
                    break;
                    
                        
                }
                
                
            }
            else if (opES.contains (operacao)) {
                System.out.println("Operador de Entrada/Saída");
                if("read".equals(operacao)){
                   if(lexer.countTokens()==1)read(lexer);else  throw new Exception("REgistradores read invalido"); 
                }else if("write".equals(operacao)){
                    if(lexer.countTokens()==1) write(lexer);else  throw new Exception("REsgistradore de write errado");
                }
                
            }
            else {
                 throw new Exception("ERRO! Opcode inválido!");
            }
                        
        }
    
    }
    
}

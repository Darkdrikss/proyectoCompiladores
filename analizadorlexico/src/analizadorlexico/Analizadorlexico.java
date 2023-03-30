
package analizadorlexico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adrikss Colin
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Analizadorlexico {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {        
     
        Scanner reader = new Scanner(new File("input.txt"));        
        
        //ArrayList para lexemas y HashTable para la tabla de símbolos
        ArrayList<String> lines = new ArrayList<>();
        Map<String, List<String>> symbolTable = new HashMap<String, List<String>>();
        
        //Lee cada línea y luego divide todo en lexemas
        while (reader.hasNextLine()) {
            String str = reader.nextLine(); 
            if (!(str.length() == 0)) {     
                String [] strSplit = str.trim().split("\\s+|\\s*,\\s*|\\;+|\\\"+|\\:+|\\[+|\\]+");     //Expresión regular para dividir el código en lexemas         
                
                List <String> list = Arrays.asList(strSplit);       //Guardar la matriz de lexemas en una lista de contenedores
                lines.addAll(list);                                 //Agregar la lista de contenedores al ArrayList o HashTable.
            }
        }
        
        //Agregar valores desde un ArrayList a la clave de un HashMap mediante palabras clave.
        List<String> keywords = new ArrayList<String>();        
        if (lines.contains("int") || lines.contains("float") || lines.contains("if") || lines.contains("else")) {         
            if (lines.contains("int")) {
                int index = 0;
                index = lines.indexOf("int");   
                keywords.add(lines.get(index));
            }  
            if (lines.contains("float")) {
                int index = 0;
                index = lines.indexOf("float");  
                keywords.add(lines.get(index));
            }  
            if (lines.contains("if")) {
                int index = 0;
                index = lines.indexOf("if");  
                keywords.add(lines.get(index));
            }  
            if (lines.contains("else")) {
                int index = 0;
                index = lines.indexOf("else");   
                keywords.add(lines.get(index));
            }            
        }        
        
       
        symbolTable.put("Keywords", keywords);
        
     
        List<String> operators = new ArrayList<String>();        
        if (lines.contains("=") || lines.contains("-") || lines.contains("+") || lines.contains("*")) {         
            if (lines.contains("=")) {
                int index = 0;
                index = lines.indexOf("=");   
                operators.add(lines.get(index));
            }  
            if (lines.contains("-")) {
                int index = 0;
                index = lines.indexOf("-");  
                operators.add(lines.get(index));
            }  
            if (lines.contains("+")) {
                int index = 0;
                index = lines.indexOf("+");  
                operators.add(lines.get(index));
            }  
            if (lines.contains("*")) {
                int index = 0;
                index = lines.indexOf("*");   
                operators.add(lines.get(index));
            }            
        }        
        
        
        symbolTable.put("Math Operators", operators); 
        
     
        List<String> logical = new ArrayList<String>();        
        if (lines.contains("<") || lines.contains(">") || lines.contains("<=") || lines.contains(">=")) {         
            if (lines.contains("<")) {
                int index = 0;
                index = lines.indexOf("<");   
                logical.add(lines.get(index));
            }  
            if (lines.contains(">")) {
                int index = 0;
                index = lines.indexOf(">");  
                logical.add(lines.get(index));
            }  
            if (lines.contains("<=")) {
                int index = 0;
                index = lines.indexOf("<=");  
                logical.add(lines.get(index));
            }  
            if (lines.contains(">=")) {
                int index = 0;
                index = lines.indexOf(">=");   
                logical.add(lines.get(index));
            }            
        }        
        

        symbolTable.put("Logical Operators", logical);          
        
    
        String [] linesArray = lines.toArray(new String [0]);
        
      
        List<String> digits = new ArrayList<String>();     
        for (int count = 0;  count < linesArray.length; count++) {
            if (linesArray[count].matches("\\d+|\\d+\\.\\d+")) {                  
                digits.add(linesArray[count]);                       
            }        
        }
        
 
        symbolTable.put("Numerical Values", digits);
        
     
        List<String> identifiers = new ArrayList<String>();     
        for (int count = 0;  count < linesArray.length; count++) {
            if (linesArray[count].matches("\\w+") && !linesArray[count].matches("\\d+") && !linesArray[count].matches("int|float|if|else")) {  //Use regex here for variables  
                if (!identifiers.contains(linesArray[count])) {
                    identifiers.add(linesArray[count]);    
                }
            }        
        }
        
       
        symbolTable.put("Identifiers", identifiers);
        
        //Agregar valores de ArrayList a HashMap Palabras clave clave
        List<String> others = new ArrayList<String>();     
        for (int count = 0;  count < linesArray.length; count++) {
            if (linesArray[count].matches("\\(|\\)|\\{|\\}")) {  
                if (!identifiers.contains(linesArray[count])) {
                    others.add(linesArray[count]);    
                }
            }        
        }
        
        //Poner ArrayLists en HashMap para claves particulares
        symbolTable.put("Others", others);
        
        //imprime ArrayList
        System.out.print("The Lexemes: ");
        System.out.println(lines);  
        
        System.out.println();
        
        //Imprime la tabla de símbolos
        for (Map.Entry<String, List<String>> entry : symbolTable.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            System.out.print(key + ": ");
            System.out.println(values);
        }
    }
}

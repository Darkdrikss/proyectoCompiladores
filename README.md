# proyectoCompiladores
El código se encarga de leer un archivo de entrada ("input.txt") y analizar el código en busca de tokens léxicos, clasificándolos en diferentes categorías y almacenándolos en una tabla de símbolos. La tabla de símbolos se implementa como un HashMap y las categorías son palabras clave, operadores matemáticos, operadores lógicos, valores numéricos, identificadores y otros tokens que no se ajustan a ninguna categoría específica.

El método principal ("main") utiliza un objeto "Scanner" para leer el archivo de entrada y un objeto "HashMap" para almacenar los tokens en la tabla de símbolos. El código primero divide cada línea del archivo de entrada en tokens léxicos utilizando expresiones regulares y los almacena en un ArrayList. Luego, utiliza este ArrayList para buscar tokens en cada categoría y almacenarlos en las listas correspondientes en la tabla de símbolos. Finalmente, imprime la tabla de símbolos resultante. 
Ponemos nuestro código también en ésta parte por los temas que tuvimos en clase con 
NetBeans

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AnalizadorLexico {

    public static void main(String[] args) throws FileNotFoundException {
        // Archivo de entrada
        File inputFile = new File("input.txt");

        // Carga el archivo en una lista de líneas
        List<String> lines = loadFile(inputFile);

        // Inicializa la tabla de símbolos
        Map<String, List<String>> symbolTable = new HashMap<>();

        // Analiza cada línea para extraer los tokens
        for (String line : lines) {
            List<String> tokens = extractTokens(line);
            for (String token : tokens) {
                addTokenToSymbolTable(token, symbolTable);
            }
        }

        // Imprime la tabla de símbolos
        printSymbolTable(symbolTable);
    }

    /**
     * Carga un archivo en una lista de líneas.
     */
    private static List<String> loadFile(File inputFile) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
        }
        return lines;
    }

    /**
     * Extrae los tokens de una línea.
     */
    private static List<String> extractTokens(String line) {
        String[] tokens = line.trim().split("\\s+|\\s*,\\s*|\\;+|\\\"+|\\:+|\\[+|\\]+");
        return Arrays.asList(tokens);
    }

    /**
     * Agrega un token a la tabla de símbolos.
     */
    private static void addTokenToSymbolTable(String token, Map<String, List<String>> symbolTable) {
        if (Pattern.matches("\\d+|\\d+\\.\\d+", token)) {
            addToSymbolTable("Numerical Values", token, symbolTable);
        } else if (Pattern.matches("\\w+", token) && !isKeyword(token)) {
            addToSymbolTable("Identifiers", token, symbolTable);
        } else if (isKeyword(token)) {
            addToSymbolTable("Keywords", token, symbolTable);
        } else if (isOperator(token)) {
            addToSymbolTable("Operators", token, symbolTable);
        } else if (isLogicalOperator(token)) {
            addToSymbolTable("Logical Operators", token, symbolTable);
        } else if (isDelimiter(token)) {
            addToSymbolTable("Delimiters", token, symbolTable);
        } else {
            addToSymbolTable("Others", token, symbolTable);
        }
    }

    /**
     * Agrega un token a una lista en la tabla de símbolos.
     */
    private static void addToSymbolTable(String key, String token, Map<String, List<String>> symbolTable) {
        List<String> list = symbolTable.computeIfAbsent(key, k -> new ArrayList<>());
        if (!list.contains(token)) {
            list.add(token);
        }
    }

    /**
     * Verifica si un token es una palabra clave.
     */
    private static boolean isKeyword(String token) {
        return token.matches("int|float|if|else");
    }

    /**
     * Verifica si un token es un operador matematico

 

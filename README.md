# proyectoCompiladores
El código se encarga de leer un archivo de entrada ("input.txt") y analizar el código en busca de tokens léxicos, clasificándolos en diferentes categorías y almacenándolos en una tabla de símbolos. La tabla de símbolos se implementa como un HashMap y las categorías son palabras clave, operadores matemáticos, operadores lógicos, valores numéricos, identificadores y otros tokens que no se ajustan a ninguna categoría específica.

El método principal ("main") utiliza un objeto "Scanner" para leer el archivo de entrada y un objeto "HashMap" para almacenar los tokens en la tabla de símbolos. El código primero divide cada línea del archivo de entrada en tokens léxicos utilizando expresiones regulares y los almacena en un ArrayList. Luego, utiliza este ArrayList para buscar tokens en cada categoría y almacenarlos en las listas correspondientes en la tabla de símbolos. Finalmente, imprime la tabla de símbolos resultante.
 

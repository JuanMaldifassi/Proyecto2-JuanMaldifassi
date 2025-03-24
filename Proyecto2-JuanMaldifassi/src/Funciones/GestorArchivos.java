/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import EDD.Arbol;
import EDD.HashTable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author juanp
 */
public class GestorArchivos {
    // Leer archivo JSON como cadena completa
    public String leerArchivo(String ruta) {
        StringBuilder contenido = new StringBuilder();
        try {
            BufferedReader lector = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = lector.readLine()) != null) {
                contenido.append(linea.trim());
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }
        return contenido.toString();
    }

    // Procesar el contenido JSON sin usar librerías
    public void procesarClaveDicotomica(String json, Arbol arbol, HashTable tabla) {
        // Eliminar espacios innecesarios
        json = json.replaceAll("\\s+", "");
        // Buscar inicio de la lista
        int inicio = json.indexOf(":[{");
        int fin = json.lastIndexOf("}]}");

        if (inicio == -1 || fin == -1) {
            System.out.println("Formato JSON incorrecto.");
            return;
        }

        String listaEspecies = json.substring(inicio + 2, fin + 1);

        // Separar cada bloque de especie
        String[] bloques = listaEspecies.split("},\\{");

        for (int i = 0; i < bloques.length; i++) {
            String bloque = bloques[i];

            // Normalizar comillas y llaves
            bloque = bloque.replaceAll("[\\{\\}\\[\\]\"]", "");

            // Separar especie de su contenido
            int sep = bloque.indexOf(':');
            if (sep == -1) continue;

            String especie = bloque.substring(0, sep);
            String resto = bloque.substring(sep + 1);

            String[] pares = resto.split(",");

            String[] preguntas = new String[pares.length];
            boolean[] respuestas = new boolean[pares.length];

            for (int j = 0; j < pares.length; j++) {
                String[] partes = pares[j].split(":");
                if (partes.length == 2) {
                    preguntas[j] = partes[0].replaceAll("_", " ").trim(); // limpia guiones bajos
                    respuestas[j] = partes[1].trim().equalsIgnoreCase("true");
                }
            }

            // Insertar en árbol
            arbol.insertarDesdeRuta(preguntas, respuestas, especie);

            // Construir ruta como texto
            String rutaTexto = "";
            for (int k = 0; k < preguntas.length; k++) {
                rutaTexto += preguntas[k] + " = " + (respuestas[k] ? "Sí" : "No");
                if (k < preguntas.length - 1) rutaTexto += " -> ";
            }

            // Insertar en hash
            tabla.insertar(especie, rutaTexto);
        }
    }
}

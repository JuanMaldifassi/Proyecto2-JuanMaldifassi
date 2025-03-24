/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author juanp
 */
public class HashTable {
    private int capacidad;
    private Lista[] tabla;
    
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Lista[] getTabla() {
        return tabla;
    }

    public void setTabla(Lista[] tabla) {
        this.tabla = tabla;
    }

    // Constructor
    public HashTable(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new Lista[capacidad];
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new Lista();
        }
    }

    // Función hash simple: suma de caracteres % capacidad
    private int hash(String clave) {
        int suma = 0;
        for (int i = 0; i < clave.length(); i++) {
            suma += clave.charAt(i);
        }
        return suma % capacidad;
    }

    // Insertar una especie con su ruta (cadena o arreglo personalizado)
    public void insertar(String especie, Object ruta) {
        int indice = hash(especie);
        String[] datos = new String[]{especie, ruta.toString()};
        tabla[indice].InsertarFinal(datos); // Se inserta un arreglo [especie, ruta]
    }

    // Buscar la ruta de una especie
    public String buscar(String especie) {
        int indice = hash(especie);
        Lista lista = tabla[indice];

        for (int i = 0; i < lista.getSize(); i++) {
            Object obj = lista.getValor(i);
            if (obj instanceof String[]) {
                String[] par = (String[]) obj;
                if (par[0].equalsIgnoreCase(especie)) {
                    return par[1]; // ruta
                }
            }
        }

        return null; // No encontrada
    }

    // Eliminar una especie
    public void eliminar(String especie) {
        int indice = hash(especie);
        Lista lista = tabla[indice];

        for (int i = 0; i < lista.getSize(); i++) {
            Object obj = lista.getValor(i);
            if (obj instanceof String[]) {
                String[] par = (String[]) obj;
                if (par[0].equalsIgnoreCase(especie)) {
                    lista.EliminarPorPosicion(i);
                    return;
                }
            }
        }
    }

    // Mostrar todo el contenido
    public void mostrarTabla() {
        for (int i = 0; i < capacidad; i++) {
            System.out.println("Índice " + i + ":");
            Lista lista = tabla[i];
            for (int j = 0; j < lista.getSize(); j++) {
                Object obj = lista.getValor(j);
                if (obj instanceof String[]) {
                    String[] par = (String[]) obj;
                    System.out.println("  Especie: " + par[0] + " | Ruta: " + par[1]);
                }
            }
        }
    }
}

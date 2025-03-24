/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author juanp
 */
public class Arbol {
    private NodoArbol raiz;

    public Arbol() {
        this.raiz = null;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    // Método para insertar manualmente sin estructuras externas
    public void insertarDesdeRuta(String[] preguntas, boolean[] respuestas, String especie) {
        if (preguntas.length != respuestas.length) {
            System.out.println("Error: preguntas y respuestas deben tener el mismo tamaño.");
            return;
        }

        if (preguntas.length == 0) {
            System.out.println("Error: ruta vacía.");
            return;
        }

        if (raiz == null) {
            raiz = new NodoArbol(preguntas[0]);
        }

        NodoArbol actual = raiz;

        for (int i = 1; i < preguntas.length; i++) {
            if (respuestas[i - 1]) {
                if (actual.getSi() == null) {
                    actual.setSi(new NodoArbol(preguntas[i]));
                }
                actual = actual.getSi();
            } else {
                if (actual.getNo() == null) {
                    actual.setNo(new NodoArbol(preguntas[i]));
                }
                actual = actual.getNo();
            }
        }

        // Nodo hoja con especie
        NodoArbol hoja = new NodoArbol(null, especie);
        if (respuestas[preguntas.length - 1]) {
            actual.setSi(hoja);
        } else {
            actual.setNo(hoja);
        }
    }

    // Buscar una especie por nombre
    public String buscarEspecie(String nombre) {
        return buscarEspecie(raiz, nombre, "");
    }

    private String buscarEspecie(NodoArbol nodo, String nombre, String camino) {
        if (nodo == null) return null;

        if (nodo.esHoja()) {
            if (nodo.getEspecie().equalsIgnoreCase(nombre)) {
                return "Ruta: " + camino;
            } else {
                return null;
            }
        }

        String nuevaPregunta = nodo.getPregunta();

        String izquierda = buscarEspecie(nodo.getSi(), nombre, camino + nuevaPregunta + " = Sí -> ");
        if (izquierda != null) return izquierda;

        String derecha = buscarEspecie(nodo.getNo(), nombre, camino + nuevaPregunta + " = No -> ");
        if (derecha != null) return derecha;

        return null;
    }

    // Mostrar todas las especies
    public void mostrarEspecies() {
        mostrarEspecies(raiz);
    }

    private void mostrarEspecies(NodoArbol nodo) {
        if (nodo == null) return;

        if (nodo.esHoja()) {
            System.out.println("Especie: " + nodo.getEspecie());
        } else {
            mostrarEspecies(nodo.getSi());
            mostrarEspecies(nodo.getNo());
        }
    }
}

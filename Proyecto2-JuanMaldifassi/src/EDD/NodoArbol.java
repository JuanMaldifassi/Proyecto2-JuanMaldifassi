/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author juanp
 */
public class NodoArbol {
    private String pregunta;       // Pregunta actual
    private String especie;        // Si es una hoja, representa la especie
    private NodoArbol si;          // Rama "Sí"
    private NodoArbol no;          // Rama "No"

    // Constructor para nodo con pregunta
    public NodoArbol(String pregunta) {
        this.pregunta = pregunta;
        this.especie = null;
        this.si = null;
        this.no = null;
    }

    // Constructor para nodo hoja (especie)
    public NodoArbol(String pregunta, String especie) {
        this.pregunta = pregunta;
        this.especie = especie;
        this.si = null;
        this.no = null;
    }

    // Getters y Setters
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public NodoArbol getSi() {
        return si;
    }

    public void setSi(NodoArbol si) {
        this.si = si;
    }

    public NodoArbol getNo() {
        return no;
    }

    public void setNo(NodoArbol no) {
        this.no = no;
    }

    public boolean esHoja() {
        return especie != null;
    }
}

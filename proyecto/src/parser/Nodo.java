
package parser;

import java.util.ArrayList;

public class Nodo {
    private String nombre;
    private String valor;
    private int numNodo;
    private String identifier; 
    private int location;
    private ArrayList<Nodo> hijos;
    public String resultado; 
    
    public Nodo(String nombre)
    {
        setNombre(nombre);
        hijos = new ArrayList<>();
        //setValor("");
        setNumNodo(0);
    }
    
    public void addHijo(Nodo hijo)
    {
        hijos.add(hijo);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the hijos
     */
    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the numNodo
     */
    public int getNumNodo() {
        return numNodo;
    }

    /**
     * @param numNodo the numNodo to set
     */
    public void setNumNodo(int numNodo) {
        this.numNodo = numNodo;
    }

    /**
     * @return the identifier
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return the location
     */
    public int getLocation() {
        return this.location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(int location) {
        this.location = location;
    }
}

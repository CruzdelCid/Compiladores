/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

/**
 *
 * @author Oscar
 */
public class TipoDato {
    private String tipo;
    private int entero;
    private double decimal;
    private String tstring;
    private char tchar;
    private boolean booleano;

    public TipoDato(String tipo){
        this.setTipo(tipo);
    }
    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the entero
     */
    public int getEntero() {
        return entero;
    }

    /**
     * @param entero the entero to set
     */
    public void setEntero(int entero) {
        this.entero = entero;
    }

    /**
     * @return the decimal
     */
    public double getDecimal() {
        return decimal;
    }

    /**
     * @param decimal the decimal to set
     */
    public void setDecimal(double decimal) {
        this.decimal = decimal;
    }

    /**
     * @return the tstring
     */
    public String getTstring() {
        return tstring;
    }

    /**
     * @param tstring the tstring to set
     */
    public void setTstring(String tstring) {
        this.tstring = tstring;
    }

    /**
     * @return the tchar
     */
    public char getTchar() {
        return tchar;
    }

    /**
     * @param tchar the tchar to set
     */
    public void setTchar(char tchar) {
        this.tchar = tchar;
    }

    /**
     * @return the booleano
     */
    public boolean getBooleano() {
        return booleano;
    }

    /**
     * @param booleano the booleano to set
     */
    public void setBooleano(boolean booleano) {
        this.booleano = booleano;
    }
}

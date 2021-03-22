
package com.bank.dominio;

public class Banco {
    
    private int idBanco;
    private String nombre;

    public Banco() {
    }

    public Banco(int idBanco, String nombre) {
        this.idBanco = idBanco;
        this.nombre = nombre;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Banco{" + "idBanco=" + idBanco + ", nombre=" + nombre + '}';
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idBanco;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Banco other = (Banco) obj;
        if (this.idBanco != other.idBanco) {
            return false;
        }
        return true;
    }
    
    
    
}

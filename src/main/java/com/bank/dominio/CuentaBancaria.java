package com.bank.dominio;

public class CuentaBancaria {
    private int idCuenta;
    private String iban;
    private double saldo;
    private int idCliente;

    public CuentaBancaria() {
    }

    public CuentaBancaria(int idCuenta, String iban, double saldo, int idCliente) {
        this.idCuenta = idCuenta;
        this.iban = iban;
        this.saldo = saldo;
        this.idCliente = idCliente;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "idCuenta=" + idCuenta + ", iban=" + iban + ", saldo=" + saldo + ", idCliente=" + idCliente + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idCuenta;
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
        final CuentaBancaria other = (CuentaBancaria) obj;
        if (this.idCuenta != other.idCuenta) {
            return false;
        }
        return true;
    }
    
    
}

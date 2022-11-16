package com.example.imcsqlite;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class IMC {

    private String dataCalculo;
    private Double peso;
    private Double altura;
    private Double imc;


    public IMC() {
    }

    public IMC(String data, Double peso, Double altura) {
        this.dataCalculo = data;
        this.peso = peso;
        this.altura = altura;
        this.imc = 0.0;
    }

    public String getDataCalculo() {
        return dataCalculo;
    }

    public void setDataCalculo(String dataCalculo) {
        this.dataCalculo = dataCalculo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getImc() {
        return imc;
    }

    public double calcImc(double altura, double peso){
        this.imc = peso/Math.pow(altura, 2);
        return this.imc;
    }
}

package br.com.fcschelb.diab.modelo;

import java.io.Serializable;

public class Alimento implements Serializable{

    private Long id;
    private String descricao;
    private String porcao;
    private String carboidratos;
    private String fibras;

    public Alimento() {
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPorcao() {
        return porcao;
    }

    public String getCarboidratos() {
        return carboidratos;
    }

    public String getFibras() {
        return fibras;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPorcao(String porcao) {
        this.porcao = porcao;
    }

    public void setCarboidratos(String carboidratos) {
        this.carboidratos = carboidratos;
    }

    public void setFibras(String fibras) {
        this.fibras = fibras;
    }

    @Override
    public String toString() {
        return getDescricao();
    }
}

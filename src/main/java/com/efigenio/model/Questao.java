package com.efigenio.model;

import java.util.List;

public class Questao {
    private String enunciado;
    private List<String> alternativas;
    private Boolean correto;

    public Questao(String enunciado, List<String> alternativas) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.correto = false;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getQuestao(int pos) {
        return this.alternativas.get(pos);
    }

    public List<String> getAlternativas() {
        return this.alternativas;
    }

    public void setAlternativas(List<String> alternativas) {
        this.alternativas = alternativas;
    }

    public void setAlternativa(String alternativa) {
        this.alternativas.add(alternativa);
    }

    public Boolean getCorreto() {
        return correto;
    }

    public void setCorreto(Boolean correto) {
        this.correto = correto;
    }
}

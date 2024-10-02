package com.efigenio.controller;

import java.util.List;

import com.efigenio.model.Questao;

public class GameController {

    private List<Questao> questoes;
    private int acerto;
    private int erro;
    private Questao questaoAtual;

    public GameController(List<Questao> questoes, int acerto, int erro, Questao questaoAtual) {
        this.questoes = questoes;
        this.acerto = acerto;
        this.erro = erro;
        this.setQuestaoAtual(questaoAtual);
    }

    public void reiniciar() {
        this.acerto = 0;
        this.erro = 0;
        this.questoes.clear();
    }

    public int getQuantidadeQuestoes() {
        return this.questoes.size();
    }

    public void setQuestaoAtual(Questao questaoAtual) {
        this.questaoAtual = questaoAtual;
    }


    public List<Questao> getQuestoes(){
        return this.questoes;
    }

    public Questao getQuestao(){
        return this.questaoAtual;
    }

}

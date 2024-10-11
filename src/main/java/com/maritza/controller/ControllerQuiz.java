package com.maritza.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.maritza.model.Questao;

public class ControllerQuiz {
    private List<Questao> questoes;
    private int questaoAtual;
    private int acertos;
    private int erros;

    public ControllerQuiz(List<Questao> questoes) {
        this.questoes = new ArrayList<>(questoes);
        reiniciar();
    }

    public void reiniciar() {
        acertos = 0;
        erros = 0;
        questaoAtual = 0;
        Collections.shuffle(questoes);
        embaralharAlternativas();
    }
    
    private void embaralharAlternativas() {
        for (Questao questao : questoes) {
            questao.embaralharAlternativas();
        }
    }

    public int getTotalQuestao() {
        return questoes.size();
    }

    public boolean temProximaQuestao() {
        return questaoAtual < getTotalQuestao();
    }

    public void proximaQuestao() {
        if (temProximaQuestao()) {
            questaoAtual++;
        }
    }

    public boolean responderQuestao(String alternativa) {
        Questao questaoAtual = getQuestao();
        boolean respostaCorreta = questaoAtual.getRespostaCorreta().equals(alternativa);
        if (respostaCorreta) {
            acertos++;
        } else {
            erros++;
        }
        return respostaCorreta;
    }

    public Questao getQuestao() {
        if (questaoAtual >= getTotalQuestao()) {
            throw new IndexOutOfBoundsException("Não há mais perguntas disponíveis.");
        }
        return questoes.get(questaoAtual);
    }

    public List<Questao> getQuestoes() {
        return Collections.unmodifiableList(questoes);
    }

    public int getQuestaoAtual() {
        return questaoAtual;
    }

    public int getAcertos() {
        return acertos;
    }

    public int getErros() {
        return erros;
    }
}
package com.maritza.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Questao {
    private final String enunciado;
    private final String respostaCorreta;
    private final List<String> outrasAlternativas;
    private final List<String> todasAlternativas;

    public Questao(String enunciado, String respostaCorreta, String[] outrasAlternativas) {
        this.enunciado = enunciado;
        this.respostaCorreta = respostaCorreta;
        this.outrasAlternativas = Arrays.asList(outrasAlternativas);
        this.todasAlternativas = new ArrayList<>(this.outrasAlternativas);
        this.todasAlternativas.add(respostaCorreta);
        Collections.shuffle(this.todasAlternativas);
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public List<String> getOutrasAlternativas() {
        return outrasAlternativas;
    }

    public List<String> getTodasAlternativas() {
        return Collections.unmodifiableList(todasAlternativas);
    }

    public void embaralharAlternativas() {
        Collections.shuffle(this.todasAlternativas);
    }

    @Override
    public String toString() {
        return enunciado + "|" + respostaCorreta + "|" + String.join("|", outrasAlternativas);
    }
}
package com.efigenio.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class TelaJogo {

    @FXML
    private Button alternativa1;

    @FXML
    private Button alternativa2;

    @FXML
    private Button alternativa3;

    @FXML
    private Button alternativa4;

    @FXML
    private Label enunciado;

    @FXML
    void respondeQuestao(ActionEvent event) {
        System.out.println("Cliquei aqui");
    }

}

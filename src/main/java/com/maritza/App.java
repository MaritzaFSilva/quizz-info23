package com.maritza;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.maritza.controller.ControllerQuiz;
import com.maritza.model.Questao;

/**
 * JavaFX App
 */

public class App extends Application {
    private ControllerQuiz controllerQuiz;

    private Button continuar = new Button("Continuar");
    private Text pergunta = new Text("Pergunta ");
    private Text enunciado = new Text("Enunciado");
    private Button alternativa1 = new Button("alternativa");
    private Button alternativa2 = new Button("alternativa2");
    private Button alternativa3 = new Button("alternativa3");
    private Button alternativa4 = new Button("alternativa4");
    private Button alternativa5 = new Button("alternativa5");
    private Button reiniciar = new Button("Reiniciar");

    private Text acerto = new Text("Acertos: ");
    private Text erro = new Text("Erros: ");

    @Override
    public void init() throws Exception {
        ArrayList<Questao> lista = new ArrayList<>();

        lista.add(new Questao("Quais os dias da aula de OO?", "Quarta-feira",
                new String[] { "Segunda-feira", "Terça-feira", "Quinta-feira", "Sexta-feira" }));
        lista.add(new Questao("em qual mês iniciou a greve?", "abril",
                new String[] { "maio", "fevereiro", "dezembro", "janeiro" }));
        lista.add(new Questao("Qual a cor favorita da prof?", "Rosa",
                new String[] { "laranja", "verde", "roxo", "vermelho" }));
        lista.add(new Questao("Qual a melhor turma de informática?", "Info 23",
                new String[] { "Info 24", "Info 22", "Info 21", "Info 20" }));

        controllerQuiz = new ControllerQuiz(lista);
        controllerQuiz.reiniciar();
        Questao questao = controllerQuiz.getQuestao();
        atualizarQuestao(questao);
    }

    @Override
    public void start(Stage stage) throws IOException {

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        aplicarEstilos();

        root.getChildren().addAll(pergunta, enunciado, alternativa1, alternativa2, alternativa3, alternativa4,
                alternativa5, acerto, erro, continuar, reiniciar);

        continuar.setVisible(false);
        acerto.setVisible(false);
        erro.setVisible(false);
        reiniciar.setVisible(false);

        alternativa1.setOnAction(this::respondeQuestao);
        alternativa2.setOnAction(this::respondeQuestao);
        alternativa3.setOnAction(this::respondeQuestao);
        alternativa4.setOnAction(this::respondeQuestao);
        alternativa5.setOnAction(this::respondeQuestao);
        continuar.setOnAction(this::continuar);
        reiniciar.setOnAction(this::reiniciar);

        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void respondeQuestao(Event event) {
        boolean acertou = controllerQuiz.responderQuestao(((Button) event.getSource()).getText());
        continuar.setVisible(true);

        atualizarEstadoBotoes(false);

        ((Button) event.getSource()).getStyleClass().add(acertou ? "acerto" : "erro");
    }

    private void continuar(Event event) {
        continuar.setVisible(false);
        controllerQuiz.proximaQuestao();

        atualizarEstadoBotoes(true);

        aplicarEstilos();

        if (!controllerQuiz.temProximaQuestao()) {
            pergunta.setVisible(false);
            enunciado.setVisible(false);
            alternativa1.setVisible(false);
            alternativa2.setVisible(false);
            alternativa3.setVisible(false);
            alternativa4.setVisible(false);
            alternativa5.setVisible(false);
            continuar.setVisible(false);
            acerto.setVisible(true);
            erro.setVisible(true);
            reiniciar.setVisible(true);
            atualizarEstadoBotoes(true);

            acerto.setText("Acertos: " + controllerQuiz.getAcertos());
            erro.setText("Erros: " + controllerQuiz.getErros());

            return;
        }

        Questao questao = controllerQuiz.getQuestao();
        atualizarQuestao(questao);
    }

    private void reiniciar(Event event) {
        atualizarEstadoBotoes(true);
        pergunta.setVisible(true);
        enunciado.setVisible(true);
        alternativa1.setVisible(true);
        alternativa2.setVisible(true);
        alternativa3.setVisible(true);
        alternativa4.setVisible(true);
        alternativa5.setVisible(true);
        acerto.setVisible(false);
        erro.setVisible(false);
        reiniciar.setVisible(false);

        controllerQuiz.reiniciar();

        Questao questao = controllerQuiz.getQuestao();
        atualizarQuestao(questao);
    }

    private void atualizarQuestao(Questao questao) {
        pergunta.setText("Pergunta " + (controllerQuiz.getQuestaoAtual() + 1) + "/" + controllerQuiz.getTotalQuestao());
        enunciado.setText(questao.getEnunciado());
        List<String> alternativas = questao.getTodasAlternativas();
        alternativa1.setText(alternativas.size() > 0 ? alternativas.get(0) : "");
        alternativa2.setText(alternativas.size() > 1 ? alternativas.get(1) : "");
        alternativa3.setText(alternativas.size() > 2 ? alternativas.get(2) : "");
        alternativa4.setText(alternativas.size() > 3 ? alternativas.get(3) : "");
        alternativa5.setText(alternativas.size() > 4 ? alternativas.get(4) : "");
    }

    private void atualizarEstadoBotoes(boolean habilitar) {
        alternativa1.setDisable(!habilitar);
        alternativa2.setDisable(!habilitar);
        alternativa3.setDisable(!habilitar);
        alternativa4.setDisable(!habilitar);
        alternativa5.setDisable(!habilitar);
    }

    private void aplicarEstilos() {
        alternativa1.getStyleClass().remove("acerto");
        alternativa1.getStyleClass().remove("erro");
        alternativa2.getStyleClass().remove("acerto");
        alternativa2.getStyleClass().remove("erro");
        alternativa3.getStyleClass().remove("acerto");
        alternativa3.getStyleClass().remove("erro");
        alternativa4.getStyleClass().remove("acerto");
        alternativa4.getStyleClass().remove("erro");
        alternativa5.getStyleClass().remove("acerto");
        alternativa5.getStyleClass().remove("erro");

        alternativa1.getStyleClass().add("botao");
        alternativa2.getStyleClass().add("botao");
        alternativa3.getStyleClass().add("botao");
        alternativa4.getStyleClass().add("botao");
        alternativa5.getStyleClass().add("botao");
        continuar.getStyleClass().add("botao");
        reiniciar.getStyleClass().add("botao");

        pergunta.getStyleClass().add("texto");
        enunciado.getStyleClass().add("texto");
        acerto.getStyleClass().add("texto");
        erro.getStyleClass().add("texto");
    }

    public static void main(String[] args) {
        launch();
    }

}
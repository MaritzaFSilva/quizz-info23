package com.efigenio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.efigenio.controller.GameController;
import com.efigenio.model.Questao;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    private GameController gameController;
    private VBox root;
    private Text enunciado;
    private Text resultadoText;
    private Button alternativa1;
    private Button alternativa2;
    private Button alternativa3;
    private Button alternativa4;

    @Override
    public void start(Stage stage) {

        inicializaComponentes();
        atualizaComponente();

        Scene cena = new Scene(root, 800, 600);
        stage.setScene(cena);
        stage.setTitle("Quiz Game");
        stage.show();
    }
   
    @Override
    public void init() throws Exception {
        super.init();

        List<Questao> questoes = new ArrayList<>();
        
        questoes.add(new Questao("Quantos Anos a Prof/Professora tem?",
                new ArrayList<>(Arrays.asList("20", "30", "26", "28"))));

        questoes.add(new Questao("Quando ocorre a aula de POO",
                new ArrayList<>(Arrays.asList("Segunda", "Terca", "Quarta", "Quinta"))));

        
        gameController = new GameController(questoes, 0, 0, questoes.get(0));


    }

    private void inicializaComponentes(){
        root = new VBox(10);
        enunciado = new Text("Pergunta 0/0");
        resultadoText = new Text("");
        alternativa1 = new Button();
        alternativa2 = new Button();
        alternativa3 = new Button();
        alternativa4 = new Button();

        alternativa1.setText("Alternativa 1");
        alternativa2.setText("Alternativa 2");
        alternativa3.setText("Alternativa 3");
        alternativa4.setText("Alternativa 4");

        alternativa1.setOnAction(this::respondeQuestao);
        alternativa2.setOnAction(this::respondeQuestao);
        alternativa3.setOnAction(this::respondeQuestao);
        alternativa4.setOnAction(this::respondeQuestao);

        root.getChildren().add(enunciado);
        root.getChildren().add(alternativa1);
        root.getChildren().add(alternativa2);
        root.getChildren().add(alternativa3);
        root.getChildren().add(alternativa4);
        root.getChildren().add(resultadoText);
        root.setAlignment(Pos.CENTER);


    }

    private void atualizaComponente(){

        Questao questaoAtual = gameController.getQuestao();

        enunciado.setText(questaoAtual.getEnunciado());

        List<String> alternativas = questaoAtual.getAlternativas();
        //Maneira não recomendada!
        alternativa1.setText(alternativas.get(0));
        alternativa2.setText(alternativas.get(1));
        alternativa3.setText(alternativas.get(2));
        alternativa4.setText(alternativas.get(3));
    }


    private void respondeQuestao(Event event){
        System.out.println("Cliquei no botão");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
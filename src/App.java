import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import javafx.scene.paint.Color;

import java.util.Stack;

public class App extends Application {

    private TextField display;
    private String historico = "0";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BatCalculadora");

        // layout
        GridPane teclado = new GridPane();
        teclado.setAlignment(Pos.CENTER);
        teclado.setHgap(10);
        teclado.setVgap(10);
        teclado.setPadding(new Insets(25, 25, 25, 25));

        // estilo
        // teclado.setStyle("-fx-background-color: gray;");

        // visor
        display = new TextField();
        display.setEditable(false);
        // display.setStyle("-fx-text-fill: white;");
        // display.setStyle("-fx-background-color: gray;");
        teclado.add(display, 0, 0, 4, 1);

        // botões
        String[][] botoes = {
                { "y\u221Ax", "x^y", "C", "Del" },
                { "%", "(", ")", "/" },
                { "7", "8", "9", "*" },
                { "4", "5", "6", "-" },
                { "1", "2", "3", "+" },
                { "ans", "0", ".", "=" }
        };

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                Button btn = new Button(botoes[i][j]);
                btn.setMinWidth(50);
                // btn.setStyle("-fx-text-fill: white; -fx-background-color: black;");
                btn.setOnAction(e -> handleButtonClick(btn.getText()));
                teclado.add(btn, j, i + 1);
            }
        }
        Scene scene = new Scene(teclado, 250, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(String value) {
        switch (value) {
            case "=":
                calcular();
                break;
            case "C":
                historico = display.getText();
                display.setText("");
                break;
            case "ans":
                display.appendText(historico);
                break;
            case "y\u221Ax":
                display.appendText("\u221A");
                break;
            case "x^y":
                display.appendText("^");
                break;
            case "Del":
                if (!display.getText().isEmpty()) {
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                }
                break;
            default:
                display.setText(display.getText() + value);
                break;
        }
    }

    private void calcular() {
        String expressao = display.getText();
        Stack<String> tokens = new Stack<>();
        StringBuilder token1 = new StringBuilder();

        // particionar
        for (char c : expressao.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                token1.append(c);
            } else {
                if (token1.length() > 0) {
                    tokens.push(token1.toString());
                    token1.setLength(0);
                }
                tokens.push(String.valueOf(c));
            }
        }
        if (token1.length() > 0) {
            tokens.push(token1.toString());
        }

        // converter
        Stack<RPN.RPNElement> rpnExpression = RPN.inFixToRPN(tokens);

        // calcular
        double resultado;
        try {
            resultado = RPN.calculateRPN(rpnExpression);
            if (Double.isNaN(resultado)) {
                display.setText("Erro: Divisão por zero ou raiz quadrada de número negativo");
            } else {
                display.setText(String.valueOf(resultado));
            }
        } catch (ArithmeticException e) {
            display.setText("Erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            display.setText("Erro: Operador inválido: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
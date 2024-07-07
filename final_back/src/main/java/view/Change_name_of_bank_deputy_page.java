package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class Change_name_of_bank_deputy_page extends VBox {
    public Change_name_of_bank_deputy_page() throws FileNotFoundException {

        Label einmenschlabel = new Label("DEPUTY") ;
        einmenschlabel.setStyle("-fx-font-size: 30px ; -fx-text-fill: rgba(255,255,255,0.93) ;");

        Label new_name = new Label("new name : ") ;
        TextField new_bank_deputy_name_textfild = new TextField();
        new_bank_deputy_name_textfild.setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");

        Label sslable = new Label("") ;

        Label passlable = new Label("new password : ") ;
        PasswordField new_bank_deputy_passowrd_filde = new PasswordField();

        Label accpasslable = new Label("re-enter the password : ") ;
        PasswordField acc_new_bank_ceo_passowrd_filde = new PasswordField();
        Button Apply_butto = new Button("APPLY");
        Apply_butto.setPrefSize(60 , 30);
        Apply_butto.setStyle("-fx-font-size: 10px ; -fx-font-family: Arial ;-fx-background-color: rgba(20,126,183,0.93) ;-fx-background-radius: 10px;");

        Apply_butto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String x = new_bank_deputy_name_textfild.getText();
                int c = parseInt(new_bank_deputy_passowrd_filde.getText());
                if (new_bank_deputy_passowrd_filde.getText().equals(acc_new_bank_ceo_passowrd_filde.getText()) && acc_new_bank_ceo_passowrd_filde.getText().equals(new_bank_deputy_passowrd_filde.getText())) {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                        PreparedStatement statement = connection.prepareStatement("UPDATE deputy SET deputyname = ? , deputypassword = ? WHERE id = '1'");
                        statement.setString(1, x);
                        statement.setInt(2, c);
                        statement.executeUpdate() ;

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    sslable.setText("REGISTERED");
                    sslable.setTextFill(Color.BLUE);
                }else {
                    sslable.setText("ERROR");
                    sslable.setTextFill(Color.RED);
                }
            }
        });

        Image image = new Image(new FileInputStream("c:/test/final_back/ch.jpg")) ;
        BackgroundSize backgroundSize = new BackgroundSize(100 , 100 , true , true , true , false) ;
        BackgroundImage backgroundImage = new BackgroundImage(image , BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.CENTER , backgroundSize) ;
        Background background = new Background(backgroundImage) ;
        this.setBackground(background);
        this.setPrefSize(480 , 300);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(4);
        this.getChildren().addAll(einmenschlabel , new_name , new_bank_deputy_name_textfild , sslable , passlable , new_bank_deputy_passowrd_filde , accpasslable , acc_new_bank_ceo_passowrd_filde , Apply_butto) ;

    }
}

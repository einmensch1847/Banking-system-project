package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class Loan_page extends VBox {
    public Loan_page() throws FileNotFoundException {
        Login_page loginPage = new Login_page() ;

        Label label1 = new Label("requested loan amount : ") ;
        TextField textField1 = new TextField() ;
        textField1.setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");

        Label label2 = new Label("") ;

        Label label3 = new Label("description : ") ;
        TextField textField3 = new TextField() ;
        textField3.setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");

        Label label4 = new Label("") ;

        Button ok_b = new Button("OK") ;
        ok_b.setPrefSize(30 , 30);
        ok_b.setStyle("-fx-font-size: 10px ; -fx-font-family: Arial ;-fx-background-color: rgba(56,175,116,0.75) ;-fx-background-radius: 10px;");

        ok_b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO loan_comments (username , balance , mony , comment , bankaccountnumber) VALUES (? , ? , ? , ? , ?)") ;
                    statement.setString(1 , loginPage.getUsername_for_userspanel());
                    statement.setInt(2 , loginPage.getUsers_bace_balance_for_userpanel());
                    statement.setInt(3 , Integer.parseInt(textField1.getText()));
                    statement.setString(4 , textField3.getText());
                    statement.setInt(5 , loginPage.getUsers_bank_acc_num_for_userspanel());
                    statement.executeUpdate() ;

                    label4.setText("SENT");
                    label4.setTextFill(Color.GREEN);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        });



        Label label = new Label("Please note that when applying for a loan, the balance and account number and\n some of your information except the password will be sent to the bank staff") ;
        label.setTextFill(Color.RED);

        Image image = new Image(new FileInputStream("c:/test/final_back/ch.jpg")) ;
        BackgroundSize backgroundSize = new BackgroundSize(100 , 100 , true , true , true , false) ;
        BackgroundImage backgroundImage = new BackgroundImage(image , BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.CENTER , backgroundSize) ;
        Background background = new Background(backgroundImage) ;
        this.setBackground(background);
        this.setPrefSize(480 , 300);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(4);
        this.getChildren().addAll(label1 , textField1 , label2 , label3 , textField3 , label4 , ok_b , label) ;


    }
}

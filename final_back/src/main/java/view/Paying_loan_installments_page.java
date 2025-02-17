package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class Paying_loan_installments_page extends VBox {
    public Paying_loan_installments_page() throws FileNotFoundException {

        Login_page loginPage = new Login_page() ;

        int x = 0 ;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?") ;
            statement.setString(1 , loginPage.getUsername_for_userspanel());
            statement.setInt(2 , loginPage.getUsers_pass_for_userpanel());
            ResultSet resultSet = statement.executeQuery() ;
            while (resultSet.next()){
                x = resultSet.getInt("balance") ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        int y = 0 ;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accepted_loans WHERE username = ? AND bankaccountnumber = ?") ;
            statement.setString(1 , loginPage.getUsername_for_userspanel());
            statement.setInt(2 , loginPage.getUsers_bank_acc_num_for_userspanel());
            ResultSet resultSet = statement.executeQuery() ;
            while (resultSet.next()){
                y = resultSet.getInt("money") ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Label labely = new Label("debt to the bank : "+y+"$") ;
        labely.setTextFill(Color.RED);

        Label label1 = new Label("amount : ") ;

        TextField textField = new TextField() ;
        textField.setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");

        Button button = new Button("OK") ;
        button.setPrefSize(30 , 30);
        button.setStyle("-fx-font-size: 10px ; -fx-font-family: Arial ;-fx-background-color: rgba(56,175,116,0.75) ;-fx-background-radius: 10px;");

        int finalX = x;
        int finalY = y;
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(finalX > Integer.parseInt(textField.getText())){
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                        PreparedStatement statement = connection.prepareStatement("UPDATE users SET balance = ? WHERE id = ?");
                        statement.setInt(1, finalX - Integer.parseInt(textField.getText()));
                        statement.setInt(2, loginPage.getUsers_id_for_user_panel());
                        statement.executeUpdate();

                        PreparedStatement statement1 = connection.prepareStatement("UPDATE accepted_loans SET money = ? WHERE bankaccountnumber = ?");
                        statement1.setInt(1, finalY - Integer.parseInt(textField.getText()));
                        statement1.setInt(2, loginPage.getUsers_bank_acc_num_for_userspanel());
                        statement1.executeUpdate();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("spaying loan installments");
                    alert.setHeaderText(null);
                    alert.setContentText("You have successfully transferred "+ textField.getText() +"$ \nto the bank and this message is your payment receipt");
                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("paying loan installments");
                    alert.setHeaderText(null);
                    alert.setContentText("Your balance is not enough to pay "+ textField.getText() +"$ to the bank");
                    alert.showAndWait();
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
        this.getChildren().addAll(labely , label1 , textField , button ) ;

    }
}

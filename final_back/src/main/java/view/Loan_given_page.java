package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class Loan_given_page extends VBox {
    public Loan_given_page() throws FileNotFoundException {

        Label einmenschlabel = new Label("\" LOAN GIVEN \"") ;
        einmenschlabel.setStyle("-fx-font-size: 30px ;");

        VBox vBox = new VBox();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM accepted_loans");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int balance = resultSet.getInt("balance");
                int money = resultSet.getInt("money");
                String comment = resultSet.getString("comment");
                int bankaccountnumber = resultSet.getInt("bankaccountnumber");

                Label label = new Label(id + "  :  " + username + "  " + bankaccountnumber + "   with account balance (int time ) : " + balance + "$" + "\nrequested loan amount : " + money + "$   " + "description : " + comment);

                Button Dbutton = new Button("DELETE");
                Dbutton.setTextFill(Color.WHITE);
                Dbutton.setPrefSize(70 , 30);
                Dbutton.setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(189,64,64,0.85) ;-fx-background-radius: 10px;");

                Dbutton.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                Dbutton.setTextFill(Color.WHITE);

                final int finalId = id;
                final String finalUsername = username;
                final int finalBalance = balance;
                final int finalMoney = money;
                final String finalComment = comment;

                Dbutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Stage stage = (Stage) Dbutton.getScene().getWindow() ;
                        stage.close();

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("deleted");
                        alert.setHeaderText(null);
                        alert.setContentText("deleted");
                        alert.showAndWait();
                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM accepted_loans WHERE id = ? AND username = ? AND balance = ? AND money = ? AND comment = ?");
                            preparedStatement.setInt(1, finalId);
                            preparedStatement.setString(2, finalUsername);
                            preparedStatement.setInt(3, finalBalance);
                            preparedStatement.setInt(4, finalMoney);
                            preparedStatement.setString(5, finalComment);
                            preparedStatement.executeUpdate();

                            vBox.getChildren().remove(label);


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });

                HBox hBox = new HBox(label , Dbutton) ;
                hBox.setAlignment(Pos.CENTER);

                vBox.getChildren().add(hBox) ;

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        Image image = new Image(new FileInputStream("c:/test/final_back/ch.jpg")) ;
        BackgroundSize backgroundSize = new BackgroundSize(100 , 100 , true , true , true , false) ;
        BackgroundImage backgroundImage = new BackgroundImage(image , BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.CENTER , backgroundSize) ;
        Background background = new Background(backgroundImage) ;
        this.setBackground(background);
        this.setPrefSize(800 , 500);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(4);
        this.getChildren().addAll(einmenschlabel , vBox ) ;
    }
}

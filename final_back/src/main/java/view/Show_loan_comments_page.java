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

public class Show_loan_comments_page extends VBox {
    public Show_loan_comments_page() throws FileNotFoundException {
        Label slabel1 = new Label("   ") ;
        Label slabel2 = new Label("   ") ;

        Label einmenschlabel = new Label("\" LOAN COMMENTS \"") ;
        einmenschlabel.setStyle("-fx-font-size: 30px ;");


        VBox vBox = new VBox();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM loan_comments");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int balance = resultSet.getInt("balance");
                int money = resultSet.getInt("mony");
                String comment = resultSet.getString("comment");
                int bankaccountnumber = resultSet.getInt("bankaccountnumber") ;

                Label label = new Label(id + "  :  " + username + "  "+bankaccountnumber+"   with account balance : " + balance + "$" + "\nrequested loan amount : " + money + "$   " + "description : " + comment);

                Button Dbutton = new Button("DELETE");
                Dbutton.setPrefSize(70 , 30);
                Dbutton.setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(189,64,64,0.85) ;-fx-background-radius: 10px;");

                Button Abutton = new Button("ACCEPT");
                Abutton.setPrefSize(70 , 30);
                Abutton.setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(24,84,29,0.85) ;-fx-background-radius: 10px;");

                Dbutton.setTextFill(Color.WHITE);

                Abutton.setTextFill(Color.WHITE);

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
                        alert.setContentText("loan comment was deleted");
                        alert.showAndWait();
                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM loan_comments WHERE id = ? AND username = ? AND balance = ? AND mony = ? AND comment = ?");
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


//                        final String finalUsername = username;
//                        final int finalBalance = balance;
                final int finalBankAccountNumber = bankaccountnumber;

                Abutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Stage stage = (Stage) Abutton.getScene().getWindow() ;
                        stage.close();

                        int x = finalBalance + finalMoney;
                        try {
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                            PreparedStatement statement1 = connection.prepareStatement("UPDATE users SET balance = ? WHERE username = ? AND bankaccountnumber = ?");
                            statement1.setInt(1, x);
                            statement1.setString(2, finalUsername);
                            statement1.setInt(3, finalBankAccountNumber);
                            int rowsUpdated = statement1.executeUpdate();

                            if (rowsUpdated > 0) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("ACCEPTED");
                                alert.setHeaderText(null);
                                alert.setContentText("Loan accepted");
                                alert.showAndWait();
                            } else {
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM loan_comments WHERE id = ? AND username = ? AND balance = ? AND mony = ? AND comment = ?");
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
                        try {
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO accepted_loans (id , username , balance , money , comment , bankaccountnumber) VALUES (? , ? , ? , ? , ? ,?)") ;
                            preparedStatement.setInt(1, finalId);
                            preparedStatement.setString(2 , finalUsername);
                            preparedStatement.setInt(3 , finalBalance);
                            preparedStatement.setInt(4 , finalMoney);
                            preparedStatement.setString(5 , finalComment);
                            preparedStatement.setInt(6 , finalBankAccountNumber);
                            preparedStatement.executeUpdate() ;

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }


                    }
                });


                HBox hBox = new HBox(label, slabel1 , Dbutton , slabel2 , Abutton);
                hBox.setAlignment(Pos.CENTER);

                vBox.getChildren().add(hBox);
            }
        } catch (SQLException e) {
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

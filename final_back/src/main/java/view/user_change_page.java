package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class user_change_page extends VBox {
    PasswordField new_user_pass ;
    public user_change_page() throws FileNotFoundException {
        Login_page loginPage = new Login_page() ;
        Label passlable = new Label("new password : ") ;
        passlable.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-text-fill: rgba(57,58,63,0.69) ;");
        Label accpasslable = new Label("re-enter the password : ") ;
        accpasslable.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-text-fill: rgba(57,58,63,0.69) ;");

        new_user_pass = new PasswordField();
        PasswordField new_user_pass2 = new PasswordField();

        Button Apply_butto = new Button("\" APPLY \"");
        Apply_butto.setStyle("-fx-font-size: 8px ; -fx-font-family: Arial ;-fx-background-color: rgba(0,79,255,0.88) ;-fx-background-radius: 10px;-fx-text-fill: rgba(255,251,251,0.93) ;");
        Apply_butto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Integer.parseInt(new_user_pass.getText())==Integer.parseInt(new_user_pass2.getText())){
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                        PreparedStatement statement = connection.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
                        statement.setInt(1, Integer.parseInt(new_user_pass2.getText()));
                        statement.setInt(2, loginPage.getUsers_id_for_user_panel());
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SET NEW PASSWORD");
                    alert.setHeaderText(null);
                    alert.setContentText("your new password is : "+Integer.parseInt(new_user_pass.getText()));
                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("SET NEW PASSWORD");
                    alert.setHeaderText(null);
                    alert.setContentText("ERROR");
                    alert.showAndWait();
                }
            }
        });


        VBox vBox1 = new VBox(passlable , accpasslable) ;
        vBox1.setSpacing(26);
        vBox1.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox(new_user_pass , new_user_pass2) ;
        vBox2.setSpacing(20);
        vBox2.setAlignment(Pos.CENTER);

        Label label = new Label("SET NEW PASSWORD") ;
        label.setStyle("-fx-font-size: 15px ;");

        HBox hBox = new HBox(vBox1 , vBox2) ;
        hBox.setSpacing(8);
        hBox.setAlignment(Pos.CENTER);

        Image image = new Image(new FileInputStream("c:/test/final_back/ch.jpg")) ;
        BackgroundSize backgroundSize = new BackgroundSize(100 , 100 , true , true , true , false) ;
        BackgroundImage backgroundImage = new BackgroundImage(image , BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.CENTER , backgroundSize) ;
        Background background = new Background(backgroundImage) ;
        this.setBackground(background);
        this.setPrefSize(480 , 300);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(label , hBox , Apply_butto) ;
    }
}

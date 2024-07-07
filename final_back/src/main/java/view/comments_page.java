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

public class comments_page extends VBox {
    public comments_page() throws FileNotFoundException {
        Login_page loginPage = new Login_page() ;

        Label label = new Label("COMMENT : ") ;
        label.setStyle("-fx-font-size: 15px ;");

        TextField Comment_textfield = new TextField() ;
        Comment_textfield.setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");
        Comment_textfield.setPrefWidth(1200);

        Label ok = new Label("") ;

        Button send_button = new Button("send") ;
        send_button.setPrefSize(60 , 30);
        send_button.setStyle("-fx-font-size: 10px ; -fx-font-family: Arial ;-fx-background-color: rgba(56,175,116,0.75) ;-fx-background-radius: 10px;");

        send_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO comments (text , user , accountnumber) VALUES (? , ? ,?)") ;
                    statement.setString(1 , Comment_textfield.getText());
                    statement.setString(2 , loginPage.getUsername_for_userspanel());
                    statement.setInt(3 , loginPage.getUsers_bank_acc_num_for_userspanel());
                    statement.executeUpdate() ;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ok.setText("sent");
                ok.setTextFill(Color.BLUE);
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
        this.getChildren().addAll(label , Comment_textfield , ok , send_button) ;

    }
}

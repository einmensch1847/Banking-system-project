package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class Show_comments_page extends VBox {
    public Show_comments_page() throws FileNotFoundException {

        Label einmenschlabel = new Label("\" COMMENTS \"") ;
        einmenschlabel.setStyle("-fx-font-size: 30px ;");

        VBox vBox = new VBox() ;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root" , "") ;
            Statement statement = connection.createStatement() ;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM comments") ;
            while (resultSet.next()){
                Label label = new Label(resultSet.getString("text")+" user :  "+resultSet.getString("user")+"account number :  "+resultSet.getInt("accountnumber")) ;
                Button button = new Button("DELETE") ;
                button.setPrefSize(70 , 30);
                button.setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(189,64,64,0.85) ;-fx-background-radius: 10px;");

                button.setBackground(Background.fill(Color.RED));
                button.setTextFill(Color.WHITE);
                String text = resultSet.getString("text") ;
                String user = resultSet.getString("user") ;
                int account = resultSet.getInt("accountnumber") ;

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Stage stage = (Stage) button.getScene().getWindow() ;
                        stage.close();
                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM comments WHERE text = ? AND user = ? AND accountnumber = ?") ;
                            preparedStatement.setString(1 , text);
                            preparedStatement.setString(2 , user);
                            preparedStatement.setInt(3 , account);
                            preparedStatement.executeUpdate() ;

                            vBox.getChildren().remove(label);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                HBox hBox = new HBox(label , button) ;
                hBox.setAlignment(Pos.CENTER);

                vBox.getChildren().add(hBox) ;
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
        this.getChildren().addAll(einmenschlabel , vBox) ;
    }
}

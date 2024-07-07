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
import java.sql.*;

public class New_employee_page extends VBox {
    public New_employee_page() throws FileNotFoundException {

        Label einmenschlabel = new Label("NEW EMPLOYEE") ;
        einmenschlabel.setStyle("-fx-font-size: 30px ; -fx-text-fill: rgba(255,255,255,0.93) ;");

        Label name_label = new Label("employee name :  ") ;
        TextField insert_employee_name_textfield = new TextField() ;
        insert_employee_name_textfield.setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");
        HBox hBox1 = new HBox(name_label , insert_employee_name_textfield) ;
        hBox1.setAlignment(Pos.CENTER);

        Label password_label = new Label("password :  ") ;
        TextField insert_employee_password_textfield = new TextField() ;
        HBox hBox2 = new HBox(password_label , insert_employee_password_textfield) ;
        hBox2.setAlignment(Pos.CENTER);

        Label slabel = new Label("") ;

        Button insert_new_employee_button = new Button("apply") ;
        insert_new_employee_button.setPrefSize(60 , 30);
        insert_new_employee_button.setStyle("-fx-font-size: 10px ; -fx-font-family: Arial ;-fx-background-color: rgba(20,126,183,0.93) ;-fx-background-radius: 10px;");

        insert_new_employee_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int employee_id = 0 ;
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root" , "") ;
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO employee (name , password) VALUES (? , ?)") ;
                    statement.setString(1 , insert_employee_name_textfield.getText());
                    statement.setInt(2 , Integer.parseInt(insert_employee_password_textfield.getText()));
                    statement.executeUpdate() ;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root" , "") ;
                    Statement statement = connection.createStatement() ;
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM employee") ;
                    while (resultSet.next()){
                        employee_id = resultSet.getInt("id") ;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                slabel.setText("A new employee was added the bank with ID : "+employee_id);
                slabel.setTextFill(Color.GREEN);

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
        this.getChildren().addAll(einmenschlabel , hBox1 , hBox2 , slabel , insert_new_employee_button) ;

    }
}

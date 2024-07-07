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

public class Money_transfer_page extends VBox {
    private static boolean action1 = false ;
    private static boolean action2 = false ;
    private static boolean action3 = false ;
    private static String user1_name = null ;
    private static String user2_name = null ;

    private static int user1_account_number = 0 ;
    private static int user2_account_number = 0 ;

    private static int user1_id = 0 ;
    private static int user2_id = 0 ;

    private static int user1_first_balance = 0 ;
    private static int user2_first_balance = 0 ;

    private static int user1_second_balance = 0 ;
    private static int user2_second_balance = 0 ;
    public Money_transfer_page() throws FileNotFoundException {
        Login_page loginPage = new Login_page() ;
        Label label1 = new Label("DESIRED ACCOUNT : ") ;
//        Label slabel1 = new Label("") ;
        Label slbl = new Label("") ;
        TextField textField1 = new TextField() ;
        textField1.setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");
        Button button1 = new Button("APPLY") ;


        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE bankaccountnumber = ?") ;
                    statement.setInt(1 , Integer.parseInt(textField1.getText()));
                    ResultSet resultSet = statement.executeQuery() ;
                    if(resultSet.next()){
//                        slabel1.setText("RIGHT");
//                        slabel1.setTextFill(Color.BLUE);

                        user1_name = resultSet.getString("username") ;
                        user1_account_number = resultSet.getInt("bankaccountnumber") ;
                        user1_id = resultSet.getInt("id") ;
                        user1_first_balance = resultSet.getInt("balance") ;
                        action1 = true ;

                        slbl.setText("account : "+user1_name);
                        slbl.setTextFill(Color.DARKBLUE);

                    }else {
                        slbl.setText("FALSE");
                        slbl.setTextFill(Color.RED);
                        action1 = false ;

                        slbl.setText("account not found");
                        slbl.setTextFill(Color.RED);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        Label label2 = new Label("      AMOUNT :   ") ;
        Label slabel2 = new Label("") ;
        TextField textField2 = new TextField() ;
        textField2.setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");
        Button button2 = new Button("APPLY") ;


        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Integer.parseInt(textField2.getText())<loginPage.getUsers_bace_balance_for_userpanel()){
                    slabel2.setText("RIGHT");
                    slabel2.setTextFill(Color.BLUE);
                    action2 = true ;
                }else {
                    slabel2.setText("Insufficient inventory");
                    slabel2.setTextFill(Color.RED);
                    action2 = false ;
                }
            }
        });


        Label label3 = new Label("    PASSWORD :   ") ;
        Label slabel3 = new Label("") ;
        TextField textField3 = new TextField() ;
        textField3.setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");
        Button button3 = new Button("APPLY") ;


        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Integer.parseInt(textField3.getText())==loginPage.getUsers_pass_for_userpanel()) {
                    user2_name = loginPage.getUsername_for_userspanel() ;
                    user2_account_number = loginPage.getUsers_bank_acc_num_for_userspanel() ;
                    user2_id = loginPage.getUsers_id_for_user_panel() ;
                    user2_first_balance = loginPage.getUsers_bace_balance_for_userpanel() ;
                    slabel3.setText("RIGHT");
                    slabel3.setTextFill(Color.BLUE);
                    action3 = true ;
                }else {
                    slabel3.setText("FALSE");
                    slabel3.setTextFill(Color.RED);
                    action3 = false ;
                }
            }
        });



        //*******************


        Button button = new Button("OK") ;
        Label final_label = new Label("") ;
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(action1 == true && action2 == true && action3 == true){


                    user1_second_balance = user1_first_balance + Integer.parseInt(textField2.getText()) ;
                    loginPage.setUsers_bace_balance_for_userpanel(user2_first_balance - Integer.parseInt(textField2.getText())) ;

                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                        PreparedStatement statement = connection.prepareStatement("UPDATE users SET balance = ? WHERE id = ? AND username = ? AND bankaccountnumber = ? ") ;
                        statement.setInt(1 , user1_second_balance);
                        statement.setInt(2 , user1_id);
                        statement.setString(3 , user1_name);
                        statement.setInt(4 , user1_account_number);
                        statement.executeUpdate() ;
                        PreparedStatement statement1 = connection.prepareStatement("UPDATE users SET balance = ? WHERE id = ? AND username = ? AND bankaccountnumber = ? ") ;
                        statement1.setInt(1 , user2_first_balance - Integer.parseInt(textField2.getText()));
                        statement1.setInt(2 , user2_id);
                        statement1.setString(3 , user2_name);
                        statement1.setInt(4 , user2_account_number);
                        statement1.executeUpdate() ;

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    final_label.setText("The money transfer was successful");
                    final_label.setTextFill(Color.BLUE);
                }else {
                    final_label.setText("The money transfer was not successful");
                    final_label.setTextFill(Color.RED);
                }

            }
        });

        HBox hBox1 = new HBox(label1 , textField1 , button1) ;
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox(label2 , textField2 , button2) ;
        hBox2.setAlignment(Pos.CENTER);
        HBox hBox3 = new HBox(label3 , textField3 , button3) ;
        hBox3.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox(hBox1 , slbl , hBox2 , slabel2 , hBox3 , slabel3) ;
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setSpacing(15);

        Label label = new Label("MONET TRANSFER") ;
        label.setStyle("-fx-font-size: 15px ;");


        Image image = new Image(new FileInputStream("c:/test/final_back/ch.jpg")) ;
        BackgroundSize backgroundSize = new BackgroundSize(100 , 100 , true , true , true , false) ;
        BackgroundImage backgroundImage = new BackgroundImage(image , BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.CENTER , backgroundSize) ;
        Background background = new Background(backgroundImage) ;
        this.setBackground(background);
        this.setPrefSize(480 , 300);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(4);
        this.getChildren().addAll(label , vBox2 , button , final_label) ;
    }
}

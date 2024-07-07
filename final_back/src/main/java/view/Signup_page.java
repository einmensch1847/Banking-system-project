package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class Signup_page extends VBox {

    private Label acc_lbl ;
    private TextField name_textfield;
    private TextField last_name_textfield;
    private TextField fathers_name_textfield;
    private TextField national_code_textfield;
    private TextField username_textfirld;
    private TextField bank_account_number_textfield ;
    private PasswordField passwordField1;
    private PasswordField passwordField2 ;
    private Button signup_buttonn;
    private Button exit_button ;
    public Signup_page() throws FileNotFoundException {

        setSignup_buttonn(new Button());
        setExit_button(new Button());

        setAcc_lbl(new Label(""));

        Label wellcome_lbl = new Label("WELCOME TO EIN MENSCH SYSTEM") ;
        wellcome_lbl.setStyle("-fx-font-size: 20px ; -fx-font-family: Arial ;-fx-text-fill: rgba(142,160,206,0.93) ;");

        Label name_lbl = new Label("name : ") ;
        name_lbl.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-text-fill: rgba(255,255,255,0.93) ;");
        Label last_name_lbl = new Label("last name : ") ;
        last_name_lbl.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-text-fill: rgba(255,255,255,0.93) ;");
        Label fathers_name_lbl = new Label("fathers name : ") ;
        fathers_name_lbl.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-text-fill: rgba(255,255,255,0.93) ;");
        Label national_code_lbl = new Label("national code : ") ;
        national_code_lbl.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-text-fill: rgba(255,255,255,0.93) ;");
        Label username_lbl = new Label("username : ") ;
        username_lbl.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-text-fill: rgba(255,255,255,0.93) ;");
        Label password1_lbl = new Label("password : ") ;
        password1_lbl.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-text-fill: rgba(255,255,255,0.93) ;");
        Label password2_lbl = new Label("repeat password : ") ;
        password2_lbl.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-text-fill: rgba(255,255,255,0.93) ;");
        Label bank_accountnumber_lbl = new Label("bank account number : ") ;
        bank_accountnumber_lbl.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-text-fill: rgba(255,255,255,0.93) ;");

        setName_textfield(new TextField());
        getName_textfield().setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");
        setLast_name_textfield(new TextField());
        getLast_name_textfield().setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");
        setFathers_name_textfield(new TextField());
        getFathers_name_textfield().setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");
        setNational_code_textfield(new TextField());
        getNational_code_textfield().setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");
        setUsername_textfirld(new TextField());
        getUsername_textfirld().setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");
        setBank_account_number_textfield(new TextField());
        getBank_account_number_textfield().setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");
        setPasswordField1(new PasswordField());
        setPasswordField2(new PasswordField());

        VBox vBox1 = new VBox(name_lbl , last_name_lbl , fathers_name_lbl , national_code_lbl , username_lbl , password1_lbl , password2_lbl , bank_accountnumber_lbl) ;
        vBox1.setSpacing(26);
        vBox1.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox(getName_textfield(), getLast_name_textfield(), getFathers_name_textfield(), getNational_code_textfield(), getUsername_textfirld(), getPasswordField1(), getPasswordField2(), getBank_account_number_textfield()) ;
        vBox2.setSpacing(20);
        vBox2.setAlignment(Pos.CENTER);

        VBox vBox3 = new VBox(getSignup_buttonn(), getAcc_lbl()) ;
        vBox3.setSpacing(40);
        vBox3.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(vBox1 , vBox2 , vBox3) ;
        hBox.setSpacing(15);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox4 = new VBox(wellcome_lbl , hBox) ;
        vBox4.setSpacing(25);
        vBox4.setAlignment(Pos.CENTER);


        getSignup_buttonn().setText("\" SIGN UP \"");
        getSignup_buttonn().setPrefSize(200 , 100);
        getSignup_buttonn().setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-background-color: rgba(20,126,183,0.93) ;-fx-background-radius: 10px;");
        getSignup_buttonn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (getName_textfield().getText().isEmpty() && getLast_name_textfield().getText().isEmpty()&& getNational_code_textfield().getText().isEmpty()&& getPasswordField1().getText().isEmpty()&& getPasswordField2().getText().isEmpty() && getBank_account_number_textfield().getText().isEmpty()){
                    getAcc_lbl().setText("User registered not successfully"); ;
                    getAcc_lbl().setTextFill(Color.RED);
                }else{
                    String username = getUsername_textfirld().getText() ;
                    int password = parseInt(getPasswordField2().getText()) ;
                    int bankaccountnumber = parseInt(getBank_account_number_textfield().getText());
                    String name = getName_textfield().getText() ;
                    String lastname = getLast_name_textfield().getText() ;
                    String fathername = getFathers_name_textfield().getText() ;
                    int national_code = Integer.parseInt(getNational_code_textfield().getText()) ;
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root" , "") ;
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username , password , bankaccountnumber , balance , name , lastname , father_name , national_code) VALUES (? , ? , ? , '200' , ? , ? , ? , ?)") ;
                        statement.setString(1 , username);
                        statement.setInt(2 , password);
                        statement.setInt(3 , bankaccountnumber);
                        statement.setString(4 , name);
                        statement.setString(5 , lastname);
                        statement.setString(6 , fathername);
                        statement.setInt(7 , national_code);
                        statement.executeUpdate() ;

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    getAcc_lbl().setText("User registered successfully"); ;
                    getAcc_lbl().setTextFill(Color.GREEN);
                }
            }
        });


        getExit_button().setText("\" EXIT \"");
        getExit_button().setPrefSize(50 , 20);
        getExit_button().setStyle("-fx-font-size: 8px ; -fx-font-family: Arial ;-fx-background-color: rgba(183,107,107,0.93) ;-fx-background-radius: 10px;");
        getExit_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main_page mainPage = null;
                try {
                    mainPage = new Main_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(mainPage));
                stage1.show();

                Stage stage = (Stage) getExit_button().getScene().getWindow() ;
                stage.close();
            }
        });



        Image image = new Image(new FileInputStream("c:/test/final_back/sigenup_page.jpg")) ;
        BackgroundSize backgroundSize = new BackgroundSize(100 , 100 , true , true , true , false) ;
        BackgroundImage backgroundImage = new BackgroundImage(image , BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.CENTER , backgroundSize) ;
        Background background = new Background(backgroundImage) ;
        this.setBackground(background);

        this.setPrefSize(1130 , 780);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(vBox4 , getExit_button()) ;


    }

    public Label getAcc_lbl() {
        return acc_lbl;
    }

    public void setAcc_lbl(Label acc_lbl) {
        this.acc_lbl = acc_lbl;
    }

    public TextField getName_textfield() {
        return name_textfield;
    }

    public void setName_textfield(TextField name_textfield) {
        this.name_textfield = name_textfield;
    }

    public TextField getLast_name_textfield() {
        return last_name_textfield;
    }

    public void setLast_name_textfield(TextField last_name_textfield) {
        this.last_name_textfield = last_name_textfield;
    }

    public TextField getFathers_name_textfield() {
        return fathers_name_textfield;
    }

    public void setFathers_name_textfield(TextField fathers_name_textfield) {
        this.fathers_name_textfield = fathers_name_textfield;
    }

    public TextField getNational_code_textfield() {
        return national_code_textfield;
    }

    public void setNational_code_textfield(TextField national_code_textfield) {
        this.national_code_textfield = national_code_textfield;
    }

    public TextField getUsername_textfirld() {
        return username_textfirld;
    }

    public void setUsername_textfirld(TextField username_textfirld) {
        this.username_textfirld = username_textfirld;
    }

    public TextField getBank_account_number_textfield() {
        return bank_account_number_textfield;
    }

    public void setBank_account_number_textfield(TextField bank_account_number_textfield) {
        this.bank_account_number_textfield = bank_account_number_textfield;
    }

    public PasswordField getPasswordField1() {
        return passwordField1;
    }

    public void setPasswordField1(PasswordField passwordField1) {
        this.passwordField1 = passwordField1;
    }

    public PasswordField getPasswordField2() {
        return passwordField2;
    }

    public void setPasswordField2(PasswordField passwordField2) {
        this.passwordField2 = passwordField2;
    }

    public Button getSignup_buttonn() {
        return signup_buttonn;
    }

    public void setSignup_buttonn(Button signup_buttonn) {
        this.signup_buttonn = signup_buttonn;
    }

    public Button getExit_button() {
        return exit_button;
    }

    public void setExit_button(Button exit_button) {
        this.exit_button = exit_button;
    }
}

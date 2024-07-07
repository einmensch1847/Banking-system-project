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
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

import static java.lang.Integer.parseInt;

public class Login_page extends VBox {
    private static String users_name_for_user_panel = null ;
    private static String users_last_name_for_user_panel = null ;
    private static String username_for_userspanel = null ;
    private static int users_pass_for_userpanel = 0 ;
    private static int users_bank_acc_num_for_userspanel = 0 ;
    private static int users_bace_balance_for_userpanel = 0 ;
    private static int users_id_for_user_panel = 0 ;
    private static int employee_id = 0 ;
    private Label acc_lbl ;
    private TextField username_textfield ;
    private PasswordField passwordField ;
    private Button login_buttonn;
    private Button exit_button ;
    public Login_page() throws FileNotFoundException {
        setAcc_lbl(new Label(""));

        Label wellcome_lbl = new Label("WELCOME BACK TO THE EIN MENSCH SYSTEM") ;
        wellcome_lbl.setStyle("-fx-font-size: 20px ; -fx-font-family: Arial ;-fx-text-fill: rgba(142,160,206,0.93) ;");

        setLogin_buttonn(new Button());
        setExit_button(new Button());

        getLogin_buttonn().setText("\" LOGIN \"");
        getLogin_buttonn().setPrefSize(200 , 100);
        getLogin_buttonn().setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-background-color: rgba(20,126,183,0.93) ;-fx-background-radius: 10px;");
        getLogin_buttonn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String adminname = username_textfield.getText() ;
                int adminpassword = parseInt(passwordField.getText()) ;

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root" , "") ;
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM admin WHERE adminname = ? AND adminpassword = ?") ;
                    statement.setString(1 , adminname);
                    statement.setInt(2 , adminpassword);

                    ResultSet resultSet = statement.executeQuery() ;
                    if (resultSet.next()){
                        Admin_panel adminPanel = null;
                        try {
                            adminPanel = new Admin_panel();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage1 = new Stage() ;
                        stage1.setScene(new Scene(adminPanel));
                        stage1.show();

                        Stage stage = (Stage) getLogin_buttonn().getScene().getWindow() ;
                        stage.close();


                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String ceo_name = username_textfield.getText() ;
                int ceo_password = parseInt(passwordField.getText()) ;

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM ceo WHERE ceoname = ? AND ceopassword = ?");
                    statement.setString(1, ceo_name);
                    statement.setInt(2, ceo_password);

                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        CEO_panel ceoPanel = null;
                        try {
                            ceoPanel = new CEO_panel();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage1 = new Stage() ;
                        stage1.setScene(new Scene(ceoPanel));
                        stage1.show();

                        Stage stage = (Stage) getLogin_buttonn().getScene().getWindow() ;
                        stage.close();



                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }

                String deputyname = username_textfield.getText() ;
                int deputypassword = parseInt(passwordField.getText()) ;

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM deputy WHERE deputyname = ? AND deputypassword = ?");
                    statement.setString(1, deputyname);
                    statement.setInt(2, deputypassword);

                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        Deputy_panel deputyPanel = null;
                        try {
                            deputyPanel = new Deputy_panel();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage1 = new Stage() ;
                        stage1.setScene(new Scene(deputyPanel));
                        stage1.show();

                        Stage stage = (Stage) getLogin_buttonn().getScene().getWindow() ;
                        stage.close();
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }

                String usernameee = username_textfield.getText() ;
                int passworddd = parseInt(passwordField.getText()) ;

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root" , "") ;
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?") ;
                    statement.setString(1 , usernameee);
                    statement.setInt(2 , passworddd);

                    ResultSet resultSet = statement.executeQuery() ;

                    if (resultSet.next()){
                        setUsername_for_userspanel(username_textfield.getText());
                        setUsers_pass_for_userpanel(Integer.parseInt(passwordField.getText()));
                        setUsers_bank_acc_num_for_userspanel(resultSet.getInt("bankaccountnumber"));
                        setUsers_bace_balance_for_userpanel(resultSet.getInt("balance"));
                        setUsers_id_for_user_panel(resultSet.getInt("id"));
                        setUsers_name_for_user_panel(resultSet.getString("name"));
                        setUsers_last_name_for_user_panel(resultSet.getString("lastname"));

                        User_panel userPanel = null;
                        try {
                            userPanel = new User_panel();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage1 = new Stage() ;
                        stage1.setScene(new Scene(userPanel));
                        stage1.show();

                        Stage stage = (Stage) getLogin_buttonn().getScene().getWindow() ;
                        stage.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String employee_name = username_textfield.getText() ;
                int employee_password = parseInt(passwordField.getText()) ;

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root" , "") ;
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE name = ? AND password = ?") ;
                    statement.setString(1 , employee_name);
                    statement.setInt(2 , employee_password);
                    ResultSet resultSet1 = statement.executeQuery() ;
                    if (resultSet1.next()){
                        setEmployee_id(resultSet1.getInt("id"));

                        Employee_panel employeePanel = null;
                        try {
                            employeePanel = new Employee_panel();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage1 = new Stage() ;
                        stage1.setScene(new Scene(employeePanel));
                        stage1.show();

                        Stage stage = (Stage) getLogin_buttonn().getScene().getWindow() ;
                        stage.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
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




        setUsername_textfield(new TextField());
        getUsername_textfield().setStyle("-fx-background-color: rgba(153,187,206,0.93) ;-fx-background-radius: 10px;");
        setPasswordField(new PasswordField());

        Label name_lbl = new Label("name : ") ;
        name_lbl.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-text-fill: rgba(255,255,255,0.93) ;");
        Label password_lbl = new Label("password : ") ;
        password_lbl.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-text-fill: rgba(255,255,255,0.93) ;");


        Image image = new Image(new FileInputStream("c:/test/final_back/login_page.jpg")) ;
        BackgroundSize backgroundSize = new BackgroundSize(100 , 100 , true , true , true , false) ;
        BackgroundImage backgroundImage = new BackgroundImage(image , BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.CENTER , backgroundSize) ;
        Background background = new Background(backgroundImage) ;
        this.setBackground(background);

        VBox vBox1 = new VBox(name_lbl , password_lbl) ;
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(46);

        VBox vBox2 = new VBox(getUsername_textfield(), getPasswordField()) ;
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setSpacing(40);

        VBox vBox3 = new VBox(getLogin_buttonn()) ;
        vBox3.setAlignment(Pos.CENTER);
        vBox3.setSpacing(40);

        HBox hBox = new HBox(vBox1 , vBox2 , vBox3) ;
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(15);

        VBox vBox4 = new VBox(wellcome_lbl , hBox , getAcc_lbl(), getExit_button()) ;
        vBox4.setAlignment(Pos.CENTER);
        vBox4.setSpacing(25);


        this.setPrefSize(1130 , 780);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(vBox4) ;

    }

    public Label getAcc_lbl() {
        return acc_lbl;
    }

    public void setAcc_lbl(Label acc_lbl) {
        this.acc_lbl = acc_lbl;
    }

    public TextField getUsername_textfield() {
        return username_textfield;
    }

    public void setUsername_textfield(TextField username_textfield) {
        this.username_textfield = username_textfield;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public Button getLogin_buttonn() {
        return login_buttonn;
    }

    public void setLogin_buttonn(Button login_buttonn) {
        this.login_buttonn = login_buttonn;
    }

    public Button getExit_button() {
        return exit_button;
    }

    public void setExit_button(Button exit_button) {
        this.exit_button = exit_button;
    }

    public String getUsers_name_for_user_panel() {
        return users_name_for_user_panel;
    }

    public void setUsers_name_for_user_panel(String users_name_for_user_panel) {
        this.users_name_for_user_panel = users_name_for_user_panel;
    }

    public String getUsers_last_name_for_user_panel() {
        return users_last_name_for_user_panel;
    }

    public void setUsers_last_name_for_user_panel(String users_last_name_for_user_panel) {
        this.users_last_name_for_user_panel = users_last_name_for_user_panel;
    }

    public String getUsername_for_userspanel() {
        return username_for_userspanel;
    }

    public void setUsername_for_userspanel(String username_for_userspanel) {
        this.username_for_userspanel = username_for_userspanel;
    }

    public int getUsers_pass_for_userpanel() {
        return users_pass_for_userpanel;
    }

    public void setUsers_pass_for_userpanel(int users_pass_for_userpanel) {
        this.users_pass_for_userpanel = users_pass_for_userpanel;
    }

    public int getUsers_bank_acc_num_for_userspanel() {
        return users_bank_acc_num_for_userspanel;
    }

    public void setUsers_bank_acc_num_for_userspanel(int users_bank_acc_num_for_userspanel) {
        this.users_bank_acc_num_for_userspanel = users_bank_acc_num_for_userspanel;
    }

    public int getUsers_bace_balance_for_userpanel() {
        return users_bace_balance_for_userpanel;
    }

    public void setUsers_bace_balance_for_userpanel(int users_bace_balance_for_userpanel) {
        this.users_bace_balance_for_userpanel = users_bace_balance_for_userpanel;
    }

    public int getUsers_id_for_user_panel() {
        return users_id_for_user_panel;
    }

    public void setUsers_id_for_user_panel(int users_id_for_user_panel) {
        this.users_id_for_user_panel = users_id_for_user_panel;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
}

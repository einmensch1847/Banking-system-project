package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class CEO_panel extends VBox {
    private   int total_bank_balance = 40_000_000 ;
    private Button number_of_accounts_button;
    private Button net_bank_balance_button;
    private Button name_of_bank_ceo_button;
    private Button name_of_bank_deputy_button;
    private Button name_of_admin_button;
    private Button change_name_of_bank_ceo_button;
    private Button change_name_of_bank_deputy_button;
    private Button change_name_of_admin_button;
    private Button set_new_employee_button;
    private Button total_bank_balance_button;

    public CEO_panel() throws FileNotFoundException {

        Label einmenschlabel = new Label("EIN MENSCH") ;
        einmenschlabel.setStyle("-fx-font-size: 30px ; -fx-text-fill: rgba(255,255,255,0.93) ;");

        Label Alabel1 = new Label("Bank ceo has access to all program features") ;
        Alabel1.setTextFill(Color.BLUE);
        setTotal_bank_balance_button(new Button("\" BALANCE \""));
        getTotal_bank_balance_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("total bank balance");
                alert.setHeaderText(null);
                alert.setContentText(getTotal_bank_balance() +"$");
                alert.showAndWait();
            }
        });
        getTotal_bank_balance_button().setPrefSize(200 , 100);
        getTotal_bank_balance_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setNumber_of_accounts_button(new Button("\" number of accounts \""));
        getNumber_of_accounts_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int count = 0 ;
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root" , "") ;
                    Statement statement = connection.createStatement() ;
                    ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users") ;
                    if(resultSet.next()){
                        count = resultSet.getInt(1) ;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("number of accounts");
                alert.setHeaderText(null);
                alert.setContentText("**"+count+"**");
                alert.showAndWait();
            }
        });
        getNumber_of_accounts_button().setPrefSize(200 , 100);
        getNumber_of_accounts_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setNet_bank_balance_button(new Button("\" net bank balance \""));
        getNet_bank_balance_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int net_bank_balance = 40_000_000;
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject" , "root" , "");
                    Statement statement = connection.createStatement() ;
                    ResultSet resultSet = statement.executeQuery("SELECT SUM(balance) FROM users") ;
                    if(resultSet.next()){
                        net_bank_balance -= resultSet.getInt(1) ;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("net bank balance");
                alert.setHeaderText(null);
                alert.setContentText(net_bank_balance+"$");
                alert.showAndWait();
            }
        });
        getNet_bank_balance_button().setPrefSize(200 , 100);
        getNet_bank_balance_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");

        VBox vBox1 = new VBox(getTotal_bank_balance_button(), getNumber_of_accounts_button(), getNet_bank_balance_button()) ;
        vBox1.setSpacing(20);
        vBox1.setAlignment(Pos.CENTER);

        setName_of_bank_ceo_button(new Button("\" name of bank ceo \""));
        getName_of_bank_ceo_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject" , "root" , "");
                    Statement statement = connection.createStatement() ;
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM ceo") ;
                    while (resultSet.next()){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("banke ceo name");
                        alert.setHeaderText(null);
                        alert.setContentText(resultSet.getString("ceoname")+"\npassword : "+resultSet.getInt("ceopassword"));
                        alert.showAndWait();
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        getName_of_bank_ceo_button().setPrefSize(200 , 100);
        getName_of_bank_ceo_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setName_of_bank_deputy_button(new Button("\" name of bank deputy \""));
        getName_of_bank_deputy_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject" , "root" , "");
                    Statement statement = connection.createStatement() ;
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM deputy") ;
                    while (resultSet.next()){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("banke deputy name");
                        alert.setHeaderText(null);
                        alert.setContentText(resultSet.getString("deputyname")+"\npassword : "+resultSet.getInt("deputypassword"));
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        getName_of_bank_deputy_button().setPrefSize(200 , 100);
        getName_of_bank_deputy_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setName_of_admin_button(new Button("\" name of admin \""));
        getName_of_admin_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject" , "root" , "");
                    Statement statement = connection.createStatement() ;
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM admin") ;
                    while (resultSet.next()){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Admin name");
                        alert.setHeaderText(null);
                        alert.setContentText(resultSet.getString("adminname")+"\npassword : "+resultSet.getInt("adminpassword"));
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        getName_of_admin_button().setPrefSize(200 , 100);
        getName_of_admin_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");

        VBox vBox2 = new VBox(getName_of_bank_ceo_button(), getName_of_bank_deputy_button(), getName_of_admin_button()) ;
        vBox2.setSpacing(20);
        vBox2.setAlignment(Pos.CENTER);

        setChange_name_of_bank_ceo_button(new Button("\" change name of bank ceo \""));
        getChange_name_of_bank_ceo_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Change_name_of_bank_ceo_page changeNameOfBankCeoPage = null;
                try {
                    changeNameOfBankCeoPage = new Change_name_of_bank_ceo_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(changeNameOfBankCeoPage));
                stage1.show();
            }
        });
        getChange_name_of_bank_ceo_button().setPrefSize(200 , 100);
        getChange_name_of_bank_ceo_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setChange_name_of_bank_deputy_button(new Button("\" change deputy name \""));
        getChange_name_of_bank_deputy_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Change_name_of_bank_deputy_page changeNameOfBankDeputyPage = null;
                try {
                    changeNameOfBankDeputyPage = new Change_name_of_bank_deputy_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(changeNameOfBankDeputyPage));
                stage1.show();
            }
        });
        getChange_name_of_bank_deputy_button().setPrefSize(200 , 100);
        getChange_name_of_bank_deputy_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setChange_name_of_admin_button(new Button("\" set new admin \""));
        getChange_name_of_admin_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Set_new_admin_page setNewAdminPage = null;
                try {
                    setNewAdminPage = new Set_new_admin_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(setNewAdminPage));
                stage1.show();
            }
        });
        getChange_name_of_admin_button().setPrefSize(200 , 100);
        getChange_name_of_admin_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");

        VBox vBox3 = new VBox(getChange_name_of_bank_ceo_button(), getChange_name_of_bank_deputy_button(), getChange_name_of_admin_button()) ;
        vBox3.setSpacing(20);
        vBox3.setAlignment(Pos.CENTER);

        HBox hBox1 = new HBox(vBox1 , vBox2 , vBox3) ;
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.CENTER);

        setSet_new_employee_button(new Button("\" set new employee \""));
        getSet_new_employee_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                New_employee_page newEmployeePage = null;
                try {
                    newEmployeePage = new New_employee_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(newEmployeePage));
                stage1.show();
            }
        });
        getSet_new_employee_button().setPrefSize(200 , 100);
        getSet_new_employee_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        Button Exit_button = new Button() ;
        Exit_button.setText("\" EXIT \"");
        Exit_button.setPrefSize(200 , 100);
        Exit_button.setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(183,107,107,0.93) ;-fx-background-radius: 10px;");
        Exit_button.setOnAction(new EventHandler<ActionEvent>() {
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

                Stage stage = (Stage) Exit_button.getScene().getWindow() ;
                stage.close();
            }
        });

        HBox hBox2 = new HBox(getSet_new_employee_button(), Exit_button) ;
        hBox2.setSpacing(20);
        hBox2.setAlignment(Pos.CENTER);



        Image image = new Image(new FileInputStream("c:/test/final_back/user_panel.jpg")) ;
        BackgroundSize backgroundSize = new BackgroundSize(100 , 100 , true , true , true , false) ;
        BackgroundImage backgroundImage = new BackgroundImage(image , BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.CENTER , backgroundSize) ;
        Background background = new Background(backgroundImage) ;
        this.setBackground(background);
        this.setPrefSize(1130 , 780);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(einmenschlabel , Alabel1 , hBox1 , hBox2) ;
    }

    public int getTotal_bank_balance() {
        return total_bank_balance;
    }

    public void setTotal_bank_balance(int total_bank_balance) {
        this.total_bank_balance = total_bank_balance;
    }

    public Button getNumber_of_accounts_button() {
        return number_of_accounts_button;
    }

    public void setNumber_of_accounts_button(Button number_of_accounts_button) {
        this.number_of_accounts_button = number_of_accounts_button;
    }

    public Button getNet_bank_balance_button() {
        return net_bank_balance_button;
    }

    public void setNet_bank_balance_button(Button net_bank_balance_button) {
        this.net_bank_balance_button = net_bank_balance_button;
    }

    public Button getName_of_bank_ceo_button() {
        return name_of_bank_ceo_button;
    }

    public void setName_of_bank_ceo_button(Button name_of_bank_ceo_button) {
        this.name_of_bank_ceo_button = name_of_bank_ceo_button;
    }

    public Button getName_of_bank_deputy_button() {
        return name_of_bank_deputy_button;
    }

    public void setName_of_bank_deputy_button(Button name_of_bank_deputy_button) {
        this.name_of_bank_deputy_button = name_of_bank_deputy_button;
    }

    public Button getName_of_admin_button() {
        return name_of_admin_button;
    }

    public void setName_of_admin_button(Button name_of_admin_button) {
        this.name_of_admin_button = name_of_admin_button;
    }

    public Button getChange_name_of_bank_ceo_button() {
        return change_name_of_bank_ceo_button;
    }

    public void setChange_name_of_bank_ceo_button(Button change_name_of_bank_ceo_button) {
        this.change_name_of_bank_ceo_button = change_name_of_bank_ceo_button;
    }

    public Button getChange_name_of_bank_deputy_button() {
        return change_name_of_bank_deputy_button;
    }

    public void setChange_name_of_bank_deputy_button(Button change_name_of_bank_deputy_button) {
        this.change_name_of_bank_deputy_button = change_name_of_bank_deputy_button;
    }

    public Button getChange_name_of_admin_button() {
        return change_name_of_admin_button;
    }

    public void setChange_name_of_admin_button(Button change_name_of_admin_button) {
        this.change_name_of_admin_button = change_name_of_admin_button;
    }

    public Button getSet_new_employee_button() {
        return set_new_employee_button;
    }

    public void setSet_new_employee_button(Button set_new_employee_button) {
        this.set_new_employee_button = set_new_employee_button;
    }

    public Button getTotal_bank_balance_button() {
        return total_bank_balance_button;
    }

    public void setTotal_bank_balance_button(Button total_bank_balance_button) {
        this.total_bank_balance_button = total_bank_balance_button;
    }
}

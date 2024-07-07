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

public class Employee_panel extends VBox {
    private Button change_name_of_bank_employee_button;
    private Button name_of_bank_employee_button;
    private Button show_loan_comments_button;
    private Button Loans_given_button;
    private Button net_bank_balance_button;
    private Button total_bank_balance_button;
    private Button number_of_accounts_button;
    private Button show_comments_button;

    public Employee_panel() throws FileNotFoundException {
        CEO_panel ceoPanel = new CEO_panel() ;

        Login_page loginPage = new Login_page() ;

        Label einmenschlabel = new Label("EIN MENSCH") ;
        einmenschlabel.setStyle("-fx-font-size: 30px ; -fx-text-fill: rgba(255,255,255,0.93) ;");

        Label Alabel1 = new Label("employee has access to all program features") ;
        Alabel1.setTextFill(Color.BLUE);

        setShow_comments_button(new Button("\" show comments \""));
        getShow_comments_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Show_comments_page showCommentsPage = null;
                try {
                    showCommentsPage = new Show_comments_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(showCommentsPage));
                stage1.show();
            }
        });
        getShow_comments_button().setPrefSize(200 , 100);
        getShow_comments_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setShow_loan_comments_button(new Button("\" show loan comments \""));
        getShow_loan_comments_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Show_loan_comments_page showLoanCommentsPage = null;
                try {
                    showLoanCommentsPage = new Show_loan_comments_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(showLoanCommentsPage));
                stage1.show();
            }
        });
        getShow_loan_comments_button().setPrefSize(200 , 100);
        getShow_loan_comments_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setLoans_given_button(new Button("\" Loans given \""));
        getLoans_given_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Loan_given_page loanGivenPage = null;
                try {
                    loanGivenPage = new Loan_given_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(loanGivenPage));
                stage1.show();
            }
        });
        getLoans_given_button().setPrefSize(200 , 100);
        getLoans_given_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");

        VBox vBox1 = new VBox(getShow_comments_button(), getShow_loan_comments_button(), getLoans_given_button()) ;
        vBox1.setSpacing(20);
        vBox1.setAlignment(Pos.CENTER);

        setTotal_bank_balance_button(new Button("\" BALANCE \""));
        getTotal_bank_balance_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("total bank balance");
                alert.setHeaderText(null);
                alert.setContentText(ceoPanel.getTotal_bank_balance()+"$");
                alert.showAndWait();
            }
        });
        getTotal_bank_balance_button().setPrefSize(200 , 100);
        getTotal_bank_balance_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
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

        VBox vBox2 = new VBox(getTotal_bank_balance_button(), getNet_bank_balance_button(), getNumber_of_accounts_button()) ;
        vBox2.setSpacing(20);
        vBox2.setAlignment(Pos.CENTER);

        setName_of_bank_employee_button(new Button("\" name of employee "+loginPage.getEmployee_id()+" \""));
        getName_of_bank_employee_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject" , "root" , "");
                    PreparedStatement statement = connection.prepareStatement("SELECT name , password FROM employee WHERE id = ?") ;
                    statement.setInt(1 , loginPage.getEmployee_id());
                    ResultSet resultSet = statement.executeQuery() ;
                    while (resultSet.next()){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("employee name");
                        alert.setHeaderText(null);
                        alert.setContentText(resultSet.getString("name")+"\npassword : "+resultSet.getInt("password"));
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        getName_of_bank_employee_button().setPrefSize(200 , 100);
        getName_of_bank_employee_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setChange_name_of_bank_employee_button(new Button("\" change name of employee "+loginPage.getEmployee_id()+" \""));
        getChange_name_of_bank_employee_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Change_name_of_Employee_page changeNameOfEmployeePage = null;
                try {
                    changeNameOfEmployeePage = new Change_name_of_Employee_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(changeNameOfEmployeePage));
                stage1.show();
            }
        });
        getChange_name_of_bank_employee_button().setPrefSize(200 , 100);
        getChange_name_of_bank_employee_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(77,100,204,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        Button Exit_button = new Button("\" EXIT \"") ;
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

        VBox vBox3 = new VBox(getName_of_bank_employee_button(), getChange_name_of_bank_employee_button(), Exit_button) ;
        vBox3.setSpacing(20);
        vBox3.setAlignment(Pos.CENTER);

        HBox hBox1 = new HBox(vBox1 , vBox2 , vBox3) ;
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.CENTER);


        Image image = new Image(new FileInputStream("c:/test/final_back/user_panel.jpg")) ;
        BackgroundSize backgroundSize = new BackgroundSize(100 , 100 , true , true , true , false) ;
        BackgroundImage backgroundImage = new BackgroundImage(image , BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.CENTER , backgroundSize) ;
        Background background = new Background(backgroundImage) ;
        this.setBackground(background);
        this.setPrefSize(1130 , 780);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(einmenschlabel ,Alabel1 , hBox1) ;
    }

    public Button getChange_name_of_bank_employee_button() {
        return change_name_of_bank_employee_button;
    }

    public void setChange_name_of_bank_employee_button(Button change_name_of_bank_employee_button) {
        this.change_name_of_bank_employee_button = change_name_of_bank_employee_button;
    }

    public Button getName_of_bank_employee_button() {
        return name_of_bank_employee_button;
    }

    public void setName_of_bank_employee_button(Button name_of_bank_employee_button) {
        this.name_of_bank_employee_button = name_of_bank_employee_button;
    }

    public Button getShow_loan_comments_button() {
        return show_loan_comments_button;
    }

    public void setShow_loan_comments_button(Button show_loan_comments_button) {
        this.show_loan_comments_button = show_loan_comments_button;
    }

    public Button getLoans_given_button() {
        return Loans_given_button;
    }

    public void setLoans_given_button(Button loans_given_button) {
        Loans_given_button = loans_given_button;
    }

    public Button getNet_bank_balance_button() {
        return net_bank_balance_button;
    }

    public void setNet_bank_balance_button(Button net_bank_balance_button) {
        this.net_bank_balance_button = net_bank_balance_button;
    }

    public Button getTotal_bank_balance_button() {
        return total_bank_balance_button;
    }

    public void setTotal_bank_balance_button(Button total_bank_balance_button) {
        this.total_bank_balance_button = total_bank_balance_button;
    }

    public Button getNumber_of_accounts_button() {
        return number_of_accounts_button;
    }

    public void setNumber_of_accounts_button(Button number_of_accounts_button) {
        this.number_of_accounts_button = number_of_accounts_button;
    }

    public Button getShow_comments_button() {
        return show_comments_button;
    }

    public void setShow_comments_button(Button show_comments_button) {
        this.show_comments_button = show_comments_button;
    }
}

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
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class User_panel extends VBox {

    private Button money_transfer_button;
    private Button show_accunt_balance_button;
    private Button help_the_bank_button;
    private Button loan;
    private Button comments;
    private Button paying_loan_installments;
    private Button user_change;
    private Button delete_accunt ;
    public User_panel() throws FileNotFoundException {
        Label einmenschlabel = new Label("EIN MENSCH") ;
        einmenschlabel.setStyle("-fx-font-size: 30px ;");

        Login_page loginPage = new Login_page() ;

        Label username_label = new Label("username : "+loginPage.getUsername_for_userspanel()) ;
        username_label.setStyle("-fx-font-size: 20px ; -fx-font-family: Arial ;-fx-text-fill: rgba(89,126,119,0.88) ;");
        Label bankaccountnumber_label = new Label("Bank account number : "+loginPage.getUsers_bank_acc_num_for_userspanel()) ;
        bankaccountnumber_label.setStyle("-fx-font-size: 20px ; -fx-font-family: Arial ;-fx-text-fill: rgba(89,126,119,0.88) ;");
        Label name_and_lastname_label = new Label("User : "+loginPage.getUsers_name_for_user_panel()+"  "+loginPage.getUsers_last_name_for_user_panel()) ;
        name_and_lastname_label.setStyle("-fx-font-size: 20px ; -fx-font-family: Arial ;-fx-text-fill: rgba(89,126,119,0.88) ;");
        Label ID_label = new Label("your ID : "+loginPage.getUsers_id_for_user_panel()) ;
        ID_label.setStyle("-fx-font-size: 20px ; -fx-font-family: Arial ;-fx-text-fill: rgba(89,126,119,0.88) ;");
        Label s_label1 = new Label("   ") ;
        Label s_label2 = new Label("   ") ;

        VBox vBox1 = new VBox(s_label1 , username_label , bankaccountnumber_label , name_and_lastname_label , ID_label , s_label2) ;
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(20);
        vBox1.setStyle("-fx-background-color: rgba(44,64,114,0.69) ; -fx-background-radius: 10px; -fx-background-size: 100,100");


        setMoney_transfer_button(new Button("money transfer"));
        getMoney_transfer_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Money_transfer_page moneyTransferPage = null;
                try {
                    moneyTransferPage = new Money_transfer_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(moneyTransferPage));
                stage1.show();
            }
        });
        getMoney_transfer_button().setPrefSize(200 , 100);
        getMoney_transfer_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(11,28,107,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setShow_accunt_balance_button(new Button("show accunt balance"));
        getShow_accunt_balance_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int x = 0 ;
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?") ;
                    statement.setString(1 , loginPage.getUsername_for_userspanel());
                    statement.setInt(2 , loginPage.getUsers_pass_for_userpanel());
                    ResultSet resultSet = statement.executeQuery() ;
                    while (resultSet.next()){
                        x = resultSet.getInt("balance") ;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("show accunt balance");
                alert.setHeaderText(null);
                alert.setContentText(x+"$");
                alert.showAndWait();
            }
        });
        getShow_accunt_balance_button().setPrefSize(200 , 100);
        getShow_accunt_balance_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(41,62,157,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ;");
        setHelp_the_bank_button(new Button("help the bank"));
        getHelp_the_bank_button().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                help_the_bank_page helpTheBankPage = null;
                try {
                    helpTheBankPage = new help_the_bank_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(helpTheBankPage));
                stage1.show();
            }
        });
        getHelp_the_bank_button().setPrefSize(200 , 100);
        getHelp_the_bank_button().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(82,103,190,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ;");

        VBox vBox2 = new VBox(getMoney_transfer_button(), getShow_accunt_balance_button(), getHelp_the_bank_button()) ;
        vBox2.setSpacing(30);
        vBox2.setAlignment(Pos.CENTER);

        setLoan(new Button("loan"));
        getLoan().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Loan_page loanPage = null;
                try {
                    loanPage = new Loan_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(loanPage));
                stage1.show();


            }
        });
        getLoan().setPrefSize(200 , 100);
        getLoan().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(24,91,8,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setComments(new Button("comments"));
        getComments().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                comments_page comments_page = null;
                try {
                    comments_page = new comments_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(comments_page));
                stage1.show();
            }
        });
        getComments().setPrefSize(200 , 100);
        getComments().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(61,128,45,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setPaying_loan_installments(new Button("paying loan installments"));
        getPaying_loan_installments().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Paying_loan_installments_page payingLoanInstallmentsPage = null;
                try {
                    payingLoanInstallmentsPage = new Paying_loan_installments_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(payingLoanInstallmentsPage));
                stage1.show();
            }
        });
        getPaying_loan_installments().setPrefSize(200 , 100);
        getPaying_loan_installments().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(90,157,73,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");

        VBox vBox3 = new VBox(getLoan(), getComments(), getPaying_loan_installments()) ;
        vBox3.setSpacing(30);
        vBox3.setAlignment(Pos.CENTER);

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
        setUser_change(new Button(" CHANGE PASSWORD "));
        getUser_change().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user_change_page userChangePage = null;
                try {
                    userChangePage = new user_change_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(userChangePage));
                stage1.show();
            }
        });
        getUser_change().setPrefSize(200 , 100);
        getUser_change().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(203,70,16,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");
        setDelete_accunt(new Button("DELETE ACCOUNT"));
        getDelete_accunt().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "");
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE username = ? AND password = ? AND id = ?");
                    statement.setString(1, loginPage.getUsername_for_userspanel());
                    statement.setInt(2, loginPage.getUsers_pass_for_userpanel());
                    statement.setInt(3, loginPage.getUsers_id_for_user_panel());

                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        Main_page mainPage = null ;
                        try {
                            mainPage = new Main_page();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage1 = new Stage() ;
                        stage1.setScene(new Scene(mainPage));
                        stage1.show();

                        Stage stage = (Stage) getDelete_accunt().getScene().getWindow() ;
                        stage.close();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Delete Account");
                        alert.setHeaderText(null);
                        alert.setContentText("Account deleted");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Delete Account");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to delete account");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        getDelete_accunt().setPrefSize(200 , 100);
        getDelete_accunt().setStyle("-fx-font-size: 13px ; -fx-font-family: Arial ;-fx-background-color: rgba(173,86,52,0.75) ;-fx-background-radius: 10px;-fx-text-fill:rgba(253,253,253,0.69) ; ");

        VBox vBox4 = new VBox(getUser_change(), getDelete_accunt(), Exit_button) ;
        vBox4.setSpacing(30);
        vBox4.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(vBox2 , vBox3 , vBox4) ;
        hBox.setSpacing(30);
        hBox.setAlignment(Pos.CENTER);


        Image image = new Image(new FileInputStream("c:/test/final_back/user_panel.jpg")) ;
        BackgroundSize backgroundSize = new BackgroundSize(100 , 100 , true , true , true , false) ;
        BackgroundImage backgroundImage = new BackgroundImage(image , BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.CENTER , backgroundSize) ;
        Background background = new Background(backgroundImage) ;
        this.setBackground(background);
        this.setPrefSize(1130 , 780);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(30);
        this.getChildren().addAll(einmenschlabel , vBox1 , hBox) ;
    }

    public Button getMoney_transfer_button() {
        return money_transfer_button;
    }

    public void setMoney_transfer_button(Button money_transfer_button) {
        this.money_transfer_button = money_transfer_button;
    }

    public Button getShow_accunt_balance_button() {
        return show_accunt_balance_button;
    }

    public void setShow_accunt_balance_button(Button show_accunt_balance_button) {
        this.show_accunt_balance_button = show_accunt_balance_button;
    }

    public Button getHelp_the_bank_button() {
        return help_the_bank_button;
    }

    public void setHelp_the_bank_button(Button help_the_bank_button) {
        this.help_the_bank_button = help_the_bank_button;
    }

    public Button getLoan() {
        return loan;
    }

    public void setLoan(Button loan) {
        this.loan = loan;
    }

    public Button getComments() {
        return comments;
    }

    public void setComments(Button comments) {
        this.comments = comments;
    }

    public Button getPaying_loan_installments() {
        return paying_loan_installments;
    }

    public void setPaying_loan_installments(Button paying_loan_installments) {
        this.paying_loan_installments = paying_loan_installments;
    }

    public Button getUser_change() {
        return user_change;
    }

    public void setUser_change(Button user_change) {
        this.user_change = user_change;
    }

    public Button getDelete_accunt() {
        return delete_accunt;
    }

    public void setDelete_accunt(Button delete_accunt) {
        this.delete_accunt = delete_accunt;
    }
}

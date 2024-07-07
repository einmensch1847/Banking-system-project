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

public class Admin_panel extends VBox {
    public Admin_panel() throws FileNotFoundException {

        Label einmenschlabel = new Label("EIN MENSCH") ;
        einmenschlabel.setStyle("-fx-font-size: 30px ; -fx-text-fill: rgba(255,255,255,0.93) ;");

        Label Alabel1 = new Label("Admin has access to all program features") ;
        Alabel1.setTextFill(Color.BLUE);

        CEO_panel ceoPanel = new CEO_panel() ;
        Deputy_panel deputyPanel = new Deputy_panel() ;
        Employee_panel employeePanel = new Employee_panel() ;

        VBox vBox1 = new VBox(deputyPanel.getShow_comments_button() , deputyPanel.getShow_loan_comments_button() , deputyPanel.getLoans_given_button() ) ;
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(20);
        VBox vBox2 = new VBox(ceoPanel.getTotal_bank_balance_button() , ceoPanel.getNet_bank_balance_button() , ceoPanel.getNumber_of_accounts_button()) ;
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setSpacing(20);
        VBox vBox3 = new VBox(ceoPanel.getName_of_bank_ceo_button() , ceoPanel.getName_of_admin_button() , ceoPanel.getName_of_bank_deputy_button()) ;
        vBox3.setAlignment(Pos.CENTER);
        vBox3.setSpacing(20);
        VBox vBox4 = new VBox(ceoPanel.getChange_name_of_bank_ceo_button() , ceoPanel.getChange_name_of_admin_button() , ceoPanel.getChange_name_of_bank_deputy_button()) ;
        vBox4.setAlignment(Pos.CENTER);
        vBox4.setSpacing(20);

        HBox hBox = new HBox(vBox1 , vBox2 , vBox3 , vBox4) ;
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);

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

        HBox hBox1 = new HBox(ceoPanel.getSet_new_employee_button() , Exit_button ) ;
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
        this.getChildren().addAll(einmenschlabel , Alabel1 , hBox , hBox1 ) ;
    }
}

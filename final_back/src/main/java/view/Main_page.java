package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main_page extends VBox {
    private Button login_button ;

    public Main_page(Button login_button) {
        this.login_button = login_button;
    }

    private Button signup_button ;
    public Main_page() throws FileNotFoundException {

        Label label = new Label("EIN MENSCH") ;
        label.setStyle("-fx-font-size: 30px ;");

        setLogin_button(new Button());
        setSignup_button(new Button());

        Image image = new Image(new FileInputStream("c:/test/final_back/main_page.jpg")) ;
        BackgroundSize backgroundSize = new BackgroundSize(100 , 100 , true , true , true , false) ;
        BackgroundImage backgroundImage = new BackgroundImage(image , BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.CENTER , backgroundSize) ;
        Background background = new Background(backgroundImage) ;
        this.setBackground(background);

        login_button.setText("\" LOGIN \"");
        login_button.setPrefSize(200 , 100);
        login_button.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-background-color: rgba(56,175,116,0.75) ;-fx-background-radius: 10px;");
        login_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Login_page loginPage = null;
                try {
                    loginPage = new Login_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(loginPage));
                stage1.show();

                Stage stage = (Stage) login_button.getScene().getWindow() ;
                stage.close();

            }
        });


        signup_button.setText("\" SIGN UP \"");
        signup_button.setPrefSize(200 , 100);
        signup_button.setStyle("-fx-font-size: 16px ; -fx-font-family: Arial ;-fx-background-color: rgba(85,57,136,0.93) ;-fx-background-radius: 10px;");
        signup_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Signup_page signupPage = null;
                try {
                    signupPage = new Signup_page();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Stage stage1 = new Stage() ;
                stage1.setScene(new Scene(signupPage));
                stage1.show();

                Stage stage = (Stage) signup_button.getScene().getWindow() ;
                stage.close();
            }
        });


        VBox vBox1 = new VBox(login_button , signup_button) ;
        vBox1.setSpacing(30);
        vBox1.setAlignment(Pos.CENTER);


        Label timeLabel = new Label();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalTime currentTime = LocalTime.now();
            timeLabel.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        timeLabel.setFont(Font.font("Arial",40));



        HBox hBox = new HBox(timeLabel , vBox1) ;
        hBox.setSpacing(40);
        hBox.setAlignment(Pos.CENTER);


        this.setPrefSize(1130 , 780);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(label , hBox) ;


    }

    public Button getLogin_button() {
        return login_button;
    }

    public void setLogin_button(Button login_button) {
        this.login_button = login_button;
    }

    public Button getSignup_button() {
        return signup_button;
    }

    public void setSignup_button(Button signup_button) {
        this.signup_button = signup_button;
    }
}

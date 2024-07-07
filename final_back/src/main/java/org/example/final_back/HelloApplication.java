package org.example.final_back;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.*;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Main_page mainPage = new Main_page() ;
//        Signup_page signupPage = new Signup_page() ;
//        Login_page loginPage = new Login_page() ;
//        User_panel userPanel = new User_panel() ;
//        CEO_panel ceoPanel = new CEO_panel() ;
//        Deputy_panel deputyPanel = new Deputy_panel() ;
//        Employee_panel employeePanel = new Employee_panel() ;
//        Admin_panel adminPanel = new Admin_panel() ;
        Scene scene = new Scene(mainPage) ;
        stage.setScene(scene);
        stage.show();

    }
}
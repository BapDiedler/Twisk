package twisk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

public class MainTwisk extends Application {
    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage){
        primaryStage.setTitle("MainTwisk");
        BorderPane root = new BorderPane();
        MondeIG monde = new MondeIG();
        root.setCenter(new VueMondeIG(monde));
        root.setBottom(new VueOutils(monde));
        root.setTop(new VueMenu(monde));
        primaryStage.setScene(new Scene(root,1000,1000));
        primaryStage.show();
    }
}

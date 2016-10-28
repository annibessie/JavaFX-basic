package minuprojekt;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
/**
 * Created by anni-bessie on 26.10.16.
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{

        VBox vbox = new VBox();
        vbox.setSpacing(20);
        Scene avaleht = new Scene(vbox, 700, 800);
        vbox.setAlignment(Pos.BASELINE_LEFT);

        primaryStage.setScene(avaleht);
        primaryStage.show();


        Label kliendinumber = new Label("Klient");
        ChoiceBox kn = new ChoiceBox(FXCollections.observableArrayList("Klient 1", "Klient 2", "Klient 3"));
        kn.getValue();

        Label tier = new Label("Tier");
        ChoiceBox tiernr = new ChoiceBox(FXCollections.observableArrayList("Tier 1", "Tier 2", "Tier 3"));
        kn.getValue();

        Label eesnimi = new Label("Eesnimi");
        TextField en = new TextField();
        en.setPrefWidth(100);

        Label perenimi = new Label("Perekonnanimi");
        TextField pn = new TextField();
        pn.setPrefWidth(100);

        Label positsioon = new Label("Positsioon");
        ChoiceBox posits = new ChoiceBox(FXCollections.observableArrayList("Team lead", "Team member"));
        kn.getValue();

        Label rahvus = new Label("Rahvus");
        TextField rahv = new TextField();

        Label akp = new Label ("Deployment algus");
        final DatePicker[] dp = {new DatePicker()};
        LocalDate[] ld = new LocalDate[1];

        LocalDate[] finalLd1 = ld;
        dp[0].setOnAction((ActionEvent event) -> {
            finalLd1[0] = dp[0].getValue();
            System.out.println("Deployment'i algus on " + finalLd1[0]);
        });


        Label lkp = new Label ("Deployment lõpp");
        final DatePicker[] loppkp = {new DatePicker()};
        ld = new LocalDate[1];

        LocalDate[] finalLd = ld;
        loppkp[0].setOnAction((ActionEvent event) -> {
            finalLd[0] = loppkp[0].getValue();
            System.out.println("Deployment'i lõpp on " + loppkp[0]);
        });


        Button salvesta = new Button("Salvesta");

        vbox.getChildren().addAll(kliendinumber, kn, tier, tiernr, eesnimi, en, perenimi, pn, positsioon, posits, rahvus, rahv, akp, dp[0], lkp, loppkp[0], salvesta);
    }
}



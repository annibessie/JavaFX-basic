package minuprojekt;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.LocalDateStringConverter;

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

        Label eesnimi = new Label("Eesnimi");
        TextField en = new TextField();
        en.setPrefWidth(100);

        Label perenimi = new Label("Perekonnanimi");
        TextField pn = new TextField();
        pn.setPrefWidth(100);

        Label positsioon = new Label("Positsioon");
        ChoiceBox posits = new ChoiceBox(FXCollections.observableArrayList("Team lead", "Team member"));

        Label rahvus = new Label("Rahvus");
        TextField rahv = new TextField();
        String sisestatudrahv = rahv.getText();
        System.out.println(sisestatudrahv);

        System.out.println(new Date());
        System.out.println(new Date().getTime());


        Label akp = new Label ("Deployment algus");
        DatePicker alguskp = new DatePicker();
        alguskp.setOnAction(event -> {
            LocalDate datealgus = alguskp.getValue();
            System.out.println("Valitud kuupäev on: " + datealgus);
            long datealguskp = alguskp.getValue().toEpochDay();

        });


        Label loppkp = new Label("Deployment lõpp");
        DatePicker loppkuupaev = new DatePicker();
        loppkuupaev.setOnAction(event -> {
            LocalDate datelopp = loppkuupaev.getValue();
            System.out.println("Valitud lõppkuupäev on: " + datelopp);

            System.out.println(kn.getValue());
            System.out.println(tiernr.getValue());

            String sisestatuden = en.getText();
            System.out.println(sisestatuden);

            String sisestatudpn = pn.getText();
            System.out.println(sisestatudpn);
            System.out.println(posits.getValue());
            long datealguskp = alguskp.getValue().toEpochDay();
            long dateloppkp = loppkuupaev.getValue().toEpochDay();
            int paevi = (int) Math.abs(dateloppkp - datealguskp + 1);


            System.out.println(paevi);

            int fee = paevi;

            if (kn.getValue().equals("Klient 1")) {
                fee = paevi * 10;
                System.out.println("Fee on " + fee);
            } else if (kn.getValue().equals("Klient 2")) {
                fee = paevi * 20;
                System.out.println("Fee on " + fee);
            } else if (kn.getValue().equals("Klient 3")) {
                fee = paevi * 30;
                System.out.println("Fee on " + fee);
            } else {
                System.out.println("Tasu pole määratud");
            }

        });

        Button salvestabutton = new Button("Salvesta");

        GridPane gridPane = new GridPane();
        Scene tulemusleht = new Scene(gridPane, 700, 800);


        salvestabutton.setOnMouseClicked(event -> {
            primaryStage.setScene(tulemusleht);
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPrefWidth(100);
            gridPane.add(new Label("Kliendinumber"), 1, 2);
            gridPane.add(new Label("Tier"), 2, 2);
            gridPane.add(new Label("Eesnimi"), 3, 2);
            gridPane.add(new Label("Perekonnanimi"), 4, 2);
            gridPane.add(new Label("Positsioon"), 5, 2);
            gridPane.add(new Label("Päevade arv"), 6, 2);
            gridPane.add(new Label("Fee"), 7, 2);
            gridPane.add(kn, 1, 3);
            //kn - mis ma peaks tegema, et ma saaksin kn-i choiceboxi väärtuse gridpane'i? .getValue().. smth?
            //gridPane.add(fee, 7, 3);
            //eelmise rea kohta: programm arvutab vastavalt kuupäevadele fee - kuidas ma selle väärtuse kätte saaksin, nii et kuvada seda järgmises scene'is grid pane'is?

        });

        vbox.getChildren().addAll(kliendinumber, kn, tier, tiernr, eesnimi, en, perenimi, pn, positsioon, posits, rahvus, rahv, akp, alguskp, loppkp, loppkuupaev, salvestabutton);
    }
}





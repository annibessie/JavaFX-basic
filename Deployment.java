package minuprojekt;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by anni-bessie on 5.01.17.
 */


public class Deployment {


    public ClientData data;

    VBox vbox = new VBox();                              //Programmi käivitamisel avaneb esimene aken
    Scene avaleht = new Scene(vbox, 700, 800);
    Stage stage = new Stage ();

    Deployment () {                                     //Kutsutakse välja Deployment stseen ja ClientData, mis on ka eraldi klassina defineeritud
        deploymentScene();
        new ClientData();
        data = new ClientData();
    }

    public Scene deploymentScene () {                   //Avalehel on VBox, kus on lahtrid, mida peab täitma
        VBox vbox = new VBox();
        Scene avaleht = new Scene(vbox, 700, 800);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.BASELINE_LEFT);
        stage.setScene(avaleht);
        stage.show();

        Label kliendinumber = new Label("Klient");      //Tuleb valida kliendinumber
        ChoiceBox kn = new ChoiceBox(FXCollections.observableArrayList("Klient 1", "Klient 2", "Klient 3"));
        kn.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue ov, String value, String new_value) {
                        data.salvestatudKN = new_value;
                    }
                });
        kn.getValue();

        Label tier = new Label("Tier");                 //Tuleb valida Tier'i number
        ChoiceBox tiernr = new ChoiceBox(FXCollections.observableArrayList("Tier 1", "Tier 2", "Tier 3"));
        kn.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue ov, String value, String new_value) {
                        data.salvestatudTier = new_value;
                    }
                });

        Label eesnimi = new Label("Eesnimi");           //Tuleb sisestada töötaja eesnimi
        TextField en = new TextField();
        en.setPrefWidth(100);

        Label perenimi = new Label("Perekonnanimi");    //Tuleb sisestada töötaja perekonnanimi
        TextField pn = new TextField();
        pn.setPrefWidth(100);

        Label positsioon = new Label("Positsioon");     //Tuleb sisestada töötaja positsioon
        ChoiceBox posits = new ChoiceBox(FXCollections.observableArrayList("Team lead", "Team member"));
        posits.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue ov, String value, String new_value) {
                        data.salvestatudposits = new_value;
                    }
                });

        Label rahvus = new Label("Rahvus");             //Tuleb sisestada töötaja rahvus
        TextField rahv = new TextField();
        String sisestatudrahv = rahv.getText();
        System.out.println(sisestatudrahv);

        System.out.println(new Date());
        System.out.println(new Date().getTime());


        Label akp = new Label("Deployment algus");      //Tuleb kalendrivaates valida tööaja alguskuupäev
        DatePicker alguskp = new DatePicker();
        alguskp.setOnAction(event -> {
            LocalDate datealgus = alguskp.getValue();
            System.out.println("Valitud kuupäev on: " + datealgus);
            long datealguskp = alguskp.getValue().toEpochDay();

        });


        Label loppkp = new Label("Deployment lõpp");    //Tuleb kalendrivaates valida tööaja lõppkuupäev
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
            data.paevi = (int) Math.abs(dateloppkp - datealguskp + 1);      //Siin lahutatakse lõppkuupäevast alguskuupäev ja liidetakse number 1, et saada töötatud päevade arv


            System.out.println(data.paevi);

            data.fee = data.paevi;                                          //Siin arvutatakse vastavalt kliendile ja töötatud päevade arvule arve kliendile

            if (kn.getValue().equals("Klient 1")) {
                data.fee = data.paevi * 10;
                System.out.println("Fee on " + data.fee);
            } else if (kn.getValue().equals("Klient 2")) {
                data.fee = data.paevi * 20;
                System.out.println("Fee on " + data.fee);
            } else if (kn.getValue().equals("Klient 3")) {
                data.fee = data.paevi * 30;
                System.out.println("Fee on " + data.fee);
            } else {
                System.out.println("Tasu pole määratud");
            }

        });


        Button salvestabutton = new Button("Salvesta");

        GridPane gridPane = new GridPane();
        Scene tulemusleht = new Scene(gridPane, 1100, 600);                 //Uue scene'i mõõdud


        salvestabutton.setOnMouseClicked(event -> {                         //Vajutades nuppu salvesta, avaneb uus stseen, kus on tabelina kajastatud eelnevalt sisestatud info
                stage.setScene(tulemusleht);
                ColumnConstraints column1 = new ColumnConstraints();
                column1.setPrefWidth(900);
                gridPane.setHgap(27);                                           //Gridpane'i lahtrite mõõdud
                gridPane.setVgap(27);
                gridPane.add(new Label("Kliendinumber"), 1, 2);                 //Gridpane'i lahtrite sisu
                gridPane.add(new Label("Tier"), 2, 2);
                gridPane.add(new Label("Eesnimi"), 3, 2);
                gridPane.add(new Label("Perekonnanimi"), 4, 2);
                gridPane.add(new Label("Positsioon"), 5, 2);
                gridPane.add(new Label("Rahvus"), 6, 2);
                gridPane.add(new Label("Deployment algus"), 7, 2);
                gridPane.add(new Label("Deployment lõpp"), 8, 2);
                gridPane.add(new Label("Päevade arv"), 9, 2);
                gridPane.add(new Label("Fee"), 10, 2);
                gridPane.add(new Label(kn.getValue().toString()), 1, 3);
                gridPane.add(new Label(tiernr.getValue().toString()), 2, 3);
                gridPane.add(new Label(en.getText()), 3, 3);
                gridPane.add(new Label(pn.getText()), 4, 3);
                gridPane.add(new Label(posits.getValue().toString()), 5, 3);
                gridPane.add(new Label(rahv.getText()), 6, 3);
                gridPane.add(new Label(alguskp.getValue().toString()), 7, 3);
                gridPane.add(new Label(loppkuupaev.getValue().toString()), 8, 3);
                gridPane.add(new Label(String.valueOf(data.paevi)), 9, 3);
                gridPane.add(new Label(String.valueOf(data.fee)), 10, 3);

                String klient = kn.getValue().toString();                       //Andmebaasi jaoks on siin tehtud kõik sisestatud väärtused String tüüpi
                String tiernumber = tiernr.getValue().toString();
                String inimeseeesnimi = en.getText();
                String perekonnanimi = pn.getText();
                String position = posits.getValue().toString();
                String nationality = rahv.getText();
                String algus = alguskp.getValue().toString();
                String lopp = loppkuupaev.getValue().toString();
                String arvpaevi = String.valueOf(data.paevi);
                String kliendifee = String.valueOf(data.fee);
                Andmebaas a = new Andmebaas();
                a.registreeriDeployment(klient, tiernumber, inimeseeesnimi, perekonnanimi, position, nationality, algus, lopp, arvpaevi, kliendifee);
                a.sulgeYhendus();

        });

        vbox.getChildren().addAll(kliendinumber, kn, tier, tiernr, eesnimi, en, perenimi, pn, positsioon, posits, rahvus, rahv, akp, alguskp, loppkp, loppkuupaev, salvestabutton);
        return tulemusleht;
    }
}


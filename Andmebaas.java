package minuprojekt;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by anni-bessie on 6.01.17.
 */
public class Andmebaas {

    Connection conn = null;

    public Andmebaas() {
        looYhendus();
        looTabel();
    }

    private void looYhendus() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
        }
    }

    public void looTabel() {
        String sql = "CREATE TABLE IF NOT EXISTS USERS (ID INT AUTO_INCREMENT, KLIENT TEXT, TIERNUMBER TEXT, INIMESEEESNIMI TEXT, PEREKONNANIMI TEXT, POSITSION TEXT, NATIONALITY TEXT, ALGUS TEXT, LOPP TEXT, ARVPAEVI TEXT, KLIENDIFEE TEXT);";
        teeAndmebaasiMuudatus(sql);

    }

    private void teeAndmebaasiMuudatus(String sql) {
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //}
        //public void lisaDeployment (String kn, String tiernr, String en, String pn, String posits, String rahv, String alguskp, String loppkuupaev, Integer paevi, Integer fee ){
        //   String sql = " INSERT INTO USERS (KN, TIERNR, EN, PN, POSITS, RAHV, ALGUSKP, LOPPKUUAEV, PAEVI, FEE) VALUES ('''+kn+''', '''+tiernr+''', )";
        //    teeAndmebaasiMuudatus(sql);
        //}
    }
    //public void registreeriDeployment (ChoiceBox kn, ChoiceBox tiernr, TextField en, TextField pn, ChoiceBox posits, TextField rahv, DatePicker alguskp, DatePicker loppkuupaev, int paevi, int fee){
    //    String sql = " INSERT INTO USERS (KN, TIERNR, EN, PN, POSITS, RAHV, ALGUSKP, LOPPKUUAEV, PAEVI, FEE) VALUES (""+kn+"", ""+tiernr+"", )";
    //    teeAndmebaasiMuudatus(sql);
    //}

    public void sulgeYhendus() {
        try {
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Ühendus suletud");
    }


    public void registreeriDeployment(String klient, String tiernumber, String inimeseeesnimi, String perekonnanimi, String position, String nationality, String algus, String lopp, String arvpaevi, String kliendifee) {
        String sql = " INSERT INTO USERS (KLIENT, TIERNUMBER, INIMESEEESNIMI, PEREKONNANIMI, POSITSION, NATIONALITY, ALGUS, LOPP, ARVPAEVI, KLIENDIFEE) VALUES ('+klient+', '+tiernumber+', )";
        teeAndmebaasiMuudatus(sql);
    }
}


package minuprojekt;


import java.sql.*;
import java.util.HashMap;

/**
 * Created by anni-bessie on 6.01.17.
 */

// Aluseks on võetud Krister Viirsaare näidisvideo
// Allikas: https://github.com/KristerV/javaSQLNaide/blob/master/src/Andmebaas.java

// Andmebaasi olen alustanud - hetkel peaks saama andmebaasi sisestatud andmeid salvestada aga mitte veel päringuid tehes kasutajale kuvada.

public class Andmebaas {

    Connection conn = null;

    // Konsutruktor ehk meetod, mis käivitub kohe objekti välja kutsumisel
    public Andmebaas() {
        looYhendus();
        looTabel();
    }
    // Andmebaasi kasutamiseks tuleb luua ühendus
    private void looYhendus() {
        try {
            Class.forName("org.sqlite.JDBC");                                   // Laeb draiveri sqlite.jar failist
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");          // Loob ühenduse andmebaasi failiga
            System.out.println("Opened database successfully");                 // Annab teada üheduse loomisest
        } catch (Exception e) {                                                 // Annab teada võimalikest erroritest
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
        }
    }

    public void looTabel() {                                                    // Loob tabeli, sulgude vahel info, mida tabel hakkab hoidma
        String sql = "CREATE TABLE IF NOT EXISTS DEPLOYMENTS (ID INT AUTO_INCREMENT, KLIENT TEXT, TIERNUMBER TEXT, INIMESEEESNIMI TEXT, PEREKONNANIMI TEXT, POSITSIOON TEXT, RAHVUS TEXT, ALGUS TEXT, LOPP TEXT, ARVPAEVI TEXT, KLIENDIFEE TEXT);";
        teeAndmebaasiMuudatus(sql);

    }
    //Meetod muudatuste tegemiseks andmebaasis
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
    //Lisan andmebaasi tulpade järgi vastava info, mida soovin salvestada
    public void registreeriDeployment(String klient, String tiernumber, String inimeseeesnimi, String perekonnanimi, String position, String nationality, String algus, String lopp, String arvpaevi, String kliendifee) {
        String sql = " INSERT INTO DEPLOYMENTS (KLIENT, TIERNUMBER, INIMESEEESNIMI, PEREKONNANIMI, POSITSIOON, RAHVUS, ALGUS, LOPP, ARVPAEVI, KLIENDIFEE) VALUES ('"+klient+"', '"+tiernumber+"', '"+inimeseeesnimi+"', '"+perekonnanimi+"', '"+position+"', '"+nationality+"', '"+algus+"', '"+lopp+"', '"+arvpaevi+"', '"+kliendifee+"' )";
        teeAndmebaasiMuudatus(sql);
        System.out.println("Info andmebaasi lisatud");
    }

    //Sulgen ühenduse, et arvuti resursse säästa
    public void sulgeYhendus() {
        try {
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Ühendus suletud");
    }

    // Deployment andmete päring
    public HashMap getDeployment (String klient){
        HashMap andmed = new HashMap();

        try{
            Statement stat = conn.createStatement();
            String sql = "SELECT FROM KLIENT WHERE KLIENT = '"+klient+"' LIMIT 5;";
            ResultSet rs = stat.executeQuery(sql);
            // Kui stat.executeQuery() toob tagasi tühja tulemuse, siis rs'i kasutada ei saa.

            // Nopin käsitsi andmed ühelt realt välja
            andmed.put("KLIENT", rs.getString("klient"));
            andmed.put("TIERNUMBER", rs.getString("tiernumber"));
            andmed.put("INIMESEEESNIMI", rs.getString("inimeseeesnimi"));
            andmed.put("PEREKONNANIMI", rs.getString("perekonnanimi"));
            andmed.put("POSITSIOON", rs.getString("position"));
            andmed.put("RAHVUS", rs.getString("nationality"));
            andmed.put("ALGUS", rs.getString("algus"));
            andmed.put("LOPP", rs.getString("lopp"));
            andmed.put("ARVPAEVI", rs.getString("arvpaevi"));
            andmed.put("KLIENDIFEE", rs.getString("kliendifee"));

            rs.close();
            stat.close();
            return andmed;
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return andmed;
    }

}


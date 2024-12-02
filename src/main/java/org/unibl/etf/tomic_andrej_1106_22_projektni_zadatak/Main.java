package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.iznajmljivanje.Iznajmljivanje;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.properties_ucitavanje.PropertiesUcitavanje;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.ucitavanje.UcitavanjeIznajmljivanje;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.ucitavanje.UcitavanjeVozilo;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila.Automobil;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila.Vozilo;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Ulazna tačka aplikacije. Inicijalizuje i upravlja glavnim prozorom aplikacije,
 * uključujući učitavanje FXML fajlova, postavljanje scena i upravljanje prelazima između njih.
 * Takođe, rukuje učitavanjem podataka, serijalizacijom i pokretanjem aplikacije.
 */
public class Main extends Application {

    /**
     * HashMap objekata tipa {@link Vozilo} sa ID-jem vozila kao ključem.
     */
    public static Map<String, Vozilo> VOZILA = new HashMap<String, Vozilo>();

    /**
     * HashMap vrsta vozila sa njihovim maksimalnim zaradama, gdje je kluč tip vozila,
     * a vrijednost par koji sadrži ID vozila i njegovu maksimalnu zaradu.
     */
    public static Map<String, Pair<String, Double>> MAKSIMALNA_ZARADA = new HashMap<String, Pair<String, Double>>();

    private Scene mapaScene, prevoznaSredstvaScene, rezultatiPoslovanjaScene, kvaroviScene, najveciPrihodiScene;
    private Stage glavniStage;

    /**
     * Inicijalizuje aplikaciju, učitava FXML fajlove za različite scene i postavlja
     * glavni prozor aplikacije. Takođe pokreće pozadinski thread za upravljanje ažuriranjem
     * podataka i osvežavanjem scena.
     *
     * @param glavniStage Glavni prozor aplikacije.
     * @throws IOException Ako učitavanje FXML fajlova ili resursa ne uspije.
     */
    @Override
    public void start(Stage glavniStage) throws IOException {
        UcitavanjeVozilo.ucitavanjeVozila();

        this.glavniStage = glavniStage;
        Properties properties = PropertiesUcitavanje.ucitajProperties();
        Image ikona = new Image(new FileInputStream(properties.getProperty("PUTANJA_IKONE")));
        glavniStage.getIcons().add(ikona);
        glavniStage.setTitle("ePJ2");
        glavniStage.setResizable(false);

        FXMLLoader mapaLoader = new FXMLLoader(getClass().getResource("Mapa.fxml"));
        Parent mapaRoot = mapaLoader.load();
        KontrolerMapa kontrolerMapa = mapaLoader.getController();
        kontrolerMapa.setMainAplikacija(this);

        FXMLLoader prevoznaSredstvaLoader = new FXMLLoader(getClass().getResource("PrevoznaSredstva.fxml"));
        Parent prevoznaSredstvaRoot = prevoznaSredstvaLoader.load();
        KontrolerPrevoznaSredstva kontrolerPrevoznaSredstva = prevoznaSredstvaLoader.getController();
        kontrolerPrevoznaSredstva.setMainAplikacija(this);

        List<List<Iznajmljivanje>> listeIznajmljivanja = UcitavanjeIznajmljivanje.ucitajIznajmljivanja(VOZILA.values(), kontrolerMapa, kontrolerPrevoznaSredstva);

        FXMLLoader rezultatiPoslovanjaLoader = new FXMLLoader(getClass().getResource("RezultatiPoslovanja.fxml"));
        Parent rezultatiPoslovanjaRoot = rezultatiPoslovanjaLoader.load();
        KontrolerRezultatiPoslovanja kontrolerRezultatiPoslovanja = rezultatiPoslovanjaLoader.getController();
        kontrolerRezultatiPoslovanja.setMainAplikacija(this);

        FXMLLoader kvaroviLoader = new FXMLLoader(getClass().getResource("Kvarovi.fxml"));
        Parent kvaroviRoot = kvaroviLoader.load();
        KontrolerKvarovi kontrolerKvarovi = kvaroviLoader.getController();
        kontrolerKvarovi.setMainAplikacija(this);

        FXMLLoader najveciPrihodiLoader = new FXMLLoader(getClass().getResource("DodatnaFunkcionalnost.fxml"));
        Parent najveciPrihodiRoot = najveciPrihodiLoader.load();
        KontrolerNajveciPrihodi kontrolerNajveciPrihodi = najveciPrihodiLoader.getController();
        kontrolerNajveciPrihodi.setMainAplikacija(this);

        mapaScene = new Scene(mapaRoot);
        prevoznaSredstvaScene = new Scene(prevoznaSredstvaRoot);
        rezultatiPoslovanjaScene = new Scene(rezultatiPoslovanjaRoot);
        kvaroviScene = new Scene(kvaroviRoot);
        najveciPrihodiScene = new Scene(najveciPrihodiRoot);

        prikaziMapaScenu();

        new Thread(() -> {
            for (List<Iznajmljivanje> lista : listeIznajmljivanja) {
                kontrolerMapa.ciscenjeMape();
                kontrolerKvarovi.osvjeziTabeluKvarova();
                kontrolerRezultatiPoslovanja.osvjeziIzvjestaj();
                for (Iznajmljivanje iznajmljivanje : lista) {
                    iznajmljivanje.start();
                }
                for (Iznajmljivanje iznajmljivanje : lista) {
                    try {
                        iznajmljivanje.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            kontrolerMapa.ciscenjeMape();
            kontrolerKvarovi.osvjeziTabeluKvarova();
            kontrolerRezultatiPoslovanja.osvjeziIzvjestaj();
            serijalizujVozilaSaMaksimalnimZaradama();
            kontrolerMapa.enableNajveciPrihodiButton();
        }).start();

    }

    /**
     * Prikazuje scenu za mapu.
     */
    public void prikaziMapaScenu() {
        glavniStage.setScene(mapaScene);
        glavniStage.show();
    }

    /**
     * Prikazuje scenu za prevozna sredstva.
     */
    public void prikaziPrevoznaSredstvaScenu() {
        glavniStage.setScene(prevoznaSredstvaScene);
        glavniStage.show();
    }

    /**
     * Prikazuje scenu za rezultate poslovanja.
     */
    public void prikaziRezultatiPoslovanjaScenu() {
        glavniStage.setScene(rezultatiPoslovanjaScene);
        glavniStage.show();
    }

    /**
     * Prikazuje scenu za najveće prihode.
     */
    public void prikaziNajvecePrihodeScenu() {
        glavniStage.setScene(najveciPrihodiScene);
        glavniStage.show();
    }

    /**
     * Prikazuje scenu za kvarove.
     */
    public void prikaziKvaroviScenu() {
        glavniStage.setScene(kvaroviScene);
        glavniStage.show();
    }

    /**
     * Serijalizuje vozila sa maksimalnim zaradama i čuva ih u odgovarajućim fajlovima.
     */
    private void serijalizujVozilaSaMaksimalnimZaradama() {
        Properties properties = PropertiesUcitavanje.ucitajProperties();
        String putanjaFoldera = properties.getProperty("PUTANJA_SERIJALIZACIJA");

        try {
            PrintWriter pw = new PrintWriter(putanjaFoldera + File.separator + "iznosi.txt");

            ObjectOutputStream oosAutomobil = new ObjectOutputStream(new FileOutputStream(putanjaFoldera + File.separator + "Automobil.ser"));
            oosAutomobil.writeObject(VOZILA.get(MAKSIMALNA_ZARADA.get("Automobil").getKey()));
            oosAutomobil.close();
            pw.println("Automobil " + MAKSIMALNA_ZARADA.get("Automobil").getValue());

            ObjectOutputStream oosBicikl = new ObjectOutputStream(new FileOutputStream(putanjaFoldera + File.separator + "Bicikl.ser"));
            oosBicikl.writeObject(VOZILA.get(MAKSIMALNA_ZARADA.get("Bicikl").getKey()));
            oosBicikl.close();
            pw.println("Bicikl " + MAKSIMALNA_ZARADA.get("Bicikl").getValue());

            ObjectOutputStream oosTrotinet = new ObjectOutputStream(new FileOutputStream(putanjaFoldera + File.separator + "Trotinet.ser"));
            oosTrotinet.writeObject(VOZILA.get(MAKSIMALNA_ZARADA.get("Trotinet").getKey()));
            oosTrotinet.close();
            pw.println("Trotinet " + MAKSIMALNA_ZARADA.get("Trotinet").getValue());

            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Glavna tačka ulaza za JavaFX aplikaciju. Ova metoda poziva `launch()` metodu
     * koja pokreće aplikaciju i inicijalizuje JavaFX okruženje.
     *
     * @param args Argumenti komandne linije (ne koriste se u ovoj aplikaciji).
     */
    public static void main(String[] args) {
        launch();
    }
}
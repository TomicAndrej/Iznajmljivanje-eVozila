package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.ucitavanje;

import javafx.util.Pair;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.Main;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.properties_ucitavanje.PropertiesUcitavanje;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila.Automobil;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila.Bicikl;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila.Trotinet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

/**
 * Klasa {@code UcitavanjeVozilo} odgovorna je za učitavanje podataka o vozilima
 * iz fajla i njihovu obradu. Ova klasa koristi konfiguracioni fajl za pronalaženje
 * putanje do fajla sa podacima o vozilima i potom učitava te podatke, kreira instance
 * odgovarajućih tipova vozila i dodaje ih u glavnu kolekciju vozila aplikacije.
 *
 * <p>Podaci o vozilima su u CSV formatu i obuhvataju automobile, bicikle i trotinete.
 * Nakon učitavanja podataka, ova klasa takođe inicijalizuje maksimalne zarade za svaki
 * tip vozila.</p>
 *
 */
public class UcitavanjeVozilo {

    /** Tip vozila za automobile */
    private static final String AUTOMOBIL = "automobil";

    /** Tip vozila za bicikle */
    private static final String BICIKL = "bicikl";

    /** Tip vozila za trotineti */
    private static final String TROTINET = "trotinet";

    /**
     * Učitava podatke o vozilima iz fajla i dodaje ih u glavnu kolekciju vozila aplikacije.
     *
     * <p>Metoda koristi konfiguracioni fajl za pronalaženje putanje do fajla sa
     * podacima o vozilima. Podaci su u CSV formatu i obuhvataju automobile, bicikle
     * i trotinete. Na osnovu tipa vozila, kreiraju se odgovarajući objekti i dodaju u
     * {@code Main.VOZILA} kolekciju. Ako vozilo sa istim ID-jem već postoji, ispisuje
     * se upozorenje.</p>
     *
     * <p>Osim toga, metoda inicijalizuje maksimalne zarade za tipove vozila u
     * {@code Main.MAKSIMALNA_ZARADA} mapu.</p>
     */
    public static void ucitavanjeVozila() {
        Properties prop = PropertiesUcitavanje.ucitajProperties();
        String putanjaFajla = prop.getProperty("PUTANJA_VOZILA");

        try {
            List<String> redovi = Files.readAllLines(new File(putanjaFajla).toPath());
            redovi.remove(0);

            redovi.stream().forEach(red -> {
                String[] split = red.split(",");
                if (!Main.VOZILA.containsKey(split[0])) {
                    if (AUTOMOBIL.equals(split[8])) {
                        Automobil automobil = new Automobil();

                        automobil.setId(split[0]);
                        automobil.setProizvodjac(split[1]);
                        automobil.setModel(split[2]);
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy.");
                        automobil.setDatumNabavke(LocalDate.parse(split[3], dtf));
                        automobil.setCijenaNabavke(Double.parseDouble(split[4]));
                        automobil.setOpis(split[7]);

                        Main.VOZILA.put(automobil.getId(), automobil);
                    } else if (BICIKL.equals(split[8])) {
                        Bicikl bicikl = new Bicikl();

                        bicikl.setId(split[0]);
                        bicikl.setProizvodjac(split[1]);
                        bicikl.setModel(split[2]);
                        bicikl.setCijenaNabavke(Double.parseDouble(split[4]));
                        bicikl.setDomet(Double.parseDouble(split[5]));

                        Main.VOZILA.put(bicikl.getId(), bicikl);
                    } else if (TROTINET.equals(split[8])) {
                        Trotinet trotinet = new Trotinet();

                        trotinet.setId(split[0]);
                        trotinet.setProizvodjac(split[1]);
                        trotinet.setModel(split[2]);
                        trotinet.setCijenaNabavke(Double.parseDouble(split[4]));
                        trotinet.setMaksimalnaBrzina(Double.parseDouble(split[6]));

                        Main.VOZILA.put(trotinet.getId(), trotinet);
                    }
                } else {
                    System.out.println("Vozilo sa id-jem " + split[0] + " vec postoji");
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Main.MAKSIMALNA_ZARADA.put("Automobil", new Pair<>("", 0.0));
        Main.MAKSIMALNA_ZARADA.put("Bicikl", new Pair<>("", 0.0));
        Main.MAKSIMALNA_ZARADA.put("Trotinet", new Pair<>("", 0.0));
    }
}

package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.racun;

import javafx.util.Pair;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.Main;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.korisnik.DomaciKorisnik;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.korisnik.StraniKorisnik;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.properties_ucitavanje.PropertiesUcitavanje;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * Klasa koja predstavlja račun za korišćenje prevoznih sredstava.
 * Ova klasa omogućava kreiranje i čuvanje računa u fajlu, kao i praćenje različitih finansijskih podataka
 * kao što su ukupni prihodi, popusti, promocije i troškovi.
 */
public class Racun {
    // ----- DNEVNI IZVJESTAJ -----
    /**
     * Map za čuvanje ukupnog prihoda po danima.
     */
    public static Map<LocalDate, Double> UKUPAN_PRIHOD_DNEVNI  = new HashMap<LocalDate, Double>();

    /**
     * Map za čuvanje ukupnog popusta po danima.
     */
    public static Map<LocalDate, Double> UKUPAN_POPUST_DNEVNI  = new HashMap<LocalDate, Double>();

    /**
     * Map za čuvanje ukupnih promocija po danima.
     */
    public static Map<LocalDate, Double> UKUPNO_PROMOCIJE_DNEVNI  = new HashMap<LocalDate, Double>();

    /**
     * Map za čuvanje ukupnog iznosa vožnji u užem i širem dijelu grada po danima.
     */
    public static Map<LocalDate, Double> UKUPAN_IZNOS_VOZNJI_UZI_SIRI_DIO_GRADA_DNEVNI  = new HashMap<LocalDate, Double>();

    /**
     * Map za čuvanje ukupnog iznosa za održavanje po danima.
     */
    public static Map<LocalDate, Double> UKUPAN_IZNOS_ZA_ODRZAVANJE_DNEVNI  = new HashMap<LocalDate, Double>();

    /**
     * Map za čuvanje ukupnog iznosa za popravke kvarova po danima.
     */
    public static Map<LocalDate, Double> UKUPAN_IZNOS_ZA_POPRAVKE_KVAROVA_DNEVNI  = new HashMap<LocalDate, Double>();

    // ----- SUMARNI IZVJESTAJ -----
    /**
     * Ukupna suma svih iznosa za plačanje na svim računima.
     */
    public static double UKUPAN_PRIHOD = 0;

    /**
     * Ukupna suma svih iznosa popusta sa svih računa.
     */
    public static double UKUPAN_POPUST = 0;

    /**
     * Ukupna suma vrijednosti svih promocija sa svih računa.
     */
    public static double UKUPNO_PROMOCIJE = 0;

    /**
     * Ukupan iznos svih vožnji u užem i širem dijelu grada.
     */
    public static double UKUPAN_IZNOS_VOZNJI_UZI_SIRI_DIO_GRADA = 0;

    /**
     * Ukupan prihod pomnožen sa 0,2.
     */
    public static double UKUPAN_IZNOS_ZA_ODRZAVANJE = 0;

    /**
     * Ukupan iznos za popravke kvarova.
     * Izračunava se kao zbir svih pojedinačnih kvarova.
     */
    public static double UKUPAN_IZNOS_ZA_POPRAVKE_KVAROVA = 0;

    /**
     * Ukupni troškovi kompanije, izračunati kao 20% ukupnog prihoda.
     */
    public static double UKUPNI_TROSKOVI_KOMPANIJE = 0;

    /**
     * Ukupan porez izračunat kao 10% od preostalog iznosa nakon odbitka održavanja, popravki kvarova i troškova kompanije.
     */
    public static double UKUPAN_POREZ = 0;


    private boolean siriDioGrada;
    private boolean kvar;
    private boolean popust;
    private boolean promocija;
    private int trajanje;
    private double iznos;
    private double ukupnoZaPlacanje;
    private LocalDateTime datumVrijeme;

    /**
     * Kreira novi objekat tipa Racun sa podrazumijevanim vrijednostima.
     */
    public Racun() {
        super();
    }

    /**
     * Konstruktor za kreiranje novog objekta Racun sa zadatim parametrima.
     *
     * @param siriDioGrada da li je vožnja u širšem dijelu grada
     * @param kvar da li je bilo kvara
     * @param popust da li je primjenjen popust
     * @param promocija da li je primjenjena promocija
     * @param trajanje trajanje vožnje u sekundama
     * @param datumVrijeme datum i vrijeme izdavanja računa
     */
    public Racun(boolean siriDioGrada, boolean kvar, boolean popust, boolean promocija, int trajanje, LocalDateTime datumVrijeme) {
        this.siriDioGrada = siriDioGrada;
        this.kvar = kvar;
        this.popust = popust;
        this.promocija = promocija;
        this.trajanje = trajanje;
        this.datumVrijeme = datumVrijeme;
    }

    /**
     * Vraća da li je vožnja bila u širem dijelu grada.
     *
     * @return {@code true} ako je vožnja bila u širem dijelu grada, {@code false} inače
     */
    public boolean isSiriDioGrada() {
        return siriDioGrada;
    }

    /**
     * Postavlja da li je vožnja bila u širem dijelu grada.
     *
     * @param siriDioGrada da li je vožnja bila u širem dijelu grada
     */
    public void setSiriDioGrada(boolean siriDioGrada) {
        this.siriDioGrada = siriDioGrada;
    }

    /**
     * Vraća da li se vozilo pokvarilo.
     *
     * @return {@code true} ako se pokvarilo, {@code false} inače.
     */
    public boolean isKvar() {
        return kvar;
    }

    /**
     * Postavlja da li se vozilo pokvarilo.
     *
     * @param kvar da li je vozilo pokvareno
     */
    public void setKvar(boolean kvar) {
        this.kvar = kvar;
    }

    /**
     * Varaća da li je ostvaren popust.
     *
     * @return {@code true} ako jeste, {@code false} inače.
     */
    public boolean getPopust() {
        return popust;
    }

    /**
     * Postavlja da li je ostvaren popust.
     *
     * @param popust da li je ostvaren popust
     */
    public void setPopust(boolean popust) {
        this.popust = popust;
    }

    /**
     * Varaća da li ima promociju.
     *
     * @return {@code true} ako ima, {@code false} inače.
     */
    public boolean getPromocija() {
        return promocija;
    }

    /**
     * Postavlja da li ima promociju.
     *
     * @param promocija da li ima promociju
     */
    public void setPromocija(boolean promocija) {
        this.promocija = promocija;
    }

    /**
     * Vraća koliko je trajanje vožnje.
     *
     * @return Trajanje vožnje
     */
    public int getTrajanje() {
        return trajanje;
    }

    /**
     * Postavlja koliko je trajanje vožnje.
     *
     * @param trajanje trajanje vožnje
     */
    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    /**
     * Vraća datum i vrijeme izdavanja računa.
     *
     * @return datum i vrijeme iydavanja računa
     */
    public LocalDateTime getDatumVrijeme() {
        return datumVrijeme;
    }

    /**
     * Postavlja datum i vrijeme izdavanja računa.
     *
     * @param datumVrijeme datum i vrijeme izdavanja računa
     */
    public void setDatumVrijeme(LocalDateTime datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    /**
     * Vraća iznos vožnje.
     *
     * @return iznos vožnje
     */
    public double getIznos() {
        return iznos;
    }

    /**
     * Vraća koliko se treba platiti vožnja (iznos umanjen za promociju ili popust, ako postoje)
     *
     * @return koliko se plaća
     */
    public double getUkupnoZaPlacane() {
        return ukupnoZaPlacanje;
    }

    /**
     * Upisuje račun u tekstualni fajl na osnovu trenutnih podataka o računu.
     * Račun se čuva u fajlu čije ime uključuje datum i vrijeme izdavanja, ime korisnika i ID vozila.
     * Fajl se smješta u direktorijum specificiran u konfiguracionom fajlu.
     *
     * @param idVozila       ID vozila za koje se izdaje račun
     * @param imeKorisnika   Ime korisnika koji je izvršio uslugu
     */
    public void upisiRacunUFajl(String idVozila, String imeKorisnika) {
        Properties prop = PropertiesUcitavanje.ucitajProperties();
        String putanjaFoldera = prop.getProperty("PUTANJA_RACUNI");
        String nazivRacuna = "Racun_" + datumVrijeme.format(DateTimeFormatter.ofPattern("d_M_yyyy_HH_mm_ss")) + "_" +
                imeKorisnika + "_" + idVozila + ".txt";
        String putanjaDoFajla = putanjaFoldera + File.separator + nazivRacuna;

        try (PrintWriter pw = new PrintWriter(new FileWriter(putanjaDoFajla))) {
            double obracun,
                    dioGrada = siriDioGrada ? Double.parseDouble(prop.getProperty("DISTANCA_SIRI_DIO_GRADA")) :
                            Double.parseDouble(prop.getProperty("DISTANCA_UZI_DIO_GRADA")),
                    iznosPopusta = popust ? Double.parseDouble(prop.getProperty("POPUST")) : 0,
                    iznosPromocije = promocija ? Double.parseDouble(prop.getProperty("POPUST_PROMOCIJA")) : 0;
            pw.println("Datum izdavanja racuna: " + datumVrijeme.format(DateTimeFormatter.ofPattern("d.M.yyyy HH:mm:ss")) + "\n");
            pw.println("Tip vozila: " + Main.VOZILA.get(idVozila).tipVozila());
            pw.println("Siri dio grada: " + (siriDioGrada ? "da" : "ne"));
            pw.println("Kvar: " + (kvar ? "da" : "ne"));
            pw.println("Popust: " + (popust ? iznosPopusta * 100 + "%" : "ne"));
            pw.println("Promocija: " + (promocija ? iznosPromocije * 100 + "%" : "ne"));
            pw.println("Trajanje: " + trajanje);
            if (!Main.VOZILA.get(idVozila).tipVozila().equals("Automobil")) {
                pw.println("Korisnik: " + imeKorisnika);
                if (Main.VOZILA.get(idVozila).tipVozila().equals("Bicikl")) {
                    obracun = Double.parseDouble(prop.getProperty("CIJENA_BICIKLA_PO_SEKUNDI"));
                    pw.println("Cijena bicikla po sekundi: " + obracun);
                } else {
                    obracun = Double.parseDouble(prop.getProperty("CIJENA_TROTINETA_PO_SEKUNDI"));
                    pw.println("Cijena trotineta po sekundi: " + obracun);
                }
            } else {
                if (new Random().nextInt(10) < 8) {
                    DomaciKorisnik korisnik = new DomaciKorisnik(imeKorisnika);
                    pw.println("Domaci korisnik: " + korisnik.getImeKorisnika() + ", broj vozacke: " + korisnik.getBrojVozacke() +
                            ", broj licne karte: " + korisnik.getBrojLicneKarte());
                } else {
                    StraniKorisnik korisnik = new StraniKorisnik(imeKorisnika);
                    pw.println("Strani korisnik: " + korisnik.getImeKorisnika() + ", broj vozacke: " + korisnik.getBrojVozacke() +
                            ", broj pasosa: " + korisnik.getBrojPasosa());
                }
                obracun = Double.parseDouble(prop.getProperty("CIJENA_AUTO_PO_SEKUNDI"));
                pw.println("Cijena automobila po sekundi: " + obracun);
            }
            LocalDate datum = datumVrijeme.toLocalDate();
            if (kvar) {
                iznos = 0;
                if (Main.VOZILA.get(idVozila).tipVozila().equals("Automobil")) {
                    UKUPAN_IZNOS_ZA_POPRAVKE_KVAROVA += Main.VOZILA.get(idVozila).getCijenaNabavke() * 0.07;
                } else if (Main.VOZILA.get(idVozila).tipVozila().equals("Bicikl")) {
                    UKUPAN_IZNOS_ZA_POPRAVKE_KVAROVA += Main.VOZILA.get(idVozila).getCijenaNabavke() * 0.04;
                } else {
                    UKUPAN_IZNOS_ZA_POPRAVKE_KVAROVA += Main.VOZILA.get(idVozila).getCijenaNabavke() * 0.02;
                }
                UKUPAN_IZNOS_ZA_POPRAVKE_KVAROVA_DNEVNI.put(datum, UKUPAN_IZNOS_ZA_POPRAVKE_KVAROVA);
            } else {
                iznos = obracun * trajanje * dioGrada;
            }

            ukupnoZaPlacanje = iznos - (iznosPopusta * iznos) - (iznosPromocije * iznos);
            UKUPAN_PRIHOD += ukupnoZaPlacanje;
            UKUPAN_PRIHOD_DNEVNI.put(datum, UKUPAN_PRIHOD_DNEVNI.getOrDefault(datum, 0.0) + ukupnoZaPlacanje);

            UKUPAN_POPUST += iznosPopusta * iznos;
            UKUPAN_POPUST_DNEVNI.put(datum, UKUPAN_POPUST_DNEVNI.getOrDefault(datum, 0.0) + iznos * iznosPopusta);

            UKUPNO_PROMOCIJE += iznosPromocije * iznos;
            UKUPNO_PROMOCIJE_DNEVNI.put(datum, UKUPNO_PROMOCIJE_DNEVNI.getOrDefault(datum, 0.0) + iznos * iznosPromocije);

            UKUPAN_IZNOS_VOZNJI_UZI_SIRI_DIO_GRADA += iznos;
            UKUPAN_IZNOS_VOZNJI_UZI_SIRI_DIO_GRADA_DNEVNI.put(datum, UKUPAN_IZNOS_VOZNJI_UZI_SIRI_DIO_GRADA_DNEVNI.getOrDefault(datum, 0.0) + iznos);

            UKUPAN_IZNOS_ZA_ODRZAVANJE = UKUPAN_PRIHOD * 0.2;
            UKUPAN_IZNOS_ZA_ODRZAVANJE_DNEVNI.put(datum, UKUPAN_IZNOS_ZA_ODRZAVANJE_DNEVNI.getOrDefault(datum, 0.0) + ukupnoZaPlacanje * 0.2);

            pw.println("Iznos: " + iznos);
            pw.println("Ukupno za placanje: " + ukupnoZaPlacanje);
            if (Main.VOZILA.get(idVozila).tipVozila().equals("Automobil") && ukupnoZaPlacanje > Main.MAKSIMALNA_ZARADA.get("Automobil").getValue()) {
                Main.MAKSIMALNA_ZARADA.put("Automobil", new Pair<>(idVozila, ukupnoZaPlacanje));
            } else if (Main.VOZILA.get(idVozila).tipVozila().equals("Bicikl") && ukupnoZaPlacanje > Main.MAKSIMALNA_ZARADA.get("Bicikl").getValue()) {
                Main.MAKSIMALNA_ZARADA.put("Bicikl", new Pair<>(idVozila, ukupnoZaPlacanje));
            } else if (Main.VOZILA.get(idVozila).tipVozila().equals("Trotinet") && ukupnoZaPlacanje > Main.MAKSIMALNA_ZARADA.get("Trotinet").getValue()) {
                Main.MAKSIMALNA_ZARADA.put("Trotinet", new Pair<>(idVozila, ukupnoZaPlacanje));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metoda za izračunavanje sumarnih izvještaja (ukupnih troškova kompanije i ukupnog poreza)
     */
    public static void izracunajRezultatePoslovanja() {
        UKUPNI_TROSKOVI_KOMPANIJE = UKUPAN_PRIHOD * 0.2;
        UKUPAN_POREZ = (UKUPAN_PRIHOD - UKUPAN_IZNOS_ZA_ODRZAVANJE - UKUPAN_IZNOS_ZA_POPRAVKE_KVAROVA - UKUPNI_TROSKOVI_KOMPANIJE) * 0.1;
    }
}

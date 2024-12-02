package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.iznajmljivanje;

import javafx.scene.paint.Color;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.KontrolerMapa;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.KontrolerPrevoznaSredstva;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.Main;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.kvar.Kvar;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.racun.Racun;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Klasa koja predstavlja proces iznajmljivanja vozila.
 * Nasljeđuje {@link Thread} i omogućava simulaciju putovanja vozila,
 * upravljanje stanjem baterije i generisanje računa na osnovu podataka o iznajmljivanju.
 */
public class Iznajmljivanje extends Thread {
    private static int brojacPopusta = 0;

    private LocalDateTime datumVrijeme;
    private String imeKorisnika;
    private String idVozila;
    private int pocetnaLokacijaX;
    private int pocetnaLokacijaY;
    private int odredisteX;
    private int odredisteY;
    private int trajanje;
    private boolean kvar;
    private boolean promocija;
    private static KontrolerMapa kontrolerMapa;
    private static KontrolerPrevoznaSredstva kontrolerPrevoznaSredstva;

    /**
     * Kreira novi objekat {@code Iznajmljivanje} sa podrazumijevanim vrijednostima.
     * Inicijalizuje nit kao daemon nit.
     */
    public Iznajmljivanje() {
        super();
        setDaemon(true);
    }

    /**
     * Kreira novi objekat {@code Iznajmljivanje} sa zadatim {@link KontrolerMapa}.
     *
     * @param kontrolerMapa             Kontroler za upravljanje mapom
     * @param kontrolerPrevoznaSredstva Kontroler za prikaz tablea prevoznih sredstava
     */
    public Iznajmljivanje(KontrolerMapa kontrolerMapa, KontrolerPrevoznaSredstva kontrolerPrevoznaSredstva) {
        Iznajmljivanje.kontrolerMapa = kontrolerMapa;
        Iznajmljivanje.kontrolerPrevoznaSredstva = kontrolerPrevoznaSredstva;
        setDaemon(true);
    }

    /**
     * Kreira novi objekat {@code Iznajmljivanje} sa specificiranim vrijednostima.
     *
     * @param datumVrijeme     Datum i vrijeme iznajmljivanja u formatu "d.M.yyyy HH:mm"
     * @param imeKorisnika     Ime korisnika koji iznajmljuje vozilo
     * @param idVozila         Identifikator iznajmljenog vozila
     * @param pocetnaLokacijaX Početna X lokacija vozila
     * @param pocetnaLokacijaY Početna Y lokacija vozila
     * @param odredisteX       Odredišna X lokacija vozila
     * @param odredisteY       Odredišna Y lokacija vozila
     * @param trajanje         Trajanje putovanja u sekundama
     * @param kvar             Da li je došlo do kvara (true) ili ne (false)
     * @param promocija        Da li je korišćena promocija (true) ili ne (false)
     */
    public Iznajmljivanje(String datumVrijeme, String imeKorisnika, String idVozila, int pocetnaLokacijaX, int pocetnaLokacijaY,
                          int odredisteX, int odredisteY, int trajanje, boolean kvar, boolean promocija) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");
        this.datumVrijeme = LocalDateTime.parse(datumVrijeme, dtf);
        this.imeKorisnika = imeKorisnika;
        this.idVozila = idVozila;
        this.pocetnaLokacijaX = pocetnaLokacijaX;
        this.pocetnaLokacijaY = pocetnaLokacijaY;
        this.odredisteX = odredisteX;
        this.odredisteY = odredisteY;
        this.trajanje = trajanje;
        this.kvar = kvar;
        this.promocija = promocija;
        setDaemon(true);
    }

    /**
     * Vraća datum i vrijeme iznajmljivanja.
     *
     * @return Datum i vrijeme iznajmljivanja
     */
    public LocalDateTime getDatumVrijeme() {
        return datumVrijeme;
    }

    /**
     * Postavlja datum i vrijeme iznajmljivanja.
     *
     * @param datumVrijeme Datum i vrijeme iznajmljivanja u formatu "d.M.yyyy HH:mm"
     */
    public void setDatumVrijeme(String datumVrijeme) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");
        this.datumVrijeme = LocalDateTime.parse(datumVrijeme, dtf);
    }

    /**
     * Vraća ime korisnika koji iznajmljuje vozilo.
     *
     * @return Ime korisnika
     */
    public String getImeKorisnika() {
        return imeKorisnika;
    }

    /**
     * Postavlja ime korisnika koji iznajmljuje vozilo.
     *
     * @param imeKorisnika Ime korisnika
     */
    public void setImeKorisnika(String imeKorisnika) {
        this.imeKorisnika = imeKorisnika;
    }

    /**
     * Vraća identifikator iznajmljenog vozila.
     *
     * @return Identifikator vozila
     */
    public String getIdVozila() {
        return idVozila;
    }

    /**
     * Postavlja identifikator iznajmljenog vozila.
     *
     * @param idVozila Identifikator vozila
     */
    public void setIdVozila(String idVozila) {
        this.idVozila = idVozila;
    }

    /**
     * Vraća početnu X lokaciju vozila.
     *
     * @return Početna X lokacija
     */
    public int getPocetnaLokacijaX() {
        return pocetnaLokacijaX;
    }

    /**
     * Postavlja početnu X lokaciju vozila.
     *
     * @param pocetnaLokacijaX Početna X lokacija
     */
    public void setPocetnaLokacijaX(int pocetnaLokacijaX) {
        this.pocetnaLokacijaX = pocetnaLokacijaX;
    }

    /**
     * Vraća početnu Y lokaciju vozila.
     *
     * @return Početna Y lokacija
     */
    public int getPocetnaLokacijaY() {
        return pocetnaLokacijaY;
    }

    /**
     * Postavlja početnu Y lokaciju vozila.
     *
     * @param pocetnaLokacijaY Početna Y lokacija
     */
    public void setPocetnaLokacijaY(int pocetnaLokacijaY) {
        this.pocetnaLokacijaY = pocetnaLokacijaY;
    }

    /**
     * Vraća odredišnu X lokaciju vozila.
     *
     * @return Odredišna X lokacija
     */
    public int getOdredisteX() {
        return odredisteX;
    }

    /**
     * Postavlja odredišnu X lokaciju vozila.
     *
     * @param odredisteX Odredišna X lokacija
     */
    public void setOdredisteX(int odredisteX) {
        this.odredisteX = odredisteX;
    }

    /**
     * Vraća odredišnu Y lokaciju vozila.
     *
     * @return Odredišna Y lokacija
     */
    public int getOdredisteY() {
        return odredisteY;
    }

    /**
     * Postavlja odredišnu Y lokaciju vozila.
     *
     * @param odredisteY Odredišna Y lokacija
     */
    public void setOdredisteY(int odredisteY) {
        this.odredisteY = odredisteY;
    }

    /**
     * Vraća trajanje putovanja u sekundama.
     *
     * @return Trajanje putovanja
     */
    public int getTrajanje() {
        return trajanje;
    }

    /**
     * Postavlja trajanje putovanja u sekundama.
     *
     * @param trajanje Trajanje putovanja
     */
    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    /**
     * Vraća stanje kvara vozila.
     *
     * @return {@code true} ako je došlo do kvara, {@code false} inače
     */
    public boolean isKvar() {
        return kvar;
    }

    /**
     * Postavlja stanje kvara vozila.
     *
     * @param kvar {@code true} ako je došlo do kvara, {@code false} inače
     */
    public void setKvar(boolean kvar) {
        this.kvar = kvar;
    }

    /**
     * Vraća stanje promocije.
     *
     * @return {@code true} ako je korišćena promocija, {@code false} inače
     */
    public boolean isPromocija() {
        return promocija;
    }

    /**
     * Postavlja status promocije za ovu instancu iznajmljivanja.
     *
     * @param promocija {@code true} ako je promocija aktivna, {@code false} inače.
     */
    public void setPromocija(boolean promocija) {
        this.promocija = promocija;
    }

    /**
     * Vraća String reprezentaciju objekta klase {@code Iznajmljivanje}.
     *
     * Formatirani String uključuje informacije o datumu i vremenu iznajmljivanja, imenu korisnika, ID vozila,
     * početnoj i odredišnoj lokaciji, trajanju iznajmljivanja, statusu kvara i promociji.
     *
     * @return String koji predstavlja informacije o ovoj instanci iznajmljivanja.
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");
        return "Iznajmljivanje [datumVrijeme=" + datumVrijeme.format(dtf) + ", imeKorisnika=" + imeKorisnika + ", idVozila="
                + idVozila + ", pocetnaLokacija=(" + pocetnaLokacijaX + ", " + pocetnaLokacijaY + "), odrediste=(" +
                odredisteX + ", " + odredisteY + "), " + "trajanje=" + trajanje + ", kvar=" + kvar + ", promocija=" +
                promocija + "]";
    }

    /**
     * Pokreće simulaciju iznajmljivanja vozila. Ova metoda se izvršava u zasebnom threadu.
     *
     * Simulacija uključuje:
     * <ul>
     *     <li>Postavljanje statusa računanja da li je iznajmljivanje u širem dijelu grada.</li>
     *     <li>Postavljanje statusa promocije i trajanja iznajmljivanja u računu.</li>
     *     <li>Simulaciju kretanja vozila od početne lokacije do odredišta, uz smanjenje nivoa baterije.</li>
     *     <li>Postavljanje statusa kvara i promocije u slučaju kvara vozila.</li>
     *     <li>Upisivanje računa u fajl.</li>
     * </ul>
     *
     * Ako se tokom simulacije nivo baterije vozila spusti ispod nule, simulacija se prekida i vozilo se označava kao neispravno.
     */
    @Override
    public void run() {

        Racun racun = new Racun();
        if (pocetnaLokacijaX < 5 || pocetnaLokacijaX > 14 || pocetnaLokacijaY < 5 || pocetnaLokacijaY > 14 ||
                odredisteX < 5 || odredisteX > 14 || odredisteY < 5 || odredisteY > 14) {
            racun.setSiriDioGrada(true);
        } else {
            racun.setSiriDioGrada(false);
        }
        racun.setPromocija(promocija);
        racun.setTrajanje(trajanje);
        racun.setDatumVrijeme(datumVrijeme.plusSeconds((long) trajanje));
        if (!kvar) {
            racun.setKvar(false);
            racun.setPopust((++brojacPopusta) % 10 == 0);
            int distanca = Math.abs(pocetnaLokacijaX - odredisteX) + Math.abs(pocetnaLokacijaY - odredisteY);
            long vrijemeZadrzavanja = (long) ((double) trajanje / distanca * 1000);
            int i, j;
            boolean indikatorBaterije = true;
            Color color;
            if (Main.VOZILA.get(idVozila).tipVozila().equals("Automobil")) {
                color = Color.rgb(255, 0, 0, 0.8);
            } else if (Main.VOZILA.get(idVozila).tipVozila().equals("Bicikl")) {
                color = Color.rgb(0, 255, 0, 0.8);
            } else {
                color = Color.rgb(0, 0, 255, 0.8);
            }

            for (i = pocetnaLokacijaX; i != odredisteX; ) {
                kontrolerMapa.pozicioniranjeAuta(i, pocetnaLokacijaY, Main.VOZILA.get(idVozila).getId(), Main.VOZILA.get(idVozila).getTrenutniNivoBaterije(), color);
                Main.VOZILA.get(idVozila).umanjiNivoBaterije();
                kontrolerPrevoznaSredstva.osvjeziTabeleVozila();
                if (Main.VOZILA.get(idVozila).getTrenutniNivoBaterije() < 0) {
                    indikatorBaterije = false;
                    Main.VOZILA.get(idVozila).setTrenutniNivoBaterije(0);
                    System.out.println(Main.VOZILA.get(idVozila) + " nije zavrsio simulaciju, jer se ispraznila baterija.");
                    break;
                }
                try {
                    sleep(vrijemeZadrzavanja);
                    kontrolerMapa.depozicioniranjeAuta(i, pocetnaLokacijaY, Main.VOZILA.get(idVozila).getId(), Main.VOZILA.get(idVozila).getTrenutniNivoBaterije() + 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i = (pocetnaLokacijaX < odredisteX) ? i + 1 : i - 1;
            }

            if (indikatorBaterije) {
                for (j = pocetnaLokacijaY; j != odredisteY; ) {
                    kontrolerMapa.pozicioniranjeAuta(i, j, Main.VOZILA.get(idVozila).getId(), Main.VOZILA.get(idVozila).getTrenutniNivoBaterije(), color);
                    Main.VOZILA.get(idVozila).umanjiNivoBaterije();
                    kontrolerPrevoznaSredstva.osvjeziTabeleVozila();
                    if (Main.VOZILA.get(idVozila).getTrenutniNivoBaterije() < 0) {
                        indikatorBaterije = false;
                        Main.VOZILA.get(idVozila).setTrenutniNivoBaterije(0);
                        System.out.println(Main.VOZILA.get(idVozila) + " nije zavrsilo simulaciju, jer se ispraznila baterija.");
                        break;
                    }
                    try {
                        sleep(vrijemeZadrzavanja);
                        kontrolerMapa.depozicioniranjeAuta(i, j, Main.VOZILA.get(idVozila).getId(), Main.VOZILA.get(idVozila).getTrenutniNivoBaterije() + 2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    j = (pocetnaLokacijaY < odredisteY) ? j + 1 : j - 1;
                }
                if (indikatorBaterije) {
                    kontrolerMapa.pozicioniranjeAuta(i, j, Main.VOZILA.get(idVozila).getId(), Main.VOZILA.get(idVozila).getTrenutniNivoBaterije(), color);
                }
            }
            Main.VOZILA.get(idVozila).punjenjeBaterije(100 - Main.VOZILA.get(idVozila).getTrenutniNivoBaterije());
        } else {
            racun.setKvar(true);
            racun.setPopust(false);
            Kvar.KVAROVI.add(new Kvar(Main.VOZILA.get(idVozila).tipVozila(), idVozila, datumVrijeme, Main.VOZILA.get(idVozila).porukaKvara()));
        }
        racun.upisiRacunUFajl(idVozila, imeKorisnika);
    }
}

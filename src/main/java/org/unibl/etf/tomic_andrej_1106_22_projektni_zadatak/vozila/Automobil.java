package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Klasa koja predstavlja automobil kao specifičnu vrstu vozila.
 * Nasljeđuje {@link Vozilo} klasu i dodaje atribute specifične za automobile,
 * uključujući datum nabavke i opis vozila.
 */
public class Automobil extends Vozilo {
    private LocalDate datumNabavke;
    private String opis;

    /**
     * Kreira novi objekat tipa {@code Automobil} sa podrazumijevanim vrijednostima.
     * Poziva konstruktor nadklase {@link Vozilo}.
     */
    public Automobil() {
        super();
    }

    /**
     * Kreira novi objekat tipa {@code Automobil} sa specificiranim vrijednostima.
     *
     * @param id             Identifikator automobila
     * @param datumNabavke   Datum nabavke automobila
     * @param cijenaNabavke  Cijena nabavke automobila
     * @param proizvodjac    Proizvodjač automobila
     * @param model          Model automobila
     * @param opis           Opis automobila
     */
    public Automobil(String id, LocalDate datumNabavke, double cijenaNabavke, String proizvodjac, String model, String opis) {
        super(id, proizvodjac, model, cijenaNabavke);
        this.datumNabavke = datumNabavke;
        this.opis = opis;
    }

    /**
     * Vraća datum nabavke automobila.
     *
     * @return Datum nabavke automobila
     */
    public LocalDate getDatumNabavke() {
        return datumNabavke;
    }

    /**
     * Postavlja datum nabavke automobila.
     *
     * @param datumNabavke Datum nabavke automobila
     */
    public void setDatumNabavke(LocalDate datumNabavke) {
        this.datumNabavke = datumNabavke;
    }

    /**
     * Vraća opis automobila.
     *
     * @return Opis automobila
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis automobila.
     *
     * @param opis Opis automobila
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * Vraća poruku o kvaru specifičnu za automobil.
     *
     * @return Poruka o kvaru automobila
     */
    @Override
    public String porukaKvara() {
        return "Automobil se pokvario";
    }

    /**
     * Vraća tip vozila kao string.
     *
     * @return Tip vozila ("Automobil")
     */
    @Override
    public String tipVozila() {
        return "Automobil";
    }

    /**
     * Vraća detalje o automobilu kao string, uključujući datum nabavke i opis.
     * Datum nabavke je formatiran kao "d.M.yyyy.".
     *
     * @return Detalji o automobilu
     */
    @Override
    public String detaljiVozila() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy.");
        return ", datumNabavke: " + datumNabavke.format(dtf) + ", opis: " + opis;
    }
}

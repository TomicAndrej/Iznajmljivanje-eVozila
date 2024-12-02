package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila;

import java.io.Serializable;

/**
 * Apstraktna klasa koja predstavlja osnovnu strukturu vozila.
 * Ova klasa implementira {@link Serializable} interfejs i pruža osnovne atribute i metode
 * za rad sa vozilima, kao što su identifikacija, proizvodjač, model, nivo baterije i cijena nabavke.
 */
public abstract class Vozilo implements Serializable {
    protected String id;
    protected String proizvodjac;
    protected String model;
    protected int trenutniNivoBaterije = 100;
    protected double cijenaNabavke;

    /**
     * Kreira novo vozilo sa podrazumijevanim atributima.
     */
    public Vozilo() {
        super();
    }

    /**
     * Kreira novo vozilo sa specificiranim ID-jem, proizvodjačem, modelom i cijenom nabavke.
     *
     * @param id             Identifikator vozila
     * @param proizvodjac    Proizvodjač vozila
     * @param model          Model vozila
     * @param cijenaNabavke  Cena nabavke vozila
     */
    public Vozilo(String id, String proizvodjac, String model, double cijenaNabavke) {
        this.id = id;
        this.proizvodjac = proizvodjac;
        this.model = model;
        this.cijenaNabavke = cijenaNabavke;
    }

    /**
     * Vraća ID vozila.
     *
     * @return ID vozila
     */
    public String getId() {
        return id;
    }

    /**
     * Postavlja ID vozila.
     *
     * @param id ID vozila
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Vraća proizvodjača vozila.
     *
     * @return Proizvodjač vozila
     */
    public String getProizvodjac() {
        return proizvodjac;
    }

    /**
     * Postavlja proizvodjača vozila.
     *
     * @param proizvodjac Proizvodjač vozila
     */
    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    /**
     * Vraća model vozila.
     *
     * @return Model vozila
     */
    public String getModel() {
        return model;
    }

    /**
     * Postavlja model vozila.
     *
     * @param model Model vozila
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Vraća trenutni nivo baterije vozila.
     *
     * @return Trenutni nivo baterije
     */
    public int getTrenutniNivoBaterije() {
        return trenutniNivoBaterije;
    }

    /**
     * Postavlja trenutni nivo baterije vozila.
     *
     * @param trenutniNivoBaterije Trenutni nivo baterije
     */
    public void setTrenutniNivoBaterije(int trenutniNivoBaterije) {
        this.trenutniNivoBaterije = trenutniNivoBaterije;
    }

    /**
     * Vraća cijenu nabavke vozila.
     *
     * @return Cijena nabavke vozila
     */
    public double getCijenaNabavke() {
        return cijenaNabavke;
    }

    /**
     * Postavlja cijenu nabavke vozila.
     *
     * @param cijenaNabavke Cijena nabavke vozila
     */
    public void setCijenaNabavke(double cijenaNabavke) {
        this.cijenaNabavke = cijenaNabavke;
    }

    /**
     * Povećava nivo baterije za navedeni iznos.
     *
     * @param nivoZaKojiSePuni Nivo za koji se puni baterija
     */
    public void punjenjeBaterije(int nivoZaKojiSePuni) {
        trenutniNivoBaterije += nivoZaKojiSePuni;
    }

    /**
     * Smanjuje trenutni nivo baterije za 2 (simulacija trošenja baterije).
     */
    public void umanjiNivoBaterije() {
        this.trenutniNivoBaterije -= 2;
    }

    /**
     * Vraća poruku koja opisuje kvar na vozilu.
     * Ova metoda mora biti implementirana u konkretnim podklasama.
     *
     * @return Poruka o kvaru
     */
    public abstract String porukaKvara();

    /**
     * Vraća tip vozila kao string.
     * Ova metoda mora biti implementirana u konkretnim podklasama.
     *
     * @return Tip vozila
     */
    public abstract String tipVozila();

    /**
     * Vraća detalje o vozilu kao string.
     * Ova metoda mora biti implementirana u konkretnim podklasama.
     *
     * @return Detalji o vozilu
     */
    public abstract String detaljiVozila();

    /**
     * Vraća string reprezentaciju vozila koja uključuje tip, ID, proizvodjača, model,
     * trenutni nivo baterije i detalje o vozilu.
     *
     * @return String reprezentacija vozila
     */
    @Override
    public String toString() {
        return tipVozila() + " [id=" + id + ", proizvodjac=" + proizvodjac + ", model=" + model + ", trenutniNivoBaterije=" +
                trenutniNivoBaterije + detaljiVozila() + "]";
    }
}

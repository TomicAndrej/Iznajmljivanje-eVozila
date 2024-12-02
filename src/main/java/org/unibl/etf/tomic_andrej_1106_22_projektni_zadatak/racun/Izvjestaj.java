package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.racun;

/**
 * Predstavlja izveštaj sa kategorijom i vrijednošću.
 * Ova klasa se koristi za skladištenje informacija o određenim kategorijama i njihovim vrednostima, koristi se
 * za prikaz dnevnih i sumarnih podataka u sceni za prikaz izvještaja.
 */
public class Izvjestaj {
    private String kategorija;
    private Double vrijednost;

    /**
     * Kreira novi izvještaj sa specificiranom kategorijom i vrijednošću.
     *
     * @param kategorija  Kategorija izvještaja
     * @param vrijednost  Vrijednost u okviru navedene kategorije
     */
    public Izvjestaj(String kategorija, Double vrijednost) {
        this.kategorija = kategorija;
        this.vrijednost = vrijednost;
    }

    /**
     * Vraća kategoriju izvještaja.
     *
     * @return Kategorija izvještaja
     */
    public String getKategorija() {
        return kategorija;
    }

    /**
     * Vraća vrijednost izvještaja.
     *
     * @return Vrijednost izvještaja
     */
    public Double getVrijednost() {
        return vrijednost;
    }
}

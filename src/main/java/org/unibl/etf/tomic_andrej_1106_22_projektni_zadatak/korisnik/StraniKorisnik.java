package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.korisnik;

/**
 * Klasa koja predstavlja stranog korisnika u sistemu. Ova klasa nasljeđuje klasu {@link Korisnik}
 * i dodaje dodatne informacije specifične za strane korisnike, kao što je broj pasoša.
 *
 * <p>Svaki strani korisnik ima jedinstven broj pasoša koji se automatski generiše prilikom
 * kreiranja objekta stranog korisnika. Ova klasa koristi statičku promjenljivu {@code pasos} za
 * generisanje jedinstvenih brojeva pasoša.</p>
 *
 * @see Korisnik
 */
public class StraniKorisnik extends Korisnik {

    /**
     * Statička promjenljiva koja se koristi za generisanje jedinstvenih brojeva pasoša.
     */
    private static int pasos = 0;

    /**
     * Broj pasoša stranog korisnika.
     */
    private String brojPasosa;

    /**
     * Kreira novog stranog korisnika sa podrazumijevanim brojem pasoša.
     * Broj pasoša se automatski generiše.
     */
    public StraniKorisnik() {
        super();
        this.brojPasosa = "P" + pasos++;
    }

    /**
     * Kreira novog stranog korisnika sa zadatim imenom i automatski generiše broj pasoša.
     *
     * @param imeKorisnika Ime stranog korisnika.
     */
    public StraniKorisnik(String imeKorisnika) {
        super(imeKorisnika);
        this.brojPasosa = "P" + pasos++;
    }

    /**
     * Vraća broj pasoša stranog korisnika.
     *
     * @return Broj pasoša stranog korisnika.
     */
    public String getBrojPasosa() {
        return brojPasosa;
    }
}

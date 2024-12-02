package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.korisnik;

/**
 * Klasa koja predstavlja domaćeg korisnika u sistemu. Ova klasa nasljeđuje klasu {@link Korisnik}
 * i dodaje dodatne informacije specifične za domaće korisnike, kao što je broj lične karte.
 *
 * <p>Svaki domaći korisnik ima jedinstven broj lične karte koji se automatski generiše prilikom
 * kreiranja objekta domaćeg korisnika. Ova klasa koristi statičku promjenljivu {@code licna} za
 * generisanje jedinstvenih brojeva ličnih karata.</p>
 *
 * @see Korisnik
 */
public class DomaciKorisnik extends Korisnik {

    /**
     * Statička promenljiva koja se koristi za generisanje jedinstvenih brojeva ličnih karata.
     */
    private static int licna = 0;

    /**
     * Broj lične karte domaćeg korisnika.
     */
    private String brojLicneKarte;

    /**
     * Kreira novog domaćeg korisnika sa podrazumijevanim brojem lične karte.
     * Broj lične karte se automatski generiše.
     */
    public DomaciKorisnik() {
        super();
        this.brojLicneKarte = "L" + licna++;
    }

    /**
     * Kreira novog domaćeg korisnika sa zadatim imenom i automatski generiše broj lične karte.
     *
     * @param imeKorisnika Ime domaćeg korisnika.
     */
    public DomaciKorisnik(String imeKorisnika) {
        super(imeKorisnika);
        this.brojLicneKarte = "L" + licna++;
    }

    /**
     * Vraća broj lične karte domaćeg korisnika.
     *
     * @return Broj lične karte domaćeg korisnika.
     */
    public String getBrojLicneKarte() {
        return brojLicneKarte;
    }
}

package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.korisnik;

/**
 * Apstraktna klasa koja predstavlja korisnika sistema. Ova klasa sadrži osnovne informacije o korisniku,
 * kao što su ime korisnika i broj vozačke dozvole. Broj vozačke dozvole automatski se generiše prilikom
 * kreiranja objekta korisnika.
 *
 * <p>Svaki korisnik ima jedinstven broj vozačke dozvole koji se generiše automatski na osnovu statičke
 * promjenljive {@code vozacka}. Takođe, korisnik može imati ime koje se može postaviti i dohvatiti.</p>
 *
 * <p>Ova klasa je apstraktna i ne može se direktno instancirati. Može se koristiti kao osnovna klasa za
 * specifične vrste korisnika.</p>
 *
 * @see org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.korisnik
 */
public abstract class Korisnik {

    /**
     * Statička promjenljiva koja se koristi za generisanje jedinstvenih brojeva vozačkih dozvola.
     */
    private static int vozacka = 0;

    /**
     * Ime korisnika.
     */
    protected String imeKorisnika;

    /**
     * Broj vozačke dozvole korisnika.
     */
    protected String brojVozacke;

    /**
     * Kreira novog korisnika sa podrazumijevanim brojem vozačke dozvole.
     * Broj vozačke dozvole se automatski generiše.
     */
    public Korisnik() {
        super();
        this.brojVozacke = "V" + vozacka++;
    }

    /**
     * Kreira novog korisnika sa zadatim imenom.
     * Broj vozačke dozvole se automatski generiše.
     *
     * @param imeKorisnika Ime korisnika.
     */
    public Korisnik(String imeKorisnika) {
        this.imeKorisnika = imeKorisnika;
        this.brojVozacke = "V" + vozacka++;
    }

    /**
     * Vraća ime korisnika.
     *
     * @return Ime korisnika.
     */
    public String getImeKorisnika() {
        return imeKorisnika;
    }

    /**
     * Postavlja ime korisnika.
     *
     * @param imeKorisnika Novo ime korisnika.
     */
    public void setImeKorisnika(String imeKorisnika) {
        this.imeKorisnika = imeKorisnika;
    }

    /**
     * Vraća broj vozačke dozvole korisnika.
     *
     * @return Broj vozačke dozvole korisnika.
     */
    public String getBrojVozacke() {
        return brojVozacke;
    }
}

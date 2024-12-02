package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila;

/**
 * Klasa koja predstavlja trotinet kao specifičnu vrstu vozila.
 * Nasljeđuje {@link Vozilo} klasu i dodaje atribut specifičan za trotinet,
 * uključujući maksimalnu brzinu.
 */
public class Trotinet extends Vozilo {
    private double maksimalnaBrzina;

    /**
     * Kreira novi objekat tipa {@code Trotinet} sa zadatim vrednostima.
     * Poziva konstruktor nadklase {@link Vozilo}.
     */
    public Trotinet() {
        super();
    }

    /**
     * Kreira novi objekat tipa {@code Trotinet} sa specificiranim vrijednostima.
     *
     * @param id               Identifikator trotineta
     * @param proizvodjac      Proizvodjač trotineta
     * @param model            Model trotineta
     * @param cijenaNabavke    Cijena nabavke trotineta
     * @param maksimalnaBrzina Maksimalna brzina trotineta u kilometrima na sat
     */
    public Trotinet(String id, String proizvodjac, String model, double cijenaNabavke, double maksimalnaBrzina) {
        super(id, proizvodjac, model, cijenaNabavke);
        this.maksimalnaBrzina = maksimalnaBrzina;
    }

    /**
     * Vraća maksimalnu brzinu trotineta.
     *
     * @return Maksimalna brzina trotineta u kilometrima na sat
     */
    public double getMaksimalnaBrzina() {
        return maksimalnaBrzina;
    }

    /**
     * Postavlja maksimalnu brzinu trotineta.
     *
     * @param maksimalnaBrzina Maksimalna brzina trotineta u kilometrima na sat
     */
    public void setMaksimalnaBrzina(double maksimalnaBrzina) {
        this.maksimalnaBrzina = maksimalnaBrzina;
    }

    /**
     * Vraća poruku o kvaru specifičnu za trotinet.
     *
     * @return Poruka o kvaru trotineta
     */
    @Override
    public String porukaKvara() {
        return "Trotinet se pokvario";
    }

    /**
     * Vraća tip vozila kao string.
     *
     * @return Tip vozila ("Trotinet")
     */
    @Override
    public String tipVozila() {
        return "Trotinet";
    }

    /**
     * Vraća detalje o trotinetu kao string, uključujući maksimalnu brzinu.
     *
     * @return Detalji o trotinetu
     */
    @Override
    public String detaljiVozila() {
        return ", maksimalnaBrzina=" + maksimalnaBrzina;
    }
}

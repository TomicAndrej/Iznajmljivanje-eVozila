package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila;

/**
 * Klasa koja predstavlja bicikl kao specifičnu vrstu vozila.
 * Nasljeđuje {@link Vozilo} klasu i dodaje atribut specifičan za bicikle,
 * uključujući domet bicikla.
 */
public class Bicikl extends Vozilo {
    private double domet;

    /**
     * Kreira novi objekat tipa {@code Bicikl} sa podrazumijevanim vrijednostima.
     * Poziva konstruktor nadklase {@link Vozilo}.
     */
    public Bicikl() {
        super();
    }

    /**
     * Kreira novi objekat tipa {@code Bicikl} sa specificiranim vrijednostima.
     *
     * @param id             Identifikator bicikla
     * @param proizvodjac    Proizvodjač bicikla
     * @param model          Model bicikla
     * @param cijenaNabavke  Cijena nabavke bicikla
     * @param domet          Domet bicikla u kilometrima
     */
    public Bicikl(String id, String proizvodjac, String model, double cijenaNabavke, double domet) {
        super(id, proizvodjac, model, cijenaNabavke);
        this.domet = domet;
    }

    /**
     * Vraća domet bicikla.
     *
     * @return Domet bicikla u kilometrima
     */
    public double getDomet() {
        return domet;
    }

    /**
     * Postavlja domet bicikla.
     *
     * @param domet Domet bicikla u kilometrima
     */
    public void setDomet(double domet) {
        this.domet = domet;
    }

    /**
     * Vraća poruku o kvaru specifičnu za bicikl.
     *
     * @return Poruka o kvaru bicikla
     */
    @Override
    public String porukaKvara() {
        return "Bicikl se pokvario";
    }

    /**
     * Vraća tip vozila kao string.
     *
     * @return Tip vozila ("Bicikl")
     */
    @Override
    public String tipVozila() {
        return "Bicikl";
    }

    /**
     * Vraća detalje o biciklu kao string, uključujući domet bicikla.
     *
     * @return Detalji o biciklu
     */
    @Override
    public String detaljiVozila() {
        return ", domet: " + domet;
    }
}

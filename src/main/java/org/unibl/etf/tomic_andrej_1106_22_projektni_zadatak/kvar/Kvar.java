package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.kvar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa koja predstavlja kvar na prevoznom sredstvu. Ova klasa sadrži informacije o vrsti
 * prevoznog sredstva, identifikatoru pokvarenog vozila, datumu i vremenu kada je kvar nastao,
 * kao i opis kvara.
 *
 * <p>Klasa takođe sadrži statičku listu {@code KVAROVI} koja služi za skladištenje svih
 * objekata tipa {@code Kvar} u sistemu.</p>
 *
 * @see java.time.LocalDateTime
 */
public class Kvar {
    /**
     * Statička lista kvarova u sistemu.
     */
    public static List<Kvar> KVAROVI = new ArrayList<Kvar>();

    /**
     * Vrsta prevoznog sredstva na kojem je nastao kvar.
     */
    private String vrstaPrevoznogSredstva;

    /**
     * Identifikator vozila na kom je nastao kvar.
     */
    private String id;

    /**
     * Datum i vrijeme kada je kvar nastao.
     */
    private LocalDateTime datumVrijeme;

    /**
     * Opis kvara koji pruža dodatne informacije o prirodi kvara.
     */
    private String opisKvara;

    /**
     * Kreira novi objekat kvara sa podrazumevanim vrijednostima.
     */
    public Kvar() {
        super();
    }

    /**
     * Kreira novi objekat kvara sa zadatim vrijednostima za vrstu prevoznog sredstva, identifikator,
     * datum i vrijeme, i opis kvara.
     *
     * @param vrstaPrevoznogSredstva Vrsta prevoznog sredstva na kojem je nastao kvar.
     * @param id Identifikator vozila na kom se desio kvar.
     * @param datumVrijeme Datum i vrijeme kada je kvar nastao.
     * @param opisKvara Opis kvara koji pruža dodatne informacije o prirodi kvara.
     */
    public Kvar(String vrstaPrevoznogSredstva, String id, LocalDateTime datumVrijeme, String opisKvara) {
        this.vrstaPrevoznogSredstva = vrstaPrevoznogSredstva;
        this.id = id;
        this.datumVrijeme = datumVrijeme;
        this.opisKvara = opisKvara;
    }

    /**
     * Vraća vrstu prevoznog sredstva na kojem je nastao kvar.
     *
     * @return Vrsta prevoznog sredstva.
     */
    public String getVrstaPrevoznogSredstva() {
        return vrstaPrevoznogSredstva;
    }

    /**
     * Postavlja vrstu prevoznog sredstva na kojem je nastao kvar.
     *
     * @param vrstaPrevoznogSredstva Vrsta prevoznog sredstva.
     */
    public void setVrstaPrevoznogSredstva(String vrstaPrevoznogSredstva) {
        this.vrstaPrevoznogSredstva = vrstaPrevoznogSredstva;
    }

    /**
     * Vraća identifikator vozila na kom je nastao kvar.
     *
     * @return Identifikator vozila.
     */
    public String getId() {
        return id;
    }

    /**
     * Postavlja identifikator vozila na kom se desio kvar.
     *
     * @param id Identifikator vozila.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Vraća datum i vrijeme kada je kvar nastao.
     *
     * @return Datum i vrijeme nastanka kvara.
     */
    public LocalDateTime getDatumVrijeme() {
        return datumVrijeme;
    }

    /**
     * Postavlja datum i vrijeme kada je kvar nastao.
     *
     * @param datumVrijeme Datum i vrijeme nastanka kvara.
     */
    public void setDatumVrijeme(LocalDateTime datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    /**
     * Vraća opis kvara koji pruža dodatne informacije o prirodi kvara.
     *
     * @return Opis kvara.
     */
    public String getOpisKvara() {
        return opisKvara;
    }

    /**
     * Postavlja opis kvara koji pruža dodatne informacije o prirodi kvara.
     *
     * @param opisKvara Opis kvara.
     */
    public void setOpisKvara(String opisKvara) {
        this.opisKvara = opisKvara;
    }
}

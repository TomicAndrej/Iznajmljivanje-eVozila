package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.ucitavanje;

import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.KontrolerMapa;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.KontrolerPrevoznaSredstva;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.iznajmljivanje.Iznajmljivanje;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.properties_ucitavanje.PropertiesUcitavanje;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila.Vozilo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Klasa {@code UcitavanjeIznajmljivanje} je odgovorna za učitavanje podataka o iznajmljivanjima vozila iz CSV fajla.
 *
 * <p>Ova klasa pruža metodu za učitavanje podataka o iznajmljivanjima i njihovu validaciju. Podaci se učitavaju iz fajla čija
 * putanja se dobija iz konfiguracionih svojstava. Klasa takođe pruža pomoćne metode za validaciju podataka kao što su datum,
 * koordinate i boolean vrijednosti. Učitani podaci se sortiraju po datumu.</p>
 */
public class UcitavanjeIznajmljivanje {

    /**
     * Učitava iznajmljivanja iz CSV fajla i grupiše ih po datumu.
     *
     * @param vozila                    Kolekcija svih dostupnih vozila koja se koristi za validaciju ID-jeva vozila.
     * @param kontrolerMapa             Kontroler za mapu koji se koristi prilikom kreiranja objekata iznajmljivanja.
     * @param kontrolerPrevoznaSredstva Kontroler za prikaz prevoznih sredstava koji se koristi prilikom kreiranje
     *                                  objekta iznajmljivanja.
     * @return Lista lista objekata {@code Iznajmljivanje} grupisanih po datumu.
     */
    public static List<List<Iznajmljivanje>> ucitajIznajmljivanja(Collection<Vozilo> vozila, KontrolerMapa kontrolerMapa, KontrolerPrevoznaSredstva kontrolerPrevoznaSredstva) {
        List<Iznajmljivanje> iznajmljivanja = new ArrayList<Iznajmljivanje>();
        Set<String> pomocPriValidaciji = new HashSet<String>();

        Properties prop = PropertiesUcitavanje.ucitajProperties();
        String putanjaFajla = prop.getProperty("PUTANJA_IZNAJMLJIVANJA");

        try {
            List<String> redovi = Files.readAllLines(new File(putanjaFajla).toPath());
            redovi.remove(0);
            redovi.stream().forEach(red -> {

                String[] split = dijeljenjeRedaIspravno(red);

                if (split != null && validacijaIznajmljivanja(vozila, split, pomocPriValidaciji)) {
                    Iznajmljivanje iznajmljivanje = new Iznajmljivanje(kontrolerMapa, kontrolerPrevoznaSredstva);

                    iznajmljivanje.setDatumVrijeme(split[0]);
                    iznajmljivanje.setImeKorisnika(split[1]);
                    iznajmljivanje.setIdVozila(split[2]);
                    iznajmljivanje.setPocetnaLokacijaX(Integer.parseInt(split[3].replace("\"", "").split(",")[0]));
                    iznajmljivanje.setPocetnaLokacijaY(Integer.parseInt(split[3].replace("\"", "").split(",")[1]));
                    iznajmljivanje.setOdredisteX(Integer.parseInt(split[4].replace("\"", "").split(",")[0]));
                    iznajmljivanje.setOdredisteY(Integer.parseInt(split[4].replace("\"", "").split(",")[1]));
                    iznajmljivanje.setTrajanje(Integer.parseInt(split[5]));
                    if (split[6].equalsIgnoreCase("da")) {
                        iznajmljivanje.setKvar(true);
                    } else {
                        iznajmljivanje.setKvar(false);
                    }
                    if (split[7].equalsIgnoreCase("da")) {
                        iznajmljivanje.setPromocija(true);
                    } else {
                        iznajmljivanje.setPromocija(false);
                    }

                    iznajmljivanja.add(iznajmljivanje);
                }

                if (split != null) {
                    pomocPriValidaciji.add(split[0] + split[2]);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        iznajmljivanja.sort(Comparator.comparing(Iznajmljivanje::getDatumVrijeme));

        List<List<Iznajmljivanje>> listeIznajmljivanja = new ArrayList<List<Iznajmljivanje>>();
        LocalDateTime termin = iznajmljivanja.get(0).getDatumVrijeme();
        int pocetak = 0;
        for (int i = 0; i < iznajmljivanja.size(); i++) {
            if (!termin.equals(iznajmljivanja.get(i).getDatumVrijeme())) {
                listeIznajmljivanja.add(iznajmljivanja.subList(pocetak, i));
                termin = iznajmljivanja.get(i).getDatumVrijeme();
                pocetak = i;
            }
        }
        listeIznajmljivanja.add(iznajmljivanja.subList(pocetak, iznajmljivanja.size()));

        return listeIznajmljivanja;
    }

    /**
     * Razdvaja red iz CSV fajla na dijelove koristeći odgovarajući delimiter.
     *
     * @param red Red iz CSV fajla koji treba razdvojiti.
     * @return Niz stringova koji predstavlja dijelove reda, ili {@code null} ako red nije validan.
     */
    private static String[] dijeljenjeRedaIspravno(String red) {
        String[] split = red.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        if (split.length != 8) {
            System.out.println("Nije validan red u fajlu.    red: " + red);
            return null;
        }
        return split;
    }

    /**
     * Validira podatke o iznajmljivanju.
     *
     * @param vozila Kolekcija vozila za validaciju ID-jeva vozila.
     * @param split Niz stringova koji predstavlja dijelove reda iz CSV fajla.
     * @param pomocPriValidaciji Skup koji se koristi za pomoć u validaciji duplikata iznajmljivanja.
     * @return {@code true} ako su podaci validni, {@code false} ako nisu validni.
     */
    private static boolean validacijaIznajmljivanja(Collection<Vozilo> vozila, String[] split, Set<String> pomocPriValidaciji) {
        if ("".equals(split[0]) || "".equals(split[1]) || "".equals(split[2]) || "".equals(split[3]) ||
                "".equals(split[4]) || "".equals(split[5]) || "".equals(split[6]) || "".equals(split[7])) {
            if ("".equals(split[0])) {
                System.out.println("Nedostaje datum iznajmljivanja.");
            }
            if ("".equals(split[1])) {
                System.out.println("Nedostaje ime korisnika.");
            }
            if ("".equals(split[2])) {
                System.out.println("Nedostaje ID vozila koje se iznajmljuje.");
            }
            if ("".equals(split[3])) {
                System.out.println("Nedostaje pocetna lokacija.");
            }
            if ("".equals(split[4])) {
                System.out.println("Nedostaje odrediste.");
            }
            if ("".equals(split[5])) {
                System.out.println("Nedostaje trajanje.");
            }
            if ("".equals(split[6])) {
                System.out.println("Nedostaje potvrda da li vozilo ima kvar ili ne.");
            }
            if ("".equals(split[7])) {
                System.out.println("Nedostaje potvrda da li vozilo ima promociju ili ne.");
            }
            return false;
        } else if (!validanDatum(split[0])) {
            System.out.println("Datum i vrijeme nisu validani!");
            return false;
        } else if (vozila.stream().filter(vozilo -> vozilo.getId().equals(split[2])).count() == 0) {
            System.out.println("Vozilo sa id-jem " + split[2] + " ne postoji, tako da se ne moze iznajmiti.");
            return false;
        } else if (!validneKoordinate(split[3].replace("\"", "").split(",")) ||
                !validneKoordinate(split[4].replace("\"", "").split(","))) {
            System.out.println("Koordinate nisu validane!");
            return false;
        } else if (!split[5].matches("\\d+")) {
            System.out.println("Trajanje nije prirodan broj.");
            return false;
        } else if (!validanBoolean(split[6])) {
            System.out.println("Kvar nije validno specificiran!");
            return false;
        } else if (!validanBoolean(split[7])) {
            System.out.println("Promocija nije validno specificirana!");
            return false;
        } else if (pomocPriValidaciji.contains(split[0] + split[2])) {
            System.out.println("Vozilo sa id-jem " + split[2] + " je vec iznajmljeno u ovom terminu (" + split[0] + ").");
            return false;
        }
        return true;
    }

    /**
     * Provjerava da li je datum u ispravnom formatu.
     *
     * @param datum Datum koji treba provjeriti.
     * @return {@code true} ako je datum u ispravnom formatu, {@code false} inače.
     */
    private static boolean validanDatum(String datum) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");
        try {
            LocalDateTime.parse(datum, dtf);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Provjerava da li su koordinate u validnom formatu.
     *
     * @param koordinate Niz stringova koji predstavljaju koordinate.
     * @return {@code true} ako su koordinate validne, {@code false} inače.
     */
    private static boolean validneKoordinate(String[] koordinate) {
        if (koordinate.length != 2) {
            return false;
        }
        try {
            int x = Integer.parseInt(koordinate[0]);
            int y = Integer.parseInt(koordinate[1]);
            if (x < 0 || x > 19 || y < 0 || y > 19) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Provjerava da li je string validna boolean vrednost.
     *
     * @param daNe String koji treba provjeriti.
     * @return {@code true} ako je string validna boolean vrijednost, {@code false} inače.
     */
    private static boolean validanBoolean(String daNe) {
        return daNe.equalsIgnoreCase("da") || daNe.equalsIgnoreCase("ne");
    }
}

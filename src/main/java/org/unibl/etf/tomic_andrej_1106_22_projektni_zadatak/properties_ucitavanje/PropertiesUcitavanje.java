package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.properties_ucitavanje;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Klasa {@code PropertiesUcitavanje} pruža metodu za učitavanje konfiguracionih
 * parametara iz fajla sa ekstenzijom {@code .properties}.
 *
 * <p>Ova klasa omogućava učitavanje svojstava iz fajla koji se nalazi u direktorijumu
 * {@code src/main/resources} u trenutnom radnom direktorijumu aplikacije.</p>
 *
 * @see java.util.Properties
 * @see java.io.FileInputStream
 */
public class PropertiesUcitavanje {

    /**
     * Učitava svojstva iz konfiguracionog fajla {@code parametri.properties}.
     *
     * <p>Fajl se nalazi u direktorijumu {@code src/main/resources} unutar trenutnog
     * radnog direktorijuma aplikacije. Ako dođe do greške prilikom učitavanja fajla,
     * greška se ispisuje na standardni izlaz grešaka.</p>
     *
     * @return {@code Properties} objekat koji sadrži učitane konfiguracijske parametre
     */
    public static Properties ucitajProperties() {
        Properties prop = new Properties();
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "parametri.properties");
        try {
            prop.load(new FileInputStream(path.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}

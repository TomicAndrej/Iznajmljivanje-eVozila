package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.racun.Izvjestaj;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.racun.Racun;

import java.time.LocalDate;

/**
 * Kontroler za prikaz rezultata poslovanja u aplikaciji. Ova klasa upravlja prikazom dnevnih i sumarnih izvještaja u
 * korisničkom interfejsu.
 */
public class KontrolerRezultatiPoslovanja {
    private Main mainAplikacija;

    @FXML
    private TableView<Izvjestaj> dnevniIzvjestajTableView;
    @FXML
    private TableColumn<Izvjestaj, String> kategorijaDnevniIzvjestajColumn;
    @FXML
    private TableColumn<Izvjestaj, Double> iznosDnevniIzvjestajColumn;
    @FXML
    private ComboBox<LocalDate> odabirDatumaComboBox;

    @FXML
    private TableView<Izvjestaj> sumarniIzvjestajTableView;
    @FXML
    private TableColumn<Izvjestaj, String> kategorijaSumarniIzvjestajColumn;
    @FXML
    private TableColumn<Izvjestaj, Double> iznosSumarniIzvjestajColumn;

    private ObservableList<Izvjestaj> dnevniIzvjestaji = FXCollections.observableArrayList();
    private ObservableList<Izvjestaj> sumarniIzvjestaji = FXCollections.observableArrayList();

    /**
     * Postavlja referencu na glavnu aplikaciju.
     *
     * @param mainAplikacija Glavna aplikacija koja upravlja scenama.
     */
    public void setMainAplikacija(Main mainAplikacija) {
        this.mainAplikacija = mainAplikacija;
    }

    /**
     * Rukuje događajem klik na dugme za prelazak na mapu.
     *
     * @param event Događaj klik na dugme.
     */
    @FXML
    public void prelazakNaMapu(ActionEvent event) {
        mainAplikacija.prikaziMapaScenu();
    }

    /**
     * Inicijalizuje kontroler za prikaz podataka u tabelama i combo box-u.
     */
    @FXML
    public void initialize() {
        odabirDatumaComboBox.getItems().setAll(Racun.UKUPAN_PRIHOD_DNEVNI.keySet());

        kategorijaDnevniIzvjestajColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKategorija()));
        iznosDnevniIzvjestajColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getVrijednost()).asObject());
        iznosDnevniIzvjestajColumn.setCellFactory(column -> {
            return new TableCell<Izvjestaj, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        // Formatiraj broj na dvije decimale
                        setText(String.format("%.2f", item));
                    }
                }
            };
        });

        odabirDatumaComboBox.setOnAction(event -> prikaziIzvjestajZaDatum(odabirDatumaComboBox.getValue()));

        kategorijaSumarniIzvjestajColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKategorija()));
        iznosSumarniIzvjestajColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getVrijednost()).asObject());
        iznosSumarniIzvjestajColumn.setCellFactory(column -> {
            return new TableCell<Izvjestaj, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(String.format("%.2f", item));
                    }
                }
            };
        });
    }

    /**
     * Prikazuje dnevni izvještaj za izabrani datum.
     *
     * @param datum Datum za koji se prikazuje dnevni izvještaj.
     */
    private void prikaziIzvjestajZaDatum(LocalDate datum) {
        dnevniIzvjestaji.clear();

        dnevniIzvjestaji.add(new Izvjestaj("Ukupan prihod", Racun.UKUPAN_PRIHOD_DNEVNI.getOrDefault(datum, 0.0)));
        dnevniIzvjestaji.add(new Izvjestaj("Ukupan popust", Racun.UKUPAN_POPUST_DNEVNI.getOrDefault(datum, 0.0)));
        dnevniIzvjestaji.add(new Izvjestaj("Ukupno promocije", Racun.UKUPNO_PROMOCIJE_DNEVNI.getOrDefault(datum, 0.0)));
        dnevniIzvjestaji.add(new Izvjestaj("Ukupan iznos vožnji u uži/širi dio grada", Racun.UKUPAN_IZNOS_VOZNJI_UZI_SIRI_DIO_GRADA_DNEVNI.getOrDefault(datum, 0.0)));
        dnevniIzvjestaji.add(new Izvjestaj("Ukupan iznos za održavanje", Racun.UKUPAN_IZNOS_ZA_ODRZAVANJE_DNEVNI.getOrDefault(datum, 0.0)));
        dnevniIzvjestaji.add(new Izvjestaj("Ukupan iznos za popravke kvarova", Racun.UKUPAN_IZNOS_ZA_POPRAVKE_KVAROVA_DNEVNI.getOrDefault(datum, 0.0)));

        dnevniIzvjestajTableView.setItems(dnevniIzvjestaji);
    }

    /**
     * Prikazuje sumarni izvještaj.
     */
    private void prikaziSumarniIzvjestaj() {
        sumarniIzvjestaji.clear();

        Racun.izracunajRezultatePoslovanja();

        sumarniIzvjestaji.add(new Izvjestaj("Ukupan prihod", Racun.UKUPAN_PRIHOD));
        sumarniIzvjestaji.add(new Izvjestaj("Ukupan popust", Racun.UKUPAN_POPUST));
        sumarniIzvjestaji.add(new Izvjestaj("Ukupno promocije", Racun.UKUPNO_PROMOCIJE));
        sumarniIzvjestaji.add(new Izvjestaj("Ukupan iznos vožnji u uži/širi dio grada", Racun.UKUPAN_IZNOS_VOZNJI_UZI_SIRI_DIO_GRADA));
        sumarniIzvjestaji.add(new Izvjestaj("Ukupan iznos za održavanje", Racun.UKUPAN_IZNOS_ZA_ODRZAVANJE));
        sumarniIzvjestaji.add(new Izvjestaj("Ukupan iznos za popravke kvarova", Racun.UKUPAN_IZNOS_ZA_POPRAVKE_KVAROVA));
        sumarniIzvjestaji.add(new Izvjestaj("Ukupni troškovi kompanije", Racun.UKUPNI_TROSKOVI_KOMPANIJE));
        sumarniIzvjestaji.add(new Izvjestaj("Ukupan porez", Racun.UKUPAN_POREZ));

        sumarniIzvjestajTableView.setItems(sumarniIzvjestaji);
    }

    /**
     * Osvježava izvještaj tako što ažurira listu datuma i ponovno prikazuje sumarni izvještaj.
     */
    public void osvjeziIzvjestaj() {
        Platform.runLater(() -> {
            odabirDatumaComboBox.getItems().clear();
            odabirDatumaComboBox.getItems().addAll(Racun.UKUPAN_PRIHOD_DNEVNI.keySet());
            prikaziSumarniIzvjestaj();
        });
    }
}

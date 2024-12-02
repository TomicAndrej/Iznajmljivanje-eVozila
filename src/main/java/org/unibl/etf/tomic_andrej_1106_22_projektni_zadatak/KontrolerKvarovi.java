package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.kvar.Kvar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Kontroler za prikaz i upravljanje tabelom kvarova u korisničkom interfejsu.
 *
 * <p>
 * Ova klasa kontroliše {@code TableView} koji prikazuje listu kvarova, kao i interakcije sa korisnikom vezane za kvarove.
 * </p>
 */
 
public class KontrolerKvarovi {
    private Main mainAplikacija;

    @FXML
    private TableView<Kvar> kvaroviTableView;
    @FXML
    private TableColumn<Kvar, String> idKvaroviColumn;
    @FXML
    private TableColumn<Kvar, String> opisKvaraKvaroviColumn;
    @FXML
    private TableColumn<Kvar, LocalDateTime> vrijemeKvaroviColumn;
    @FXML
    private TableColumn<Kvar, String> vrstaPrevoznogSredstvaKvaroviColumn;

    private ObservableList<Kvar> kvarovi = FXCollections.observableArrayList();

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
     * Inicijalizuje {@code TableView} za prikaz kvarova.
     * Ova metoda postavlja {@code CellValueFactory} za svaku kolonu u tabeli i formatira prikaz vremena.
     */
    public void initialize() {
        vrstaPrevoznogSredstvaKvaroviColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVrstaPrevoznogSredstva()));
        idKvaroviColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        vrijemeKvaroviColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDateTime>(cellData.getValue().getDatumVrijeme()));
        opisKvaraKvaroviColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpisKvara()));

        vrijemeKvaroviColumn.setCellFactory(column -> {
            return new TableCell<Kvar, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("d.M.yyyy HH:mm:ss")));
                    }
                }
            };
        });

        kvarovi.addAll(Kvar.KVAROVI);
        kvaroviTableView.setItems(kvarovi);
    }

    /**
     * Osvježava tabelu kvarova sa najnovim podacima iz {@code Kvar.KVAROVI}.
     * Metoda se pokreće na JavaFX aplikacionom threadu koristeći {@code Platform.runLater()}.
     */
    public void osvjeziTabeluKvarova() {
        Platform.runLater(() -> {
            kvarovi.clear();
            kvarovi.addAll(Kvar.KVAROVI);
            kvaroviTableView.refresh();
        });
    }
}

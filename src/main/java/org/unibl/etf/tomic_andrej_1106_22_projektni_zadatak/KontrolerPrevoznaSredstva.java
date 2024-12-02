package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Kontroler za prikaz prevoznih sredstava u JavaFX aplikaciji.
 * <p>
 * Ova klasa upravlja prikazom automobila, bicikala i trotineta u tabelama.
 * Omogućava prelazak na mapu i inicijalizaciju prikaza podataka iz `Main.VOZILA`.
 * </p>
 */
public class KontrolerPrevoznaSredstva {
    private Main mainAplikacija;

    @FXML
    private TableView<Automobil> automobilTableView;
    @FXML
    private TableColumn<Automobil, Double> cijenaNabavkeAutomobilColumn;
    @FXML
    private TableColumn<Automobil, LocalDate> datumNabavkeAutomobilColumn;
    @FXML
    private TableColumn<Automobil, String> idAutomobilColumn;
    @FXML
    private TableColumn<Automobil, String> modelAutomobilColumn;
    @FXML
    private TableColumn<Automobil, String> opisAutomobilColumn;
    @FXML
    private TableColumn<Automobil, String> proizvodjacAutomobilColumn;
    @FXML
    private TableColumn<Automobil, Integer> trenutniNivoBaterijeAutomobilColumn;

    @FXML
    private TableView<Bicikl> biciklTableView;
    @FXML
    private TableColumn<Bicikl, Double> cijenaNabavkeBiciklColumn;
    @FXML
    private TableColumn<Bicikl, Double> dometBiciklColumn;
    @FXML
    private TableColumn<Bicikl, String> idBiciklColumn;
    @FXML
    private TableColumn<Bicikl, String> modelBiciklColumn;
    @FXML
    private TableColumn<Bicikl, String> proizvodjacBiciklColumn;
    @FXML
    private TableColumn<Bicikl, Integer> trenutniNivoBaterijeBiciklColumn;

    @FXML
    private TableView<Trotinet> trotinetTableView;
    @FXML
    private TableColumn<Trotinet, Double> cijenaNabavkeTrotinetColumn;
    @FXML
    private TableColumn<Trotinet, String> idTrotinetColumn;
    @FXML
    private TableColumn<Trotinet, Integer> trenutniNivoBaterijeTrotinetColumn;
    @FXML
    private TableColumn<Trotinet, String> proizvodjacTrotinetColumn;
    @FXML
    private TableColumn<Trotinet, String> modelTrotinetColumn;
    @FXML
    private TableColumn<Trotinet, Double> maksimalnaBrzinaTrotinetColumn;

    private ObservableList<Automobil> automobili = FXCollections.observableArrayList();
    private ObservableList<Bicikl> bicikli = FXCollections.observableArrayList();
    private ObservableList<Trotinet> trotineti = FXCollections.observableArrayList();

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
     * Inicijalizuje kolone tabela i popunjava ih podacima iz `Main.VOZILA`.
     * Ova metoda se poziva automatski nakon učitavanja FXML fajla.
     */
    @FXML
    public void initialize() {
        idAutomobilColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        proizvodjacAutomobilColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProizvodjac()));
        modelAutomobilColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        trenutniNivoBaterijeAutomobilColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTrenutniNivoBaterije()).asObject());
        cijenaNabavkeAutomobilColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCijenaNabavke()).asObject());
        opisAutomobilColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpis()));
        datumNabavkeAutomobilColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDatumNabavke()));
        datumNabavkeAutomobilColumn.setCellFactory(column -> {
            return new TableCell<Automobil, LocalDate>() {
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("d.M.yyyy")));
                    }
                }
            };
        });

        idBiciklColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        proizvodjacBiciklColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProizvodjac()));
        modelBiciklColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        trenutniNivoBaterijeBiciklColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTrenutniNivoBaterije()).asObject());
        cijenaNabavkeBiciklColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCijenaNabavke()).asObject());
        dometBiciklColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getDomet()).asObject());

        idTrotinetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        proizvodjacTrotinetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProizvodjac()));
        modelTrotinetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        trenutniNivoBaterijeTrotinetColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTrenutniNivoBaterije()).asObject());
        cijenaNabavkeTrotinetColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCijenaNabavke()).asObject());
        maksimalnaBrzinaTrotinetColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMaksimalnaBrzina()).asObject());

        raspodjelaVozila();

        automobilTableView.setItems(automobili);
        biciklTableView.setItems(bicikli);
        trotinetTableView.setItems(trotineti);
    }

    /**
     * Razvrstava vozila po tipu u tri ObservableList-e
     */
    private void raspodjelaVozila() {
        for (Vozilo vozilo : Main.VOZILA.values()) {
            if (vozilo.tipVozila().equals("Automobil")) {
                automobili.add((Automobil) vozilo);
            } else if (vozilo.tipVozila().equals("Bicikl")) {
                bicikli.add((Bicikl) vozilo);
            } else if (vozilo.tipVozila().equals("Trotinet")) {
                trotineti.add((Trotinet) vozilo);
            }
        }
    }

    /**
     * Osvježava tabele prevoznih sredstava sa najnovim podacima o trenutnom nivou baterije.
     * Metoda se pokreće na threadu svakog iznajmljivanja koristeći {@code Platform.runLater()}.
     */
    public void osvjeziTabeleVozila() {
        Platform.runLater(() -> {
            automobili.clear();
            bicikli.clear();
            trotineti.clear();
            raspodjelaVozila();
            automobilTableView.refresh();;
            biciklTableView.refresh();
            trotinetTableView.refresh();
        });
    }
}
package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak;

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
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.properties_ucitavanje.PropertiesUcitavanje;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila.Automobil;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila.Bicikl;
import org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak.vozila.Trotinet;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

/**
 * Kontroler za prikaz podataka o najvećim prihodima za različite vrste vozila.
 *
 * <p>
 * Ova klasa upravlja prikazom podataka o automobilima, biciklima i trotinetima u tabelama
 * i omogućava deserijalizaciju podataka iz datoteka i prikaz tih podataka u korisničkom interfejsu.
 * </p>
 */
public class KontrolerNajveciPrihodi {
    private Main mainAplikacija;

    @FXML
    private TableView<Automobil> automobilTableView;
    @FXML
    private TableColumn<Automobil, Double> cijenaNabavkeNajviseAutomobilColumn;
    @FXML
    private TableColumn<Automobil, LocalDate> datumNabavkeNajviseAutomobilColumn;
    @FXML
    private TableColumn<Automobil, String> idNajviseAutomobilColumn;
    @FXML
    private TableColumn<Automobil, String> modelNajviseAutomobilColumn;
    @FXML
    private TableColumn<Automobil, String> opisNajviseAutomobilColumn;
    @FXML
    private TableColumn<Automobil, String> proizvodjacNajviseAutomobilColumn;
    @FXML
    private TableColumn<Automobil, Double> zaradaNajviseAutomobilColumn;
    @FXML
    private TableColumn<Automobil, Integer> trenutniNivoBaterijeNajviseAutomobilColumn;
    private ObservableList<Automobil> automobili = FXCollections.observableArrayList();

    @FXML
    private TableView<Bicikl> biciklTableView;
    @FXML
    private TableColumn<Bicikl, Double> cijenaNabavkeNajviseBiciklColumn;
    @FXML
    private TableColumn<Bicikl, Double> dometNajviseBiciklColumn;
    @FXML
    private TableColumn<Bicikl, String> idNajviseBiciklColumn;
    @FXML
    private TableColumn<Bicikl, String> modelNajviseBiciklColumn;
    @FXML
    private TableColumn<Bicikl, Double> zaradaNajviseBiciklColumn;
    @FXML
    private TableColumn<Bicikl, String> proizvodjacNajviseBiciklColumn;
    @FXML
    private TableColumn<Bicikl, Integer> trenutniNivoBaterijeNajviseBiciklColumn;
    private ObservableList<Bicikl> bicikli = FXCollections.observableArrayList();

    @FXML
    private TableView<Trotinet> trotinetTableView;
    @FXML
    private TableColumn<Trotinet, Double> cijenaNabavkeNajviseTrotinetColumn;
    @FXML
    private TableColumn<Trotinet, String> idNajviseTrotinetColumn;
    @FXML
    private TableColumn<Trotinet, Double> maksimalnaBrzinaNajviseTrotinetColumn;
    @FXML
    private TableColumn<Trotinet, String> modelNajviseTrotinetColumn;
    @FXML
    private TableColumn<Trotinet, String> proizvodjacNajviseTrotinetColumn;
    @FXML
    private TableColumn<Trotinet, Integer> trenutniNivoBaterijeNajviseTrotinetColumn;
    @FXML
    private TableColumn<Trotinet, Double> zaradaNajviseTrotinetColumn;
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
     * Deserijalizuje podatke iz datoteka i ažurira tabelu sa podacima o automobilima, biciklima i trotineta.
     *
     * Ova metoda učitava podatke o automobilima, biciklima i trotinetima iz serijalizovanih datoteka
     * i izračunava zaradu za svaki tip vozila. Nakon učitavanja podataka, tabela se osvježava.
     *
     * @param event Događaj klik na dugme za deserijalizaciju podataka.
     */
    @FXML
    public void deserijalizujPodatke(ActionEvent event) {
        automobili.clear();
        bicikli.clear();
        trotineti.clear();

        Properties properties = PropertiesUcitavanje.ucitajProperties();
        String putanjaFajla = properties.getProperty("PUTANJA_SERIJALIZACIJA");

        try {
            double automobilIznos, biciklIznos, trotinetIznos;
            List<String> redovi = Files.readAllLines(new File(putanjaFajla + File.separator + "iznosi.txt").toPath());

            ObjectInputStream oisAutomobil = new ObjectInputStream(new FileInputStream(putanjaFajla + File.separator + "Automobil.ser"));
            Automobil automobil = (Automobil) oisAutomobil.readObject();
            oisAutomobil.close();
            automobilIznos = Double.parseDouble(redovi.get(0).split(" ")[1]);

            ObjectInputStream oisBicikl = new ObjectInputStream(new FileInputStream(putanjaFajla + File.separator + "Bicikl.ser"));
            Bicikl bicikl = (Bicikl) oisBicikl.readObject();
            oisBicikl.close();
            biciklIznos = Double.parseDouble(redovi.get(1).split(" ")[1]);

            ObjectInputStream oisTrotinet = new ObjectInputStream(new FileInputStream(putanjaFajla + File.separator + "Trotinet.ser"));
            Trotinet trotinet = (Trotinet) oisTrotinet.readObject();
            oisTrotinet.close();
            trotinetIznos = Double.parseDouble(redovi.get(2).split(" ")[1]);

            idNajviseAutomobilColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
            datumNabavkeNajviseAutomobilColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDatumNabavke()));
            datumNabavkeNajviseAutomobilColumn.setCellFactory(column -> {
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
            cijenaNabavkeNajviseAutomobilColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCijenaNabavke()).asObject());
            proizvodjacNajviseAutomobilColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProizvodjac()));
            modelNajviseAutomobilColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
            opisNajviseAutomobilColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpis()));
            trenutniNivoBaterijeNajviseAutomobilColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTrenutniNivoBaterije()).asObject());
            zaradaNajviseAutomobilColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(automobilIznos).asObject());
            automobili.add(automobil);
            automobilTableView.setItems(automobili);

            idNajviseBiciklColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
            proizvodjacNajviseBiciklColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProizvodjac()));
            modelNajviseBiciklColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
            cijenaNabavkeNajviseBiciklColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCijenaNabavke()).asObject());
            trenutniNivoBaterijeNajviseBiciklColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTrenutniNivoBaterije()).asObject());
            dometNajviseBiciklColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getDomet()).asObject());
            zaradaNajviseBiciklColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(biciklIznos).asObject());
            bicikli.add(bicikl);
            biciklTableView.setItems(bicikli);

            idNajviseTrotinetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
            proizvodjacNajviseTrotinetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProizvodjac()));
            modelNajviseTrotinetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
            trenutniNivoBaterijeNajviseTrotinetColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTrenutniNivoBaterije()).asObject());
            cijenaNabavkeNajviseTrotinetColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCijenaNabavke()).asObject());
            maksimalnaBrzinaNajviseTrotinetColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMaksimalnaBrzina()).asObject());
            zaradaNajviseTrotinetColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(trotinetIznos).asObject());
            trotineti.add(trotinet);
            trotinetTableView.setItems(trotineti);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        automobilTableView.refresh();
        biciklTableView.refresh();
        trotinetTableView.refresh();
    }
}

package org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Kontroler za upravljanje scenom mape u aplikaciji. Ova klasa upravlja prikazom i interakcijama sa scenom
 * koja predstavlja mapu u kojoj su prikazani automobili i drugi elementi.
 */
public class KontrolerMapa {
    private Main mainAplikacija;

    @FXML
    private GridPane mapaGridPane;
    @FXML
    private Button najveciPrihodiButton;

    /**
     * Postavlja referencu na glavnu aplikaciju.
     *
     * @param mainAplikacija Glavna aplikacija koja upravlja scenama.
     */
    public void setMainAplikacija(Main mainAplikacija) {
        this.mainAplikacija = mainAplikacija;
    }

    /**
     * Prelazi na scenu kvarova kada se klikne odgovarajuće dugme.
     *
     * @param event Akcioni događaj.
     */
    @FXML
    public void prelazakNaKvarove(ActionEvent event) {
        mainAplikacija.prikaziKvaroviScenu();
    }

    /**
     * Prelazi na scenu prevoznih sredstava kada se klikne odgovarajuće dugme.
     *
     * @param event Akcioni događaj.
     */
    @FXML
    public void prelazakNaPrevoznaSredstva(ActionEvent event) {
        mainAplikacija.prikaziPrevoznaSredstvaScenu();
    }

    /**
     * Prelazi na scenu rezultata poslovanja kada se klikne odgovarajuće dugme.
     *
     * @param event Akcioni događaj.
     */
    @FXML
    public void prelazakNaRezultatePoslovanja(ActionEvent event) {
        mainAplikacija.prikaziRezultatiPoslovanjaScenu();
    }

    /**
     * Prelazi na scenu sa najvećim prihodima kada se klikne odgovarajuće dugme.
     *
     * @param event Akcioni događaj.
     */
    @FXML
    public void prelazakNaNajvecePrihode(ActionEvent event) {
        mainAplikacija.prikaziNajvecePrihodeScenu();
    }

    /**
     * Omogućava klikabilnim dugme za najveće prihode.
     */
    public void enableNajveciPrihodiButton() {
        najveciPrihodiButton.setDisable(false);
    }

    /**
     * Pozicionira automobil na mapu u GridPane na određenoj poziciji i prikazuje informacije o automobilu.
     *
     * @param i Indeks reda u GridPane.
     * @param j Indeks kolone u GridPane.
     * @param id Identifikator automobila.
     * @param trenutniNivoBaterije Trenutni nivo baterije automobila.
     * @param color Boja koja će biti korišćena za pozadinu prikaza automobila.
     */
    public void pozicioniranjeAuta(int i, int j, String id, int trenutniNivoBaterije, Color color) {
        if (i * 20 + j < mapaGridPane.getChildren().size()) {
            Node node = mapaGridPane.getChildren().get(i * 20 + j);

            if (node instanceof VBox) {
                VBox vbox = (VBox) node;

                AnchorPane greenAnchorPane = new AnchorPane();
                String colorStyle = String.format("-fx-background-color: #%02x%02x%02x;",
                        (int) (color.getRed() * 255),
                        (int) (color.getGreen() * 255),
                        (int) (color.getBlue() * 255));
                greenAnchorPane.setStyle(colorStyle);


                Label label = new Label(" " + id + " " + trenutniNivoBaterije + "%");
                if (color.equals(Color.rgb(0, 255, 0, 0.8))) {
                    label.setStyle("-fx-font-size: 7px; -fx-text-fill: black;");
                } else {
                    label.setStyle("-fx-font-size: 7px; -fx-text-fill: white;");
                }
                Platform.runLater(() -> {
                    greenAnchorPane.getChildren().add(label);
                    vbox.getChildren().add(greenAnchorPane);
                });
            }
        }
    }

    /**
     * Uklanja automobil sa mape sa određenog mjesta.
     *
     * @param i Indeks reda u GridPane.
     * @param j Indeks kolone u GridPane.
     * @param id Identifikator automobila.
     * @param trenutniNivoBaterije Trenutni nivo baterije automobila.
     */
    public void depozicioniranjeAuta(int i, int j, String id, int trenutniNivoBaterije) {
        VBox vbox = (VBox) mapaGridPane.getChildren().get(i * 20 + j);
        for (Node node : vbox.getChildren()) {
            if (node instanceof AnchorPane) {
                AnchorPane anchorPane1 = (AnchorPane) node;
                Label label = (Label) anchorPane1.getChildren().get(0);
                if (label.getText().equals(" " + id + " " + trenutniNivoBaterije + "%")) {
                    Platform.runLater(() -> {
                        vbox.getChildren().remove(anchorPane1);
                    });
                    break;
                }
            }
        }
    }

    /**
     * Čisti sve prikazane automobile na mapi.
     */
    public void ciscenjeMape() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                VBox vbox = (VBox) mapaGridPane.getChildren().get(i * 20 + j);
                for (Node node : vbox.getChildren()) {
                    if (node instanceof AnchorPane) {
                        AnchorPane anchorPane1 = (AnchorPane) node;
                        Platform.runLater(() -> {
                            vbox.getChildren().remove(anchorPane1);
                        });
                    }
                }
            }
        }
    }
}

package org.iesalandalus.programacion.tallermecanico.vista.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;


import javax.print.DocFlavor;
import java.util.ResourceBundle;

public class BorrarCliente extends Controlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private DocFlavor.URL location;

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField tfCampoDni;

    private boolean aceptado;
    @FXML
    void aceptar() {
        aceptado = true;


    }

    @FXML
    void cancelar() {
        aceptado = false;

    }

    public boolean isAceptado() {
        return aceptado;
    }
}

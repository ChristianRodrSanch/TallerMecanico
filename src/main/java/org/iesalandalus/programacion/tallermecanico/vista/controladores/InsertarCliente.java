package org.iesalandalus.programacion.tallermecanico.vista.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

public class InsertarCliente extends Controlador {

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

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


    public Cliente getCliente() {
        String nombre = tfNombre.getText();
        String Dni = tfDni.getText();
        String telefono = tfTelefono.getText();

        return new Cliente(nombre, Dni, telefono);
    }
}

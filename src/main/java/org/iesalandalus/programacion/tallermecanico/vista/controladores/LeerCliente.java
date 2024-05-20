package org.iesalandalus.programacion.tallermecanico.vista.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

import java.net.URL;
import java.util.ResourceBundle;

public class LeerCliente extends Controlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCerrarIz;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    public Cliente cliente;

    private static boolean cancelado = false;


    public static boolean isCancelado() {
        return cancelado;
    }


    @FXML
    void cerrar(ActionEvent event) {
        cancelado = false;
        getEscenario().close();
    }

    @FXML
    void aceptar(ActionEvent event) {

        cancelado = false;
        getEscenario().close();

    }

    public void limpiar() {
        String cadenaVacia = "";
        tfNombre.setText(cadenaVacia);
        tfDni.setText(cadenaVacia);
        tfTelefono.setText(cadenaVacia);
    }

    public Cliente getCliente() {
       String nombre = tfNombre.getText();
       String Dni = tfDni.getText();
       String telefono = tfTelefono.getText();

        return new Cliente(nombre,Dni,telefono);
    }


}

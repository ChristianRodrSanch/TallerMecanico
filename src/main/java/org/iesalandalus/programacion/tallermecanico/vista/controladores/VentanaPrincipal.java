

package org.iesalandalus.programacion.tallermecanico.vista.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;

public class VentanaPrincipal extends Controlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btBuscarCliente;

    @FXML
    private Button btBuscarVehiculo;

    @FXML
    private Button btInsertarCliente;

    @FXML
    private Button btInsertarTrabajos;

    @FXML
    private Button btInsertarVehiculos;

    @FXML
    private Button btListarTrabajos;

    @FXML
    private Button btListarVehiculo;

    @FXML
    private Button btlistarCliente;

    @FXML
    private MenuItem miSalir;

    public Cliente cliente;

    public LeerCliente leerCliente;

    public GestorEventos gestorEventos;




    public void incializar(){
        getEscenario().setOnCloseRequest(this::salir);
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.LISTAR_CLIENTES);
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.LISTAR_VEHICULOS);
    }
    @FXML
    void ListarVehiculo(ActionEvent event) {

    }

    @FXML
    void buscarCliente() {
      String dnileido = Dialogos.mostrarDialogoTexto("Leer DNI", "Introduce el DNI: ", getEscenario(), Cliente.ER_DNI);
      if(dnileido != null) {
         VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BUSCAR_CLIENTE);
      }
    }

    @FXML
    void buscarVehiculo(ActionEvent event) {

    }

    @FXML
    void insertarCliente(ActionEvent event) {
        LeerCliente leerCliente = (LeerCliente) (Controladores.get("/vistas/LeerCliente.fxml","Leer Cliente", getEscenario()));
        leerCliente.limpiar();
        leerCliente.getEscenario().showAndWait();
        if (!LeerCliente.isCancelado()) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_CLIENTE);
        }
    }


    @FXML
    void insertarTrabajo(ActionEvent event) {

    }

    @FXML
    void insertarVehiculo(ActionEvent event) {

    }

    @FXML
    void listarCliente(ActionEvent event) {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.LISTAR_CLIENTES);
    }

    @FXML
    void listarTrabajo(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btBuscarCliente != null : "fx:id=\"btBuscarCliente\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";
        assert btBuscarVehiculo != null : "fx:id=\"btBuscarVehiculo\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";
        assert btInsertarCliente != null : "fx:id=\"btInsertarCliente\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";
        assert btInsertarTrabajos != null : "fx:id=\"btInsertarTrabajos\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";
        assert btInsertarVehiculos != null : "fx:id=\"btInsertarVehiculos\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";
        assert btListarTrabajos != null : "fx:id=\"btListarTrabajos\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";
        assert btListarVehiculo != null : "fx:id=\"btListarVehiculo\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";
        assert btlistarCliente != null : "fx:id=\"btlistarCliente\" was not injected: check your FXML file 'VentanaPrincipal.fxml'.";

    }


    @FXML
    void salir(ActionEvent event) {
            if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estas seguro que deseas salir de la aplicación?",getEscenario())) {
                getEscenario().close();
                VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.SALIR);
            }
    }

    private void salir(WindowEvent e, Stage escenario) {
        if (Dialogos.mostrarDialogoConfirmacion("salir", "¿Estas seguro de que quieres salir de la aplicacioón?", escenario)) {
            escenario.close();
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.SALIR);

        } else {
            e.consume();
        }
    }

}




/*package org.iesalandalus.programacion.tallermecanico.vista.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;


public class VentanaPrincipal extends Controlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btBorrarCliente;

    @FXML
    private Button btBuscarCliente;

    @FXML
    private Button btBuscarTrabajos;

    @FXML
    private Button btBuscarVehiculos;

    @FXML
    private Button btInsertarCliente;

    @FXML
    private Button btInsertarTrabajos;

    @FXML
    private Button btInsertarVehiculos;


    public BorrarCliente borrarCliente;
    public InsertarCliente insertarCliente;
    public LeerCliente leerCliente;
    public  GestorEventos gestorEventos;
    @FXML
    void borrarCliente(ActionEvent event) {
        BorrarCliente borrarCliente = (BorrarCliente) (Controladores.get("/vistas/BorrarCliente.fxml", "Clientes", getEscenario()));
        getEscenario().showAndWait();
        if (borrarCliente.isAceptado()) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BORRAR_CLIENTE);
        }
    }

    @FXML
    void insertarCliente(ActionEvent event) {
       LeerCliente leerCliente = (LeerCliente) (Controladores.get("/vistas/InsertarCliente.fxml","Insertar Cliente", getEscenario()));
        getEscenario().showAndWait();
        if (insertarCliente.isAceptado()) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_CLIENTE);
        }
    }

    @FXML
    void leerCliente(ActionEvent event) {
        LeerCliente leerCliente = (LeerCliente) (Controladores.get("/vistas/LeerCliente.fxml", "Clientes", getEscenario()));
        leerCliente.limpiar();
        leerCliente.getEscenario().showAndWait();


    }

    @FXML
    void initialize() {
        ;

    }


}


 */

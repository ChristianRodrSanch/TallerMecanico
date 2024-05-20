package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;

import static jdk.internal.net.http.common.Utils.close;

public class LanzadorVentanaPrincipal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Controlador ventanaPrincipal = Controladores.get("/vistas/VentanaPrincipal.fxml", "Taller Mecanico", null);
        ventanaPrincipal.getEscenario().setOnCloseRequest(e -> salir(e, ventanaPrincipal.getEscenario()));
        VistaGrafica.getInstancia().setVentanaPrincipal(ventanaPrincipal);
        ventanaPrincipal.getEscenario().show();
    }

    private void salir(WindowEvent e, Stage escenario) {
    if(Dialogos.mostrarDialogoConfirmacion("salir", "¿Estas seguro de que quieres salir de la aplicacioón?",escenario)) {
        escenario.close();
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.SALIR);
    } else {
        e.consume();
 }
    }

    public static void comenzar() { launch(LanzadorVentanaPrincipal.class);}

    public static  void terminar() {}

}

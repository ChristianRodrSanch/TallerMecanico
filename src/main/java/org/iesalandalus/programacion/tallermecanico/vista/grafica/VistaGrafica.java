package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.controladores.LeerCliente;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VistaGrafica implements Vista {

    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());

    private static VistaGrafica instancia;

    private Controlador ventanaPrincipal;

    public void mostrarTrabajoCliente(List<Trabajo> trabajos) {mostrarTrabajos(trabajos);}

    public void mostrarTrabajoVehiculo(List<Trabajo> trabajos) {mostrarTrabajos(trabajos);}


    public  static VistaGrafica getInstancia(){
        if(instancia == null) {
            instancia = new VistaGrafica();
        }
        return instancia;
    }

    void setVentanaPrincipal(Controlador ventanaPrincipal) { this.ventanaPrincipal = ventanaPrincipal;}

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() {
        LanzadorVentanaPrincipal.comenzar();
    }

    @Override
    public void terminar() {
        System.out.println("Hasta luego");

    }

    @Override
    public Cliente leerCliente() {
        LeerCliente leerCliente = (LeerCliente) (Controladores.get("/vistas/LeerCliente.fxml", "Clientes", ventanaPrincipal.getEscenario()));
        return leerCliente.getCliente();
    }

    @Override
    public Cliente leerClienteDni() {
        LeerCliente leerCliente= (LeerCliente) (Controladores.get("/vistas/LeerCliente.fxml", "Clientes", ventanaPrincipal.getEscenario()));
        return Cliente.get(leerClienteDni().getDni());
    }

    @Override
    public String leerNuevoNombre() {
        LeerCliente leerCliente = (LeerCliente) (Controladores.get("/vistas/LeerCliente.fxml", "Clientes", ventanaPrincipal.getEscenario()));
        return leerCliente.getCliente().getNombre();
    }

    @Override
    public String leerNuevoTelefono() {
        LeerCliente leerCliente = (LeerCliente) (Controladores.get("/vistas/LeerCliente.fxml", "Clientes", ventanaPrincipal.getEscenario()));
        return leerCliente.getCliente().getTelefono();
    }

    @Override
    public Vehiculo leerVehiculo() {
        return null;
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        return null;
    }

    @Override
    public Trabajo leerRevision() {
        return null;
    }

    @Override
    public Trabajo leerMecanico() {
        return null;
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return null;
    }

    @Override
    public int leerHoras() {
        return 0;
    }

    @Override
    public float leerPrecioMaterial() {
        return 0;
    }

    @Override
    public LocalDate leerFechaCierre() {
        return null;
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        System.out.println(evento + texto + exito);
        if (exito) {
            Dialogos.mostrarDialogoInformacion(evento.toString(), texto, ventanaPrincipal.getEscenario());

        } else {
            Dialogos.mostrarDialogoError(evento.toString(), texto, ventanaPrincipal.getEscenario());
        }
    }

    @Override
    public void mostrarCliente(Cliente cliente) {

    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {

    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {

    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {

    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {

    }

    @Override
    public LocalDate leerMes() {
        return null;
    }

    @Override
    public void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadisticas) {

    }
}

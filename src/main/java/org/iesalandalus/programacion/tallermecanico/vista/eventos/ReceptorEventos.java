package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import org.iesalandalus.programacion.tallermecanico.modelo.controlador.IControlador;

public interface ReceptorEventos extends IControlador {
    void actualizar(Evento evento);
}

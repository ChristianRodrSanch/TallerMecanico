package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {


    private Map<Evento, List<ReceptorEventos>> receptores;


    public GestorEventos(Evento... eventos) {

        this.receptores = new HashMap<>(); // esto lo que hace es inicializar el mapa y aparte preparar la Lista para asociar el atributo receptores a cada uno de los eventos

        for (Evento evento : eventos) {
            this.receptores.put(evento, new ArrayList<>());
        }


    }

    public void suscribir(ReceptorEventos receptor, Evento... eventos) {
        Objects.requireNonNull(receptor, "El receptor no puede ser nulo");
        Objects.requireNonNull(eventos, "El evento no puede ser nulo");

        for (Evento evento : eventos) {
            List<ReceptorEventos> listaReceptores = receptores.get(evento);
            listaReceptores.add(receptor);
        }

    }

    public void desuscribir(ReceptorEventos receptor, Evento... eventos) {
        Objects.requireNonNull(receptor, "El receptor no puede ser nulo");
        Objects.requireNonNull(eventos, "El evento no puede ser nulo");
        for (Evento evento : eventos) {
            List<ReceptorEventos> listaReceptores = receptores.get(evento);
            listaReceptores.remove(receptor);
        }

    }

    public void notificar(Evento evento) throws InterruptedException {
        Objects.requireNonNull(evento, "El evento no puede ser nulo");

        for (ReceptorEventos receptorEventos : receptores.get(evento)) {
            receptorEventos.actualizar(evento);


        }
    }
}


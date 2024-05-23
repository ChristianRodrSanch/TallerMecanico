package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {


    private Map<Evento, List<ReceptorEventos>> receptores = new HashMap<>();


    public GestorEventos(Evento... eventos) {
        Objects.requireNonNull(eventos,"Se debe gestionar algun evento.");
        // esto lo que hace es inicializar el mapa y aparte preparar la Lista para asociar el atributo receptores a cada uno de los eventos
        for (Evento evento : eventos) {
          receptores.put(evento, new ArrayList<>());
        }


    }

    public void suscribir(ReceptorEventos receptor, Evento... eventos) {
        Objects.requireNonNull(receptor, "El receptor de eventos no puede ser nulo.");
        Objects.requireNonNull(eventos, "te debes suscribir a algún evento.");

        for (Evento evento : eventos) {
            List<ReceptorEventos> listaReceptores = receptores.get(evento);
            listaReceptores.add(receptor);
        }

    }

    public void desuscribir(ReceptorEventos receptor, Evento... eventos) {
        Objects.requireNonNull(receptor, "El receptor de eventos no puede ser nulo.");
        Objects.requireNonNull(eventos, "Te debes desuscribir de algún evento.");
        for (Evento evento : eventos) {
            List<ReceptorEventos> listaReceptores = receptores.get(evento);
            listaReceptores.remove(receptor);
        }

    }

    public void notificar(Evento evento)  {
        Objects.requireNonNull(evento, "No se puede notificar un evento nulo");
        List<ReceptorEventos> listaReceptores = receptores.get(evento);
        for (ReceptorEventos receptorEventos : listaReceptores) {
            receptorEventos.actualizar(evento);


        }
    }
}


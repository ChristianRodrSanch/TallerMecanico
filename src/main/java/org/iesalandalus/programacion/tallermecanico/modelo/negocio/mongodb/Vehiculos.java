package org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;


public class Vehiculos implements IVehiculos {

    private static final String VEHICULOS = "vehiculos";
    private static final String MARCA = "marca";
    private static final String MATRICULA = "matricula";
    private static final String MODELO = "modelo";

    private static Vehiculos instancia;

    private MongoCollection<Document> coleccionVehiculos;

    private Vehiculos() {}


    public static Vehiculos getInstancia() {
        if(instancia == null) {
            instancia = new Vehiculos();
        }
        return instancia;
    }

    @Override
    public void comenzar() {
        coleccionVehiculos = MongoDB.getBD().getCollection(VEHICULOS);

    }

    @Override
    public void terminar() {
        MongoDB.cerrarConexion();
    }



    Vehiculo getVehiculo(Document documento) {
        Vehiculo vehiculo = null;
        if (documento != null) {
            vehiculo = new Vehiculo(documento.getString(MARCA), documento.getString(MATRICULA), documento.getString(MODELO));
        }
        return vehiculo;
    }

    @Override
    public List<Vehiculo> get() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (Document documento : coleccionVehiculos.find()) {
            vehiculos.add(getVehiculo(documento));
        }
        return vehiculos;
    }

    Document getDocumento(Vehiculo vehiculo) {
        Document documento = null;
        if (vehiculo != null) {
            String modelo = vehiculo.modelo();
            String matricula = vehiculo.matricula();
            String marca = vehiculo.marca();
            documento = new Document().append(MODELO,modelo).append(MATRICULA, matricula).append(MARCA, marca);
        }
        return documento;
    }

    private Bson getCriterioBusqueda(Vehiculo vehiculo) {
        return eq(MATRICULA, vehiculo.matricula());
    }


    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede insertar una matricula nula.");
        FindOneAndReplaceOptions opciones = new FindOneAndReplaceOptions().upsert(true);
        Document resultado = coleccionVehiculos.findOneAndReplace(getCriterioBusqueda(vehiculo), getDocumento(vehiculo), opciones);
        if (resultado != null) {
            throw new OperationNotSupportedException("Ya existe un vehiculo con esa matricula.");
        }
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehiculo nulo.");
        return getVehiculo(coleccionVehiculos.find(getCriterioBusqueda(vehiculo)).first());
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehiculo nulo.");
        DeleteResult resultado = coleccionVehiculos.deleteOne(getCriterioBusqueda(vehiculo));
        if (resultado.getDeletedCount() == 0) {
            throw new OperationNotSupportedException("No existe ningún vehiculo con esa matricula.");
        }
    }





}

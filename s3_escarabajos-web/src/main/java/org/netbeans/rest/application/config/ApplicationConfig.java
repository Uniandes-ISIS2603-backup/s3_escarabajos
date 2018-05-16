/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author n.gaitan
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.uniandes.csw.escarabajos.mappers.BusinessLogicExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.AcessorioResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.BicicletaResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.BicicletaUsadaResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.CarritoFacturaResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.CarritoItemsResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.CarritoResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.CatalogoResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.ClienteCarritoResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.ClienteFacturaReclamoResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.ClienteListaDeseosResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.ClienteMedioPagoResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.ClienteResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.FacturaResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.ItemResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.ListaDeseosResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.ListaItemsResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.MedioPagoResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.ModeloCalificacionResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.ModeloItemsResource.class);
        resources.add(co.edu.uniandes.csw.escarabajos.resources.ModeloResource.class);
    }
    
}

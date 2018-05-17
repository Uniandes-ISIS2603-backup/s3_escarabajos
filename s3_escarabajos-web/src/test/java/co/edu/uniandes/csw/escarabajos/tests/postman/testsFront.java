/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.tests.postman;
import co.edu.uniandes.csw.escarabajos.resources.*;
import co.edu.uniandes.csw.postman.tests.PostmanTestBuilder;
import java.io.File;
import java.io.IOException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 *
 * @author n.gaitan
 */
@RunWith(Arquillian.class)
public class testsFront {
     @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "frontstepbystep-web.war")
                // Se agregan las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                
                .addPackage(AcessorioResource.class.getPackage())
                .addPackage(BicicletaResource.class.getPackage())
                .addPackage(BicicletaUsadaResource.class.getPackage())
                .addPackage(CarritoFacturaResource.class.getPackage())
                .addPackage(CarritoItemsResource.class.getPackage())
                .addPackage(CarritoResource.class.getPackage())
                .addPackage(CatalogoResource.class.getPackage())
                .addPackage(ClienteCarritoResource.class.getPackage())
                .addPackage(ClienteFacturaReclamoResource.class.getPackage())
                .addPackage(ClienteListaDeseosResource.class.getPackage())
                .addPackage(ClienteMedioPagoResource.class.getPackage())
                .addPackage(ClienteResource.class.getPackage())
                .addPackage(FacturaResource.class.getPackage())
                .addPackage(ItemResource.class.getPackage())
                .addPackage(ListaDeseosResource.class.getPackage())
                .addPackage(ListaItemsResource.class.getPackage())
                .addPackage(MedioPagoResource.class.getPackage())
                .addPackage(ModeloCalificacionResource.class.getPackage())
                .addPackage(ModeloItemsResource.class.getPackage())
                .addPackage(ModeloResource.class.getPackage())
                .addPackage(ModeloCalificacionResource.class.getPackage())


                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }
  

    @Test
    public void postman() throws IOException {
        
        PostmanTestBuilder tp = new PostmanTestBuilder();
        tp.setTestWithoutLogin("paso5.postman_collection");
        String desiredResult = "0";
        if (tp.getAssertions_failed() != null) {
            Assert.assertEquals(desiredResult, tp.getAssertions_failed());
        }
        if (tp.getIterations_failed() != null) {
            Assert.assertEquals(desiredResult, tp.getIterations_failed());
        }
        if (tp.getPrerequest_scripts_failed() != null) {
            Assert.assertEquals(desiredResult, tp.getPrerequest_scripts_failed());
        }
        if (tp.getRequests_failed() != null) {
            Assert.assertEquals(desiredResult, tp.getRequests_failed());
        }
        if (tp.getTest_scripts_failed() != null) {
            Assert.assertEquals(desiredResult, tp.getTest_scripts_failed());
        }

    }
}

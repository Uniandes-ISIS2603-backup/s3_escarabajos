/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

/**
 *
 * @author n.gaitan
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class ReclamoEntity implements Serializable {

    /**
     * Atributo que representa el id del reclamo. id autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa el mensaje del reclamo.
     */
    private String mensaje;

    /**
     * Atributo que representa la razon del reclamo.
     */
    private String razon;

    /**
     * Atributo que representa la factura a la cual se hace el reclamo.
     */
    @PodamExclude
    @OneToOne(cascade = CascadeType.PERSIST)
    private FacturaEntity factura;

    /**
     * Atributo que representa el cliente que hace el reclamo.
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ClienteEntity cliente;

    /**
     * Atributo que representa si el reclamo esta en proceso o no.
     */
    @PodamExclude
    private Boolean enProceso = true;

    /**
     * Devuelve el mensaje.
     *
     * @return mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Modifica el mensaje.
     *
     * @param msj nuevo mensaje
     */
    public void setMensaje(String msj) {
        mensaje = msj;
    }

    /**
     * Devuelve la razon.
     *
     * @return razon
     */
    public String getRazon() {
        return razon;
    }

    /**
     * Modifica la razon
     *
     * @param raz nueva razon
     */
    public void setRazon(String raz) {
        razon = raz;
    }

    /**
     * Devuelve la factura.
     *
     * @return factura
     */
    public FacturaEntity getFactura() {
        return factura;
    }

    /**
     * Modifica la factura.
     *
     * @param factura nueva factura
     */
    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    /**
     * Devuelve el cliente.
     *
     * @return cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * Modifica el cliente.
     *
     * @param cliente nuevo cliente
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    /**
     * Devuelve el id.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Le da valor false a enProceso.
     */
    public void terminar() {
        enProceso = false;
    }

    /**
     * Le da valor true a enProceso.
     */
    public void renaudar() {
        enProceso = true;
    }

    /**
     * Modifica el id.
     *
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Compara si dos objetos son iguales.
     *
     * @param obj objeto a comparar
     * @return boolean Si son iguales retorna true. De lo contrario retorna
     * false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReclamoEntity other = (ReclamoEntity) obj;
        return Objects.equals(this.id, other.id);
    }

    /**
     * Metodo predeterminado hashCode.
     *
     * @return el hashCode creado
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

    /**
     * Devuelve si esta en proceso o no el reclamo.
     *
     * @return enProceso
     */
    public boolean isEnProceso() {
        return enProceso;
    }
}

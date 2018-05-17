/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author n.gaitan
 */
@Entity
public class CalificacionEntity implements Serializable {

    /**
     * Atributo que representa el id de la calificacion. id autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa el comentario de la calificacion.
     */
    private String comentario;

    /**
     * Atributo que representa el puntaje de la calificacion.
     */
    private Double puntaje;

    /**
     * Atribuo que representa el modelo a calificar.
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ModeloEntity modelo;

    /**
     * Atributo que representa el cliente que califica.
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ClienteEntity cliente;

    /**
     * Devuelve el comentario.
     *
     * @return comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Devuelve el puntaje.
     *
     * @return puntaje
     */
    public double getPuntaje() {
        return puntaje;
    }

    /**
     * Modifica el puntaje.
     *
     * @param punt nuevo puntaje
     */
    public void setPuntaje(double punt) {
        puntaje = punt;
    }

    /**
     * Modifica el comentario.
     *
     * @param com nuevo comentario
     */
    public void setComentario(String com) {
        comentario = com;
    }

    /**
     * Devuelve el modelo
     *
     * @return modelo
     */
    public ModeloEntity getModelo() {
        return modelo;
    }

    /**
     * Modifica el modelo.
     *
     * @param modelo nuevo modelo
     */
    public void setModelo(ModeloEntity modelo) {
        this.modelo = modelo;
    }

    /**
     * Devuelve el cliente.
     *
     * @return cliente
     */
    public ClienteEntity getClientes() {
        return getCliente();
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
     * Modifica el id.
     *
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el cliente
     *
     * @return cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
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
        final CalificacionEntity other = (CalificacionEntity) obj;
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
}

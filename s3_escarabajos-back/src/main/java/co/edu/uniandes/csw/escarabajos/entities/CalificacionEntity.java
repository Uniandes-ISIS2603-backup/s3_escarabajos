/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.List;
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
public class CalificacionEntity  implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String comentario;
    
    private Double puntaje;
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ModeloEntity modelo;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    
    public String getComentario()
    {
        return comentario;
    }
    public double getPuntaje()
    {
        return puntaje;
    }
    public void setPuntaje(double punt)
    {
        puntaje = punt;
    }
    public void setComentario(String com)
    {
        comentario = com;
    }

    public ModeloEntity getModelo() {
        return modelo;
    }

    public void setModelo(ModeloEntity modelo) {
        this.modelo = modelo;
    }

    public ClienteEntity getClientes() {
        return getCliente();
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }
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

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}

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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class ReclamoEntity  implements Serializable

{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @PodamExclude
    @OneToMany(mappedBy = "reclamo", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FotoEntity> album = new ArrayList<FotoEntity>();
    
    private String mensaje;
    private String razon;
    
    @PodamExclude
    @OneToOne( cascade = CascadeType.PERSIST )
    private FacturaEntity factura;

    @PodamExclude
    @ManyToOne( cascade = CascadeType.PERSIST )
    private ClienteEntity cliente;
   
    @PodamExclude
    private Boolean enProceso = true;
    
    public String getMensaje()
    {
        return mensaje;
    }
    public void setMensaje(String msj)
    {
        mensaje = msj;
    }
     public String getRazon()
    {
        return razon;
    }
    public void setRazon(String raz)
    {
        razon = raz;
    }
    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the album
     */
    public List<FotoEntity> getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(List<FotoEntity> album) {
        this.album = album;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    public void terminar()
    {
        enProceso = false;
    }
    public void renaudar()
    {
        enProceso = true;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
        final ReclamoEntity other = (ReclamoEntity) obj;
        return Objects.equals(this.id, other.id);
    }
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

    /**
     * @return the enProceso
     */
    public boolean isEnProceso() {
        return enProceso;
    }
}

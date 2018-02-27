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
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class ReclamoEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @PodamExclude
    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FotoEntity> album = new ArrayList<FotoEntity>();
    
    private String mensaje;
    private String razon;
    
    @ManyToOne( cascade = CascadeType.PERSIST )
    private FacturaEntity facuta;

   @ManyToOne( cascade = CascadeType.PERSIST )
    private ClienteEntity cliente;
   
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
     public Long getId()
    {
        return id;
    }
     public void setId(Long id)
     {
         this.id = id;
     }

    public FacturaEntity getFacuta() {
        return facuta;
    }

    public void setFacuta(FacturaEntity facuta) {
        this.facuta = facuta;
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
     
}

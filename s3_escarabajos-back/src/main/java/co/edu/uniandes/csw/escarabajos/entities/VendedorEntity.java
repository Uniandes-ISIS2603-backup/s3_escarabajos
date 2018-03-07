/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.beltran
 */
@Entity
public class VendedorEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String direccion;
    private String telefono;
    private String nombre;
    
    @PodamExclude
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<BicicletaUsadaEntity> bicicletasUsadas = new ArrayList<BicicletaUsadaEntity>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDireccion(){
        return direccion;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
    /**
     * @return the bicicletasUsadas
     */
    public List<BicicletaUsadaEntity> getBicicletasUsadas() {
        return bicicletasUsadas;
    }

    /**
     * @param bicicletasUsadas the bicicletasUsadas to set
     */
    public void setBicicletasUsadas(List<BicicletaUsadaEntity> bicicletasUsadas) {
        this.bicicletasUsadas = bicicletasUsadas;
    }
    
}

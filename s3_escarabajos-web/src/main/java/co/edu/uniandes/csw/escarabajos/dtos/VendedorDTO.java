/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.VendedorEntity;

/**
 * VendedorDTO Objeto de transferencia de datos del . Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
	"nombre":String,
	"direccion":String,
	"telefono":String
	
    }
 * </pre> Por ejemplo un modelo se representa asi:<br>
 *
 * <pre>
 *
 *   {
	"nombre":"pibe",
	"direccion":"Cra 57 b 121",
	"telefono":"5885845"
        "id": 2
	
    }
 *
 * </pre>
 *
 * @author s.beltran
 */
public class VendedorDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    
    public VendedorDTO(){
        
    }
    public VendedorDTO(VendedorEntity vendedor){
        this.id = vendedor.getId();
        this.nombre = vendedor.getNombre();
        this.direccion = vendedor.getDireccion();
        this.telefono = vendedor.getTelefono();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public VendedorEntity toEntity(){
        VendedorEntity entity = new VendedorEntity();
        entity.setNombre(this.nombre);
        entity.setId(this.id);
        entity.setDireccion(this.direccion);
        entity.setTelefono(this.telefono);
        return entity;
    }
}

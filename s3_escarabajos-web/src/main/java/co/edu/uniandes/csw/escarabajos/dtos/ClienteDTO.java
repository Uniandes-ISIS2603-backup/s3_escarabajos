/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;

/**
 * CarritoDTO Objeto de transferencia de datos del . Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 {
  "id": Long,
  "nombre": String,
  "correo": String,
  "usuario": String,
  "cedula": Integer
 }
 *   }
 * </pre> Por ejemplo un cliente se representa asi:<br>
 *
 * <pre>
 *
 *   {
  "id": 1,
  "nombre": "Ariel",
  "correo": "asnar0@discuz.net",
  "usuario": "␣",
  "cedula": "8643803030"
}
 *
 * </pre>
 *
 * @author s.beltran
 */
public class ClienteDTO {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    private Long id;
    private String nombre;
    private String correo;
    private String usuario;
    private String cedula;
    private String direccion;
    private String telefono;
    public ClienteDTO(){
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param cliente: Es la entidad que se va a convertir a DTO
     */
    public ClienteDTO(ClienteEntity cliente) {
        if(cliente != null){
            this.id = cliente.getId();
            this.nombre = cliente.getNombre();
            this.correo = cliente.getCorreo();
            this.usuario = cliente.getUsuario();
            this.cedula = cliente.getCedula();
            this.direccion = cliente.getDireccion();
            this.telefono = cliente.getTelefono();
        }
    }
    
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }
    public String getUsuario(){
        return usuario;
    }
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    public String getCedula(){
        return cedula;
    }
    public void setCedula(String cedula){
        this.cedula = cedula;
    }
        public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ClienteEntity toEntity() {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(this.getId());
        entity.setNombre(this.getNombre());
        entity.setCorreo(this.getCorreo());
        entity.setUsuario(this.getUsuario());
        entity.setCedula(this.getCedula());
        entity.setDireccion(this.getDireccion());
        entity.setTelefono(this.getTelefono());
        return entity;
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
}

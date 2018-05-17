/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import java.util.logging.Logger;

/**
 * CarritoDTO Objeto de transferencia de datos del . Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 * {
 * "id": Long,
 * "nombre": String,
 * "correo": String,
 * "usuario": String,
 * "cedula": Integer,
 * "vendedor":String
 * }
 *   }
 * </pre> Por ejemplo un cliente se representa asi:<br>
 *
 * <pre>
 *
 *   {
 * "id": 1,
 * "nombre": "Ariel",
 * "correo": "asnar0@discuz.net",
 * "usuario": "␣",
 * "cedula": "8643803030",
 * "vendedor":String
 * }
 *
 * </pre>
 *
 * @author s.beltran
 */
public class ClienteDTO {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Modela el id de cliente.
     */
    private Long id;

    /**
     * Modela el nombre del cliente.
     */
    private String nombre;

    /**
     * Modela el correo del cliente.
     */
    private String correo;

    /**
     * Modela el usuario del cliente.
     */
    private String usuario;

    /**
     * Modela la cedula del cliente.
     */
    private String cedula;

    /**
     * Modela la direccion del cliente.
     */
    private String direccion;

    /**
     * Modela el telefono del cliente.
     */
    private String telefono;

    /**
     * Modela si el cliente es vendedor o no.
     */
    private String vendedor;

    /**
     * Constructor por defecto.
     */
    public ClienteDTO() {
        //empty
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param cliente: Es la entidad que se va a convertir a DTO
     */
    public ClienteDTO(ClienteEntity cliente) {
        if (cliente != null) {
            this.id = cliente.getId();
            this.nombre = cliente.getNombre();
            this.correo = cliente.getCorreo();
            this.usuario = cliente.getUsuario();
            this.cedula = cliente.getCedula();
            this.direccion = cliente.getDireccion();
            this.telefono = cliente.getTelefono();
            this.vendedor = cliente.getVendedor();
        }
    }

    /**
     * Devuelve si es vendedor o no.
     *
     * @return vendedor
     */
    public String getVendedor() {
        return vendedor;
    }

    /**
     * Modifica si es vendedor o no.
     *
     * @param vendedor nuevo vendedor
     */
    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * Devuelve el nombre.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el correo.
     *
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Modifica el correo.
     *
     * @param correo nuevo correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Devuelve el usuario.
     *
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Modifica el usuario.
     *
     * @param usuario nuevo usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Devuelve la cedula.
     *
     * @return cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Modifica la cedula.
     *
     * @param cedula nueva cedula
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Devuelve la direccion.
     *
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Modifica la direccion.
     *
     * @param direccion nueva direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve el telefono.
     *
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Modifica el telefono.
     *
     * @param telefono nuevo telefono
     */
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
        entity.setVendedor(this.getVendedor());
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
     * Devuelve el id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id
     *
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }
}

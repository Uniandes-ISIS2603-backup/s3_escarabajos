/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.beltran
 */
@Entity
public class ClienteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String usuario;
    private String cedula;
    private String direccion;
    private String telefono;
    
    @PodamExclude
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private CarritoEntity carrito;
    
    @PodamExclude
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private ListaDeseosEntity listaDeseos;

    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FacturaEntity> compras = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MedioPagoEntity> mediosPago = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CalificacionEntity> calificaciones = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ReclamoEntity> reclamos = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<BicicletaUsadaEntity> bicicletasUsadas = new ArrayList<>();
 
   
    
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
        final ClienteEntity other = (ClienteEntity) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the carrito
     */
    public CarritoEntity getCarrito() {
        return carrito;
    }

    /**
     * @param carrito the carrito to set
     */
    public void setCarrito(CarritoEntity carrito) {
        this.carrito = carrito;
    }

    /**
     * @return the listaDeseos
     */
    public ListaDeseosEntity getListaDeseos() {
        return listaDeseos;
    }

    /**
     * @param listaDeseos the listaDeseos to set
     */
    public void setListaDeseos(ListaDeseosEntity listaDeseos) {
        this.listaDeseos = listaDeseos;
    }

    /**
     * @return the compras
     */
    public List<FacturaEntity> getCompras() {
        return compras;
    }

    /**
     * @param compras the compras to set
     */
    public void setCompras(List<FacturaEntity> compras) {
        this.compras = compras;
    }

    /**
     * @return the mediosPago
     */
    public List<MedioPagoEntity> getMediosPago() {
        return mediosPago;
    }

    /**
     * @param mediosPago the mediosPago to set
     */
    public void setMediosPago(List<MedioPagoEntity> mediosPago) {
        this.mediosPago = mediosPago;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the reclamos
     */
    public List<ReclamoEntity> getReclamos() {
        return reclamos;
    }

    /**
     * @param reclamos the reclamos to set
     */
    public void setReclamos(List<ReclamoEntity> reclamos) {
        this.reclamos = reclamos;
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

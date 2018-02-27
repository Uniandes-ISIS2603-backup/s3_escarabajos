/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author s.beltran
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable {

    private String nombre;
    private String correo;
    private String usuario;
    private int cedula;
    
    @OneToOne(mappedBy="cliente")
    private CarritoEntity carrito;

    @OneToMany(mappedBy="cliente")
    private List<FacturaEntity> facturas;
    
    @OneToMany(mappedBy="cliente")
    private List<MedioPagoEntity> mediosPago;
    
    @OneToMany(mappedBy="cliente")
    private List<CalificacionEntity> calificaciones;
    
    @OneToMany(mappedBy="cliente")
    private List<ReclamoEntity> reclamos;
    
    public List<FacturaEntity> getFacturas(){
        return facturas;
    }
    public void setFacturas(List<FacturaEntity> facturas){
        this.facturas = facturas;
    }
    
     public List<MedioPagoEntity> getMediosPago(){
        return mediosPago;
    }
    public void setMediosPago(List<MedioPagoEntity> mediosPago){
        this.mediosPago = mediosPago;
    }
    
    public List<CalificacionEntity> getCalificaciones(){
        return calificaciones;
    }
    public void setCalificaciones(List<CalificacionEntity> calificaciones){
        this.calificaciones = calificaciones;
    }
    
    public List<ReclamoEntity> getReclamos(){
        return reclamos;
    }
    public void setReclamos(List<ReclamoEntity> reclamos){
        this.reclamos = reclamos;
    }
    
    public CarritoEntity getCarrito(){
        return carrito;
    }
    public void setCarrito(CarritoEntity carrito){
        this.carrito = carrito;
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
    public int getCedula(){
        return cedula;
    }
    public void setCedula(int cedula){
        this.cedula = cedula;
    } 
}

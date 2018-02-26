/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
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
    
    @OneToOne(mappedBy="carrito")
    private CarritoEntity carrito;

    @OneToMany(mappedBy="factura")
    private FacturaEntity factura;
    
    @OneToMany(mappedBy="medioPago")
    private MedioPagoEntity medioPago;
    
    @OneToMany(mappedBy="calificacion")
    private CalificacionEntity calificacion;
    
    @OneToMany(mappedBy="reclamo")
    private ReclamoEntity reclamo;
    
    public FacturaEntity getFactura(){
        return factura;
    }
    public void setFactura(FacturaEntity factura){
        this.factura = factura;
    }
    
     public MedioPagoEntity getMedioPago(){
        return medioPago;
    }
    public void setMedioPago(MedioPagoEntity medioPago){
        this.medioPago = medioPago;
    }
    
    public CalificacionEntity getCalificacion(){
        return calificacion;
    }
    public void setCalificacion(CalificacionEntity calificacion){
        this.calificacion = calificacion;
    }
    
    public ReclamoEntity getReclamo(){
        return reclamo;
    }
    public void setReclamo(ReclamoEntity reclamo){
        this.reclamo = reclamo;
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

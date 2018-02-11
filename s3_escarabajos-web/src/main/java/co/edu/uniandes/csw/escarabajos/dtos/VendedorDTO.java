/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.VendedorEntity;

/**
 *
 * @author s.beltran
 */
public class VendedorDTO {
    private String direccion;
    private String telefono;
    private String factura;
    
    public VendedorDTO(){
        
    }
    public VendedorDTO(VendedorEntity vendedor){
        this.direccion = vendedor.getDireccion();
        this.factura = vendedor.getFactura();
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
    public String getFactura(){
        return factura;
    }
    public void setFactura(String factura){
        this.factura = factura;
    }
    
    public VendedorEntity toEntity(){
        VendedorEntity entity = new VendedorEntity();
        entity.setDireccion(this.direccion);
        entity.setFactura(this.factura);
        entity.setTelefono(this.telefono);
        return entity;
    }
}

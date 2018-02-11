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
public class VendedorDetailDTO extends VendedorDTO{
    
    public VendedorDetailDTO(){
        
    }
    
    public VendedorDetailDTO (VendedorEntity entity){
        super(entity);
    }
    
    public VendedorEntity toEntity(){
        VendedorEntity vendedorE = new VendedorEntity();
        return vendedorE;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

/**
 *
 * @author s.beltran
 */
public class ClienteDetailDTO extends ClienteDTO{
    
    private CarritoDTO carrito;

    private List<FacturaDTO> compras;
    
    private List<MedioPagoDTO> mediosPago ;
    

    private List<CalificacionDTO> calificaciones ;
    

    private List<ReclamoDTO> reclamos ;
    

    /**private List<ItemDTO> listaDeseos ;*/
    /**
     * Constructor por defecto
     */
    public ClienteDetailDTO() {
        super();
    }
    
    //TODO Todos los que tiene podamexclude
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ClienteDetailDTO(ClienteEntity entity) {
        super(entity);
        if (entity != null) {
            if (entity.getCarrito()!= null) {
                this.carrito = new CarritoDTO(entity.getCarrito());
            } else {
                entity.setCarrito(null);
            }


                compras = new ArrayList<>();
                for (FacturaEntity entityCompras : entity.getCompras()) {
                    compras.add(new FacturaDTO(entityCompras));
                }
           
                reclamos = new ArrayList<>();
                for (ReclamoEntity entityReclamos : entity.getReclamos()) {
                    reclamos.add(new ReclamoDTO(entityReclamos));
                }

            
            
                calificaciones = new ArrayList<>();
                for (CalificacionEntity entityCalificaciones : entity.getCalificaciones()) {
                    calificaciones.add(new CalificacionDTO(entityCalificaciones));
                }

            
            
                mediosPago = new ArrayList<>();
                for (MedioPagoEntity entityMediosPago : entity.getMediosPago()) {
                    mediosPago.add(new MedioPagoDTO(entityMediosPago));
                }

            
        }
        
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public ClienteEntity toEntity() {
        ClienteEntity entity = super.toEntity();
        
        if (this.getCarrito()!= null) {
            entity.setCarrito(this.getCarrito().toEntity());
        }
        
        if (compras != null) {
            List<FacturaEntity> comprasEntity = new ArrayList<>();
            for (FacturaDTO dtoFactura : compras) {
                comprasEntity.add(dtoFactura.toEntity());
            }
            entity.setCompras(comprasEntity);
        }
        
       /* if (listaDeseos != null) {
            List<ItemEntity> listaDeseosEntity = new ArrayList<>();
            for (ItemDTO dtoListaDeseos : listaDeseos) {
                listaDeseosEntity.add(dtoListaDeseos.toEntity());
            }
            entity.setListaDeseos(listaDeseosEntity);
        }*/
        
        if (reclamos != null) {
            List<ReclamoEntity> reclamoEntity = new ArrayList<>();
            for (ReclamoDTO dtoReclamo : reclamos) {
                reclamoEntity.add(dtoReclamo.toEntity());
            }
            entity.setReclamos(reclamoEntity);
        }
        
        if (calificaciones != null) {
            List<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for (CalificacionDTO dtoCalificaciones : calificaciones) {
                calificacionesEntity.add(dtoCalificaciones.toEntity());
            }
            entity.setCalificaciones(calificacionesEntity);
        }
        
        if (mediosPago != null) {
            List<MedioPagoEntity> mediosPagoEntity = new ArrayList<>();
            for (MedioPagoDTO dtoMediosPago : mediosPago) {
                mediosPagoEntity.add(dtoMediosPago.toEntity());
            }
            entity.setMediosPago(mediosPagoEntity);
        }

        return entity;
    }

    /**
     * @return the carrito
     */
    public CarritoDTO getCarrito() {
        return carrito;
    }

    /**
     * @param carrito the carrito to set
     */
    public void setCarrito(CarritoDTO carrito) {
        this.carrito = carrito;
    }

    /**
     * @return the compras
     */
    public List<FacturaDTO> getCompras() {
        return compras;
    }

    /**
     * @param compras the compras to set
     */
    public void setCompras(List<FacturaDTO> compras) {
        this.compras = compras;
    }

    /**
     * @return the mediosPago
     */
    public List<MedioPagoDTO> getMediosPago() {
        return mediosPago;
    }

    /**
     * @param mediosPago the mediosPago to set
     */
    public void setMediosPago(List<MedioPagoDTO> mediosPago) {
        this.mediosPago = mediosPago;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the reclamos
     */
    public List<ReclamoDTO> getReclamos() {
        return reclamos;
    }

    /**
     * @param reclamos the reclamos to set
     */
    public void setReclamos(List<ReclamoDTO> reclamos) {
        this.reclamos = reclamos;
    }

    /**
     * @return the listaDeseos
     */
    /**public List<ItemDTO> getListaDeseos() {
        return listaDeseos;
    }/*

    /**
     * @param listaDeseos the listaDeseos to set
     */
    /**public void setListaDeseos(List<ItemDTO> listaDeseos) {
        this.listaDeseos = listaDeseos;
    }*/
}

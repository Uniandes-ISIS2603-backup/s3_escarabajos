/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
import co.edu.uniandes.csw.escarabajos.resources.ClienteCarritoResource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Clase que extiende de {@link ItemDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del item vaya a la documentacion de {@link ItemDTO}
 * 
 *Al serializarse como JSON esta clase implementa el siguiente cliente: <br>
 * <pre>
 * {
        "cedula": String,
        "correo": String,
        "id": Long,
        "nombre": String,
        "usuario": String,
        "calificaciones": [{@CalificacionDTO}],
        "compras": [{@FacturaDTO}],
        "mediosPago": [{@MedioPagoDTO}],
        "reclamos": [{@ReclamoDTO}],
        "regalos": [{@ReclamoDTO}]
 * }
 * </pre>
 * Por ejemplo un cliente se representa asi:<br>
 * 
 * <pre>
 *   {
        "type": "clienteDetailDTO",
        "cedula": "8643803030",
        "correo": "asnar0@discuz.net",
        "id": 4,
        "nombre": "pooper",
        "usuario": "â\ufffd£",
        "calificaciones": [],
        "compras": [],
        "mediosPago": [],
        "reclamos": [],
        "regalos": []
    }
 * </pre>
 * @author s.beltran
 */
public class ClienteDetailDTO extends ClienteDTO{
    
    
    
    private static final Logger LOGGER = Logger.getLogger(ClienteDetailDTO.class.getName());
    
    
    private CarritoDTO carrito;

    private List<FacturaDTO> compras;
    
    private List<MedioPagoDTO> mediosPago ;
    

    private List<CalificacionDTO> calificaciones ;
    

    private List<ReclamoDTO> reclamos ;
    
    private List<ItemDTO> regalos ;
    
    private List<BicicletaUsadaDTO> bicicletasUsadas;

    /**private List<ItemDTO> listaDeseos ;*/
    /**
     * Constructor por defecto
     */
    public ClienteDetailDTO() {
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ClienteDetailDTO(ClienteEntity entity) {
        
        super(entity);
        
        LOGGER.info("ENTRO AL DETAIL DTO CLIENTE");
                
        if (entity != null) {
            if (entity.getCarrito()!= null) {
                this.carrito = new CarritoDTO(entity.getCarrito());
            } else {
                entity.setCarrito(null);
            }
            
            if (entity.getDeseados()!= null) {
            regalos = new ArrayList<ItemDTO>();
                for (ItemEntity itemEntity : entity.getDeseados()) {
                    if (itemEntity instanceof AccesorioEntity) {
                       regalos.add(new AccesorioDTO((AccesorioEntity)itemEntity)); 
                    }
                    else if (itemEntity instanceof BicicletaEntity) {
                       regalos.add(new BicicletaDTO((BicicletaEntity)itemEntity)); 
                    }
                }
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
                
                bicicletasUsadas = new ArrayList<>();
                for (BicicletaUsadaEntity entityBicicletaUsada: entity.getBicicletasUsadas()) {
                    bicicletasUsadas.add(new BicicletaUsadaDTO(entityBicicletaUsada));
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
        
       if (getRegalos() != null) {
            List<ItemEntity> itemsEntities = new ArrayList<>();
            for (ItemDTO itemDto : getRegalos()) {
                itemsEntities.add(itemDto.toEntity());
            }
            entity.setDeseados(itemsEntities);
        }
        
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
        
        if (getBicicletasUsadas() != null) {
            List<BicicletaUsadaEntity> bicicletasUsadasEntity = new ArrayList<>();
            for (BicicletaUsadaDTO dtoBicicletaUsada : getBicicletasUsadas()) {
               bicicletasUsadasEntity.add(dtoBicicletaUsada.toEntity());
            }
            entity.setBicicletasUsadas(bicicletasUsadasEntity);
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
     * @return the regalos
     */
    public List<ItemDTO> getRegalos() {
        return regalos;
    }

    /**
     * @param regalos the regalos to set
     */
    public void setRegalos(List<ItemDTO> regalos) {
        this.regalos = regalos;
    }
    
    /**
     * @return the bicicletasUsadas
     */
    public List<BicicletaUsadaDTO> getBicicletasUsadas() {
        return bicicletasUsadas;
    }

    /**
     * @param bicicletasUsadas the bicicletasUsadas to set
     */
    public void setBicicletasUsadas(List<BicicletaUsadaDTO> bicicletasUsadas) {
        this.bicicletasUsadas = bicicletasUsadas;
    }

}

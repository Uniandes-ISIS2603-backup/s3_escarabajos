/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Clase que extiende de {@link ItemDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del item vaya a la documentacion de {@link ItemDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente cliente: <br>
 * <pre>
 * {
 * "cedula": String,
 * "correo": String,
 * "id": Long,
 * "nombre": String,
 * "usuario": String,
 * "calificaciones": [{@CalificacionDTO}],
 * "compras": [{
 * @FacturaDTO}], "mediosPago": [{
 * @MedioPagoDTO}], "reclamos": [{
 * @ReclamoDTO}], "carrito": {
 * @CarritoDTO}, "listaDeseos": {
 * @ListaDeseosDTO} }
 * </pre> Por ejemplo un cliente se representa asi:<br>
 *
 * <pre>
 *   {
 * "type": "clienteDetailDTO",
 * "cedula": "8643803030",
 * "correo": "asnar0@discuz.net",
 * "id": 4,
 * "nombre": "pooper",
 * "usuario": "â\ufffd£",
 * "calificaciones": [],
 * "compras": [],
 * "mediosPago": [],
 * "reclamos": [],
 * "regalos": [],
 * "carrito": {},
 * "listaDeseos": {}
 * }
 * </pre>
 *
 * @author s.beltran
 */
public class ClienteDetailDTO extends ClienteDTO {

    /**
     * Modela el carrito del cliente.
     */
    private CarritoDTO carrito;

    /**
     * Modela la lista de deseos del cliente.
     */
    private ListaDeseosDTO listaDeseos;

    /**
     * Modela las facturas del cliente.
     */
    private List<FacturaDTO> compras;

    /**
     * Modela los medios de pago del cliente.
     */
    private List<MedioPagoDTO> mediosPago;

    /**
     * Modela las calificaciones del cliente.
     */
    private List<CalificacionDTO> calificaciones;

    /**
     * Modela los reclamos del cliente.
     */
    private List<ReclamoDTO> reclamos;

    /**
     * Modela los regalos del cliente.
     */
    private List<ItemDTO> regalos;

    /**
     * Modela las bicicletas usadas del cliente.
     */
    private List<BicicletaUsadaDTO> bicicletasUsadas;

    /**
     * Constructor por defecto.
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

        if (entity != null) {
            if (entity.getCarrito() != null) {
                this.carrito = new CarritoDTO(entity.getCarrito());
            } else {
                entity.setCarrito(null);
            }
            if (entity.getListaDeseos() != null) {
                this.listaDeseos = new ListaDeseosDTO(entity.getListaDeseos());
            } else {
                entity.setListaDeseos(null);
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
            for (BicicletaUsadaEntity entityBicicletaUsada : entity.getBicicletasUsadas()) {
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

        if (this.getCarrito() != null) {
            entity.setCarrito(this.getCarrito().toEntity());
        }

        if (this.getListaDeseos() != null) {
            entity.setListaDeseos(this.getListaDeseos().toEntity());
        }

        if (compras != null) {
            List<FacturaEntity> comprasEntity = new ArrayList<>();
            for (FacturaDTO dtoFactura : compras) {
                comprasEntity.add(dtoFactura.toEntity());
            }
            entity.setCompras(comprasEntity);
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
     * Devuelve el carrito.
     *
     * @return carrito
     */
    public CarritoDTO getCarrito() {
        return carrito;
    }

    /**
     * Modifica el carrito.
     *
     * @param carrito nuevo carrito
     */
    public void setCarrito(CarritoDTO carrito) {
        this.carrito = carrito;
    }

    /**
     * Devuelve las compras.
     *
     * @return compras
     */
    public List<FacturaDTO> getCompras() {
        return compras;
    }

    /**
     * Modifica las compras.
     *
     * @param compras nuevas compras
     */
    public void setCompras(List<FacturaDTO> compras) {
        this.compras = compras;
    }

    /**
     * Devuelve los medios de pago
     *
     * @return mediosPago
     */
    public List<MedioPagoDTO> getMediosPago() {
        return mediosPago;
    }

    /**
     * Modifica los medios de pago.
     *
     * @param mediosPago nuevos medios de Pago
     */
    public void setMediosPago(List<MedioPagoDTO> mediosPago) {
        this.mediosPago = mediosPago;
    }

    /**
     * Devuelve las calificaciones.
     *
     * @return calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Modifica las calificaciones.
     *
     * @param calificaciones calificaciones
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * Devuelve los reclamos
     *
     * @return reclamos
     */
    public List<ReclamoDTO> getReclamos() {
        return reclamos;
    }

    /**
     * Modifica los reclamos.
     *
     * @param reclamos nuevos reclamos
     */
    public void setReclamos(List<ReclamoDTO> reclamos) {
        this.reclamos = reclamos;
    }

    /**
     * Devuelve los regalos.
     *
     * @return regalos
     */
    public List<ItemDTO> getRegalos() {
        return regalos;
    }

    /**
     * Modifica los regalos.
     *
     * @param regalos nuevos regalos
     */
    public void setRegalos(List<ItemDTO> regalos) {
        this.regalos = regalos;
    }

    /**
     * Devuelve las bicicletas usadas.
     *
     * @return bicicletasUsadas
     */
    public List<BicicletaUsadaDTO> getBicicletasUsadas() {
        return bicicletasUsadas;
    }

    /**
     * Modifica las bicicletas usadas.
     *
     * @param bicicletasUsadas nuevas bicicletas Usadasto
     */
    public void setBicicletasUsadas(List<BicicletaUsadaDTO> bicicletasUsadas) {
        this.bicicletasUsadas = bicicletasUsadas;
    }

    /**
     * Devuelve la lista de deseos.
     *
     * @return listaDeseos
     */
    public ListaDeseosDTO getListaDeseos() {
        return listaDeseos;
    }

    /**
     * Modifica la lista de deseos.
     *
     * @param listaDeseos nueva lista de deseos
     */
    public void setListaDeseos(ListaDeseosDTO listaDeseos) {
        this.listaDeseos = listaDeseos;
    }

}

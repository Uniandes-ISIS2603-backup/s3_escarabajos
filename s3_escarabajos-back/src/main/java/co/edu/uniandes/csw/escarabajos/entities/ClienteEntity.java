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

    /**
     * Atributo que representa el id del cliente. id autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa el nombre del cliente.
     */
    private String nombre;

    /**
     * Atributo que representa el correo del cliente.
     */
    private String correo;

    /**
     * Atributo que representa el usuario del cliente.
     */
    private String usuario;

    /**
     * Atributo que representa la cedula del cliente.
     */
    private String cedula;

    /**
     * Atributo que representa la direccion del cliente.
     */
    private String direccion;

    /**
     * Atributo que representa el telefono del cliente.
     */
    private String telefono;

    /**
     * Atributo que representa si el cliente es vendedor o no.
     */
    private String vendedor;

    /**
     * Atributo que representa el carrito del cliente.
     */
    @PodamExclude
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private CarritoEntity carrito;

    /**
     * Atributo que representa la lista de deseos del cliente.
     */
    @PodamExclude
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private ListaDeseosEntity listaDeseos;

    /**
     * Atributo que representa las facturas del cliente.
     */
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FacturaEntity> compras = new ArrayList<>();

    /**
     * Atributo que representa los medios de pago del cliente.
     */
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MedioPagoEntity> mediosPago = new ArrayList<>();

    /**
     * Atributo que representa las calificaciones del cliente.
     */
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CalificacionEntity> calificaciones = new ArrayList<>();

    /**
     * Atributo que representa los reclamos del cliente.
     */
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ReclamoEntity> reclamos = new ArrayList<>();

    /**
     * Atributo que representa las bicicletas usadas del cliente.
     */
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<BicicletaUsadaEntity> bicicletasUsadas = new ArrayList<>();

    /**
     * Compara si dos objetos son iguales.
     *
     * @param obj objeto a comparar
     * @return boolean Si son iguales retorna true. De lo contrario retorna
     * false
     */
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

    /**
     * Metodo predeterminado hashCode.
     *
     * @return el hashCode creado
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

    /**
     * Devuelve el id.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id.
     *
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
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
     * Modifica el nombre del cliente.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el correo del cliente.
     *
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Modifica el correo del cliente.
     *
     * @param correo nuevo correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Devuelve el usuario del cliente.
     *
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Modifica el usuario del cliente.
     *
     * @param usuario nuevo usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Devuelve la cedula del cliente.
     *
     * @return cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Modifica la cedula del cliente.
     *
     * @param cedula nueva cedula
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Devuelve la direccion del cliente.
     *
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Modifica la direccion del cliente.
     *
     * @param direccion nueva direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve el telefono del cliente.
     *
     * @return
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Modifica el telefono del cliente.
     *
     * @param telefono nuevo telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Devuelve el carrito del cliente.
     *
     * @return carrito
     */
    public CarritoEntity getCarrito() {
        return carrito;
    }

    /**
     * Modifica el carrito del cliente.
     *
     * @param carrito nuevo carrito
     */
    public void setCarrito(CarritoEntity carrito) {
        this.carrito = carrito;
    }

    /**
     * Devuelve la lista de deseos del cliente.
     *
     * @return lista de Deseos
     */
    public ListaDeseosEntity getListaDeseos() {
        return listaDeseos;
    }

    /**
     * Modifica la lista de deseos del cliente.
     *
     * @param listaDeseos nueva lista de Deseos
     */
    public void setListaDeseos(ListaDeseosEntity listaDeseos) {
        this.listaDeseos = listaDeseos;
    }

    /**
     * Devuelve las facturas del cliente.
     *
     * @return compras
     */
    public List<FacturaEntity> getCompras() {
        return compras;
    }

    /**
     * Modifica las facturas del cliente.
     *
     * @param compras nuevas compras
     */
    public void setCompras(List<FacturaEntity> compras) {
        this.compras = compras;
    }

    /**
     * Devuelve los medios de pago del cliente.
     *
     * @return mediosPago
     */
    public List<MedioPagoEntity> getMediosPago() {
        return mediosPago;
    }

    /**
     * Modifica los medios de pago del cliente.
     *
     * @param mediosPago nuevos medios de pago
     */
    public void setMediosPago(List<MedioPagoEntity> mediosPago) {
        this.mediosPago = mediosPago;
    }

    /**
     * Devuelve las calificaciones del cliente.
     *
     * @return calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Modifica las calificaciones del cliente.
     *
     * @param calificaciones nuevas calificaciones
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * Devuelve los reclamos del cliente.
     *
     * @return reclamos
     */
    public List<ReclamoEntity> getReclamos() {
        return reclamos;
    }

    /**
     * Modifica los reclamos del cliente.
     *
     * @param reclamos nuevos reclamos
     */
    public void setReclamos(List<ReclamoEntity> reclamos) {
        this.reclamos = reclamos;
    }

    /**
     * Devuelve las bicicletas usadas del cliente.
     *
     * @return bicicletasUsadas
     */
    public List<BicicletaUsadaEntity> getBicicletasUsadas() {
        return bicicletasUsadas;
    }

    /**
     * Modifica las bicicletas usadas del cliente.
     *
     * @param bicicletasUsadas nuevas bicicletas usadas
     */
    public void setBicicletasUsadas(List<BicicletaUsadaEntity> bicicletasUsadas) {
        this.bicicletasUsadas = bicicletasUsadas;
    }

    /**
     * Devuelve si el cliente es vendedor o no.
     *
     * @return vendedor
     */
    public String getVendedor() {
        return vendedor;
    }

    /**
     * Modifica si el cliente es vendedor o no.
     *
     * @param vendedor nuevo vendedor
     */
    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }
}

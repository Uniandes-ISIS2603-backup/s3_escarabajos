/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import co.edu.uniandes.csw.escarabajos.entities.TransferenciaEntity;

/**
 *
 * @author jp.carreno
 */
@Entity
public class TransferenciaDTO{

    @Id
    private Long id;
    private String usuarioT;
    private String dineroT;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuarioT() {
        return usuarioT;
    }

    public String getDineroT() {
        return dineroT;
    }

    public void setUsuarioT(String usuarioT) {
        this.usuarioT = usuarioT;
    }

    public void setDineroT(String dineroT) {
        this.dineroT = dineroT;
    }
    
    /**
     * Constructor por defecto
     */
    public TransferenciaDTO() {

    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param bici: Es la entidad que se va a convertir a DTO
     */
    public TransferenciaDTO(TransferenciaEntity transfer) {
        this.id = transfer.getId();
        this.usuarioT = transfer.getUsuario();
        this.dineroT = transfer.getDinero();
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public TransferenciaEntity toEntity() {
        TransferenciaEntity entity = new TransferenciaEntity();
        entity.setId(this.id);
        entity.setDinero(this.dineroT);
        entity.setUsuario(this.usuarioT);

        return entity;
    }
    
}

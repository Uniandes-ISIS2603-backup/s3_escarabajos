/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andres
 */
public class ItemPersistence {
    
    public static final Logger LOGGER = Logger.getLogger(ItemPersistence.class.getName());
    
    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;
   
    /**
     * Crea un autor en la base de datos
     * @param entity objeto item que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ItemEntity create(ItemEntity entity) {
        LOGGER.info("Creando un item nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir el item en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un item nuevo");
        return entity;
    }

    /**
     * Actualiza un item.
     *
     * @param entity: el item que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un item con los cambios aplicados.
     */
    public ItemEntity update(ItemEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando item con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        el item con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     * Borra un item de la base de datos recibiendo como argumento el id
     * del item
     *
     * @param id: id correspondiente a el item a borrar.
     */
    public void delete(Long id) {

        LOGGER.log(Level.INFO, "Borrando item con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public ItemEntity find(Long id) para obtener el item a borrar.
        ItemEntity entity = em.find(ItemEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from ItemEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);

    }

    /**
     * Busca si hay algun item con el id que se envía de argumento
     *
     * @param id: id correspondiente a el item buscado.
     * @return un item.
     */
    public ItemEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando item con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from ItemEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(ItemEntity.class, id);
    }

    /**
     * Devuelve todos los items de la base de datos.
     *
     * @return una lista con todos lositems que encuentre en la base de
     * datos, "select u from ItemEntity u" es como un "select * from
     * ItemEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<ItemEntity> findAll() {
        LOGGER.info("Consultando todos los items");
        // Se crea un query para buscar todos los items en la base de datos.
        TypedQuery query = em.createQuery("select u from ItemEntity u", ItemEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de items.
        return query.getResultList();
    }
}
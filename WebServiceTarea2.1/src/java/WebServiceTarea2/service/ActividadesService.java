/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceTarea2.service;

import WebServiceTarea2.model.Actividades;
import WebServiceTarea2.model.ActividadesDto;
import WebServiceTarea2.util.CodigoRespuesta;
import WebServiceTarea2.util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import static org.jboss.weld.logging.BeanLogger.LOG;

/**
 *
 * @author Bran
 */
@Stateless
@LocalBean
public class ActividadesService {
    private static final Logger LOG = Logger.getLogger(ActividadesService.class.getName());//imprime el error en payara
    @PersistenceContext(unitName = "WebServiceTarea2PU")
    private EntityManager em ;
    
    
   public Respuesta getActividadeses() {
        try {
            Query qryActividades = em.createNamedQuery("Actividad.findAll", Actividades.class);
            List<Actividades> Actividades = qryActividades.getResultList();
            List<ActividadesDto> Actividadeses = new ArrayList<>();
            for (Actividades actividades : Actividades) {
                Actividadeses.add(new ActividadesDto(actividades));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Actividades", Actividadeses);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen Actividadess con los criterios ingresados.", "getActividadess NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Actividades.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Actividades.", "getActividades " + ex.getMessage());
        }
    }

    public Respuesta guardarActividades(ActividadesDto ActividadesDto) {
        try {
            Actividades Actividades;
            if (ActividadesDto.getId()!= null && ActividadesDto.getId() > 0) {
                Actividades = em.find(Actividades.class, ActividadesDto.getId());

                if (Actividades == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Actividades a modificar.", "guardarActividades NoResultException");
                }

                Actividades.actualizarActividades(ActividadesDto);
                Actividades = em.merge(Actividades);
                
            } else {
                Actividades = new Actividades(ActividadesDto);
                em.persist(Actividades);
            }

            em.flush();

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "Actividades guardado exitosamente", "", "Actividades", new ActividadesDto(Actividades));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Actividades.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Actividades.", "guardarActividades " + ex.getMessage());
        }
    }

    public Respuesta eliminarActividades(Long id) {
        try {
            //Empleado empleado;
            Actividades Actividades;
            if (id != null && id > 0) {
                Actividades = em.find(Actividades.class, id);
                if (Actividades == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el Actividades a eliminar.", "EliminarActividades NoResultException");
                }
                em.remove(Actividades);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_CLIENTE, "Debe cargar el Actividades a eliminar.", "EliminarActividades NoResultException");
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_PERMISOS, "No se puede eliminar el Actividades porque tiene relaciones con otros registros.", "EliminarActividades " + ex.getMessage());
            }
            Logger.getLogger(ActividadesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el Actividades.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el Actividades.", "EliminarActividades " + ex.getMessage());
        }
    }
}

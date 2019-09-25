/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceTarea2.service;

import WebServiceTarea2.model.Proyecto;
import WebServiceTarea2.model.ProyectoDto;
import WebServiceTarea2.model.Proyecto;
import WebServiceTarea2.model.ProyectoDto;
import WebServiceTarea2.util.CodigoRespuesta;
import WebServiceTarea2.util.Respuesta;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.math.BigDecimal;
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

/**
 *
 * @author Bran
 */
@Stateless
@LocalBean
public class ProyectoService {
    private static final Logger LOG = Logger.getLogger(ProyectoService.class.getName());//imprime el error en payara
    @PersistenceContext(unitName = "WebServiceTarea2PU")
    private EntityManager em;
    
    public Respuesta getProyectos() {
        try {
            Query qryProyecto = em.createNamedQuery("Proyecto.findAll", Proyecto.class);
            List<Proyecto> proyectos = qryProyecto.getResultList();
            List<ProyectoDto> proyectosDto = new ArrayList<>();
            for (Proyecto proyecto : proyectos) {
                proyectosDto.add(new ProyectoDto(proyecto));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Proyectos", proyectosDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen Proyectos con los criterios ingresados.", "getProyectos NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Proyecto.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Proyecto.", "getProyecto " + ex.getMessage());
        }
    }

    public Respuesta guardarProyecto(ProyectoDto ProyectoDto) {
        try {
            Proyecto Proyecto;
            if (ProyectoDto.getProId() != null && ProyectoDto.getProId() > 0) {
                Proyecto = em.find(Proyecto.class, ProyectoDto.getProId());

                if (Proyecto == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Proyecto a modificar.", "guardarProyecto NoResultException");
                }
                Proyecto.actualizarProyecto(ProyectoDto);
                Proyecto = em.merge(Proyecto);
                
            } else {
                Proyecto = new Proyecto(ProyectoDto);
                em.persist(Proyecto);
            }

            em.flush();

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "Proyecto guardado exitosamente", "", "Proyecto", new ProyectoDto(Proyecto));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Proyecto.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Proyecto.", "guardarProyecto " + ex.getMessage());
        }
    }

    public Respuesta eliminarProyecto(Long id) {
        try {
            //Empleado empleado;
            Proyecto Proyecto;
            if (id != null && id > 0) {
                Proyecto = em.find(Proyecto.class, id);
                if (Proyecto == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el Proyecto a eliminar.", "EliminarProyecto NoResultException");
                }
                em.remove(Proyecto);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_CLIENTE, "Debe cargar el Proyecto a eliminar.", "EliminarProyecto NoResultException");
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_PERMISOS, "No se puede eliminar el Proyecto porque tiene relaciones con otros registros.", "EliminarProyecto " + ex.getMessage());
            }
            Logger.getLogger(ProyectoService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el Proyecto.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el Proyecto.", "EliminarProyecto " + ex.getMessage());
        }
    }
}

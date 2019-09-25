/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceTarea2.service;

import WebServiceTarea2.model.Seguimiento;
import WebServiceTarea2.model.SeguimientoDto;
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

/**
 *
 * @author Jose Pablo Bermudez
 */
@Stateless
@LocalBean
public class SeguimientoService {
    private static final Logger LOG = Logger.getLogger(SeguimientoService.class.getName());//imprime el error en payara
    @PersistenceContext(unitName = "WebServiceTarea2PU")
    private EntityManager em;
    
    
     public Respuesta getSeguimientos() {
        try {
            Query qrySeguimiento = em.createNamedQuery("Seguimiento.findAll", Seguimiento.class);
            List<Seguimiento> Seguimientos = qrySeguimiento.getResultList();
            List<SeguimientoDto> SeguimientosDto = new ArrayList<>();
            for (Seguimiento Seguimiento : Seguimientos) {
                SeguimientosDto.add(new SeguimientoDto(Seguimiento));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Seguimientos", SeguimientosDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen Seguimientos con los criterios ingresados.", "getSeguimientos NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Seguimiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Seguimiento.", "getSeguimiento " + ex.getMessage());
        }
    }

    public Respuesta guardarSeguimiento(SeguimientoDto SeguimientoDto) {
        try {
            Seguimiento Seguimiento;
            if (SeguimientoDto.getId()!= null && SeguimientoDto.getId() > 0) {
                Seguimiento = em.find(Seguimiento.class, SeguimientoDto.getId());

                if (Seguimiento == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Seguimiento a modificar.", "guardarSeguimiento NoResultException");
                }

                Seguimiento.actualizarSeguimiento(SeguimientoDto);
                Seguimiento = em.merge(Seguimiento);
                
            } else {
                Seguimiento = new Seguimiento(SeguimientoDto);
                em.persist(Seguimiento);
            }

            em.flush();

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "Seguimiento guardado exitosamente", "", "Seguimiento", new SeguimientoDto(Seguimiento));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Seguimiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Seguimiento.", "guardarSeguimiento " + ex.getMessage());
        }
    }

    public Respuesta eliminarSeguimiento(Long id) {
        try {
            //Empleado empleado;
            Seguimiento Seguimiento;
            if (id != null && id > 0) {
                Seguimiento = em.find(Seguimiento.class, id);
                if (Seguimiento == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el Seguimiento a eliminar.", "EliminarSeguimiento NoResultException");
                }
                em.remove(Seguimiento);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_CLIENTE, "Debe cargar el Seguimiento a eliminar.", "EliminarSeguimiento NoResultException");
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_PERMISOS, "No se puede eliminar el Seguimiento porque tiene relaciones con otros registros.", "EliminarSeguimiento " + ex.getMessage());
            }
            Logger.getLogger(SeguimientoService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el Seguimiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el Seguimiento.", "EliminarSeguimiento " + ex.getMessage());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceTarea2.service;

import WebServiceTarea2.model.Administrador;
import WebServiceTarea2.model.AdministradorDto;
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
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author Jose Pablo Bermudez
 */
@Stateless
@LocalBean
public class AdministradorService {

    @PersistenceContext(unitName = "WebServiceTarea2PU")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(AdministradorService.class.getName());
     
    /*public Administrador getAdmin(String usu, String contra){
        Administrador admin; 
        Query qryUsuContra = em.createNamedQuery("Administrador.findByAdmClave",Administrador.class);
        qryUsuContra.setParameter("admClave",contra);
        qryUsuContra.setParameter("admUsuario",usu);
        try {
            admin = (Administrador) qryUsuContra.getSingleResult();
        } catch (NoResultException ex) {
            admin = null;
        }
        return admin; 
    }
    
    public Administrador getAdmin(Long adminId){
        Administrador admin;
        Query qry = em.createNamedQuery("Administrador.findByAdmId", Administrador.class);
        qry.setParameter("admId", adminId);
        try{
            admin = (Administrador) qry.getSingleResult();
        } catch(NoResultException ex){
            admin = null;
        }
        return admin;
    }*/
    
    public Respuesta validarAdministrador(String usuario, String contrasena) { 
        try {
            Query qryActividad = em.createNamedQuery("Administrador.findByUsuClave", Administrador.class);
            qryActividad.setParameter("adnUsuario", usuario);
            qryActividad.setParameter("adnContrasena", contrasena);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Administrador", new AdministradorDto((Administrador) qryActividad.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con las credenciales ingresadas.", "validarUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario " + ex.getMessage());
        }
    }
    
    public Administrador getAdmin(String usu, String contra){
        Administrador admin; 
        Query qryUsuContra = em.createNamedQuery("Administrador.findByAdnUsClave",Administrador.class);
        qryUsuContra.setParameter("usu",usu);
        qryUsuContra.setParameter("contra",contra);
        try {
            admin = (Administrador) qryUsuContra.getSingleResult();
        } catch (NoResultException ex) {
            admin = null;
        }
        return admin; 
    }
    
    public Respuesta getAdministradores() {
        try {
            Query qryAdministrador = em.createNamedQuery("Administrador.findAll", Administrador.class);
            List<Administrador> Administrador = qryAdministrador.getResultList();
            List<AdministradorDto> AdministradorsDto = new ArrayList<>();
            for (Administrador administradores : Administrador) {
                AdministradorsDto.add(new AdministradorDto(administradores));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Administradors", AdministradorsDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen Administradors con los criterios ingresados.", "getAdministradors NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Administrador.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Administrador.", "getAdministrador " + ex.getMessage());
        }
    }

    public Respuesta guardarAdministrador(AdministradorDto AdministradorDto) {
        try {
            Administrador Administrador;
            if (AdministradorDto.getAdnId()!= null && AdministradorDto.getAdnId() > 0) {
                Administrador = em.find(Administrador.class, AdministradorDto.getAdnId());
                if (Administrador == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Administrador a modificar", "guardarAdministrador NoResultException");
                }
                Administrador.actualizarAdministrador(AdministradorDto);
                Administrador = em.merge(Administrador);
            } else {
                Administrador = new Administrador(AdministradorDto);
                Administrador.setAdnEstado("A");
                Administrador.setAdnVersion(new Long(1));
                em.persist(Administrador);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "Administrador guardado exitosamente", "", "Administrador", new AdministradorDto(Administrador));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Administrador.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Administrador.", "guardarAdministrador " + ex.getMessage());
        }
    }
    
    public Respuesta eliminarAdministrador(Long id) {
        try {
            //Empleado empleado;
            Administrador Administrador;
            if (id != null && id > 0) {
                Administrador = em.find(Administrador.class, id);
                if (Administrador == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el Administrador a eliminar.", "EliminarAdministrador NoResultException");
                }
                em.remove(Administrador);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_CLIENTE, "Debe cargar el Administrador a eliminar.", "EliminarAdministrador NoResultException");
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_PERMISOS, "No se puede eliminar el Administrador porque tiene relaciones con otros registros.", "EliminarAdministrador " + ex.getMessage());
            }
            Logger.getLogger(AdministradorService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el Administrador.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el Administrador.", "EliminarAdministrador " + ex.getMessage());
        }
    }
    
    public Administrador getAdmin(Long adminId){
        Administrador admin;
        Query qry = em.createNamedQuery("Administrador.findByAdmId", Administrador.class);
        qry.setParameter("admId", adminId);
        try{
            admin = (Administrador) qry.getSingleResult();
        } catch(NoResultException ex){
            admin = null;
        }
        return admin;
    }
    
    /**
     * 
     * @param admin
     * @return 
     */ 
    /*public Boolean eliminarAdmin(Administrador admin){
        Administrador adminAux = null;
        if(admin!=null && admin.getAdmId()!=null){
            Query qryId = em.createNamedQuery("Administrador.findByAdmId", Administrador.class);            
            qryId.setParameter("admId", admin.getAdmId());   
            try {
                adminAux = (Administrador) qryId.getSingleResult();
            } catch (NoResultException ex) {
                adminAux = null;
            }
            if(adminAux != null){
                Administrador adminAux2 = adminAux;
                em.remove(adminAux2);
                em.flush();
                em.getEntityManagerFactory().getCache().evictAll();
                adminAux = getAdmin(adminAux2.getAdmId());
            } else {
                adminAux = null;
            }
        }
        return (adminAux == null);
    }*/
    
/*    public Respuesta validarAdministrador(String usuario, String clave) {
        try {
            Query query = em.createNamedQuery("Administrador.findbyUsuClave", Administrador.class);
            query.setParameter("usuario", usuario);
            query.setParameter("clave", clave);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Administrador", new AdministradorDto((Administrador) query.getSingleResult()));
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con las credenciales ingresadas", "alidar usuario No result exception");
        } catch (Exception es) {
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error con la consulta", "Error desconocido Exception");
        }
    }

    public Respuesta cargarAdministradores() {
        try {
            Query query = em.createNamedQuery("Administrador.findAll");
            List<Administrador> adminsitradorList = query.getResultList();
            List<AdministradorDto> administradorDtoList = new ArrayList<>();
            for (Administrador administrador : adminsitradorList) {
                administradorDtoList.add(new AdministradorDto(administrador));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Administradores", administradorDtoList);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "no existen adminsitradores en este momento", "No ResultException");
        } catch (Exception ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "error desconocido", "excepton");
        }
    }

    public Respuesta GuardarAdministrador(AdministradorDto administradorDto) {
        try {
            Administrador administrador;
            if (administradorDto.getID() != null && administradorDto.getID() > 0) {
                administrador = em.find(Administrador.class, administradorDto.getID());
                if (administrador == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontro el empleado a modificar", "guardar empleado NoResult");
                }
                administrador.actualizarAdministrador(administradorDto);
                em.persist(administrador);
            } else {
                administrador = new Administrador(administradorDto);
                em.persist(administrador);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "administrador", new AdministradorDto(administrador));
        } catch (Exception ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el empleado", "guardar empleado");
        }

    }

    public Respuesta eliminarAdmnistrador(Long id) {
        try {
            Administrador administrador;
            if (id != 0 && id > 0) {
                administrador = em.find(Administrador.class, id);
                if (administrador == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "no se encontro el adminsitrador que desea eliminar", "Objetivo a eliminar no encontrado");

                }
                em.remove(administrador);

            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Debe de seleccionar un empleado a eliminar ", "no se entro un id valido");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");

        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {// relaciones con otras entidades
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "El Administrador tiene relaciones con otros registros, no se pudo eliminar", "depedenciade entidades");
            }
            return new Respuesta(false,CodigoRespuesta.ERROR_INTERNO,"error desconocido","excepcion");
        }
    }*/
    
   
}

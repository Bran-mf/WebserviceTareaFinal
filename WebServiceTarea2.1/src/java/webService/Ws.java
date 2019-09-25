/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webService;

import WebServiceTarea2.model.ActividadesDto;
import WebServiceTarea2.model.Administrador;
import WebServiceTarea2.model.AdministradorDto;
import WebServiceTarea2.model.ProyectoDto;
import WebServiceTarea2.model.SeguimientoDto;
import WebServiceTarea2.service.ActividadesService;
import WebServiceTarea2.service.AdministradorService;
import WebServiceTarea2.service.ProyectoService;
import WebServiceTarea2.service.SeguimientoService;
import WebServiceTarea2.util.CodigoRespuesta;
import WebServiceTarea2.util.Respuesta;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Jose Pablo Bermudez
 */
@WebService(serviceName = "WS")
public class WS {
    @EJB
    ActividadesService actividadService;
    @EJB
    AdministradorService administradorService;
    @EJB
    ProyectoService proyectoService;
    @EJB
    SeguimientoService seguimientoService;

    @WebMethod(operationName = "getUsuario")
    public Respuesta getUsuario(@WebParam(name = "usuario") String usuario, @WebParam(name = "clave") String clave) {
        try {
            // Consulto el service del Administrador y devuelvo la respuesta al cliente
            Respuesta respuesta = administradorService.validarAdministrador(usuario, clave);
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error al obtener el Usuario", "getUsuario:"+ex.getMessage());
        }
    }

    @WebMethod(operationName = "guardarAdministrador")
    public Respuesta guardarAdministrador(@WebParam(name = "Administrador") AdministradorDto Administrador) {
        try {
            Respuesta respuesta = administradorService.guardarAdministrador(Administrador);
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error guardando usuario", ex.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarAdministrador")
    public Respuesta EliminarAdministrador(@WebParam(name = "ID") Long ID) {
        try {
            Respuesta respuesta = administradorService.eliminarAdministrador(ID);
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false,CodigoRespuesta.ERROR_CLIENTE, "Error al eliminar el Administrador", ex.getMessage());
        }
    }

    /**
     * Web service operation
     * @param proyecto
     * @return 
     */
    @WebMethod(operationName = "guardarProyecto")
    public Respuesta guardarProyecto(@WebParam(name = "proyecto") ProyectoDto proyecto) {
        //TODO write your implementation code here:
        try {
            Respuesta respuesta = proyectoService.guardarProyecto(proyecto);
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error guardando Proyecto", ex.getMessage());
        }
    }

    /**
     * Web service operation
     * @param ID
     * @return 
     */
    @WebMethod(operationName = "eliminarProyecto")
    public Respuesta eliminarProyecto(@WebParam(name = "ID") Long ID) {
       try {
            Respuesta respuesta = proyectoService.eliminarProyecto(ID);
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false,CodigoRespuesta.ERROR_CLIENTE, "Error al eliminar el proyecto.", ex.getMessage());
        }
    }

    /**
     * Web service operation
     * @param adminPorPro
     * @return 
     */
  

    /**
     * Web service operation
     * @param actividad
     * @return 
     */
    @WebMethod(operationName = "guardarActividad")
    public Respuesta guardarActividad(@WebParam(name = "actividad") ActividadesDto actividad) {
        //TODO write your implementation code here:
        try {
            Respuesta respuesta = actividadService.guardarActividades(actividad);
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error guardando la Actividad", ex.getMessage());
            
        }
    }

    /**
     * Web service operation
     * @param ID
     * @return 
     */
   /* @WebMethod(operationName = "elminarActividad")
    public Respuesta elminarActividad(@WebParam(name = "ID") Long ID) {
        try {
            Respuesta respuesta = actividadService.eliminarActividad(ID);
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(ws.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false,CodigoRespuesta.ERROR_CLIENTE, "Error al eliminar la Actividad", ex.getMessage());
        }
    }*/

    /**
     * Web service operation
     * @param seguimiento
     * @return 
     */
    @WebMethod(operationName = "guardarSeguimiento")
    public Respuesta guardarSeguimiento(@WebParam(name = "seguimiento") SeguimientoDto seguimiento) {
        //TODO write your implementation code here:
        try {
            Respuesta respuesta = seguimientoService.guardarSeguimiento(seguimiento);
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error guardando el Seguimiento", ex.getMessage());
        }
    }

    /**
     * Web service operation
     * @param ID
     * @return 
     */
    @WebMethod(operationName = "eliminarSeguimiento")
    public Respuesta eliminarSeguimiento(@WebParam(name = "ID") Long ID) {
        try {
            Respuesta respuesta = seguimientoService.eliminarSeguimiento(ID);
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false,CodigoRespuesta.ERROR_CLIENTE, "Error al eliminar el seguimiento", ex.getMessage());
        }
    }

    /**
     * Web service operation
     * @param ID
     * @return 
     */
    @WebMethod(operationName = "eliminarActividad")
    public Respuesta eliminarActividad(@WebParam(name = "ID") Long ID) {
        //TODO write your implementation code here:
        try {
            Respuesta respuesta = actividadService.eliminarActividades(ID);
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false,CodigoRespuesta.ERROR_CLIENTE, "Error al eliminar la Actividad", ex.getMessage());
        }
    }
    
}

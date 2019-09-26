/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceTarea2.util;

import java.io.Serializable;
import java.util.HashMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ccarranza
 */
@XmlRootElement(name = "Respuesta")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Respuesta implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Boolean estado;
    private CodigoRespuesta codigoRespuesta;   
    private String mensaje;    
    private String mensajeInterno;
    private Object resultado;

    public Respuesta() {
    }

    public Respuesta(Boolean estado, CodigoRespuesta codigoRespuesta, String mensaje, String mensajeInterno) {
        this.estado = estado;
        this.codigoRespuesta = codigoRespuesta;
        this.mensaje = mensaje;
        this.mensajeInterno = mensajeInterno;
    }
    
    public Respuesta(Boolean estado, CodigoRespuesta codigoRespuesta, String mensaje, String mensajeInterno, String nombre, Object resultado) {
        this.estado = estado;
        this.codigoRespuesta = codigoRespuesta;
        this.mensaje = mensaje;
        this.mensajeInterno = mensajeInterno;
        this.resultado = resultado;
    }

    public Respuesta(Boolean estado, CodigoRespuesta codigoRespuesta, String mensaje, String mensajeInterno, Object resultado) {
        this.estado = estado;
        this.codigoRespuesta = codigoRespuesta;
        this.mensaje = mensaje;
        this.mensajeInterno = mensajeInterno;
        this.resultado = resultado;
    }
    
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public CodigoRespuesta getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(CodigoRespuesta codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeInterno() {
        return mensajeInterno;
    }

    public void setMensajeInterno(String mensajeInterno) {
        this.mensajeInterno = mensajeInterno;
    }
    
    public Object getResultado() {
        return resultado;
    }

    public void setResultado(Object resultado) {
        this.resultado = resultado;
    }
}
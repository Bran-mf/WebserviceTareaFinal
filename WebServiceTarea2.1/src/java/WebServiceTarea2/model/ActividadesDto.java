/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceTarea2.model;

import WebServiceTarea2.util.LocalDateAdapter;
import java.time.ZoneId;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Jose Pablo Bermudez
 */
@XmlRootElement(name = "ActividadesDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ActividadesDto {
    private Long id;
    private String descripcion;
    private String encargado;
    private String estado;
    private String inicioEsperado;
    private String finalEsperado;
    private Integer orden;
    private Long version;
    private ProyectoDto proyecto;
    private String finalReal;
    private String inicioReal;
    //metodos
    //get and set

    
    public ActividadesDto(Long id, String descripcion, String estado, String inicioEsperado, String finalEsperado, Integer orden, Long version) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.inicioEsperado = inicioEsperado;
        this.finalEsperado = finalEsperado;
        this.orden = orden;
        this.version = version;
    }
    //constructores
    public ActividadesDto() {
    }
    public ActividadesDto(Actividades actividades){
        this.id =  actividades.getActId();
        this.descripcion = actividades.getActDescripcion();
        this.estado = actividades.getActEstado();
        this.inicioEsperado = actividades.getActInicioesperado().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        this.finalEsperado = actividades.getActFinalesperado().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        this.finalReal = actividades.getActFinalreal().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        this.inicioReal = actividades.getActInicioreal().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        this.orden = actividades.getActOrden();
        this.version = actividades.getActVersion();
        this.encargado = actividades.getActEncargado();
        //this.proyecto = new ProyectoDto(actividades.getPryId());
    }
    /*public Actividades getModel(){
        return new Actividades(id, descripcion, estado, estado, inicioEsperado, finalEsperado, orden, version);
    }*/

    public ProyectoDto getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoDto proyecto) {
        this.proyecto = proyecto;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
     public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getInicioEsperado() {
        return inicioEsperado;
    }

    public void setInicioEsperado(String inicioEsperado) {
        this.inicioEsperado = inicioEsperado;
    }

    public String getFinalEsperado() {
        return finalEsperado;
    }

    public void setFinalEsperado(String finalEsperado) {
        this.finalEsperado = finalEsperado;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getFinalReal() {
        return finalReal;
    }

    public void setFinalReal(String finalReal) {
        this.finalReal = finalReal;
    }

    public String getInicioReal() {
        return inicioReal;
    }

    public void setInicioReal(String inicioReal) {
        this.inicioReal = inicioReal;
    }
    

    
    
}

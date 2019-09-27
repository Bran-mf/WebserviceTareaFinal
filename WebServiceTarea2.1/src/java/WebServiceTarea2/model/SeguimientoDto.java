/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceTarea2.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose Pablo Bermudez
 */
@XmlRootElement(name = "SeguimientoDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class SeguimientoDto {
    private Long id;
    private Date fecha; //dia en que se hizo el seguimiento
    private Integer avance; //% del avance
    private Long version;
    ProyectoDto proyecto;

    //constructores

    public SeguimientoDto() {
    }

    public SeguimientoDto(Long id, Date fecha, Integer avance, Long version, ProyectoDto proyecto) {
        this.id = id;
        this.fecha = fecha;
        this.avance = avance;
        this.version = version;
        this.proyecto = proyecto;
    }
    public SeguimientoDto(Seguimiento seguimiento){
        this.id=seguimiento.getSegId();
        this.fecha = seguimiento.getSegFecha();
        this.avance= seguimiento.getSegAvance();
        this.version= seguimiento.getSegVersion();
        //this.proyecto=  new ProyectoDto(seguimiento.getPryId());
    }
    public Seguimiento getModel(){
        return new Seguimiento(id, fecha, avance, version);
    }
    //metodos
    //get and set
    public Long getId() {
        return id;
    }

    public void setSegId(Long id) {
        this.id = id;
    }

    public Date getSegFecha() {
        return fecha;
    }

    public void setSegFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getSegAvance() {
        return avance;
    }

    public void setSegAvance(Integer avance) {
        this.avance = avance;
    }

    public Long getSegVersion() {
        return version;
    }

    public void setsegVersion(Long version) {
        this.version = version;
    }

    public ProyectoDto getSegProyecto() {
        return proyecto;
    }

    public void setSegProyecto(ProyectoDto proyecto) {
        this.proyecto = proyecto;
    }
    
    
}

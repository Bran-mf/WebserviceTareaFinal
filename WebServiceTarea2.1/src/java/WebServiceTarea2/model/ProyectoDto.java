/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceTarea2.model;

import WebServiceTarea2.util.LocalDateAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Jose Pablo Bermudez
 */
@XmlRootElement(name = "ProyectoDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ProyectoDto {
    private Long proId;
    private String proNombre;
    private String proPatrocinador;
    private String proLiderusuario;
    private String proLidertecnico;
    private String proCorreopatrocinador;
    private String proCorreousuario;
    private String proCorreotecnico;
    private String proFechainireal;
    private String proFechafinreal;
    private String proFechainicio;
    private String proFechafinal;
    private String proEstado;
    private AdministradorDto proAdmin;
    private Long proVersion;

     private List<ActividadesDto> actividades;
    private List<SeguimientoDto> seguimientos;
    
    public ProyectoDto() {
        actividades = new ArrayList<>();
        seguimientos = new ArrayList<>();
    }

    
    public ProyectoDto(Proyecto proyecto) {
        this.proCorreopatrocinador = proyecto.getProCorreopatrocinador();
        this.proCorreotecnico = proyecto.getProCorreotenico();
        this.proCorreousuario = proyecto.getProCorreousuario();
        this.proEstado = proyecto.getProEstado();
        this.proFechafinreal = proyecto.getProFinalreal().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        this.proFechainireal = proyecto.getProInicioreal().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        this.proFechainicio = proyecto.getProInicioesperado().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        this.proFechafinal = proyecto.getProFinalesperado().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        this.proId = proyecto.getProId();
        this.proLidertecnico = proyecto.getProLiderusuario();
        this.proLiderusuario = proyecto.getProLiderusuario();
        this.proNombre = proyecto.getProNombre();
        this.proPatrocinador = proyecto.getProPatrocinador();
        this.proVersion = proyecto.getProVersion();
        //this.proAdmin = proyecto.getProAdministrador();
        /*setActividadesFromDB(proyecto.getActividadesList());
        setSeguimientosFromDB(proyecto.getSeguimientoList());*/
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProNombre() {
        return proNombre;
    }

    public void setProNombre(String proNombre) {
        this.proNombre = proNombre;
    }

    public String getProPatrocinador() {
        return proPatrocinador;
    }

    public void setProPatrocinador(String proPatrocinador) {
        this.proPatrocinador = proPatrocinador;
    }

    public String getProLiderusuario() {
        return proLiderusuario;
    }

    public void setProLiderusuario(String proLiderusuario) {
        this.proLiderusuario = proLiderusuario;
    }

    public String getProLidertecnico() {
        return proLidertecnico;
    }

    public void setProLidertecnico(String proLidertecnico) {
        this.proLidertecnico = proLidertecnico;
    }

    public String getProCorreopatrocinador() {
        return proCorreopatrocinador;
    }

    public void setProCorreopatrocinador(String proCorreopatrocinador) {
        this.proCorreopatrocinador = proCorreopatrocinador;
    }

    public String getProCorreousuario() {
        return proCorreousuario;
    }

    public void setProCorreousuario(String proCorreousuario) {
        this.proCorreousuario = proCorreousuario;
    }

    public String getProCorreotecnico() {
        return proCorreotecnico;
    }

    public void setProCorreotecnico(String proCorreotecnico) {
        this.proCorreotecnico = proCorreotecnico;
    }

    public String getProFechainireal() {
        return proFechainireal;
    }

    public void setProFechainireal(String proFechainireal) {
        this.proFechainireal = proFechainireal;
    }

    public String getProFechafinreal() {
        return proFechafinreal;
    }

    public void setProFechafinreal(String proFechafinreal) {
        this.proFechafinreal = proFechafinreal;
    }

    public String getProEstado() {
        return proEstado;
    }

    public void setProEstado(String proEstado) {
        this.proEstado = proEstado;
    }

    public String getProFechainicio() {
        return proFechainicio;
    }

    public void setProFechainicio(String proFechainicio) {
        this.proFechainicio = proFechainicio;
    }

    public String getProFechafinal() {
        return proFechafinal;
    }

    public void setProFechafinal(String proFechafinal) {
        this.proFechafinal = proFechafinal;
    }
    
    public Long getProVersion() {
        return proVersion;
    }

    public void setProVersion(Long proVersion) {
        this.proVersion = proVersion;
    }

    public AdministradorDto getProAdmin() {
        return proAdmin;
    }

    public void setProAdmin(AdministradorDto proAdmin) {
        this.proAdmin = proAdmin;
    }
    
    public List<ActividadesDto> getActividades() {
        return actividades;
    }
    
    public List<Actividades> getActividadesToDB(){
        List<Actividades> acts = new ArrayList<>();
        for (ActividadesDto a : this.actividades){
            Actividades act = new Actividades(a);
            acts.add(act);
        }
        return acts;
    }

    public void setActividades(List<ActividadesDto> actividades) {
        this.actividades = actividades;
    }
    
    public void setActividadesFromDB(List<Actividades> actividades){
        if(actividades != null){
            for (Actividades a : actividades){
                ActividadesDto act = new ActividadesDto(a);
                //act.setActProyecto(this);
                this.actividades.add(act);
            }
        }
    }

    public List<SeguimientoDto> getSeguimientos() {
        return seguimientos;
    }
    
    public List<Seguimiento> getSeguimientoToDB(){
        List<Seguimiento> segs = new ArrayList<>();
        for (SeguimientoDto s : this.seguimientos){
            Seguimiento se = new Seguimiento(s);
            segs.add(se);
        }
        return segs;
    }

    public void setSeguimientos(List<SeguimientoDto> seguimientos) {
        this.seguimientos = seguimientos;
    }
    
    public void setSeguimientosFromDB(List<Seguimiento> seguimientos){
        if(seguimientos != null){
            for (Seguimiento s : seguimientos){
                SeguimientoDto seg = new SeguimientoDto(s);
                //seg.setSegProyecto(this);
                this.seguimientos.add(seg);
            }
        }
    }
    
}

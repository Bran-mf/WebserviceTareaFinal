/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceTarea2.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jose Pablo Bermudez
 */
@Entity
@Table(name = "TBL_PROYECTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
    , @NamedQuery(name = "Proyecto.findByProId", query = "SELECT p FROM Proyecto p WHERE p.proId = :proId")
    , @NamedQuery(name = "Proyecto.findByProNombre", query = "SELECT p FROM Proyecto p WHERE p.proNombre = :proNombre")
    , @NamedQuery(name = "Proyecto.findByProPatrocinador", query = "SELECT p FROM Proyecto p WHERE p.proPatrocinador = :proPatrocinador")
    , @NamedQuery(name = "Proyecto.findByProLiderusuario", query = "SELECT p FROM Proyecto p WHERE p.proLiderusuario = :proLiderusuario")
    , @NamedQuery(name = "Proyecto.findByProLidertecnico", query = "SELECT p FROM Proyecto p WHERE p.proLidertecnico = :proLidertecnico")
    , @NamedQuery(name = "Proyecto.findByProInicioesperado", query = "SELECT p FROM Proyecto p WHERE p.proInicioesperado = :proInicioesperado")
    , @NamedQuery(name = "Proyecto.findByProInicioreal", query = "SELECT p FROM Proyecto p WHERE p.proInicioreal = :proInicioreal")
    , @NamedQuery(name = "Proyecto.findByProFinalesperado", query = "SELECT p FROM Proyecto p WHERE p.proFinalesperado = :proFinalesperado")
    , @NamedQuery(name = "Proyecto.findByProFinalreal", query = "SELECT p FROM Proyecto p WHERE p.proFinalreal = :proFinalreal")
    , @NamedQuery(name = "Proyecto.findByProEstado", query = "SELECT p FROM Proyecto p WHERE p.proEstado = :proEstado")
    , @NamedQuery(name = "Proyecto.findByProCorreousuario", query = "SELECT p FROM Proyecto p WHERE p.proCorreousuario = :proCorreousuario")
    , @NamedQuery(name = "Proyecto.findByProCorreotenico", query = "SELECT p FROM Proyecto p WHERE p.proCorreotenico = :proCorreotenico")
    , @NamedQuery(name = "Proyecto.findByProCorreopatrocinador", query = "SELECT p FROM Proyecto p WHERE p.proCorreopatrocinador = :proCorreopatrocinador")
    , @NamedQuery(name = "Proyecto.findByProVersion", query = "SELECT p FROM Proyecto p WHERE p.proVersion = :proVersion")})
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PRO_ID")
    private Long proId;
    @Basic(optional = false)
    @Column(name = "PRO_NOMBRE")
    private String proNombre;
    @Basic(optional = false)
    @Column(name = "PRO_PATROCINADOR")
    private String proPatrocinador;
    @Basic(optional = false)
    @Column(name = "PRO_LIDERUSUARIO")
    private String proLiderusuario;
    @Basic(optional = false)
    @Column(name = "PRO_LIDERTECNICO")
    private String proLidertecnico;
    @Basic(optional = false)
    @Column(name = "PRO_INICIOESPERADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proInicioesperado;
    @Column(name = "PRO_INICIOREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proInicioreal;
    @Basic(optional = false)
    @Column(name = "PRO_FINALESPERADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proFinalesperado;
    @Column(name = "PRO_FINALREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proFinalreal;
    @Basic(optional = false)
    @Column(name = "PRO_ESTADO")
    private String proEstado;
    @Basic(optional = false)
    @Column(name = "PRO_CORREOUSUARIO")
    private String proCorreousuario;
    @Basic(optional = false)
    @Column(name = "PRO_CORREOTENICO")
    private String proCorreotenico;
    @Basic(optional = false)
    @Column(name = "PRO_CORREOPATROCINADOR")
    private String proCorreopatrocinador;
    @Basic(optional = false)
    @Column(name = "PRO_VERSION")
    private Long proVersion;
    @JoinColumn(name = "ADN_ID", referencedColumnName = "ADN_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Administrador adnId;
    @OneToMany(mappedBy = "segPro", fetch = FetchType.LAZY)
    private List<Actividades> actividadesList;
    @OneToMany(mappedBy = "actPro", fetch = FetchType.LAZY)
    private List<Seguimiento> seguimientoList;

        public Proyecto() {
    }

    public Proyecto(Long proId) {
        this.proId = proId;
    }

    public Proyecto(Long proId, String proNombre, String proPatrocinador, String proLiderusuario, String ProLidertecnico, Date ProInicioesperado, Date proInicioreal, Date proFinalesperado, Date proFinalreal, String proEstado, String proCorreousuario, String proCorreotenico, String proCorreopatrocinador, Long proVersion/*, List<Actividades> actividadesList, List<Seguimiento> seguimientoList*/) {
        this.proId = proId;
        this.proNombre = proNombre;
        this.proPatrocinador = proPatrocinador;
        this.proLiderusuario = proLiderusuario;
        this.proLidertecnico = ProLidertecnico;
        this.proInicioesperado = ProInicioesperado;
        this.proInicioreal = proInicioreal;
        this.proFinalesperado = proFinalesperado;
        this.proFinalreal = proFinalreal;
        this.proEstado = proEstado;
        this.proCorreousuario = proCorreousuario;
        this.proCorreotenico = proCorreotenico;
        this.proCorreopatrocinador = proCorreopatrocinador;
        this.proVersion = proVersion;
        /*this.actividadesList = actividadesList;
        this.seguimientoList = seguimientoList;*/
    }

    

    public void actualizarProyecto(ProyectoDto proyectoDto){
    
        this.proCorreopatrocinador = proyectoDto.getProCorreopatrocinador();
        this.proCorreotenico = proyectoDto.getProCorreotecnico();
        this.proCorreousuario = proyectoDto.getProCorreousuario();
        this.proEstado = proyectoDto.getProEstado();
        
        LocalDate fechFinal = LocalDate.parse(proyectoDto.getProFechafinal(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fechFinalReal = LocalDate.parse(proyectoDto.getProFechafinreal(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fechIni = LocalDate.parse(proyectoDto.getProFechainicio(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fechIniReal = LocalDate.parse(proyectoDto.getProFechainireal(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        this.proFinalreal = Date.from(fechFinalReal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.proInicioreal =  Date.from(fechIniReal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.proInicioesperado = Date.from(fechFinal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.proInicioesperado = Date.from(fechIni.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        this.proId = proyectoDto.getProId();
        this.proLiderusuario = proyectoDto.getProLiderusuario();
        this.proNombre = proyectoDto.getProNombre();
        this.proPatrocinador = proyectoDto.getProPatrocinador();
        this.proVersion = proyectoDto.getProVersion();
        this.proLidertecnico = proyectoDto.getProLidertecnico();
        //this.adnId = new Administrador(proyectoDto.getProAdmin());
    }
    public Proyecto( ProyectoDto proyectoDto ){
    
        this.proId = proyectoDto.getProId();
        actualizarProyecto(proyectoDto);
        
    }

     @XmlTransient
    public List<Actividades> getActividadesList() {
        return actividadesList;
    }

    public void setActividadesList(List<Actividades> actividadesList) {
        this.actividadesList = actividadesList;
    }
    @XmlTransient
    public List<Seguimiento> getSeguimientoList() {
        return seguimientoList;
    }

    public void setSeguimientoList(List<Seguimiento> seguimientoList) {
        this.seguimientoList = seguimientoList;
    }
    
    public Administrador getProAdministrador() {
        return adnId;
    }

    public void setProAdministrador(Administrador proAdministrador) {
        this.adnId = proAdministrador;
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

    public void setProLidertecnico(String ProLidertecnico) {
        this.proLidertecnico = ProLidertecnico;
    }

    public Date getProInicioesperado() {
        return proInicioesperado;
    }

    public void setProInicioesperado(Date ProInicioesperado) {
        this.proInicioesperado = ProInicioesperado;
    }

    public Date getProInicioreal() {
        return proInicioreal;
    }

    public void setProInicioreal(Date proInicioreal) {
        this.proInicioreal = proInicioreal;
    }

    public Date getProFinalesperado() {
        return proFinalesperado;
    }

    public void setProFinalesperado(Date proFinalesperado) {
        this.proFinalesperado = proFinalesperado;
    }

    public Date getProFinalreal() {
        return proFinalreal;
    }

    public void setProFinalreal(Date proFinalreal) {
        this.proFinalreal = proFinalreal;
    }

    public String getProEstado() {
        return proEstado;
    }

    public void setProEstado(String proEstado) {
        this.proEstado = proEstado;
    }

    public String getProCorreousuario() {
        return proCorreousuario;
    }

    public void setProCorreousuario(String proCorreousuario) {
        this.proCorreousuario = proCorreousuario;
    }

    public String getProCorreotenico() {
        return proCorreotenico;
    }

    public void setProCorreotenico(String proCorreotenico) {
        this.proCorreotenico = proCorreotenico;
    }

    public String getProCorreopatrocinador() {
        return proCorreopatrocinador;
    }

    public void setProCorreopatrocinador(String proCorreopatrocinador) {
        this.proCorreopatrocinador = proCorreopatrocinador;
    }

    public Long getProVersion() {
        return proVersion;
    }

    public void setProVersion(Long proVersion) {
        this.proVersion = proVersion;
    }

    /*@XmlTransient
    public List<Actividades> getActividadesList() {
        return actividadesList;
    }

    public void setActividadesList(List<Actividades> actividadesList) {
        this.actividadesList = actividadesList;
    }

    @XmlTransient
    public List<Seguimiento> getSeguimientoList() {
        return seguimientoList;
    }

    public void setSeguimientoList(List<Seguimiento> seguimientoList) {
        this.seguimientoList = seguimientoList;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proId != null ? proId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.proId == null && other.proId != null) || (this.proId != null && !this.proId.equals(other.proId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WebServiceTarea2.model.Proyecto[ proId=" + proId + " ]";
    }


    public Administrador getAdnId() {
        return adnId;
    }

    public void setAdnId(Administrador adnId) {
        this.adnId = adnId;
    }

    /*@XmlTransient
    public List<Actividades> getActividadesList() {
        return actividadesList;
    }

    public void setActividadesList(List<Actividades> actividadesList) {
        this.actividadesList = actividadesList;
    }

    @XmlTransient
    public List<Seguimiento> getSeguimientoList() {
        return seguimientoList;
    }

    public void setSeguimientoList(List<Seguimiento> seguimientoList) {
        this.seguimientoList = seguimientoList;
    }*/
    
}

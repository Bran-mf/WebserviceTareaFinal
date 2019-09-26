
package WebServiceTarea2.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose Pablo Bermudez
 */
@Entity
@Table(name = "TBL_ACTIVIDADES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a")
    , @NamedQuery(name = "Actividades.findByActId", query = "SELECT a FROM Actividades a WHERE a.actId = :actId")
    , @NamedQuery(name = "Actividades.findByActDescripcion", query = "SELECT a FROM Actividades a WHERE a.actDescripcion = :actDescripcion")
    , @NamedQuery(name = "Actividades.findByActEncargado", query = "SELECT a FROM Actividades a WHERE a.actEncargado = :actEncargado")
    , @NamedQuery(name = "Actividades.findByActEstado", query = "SELECT a FROM Actividades a WHERE a.actEstado = :actEstado")
    , @NamedQuery(name = "Actividades.findByActInicioreal", query = "SELECT a FROM Actividades a WHERE a.actInicioreal = :actInicioreal")
    , @NamedQuery(name = "Actividades.findByActInicioesperado", query = "SELECT a FROM Actividades a WHERE a.actInicioesperado = :actInicioesperado")
    , @NamedQuery(name = "Actividades.findByActFinalreal", query = "SELECT a FROM Actividades a WHERE a.actFinalreal = :actFinalreal")
    , @NamedQuery(name = "Actividades.findByActFinalesperado", query = "SELECT a FROM Actividades a WHERE a.actFinalesperado = :actFinalesperado")
    , @NamedQuery(name = "Actividades.findByActOrden", query = "SELECT a FROM Actividades a WHERE a.actOrden = :actOrden")
    , @NamedQuery(name = "Actividades.findByActVersion", query = "SELECT a FROM Actividades a WHERE a.actVersion = :actVersion")})
public class Actividades implements Serializable {

    
    @Basic(optional = false)
    @Column(name = "ACT_ORDEN")
    private Integer actOrden;
    @Basic(optional = false)
    @Column(name = "ACT_VERSION")
    private Long actVersion;
    @JoinColumn(name = "SEG_PRO", referencedColumnName = "PRO_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Proyecto segPro;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "ACT_ID_GENERATOR", sequenceName = "SEQ_ACTIVIDADES", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACT_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ACT_ID")
    private Long actId;
    @Basic(optional = false)
    @Column(name = "ACT_DESCRIPCION")
    private String actDescripcion;
    @Basic(optional = false)
    @Column(name = "ACT_ENCARGADO")
    private String actEncargado;
    @Basic(optional = false)
    @Column(name = "ACT_ESTADO")
    private String actEstado;
    @Column(name = "ACT_INICIOREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actInicioreal;
    @Basic(optional = false)
    @Column(name = "ACT_INICIOESPERADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actInicioesperado;
    @Column(name = "ACT_FINALREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actFinalreal;
    @Basic(optional = false)
    @Column(name = "ACT_FINALESPERADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actFinalesperado;

    public Actividades() {
    }

    public Actividades(Long actId) {
        this.actId = actId;
    }

    public Actividades(Long actId, String actDescripcion, String actEncargado, String actEstado, Date actInicioesperado, Date actFinalesperado, Integer actOrden, Long actVersion) {
        this.actId = actId;
        this.actDescripcion = actDescripcion;
        this.actEncargado = actEncargado;
        this.actEstado = actEstado;
        this.actInicioesperado = actInicioesperado;
        this.actFinalesperado = actFinalesperado;
        this.actOrden = actOrden;
        this.actVersion = actVersion;//
    }

    public Actividades(ActividadesDto act){
        this.actId = act.getId();
        actualizarActividades(act);
    }
    
    public void actualizarActividades(ActividadesDto act){
        this.actDescripcion = act.getDescripcion();
        this.actEncargado = act.getEncargado();
        this.actEstado = act.getEstado();
        LocalDate fechFinal = LocalDate.parse(act.getFinalEsperado(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate fechFinalReal = LocalDate.parse(act.getFinalReal(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate fechIni = LocalDate.parse(act.getInicioEsperado(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate fechIniReal = LocalDate.parse(act.getInicioReal(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        
        this.actFinalesperado = Date.from(fechFinal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.actFinalreal = Date.from(fechFinalReal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.actInicioesperado = Date.from(fechIni.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.actInicioreal =Date.from(fechIniReal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.actId = act.getId();
        this.actOrden = act.getOrden();
        this.actVersion = act.getVersion();
       // this.pryId = new Proyecto(act.getProyecto());
    }
    
    
    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public String getActDescripcion() {
        return actDescripcion;
    }

    public void setActDescripcion(String actDescripcion) {
        this.actDescripcion = actDescripcion;
    }

    public String getActEncargado() {
        return actEncargado;
    }

    public void setActEncargado(String actEncargado) {
        this.actEncargado = actEncargado;
    }

    public String getActEstado() {
        return actEstado;
    }

    public void setActEstado(String actEstado) {
        this.actEstado = actEstado;
    }

    public Date getActInicioreal() {
        return actInicioreal;
    }

    public void setActInicioreal(Date actInicioreal) {
        this.actInicioreal = actInicioreal;
    }

    public Date getActInicioesperado() {
        return actInicioesperado;
    }

    public void setActInicioesperado(Date actInicioesperado) {
        this.actInicioesperado = actInicioesperado;
    }

    public Date getActFinalreal() {
        return actFinalreal;
    }

    public void setActFinalreal(Date actFinalreal) {
        this.actFinalreal = actFinalreal;
    }

    public Date getActFinalesperado() {
        return actFinalesperado;
    }

    public void setActFinalesperado(Date actFinalesperado) {
        this.actFinalesperado = actFinalesperado;
    }

    public Integer getActOrden() {
        return actOrden;
    }

    public void setActOrden(Integer actOrden) {
        this.actOrden = actOrden;
    }

    public Long getActVersion() {
        return actVersion;
    }

    public void setActVersion(Long actVersion) {
        this.actVersion = actVersion;
    }

    /*public Proyecto getPryId() {
        return pryId;
    }

    public void setPryId(Proyecto pryId) {
        this.pryId = pryId;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actId != null ? actId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if ((this.actId == null && other.actId != null) || (this.actId != null && !this.actId.equals(other.actId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WebServiceTarea2.model.Actividades[ actId=" + actId + " ]";
    }
    public Proyecto getSegPro() {
        return segPro;
    }

    public void setSegPro(Proyecto segPro) {
        this.segPro = segPro;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceTarea2.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jose Pablo Bermudez
 */
@Entity
@Table(name = "TBL_ADMINISTRADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a")
    , @NamedQuery(name = "Administrador.findByAdnId", query = "SELECT a FROM Administrador a WHERE a.adnId = :adnId")
    , @NamedQuery(name = "Administrador.findByAdnNombre", query = "SELECT a FROM Administrador a WHERE a.adnNombre = :AdnNombre")
    , @NamedQuery(name = "Administrador.findByAdnPapellido", query = "SELECT a FROM Administrador a WHERE a.adnPapellido = :adnPapellido")
    , @NamedQuery(name = "Administrador.findByAdnSapellido", query = "SELECT a FROM Administrador a WHERE a.adnSapellido = :adnSapellido")
    , @NamedQuery(name = "Administrador.findByAdnCorreo", query = "SELECT a FROM Administrador a WHERE a.adnCorreo = :adnCorreo")
    , @NamedQuery(name = "Administrador.findByAdnUsuario", query = "SELECT a FROM Administrador a WHERE a.adnUsuario = :AdnUsuario")
    , @NamedQuery(name = "Administrador.findByAdnContrasena", query = "SELECT a FROM Administrador a WHERE a.adnContrasena = :adnContrasena")
    , @NamedQuery(name = "Administrador.findByAdnEstado", query = "SELECT a FROM Administrador a WHERE a.adnEstado = :AdnEstado")
    , @NamedQuery(name = "Administrador.findByAdnVersion", query = "SELECT a FROM Administrador a WHERE a.adnVersion = :AdnVersion")
    , @NamedQuery(name = "Administrador.findByAdnCedula", query = "SELECT a FROM Administrador a WHERE a.adnCedula = :AdnCedula")
    , @NamedQuery(name = "Administrador.findByUsuClave", query = "SELECT a FROM Administrador a WHERE a.adnUsuario = :adnUsuario and a.adnContrasena = :adnContrasena", hints = @QueryHint(name = "eclipselink.refresh", value = "true"))})
public class Administrador implements Serializable {

    @Basic(optional = false)
    @Column(name = "ADN_VERSION")
    private Long adnVersion;
    @OneToMany(mappedBy = "adnId", fetch = FetchType.LAZY)
    private List<Proyecto> proyectoList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "ADM_ID_GENERATOR", sequenceName = "SEQ_ADMINISTRADOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ADN_ID")
    private Long adnId;
    @Basic(optional = false)
    @Column(name = "ADN_NOMBRE")
    private String adnNombre;
    @Basic(optional = false)
    @Column(name = "ADN_CEDULA")
    private String adnCedula;
    @Basic(optional = false)
    @Column(name = "ADN_PAPELLIDO")
    private String adnPapellido;
    @Basic(optional = false)
    @Column(name = "ADN_SAPELLIDO")
    private String adnSapellido;
    @Basic(optional = false)
    @Column(name = "ADN_CORREO")
    private String adnCorreo;
    @Basic(optional = false)
    @Column(name = "ADN_USUARIO")
    private String adnUsuario;
    @Basic(optional = false)
    @Column(name = "ADN_CONTRASENA")
    private String adnContrasena;
    @Basic(optional = false)
    @Column(name = "ADN_ESTADO")
    private String adnEstado;

    public Administrador() {
    }

    public Administrador(Long admId) {
        this.adnId = admId;
    }

    public Administrador(Long admId, String AdmNombre, String admPapellido, String admSapellido, String cedula,String admCorreo, String AdmUsuario, String AdmClave, String AdmEstado, Long AdmVersion) {
        this.adnId = admId;
        this.adnNombre = AdmNombre;
        this.adnPapellido = admPapellido;
        this.adnSapellido = admSapellido;
        this.adnCedula = cedula;
        this.adnCorreo = admCorreo;
        this.adnUsuario = AdmUsuario;
        this.adnContrasena = AdmClave;
        this.adnEstado = AdmEstado;
        this.adnVersion = AdmVersion;
        
    }
    public Administrador(AdministradorDto AdministradorDto) {
        this.adnId = AdministradorDto.getAdnId();
        actualizarAdministrador(AdministradorDto);
    }

    public void actualizarAdministrador(AdministradorDto adm){
        this.adnNombre = adm.getAdnNombre();
        this.adnPapellido = adm.getAdnPapellido();
        this.adnSapellido = adm.getAdnSapellido();
        this.adnCedula = adm.getAdnCedula();
        this.adnCorreo = adm.getAdnCorreo();
        this.adnUsuario = adm.getAdnUsuario();
        this.adnContrasena = adm.getAdnContrasena();
        this.adnEstado = adm.getAdnEstado();
        this.adnVersion = adm.getAdnVersion();
        
    }

    public Long getAdnId() {
        return adnId;
    }

    public void setAdnId(Long admId) {
        this.adnId = admId;
    }

    public String getAdnNombre() {
        return adnNombre;
    }

    public String getAdnCedula() {
        return adnCedula;
    }

    public void setAdnCedula(String admCedula) {
        this.adnCedula = admCedula;
    }
    
    public void setAdnNombre(String AdmNombre) {
        this.adnNombre = AdmNombre;
    }

    public String getAdnPapellido() {
        return adnPapellido;
    }

    public void setAdnPapellido(String admPapellido) {
        this.adnPapellido = admPapellido;
    }

    public String getAdnSapellido() {
        return adnSapellido;
    }

    public void setAdnSapellido(String admSapellido) {
        this.adnSapellido = admSapellido;
    }

    public String getAdnCorreo() {
        return adnCorreo;
    }

    public void setAdnCorreo(String admCorreo) {
        this.adnCorreo = admCorreo;
    }

    public String getAdnUsuario() {
        return adnUsuario;
    }

    public void setAdnUsuario(String AdmUsuario) {
        this.adnUsuario = AdmUsuario;
    }

    public String getAdnContrasena() {
        return adnContrasena;
    }

    public void setAdnContrasena(String AdmClave) {
        this.adnContrasena = AdmClave;
    }

    public String getAdnEstado() {
        return adnEstado;
    }

    public void setAdnEstado(String AdmEstado) {
        this.adnEstado = AdmEstado;
    }

    public Long getAdnVersion() {
        return adnVersion;
    }

    public void setAdnVersion(Long AdmVersion) {
        this.adnVersion = AdmVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adnId != null ? adnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.adnId == null && other.adnId != null) || (this.adnId != null && !this.adnId.equals(other.adnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WebServiceTarea2.model.Administrador[ admId=" + adnId + " ]";
    }

    @XmlTransient
    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

}

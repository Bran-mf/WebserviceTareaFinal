/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceTarea2.model;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "TBL_SEGUIMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seguimiento.findAll", query = "SELECT s FROM Seguimiento s")
    , @NamedQuery(name = "Seguimiento.findBySegId", query = "SELECT s FROM Seguimiento s WHERE s.segId = :segId")
    , @NamedQuery(name = "Seguimiento.findBySegFecha", query = "SELECT s FROM Seguimiento s WHERE s.segFecha = :segFecha")
    , @NamedQuery(name = "Seguimiento.findBySegAvance", query = "SELECT s FROM Seguimiento s WHERE s.segAvance = :segAvance")
    , @NamedQuery(name = "Seguimiento.findBySegVersion", query = "SELECT s FROM Seguimiento s WHERE s.segVersion = :segVersion")})
public class Seguimiento implements Serializable {

    @Basic(optional = false)
    @Column(name = "SEG_AVANCE")
    private Integer segAvance;
    @Basic(optional = false)
    @Column(name = "SEG_VERSION")
    private Long segVersion;
    @JoinColumn(name = "ACT_PRO", referencedColumnName = "PRO_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Proyecto actPro;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "SEG_ID_GENERATOR", sequenceName = "SEQ_SEGUIMIENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEG_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "SEG_ID")
    private Long segId;
    @Basic(optional = false)
    @Column(name = "SEG_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date segFecha;

    public Seguimiento() {
    }

    public Seguimiento(Long segId) {
        this.segId = segId;
    }

    public Seguimiento(Long segId, Date segFecha, Integer segAvance, Long segVersion) {
        this.segId = segId;
        this.segFecha = segFecha;
        this.segAvance = segAvance;
        this.segVersion = segVersion;
    }

    public void actualizarSeguimiento(SeguimientoDto seg){
        this.segAvance = seg.getAvance();
        this.segFecha = seg.getFecha();
        this.segId  = seg.getId();
        this.segVersion = seg.getVersion();
        //this.actPro = new Proyecto(seg.getProyecto());
        
    }
    public Seguimiento(SeguimientoDto seg){
        this.segId = seg.getId();
        actualizarSeguimiento(seg);
    }
    public Long getSegId() {
        return segId;
    }

    public void setSegId(Long segId) {
        this.segId = segId;
    }

    public Date getSegFecha() {
        return segFecha;
    }

    public void setSegFecha(Date segFecha) {
        this.segFecha = segFecha;
    }

    public Integer getSegAvance() {
        return segAvance;
    }

    public void setSegAvance(Integer segAvance) {
        this.segAvance = segAvance;
    }

    public Long getSegVersion() {
        return segVersion;
    }

    public void setSegVersion(Long segVersion) {
        this.segVersion = segVersion;
    }

    /*public Proyecto getPryId() {
        return actPro;
    }

    public void setPryId(Proyecto pryId) {
        this.actPro = pryId;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (segId != null ? segId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguimiento)) {
            return false;
        }
        Seguimiento other = (Seguimiento) object;
        if ((this.segId == null && other.segId != null) || (this.segId != null && !this.segId.equals(other.segId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WebServiceTarea2.model.Seguimiento[ segId=" + segId + " ]";
    }

   
    public Proyecto getActPro() {
        return actPro;
    }

    public void setActPro(Proyecto actPro) {
        this.actPro = actPro;
    }
    
}

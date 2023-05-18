/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author veneg
 */
@Entity
@Table(name = "nota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nota.findAll", query = "SELECT n FROM Nota n"),
    @NamedQuery(name = "Nota.findByIdnota", query = "SELECT n FROM Nota n WHERE n.idnota = :idnota"),
    @NamedQuery(name = "Nota.findByFechanota", query = "SELECT n FROM Nota n WHERE n.fechanota = :fechanota"),
    @NamedQuery(name = "Nota.findByNArticulosDiferentes", query = "SELECT n FROM Nota n WHERE n.nArticulosDiferentes = :nArticulosDiferentes"),
    @NamedQuery(name = "Nota.findByImporte", query = "SELECT n FROM Nota n WHERE n.importe = :importe")})
public class Nota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnota")
    private Integer idnota;
    @Column(name = "fechanota")
    @Temporal(TemporalType.DATE)
    private Date fechanota;
    @Column(name = "nArticulosDiferentes")
    private Integer nArticulosDiferentes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private Double importe;
    @OneToMany(mappedBy = "folio")
    private List<Venta> ventaList;

    public Nota() {
    }

    public Nota(Integer idnota) {
        this.idnota = idnota;
    }

    public Integer getIdnota() {
        return idnota;
    }

    public void setIdnota(Integer idnota) {
        this.idnota = idnota;
    }

    public Date getFechanota() {
        return fechanota;
    }

    public void setFechanota(Date fechanota) {
        this.fechanota = fechanota;
    }

    public Integer getNArticulosDiferentes() {
        return nArticulosDiferentes;
    }

    public void setNArticulosDiferentes(Integer nArticulosDiferentes) {
        this.nArticulosDiferentes = nArticulosDiferentes;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnota != null ? idnota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nota)) {
            return false;
        }
        Nota other = (Nota) object;
        if ((this.idnota == null && other.idnota != null) || (this.idnota != null && !this.idnota.equals(other.idnota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Nota[ idnota=" + idnota + " ]";
    }
    
}

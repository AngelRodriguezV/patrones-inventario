/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package adms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import logicanegocio.LnNotas;
import modelo.Nota;

/**
 *
 * @author veneg
 */
@Named(value = "adNota")
@SessionScoped
public class AdNota implements Serializable {

    @EJB
    private LnNotas lnNotas;

    public List<Nota> getNotas() {
        return lnNotas.findNotas();
    }
    
    /**
     * Creates a new instance of Adnota
     */
    public AdNota() {
    }
    
}

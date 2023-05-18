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
import logicanegocio.LnVentas;
import modelo.Venta;

/**
 *
 * @author veneg
 */
@Named(value = "adVenta")
@SessionScoped
public class AdVenta implements Serializable {

   @EJB
   private LnVentas lnVentas;

    public AdVenta() {
    }

    public List<Venta> getVentas() {
        return lnVentas.findVentas();
    }
}

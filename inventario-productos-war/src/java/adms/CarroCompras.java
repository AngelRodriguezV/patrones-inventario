/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package adms;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import logicanegocio.LnNotas;
import logicanegocio.LnProductos;
import logicanegocio.LnVentas;
import modelo.Nota;
import modelo.Productos;
import modelo.Venta;

/**
 *
 * @author veneg
 */
@Named(value = "carroCompras")
@SessionScoped
public class CarroCompras implements Serializable {

    @EJB
    private LnVentas lnVentas;
    @EJB
    private LnProductos lnProductos;
    @EJB
    private LnNotas lnNotas;
    
    private Nota nota;
    private Productos producto;
    private Venta venta;
    private List<Venta> ventas;
    private double importe;
    
    public CarroCompras() {
        nota = new Nota();
        venta = new Venta();
        producto = new Productos();
        ventas = new ArrayList<Venta>();
        importe = 0;
    }
    
    public Nota getNota() {
        return nota;
    }
    
    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    public double getImporte() {
        importe = 0;
        for(Venta v : ventas) {
            importe += v.getImporte();
        }
        return importe;
    }
    
    public List<Venta> getVentas() {
        return ventas;
    }
    
    public boolean estaProductoCarro() {
        for(Venta v : ventas) {
            if (v.getIdProducto().getIdprod().equals(producto.getIdprod())) {
                return true;
            }
        }
        return false;
    }
    
    public Venta getProductoCarro() {
        Venta vv = new Venta();
        for(Venta v : ventas) {
            if (v.getIdProducto().getIdprod().equals(producto.getIdprod())) {
                vv = v;
            }
        }
        return vv;
    }
    
    public void agregarArticulo() {
        producto = lnProductos.findProducto(producto.getIdprod());
        if (estaProductoCarro()) {
            Venta v = getProductoCarro();
            v.setCantidad(v.getCantidad() + venta.getCantidad());
            v.setImporte(producto.getPrecio() * v.getCantidad());
        }
        else {
            venta.setImporte(producto.getPrecio() * venta.getCantidad());
            venta.setIdProducto(producto);
            ventas.add(venta);
        }
        venta = new Venta();
        producto = new Productos();
    }
    
    public void eliminarArticulo(Venta venta) {
        int n = -1;
        for(Venta v : ventas) {
            n++;
            if (v.getIdProducto().getIdprod().equals(venta.getIdProducto().getIdprod())) {
                break;
            }
        }
        ventas.remove(n);
    }
    
    public void comprarArticulos() {
        if (!ventas.isEmpty()) {
            nota.setFechanota(new Date());
            nota.setNArticulosDiferentes(ventas.size());
            nota.setImporte(importe);
            lnNotas.addNota(nota);
            for (Venta v : ventas) {
                v.setFolio(nota);
                lnVentas.addVenta(v);
                Productos pd = lnProductos.findProducto(v.getIdProducto().getIdprod());
                int existencia = pd.getExistencia() - v.getCantidad();
                pd.setExistencia(existencia);
                lnProductos.editProducto(pd);
            }
            nota = new Nota();
            ventas.clear();
        }
    }
}

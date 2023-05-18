/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package logicanegocio;

import accesodatos.NotasFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Nota;

/**
 *
 * @author veneg
 */
@Stateless
@LocalBean
public class LnNotas {

    @EJB
    private NotasFacade notasFacade;

    public void addNota(Nota n){
        notasFacade.create(n);
    }
    public Nota ultimaNota(){
        return notasFacade.getUltimanota();
    }
    
    public List<Nota> findNotas() {
        return notasFacade.findAll();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Nota;

/**
 *
 * @author veneg
 */
@Stateless
public class NotasFacade extends AbstractFacade<Nota> {

    @PersistenceContext(unitName = "inventario-productos-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Nota getUltimanota() {
        Query q = em.createQuery("SELECT n FROM Notas n ORDER BY n.idnota DESC");
        List<Nota> notas = q.getResultList();
        if (!notas.isEmpty()) {
            return notas.get(0);
        } else {
            return null;
        }

    }

    public NotasFacade() {
        super(Nota.class);
    }

}

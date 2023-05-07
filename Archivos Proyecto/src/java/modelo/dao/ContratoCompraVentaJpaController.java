/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.dao.exceptions.PreexistingEntityException;
import modelo.entidades.ContratoCompraVenta;

/**
 *
 * @author Zatonio
 */
public class ContratoCompraVentaJpaController implements Serializable {

    public ContratoCompraVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContratoCompraVenta contratoCompraVenta) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(contratoCompraVenta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContratoCompraVenta(contratoCompraVenta.getId_contrato_compra_venta()) != null) {
                throw new PreexistingEntityException("ContratoCompraVenta " + contratoCompraVenta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContratoCompraVenta contratoCompraVenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            contratoCompraVenta = em.merge(contratoCompraVenta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = contratoCompraVenta.getId_contrato_compra_venta();
                if (findContratoCompraVenta(id) == null) {
                    throw new NonexistentEntityException("The contratoCompraVenta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContratoCompraVenta contratoCompraVenta;
            try {
                contratoCompraVenta = em.getReference(ContratoCompraVenta.class, id);
                contratoCompraVenta.getId_contrato_compra_venta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contratoCompraVenta with id " + id + " no longer exists.", enfe);
            }
            em.remove(contratoCompraVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContratoCompraVenta> findContratoCompraVentaEntities() {
        return findContratoCompraVentaEntities(true, -1, -1);
    }

    public List<ContratoCompraVenta> findContratoCompraVentaEntities(int maxResults, int firstResult) {
        return findContratoCompraVentaEntities(false, maxResults, firstResult);
    }

    private List<ContratoCompraVenta> findContratoCompraVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContratoCompraVenta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ContratoCompraVenta findContratoCompraVenta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContratoCompraVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratoCompraVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContratoCompraVenta> rt = cq.from(ContratoCompraVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

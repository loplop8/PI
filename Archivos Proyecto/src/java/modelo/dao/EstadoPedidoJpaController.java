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
import modelo.entidades.EstadoPedido;

/**
 *
 * @author Zatonio
 */
public class EstadoPedidoJpaController implements Serializable {

    public EstadoPedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoPedido estadoPedido) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadoPedido);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoPedido(estadoPedido.getId_estado_pedido()) != null) {
                throw new PreexistingEntityException("EstadoPedido " + estadoPedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoPedido estadoPedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadoPedido = em.merge(estadoPedido);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoPedido.getId_estado_pedido();
                if (findEstadoPedido(id) == null) {
                    throw new NonexistentEntityException("The estadoPedido with id " + id + " no longer exists.");
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
            EstadoPedido estadoPedido;
            try {
                estadoPedido = em.getReference(EstadoPedido.class, id);
                estadoPedido.getId_estado_pedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoPedido with id " + id + " no longer exists.", enfe);
            }
            em.remove(estadoPedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoPedido> findEstadoPedidoEntities() {
        return findEstadoPedidoEntities(true, -1, -1);
    }

    public List<EstadoPedido> findEstadoPedidoEntities(int maxResults, int firstResult) {
        return findEstadoPedidoEntities(false, maxResults, firstResult);
    }

    private List<EstadoPedido> findEstadoPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoPedido.class));
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

    public EstadoPedido findEstadoPedido(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoPedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoPedido> rt = cq.from(EstadoPedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

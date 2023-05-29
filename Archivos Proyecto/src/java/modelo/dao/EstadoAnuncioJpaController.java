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
import modelo.entidades.EstadoAnuncio;

/**
 *
 * @author Zatonio
 */
public class EstadoAnuncioJpaController implements Serializable {

    public EstadoAnuncioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoAnuncio estadoAnuncio) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadoAnuncio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoAnuncio(estadoAnuncio.getId_estado_anuncio()) != null) {
                throw new PreexistingEntityException("EstadoAnuncio " + estadoAnuncio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoAnuncio estadoAnuncio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadoAnuncio = em.merge(estadoAnuncio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoAnuncio.getId_estado_anuncio();
                if (findEstadoAnuncio(id) == null) {
                    throw new NonexistentEntityException("The estadoAnuncio with id " + id + " no longer exists.");
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
            EstadoAnuncio estadoAnuncio;
            try {
                estadoAnuncio = em.getReference(EstadoAnuncio.class, id);
                estadoAnuncio.getId_estado_anuncio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoAnuncio with id " + id + " no longer exists.", enfe);
            }
            em.remove(estadoAnuncio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoAnuncio> findEstadoAnuncioEntities() {
        return findEstadoAnuncioEntities(true, -1, -1);
    }

    public List<EstadoAnuncio> findEstadoAnuncioEntities(int maxResults, int firstResult) {
        return findEstadoAnuncioEntities(false, maxResults, firstResult);
    }

    private List<EstadoAnuncio> findEstadoAnuncioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoAnuncio.class));
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

    public EstadoAnuncio findEstadoAnuncio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoAnuncio.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoAnuncioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoAnuncio> rt = cq.from(EstadoAnuncio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

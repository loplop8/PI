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
import modelo.entidades.Hilo;

/**
 *
 * @author Zatonio
 */
public class HiloJpaController implements Serializable {

    public HiloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hilo hilo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hilo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHilo(hilo.getId_hilo()) != null) {
                throw new PreexistingEntityException("Hilo " + hilo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hilo hilo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hilo = em.merge(hilo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = hilo.getId_hilo();
                if (findHilo(id) == null) {
                    throw new NonexistentEntityException("The hilo with id " + id + " no longer exists.");
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
            Hilo hilo;
            try {
                hilo = em.getReference(Hilo.class, id);
                hilo.getId_hilo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hilo with id " + id + " no longer exists.", enfe);
            }
            em.remove(hilo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Hilo> findHiloEntities() {
        return findHiloEntities(true, -1, -1);
    }

    public List<Hilo> findHiloEntities(int maxResults, int firstResult) {
        return findHiloEntities(false, maxResults, firstResult);
    }

    private List<Hilo> findHiloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Hilo.class));
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

    public Hilo findHilo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hilo.class, id);
        } finally {
            em.close();
        }
    }

    public int getHiloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Hilo> rt = cq.from(Hilo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

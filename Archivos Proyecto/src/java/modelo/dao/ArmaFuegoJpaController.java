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
import modelo.entidades.Arma;
import modelo.entidades.ArmaFuego;

/**
 *
 * @author Zatonio
 */
public class ArmaFuegoJpaController implements Serializable {

    public ArmaFuegoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ArmaFuego armaFuego) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(armaFuego);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArmaFuego(armaFuego.getId_arma()) != null) {
                throw new PreexistingEntityException("ArmaFuego " + armaFuego + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ArmaFuego armaFuego) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            armaFuego = em.merge(armaFuego);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Arma id = armaFuego.getId_arma();
                if (findArmaFuego(id) == null) {
                    throw new NonexistentEntityException("The armaFuego with id " + id + " no longer exists.");
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
            ArmaFuego armaFuego;
            try {
                armaFuego = em.getReference(ArmaFuego.class, id);
                armaFuego.getId_arma();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The armaFuego with id " + id + " no longer exists.", enfe);
            }
            em.remove(armaFuego);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Arma id) throws NonexistentEntityException {
        destroy(id.getId_arma());
    }

    public List<ArmaFuego> findArmaFuegoEntities() {
        return findArmaFuegoEntities(true, -1, -1);
    }

    public List<ArmaFuego> findArmaFuegoEntities(int maxResults, int firstResult) {
        return findArmaFuegoEntities(false, maxResults, firstResult);
    }

    private List<ArmaFuego> findArmaFuegoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ArmaFuego.class));
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

    public ArmaFuego findArmaFuego(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ArmaFuego.class, id);
        } finally {
            em.close();
        }
    }

    public ArmaFuego findArmaFuego(Arma id) {
        return findArmaFuego(id.getId_arma());
    }

    public int getArmaFuegoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ArmaFuego> rt = cq.from(ArmaFuego.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

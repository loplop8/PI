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
import modelo.entidades.ArmaReplica;

/**
 *
 * @author Zatonio
 */
public class ArmaReplicaJpaController implements Serializable {

    public ArmaReplicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ArmaReplica armaReplica) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(armaReplica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArmaReplica(armaReplica.getId_arma()) != null) {
                throw new PreexistingEntityException("ArmaReplica " + armaReplica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ArmaReplica armaReplica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            armaReplica = em.merge(armaReplica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Arma id = armaReplica.getId_arma();
                if (findArmaReplica(id) == null) {
                    throw new NonexistentEntityException("The armaReplica with id " + id + " no longer exists.");
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
            ArmaReplica armaReplica;
            try {
                armaReplica = em.getReference(ArmaReplica.class, id);
                armaReplica.getId_arma();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The armaReplica with id " + id + " no longer exists.", enfe);
            }
            em.remove(armaReplica);
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

    public List<ArmaReplica> findArmaReplicaEntities() {
        return findArmaReplicaEntities(true, -1, -1);
    }

    public List<ArmaReplica> findArmaReplicaEntities(int maxResults, int firstResult) {
        return findArmaReplicaEntities(false, maxResults, firstResult);
    }

    private List<ArmaReplica> findArmaReplicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ArmaReplica.class));
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

    public ArmaReplica findArmaReplica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ArmaReplica.class, id);
        } finally {
            em.close();
        }
    }

    public ArmaReplica findArmaReplica(Arma id) {
        return findArmaReplica(id.getId_arma());
    }

    public int getArmaReplicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ArmaReplica> rt = cq.from(ArmaReplica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

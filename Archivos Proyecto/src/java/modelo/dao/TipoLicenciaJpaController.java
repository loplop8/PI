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
import modelo.entidades.TipoLicencia;

/**
 *
 * @author Zatonio
 */
public class TipoLicenciaJpaController implements Serializable {

    public TipoLicenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoLicencia tipoLicencia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoLicencia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoLicencia(tipoLicencia.getId_tipo_licencia()) != null) {
                throw new PreexistingEntityException("TipoLicencia " + tipoLicencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoLicencia tipoLicencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoLicencia = em.merge(tipoLicencia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoLicencia.getId_tipo_licencia();
                if (findTipoLicencia(id) == null) {
                    throw new NonexistentEntityException("The tipoLicencia with id " + id + " no longer exists.");
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
            TipoLicencia tipoLicencia;
            try {
                tipoLicencia = em.getReference(TipoLicencia.class, id);
                tipoLicencia.getId_tipo_licencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoLicencia with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoLicencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoLicencia> findTipoLicenciaEntities() {
        return findTipoLicenciaEntities(true, -1, -1);
    }

    public List<TipoLicencia> findTipoLicenciaEntities(int maxResults, int firstResult) {
        return findTipoLicenciaEntities(false, maxResults, firstResult);
    }

    private List<TipoLicencia> findTipoLicenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoLicencia.class));
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

    public TipoLicencia findTipoLicencia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoLicencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoLicenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoLicencia> rt = cq.from(TipoLicencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

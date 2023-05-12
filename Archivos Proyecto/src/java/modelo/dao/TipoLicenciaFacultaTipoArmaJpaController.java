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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.dao.exceptions.PreexistingEntityException;
import modelo.entidades.TipoArma;
import modelo.entidades.TipoLicenciaFacultaTipoArma;

/**
 *
 * @author Zatonio
 */
public class TipoLicenciaFacultaTipoArmaJpaController implements Serializable {

    public TipoLicenciaFacultaTipoArmaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoLicenciaFacultaTipoArma tipoLicenciaFacultaTipoArma) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoLicenciaFacultaTipoArma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoLicenciaFacultaTipoArma(tipoLicenciaFacultaTipoArma.getIdTipoLicencia()) != null) {
                throw new PreexistingEntityException("TipoLicenciaFacultaTipoArma " + tipoLicenciaFacultaTipoArma + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoLicenciaFacultaTipoArma tipoLicenciaFacultaTipoArma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoLicenciaFacultaTipoArma = em.merge(tipoLicenciaFacultaTipoArma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoLicenciaFacultaTipoArma.getIdTipoLicencia();
                if (findTipoLicenciaFacultaTipoArma(id) == null) {
                    throw new NonexistentEntityException("The tipoLicenciaFacultaTipoArma with id " + id + " no longer exists.");
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
            TipoLicenciaFacultaTipoArma tipoLicenciaFacultaTipoArma;
            try {
                tipoLicenciaFacultaTipoArma = em.getReference(TipoLicenciaFacultaTipoArma.class, id);
                tipoLicenciaFacultaTipoArma.getIdTipoLicencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoLicenciaFacultaTipoArma with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoLicenciaFacultaTipoArma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoLicenciaFacultaTipoArma> findTipoLicenciaFacultaTipoArmaEntities() {
        return findTipoLicenciaFacultaTipoArmaEntities(true, -1, -1);
    }

    public List<TipoLicenciaFacultaTipoArma> findTipoLicenciaFacultaTipoArmaEntities(int maxResults, int firstResult) {
        return findTipoLicenciaFacultaTipoArmaEntities(false, maxResults, firstResult);
    }

    private List<TipoLicenciaFacultaTipoArma> findTipoLicenciaFacultaTipoArmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoLicenciaFacultaTipoArma.class));
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

    public TipoLicenciaFacultaTipoArma findTipoLicenciaFacultaTipoArma(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoLicenciaFacultaTipoArma.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoLicenciaFacultaTipoArmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoLicenciaFacultaTipoArma> rt = cq.from(TipoLicenciaFacultaTipoArma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   







    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
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

/**
 *
 * @author Zatonio
 */
public class TipoArmaJpaController implements Serializable {

    public TipoArmaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoArma tipoArma) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoArma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoArma(tipoArma.getId_tipo_arma()) != null) {
                throw new PreexistingEntityException("TipoArma " + tipoArma + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoArma tipoArma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoArma = em.merge(tipoArma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoArma.getId_tipo_arma();
                if (findTipoArma(id) == null) {
                    throw new NonexistentEntityException("The tipoArma with id " + id + " no longer exists.");
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
            TipoArma tipoArma;
            try {
                tipoArma = em.getReference(TipoArma.class, id);
                tipoArma.getId_tipo_arma();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoArma with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoArma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoArma> findTipoArmaEntities() {
        return findTipoArmaEntities(true, -1, -1);
    }

    public List<TipoArma> findTipoArmaEntities(int maxResults, int firstResult) {
        return findTipoArmaEntities(false, maxResults, firstResult);
    }

    private List<TipoArma> findTipoArmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoArma.class));
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

    public TipoArma findTipoArma(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoArma.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoArmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoArma> rt = cq.from(TipoArma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   public List<TipoArma> obtenerTiposArmasPorUsuario(Long userId) {
        EntityManager entityManager=getEntityManager();
       String queryString ="SELECT t.tipo, t.id_tipo_arma " +
    "FROM Usuario u, Licencia l, TipoLicencia tl, TipoLicenciaFacultaTipoArma f, TipoArma t " +
    "WHERE (u.id_usuario = l.id_usuario.id_usuario " +
    "AND l.id_tipo_licencia.id_tipo_licencia = tl.id_tipo_licencia " +
    "AND tl.id_tipo_licencia = f.idTipoLicencia " +
    "AND f.idTipoArma = t.id_tipo_arma " +               
    "AND u.id_usuario = :userId)";

        TypedQuery<Object[]> query = entityManager.createQuery(queryString, Object[].class)
                .setParameter("userId", userId);

        List<TipoArma> tiposArmas = new ArrayList<>();
    List<Object[]> results = query.getResultList();
    for (Object[] result : results) {
        TipoArma tipoArma = new TipoArma();
        tipoArma.setTipo((String) result[0]);
        tipoArma.setId_tipo_arma((Long) result[1]);
        tiposArmas.add(tipoArma);
    }

    return tiposArmas;
}







    }

    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Notificacion;
/**
 *
 * @author Zatonio
 */
public class NotificacionJpaController implements Serializable {

    public NotificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Notificacion notificacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(notificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Notificacion notificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Notificacion persistentNotificacion = em.find(Notificacion.class, notificacion.getId_notificacion());
            
            if(persistentNotificacion == null) {
                throw new Exception();
            }
            
            notificacion = em.merge(notificacion);
            em.getTransaction().commit();
            
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = notificacion.getId_notificacion();
                if (findNotificacion(id) == null) {
                    throw new NonexistentEntityException("El notificacion con id " + id + " no existe.");
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
            Notificacion notificacion;
            try {
                notificacion = em.getReference(Notificacion.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            
            em.remove(notificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Notificacion> findNotificacionEntities() {
        return NotificacionJpaController.this.findNotificacionEntities(true, -1, -1);
    }

    public List<Notificacion> findNotificacionEntities(int maxResults, int firstResult) {
        return NotificacionJpaController.this.findNotificacionEntities(false, maxResults, firstResult);
    }

    private List<Notificacion> findNotificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Notificacion.class));
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

    public Notificacion findNotificacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Notificacion.class, id);
        } finally {
            em.close();
        }
    }

    
    
}

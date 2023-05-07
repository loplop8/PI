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
import modelo.entidades.Anuncio;
/**
 *
 * @author Zatonio
 */
public class AnuncioJpaController implements Serializable {

    public AnuncioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Anuncio anuncio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(anuncio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Anuncio anuncio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Anuncio persistentAnuncio = em.find(Anuncio.class, anuncio.getId_anuncio());
            
            if(persistentAnuncio == null) {
                throw new Exception();
            }
            
            anuncio = em.merge(anuncio);
            em.getTransaction().commit();
            
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = anuncio.getId_anuncio();
                if (findAnuncio(id) == null) {
                    throw new NonexistentEntityException("El anuncio con id " + id + " no existe.");
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
            Anuncio anuncio;
            try {
                anuncio = em.getReference(Anuncio.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            
            em.remove(anuncio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Anuncio> findAnuncioEntities() {
        return AnuncioJpaController.this.findAnuncioEntities(true, -1, -1);
    }

    public List<Anuncio> findAnuncioEntities(int maxResults, int firstResult) {
        return AnuncioJpaController.this.findAnuncioEntities(false, maxResults, firstResult);
    }

    private List<Anuncio> findAnuncioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Anuncio.class));
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

    public Anuncio findAnuncio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Anuncio.class, id);
        } finally {
            em.close();
        }
    }
    
    
}

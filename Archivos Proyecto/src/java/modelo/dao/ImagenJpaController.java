/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import modelo.entidades.Anuncio;
import modelo.entidades.Arma;
import modelo.entidades.EstadoAnuncio;
import modelo.entidades.Imagen;

/**
 *
 * @author Zatonio
 */
public class ImagenJpaController implements Serializable {

    public ImagenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Imagen imagen) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(imagen);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findImagen(imagen.getId_imagen()) != null) {
                throw new PreexistingEntityException("Imagen " + imagen + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Imagen imagen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            imagen = em.merge(imagen);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = imagen.getId_imagen();
                if (findImagen(id) == null) {
                    throw new NonexistentEntityException("The imagen with id " + id + " no longer exists.");
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
            Imagen imagen;
            try {
                imagen = em.getReference(Imagen.class, id);
                imagen.getId_imagen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The imagen with id " + id + " no longer exists.", enfe);
            }
            em.remove(imagen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Imagen> findImagenEntities() {
        return findImagenEntities(true, -1, -1);
    }

    public List<Imagen> findImagenEntities(int maxResults, int firstResult) {
        return findImagenEntities(false, maxResults, firstResult);
    }

    private List<Imagen> findImagenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Imagen.class));
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

    public Imagen findImagen(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Imagen.class, id);
        } finally {
            em.close();
        }
    }

    public int getImagenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Imagen> rt = cq.from(Imagen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
      
}

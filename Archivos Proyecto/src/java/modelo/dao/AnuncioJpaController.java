/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Anuncio;
import modelo.entidades.Arma;
import modelo.entidades.EstadoAnuncio;

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
    
     public List<Anuncio> obtenerListaAnunciosArmasFuego() {
        EntityManager em = getEntityManager();
          String queryString ="select a.id_anuncio, a.fecha_public, a.descripcion, a.titulo, a.precio, a.id_arma, a.id_estado_anuncio, a.url_img_principal "
                  + " from Anuncio a, Arma ar, ArmaFuego af where a.id_arma.id_arma=ar.id_arma AND ar.id_arma=af.id_arma.id_arma";

        TypedQuery<Object[]> query = em.createQuery(queryString, Object[].class);
                

        List<Anuncio> anunciosArmasFuego = new ArrayList<>();
    List<Object[]> results = query.getResultList();
    for (Object[] result : results) {
        Anuncio anuncio = new Anuncio();
        anuncio.setId_anuncio((Long)result[0]);
        anuncio.setFecha_public((Date)result[1]);
        anuncio.setDescripcion((String)result[2]);
        anuncio.setTitulo((String)result[3]);
        anuncio.setPrecio((Double)result[4]);
        anuncio.setId_arma((Arma)result[5]);
        anuncio.setId_estado_anuncio((EstadoAnuncio)result[6]);
        anuncio.setUrl_img_principal((String)result[7]);
        anunciosArmasFuego.add(anuncio);
    }
    return anunciosArmasFuego;
    }
     
     
     
     public List<Anuncio> obtenerListaAnunciosArmasReplica() {
        EntityManager em = getEntityManager();
          String queryString ="select a.id_anuncio, a.fecha_public, a.descripcion, a.titulo, a.precio, a.id_arma, a.id_estado_anuncio, a.url_img_principal "
                  + " from Anuncio a, Arma ar, ArmaReplica are where a.id_arma.id_arma=ar.id_arma AND ar.id_arma=are.id_arma.id_arma";

        TypedQuery<Object[]> query = em.createQuery(queryString, Object[].class);
                

        List<Anuncio> anunciosArmasReplica = new ArrayList<>();
    List<Object[]> results = query.getResultList();
    for (Object[] result : results) {
        Anuncio anuncio = new Anuncio();
        anuncio.setId_anuncio((Long)result[0]);
        anuncio.setFecha_public((Date)result[1]);
        anuncio.setDescripcion((String)result[2]);
        anuncio.setTitulo((String)result[3]);
        anuncio.setPrecio((Double)result[4]);
        anuncio.setId_arma((Arma)result[5]);
        anuncio.setId_estado_anuncio((EstadoAnuncio)result[6]);
        anuncio.setUrl_img_principal((String)result[7]);
        anunciosArmasReplica.add(anuncio);
    }
    return anunciosArmasReplica;
    }
     
    public Boolean esAnuncioArmasReplica(Long idAnuncio) {
        Boolean esAnuncioArmasReplica=false;
        EntityManager em = getEntityManager();
          String queryString ="select a.id_anuncio, a.fecha_public, a.descripcion, a.titulo, a.precio, a.id_arma, a.id_estado_anuncio, a.url_img_principal "
                  + " from Anuncio a, Arma ar, ArmaReplica are where a.id_arma.id_arma=ar.id_arma AND ar.id_arma=are.id_arma.id_arma AND a.id_anuncio=:id";

        TypedQuery<Object[]> query = em.createQuery(queryString, Object[].class).setParameter("id", idAnuncio);
                

        List<Anuncio> anunciosArmasReplica = new ArrayList<>();
    List<Object[]> results = query.getResultList();
    if( !results.isEmpty()){
        esAnuncioArmasReplica=true;
    }
    return esAnuncioArmasReplica;
    } 
     
     
     
      public List<Anuncio> obtenerListaAnunciosValidados() {
        EntityManager em = getEntityManager();
          String queryString =" select a.id_anuncio, a.fecha_public, a.descripcion, a.titulo, a.precio, a.id_arma, a.id_estado_anuncio, a.url_img_principal"
                  + " from Anuncio a   where (a.id_estado_anuncio.id_estado_anuncio=3 or a.id_estado_anuncio.id_estado_anuncio=6)";
          
        TypedQuery<Object[]> query = em.createQuery(queryString, Object[].class);
        List<Anuncio> anunciosArmasReplica = new ArrayList<>();
    List<Object[]> results = query.getResultList();
    for (Object[] result : results) {
        Anuncio anuncio = new Anuncio();
        anuncio.setId_anuncio((Long)result[0]);
        anuncio.setFecha_public((Date)result[1]);
        anuncio.setDescripcion((String)result[2]);
        anuncio.setTitulo((String)result[3]);
        anuncio.setPrecio((Double)result[4]);
        anuncio.setId_arma((Arma)result[5]);
        anuncio.setId_estado_anuncio((EstadoAnuncio)result[6]);
        anuncio.setUrl_img_principal((String)result[7]);
        anunciosArmasReplica.add(anuncio);
    }
    return anunciosArmasReplica;
    }
      
      
      
      

      
      
      
    
    
}

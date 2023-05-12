/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.dao.MunicipioJpaController;
import modelo.dao.ProvinciaJpaController;
import modelo.dao.TipoArmaJpaController;
import modelo.dao.TipoLicenciaFacultaTipoArmaJpaController;
import modelo.dao.TipoLicenciaJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Municipio;
import modelo.entidades.Provincia;
import modelo.dao.exceptions.*;
import modelo.entidades.TipoArma;
import modelo.entidades.TipoLicencia;
import modelo.entidades.TipoLicenciaFacultaTipoArma;
import modelo.entidades.Usuario;
/**
 *
 * @author Zatonio
 */
@javax.ejb.Stateless
@Path("")
public class RestController  {

    

     
     @GET     
     @Path("Provincias")
      public List<Provincia> getProvincias() throws NonexistentEntityException{
          
          
          
          List<Provincia> provincias = new ArrayList<>();
        
         
          
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
         ProvinciaJpaController pjc = new ProvinciaJpaController(emf);
    try{
        provincias= pjc.findProvinciaEntities();
    }catch(Exception e){
        
    }
         
         
          
         
         return provincias;
       
         }
    
    
    
     @GET     
     @Path("Municipios/{id}")
      public List<Municipio> getMunicipiosProvincia(@PathParam("id") Long idProvincia){
          
          
          
          List<Municipio> municipios = new ArrayList<>();
        
         
          
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
         MunicipioJpaController mjc = new MunicipioJpaController(emf);
    try{
        municipios= mjc.findMunicipiosPorProvincia(idProvincia);
    }catch(Exception e){
        
    }
         
         
          
         
         return municipios;
       
         }
      
      
      @GET
      @Path("Usuarios")
      public List<Usuario> getUsuariosNickname(){
          
          List<Usuario> usuarios = new ArrayList<>();
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
          UsuarioJpaController ujc = new UsuarioJpaController(emf);
    try{
        usuarios= ujc.findUsuarioEntities();
    }catch(Exception e){
        
    }
    return usuarios;
      }
      
      
      @GET
      @Path("TipoLicencias")
      public List<TipoLicencia> getTiposLicencia(){
          List<TipoLicencia> tipos= new ArrayList<>();
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
           TipoLicenciaJpaController tjc= new TipoLicenciaJpaController(emf);
           
           try{
        tipos= tjc.findTipoLicenciaEntities();
    }catch(Exception e){
        
    }
    return tipos;
     
           
      }
         
    @Context
    private HttpServletRequest request;
    
        @GET
        @Path("UsuarioPuedeUsarTipoArma")
     public List<TipoArma> getTiposArmaporLicencia(){
    
         Usuario usuario=(Usuario)request.getSession().getAttribute("usuario");
         
         Long idUsuario=usuario.getId_usuario();
         
         List<TipoArma> lista= new ArrayList<>();
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
         TipoLicenciaFacultaTipoArmaJpaController tjc= new TipoLicenciaFacultaTipoArmaJpaController(emf);
         
         TipoArmaJpaController tajc=new TipoArmaJpaController(emf);
         
         
         try{
             lista=tajc.obtenerTiposArmasPorUsuario(idUsuario);
         }catch(Exception e){
             System.out.println("El error es : "+e);
            }
         return lista;
         
    
          
}

}   
      
      

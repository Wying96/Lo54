/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationecole.repository;

import fr.utbm.formationecole.entity.Client;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import fr.utbm.formationecole.entity.CourseSession;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import fr.utbm.formationecole.repository.exceptions.NonexistentEntityException;
import fr.utbm.formationecole.repository.exceptions.PreexistingEntityException;

/**
 *
 * @author wuying
 */
public class ClientJpaController implements Serializable {

    public ClientJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Client client) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CourseSession courseSessionId = client.getCourseSessionId();
            if (courseSessionId != null) {
                courseSessionId = em.getReference(courseSessionId.getClass(), courseSessionId.getId());
                client.setCourseSessionId(courseSessionId);
            }
            em.persist(client);
            if (courseSessionId != null) {
                courseSessionId.getClientSet().add(client);
                courseSessionId = em.merge(courseSessionId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClient(client.getId()) != null) {
                throw new PreexistingEntityException("Client " + client + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Client client) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client persistentClient = em.find(Client.class, client.getId());
            CourseSession courseSessionIdOld = persistentClient.getCourseSessionId();
            CourseSession courseSessionIdNew = client.getCourseSessionId();
            if (courseSessionIdNew != null) {
                courseSessionIdNew = em.getReference(courseSessionIdNew.getClass(), courseSessionIdNew.getId());
                client.setCourseSessionId(courseSessionIdNew);
            }
            client = em.merge(client);
            if (courseSessionIdOld != null && !courseSessionIdOld.equals(courseSessionIdNew)) {
                courseSessionIdOld.getClientSet().remove(client);
                courseSessionIdOld = em.merge(courseSessionIdOld);
            }
            if (courseSessionIdNew != null && !courseSessionIdNew.equals(courseSessionIdOld)) {
                courseSessionIdNew.getClientSet().add(client);
                courseSessionIdNew = em.merge(courseSessionIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = client.getId();
                if (findClient(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client client;
            try {
                client = em.getReference(Client.class, id);
                client.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", enfe);
            }
            CourseSession courseSessionId = client.getCourseSessionId();
            if (courseSessionId != null) {
                courseSessionId.getClientSet().remove(client);
                courseSessionId = em.merge(courseSessionId);
            }
            em.remove(client);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Client> findClientEntities() {
        return findClientEntities(true, -1, -1);
    }

    public List<Client> findClientEntities(int maxResults, int firstResult) {
        return findClientEntities(false, maxResults, firstResult);
    }

    private List<Client> findClientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Client.class));
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

    public Client findClient(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Client> rt = cq.from(Client.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

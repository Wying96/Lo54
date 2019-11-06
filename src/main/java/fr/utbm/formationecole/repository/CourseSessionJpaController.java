/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationecole.repository;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import fr.utbm.formationecole.entity.Course;
import fr.utbm.formationecole.entity.Location;
import fr.utbm.formationecole.entity.Client;
import fr.utbm.formationecole.entity.CourseSession;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import fr.utbm.formationecole.repository.exceptions.IllegalOrphanException;
import fr.utbm.formationecole.repository.exceptions.NonexistentEntityException;

/**
 *
 * @author wuying
 */
public class CourseSessionJpaController implements Serializable {

    public CourseSessionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CourseSession courseSession) {
        if (courseSession.getClientSet() == null) {
            courseSession.setClientSet(new HashSet<Client>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Course courseId = courseSession.getCourseId();
            if (courseId != null) {
                courseId = em.getReference(courseId.getClass(), courseId.getId());
                courseSession.setCourseId(courseId);
            }
            Location locationId = courseSession.getLocationId();
            if (locationId != null) {
                locationId = em.getReference(locationId.getClass(), locationId.getId());
                courseSession.setLocationId(locationId);
            }
            Set<Client> attachedClientSet = new HashSet<Client>();
            for (Client clientSetClientToAttach : courseSession.getClientSet()) {
                clientSetClientToAttach = em.getReference(clientSetClientToAttach.getClass(), clientSetClientToAttach.getId());
                attachedClientSet.add(clientSetClientToAttach);
            }
            courseSession.setClientSet(attachedClientSet);
            em.persist(courseSession);
            if (courseId != null) {
                courseId.getCourseSessionSet().add(courseSession);
                courseId = em.merge(courseId);
            }
            if (locationId != null) {
                locationId.getCourseSessionSet().add(courseSession);
                locationId = em.merge(locationId);
            }
            for (Client clientSetClient : courseSession.getClientSet()) {
                CourseSession oldCourseSessionIdOfClientSetClient = clientSetClient.getCourseSessionId();
                clientSetClient.setCourseSessionId(courseSession);
                clientSetClient = em.merge(clientSetClient);
                if (oldCourseSessionIdOfClientSetClient != null) {
                    oldCourseSessionIdOfClientSetClient.getClientSet().remove(clientSetClient);
                    oldCourseSessionIdOfClientSetClient = em.merge(oldCourseSessionIdOfClientSetClient);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CourseSession courseSession) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CourseSession persistentCourseSession = em.find(CourseSession.class, courseSession.getId());
            Course courseIdOld = persistentCourseSession.getCourseId();
            Course courseIdNew = courseSession.getCourseId();
            Location locationIdOld = persistentCourseSession.getLocationId();
            Location locationIdNew = courseSession.getLocationId();
            Set<Client> clientSetOld = persistentCourseSession.getClientSet();
            Set<Client> clientSetNew = courseSession.getClientSet();
            List<String> illegalOrphanMessages = null;
            for (Client clientSetOldClient : clientSetOld) {
                if (!clientSetNew.contains(clientSetOldClient)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Client " + clientSetOldClient + " since its courseSessionId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (courseIdNew != null) {
                courseIdNew = em.getReference(courseIdNew.getClass(), courseIdNew.getId());
                courseSession.setCourseId(courseIdNew);
            }
            if (locationIdNew != null) {
                locationIdNew = em.getReference(locationIdNew.getClass(), locationIdNew.getId());
                courseSession.setLocationId(locationIdNew);
            }
            Set<Client> attachedClientSetNew = new HashSet<Client>();
            for (Client clientSetNewClientToAttach : clientSetNew) {
                clientSetNewClientToAttach = em.getReference(clientSetNewClientToAttach.getClass(), clientSetNewClientToAttach.getId());
                attachedClientSetNew.add(clientSetNewClientToAttach);
            }
            clientSetNew = attachedClientSetNew;
            courseSession.setClientSet(clientSetNew);
            courseSession = em.merge(courseSession);
            if (courseIdOld != null && !courseIdOld.equals(courseIdNew)) {
                courseIdOld.getCourseSessionSet().remove(courseSession);
                courseIdOld = em.merge(courseIdOld);
            }
            if (courseIdNew != null && !courseIdNew.equals(courseIdOld)) {
                courseIdNew.getCourseSessionSet().add(courseSession);
                courseIdNew = em.merge(courseIdNew);
            }
            if (locationIdOld != null && !locationIdOld.equals(locationIdNew)) {
                locationIdOld.getCourseSessionSet().remove(courseSession);
                locationIdOld = em.merge(locationIdOld);
            }
            if (locationIdNew != null && !locationIdNew.equals(locationIdOld)) {
                locationIdNew.getCourseSessionSet().add(courseSession);
                locationIdNew = em.merge(locationIdNew);
            }
            for (Client clientSetNewClient : clientSetNew) {
                if (!clientSetOld.contains(clientSetNewClient)) {
                    CourseSession oldCourseSessionIdOfClientSetNewClient = clientSetNewClient.getCourseSessionId();
                    clientSetNewClient.setCourseSessionId(courseSession);
                    clientSetNewClient = em.merge(clientSetNewClient);
                    if (oldCourseSessionIdOfClientSetNewClient != null && !oldCourseSessionIdOfClientSetNewClient.equals(courseSession)) {
                        oldCourseSessionIdOfClientSetNewClient.getClientSet().remove(clientSetNewClient);
                        oldCourseSessionIdOfClientSetNewClient = em.merge(oldCourseSessionIdOfClientSetNewClient);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = courseSession.getId();
                if (findCourseSession(id) == null) {
                    throw new NonexistentEntityException("The courseSession with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CourseSession courseSession;
            try {
                courseSession = em.getReference(CourseSession.class, id);
                courseSession.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The courseSession with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Client> clientSetOrphanCheck = courseSession.getClientSet();
            for (Client clientSetOrphanCheckClient : clientSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CourseSession (" + courseSession + ") cannot be destroyed since the Client " + clientSetOrphanCheckClient + " in its clientSet field has a non-nullable courseSessionId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Course courseId = courseSession.getCourseId();
            if (courseId != null) {
                courseId.getCourseSessionSet().remove(courseSession);
                courseId = em.merge(courseId);
            }
            Location locationId = courseSession.getLocationId();
            if (locationId != null) {
                locationId.getCourseSessionSet().remove(courseSession);
                locationId = em.merge(locationId);
            }
            em.remove(courseSession);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CourseSession> findCourseSessionEntities() {
        return findCourseSessionEntities(true, -1, -1);
    }

    public List<CourseSession> findCourseSessionEntities(int maxResults, int firstResult) {
        return findCourseSessionEntities(false, maxResults, firstResult);
    }

    private List<CourseSession> findCourseSessionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CourseSession.class));
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

    public CourseSession findCourseSession(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CourseSession.class, id);
        } finally {
            em.close();
        }
    }

    public int getCourseSessionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CourseSession> rt = cq.from(CourseSession.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

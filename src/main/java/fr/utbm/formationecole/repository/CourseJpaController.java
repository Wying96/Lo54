/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationecole.repository;

import fr.utbm.formationecole.entity.Course;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import fr.utbm.formationecole.entity.CourseSession;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import fr.utbm.formationecole.repository.exceptions.IllegalOrphanException;
import fr.utbm.formationecole.repository.exceptions.NonexistentEntityException;
import fr.utbm.formationecole.repository.exceptions.PreexistingEntityException;

/**
 *
 * @author wuying
 */
public class CourseJpaController implements Serializable {

    public CourseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Course course) throws PreexistingEntityException, Exception {
        if (course.getCourseSessionSet() == null) {
            course.setCourseSessionSet(new HashSet<CourseSession>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<CourseSession> attachedCourseSessionSet = new HashSet<CourseSession>();
            for (CourseSession courseSessionSetCourseSessionToAttach : course.getCourseSessionSet()) {
                courseSessionSetCourseSessionToAttach = em.getReference(courseSessionSetCourseSessionToAttach.getClass(), courseSessionSetCourseSessionToAttach.getId());
                attachedCourseSessionSet.add(courseSessionSetCourseSessionToAttach);
            }
            course.setCourseSessionSet(attachedCourseSessionSet);
            em.persist(course);
            for (CourseSession courseSessionSetCourseSession : course.getCourseSessionSet()) {
                Course oldCourseIdOfCourseSessionSetCourseSession = courseSessionSetCourseSession.getCourseId();
                courseSessionSetCourseSession.setCourseId(course);
                courseSessionSetCourseSession = em.merge(courseSessionSetCourseSession);
                if (oldCourseIdOfCourseSessionSetCourseSession != null) {
                    oldCourseIdOfCourseSessionSetCourseSession.getCourseSessionSet().remove(courseSessionSetCourseSession);
                    oldCourseIdOfCourseSessionSetCourseSession = em.merge(oldCourseIdOfCourseSessionSetCourseSession);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCourse(course.getId()) != null) {
                throw new PreexistingEntityException("Course " + course + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Course course) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Course persistentCourse = em.find(Course.class, course.getId());
            Set<CourseSession> courseSessionSetOld = persistentCourse.getCourseSessionSet();
            Set<CourseSession> courseSessionSetNew = course.getCourseSessionSet();
            List<String> illegalOrphanMessages = null;
            for (CourseSession courseSessionSetOldCourseSession : courseSessionSetOld) {
                if (!courseSessionSetNew.contains(courseSessionSetOldCourseSession)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CourseSession " + courseSessionSetOldCourseSession + " since its courseId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<CourseSession> attachedCourseSessionSetNew = new HashSet<CourseSession>();
            for (CourseSession courseSessionSetNewCourseSessionToAttach : courseSessionSetNew) {
                courseSessionSetNewCourseSessionToAttach = em.getReference(courseSessionSetNewCourseSessionToAttach.getClass(), courseSessionSetNewCourseSessionToAttach.getId());
                attachedCourseSessionSetNew.add(courseSessionSetNewCourseSessionToAttach);
            }
            courseSessionSetNew = attachedCourseSessionSetNew;
            course.setCourseSessionSet(courseSessionSetNew);
            course = em.merge(course);
            for (CourseSession courseSessionSetNewCourseSession : courseSessionSetNew) {
                if (!courseSessionSetOld.contains(courseSessionSetNewCourseSession)) {
                    Course oldCourseIdOfCourseSessionSetNewCourseSession = courseSessionSetNewCourseSession.getCourseId();
                    courseSessionSetNewCourseSession.setCourseId(course);
                    courseSessionSetNewCourseSession = em.merge(courseSessionSetNewCourseSession);
                    if (oldCourseIdOfCourseSessionSetNewCourseSession != null && !oldCourseIdOfCourseSessionSetNewCourseSession.equals(course)) {
                        oldCourseIdOfCourseSessionSetNewCourseSession.getCourseSessionSet().remove(courseSessionSetNewCourseSession);
                        oldCourseIdOfCourseSessionSetNewCourseSession = em.merge(oldCourseIdOfCourseSessionSetNewCourseSession);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = course.getId();
                if (findCourse(id) == null) {
                    throw new NonexistentEntityException("The course with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Course course;
            try {
                course = em.getReference(Course.class, id);
                course.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The course with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<CourseSession> courseSessionSetOrphanCheck = course.getCourseSessionSet();
            for (CourseSession courseSessionSetOrphanCheckCourseSession : courseSessionSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Course (" + course + ") cannot be destroyed since the CourseSession " + courseSessionSetOrphanCheckCourseSession + " in its courseSessionSet field has a non-nullable courseId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(course);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Course> findCourseEntities() {
        return findCourseEntities(true, -1, -1);
    }

    public List<Course> findCourseEntities(int maxResults, int firstResult) {
        return findCourseEntities(false, maxResults, firstResult);
    }

    private List<Course> findCourseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Course.class));
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

    public Course findCourse(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Course.class, id);
        } finally {
            em.close();
        }
    }

    public int getCourseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Course> rt = cq.from(Course.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

package ;
import org.hibernate.Session;
import hibernate.HibernateUtil;
import hibernate.Medicine;
public class Save {
    public static void main(String[] args) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();       
            session.beginTransaction();
            Class  q = new ...()
            q.setId(12);
           session.save(q);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

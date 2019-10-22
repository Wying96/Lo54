
import org.hibernate.Session;
import tools.HibernateUtil;
import entity.Client;
import entity.Location;
public class TestSaveHibernate {
    public static void main(String[] args) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Location sb = new Location();
      
            sb.setCity("taizhou");
            
           session.save(sb);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}

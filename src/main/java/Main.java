import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;

import javax.persistence.metamodel.EntityType;
import java.util.Map;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            ourSessionFactory = new Configuration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);

        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            //获取元数据
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            //获取所有实体类
            for (EntityType<?> entityType : metamodel.getEntities()) {
                //获取实体的名字
                final String entityName = entityType.getName();
                //执行查询
                final Query query = session.createQuery("from " + entityName);
                //获取实体对应表的所有的数据
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
    }
}

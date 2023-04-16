import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.hql.internal.ast.tree.Statement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();



        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> studentQuery = builder.createQuery(Student.class);
        Root<Student> studentRoot = studentQuery.from(Student.class);

        CriteriaQuery <Course> courseQuery = builder.createQuery(Course.class);
        Root<Course> courseRoot = courseQuery.from(Course.class);

        String hql = "From " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchaseLists = session.createQuery(hql).getResultList();
        for (PurchaseList var : purchaseLists){
            studentQuery.select(studentRoot).where(builder.equal(studentRoot.get("name"), var.getStudentName()));
            courseQuery.select(courseRoot).where(builder.equal(courseRoot.get("name"), var.getCourseName()));

            Student student = session.createQuery(studentQuery).getSingleResult();
            Course course = session.createQuery(courseQuery).getSingleResult();

            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList(new KeyForLinkedPurchaseList(student.getId(), course.getId()), student.getId(), course.getId());

            session.save(linkedPurchaseList);
        }

///Commented code below is unnecessary. Just for practising.
////        session.get(PurchaseList.class, new KeyForLinkedPurchaseList());
//
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<PurchaseList> purchaseListCriteriaQuery = criteriaBuilder.createQuery(PurchaseList.class);
//        Root<PurchaseList> purchaseListRoot = purchaseListCriteriaQuery.from(PurchaseList.class);
//        purchaseListCriteriaQuery.select(purchaseListRoot);
//        List<PurchaseList> purchaseListList = session.createQuery(purchaseListCriteriaQuery).getResultList();
//
//
////        for (PurchaseList purchaseList : purchaseListList) {
////            System.out.println(purchaseList.getCourseName());
////        }
////
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Course> query = builder.createQuery(Course.class);
//        Root<Course> root = query.from(Course.class);
//        query.select(root);
//        List<Course> courseList = session.createQuery(query).getResultList();
////
////        for (Course course : courseList) {
////            System.out.println(course.getName());
////
////
////        }




        sessionFactory.close();
    }
}

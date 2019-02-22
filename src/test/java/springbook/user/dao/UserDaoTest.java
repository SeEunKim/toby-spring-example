package springbook.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        //UserDao dao = (UserDao)context.getBean("userDao"); // 위의 줄과 같은 의미

        dao.init();

        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + "조회 성공");



        DaoFactory factory = new DaoFactory();
        UserDao dao1 = factory.userDao();
        UserDao dao2 = factory.userDao();

        System.out.println("dao1 : " + dao1);
        System.out.println("dao2 : " + dao2);

        ApplicationContext context1 = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao3 = context1.getBean("userDao", UserDao.class);
        UserDao dao4 = context1.getBean("userDao", UserDao.class);

        System.out.println("dao3 : " + dao3);
        System.out.println("dao4 : " + dao4);

        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        CountingConnectionMaker ccm = context2.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection counter : " + ccm.getCounter());
    }


}
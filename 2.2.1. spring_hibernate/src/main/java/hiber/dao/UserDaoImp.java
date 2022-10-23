package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public User findOnCar(Car car) {
      Query query = sessionFactory.getCurrentSession().createQuery("from User");
      List<User> users = query.getResultList();
      return users.stream()
              .filter((user -> user.getCar().getModel().equals(car.getModel())))
              .filter((user -> user.getCar().getSeries().equals(car.getSeries()))).findFirst()
              .orElse(null);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}

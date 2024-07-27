package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
   public void addCar(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCarModelSeries(String carModel, int carSeries) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("from User u where u.userCar.model = :carModel and u.userCar.series = :carSeries"
                      , User.class);
      query.setParameter("carModel", carModel);
      query.setParameter("carSeries", carSeries);
      return query.getSingleResult();

   }

}

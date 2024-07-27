package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car bmw = new Car("BMW", 6);
      Car mers = new Car("Mercedes", 5);
      Car audi = new Car("Audi", 100);
      User bmwOwner = new User("Peter", "Griffin", "bmw@mail.ru");
      User mersOwner = new User("Homer", "Simpson", "mers@mail.ru");
      User audiOwner = new User("Howard", "Lovecraft", "audi@mail.ru");

      // In case if cascadeType.All is prohibited
//      userService.addCar(bmw);
//      userService.addCar(mers);
//      userService.addCar(audi);

      bmwOwner.setUserCar(bmw);
      mersOwner.setUserCar(mers);
      audiOwner.setUserCar(audi);

      userService.add(bmwOwner);
      userService.add(mersOwner);
      userService.add(audiOwner);



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getUserCar());
         System.out.println();
      }
      System.out.println(userService.getUserByCarModelSeries(bmw.getModel(), bmw.getSeries()));
      System.out.println(userService.getUserByCarModelSeries(audi.getModel(), audi.getSeries()));
      System.out.println(userService.getUserByCarModelSeries(mers.getModel(), mers.getSeries()));

      context.close();
   }
}

package web.dao;



import org.springframework.stereotype.Component;

import web.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> typedQuery = entityManager.createQuery("from User", User.class);
        return typedQuery.getResultList();
    }

    @Override
    public void addUser(String name, String surname, int age) {
        User user = new User(name, surname, age);
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = getUser(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void editUser(int id, String name, String surname, int age) {
        User user = getUser(id);
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        entityManager.merge(user);
    }
}


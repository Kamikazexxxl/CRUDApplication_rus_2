package web.repository;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepoImpl implements UserRepo {

    @PersistenceContext
    EntityManager em;

    public UserRepoImpl() {

    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void addUser(String name, String surname, Integer  age) {
        em.persist(new User(name, surname, age));
    }

    @Override
    public void editUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        em.remove(getUser(id));
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        return em.createQuery("from User where id=:id",User.class).setParameter("id",id).getSingleResult();
    }

}

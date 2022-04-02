package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repository.UserRepo;
import web.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    private UserRepo userRepo;

    private UserRepository repoSpringData;

    @Autowired
    public void setRepoSpringData(UserRepository repoSpringData) {
        this.repoSpringData = repoSpringData;
    }

    @Override
    @Transactional
    public void addUser(User user) {
//        userRepo.addUser(user);
        repoSpringData.save(user);
    }

    @Override
    @Transactional
    public void addUser(String name, String surname, Integer age) {

//        userRepo.addUser(name, surname, age);
        repoSpringData.save(new User(name, surname, age));
    }

    @Override
    @Transactional
    public void editUser(User user) {

//       userRepo.editUser(user);
        repoSpringData.findById(user.getId()).get();
        repoSpringData.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {

//        userRepo.deleteUser(id);
        repoSpringData.deleteById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
//        return userRepo.getAllUsers();
        return StreamSupport.stream(repoSpringData.findAll().spliterator(), false)
                .collect(Collectors.toList());

    }

    @Override
    public User getUser(long id) {

//        return userRepo.getUser(id);
        return repoSpringData.findById(id).get();
    }
}

package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> index() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }
    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
    @Override
    public void update(int id, User updatedUser) {
        User userTobeUpdated = show(id);

        userTobeUpdated.setName(updatedUser.getName());
        userTobeUpdated.setSurName(updatedUser.getSurName());
        userTobeUpdated.setDepartment(updatedUser.getDepartment());
        userTobeUpdated.setSalary(updatedUser.getSalary());
    }
    @Override
    public void delete(int id) {
        entityManager.remove(show(id));
    }
}
package bookStore.service.user;

import bookStore.dto.UserDTO;
import bookStore.entity.User;
import bookStore.entity.UserRole;
import bookStore.repository.UserRepository;
import bookStore.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserRolesRepository userRolesRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO user) {
        byte userEnabled = 1;
        User userToSave = new User(user.username, new ShaPasswordEncoder().encodePassword(user.password, null), userEnabled);
        UserRole userRole = new UserRole(user.username, "EMPLOYEE");
        User savedUser = userRepository.save(userToSave);
        userRolesRepository.save(userRole);
        return savedUser;
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException("User could not be found!");
        }
        userRolesRepository.delete(userRolesRepository.findByUsername(username));
        userRepository.delete(user);
    }

    @Override
    public User update(UserDTO user){
        User userToUpdate = userRepository.findByUsername(user.username);
        if (userToUpdate == null) {
            throw new EntityNotFoundException("User could not be found!");
        }
        userToUpdate.setPassword(new ShaPasswordEncoder().encodePassword(user.password, null));
        return userRepository.save(userToUpdate);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}

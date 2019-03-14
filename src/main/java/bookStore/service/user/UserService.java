package bookStore.service.user;

import bookStore.dto.UserDTO;
import bookStore.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User create(UserDTO book);
    void delete(String username);
    User update(UserDTO user);

    User findByUsername(String username);
}

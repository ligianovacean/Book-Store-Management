package bookStore.unit;

import bookStore.dto.UserDTO;
import bookStore.entity.User;
import bookStore.entity.UserRole;
import bookStore.repository.UserRepository;
import bookStore.repository.UserRolesRepository;
import bookStore.service.user.UserService;
import bookStore.service.user.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;


import java.util.Collections;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    UserService userService;
    @Mock
    UserRolesRepository userRolesRepository;
    @Mock
    UserRepository userRepository;

    private static final String USERNAME = "ligia.novacean";
    private static final String PASSWORD = "qwerty.1";

    @Before
    public void setup(){
        userService = new UserServiceImpl(userRepository, userRolesRepository);
        byte enabled = 0;
        User user = new User(USERNAME, new ShaPasswordEncoder().encodePassword(PASSWORD, null), enabled);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
    }

    @Test
    public void create(){
        UserDTO userDTO = new UserDTO();
        userDTO.password = PASSWORD;
        userDTO.username = USERNAME;
        Assert.assertEquals(userService.create(userDTO).getUsername(), USERNAME);
    }

    @Test
    public void getAll(){
        Assert.assertEquals(userService.getAll().size(), 1);
    }

}

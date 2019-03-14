package bookStore.integration;

import bookStore.Application;
import bookStore.dto.UserDTO;
import bookStore.entity.User;
import bookStore.service.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
@SpringBootApplication
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void createAndDelete(){
        UserDTO userDTO = new UserDTO();
        userDTO.username = "ligia.nov";
        userDTO.password = "qwerty.1";
        Assert.assertTrue(userService.create(userDTO).getUsername().equals("ligia.nov"));
        userService.delete("ligia.nov");
        Assert.assertNull(userService.findByUsername("ligia.nov"));
    }

    @Test
    public void findByUsername() {
        UserDTO userDTO = new UserDTO();
        userDTO.username = "ligia.nova9";
        userDTO.password = "qwerty.1";
        userService.create(userDTO);
        Assert.assertNotNull(userService.findByUsername("ligia.nova9"));
        userService.delete("ligia.nova9");
    }

    @Test
    public void update() {
        UserDTO userDTO = new UserDTO();
        userDTO.username = "ligia.nova0";
        userDTO.password = "qwerty.1";
        userService.create(userDTO);
        userDTO.password = "qwerty.2";
        User user = userService.update(userDTO);
        Assert.assertTrue(user.getUsername().equals("ligia.nova0"));
        userService.delete("ligia.nova0");
    }

}

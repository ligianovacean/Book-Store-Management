package bookStore.repository;

import bookStore.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRole, Integer> {
    UserRole findByUsername(String username);
}

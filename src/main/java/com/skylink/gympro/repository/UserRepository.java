package com.skylink.gympro.repository;
import com.skylink.gympro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByDeleted(boolean deleted);
}

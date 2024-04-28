package hu.imregerman.investmentportfoliomanager.service;

import hu.imregerman.investmentportfoliomanager.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService extends UserDetailsService {
    public Optional<User> getUserById(UUID id);

    public User getUserByName(String name);

    public List<User> getUsers();
}

package hu.imregerman.investmentportfoliomanager.service;

import hu.imregerman.investmentportfoliomanager.model.User;
import hu.imregerman.investmentportfoliomanager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByUserName(name).get();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("No registered user with " + username));
        return org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(user.getUserName())
                .password(user.getPassword())
                .build();
    }
}

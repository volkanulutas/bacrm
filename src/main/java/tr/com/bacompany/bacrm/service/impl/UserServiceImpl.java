package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.data.entity.user.User;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.UserRepository;
import tr.com.bacompany.bacrm.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(Long id) throws ResourceNotFoundException {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            return optUser.get();
        }
        throw new ResourceNotFoundException("User", "User is not found with id: " + id);
    }

    @Override
    public boolean isPresent(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            //authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

}

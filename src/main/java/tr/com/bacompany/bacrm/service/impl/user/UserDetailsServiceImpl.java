package tr.com.bacompany.bacrm.service.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.converter.user.UserConverter;
import tr.com.bacompany.bacrm.data.entity.user.User;
import tr.com.bacompany.bacrm.repository.UserRepository;
import tr.com.bacompany.bacrm.data.dto.auth.UserDetailsImpl;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByEmail(username);
        if (!userOpt.isPresent()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return UserDetailsImpl.build(UserConverter.toDto(userOpt.get()));
    }
}

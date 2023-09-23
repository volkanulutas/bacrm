package tr.com.bacompany.bacrm.controller.sec;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bacompany.bacrm.data.dto.auth.UserDetailsImpl;
import tr.com.bacompany.bacrm.data.dto.auth.request.LoginRequest;
import tr.com.bacompany.bacrm.data.dto.auth.request.SignupRequest;
import tr.com.bacompany.bacrm.data.dto.auth.response.JwtResponse;
import tr.com.bacompany.bacrm.data.dto.auth.response.MessageResponse;
import tr.com.bacompany.bacrm.data.entity.user.Role;
import tr.com.bacompany.bacrm.data.entity.user.User;
import tr.com.bacompany.bacrm.repository.RoleRepository;
import tr.com.bacompany.bacrm.repository.UserRepository;
import tr.com.bacompany.bacrm.security.jwt.JwtUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository,
                          PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            log.error("Error is occurred. ", ex);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            Role adminRole = roleRepository.findByName(role).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
        });
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content. Auth";
    }
}

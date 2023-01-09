package bg.codeacademy.spring.gossiptalks.auth;

import bg.codeacademy.spring.gossiptalks.role.Role;
import bg.codeacademy.spring.gossiptalks.role.RoleRepository;
import bg.codeacademy.spring.gossiptalks.user.User;
import bg.codeacademy.spring.gossiptalks.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
    Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginDto.getUsername(), loginDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody RegistrationDto registrationDto){
    if(this.userRepository.existsByUsername(registrationDto.getUsername())){
      return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
    }

    User user = new User();
    user.setName(registrationDto.getName());
    user.setUsername(registrationDto.getUsername());
    user.setEmail(registrationDto.getEmail());
    user.setPassword(this.passwordEncoder.encode(registrationDto.getPassword()));

    Role roles = this.roleRepository.findByName("ROLE_ADMIN").get();
    user.setRoles(Collections.singleton(roles));

    this.userRepository.save(user);

    return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
  }

  @GetMapping("/logout")
  public ResponseEntity<?> logout(){
    return new ResponseEntity<>("Logout successfully", HttpStatus.OK);
  }
}

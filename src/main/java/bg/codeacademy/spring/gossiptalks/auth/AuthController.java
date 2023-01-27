package bg.codeacademy.spring.gossiptalks.auth;

import bg.codeacademy.spring.gossiptalks.user.User;
import bg.codeacademy.spring.gossiptalks.user.UserRepository;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationDto registrationDto){
    if(this.userRepository.existsByUsername(registrationDto.getUsername())){
      return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
    }

    User user = new User();
    user.setName(registrationDto.getName());
    user.setUsername(registrationDto.getUsername());
    user.setEmail(registrationDto.getEmail());
    user.setPassword(this.passwordEncoder.encode(registrationDto.getPassword()));

//    String json = """
//        {
//          "name": "Ivan"
//        }
//      """

    this.userRepository.save(user);

    return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
  }

  @GetMapping("/logout")
  public ResponseEntity<?> logout(){
    return new ResponseEntity<>("Logout successfully", HttpStatus.OK);
  }
}

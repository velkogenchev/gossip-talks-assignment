package bg.codeacademy.spring.gossiptalks.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
  @SequenceGenerator(name = "users_sequence", initialValue = 100) // first 100 are reserved
  private Long id;
  @Column(unique=true)
  private String email;
  @Column(unique=true)
  private String username;
  private String name;
  private String password;
  @ManyToMany
  @JoinTable(
      name = "following",
      joinColumns = @JoinColumn(name = "follower_id"),
      inverseJoinColumns = @JoinColumn(name = "following_id")
  )
  private Set<User> following;

  @ManyToMany(mappedBy = "following")
  private Set<User> followers;

  public User(String email, String username, String name, String password) {
    this.email = email;
    this.username = username;
    this.name = name;
    this.password = password;
  }
}

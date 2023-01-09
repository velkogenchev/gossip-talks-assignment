package bg.codeacademy.spring.gossiptalks.user;

import bg.codeacademy.spring.gossiptalks.role.Role;
import lombok.AccessLevel;
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
  @Setter(AccessLevel.PROTECTED)
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @Column(unique=true)
  private String email;

  @Column(unique=true)
  private String username;
  private String name;
  private String password;
  private boolean following;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles;

  public User(String email, String username, String name, String password, boolean following) {
    this.email = email;
    this.username = username;
    this.name = name;
    this.password = password;
    this.following = following;
  }
}

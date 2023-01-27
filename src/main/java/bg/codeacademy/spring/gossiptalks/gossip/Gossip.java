package bg.codeacademy.spring.gossiptalks.gossip;

import bg.codeacademy.spring.gossiptalks.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "gossips")
public class Gossip {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
  @SequenceGenerator(name = "users_sequence", initialValue = 50)
  private Long id;
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
  @CreationTimestamp
  private LocalDateTime datetime;
  private String text;
}
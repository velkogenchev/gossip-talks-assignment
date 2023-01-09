package bg.codeacademy.spring.gossiptalks.gossip;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "gossips")
public class Gossip {
  @Id
  @Setter(AccessLevel.PROTECTED)
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;
  private String username;
  @CreationTimestamp
  private LocalDateTime datetime;
  private String text;
}
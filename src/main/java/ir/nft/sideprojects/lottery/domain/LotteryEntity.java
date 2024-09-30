package ir.nft.sideprojects.lottery.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@MappedSuperclass
@Getter
public abstract class LotteryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  protected long id;

  @Column(name = "CreatedAt", nullable = false)
  @CreationTimestamp
  protected Instant createdAt;
}

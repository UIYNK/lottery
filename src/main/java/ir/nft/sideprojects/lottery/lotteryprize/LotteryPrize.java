package ir.nft.sideprojects.lottery.lotteryprize;

import ir.nft.sideprojects.lottery.domain.LotteryEntity;
import ir.nft.sideprojects.lottery.lotteryevent.LotteryEvent;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NamedEntityGraph(
    name = "whole-prize",
    attributeNodes = {@NamedAttributeNode("description"), @NamedAttributeNode("event")})
@NamedEntityGraph(
    name = "prize-with-event",
    attributeNodes = {@NamedAttributeNode("event")})
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Table(
    name = "LotteryPrizes",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = {"Title", "EventID"}),
      @UniqueConstraint(columnNames = {"Priority", "EventID"})
    })
public class LotteryPrize extends LotteryEntity {

  @Column(name = "Title", nullable = false)
  private String title;

  @Column(name = "Number", nullable = false)
  private int number;

  @Column(name = "Priority", nullable = false)
  private int priority;

  @Column(name = "Description")
  @Basic(fetch = FetchType.LAZY)
  private String description;

  @JoinColumn(name = "EventID", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private LotteryEvent event;
}

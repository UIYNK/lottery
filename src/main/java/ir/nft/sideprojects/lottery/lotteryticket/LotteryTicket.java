package ir.nft.sideprojects.lottery.lotteryticket;

import ir.nft.sideprojects.lottery.domain.LotteryEntity;
import ir.nft.sideprojects.lottery.lotteryevent.LotteryEvent;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NamedEntityGraph(
    name = "whole-ticket",
    attributeNodes = {@NamedAttributeNode("event")})
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Table(name = "LotteryTickets")
public class LotteryTicket extends LotteryEntity {

  @Column(name = "SerialNumber", nullable = false, unique = true)
  private Long serialNumber;

  @JoinColumn(name = "EventID", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private LotteryEvent event;
}

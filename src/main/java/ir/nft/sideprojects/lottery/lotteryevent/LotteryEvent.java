package ir.nft.sideprojects.lottery.lotteryevent;

import ir.nft.sideprojects.lottery.domain.LotteryEntity;
import ir.nft.sideprojects.lottery.lotteryprize.LotteryPrize;
import ir.nft.sideprojects.lottery.lotteryticket.LotteryTicket;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NamedEntityGraph(
    name = "LotteryEvent.withPrizes",
    attributeNodes = {@NamedAttributeNode("prizes")})
@NamedEntityGraph(
    name = "LotteryEvent.withTickets",
    attributeNodes = {@NamedAttributeNode("tickets")})
@NamedEntityGraph(
    name = "LotteryEvent.withDescription",
    attributeNodes = {@NamedAttributeNode("description")})
@NamedEntityGraph(name = "whole-event", includeAllAttributes = true)
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Table(name = "LotteryEvents")
public class LotteryEvent extends LotteryEntity {

  @Column(name = "Title", unique = true, nullable = false)
  private String title;

  @Column(name = "Description")
  @Basic(fetch = FetchType.LAZY)
  private String description;

  @OneToMany(mappedBy = "event", orphanRemoval = true)
  private List<LotteryPrize> prizes;

  @OneToMany(mappedBy = "event", orphanRemoval = true)
  private List<LotteryTicket> tickets;
}

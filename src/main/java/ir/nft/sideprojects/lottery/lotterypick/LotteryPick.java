package ir.nft.sideprojects.lottery.lotterypick;

import ir.nft.sideprojects.lottery.domain.LotteryEntity;
import ir.nft.sideprojects.lottery.lotteryprize.LotteryPrize;
import ir.nft.sideprojects.lottery.lotteryticket.LotteryTicket;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NamedEntityGraph(
    name = "LotteryPick.withTicketAndPrize",
    attributeNodes = {@NamedAttributeNode("ticket"), @NamedAttributeNode("prize")})
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Table(name = "LotteryPicks")
public class LotteryPick extends LotteryEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TicketID", nullable = false, unique = true)
  private LotteryTicket ticket;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PrizeID", nullable = false)
  private LotteryPrize prize;
}

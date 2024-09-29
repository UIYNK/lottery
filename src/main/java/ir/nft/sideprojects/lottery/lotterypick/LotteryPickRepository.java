package ir.nft.sideprojects.lottery.lotterypick;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotteryPickRepository extends JpaRepository<LotteryPick, Long> {
  @EntityGraph("LotteryPick.withTicketAndPrize")
  List<LotteryPick> findAllByOrderByCreatedAtDesc();
}

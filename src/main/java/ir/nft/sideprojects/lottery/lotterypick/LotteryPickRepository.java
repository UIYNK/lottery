package ir.nft.sideprojects.lottery.lotterypick;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LotteryPickRepository extends JpaRepository<LotteryPick, Long> {
  @EntityGraph("LotteryPick.withTicketAndPrize")
  List<LotteryPick> findAllByOrderByCreatedAtDesc();

  long countLotteryPickByPrize_PriorityAndPrize_Event_Id(int prizePriority, long eventId);

  Optional<LotteryPick> findByTicket_SerialNumber(long serialNumber);

  @EntityGraph("LotteryPick.withTicketAndPrize")
  List<LotteryPick> findAllByOrderByIdDesc();

  void deleteAllByTicket_Event_Id(long eventId);
}

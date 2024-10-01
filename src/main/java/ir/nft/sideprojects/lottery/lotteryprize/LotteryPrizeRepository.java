package ir.nft.sideprojects.lottery.lotteryprize;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotteryPrizeRepository extends JpaRepository<LotteryPrize, Long> {
  List<LotteryPrize> findAllByEvent_IdOrderByPriorityDesc(Long eventId);
}

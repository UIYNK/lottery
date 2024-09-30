package ir.nft.sideprojects.lottery.lotteryevent;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LotteryEventRepository extends JpaRepository<LotteryEvent, Long> {
  @EntityGraph("LotteryEvent.withPrizes")
  Optional<LotteryEvent> findById(Long id);

  LotteryEvent findFirstByOrderByIdAsc();
}

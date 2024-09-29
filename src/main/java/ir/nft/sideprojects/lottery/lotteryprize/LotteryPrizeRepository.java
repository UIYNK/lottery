package ir.nft.sideprojects.lottery.lotteryprize;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryPrizeRepository extends JpaRepository<LotteryPrize, Long> {}

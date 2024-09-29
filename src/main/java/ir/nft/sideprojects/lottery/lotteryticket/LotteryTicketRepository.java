package ir.nft.sideprojects.lottery.lotteryticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LotteryTicketRepository extends JpaRepository<LotteryTicket, Long> {
  @Query(
      nativeQuery = true,
      value =
          "select top 1  tk.* from\n"
              + "                   LOTTERYTICKETS tk left join LOTTERYPICKS pk on tk.ID=pk.TICKETID where pk.ID is null order by RAND()*RAND();")
  LotteryTicket pickRandomTicket();
}

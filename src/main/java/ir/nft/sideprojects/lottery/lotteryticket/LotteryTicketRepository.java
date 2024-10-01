package ir.nft.sideprojects.lottery.lotteryticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LotteryTicketRepository extends JpaRepository<LotteryTicket, Long> {
  //  @Query(
  //      nativeQuery = true,
  //      value =
  //          "select top 1  tk.* from\n"
  //              + "                   LOTTERYTICKETS tk left join LOTTERYPICKS pk on
  // tk.ID=pk.TICKETID where pk.ID is null order by RAND()*RAND();")
  //  LotteryTicket pickRandomTicket();

  Optional<LotteryTicket> findBySerialNumber(long serialNumber);

  LotteryTicket findFirstByOrderByRandomIndex();

  @Query(
      nativeQuery = true,
      value =
          "select top 1  tk.* from LOTTERYTICKETS tk left join LOTTERYPICKS pk on tk.ID=pk.TICKETID where pk.ID is null order by RAND()*RAND();")
  LotteryTicket pickRandomTicket();
}

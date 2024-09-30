package ir.nft.sideprojects.lottery.business;

import ir.nft.sideprojects.lottery.lotteryevent.LotteryEventRepository;
import ir.nft.sideprojects.lottery.lotterypick.LotteryPickRepository;
import ir.nft.sideprojects.lottery.lotteryprize.LotteryPrizeRepository;
import ir.nft.sideprojects.lottery.lotteryticket.LotteryTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotteryService {
  private final LotteryEventRepository eventRepository;
  private final LotteryPickRepository pickRepository;
  private final LotteryTicketRepository ticketRepository;
  private final LotteryPrizeRepository prizeRepository;
}

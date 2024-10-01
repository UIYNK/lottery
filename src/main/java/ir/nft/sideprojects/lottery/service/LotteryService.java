package ir.nft.sideprojects.lottery.service;

import ir.nft.sideprojects.lottery.exception.OutOfPrizeException;
import ir.nft.sideprojects.lottery.exception.TicketAlreadyWonException;
import ir.nft.sideprojects.lottery.exception.TicketNotFoundException;
import ir.nft.sideprojects.lottery.lotteryevent.LotteryEvent;
import ir.nft.sideprojects.lottery.lotteryevent.LotteryEventRepository;
import ir.nft.sideprojects.lottery.lotterypick.LotteryPick;
import ir.nft.sideprojects.lottery.lotterypick.LotteryPickRepository;
import ir.nft.sideprojects.lottery.lotteryprize.LotteryPrize;
import ir.nft.sideprojects.lottery.lotteryprize.LotteryPrizeRepository;
import ir.nft.sideprojects.lottery.lotteryticket.LotteryTicket;
import ir.nft.sideprojects.lottery.lotteryticket.LotteryTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LotteryService {
  private final LotteryEventRepository eventRepository;
  private final LotteryPickRepository pickRepository;
  private final LotteryTicketRepository ticketRepository;
  private final LotteryPrizeRepository prizeRepository;

  public void clearPicks(long eventId) {
    pickRepository.deleteAll();
  }

  public List<LotteryPick> getCurrentPicks() {
    return pickRepository.findAllByOrderByIdDesc();
  }

  public void autoPickRemainingPrizes(long eventId) {

    while (true) {
      LotteryTicket newTicket = ticketRepository.pickRandomTicket();

      LotteryPrize currentPrize = getCurrentPrize(eventId);
      if (currentPrize == null) {
        break;
      }
      LotteryPick newPick = LotteryPick.builder().prize(currentPrize).ticket(newTicket).build();
      pickRepository.save(newPick);
    }
  }

  public void pickIndividualSerialNumber(long serialNumber, long eventId)
      throws OutOfPrizeException, TicketAlreadyWonException, TicketNotFoundException {

    LotteryPrize currentPrize = getCurrentPrize(eventId);
    if (currentPrize == null) {
      throw new OutOfPrizeException();
    }
    LotteryTicket ticket =
        ticketRepository
            .findBySerialNumber(serialNumber)
            .orElseThrow(() -> new TicketNotFoundException());

    if (pickRepository.findByTicket_SerialNumber(serialNumber).isEmpty()) {
      LotteryPick newPick = LotteryPick.builder().prize(currentPrize).ticket(ticket).build();
      pickRepository.save(newPick);
      return;
    }
    throw new TicketAlreadyWonException();
  }

  public LotteryPrize getCurrentPrize(long eventId) {
    List<LotteryPrize> prizes = prizeRepository.findAllByEvent_IdOrderByPriorityDesc(eventId);
    LotteryPrize currentPrize = null;
    for (LotteryPrize p : prizes) {
      if (pickRepository.countLotteryPickByPrize_PriorityAndPrize_Event_Id(p.getPriority(), eventId)
          < p.getNumber()) {
        currentPrize = p;
        break;
      }
    }
    if (currentPrize == null) {
      return null;
    }
    return currentPrize;
  }

  public LotteryEvent findCurrentEvent() {
    // check for null later
    return eventRepository.findFirstByOrderByIdAsc();
  }
}

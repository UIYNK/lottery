package ir.nft.sideprojects.lottery.managedbean;

import ir.nft.sideprojects.lottery.business.LotteryService;
import ir.nft.sideprojects.lottery.lotteryevent.LotteryEvent;
import ir.nft.sideprojects.lottery.lotteryevent.LotteryEventRepository;
import ir.nft.sideprojects.lottery.lotterypick.LotteryPick;
import ir.nft.sideprojects.lottery.lotterypick.LotteryPickRepository;
import ir.nft.sideprojects.lottery.lotteryprize.LotteryPrize;
import ir.nft.sideprojects.lottery.lotteryticket.LotteryTicket;
import ir.nft.sideprojects.lottery.lotteryticket.LotteryTicketRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Named("eventState")
@ViewScoped
@Getter
@Setter
public class EventState implements Serializable {
  private final LotteryEventRepository eventRepository;
  private final LotteryPickRepository pickRepository;
  private final LotteryService lotteryService;

  private LotteryEvent currentEvent;
  private List<LotteryPrize> currentEventPrizes;
  private int toBePicked;
  private int alreadyPicked;
  private int remainingPicks;
  private List<LotteryPick> currentEventPicks;
  private boolean isFinished;

  @Inject
  public EventState(
      LotteryEventRepository eventRepository,
      LotteryPickRepository pickRepository,
      LotteryService lotteryService) {
    this.eventRepository = eventRepository;
    this.pickRepository = pickRepository;
    this.lotteryService = lotteryService;
  }

  @PostConstruct
  public void init() {
    currentEvent =
        eventRepository.findById(1L).orElseThrow(() -> new RuntimeException("No event found"));
    currentEventPicks = pickRepository.findAllByOrderByCreatedAtDesc();
    currentEventPrizes = currentEvent.getPrizes();
    alreadyPicked = currentEventPicks.size();
    remainingPicks = currentEventPrizes.size() - alreadyPicked;
    isFinished = (remainingPicks < 1) || (alreadyPicked >= currentEvent.getTickets().size());
    toBePicked = isFinished ? 0 : 1;
  }

  public void pick() {
    if (toBePicked > remainingPicks) {
      toBePicked = remainingPicks;
    }
    lotteryService.pickTickets(toBePicked);
    currentEventPicks = pickRepository.findAllByOrderByCreatedAtDesc();
    alreadyPicked = currentEventPicks.size();
    remainingPicks = currentEvent.getPrizes().size() - alreadyPicked;
    if (remainingPicks == 0 || (alreadyPicked >= currentEvent.getTickets().size())) {
      toBePicked = 0;
      isFinished = true;
    }
  }

  public void rest() {
    lotteryService.resetPicks();
    currentEventPicks = pickRepository.findAllByOrderByCreatedAtDesc();
    alreadyPicked = currentEventPicks.size();
    remainingPicks = currentEvent.getPrizes().size() - alreadyPicked;
    isFinished = (remainingPicks < 1) || (alreadyPicked >= currentEvent.getTickets().size());
    toBePicked = isFinished ? 0 : 1;
  }
}

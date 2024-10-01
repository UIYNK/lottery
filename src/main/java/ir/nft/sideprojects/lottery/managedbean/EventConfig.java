package ir.nft.sideprojects.lottery.managedbean;

import ir.nft.sideprojects.lottery.lotteryevent.LotteryEvent;
import ir.nft.sideprojects.lottery.lotteryevent.LotteryEventRepository;
import ir.nft.sideprojects.lottery.lotteryticket.LotteryTicket;
import ir.nft.sideprojects.lottery.lotteryticket.LotteryTicketRepository;
import ir.nft.sideprojects.lottery.service.LotteryService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Named("eventConfig")
@ViewScoped
@Getter
@Setter
public class EventConfig {
  private final LotteryTicketRepository ticketRepository;
  private final LotteryEventRepository eventRepository;
  private final LotteryService lotteryService;

  private LotteryEvent currentEvent;
  private int serialNumbersStart;
  private int serialNumbersEnd;

  @Inject
  public EventConfig(
      LotteryTicketRepository ticketRepository,
      LotteryEventRepository eventRepository,
      LotteryService lotteryService) {
    this.ticketRepository = ticketRepository;
    this.eventRepository = eventRepository;
    this.lotteryService = lotteryService;
  }

  @PostConstruct
  public void init() {
    // null check later
    currentEvent = eventRepository.findFirstByOrderByIdAsc();
  }

  public void clearPicks() {
    lotteryService.clearPicks(currentEvent.getId());
  }

  public void saveEventConfig() {
    List<LotteryTicket> tickets = new ArrayList<>();
    for (long i = serialNumbersStart; i <= serialNumbersEnd; i++) {
      tickets.add(
          LotteryTicket.builder()
              .event(currentEvent)
              .serialNumber(i)
              .randomIndex(UUID.randomUUID())
              .build());
    }
    ticketRepository.saveAll(tickets);
  }
}

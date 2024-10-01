package ir.nft.sideprojects.lottery.managedbean;

import ir.nft.sideprojects.lottery.lotteryevent.LotteryEvent;
import ir.nft.sideprojects.lottery.lotterypick.LotteryPick;
import ir.nft.sideprojects.lottery.lotteryticket.LotteryTicket;
import ir.nft.sideprojects.lottery.service.LotteryService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Named("resultsView")
@ViewScoped
@Getter
@Setter
public class ResultsView {
  private final LotteryService lotteryService;

  private LotteryEvent currentEvent;
  private List<LotteryPick> winners;
  private List<LotteryTicket> carWinner = new ArrayList<>();
  private List<LotteryTicket> bicycleWinners = new ArrayList<>();
  private List<LotteryTicket> couponWinners = new ArrayList<>();
  private List<LotteryTicket> goodsWinners = new ArrayList<>();

  @Inject
  public ResultsView(LotteryService lotteryService) {
    this.lotteryService = lotteryService;
  }

  @PostConstruct
  public void init() {
    currentEvent = lotteryService.findCurrentEvent();
    winners = lotteryService.getCurrentPicks();
    winners.stream()
        .forEach(
            lotteryPick -> {
              if (lotteryPick.getPrize().getTitle().equals("خودرو")) {
                carWinner.add(lotteryPick.getTicket());
              } else if (lotteryPick.getPrize().getTitle().equals("دوچرخه")) {
                bicycleWinners.add(lotteryPick.getTicket());
              } else if (lotteryPick.getPrize().getTitle().equals("بن خرید")) {
                couponWinners.add(lotteryPick.getTicket());
              } else if (lotteryPick.getPrize().getTitle().equals("اقلام مصرفی")) {
                goodsWinners.add(lotteryPick.getTicket());
              }
            });
  }

  public List<Long> getBicycleWinnersSerials() {
    return bicycleWinners.stream().map((winner) -> winner.getSerialNumber()).toList();
  }

  public List<Long> getCouponWinnersSerials() {
    return couponWinners.stream().map((winner) -> winner.getSerialNumber()).toList();
  }

  public List<Long> getGoodsWinnersSerials() {
    return goodsWinners.stream().map((winner) -> winner.getSerialNumber()).toList();
  }

  public List<Long> getCarWinnersSerials() {
    return carWinner.stream().map((winner) -> winner.getSerialNumber()).toList();
  }
}

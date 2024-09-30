package ir.nft.sideprojects.lottery.managedbean;

import ir.nft.sideprojects.lottery.business.LotteryService;
import ir.nft.sideprojects.lottery.lotteryevent.LotteryEventRepository;
import ir.nft.sideprojects.lottery.lotterypick.LotteryPickRepository;
import ir.nft.sideprojects.lottery.lotteryticket.LotteryTicketRepository;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named("lotteryView")
@ViewScoped
@Getter
@Setter
public class LotteryView {
  private final LotteryService lotteryService;

  @Inject
  public LotteryView(LotteryService lotteryService) {
    this.lotteryService = lotteryService;
  }
}

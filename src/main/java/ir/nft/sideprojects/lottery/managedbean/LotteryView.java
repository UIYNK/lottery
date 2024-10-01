package ir.nft.sideprojects.lottery.managedbean;

import ir.nft.sideprojects.lottery.lotterypick.LotteryPick;
import ir.nft.sideprojects.lottery.service.LotteryService;
import ir.nft.sideprojects.lottery.lotteryevent.LotteryEvent;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Named("lotteryView")
@ViewScoped
@Getter
@Setter
public class LotteryView {
  private final LotteryService lotteryService;

  private LotteryEvent currentEvent;
  private List<LotteryPick> currentPicks;
  private int[] individualNumber = new int[] {0, 0, 0, 0, 0};

  @Inject
  public LotteryView(LotteryService lotteryService) {
    this.lotteryService = lotteryService;
  }

  @PostConstruct
  public void init() {
    currentEvent = lotteryService.findCurrentEvent();
    currentPicks = lotteryService.getCurrentPicks();
  }

  public void autoPickRemainingPrizes() {
    lotteryService.autoPickRemainingPrizes(currentEvent.getId());
    currentPicks = lotteryService.getCurrentPicks();
  }

  public void pickIndividualSerialNumber() {
    long serialNumber = turnDigitsIntoInteger(individualNumber);
    try {
      lotteryService.pickIndividualSerialNumber(serialNumber, currentEvent.getId());
      currentPicks = lotteryService.getCurrentPicks();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public long turnDigitsIntoInteger(int[] digits) {
    int integerNumber = 0;
    for (int i = 0; i < digits.length; i++) {
      integerNumber += (Math.pow(10, i) * digits[i]);
    }
    return integerNumber;
  }
}

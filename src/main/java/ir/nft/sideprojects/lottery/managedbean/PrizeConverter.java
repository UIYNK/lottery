package ir.nft.sideprojects.lottery.managedbean;

import ir.nft.sideprojects.lottery.lotteryprize.LotteryPrize;
import ir.nft.sideprojects.lottery.lotteryprize.LotteryPrizeRepository;
import jakarta.faces.FacesException;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import org.springframework.stereotype.Component;

// @FacesConverter(value = "lotteryPrizeConverter", managed = true)
@Component("lotteryPrizeConverter")
public class PrizeConverter implements Converter<LotteryPrize> {

  private final LotteryPrizeRepository lotteryPrizeRepository;

  public PrizeConverter(LotteryPrizeRepository lotteryPrizeRepository) {
    this.lotteryPrizeRepository = lotteryPrizeRepository;
  }

  @Override
  public LotteryPrize getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
    return lotteryPrizeRepository
        .findById(Long.valueOf(id))
        .orElseThrow(() -> new FacesException("Prize not found"));
  }

  @Override
  public String getAsString(
      FacesContext facesContext, UIComponent uiComponent, LotteryPrize lotteryPrize) {
    if (lotteryPrize == null) {
      return "";
    }
    return String.valueOf(lotteryPrize.getId());
  }
}

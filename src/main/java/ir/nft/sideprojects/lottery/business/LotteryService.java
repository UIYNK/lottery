package ir.nft.sideprojects.lottery.business;

import ir.nft.sideprojects.lottery.lotterypick.LotteryPick;
import ir.nft.sideprojects.lottery.lotteryprize.LotteryPrize;
import ir.nft.sideprojects.lottery.lotteryticket.LotteryTicket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LotteryService {
  private final EntityManagerFactory entityManagerFactory;

  public boolean resetPicks() {
    try (EntityManager entityManager = entityManagerFactory.createEntityManager(); ) {
      entityManager.getTransaction().begin();
      Query query = entityManager.createQuery("delete from LotteryPick");
      query.executeUpdate();
      entityManager.getTransaction().commit();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return true;
  }

  public boolean pickTickets(int num) {
    List<LotteryPick> lotteryPicks = new ArrayList<>();
    try (EntityManager entityManager = entityManagerFactory.createEntityManager(); ) {
      entityManager.getTransaction().begin();
      for (int i = 0; i < num; i++) {

        Query createPickQuery =
            entityManager.createNativeQuery(
                "insert into LOTTERYPICKS(createdat, ticketid, prizeid) VALUES ( CURRENT_TIMESTAMP,(select  top 1 tk.ID from LOTTERYTICKETS tk left join LOTTERYPICKS pk on tk.ID=pk.TICKETID where pk.ID is null order by RAND()*RAND()),(select top 1 pr.ID from LOTTERYPRIZES pr left join LOTTERYPICKS pk on pr.ID=pk.PRIZEID where pk.ID is null order by RAND()*RAND()));");
        createPickQuery.executeUpdate();
      }

      entityManager.getTransaction().commit();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return true;
  }
}

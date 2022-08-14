import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

//With the class , we can easily create instance, config with another campaign, easily to improve and can be used by another class

public class IntegerDistribution {

  private final String distribution;

  public IntegerDistribution(String distribution) {
    this.distribution = distribution;
  }

  public Integer getRandom() {
    List<Integer> percent = new ArrayList<>();
    List<Integer> prize = new ArrayList<>();

    if (distribution != null && !"".equalsIgnoreCase(distribution)) {
      String[] keyValues = distribution.split(",");
      int sum = 0;
      for (String keyValue : keyValues) {
        String[] values = keyValue.split("=");
        int chance = (int) (Double.parseDouble(values[0]) * 100);
        prize.add(Integer.valueOf(values[1]));
        sum += chance;
        percent.add(sum);
      }

      Random random = new Random();
      int randomNumber = random.nextInt(100) + 1;

      for (int i = 0; i < percent.size(); i++) {
        if (randomNumber <= percent.get(i)) {
          return prize.get(i);
        }
      }
      return prize.get(prize.size() - 1);
    }
    return 0;
  }

  private Map<Integer, Integer> getPrizeSummary() {
    Map<Integer, Integer> prizeSummary = new HashMap<>();
    prizeSummary.put(0, 0);
    if (distribution != null && !"".equalsIgnoreCase(distribution)) {
      String[] keyValues = distribution.split(",");
      for (String keyValue : keyValues) {
        String[] values = keyValue.split("=");
        prizeSummary.put(Integer.valueOf(values[1]), 0);
      }
    }
    return prizeSummary;
  }

  public static void main(String[] args) {
    int n = 100;
    IntegerDistribution test = new IntegerDistribution("0.5=1000,0.3=5000,0.15=10000,0.05=1000000");
    Map<Integer, Integer> map = test.getPrizeSummary();
    for (int i = 0; i < n; i++) {
      int result = test.getRandom();
      map.put(result, map.get(result) + 1);
    }
    System.out.println(map);
  }

}
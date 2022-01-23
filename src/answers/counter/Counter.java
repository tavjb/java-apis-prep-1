package answers.counter;

import lombok.Data;
import java.util.Calendar;

@Data
public class Counter {
  public Counter(int goal, String name) {
    this.goal = goal;
    this.name = name;
  }

  public Counter(int goal, String name, Calendar expiration) {
    this.goal = goal;
    this.name = name;
    this.expiration = expiration;
  }

  private final int goal;
  private final String name;
  private int stoppedAt;
  private Calendar expiration;
}

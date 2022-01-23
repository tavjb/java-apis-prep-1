package answers;

import answers.counter.Counter;
import answers.threads.CounterTask;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class CountingSystem {
  public static final CountingSystem instance = new CountingSystem();

  private CountingSystem() {
    this.isRunning = true;
    finishedTaskKillerThread = new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(20000);
        } catch (InterruptedException e) {
          System.out.println("counter-killer stopped");
        }

        for (Counter counter : getCounters()) {
          if (counter.getGoal() >= counter.getStoppedAt()) {
            System.out.println("Removing " + counter);
            removeCounter(counter);
          }
        }
      }
    });
    finishedTaskKillerThread.start();
  }

  public void printMenu() {
    System.out.println("Please choose an action: 1 - add new counter, 2 - reactivate counter, 3 - see all available counters, 4 - exit");
  }

  @Getter
  private final Set<Counter> counters = new HashSet<>();
  @Getter
  @Setter
  private boolean isRunning;
  private final Thread finishedTaskKillerThread;

  public void addCounter(int goal, String name, Calendar expiration) {
    try {
      Counter counter = new Counter(goal, name, expiration);
      new Thread(new CounterTask(counter)).start();
      counters.add(counter);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void removeCounter(Counter counter) {
    counters.remove(counter);
  }

  public void reactivateCounter(String name, Calendar expiration) throws IOException {
    for (Counter counter : counters) {
      if (name.equals(counter.getName())) {
        counter.setExpiration(expiration);
        new Thread(new CounterTask(counter)).start();
      }
    }
  }

  public void kill() {
    finishedTaskKillerThread.stop();
  }

}

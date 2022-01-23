package answers.threads;

import answers.counter.Counter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class CounterTask implements Runnable {
  public CounterTask(Counter counter) throws IOException {
    this.counter = counter;
    this.bufferedWriter = new BufferedWriter(new FileWriter(this.counter.getName() + ".txt"));
  }

  private final Counter counter;
  private final BufferedWriter bufferedWriter;

  @Override
  public void run() {
    try {
      int current = counter.getStoppedAt();
      while (current <= counter.getGoal() && Calendar.getInstance().before(counter.getExpiration())) {
        Thread.sleep(1000);
        bufferedWriter.write(String.valueOf(current++) + "\n");
        bufferedWriter.flush();
      }
      bufferedWriter.write(String.valueOf(Calendar.getInstance()));
      counter.setStoppedAt(current);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}

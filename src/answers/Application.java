package answers;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Application {
  public static void main(String[] args) throws IOException {
    while (CountingSystem.instance.isRunning()) {
      CountingSystem.instance.printMenu();
      Scanner scanner = new Scanner(System.in);
      int cmd = scanner.nextInt();
      switch (cmd) {
        case 1:
          initNewCounter();
          break;

        case 2:
          reactivateCounter();
          break;

        case 3:
          CountingSystem.instance.getCounters().forEach(System.out::println);
          break;

        default:
          CountingSystem.instance.setRunning(false);
          CountingSystem.instance.kill();
      }
    }
  }

  private static void reactivateCounter() throws IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.print("What's the counter's name?");
    String name = scanner.next();
    CountingSystem.instance.reactivateCounter(name, promptExpiration());
  }

  private static void initNewCounter() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("What's the counter's name?");
    String name = scanner.next();
    System.out.print("What's the counter goal?");
    int goal = scanner.nextInt();
    CountingSystem.instance.addCounter(goal, name, promptExpiration());
  }

  private static Calendar promptExpiration() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("When do you want to counter to expire? (yyyy MM dd HH mm ss): ");
    int year = scanner.nextInt();
    int month = scanner.nextInt();
    int day = scanner.nextInt();
    int hour = scanner.nextInt();
    int minute = scanner.nextInt();
    int second = scanner.nextInt();
    Calendar expiration = new GregorianCalendar(year, month - 1, day, hour, minute, second);
    return expiration;
  }


}

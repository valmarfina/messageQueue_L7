import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
  public Main() {
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Need to pass a positive integer!\n");
    } else {
      int value;
      try {
        value = Integer.parseInt(args[0]);
      } catch (NumberFormatException var8) {
        System.err.println("Need to pass a positive integer!\n");
        return;
      }
      if (value < 0) {
        System.err.println("Need to pass a positive integer!\n");
      } else {
        BlockingQueue<String> queue = new LinkedBlockingQueue(10);
        AtomicInteger ind = new AtomicInteger(0);
        ExecutorService writeExc = Executors.newCachedThreadPool((r) -> {
          return new Thread(r, "Writer-" + ind.getAndIncrement());
        });
        ExecutorService readExc = Executors.newCachedThreadPool((r) -> {
          return new Thread(r, "Reader-" + ind.getAndIncrement());
        });

        for (int i = 0; i < value; ++i) {
          writeExc.execute(new Writer(queue));
          readExc.execute(new Reader(queue));
        }

        try {
          Thread.sleep(3000L);
        } catch (InterruptedException var7) {
          var7.printStackTrace();
        }
        writeExc.shutdownNow();
        readExc.shutdownNow();
      }
    }
  }
}

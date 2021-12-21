import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;

public class Writer implements Runnable {
  private final BlockingQueue<String> queue;

  Writer(BlockingQueue<String> tempQueue) {
    this.queue = tempQueue;
  }

  public void run() {
    int count = 0;

    while (true) {
      try {
        PrintStream var10000 = System.out;
        String var10001 = Thread.currentThread().getName();
        var10000.println(var10001 + " write " + count + " message in queue");
        this.queue.put("[" + count + " message of " + Thread.currentThread().getName() + "]");
        Thread.sleep(300L);
      } catch (InterruptedException var3) {
        return;
      }

      ++count;
    }
  }
}
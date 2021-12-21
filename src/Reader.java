import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;

public class Reader implements Runnable {
  private final BlockingQueue<String> queue;

  Reader(BlockingQueue<String> tempQueue) {
    this.queue = tempQueue;
  }

  public void run() {
    while(true) {
      String str = "";
      try {
        str = (String)this.queue.take();
      } catch (InterruptedException var3) {
        return;
      }
      PrintStream var10000 = System.out;
      String var10001 = Thread.currentThread().getName();
      var10000.println(var10001 + " read from queue: " + str);
    }
  }
}

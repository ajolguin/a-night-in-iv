package edu.ucsb.cs48.a_night_in_iv;

import java.lang.Runnable;

/*    Allows the "treasure found" message to disappear.    
      @author George Lieu (3/1/16, W16, CS56)
      @author Danielle Dodd (3/1/16, W16, CS56)
      

*/

public class MessageThread implements Runnable {

    GameComponent gc;

    public MessageThread(GameComponent gc) {
        this.gc = gc;
    }

    public void run() {

        try {
            Thread.sleep(5000);
            gc.message = "";
        } catch (InterruptedException ie) {
            System.out.println("Thread was interrupted!");
        }
    }
}

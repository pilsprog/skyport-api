package skyport.api.example;

/**
 * 
 * A testgame that starts two Randomplayers and a graphical
 * interface and connects it to the game server.
 * 
 * @author Bjarte
 *
 */
public class TestGame {

    public static void main(String[] args) throws InterruptedException {
    	
    	// Instantiates a graphic interface
        MockGraphicsClient graphics = new MockGraphicsClient("localhost", 54331);
        
        // Instantiates two Random Players
        RandomPlayer daneel = new RandomPlayer("Daneel", "localhost", 54321);
        RandomPlayer giskard = new RandomPlayer("Giskard", "localhost", 54321);

        //Starts everything
        Thread g = new Thread(graphics);
        Thread b1 = new Thread(daneel);
        Thread b2 = new Thread(giskard);

        g.start();
        b1.start();
        b2.start();

        g.join();
        b1.join();
        b2.join();
    }
}

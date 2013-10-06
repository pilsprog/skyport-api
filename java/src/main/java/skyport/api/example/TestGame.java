package skyport.api.example;

public class TestGame {

    public static void main(String[] args) throws InterruptedException {
        MockGraphicsClient graphics = new MockGraphicsClient("localhost", 54331);
        RandomPlayer daneel = new RandomPlayer("Daneel", "localhost", 54321);
        RandomPlayer giskard = new RandomPlayer("Giskard", "localhost", 54321);

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

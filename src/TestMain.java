
import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

public class TestMain {
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new TestGame(), new Dimension(900, 900), false);
        game.start();
    }
}

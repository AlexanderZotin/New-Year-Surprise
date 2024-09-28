package surprise.model;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

public class Animation implements Animatable {
    private final NewYearTree newYearTree;
    private final StarScy starScy;
    private final GreetingText greetingText;

    public Animation() {
        newYearTree = new NewYearTree();
        starScy = new StarScy();
        greetingText = new GreetingText();
    }

    @Override
    public void start(Runnable repaintAction) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                starScy.forEachStar(StarScy::regenerateIfNeed);
                starScy.forEachStar(current -> {
                    for (int j = 0; j < current[1].length; j++) {
                        current[1][j] += 2;
                    }
                });
                repaintAction.run();
            }
        }, 0, 100);
        greetingText.start(repaintAction);
    }

    @Override
    public void paint(Graphics g) {
        newYearTree.paint(g);
        starScy.paint(g);
        greetingText.paint(g);
    }
}

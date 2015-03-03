/**
 * @author Aphanel
 */
package ForestWorldLibrary;
import java.io.FileNotFoundException;
import static common.misc.ConsoleUtils.*;

public class main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        //send output to swing component instead of console
        enableSwing();
        //create a lumberjack
        LumberJack lumberjack = new LumberJack(EntityNames.ent_JackLumber);
        
        //simply run the lumberjack through a few Update calls
        for (int i = 0; i < 50; ++i) {
            lumberjack.Update();
            Thread.sleep(800);
        }

        //wait for a keypress (enter) before exiting
        PressAnyKeyToContinue();
    }
}

/**
 * @author Aphanel
 */
package ForestWorld.LumberJackOwnedStates;

import ForestWorldLibrary.LumberJack;
import ForestWorldLibrary.State;
import ForestWorldLibrary.location_type;
import static ForestWorldLibrary.EntityNames.GetNameOfEntity;
import static common.misc.ConsoleUtils.*;
import static common.windows.*;

//------------------------------------------------------------------------
//
//  Lumberjack will go home and sleep until his fatigue is decreased
//  sufficiently
//------------------------------------------------------------------------
public class GoHomeAndSleepTilRested extends State {

    static final GoHomeAndSleepTilRested instance = new GoHomeAndSleepTilRested();

    private GoHomeAndSleepTilRested() {
    }

    //copy ctor and assignment should be private
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    static public GoHomeAndSleepTilRested Instance() {
        return instance;
    }

    @Override
    public void Enter(LumberJack pLumberjack) {
        if (pLumberjack.Location() != location_type.cabin) {
            SetTextColor(FOREGROUND_RED| FOREGROUND_INTENSITY);
            cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": " + "Walkin' back to my cabin");
            pLumberjack.ChangeLocation(location_type.cabin);
        }
    }

    @Override
    public void Execute(LumberJack pLumberjack) {
        //if lumberjack is not fatigued start to cut more trees.
        if (!pLumberjack.Fatigued()) {
            SetTextColor(FOREGROUND_RED| FOREGROUND_INTENSITY); 
            cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": "
                    + "What a great fa.. heu, Nap! Time to cut down some more trees !");

            pLumberjack.ChangeState(EnterForestAndCutWood.Instance());
        } else {
            //sleep
            pLumberjack.DecreaseFatigue();
            SetTextColor(FOREGROUND_RED| FOREGROUND_INTENSITY);
            cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": " + "ZZZZ... ");
        }
    }

    @Override
    public void Exit(LumberJack pLumberjack) {
        SetTextColor(FOREGROUND_RED| FOREGROUND_INTENSITY);
        cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": " + "Leaving the cabin");
    }
}

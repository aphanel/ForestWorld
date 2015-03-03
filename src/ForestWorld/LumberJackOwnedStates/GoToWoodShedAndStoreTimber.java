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
//  Entity will go to a woodshed and store all the timber his sledge is carrying. If the 
//  lumberjack is subsequently happy enough with his amount of timber stored, he'll walk home, 
//  otherwise he'll keep going to get more timber.
//------------------------------------------------------------------------
public class GoToWoodShedAndStoreTimber extends State {

    static final GoToWoodShedAndStoreTimber instance = new GoToWoodShedAndStoreTimber();

    private GoToWoodShedAndStoreTimber() {
    }

    //copy ctor and assignment should be private
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    //this is a singleton
    static public GoToWoodShedAndStoreTimber Instance() {
        return instance;
    }

    @Override
    public void Enter(LumberJack pLumberjack) {
        //on entry the lumberjack makes sure he is located at the woodshed
        if (pLumberjack.Location() != location_type.woodshed) {
            SetTextColor(FOREGROUND_RED | FOREGROUND_INTENSITY);
            cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": " + "Goin' to the woodshed on my sledge.");

            pLumberjack.ChangeLocation(location_type.woodshed);
        }
    }

    @Override
    public void Execute(LumberJack pLumberjack) {

        //store the timber
        pLumberjack.AddToWoodShed(pLumberjack.TimberCarried());

        pLumberjack.SetTimberCarried(0);

        SetTextColor(FOREGROUND_RED | FOREGROUND_INTENSITY);
        cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": "
                + "Storing Timber. Total amount of Timber now: " + pLumberjack.TotalWoodInShed());

        //Happy enough to have a well earned rest?
        if (pLumberjack.TotalWoodInShed() >= LumberJack.ComfortLevel) {
            SetTextColor(FOREGROUND_RED | FOREGROUND_INTENSITY);
            cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": "
                    + "WooHoo! That's enough for the day. A man must not live to"
                    + "work but work to live!");

            pLumberjack.ChangeState(GoHomeAndSleepTilRested.Instance());
        } //otherwise get more timber
        else {
            pLumberjack.ChangeState(EnterForestAndCutWood.Instance());
        }
    }

    @Override
    public void Exit(LumberJack pLumberjack) {
        SetTextColor(FOREGROUND_RED | FOREGROUND_INTENSITY);
        cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": " + "Leavin' the woodshed on my sledge !"
                + "How cool that is right ?");
    }
}

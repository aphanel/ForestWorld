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
//  lumberjack changes location to the well and drinks water until
//  his thirst is quenched. When satisfied he returns to the forest
//  and resumes his quest for timbers.
//------------------------------------------------------------------------
class QuenchThirst extends State {

    static final QuenchThirst instance = new QuenchThirst();

    private QuenchThirst() {
    }

    //copy ctor and assignment should be private
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    static public QuenchThirst Instance() {
        return instance;
    }

    @Override
    public void Enter(LumberJack pLumberjack) {
        if (pLumberjack.Location() != location_type.well) {
            pLumberjack.ChangeLocation(location_type.well);

            SetTextColor(FOREGROUND_RED| FOREGROUND_INTENSITY);
            cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": "
                    + "Geez, I am so thirsty man ! Walking to the well");
        }
    }

    @Override
    public void Execute(LumberJack pLumberjack) {
        if (pLumberjack.Thirsty()) {
            pLumberjack.DrinkFromTheWell();

            SetTextColor(FOREGROUND_RED| FOREGROUND_INTENSITY);
            cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": "
                    + "Water is THE BEST ! Nah, just kidding, I'd kill for a beer right now...");

            pLumberjack.ChangeState(EnterForestAndCutWood.Instance());
        } else {
            SetTextColor(FOREGROUND_RED | FOREGROUND_INTENSITY);
            cout("\nERROR!\nERROR!\nERROR!");
        }
    }

    @Override
    public void Exit(LumberJack pMiner) {
        SetTextColor(FOREGROUND_RED| FOREGROUND_INTENSITY);
        cout("\n" + GetNameOfEntity(pMiner.ID()) + ": "
                + "Leaving the well, feelin' good, not thirsty nor drunk.");
    }
}
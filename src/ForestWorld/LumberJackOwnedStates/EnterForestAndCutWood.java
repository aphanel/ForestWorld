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
//  In this state the lumberjack will walk to the forest and cut down some
//  trees. If the lumberjack's sledge is full, he'll change state
//  to GoToWoodShedAndStoreTimber. If he gets thirsty he'll change state
//  to QuenchThirst
//------------------------------------------------------------------------
public class EnterForestAndCutWood extends State {

    static final EnterForestAndCutWood instance = new EnterForestAndCutWood();

    private EnterForestAndCutWood() {
    }

    //copy ctor and assignment should be private
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    //this is a singleton
    public static EnterForestAndCutWood Instance() {
        return instance;
    }

    @Override
    public void Enter(LumberJack pLumberjack) {
        //if the lumberjack is not already located at the forest, he must
        //change location to the forest
        if (pLumberjack.Location() != location_type.forest) {
            SetTextColor(FOREGROUND_RED | FOREGROUND_INTENSITY);
            cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": " + "Walkin' to the forest");

            pLumberjack.ChangeLocation(location_type.forest);
        }
    }

    @Override
    public void Execute(LumberJack pLumberjack) {
        //the lumberjack cuts down trees for timber until his slede is full. 
        //If he gets thirsty during his woodcuting, he packs up work for a while and 
        //changes state to QuenchThirst.
        pLumberjack.AddToTimberCarried(1);

        pLumberjack.IncreaseFatigue();

        SetTextColor(FOREGROUND_RED | FOREGROUND_INTENSITY);
        cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": " + "One tree down ! One more Timber in my sledge !");

        //if enough timber, go and put it in the woodshed
        if (pLumberjack.SledgeFull()) {
            pLumberjack.ChangeState(GoToWoodShedAndStoreTimber.Instance());
        }

        if (pLumberjack.Thirsty()) {
            pLumberjack.ChangeState(QuenchThirst.Instance());
        }
    }

    @Override
    public void Exit(LumberJack pLumberjack) {
        SetTextColor(FOREGROUND_RED| FOREGROUND_INTENSITY);
        cout("\n" + GetNameOfEntity(pLumberjack.ID()) + ": "
                + "Ah! I'm leavin' the forest with my sledge full of timber !");
    }
};
/**
 * A class defining a lumberjack.
 * 
 * @author Aphanel
 */
package ForestWorldLibrary;

import ForestWorld.LumberJackOwnedStates.GoHomeAndSleepTilRested;

public class LumberJack extends BaseGameEntity {
    //the amount of timber a Lumberjack must have before he feels comfortable for the day
    final public static int ComfortLevel = 5;
    //the amount of timber a lumberjack can carry on his sledge
    final public static int MaxTimber = 3;
    //above this value a lumberjack is thirsty
    final public static int ThirstLevel = 5;
    //above this value a lumberjack is sleepy
    final public static int TirednessThreshold = 5;
    
    private State m_pCurrentState;
    private location_type m_Location;
    //how many timber the lumberjack has on his sledge
    private int m_iTimberCarried;
    private int m_iTimberInWoodShed;
    //the higher the value, the thirstier the lumberjack
    private int m_iThirst;
    //the higher the value, the more tired the lumberjack
    private int m_iFatigue;

    public LumberJack(EntityNames id) {
        super(id);
        m_Location = location_type.cabin;
        m_iTimberCarried = 0;
        m_iTimberInWoodShed = 0;
        m_iThirst = 0;
        m_iFatigue = 0;
        m_pCurrentState = GoHomeAndSleepTilRested.Instance();
    }

//--------------------------- ChangeState -------------------------------------
    //this method changes the current state to the new state. It first
    //calls the Exit() method of the current state, then assigns the
    //new state to m_pCurrentState and finally calls the Entry()
    //method of the new state.
//-----------------------------------------------------------------------------
    public void ChangeState(State pNewState) {
        //make sure both states are both valid before attempting to 
        //call their methods
        assert m_pCurrentState != null : pNewState;

        //call the exit method of the existing state
        m_pCurrentState.Exit(this);

        //change state to the new state
        m_pCurrentState = pNewState;

        //call the entry method of the new state
        m_pCurrentState.Enter(this);
    }

//-----------------------------------------------------------------------------
    public void AddToTimberCarried(int val) {
        m_iTimberCarried += val;

        if (m_iTimberCarried < 0) {
            m_iTimberCarried = 0;
        }
    }

//-----------------------------------------------------------------------------
    public void AddToWoodShed(int val) {
        m_iTimberInWoodShed += val;

        if (m_iTimberInWoodShed < 0) {
            m_iTimberInWoodShed = 0;
        }
    }

//-----------------------------------------------------------------------------
    public boolean Thirsty() {
        if (m_iThirst >= ThirstLevel) {
            return true;
        }

        return false;
    }

//-----------------------------------------------------------------------------
    @Override
    public void Update() {
        m_iThirst += 1;

        if (m_pCurrentState != null) {
            m_pCurrentState.Execute(this);
        }
    }

//-----------------------------------------------------------------------------
   public boolean Fatigued() {
        if (m_iFatigue > TirednessThreshold) {
            return true;
        }

        return false;
    }

    public location_type Location() {
        return m_Location;
    }

    public void ChangeLocation(location_type loc) {
        m_Location = loc;
    }

    public int TimberCarried() {
        return m_iTimberCarried;
    }

    public void SetTimberCarried(int val) {
        m_iTimberCarried = val;
    }

    public boolean SledgeFull() {
        return m_iTimberCarried >= MaxTimber;
    }

    public void DecreaseFatigue() {
        m_iFatigue -= 1;
    }

    public void IncreaseFatigue() {
        m_iFatigue += 1;
    }

    public int TotalWoodInShed() {
        return m_iTimberInWoodShed;
    }

    public void SetTotalWoodInShed(int val) {
        m_iTimberInWoodShed = val;
    }

    public void DrinkFromTheWell() {
        m_iThirst = 0;
    }
};
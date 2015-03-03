/**
 * abstract base class to define an interface for a state
 * 
 * @author Aphanel
 */
package ForestWorldLibrary;

public abstract class State {

  @Override
  public void finalize() throws Throwable{ super.finalize();}

  //this will execute when the state is entered
  abstract public void Enter(LumberJack lumberjack);

  //this is the state's normal update function
  abstract public void Execute(LumberJack lumberjack);

  //this will execute when the state is exited.
  abstract public void Exit(LumberJack lumberjack);
}

/**
 *
 * @author Aphanel
 */
package ForestWorldLibrary;

public enum EntityNames {

    ent_JackLumber(0),
    ent_Dog(1);

    final public int id;

    EntityNames(int id) {
        this.id = id;
    }

    public static String GetNameOfEntity(EntityNames e) {
        return e.toString();
    }
    
    @Override
    public String toString() {
        return EntityNames.GetNameOfEntity(this.id);
    }
    
    static public String GetNameOfEntity(int n) {
        switch (n) {
            case 0:
                return "Jack Lumber";
            case 1:
                return "Dog";
            default:
                return "UNKNOWN!";
        }
    }
}

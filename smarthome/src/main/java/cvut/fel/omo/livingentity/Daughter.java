package cvut.fel.omo.livingentity;

import cvut.fel.omo.utilities.Constants;
/**
 * Represent a daughter. Inherits from the Person class.
 */
public class Daughter extends Person{
    /**
     * Constructor of the Daughter class.
     *
     * @param name The name of the daughter.
     */
    public Daughter(String name) {
        super(name);
        this.maxRepairCapability = Constants.MAX_DAUGHTER_REPAIR_CAPABILITY;
    }
}

package cvut.fel.omo.livingentity;

import cvut.fel.omo.utilities.Constants;
/**
 * Represent a son. Inherits from the Person class.
 */
public class Son extends Person{
    /**
     * Constructor of the Son class.
     *
     * @param name The name of the son.
     */
    public Son(String name) {
        super(name);
        this.maxRepairCapability = Constants.MAX_SON_REPAIR_CAPABILITY;
    }
}

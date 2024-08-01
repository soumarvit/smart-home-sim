package cvut.fel.omo.livingentity;

import cvut.fel.omo.utilities.Constants;
/**
 * Represent a mum. Inherits from the Person class.
 */
public class Mum extends Person{
    /**
     * Constructor of the Mum class.
     *
     * @param name The name of the mum.
     */
    public Mum(String name) {
        super(name);
        this.maxRepairCapability = Constants.MAX_MUM_REPAIR_CAPABILITY;
    }

}

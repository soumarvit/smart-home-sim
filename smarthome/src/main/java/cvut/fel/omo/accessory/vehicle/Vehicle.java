package cvut.fel.omo.accessory.vehicle;

import cvut.fel.omo.report.Activity;
import cvut.fel.omo.accessory.Accessory;
import cvut.fel.omo.livingentity.LivingEntity;

/**
 * Extends the functionality of {@link Accessory}.
 */
public class Vehicle extends Accessory {

    public Vehicle(String name){
        this.name = name;
    }

    @Override
    public void use(LivingEntity user) {
        new Activity(user.getName() + " is being active", user, this.name);
        this.isInUse = true;
    }

    @Override
    public void stopUsing(LivingEntity user) {
        new Activity(user.getName() + " stopped being active", user, this.name);
        this.isInUse = false;
    }
}

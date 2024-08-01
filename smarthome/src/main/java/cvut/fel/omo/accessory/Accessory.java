package cvut.fel.omo.accessory;

import cvut.fel.omo.livingentity.LivingEntity;

import java.util.Optional;

/**
 * Class that provides abstract use() and stopUsing() methods.
 * In other words an implementation of a usable object.
 */
public abstract class Accessory {
    protected String name;
    protected Optional<LivingEntity> user = Optional.empty();
    protected boolean isInUse = false;

    /**
     * Use this accessory.
     * @param user The one who wants to use this accessory.
     */
    public abstract void use(LivingEntity user);

    /**
     * Stop using this accessory.
     * @param user The one who was previously using this accessory and decided not to use it anymore.
     */
    public abstract void stopUsing(LivingEntity user);

    public String getName(){
        return name;
    }

    public boolean isInUse() {
        return isInUse;
    }
}

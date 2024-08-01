package cvut.fel.omo.livingentity;

import cvut.fel.omo.utilities.AppLogger;
import cvut.fel.omo.accessory.Accessory;
import cvut.fel.omo.house.Room;

import java.util.Optional;
/**
 * Represents a living entity (person, animal) living in the house
 */
public abstract class LivingEntity {
    protected String name;
    private Room currentRoom;
    protected int busyCountdown = 0;
    protected boolean isBusy = false;
    protected Optional<Accessory> currentAccessory = Optional.empty();

    /**
     * Constructor of the LivingEntity class.
     *
     * @param name The name of the living entity.
     */
    public LivingEntity( String name) {
        this.name = name;
    }
    /**
     * Sets the current accessory and uses the specified accessory.
     *
     * @param accessory The accessory to be used.
     */
    public void useAccessory(Accessory accessory){
        currentAccessory = Optional.of(accessory);
        busyCountdown = 1;
        isBusy = true;
        accessory.use(this);
    }
    /**
     * Method for updating the simulation state of the living entity.
     */
    public void simulationUpdate(){
        if(isBusy && busyCountdown == 0){
            isBusy = false;
            currentAccessory.ifPresent(accessory -> accessory.stopUsing(this));
        }
        if (busyCountdown > 0) busyCountdown--;
    }
    /**
     * Moves the living entity to the specified room.
     *
     * @param room The room to move to.
     */
    public void goToRoom(Room room) {
        this.currentRoom = room;
        AppLogger.logger.config(this.name + " went to " + room.getName() + '\n');
    }
    /**
     * Returns the name of the living entity.
     *
     * @return The name of the living entity.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the living entity is busy.
     *
     * @return true if the living entity is busy; otherwise, false.
     */
    public boolean isBusy() {
        return isBusy;
    }

    /**
     * Returns the countdown value indicating the remaining busy time for the living entity.
     *
     * @return The busy countdown value.
     */
    public int getBusyCountdown() {
        return this.busyCountdown;
    }
}

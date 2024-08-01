package cvut.fel.omo.house;

import cvut.fel.omo.accessory.devicecontroller.BlindsDeviceController;
/**
 * Represents a window in a room with a blinds device controller.
 */
public class Window {
    private final BlindsDeviceController blindsDeviceController;

    /**
     * Constructor of the blind device controller
     *
     * @param blindsDeviceController The blinds device controller associated with the window.
     */
    public Window(BlindsDeviceController blindsDeviceController) {
        this.blindsDeviceController = blindsDeviceController;
    }

    public BlindsDeviceController getBlindsDeviceController() {
        return blindsDeviceController;
    }
}

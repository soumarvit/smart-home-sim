package cvut.fel.omo.livingentity;

import cvut.fel.omo.accessory.devicecontroller.device.Device;
/**
 * Defines the contract for handling device repair requests using the Chain of Responsibility pattern.
 */
public interface RepairChainOfResponsibilityHandler {
    /**
     * Sets the next repair handler in the chain.
     *
     * @param handler The next repair handler to set.
     */
    void setNextRepairHandler(RepairChainOfResponsibilityHandler handler);

    /**
     * Handles the repair of the specified device.
     * Chain of responsibility handle function
     *
     * @param device The device to be repaired.
     */
    void repairDevice(Device device);
}

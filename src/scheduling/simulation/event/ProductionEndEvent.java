package scheduling.simulation.event;

import scheduling.core.input.*;
import scheduling.core.output.ProductionInstruction;
import scheduling.simulation.Event;
import scheduling.simulation.Simulator;

import java.util.Map;

public class ProductionEndEvent extends Event {
    private ProductionInstruction instruction;

    public ProductionEndEvent(int date, ProductionInstruction instruction) {
        super(date);
        this.instruction = instruction;
    }

    public ProductionInstruction getInstruction() {
        return instruction;
    }

    /**
     * End a production.
     * (1) Increase the inventory of the produced item.
     * @param simulator the simulator.
     */
    @Override
    public void trigger(Simulator simulator) {
        Production production = instruction.getProduction();
        Item item = production.getItem();
        Plant plant = production.getPlant();
        int quantity = instruction.getQuantity();

        simulator.getState().addInventory(item, plant, quantity);
    }

    @Override
    public String toString() {
        return "<ProdEnd: " + instruction.toString() + ">";
    }
}

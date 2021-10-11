package lld.parkinglot.interaction.commands;

import lld.parkinglot.exceptions.InvalidParameterException;
import lld.parkinglot.handler.ParkingLotCommandHandler;

public class SlotNumberForRegistrationNumberCommand implements Command {
    private ParkingLotCommandHandler parkingLotCommandHandler;

    public SlotNumberForRegistrationNumberCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "slot_number_for_registration_number <registration_number>";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if(params.length < 1) {
            throw new InvalidParameterException("Expected one parameter <registration_number>");
        }

        String color = params[0];
        parkingLotCommandHandler.getSlotNumberForRegistrationNumber(color);
    }
}

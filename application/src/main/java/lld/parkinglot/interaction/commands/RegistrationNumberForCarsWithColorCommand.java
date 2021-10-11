package lld.parkinglot.interaction.commands;

import lld.parkinglot.exceptions.InvalidParameterException;
import lld.parkinglot.handler.ParkingLotCommandHandler;

public class RegistrationNumberForCarsWithColorCommand implements Command {
    private ParkingLotCommandHandler parkingLotCommandHandler;

    public RegistrationNumberForCarsWithColorCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "registration_numbers_for_cars_with_colour <color>";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if(params.length < 1) {
            throw new InvalidParameterException("Expected one parameter <color>");
        }

        String color = params[0];
        parkingLotCommandHandler.getRegistrationNumbersByColor(color);
    }
}

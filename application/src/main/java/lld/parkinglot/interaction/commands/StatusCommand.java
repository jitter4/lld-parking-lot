package lld.parkinglot.interaction.commands;

import lld.parkinglot.handler.ParkingLotCommandHandler;
import lld.parkinglot.exceptions.InvalidParameterException;

public class StatusCommand implements Command {
    private ParkingLotCommandHandler parkingLotCommandHandler;

    public StatusCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "status";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        this.parkingLotCommandHandler.status();
    }
}

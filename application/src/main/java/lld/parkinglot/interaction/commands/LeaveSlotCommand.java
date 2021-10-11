package lld.parkinglot.interaction.commands;

import lld.parkinglot.exceptions.InvalidParameterException;
import lld.parkinglot.handler.ParkingLotCommandHandler;
import lld.parkinglot.utils.StringUtils;

public class LeaveSlotCommand implements Command {
    private ParkingLotCommandHandler parkingLotCommandHandler;

    public LeaveSlotCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "leave <slot_number>";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if(params.length < 1) {
            throw new InvalidParameterException("Expected one parameter <slot_number>");
        }

        if(!StringUtils.isInteger(params[0])) {
            throw new InvalidParameterException("slotNumber must be an integer");
        }

        Integer slotNumber = Integer.valueOf(params[0]);
        parkingLotCommandHandler.leaveSlot(slotNumber);
    }
}

package lld.parkinglot.interaction.commands;


import lld.parkinglot.handler.ParkingLotCommandHandler;
import lld.parkinglot.exceptions.InvalidParameterException;
import lld.parkinglot.utils.StringUtils;

public class CreateLotCommand implements Command {
    private ParkingLotCommandHandler parkingLotCommandHandler;

    public CreateLotCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
       return  "create_parking_lot <numSlots>";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if(params.length < 1) {
            throw new InvalidParameterException("Expected one parameter <numSlots>");
        }

        if(!StringUtils.isInteger(params[0])) {
            throw new InvalidParameterException("numSlots must be an integer");
        }

        int numSlots = Integer.parseInt(params[0]);
        this.parkingLotCommandHandler.createParkingLot(numSlots);
    }
}

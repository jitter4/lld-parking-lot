package lld.parkinglot.exceptions;

import lld.parkinglot.utils.MessageConstants;

public class ParkingLotFullException extends RuntimeException {
    @Override
    public String getMessage() {
        return MessageConstants.PARKING_LOT_FULL_MSG;
    }
}

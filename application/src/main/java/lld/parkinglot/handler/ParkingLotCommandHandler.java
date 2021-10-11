package lld.parkinglot.handler;

import lld.parkinglot.domain.ParkingSlot;
import lld.parkinglot.exceptions.ParkingLotFullException;
import lld.parkinglot.domain.Car;
import lld.parkinglot.domain.ParkingLot;
import lld.parkinglot.domain.Ticket;
import lld.parkinglot.utils.StringUtils;
import lld.parkinglot.utils.MessageConstants;

import java.util.List;
import java.util.Optional;

public class ParkingLotCommandHandler {
    private ParkingLot parkingLot;

    public void createParkingLot(int numSlots) {
        if(isParkingLotCreated()) {
            System.out.println(MessageConstants.PARKING_LOT_ALREADY_CREATED);
            return;
        }

        try {
            parkingLot = new ParkingLot(numSlots);
            System.out.println(String.format(MessageConstants.PARKING_LOT_CREATED_MSG, parkingLot.getNumSlots()));
        } catch (IllegalArgumentException ex) {
            System.out.println("Bad input: " + ex.getMessage());
        }
    }

    public void park(String registrationNumber, String color) {
        if(!isParkingLotCreated()) {
            System.out.println(MessageConstants.PARKING_LOT_NOT_CREATED);
            return;
        }

        if (parkingLot.isCarWithRegistrationNumberParked(registrationNumber)) {
            System.out.println(MessageConstants.DUPLICATE_VEHICLE_MESSAGE);
            return;
        }

        try {
            Car car = new Car(registrationNumber, color);
            Ticket ticket = parkingLot.reserveSlot(car);
            System.out.println(String.format(MessageConstants.PARKING_SLOT_ALLOCATED_MSG, ticket.getSlotNumber()));
        } catch (IllegalArgumentException ex) {
            System.out.println("Bad input: " + ex.getMessage());
        } catch (ParkingLotFullException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void status() {
        if(!isParkingLotCreated()) {
            System.out.println(MessageConstants.PARKING_LOT_NOT_CREATED);
            return;
        }

        System.out.println(MessageConstants.SLOT_NO + "    " + MessageConstants.REGISTRATION_NO + "    " + MessageConstants.Color);
        parkingLot.getOccupiedSlots().forEach(parkingSlot -> {
            System.out.println(
                    StringUtils.rightPadSpaces(Integer.toString(parkingSlot.getSlotNumber()), MessageConstants.SLOT_NO.length()) + "    " +
                    StringUtils.rightPadSpaces(parkingSlot.getCar().getRegistrationNumber(), MessageConstants.REGISTRATION_NO.length()) + "    " +
                    parkingSlot.getCar().getColor());
        });
    }

    public void leaveSlot(int slotNumber) {
        if(!isParkingLotCreated()) {
            System.out.println(MessageConstants.PARKING_LOT_NOT_CREATED);
            return;
        }

        ParkingSlot slot = parkingLot.leaveSlot(slotNumber);
        if (slot != null)
            System.out.println(String.format("Slot number %s is free", slot.getSlotNumber()));
    }

    public void getRegistrationNumbersByColor(String color) {
        if(!isParkingLotCreated()) {
            System.out.println(MessageConstants.PARKING_LOT_NOT_CREATED);
            return;
        }

        List<String> registrationNumbers = parkingLot.getRegistrationNumbersByColor(color);
        System.out.println(String.join(", ", registrationNumbers));
    }

    public void getSlotNumbersByColor(String color) {
        if(!isParkingLotCreated()) {
            System.out.println(MessageConstants.PARKING_LOT_NOT_CREATED);
            return;
        }

        List<Integer> slotNumbers = parkingLot.getSlotNumbersByColor(color);
        slotNumbers.forEach(slotNumber -> System.out.print(slotNumber + ", "));
    }

    public void getSlotNumberForRegistrationNumber(String registrationNumber) {
        if(!isParkingLotCreated()) {
            System.out.println(MessageConstants.PARKING_LOT_NOT_CREATED);
            return;
        }

        Optional<Integer> slotNumber = parkingLot.getSlotNumberByRegistrationNumber(registrationNumber);
        if (slotNumber.isPresent())
            System.out.println(slotNumber.get());
        else
            System.out.println("Not found");

    }



    private boolean isParkingLotCreated() {
        if(parkingLot == null) {
            return false;
        }
        return true;
    }
}

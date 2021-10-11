package lld.parkinglot.domain;

import lld.parkinglot.exceptions.ParkingLotFullException;
import lld.parkinglot.exceptions.SlotNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingLot {
    private final int numSlots;
    private final int numFloors;
    private SortedSet<ParkingSlot> availableSlots = new TreeSet<>();
    private Set<ParkingSlot> occupiedSlots = new HashSet<>();

    public ParkingLot(int numSlots) {
        if(numSlots <= 0) {
            throw new IllegalArgumentException("Number of slots in the Parking Lot must be greater than zero.");
        }

        // Assuming Single floor since only numSlots are specified in the input.
        this.numSlots = numSlots;
        this.numFloors = 1;

        for(int i = 0; i < numSlots; i++) {
            ParkingSlot parkingSlot = new ParkingSlot(i+1, 1);
            this.availableSlots.add(parkingSlot);
        }
    }

    public synchronized Ticket reserveSlot(Car car) {
        if(car == null) {
            throw new IllegalArgumentException("Car must not be null");
        }

        if(this.isFull()) {
            throw new ParkingLotFullException();
        }

        ParkingSlot nearestSlot = this.availableSlots.first();

        nearestSlot.reserve(car);
        this.availableSlots.remove(nearestSlot);
        this.occupiedSlots.add(nearestSlot);

        return new Ticket(nearestSlot.getSlotNumber(), car.getRegistrationNumber(), car.getColor());
    }

    public ParkingSlot leaveSlot(int slotNumber) {
        ParkingSlot parkingSlot = this.occupiedSlots
                .stream()
                .filter(slot -> slot.getSlotNumber() == slotNumber)
                .findAny()
                .orElseThrow(() -> new SlotNotFoundException(slotNumber));
        if (parkingSlot != null) {
            parkingSlot.clear();
            this.occupiedSlots.remove(parkingSlot);
            this.availableSlots.add(parkingSlot);
        }
        return parkingSlot;
    }

    public boolean isFull() {
        return this.availableSlots.isEmpty();
    }

    public List<String> getRegistrationNumbersByColor(String color) {
        return this.occupiedSlots
                .stream()
                .map(ParkingSlot::getCar)
                .filter(car -> color.equals(car.getColor()))
                .map(Car::getRegistrationNumber)
                .collect(Collectors.toList());
    }

    public List<Integer> getSlotNumbersByColor(String color) {
        return this.occupiedSlots
                .stream()
                .filter(parkingSlot -> color.equals(parkingSlot.getCar().getColor()))
                .map(ParkingSlot::getSlotNumber)
                .collect(Collectors.toList());
    }

    public Optional<Integer> getSlotNumberByRegistrationNumber(String registrationNumber) {
        return this.occupiedSlots
                .stream()
                .filter(parkingSlot -> registrationNumber.equals(parkingSlot.getCar().getRegistrationNumber()))
                .map(ParkingSlot::getSlotNumber)
                .findAny();
    }


    public int getNumSlots() {
        return numSlots;
    }

    public int getNumFloors() {
        return numFloors;
    }

    public SortedSet<ParkingSlot> getAvailableSlots() {
        return availableSlots;
    }

    public Set<ParkingSlot> getOccupiedSlots() {
        return occupiedSlots;
    }

    public boolean isCarWithRegistrationNumberParked(String registrationNumber) {
        return this.occupiedSlots
                .stream()
                .anyMatch(parkingSlot ->
                        registrationNumber.equals(parkingSlot.getCar().getRegistrationNumber()));
    }
}

package lld.parkinglot.interaction.command;

import lld.parkinglot.exceptions.InvalidParameterException;
import lld.parkinglot.handler.ParkingLotCommandHandler;
import lld.parkinglot.interaction.commands.SlotNumbersForCarsWithColorCommand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SlotNumbersForCardWithColorCommandTests {
    private static ParkingLotCommandHandler parkingLotCommandHandler;
    private static SlotNumbersForCarsWithColorCommand slotNumbersForCardWithColorCommand;

    @BeforeAll
    public static void createCommand() {
        parkingLotCommandHandler = new ParkingLotCommandHandler();
        slotNumbersForCardWithColorCommand = new SlotNumbersForCarsWithColorCommand(parkingLotCommandHandler);
    }

    @Test
    public void executeWithNoArg_shouldThrowError() {
        String[] params = {};
        assertThrows(InvalidParameterException.class, () -> slotNumbersForCardWithColorCommand.execute(params));
    }

    @Test
    public void executeWithValidArgs_shouldWork() {
        String[] params = {"RED"};
        assertDoesNotThrow(() -> slotNumbersForCardWithColorCommand.execute(params));
    }
}
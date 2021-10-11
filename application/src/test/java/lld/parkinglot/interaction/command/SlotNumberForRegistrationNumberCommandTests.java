package lld.parkinglot.interaction.command;

import lld.parkinglot.exceptions.InvalidParameterException;
import lld.parkinglot.handler.ParkingLotCommandHandler;
import lld.parkinglot.interaction.commands.SlotNumberForRegistrationNumberCommand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SlotNumberForRegistrationNumberCommandTests {
    private static ParkingLotCommandHandler parkingLotCommandHandler;
    private static SlotNumberForRegistrationNumberCommand slotNumberForRegistrationNumberCommand;

    @BeforeAll
    public static void createCommand() {
        parkingLotCommandHandler = new ParkingLotCommandHandler();
        slotNumberForRegistrationNumberCommand = new SlotNumberForRegistrationNumberCommand(parkingLotCommandHandler);
    }

    @Test
    public void executeWithNoArg_shouldThrowError() {
        String[] params = {};
        assertThrows(InvalidParameterException.class, () -> slotNumberForRegistrationNumberCommand.execute(params));
    }

    @Test
    public void executeWithValidArgs_shouldWork() {
        String[] params = {"KA"};
        assertDoesNotThrow(() -> slotNumberForRegistrationNumberCommand.execute(params));
    }
}
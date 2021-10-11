package lld.parkinglot.interaction.command;

import lld.parkinglot.exceptions.InvalidParameterException;
import lld.parkinglot.handler.ParkingLotCommandHandler;
import lld.parkinglot.interaction.commands.ParkCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkCommandTests {
    private static ParkingLotCommandHandler parkingLotCommandHandler;
    private static ParkCommand parkCommand;

    @BeforeAll
    public static void createCommand() {
        parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkCommand = new ParkCommand(parkingLotCommandHandler);
    }

    @Test
    public void executeWithNoArg_shouldThrowError() {
        String[] params = {};
        assertThrows(InvalidParameterException.class, () -> parkCommand.execute(params));
    }

    @Test
    public void executeWithoutTwoArgs_shouldThrowError() {
        String[] params = {"Foo"};
        assertThrows(InvalidParameterException.class, () -> parkCommand.execute(params));
    }

    @Test
    public void executeWithValidArgs_shouldWork() {
        String[] params = {"KA-01-HQ-4669", "White"};
        Assertions.assertDoesNotThrow(() -> parkCommand.execute(params));
    }
}

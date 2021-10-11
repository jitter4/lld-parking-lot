package lld.parkinglot.interaction.command;

import lld.parkinglot.exceptions.InvalidParameterException;
import lld.parkinglot.handler.ParkingLotCommandHandler;
import lld.parkinglot.interaction.commands.RegistrationNumberForCarsWithColorCommand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegistrationNumberForCardWithColorCommandTests {
    private static ParkingLotCommandHandler parkingLotCommandHandler;
    private static RegistrationNumberForCarsWithColorCommand registrationNumberForCardWithColorCommand;

    @BeforeAll
    public static void createCommand() {
        parkingLotCommandHandler = new ParkingLotCommandHandler();
        registrationNumberForCardWithColorCommand = new RegistrationNumberForCarsWithColorCommand(parkingLotCommandHandler);
    }

    @Test
    public void executeWithNoArg_shouldThrowError() {
        String[] params = {};
        assertThrows(InvalidParameterException.class, () -> registrationNumberForCardWithColorCommand.execute(params));
    }

    @Test
    public void executeWithValidArgs_shouldWork() {
        String[] params = {"RED"};
        assertDoesNotThrow(() -> registrationNumberForCardWithColorCommand.execute(params));
    }
}
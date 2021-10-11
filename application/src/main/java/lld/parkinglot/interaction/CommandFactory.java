package lld.parkinglot.interaction;

import lld.parkinglot.handler.ParkingLotCommandHandler;
import lld.parkinglot.exceptions.CommandNotFoundException;
import lld.parkinglot.exceptions.InvalidParameterException;
import lld.parkinglot.interaction.commands.Command;
import lld.parkinglot.interaction.commands.CreateLotCommand;
import lld.parkinglot.interaction.commands.LeaveSlotCommand;
import lld.parkinglot.interaction.commands.ParkCommand;
import lld.parkinglot.interaction.commands.RegistrationNumberForCarsWithColorCommand;
import lld.parkinglot.interaction.commands.SlotNumberForRegistrationNumberCommand;
import lld.parkinglot.interaction.commands.SlotNumbersForCarsWithColorCommand;
import lld.parkinglot.interaction.commands.StatusCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private Map<String, Command> commands;

    private CommandFactory() {
        commands = new HashMap<>();
    }

    public static CommandFactory init(ParkingLotCommandHandler parkingLotCommandHandler) {
        final CommandFactory cf = new CommandFactory();

        cf.addCommand("create_parking_lot", new CreateLotCommand(parkingLotCommandHandler));
        cf.addCommand("park", new ParkCommand(parkingLotCommandHandler));
        cf.addCommand("status", new StatusCommand(parkingLotCommandHandler));
        cf.addCommand("leave", new LeaveSlotCommand(parkingLotCommandHandler));
        cf.addCommand("registration_numbers_for_cars_with_colour",
                new RegistrationNumberForCarsWithColorCommand(parkingLotCommandHandler));
        cf.addCommand("slot_numbers_for_cars_with_colour",
                new SlotNumbersForCarsWithColorCommand(parkingLotCommandHandler));
        cf.addCommand("slot_number_for_registration_number",
                new SlotNumberForRegistrationNumberCommand(parkingLotCommandHandler));

        return cf;
    }

    public void addCommand(String name, Command command) {
        commands.put(name, command);
    }

    public void executeCommand(String name, String[] params) throws CommandNotFoundException, InvalidParameterException {
        if(commands.containsKey(name)) {
            commands.get(name).execute(params);
        } else {
            throw new CommandNotFoundException(name);
        }
    }

    public void listCommandHelp() {
        commands.keySet().stream()
                .map(command -> commands.get(command).helpText())
                .forEach(System.out::println);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}

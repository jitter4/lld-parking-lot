package lld.parkinglot.interaction.commands;

import lld.parkinglot.exceptions.InvalidParameterException;

public interface Command {
    String helpText();
    void execute(String[] params) throws InvalidParameterException;
}

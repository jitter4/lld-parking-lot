package lld.parkinglot.client;

import lld.parkinglot.interaction.CommandFactory;

import java.io.BufferedReader;

public class CLIClient extends Client {
    public CLIClient(BufferedReader inputReader, CommandFactory commandFactory) {
        super(inputReader, commandFactory);
    }
}

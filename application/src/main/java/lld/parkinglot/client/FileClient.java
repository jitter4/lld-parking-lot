package lld.parkinglot.client;

import lld.parkinglot.interaction.CommandFactory;

import java.io.BufferedReader;

public class FileClient extends Client {
    public FileClient(BufferedReader inputReader, CommandFactory commandFactory) {
        super(inputReader, commandFactory);
    }
}

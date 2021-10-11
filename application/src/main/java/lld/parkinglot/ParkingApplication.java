package lld.parkinglot;

import lld.parkinglot.client.Client;
import lld.parkinglot.client.ClientFactory;
import lld.parkinglot.handler.ParkingLotCommandHandler;
import lld.parkinglot.interaction.CommandFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ParkingApplication {
    public static void main(String[] args) {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        CommandFactory commandFactory = CommandFactory.init(parkingLotCommandHandler);

        try {
            Client client = ClientFactory.buildClient(args, commandFactory);
            client.handleInput();
        } catch (FileNotFoundException ex) {
            System.out.println("Sorry! the supplied input file was not found!");
        } catch (IOException ex) {
            System.out.println("Something went wrong. Please try again!");
        }
    }
}
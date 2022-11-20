package com.forg;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        //Building building = new Building(10);
        ElevatorHandler handler = new ElevatorHandler();
        for (int i = 0; i < 3; i++) {
            ElevatorHandler.elevators.add(new Elevator());
        }
        System.out.println(ElevatorHandler.elevators);
        handler.freeElevator().move(2);
        System.out.println(ElevatorHandler.elevators);
        handler.freeElevator().move(5);
        System.out.println(ElevatorHandler.elevators);
        handler.freeElevator().move(10);
        Thread.sleep(2000);
        System.out.println(ElevatorHandler.elevators);
        Thread.sleep(5000);
        System.out.println(ElevatorHandler.elevators);
        handler.freeElevator().move(5);
        System.out.println(ElevatorHandler.elevators);
    }
}

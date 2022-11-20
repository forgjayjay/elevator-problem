package com.forg;

public class App 
{
    public static void main( String[] args )
    {
        //Building building = new Building(10);
        ElevatorHandler handler = new ElevatorHandler();
        for (int i = 0; i < 3; i++) {
            ElevatorHandler.elevators.add(new Elevator());
        }
        try {
            System.out.println(ElevatorHandler.elevators);
            
            handler.freeElevator(2).move(2);
            System.out.println(ElevatorHandler.elevators);
            handler.freeElevator(5).move(5);
            System.out.println(ElevatorHandler.elevators);
            handler.freeElevator(10).move(10);
            Thread.sleep(2000);
            System.out.println(ElevatorHandler.elevators);
            Thread.sleep(5000);
            System.out.println(ElevatorHandler.elevators);
            System.out.println(handler.freeElevator(5).move(5));
            System.out.println(handler.freeElevator(5));
            System.out.println(ElevatorHandler.elevators);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

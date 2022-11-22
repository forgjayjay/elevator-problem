package com.forg;

import java.util.ArrayList;

public class ElevatorHandler {
    public static ArrayList<Elevator> elevators = new ArrayList<>();
    public ArrayList<Elevator> availableElevators = new ArrayList<>();

    public ElevatorHandler(){

    }

    public boolean insertElevator(Elevator elev){
        return elevators.add(elev);
    }

    public Elevator freeElevator(int floor, Direction direction) {
        Elevator returnElevator = null;
        int currentClosest = Integer.MAX_VALUE;
        for (Elevator elevator : elevators) {
            if(direction==elevator.getCurrentDirection()||!elevator.getActive()){
                if(availableElevators.contains(elevator)==false) 
                    {
                        availableElevators.add(elevator);
                    }
            }
        }
        for (Elevator elevator : availableElevators) {
            int newClosest = Math.abs(elevator.getCurrentFloor()-floor);
            if(newClosest < currentClosest) {
                returnElevator = elevator;
            }
        }
        availableElevators.clear();
        returnElevator.addToQueue(floor);
        return returnElevator;
    }
    public Elevator elevatorOnFloor(int floor) {
        Elevator returnElevator = null;
        for (Elevator elevator : elevators) {
            if(elevator.getCurrentFloor() == floor){
                returnElevator = elevator;
                break;
            }
        }   
        return returnElevator;
    }
}

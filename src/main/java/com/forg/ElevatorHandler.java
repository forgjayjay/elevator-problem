package com.forg;

import java.util.ArrayList;

public class ElevatorHandler {
    public static ArrayList<Elevator> elevators = new ArrayList<>();


    public ElevatorHandler(){

    }

    public boolean insertElevator(Elevator elev){
        return elevators.add(elev);
    }

    public Elevator freeElevator() {
        Elevator returnElevator = null;
        for (Elevator elevator : elevators) {
            if(!elevator.getActive()){
                returnElevator = elevator;
                break;
            }
        }   
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

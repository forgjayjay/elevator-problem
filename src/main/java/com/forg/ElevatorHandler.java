package com.forg;

import java.util.ArrayList;

public class ElevatorHandler {
    public static ArrayList<Elevator> elevators = new ArrayList<>();


    public ElevatorHandler(){

    }

    public boolean insertElevator(Elevator elev){
        return elevators.add(elev);
    }

    public Elevator freeElevator(int floor) {
        Elevator returnElevator = null;
        int currentClosest = Integer.MAX_VALUE;
        for (Elevator elevator : elevators) {
            int newClosest = Math.abs(elevator.getCurrentFloor()-floor);
            if(newClosest < currentClosest&&!elevator.getActive()){
                currentClosest = newClosest;
                if(!elevator.getActive()||currentClosest == 0) return elevator;
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

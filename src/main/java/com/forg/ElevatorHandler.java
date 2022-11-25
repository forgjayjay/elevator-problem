package com.forg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ElevatorHandler {
    public static ArrayList<Elevator> elevators = new ArrayList<>();
    public ArrayList<Elevator> availableElevators = new ArrayList<>();
    public static Map<Integer, Direction> floorQueue = new HashMap<>();

    public ElevatorHandler(){

    }

    public boolean insertElevator(Elevator elev){
        return elevators.add(elev);
    }

    public Elevator freeElevator(int floor, Direction direction) {
        Elevator returnElevator = null;
        int currentClosest = Integer.MAX_VALUE;
        for (Elevator elevator : elevators) {
            if(!elevator.getActive()||direction==elevator.getCurrentDirection()){
                if(availableElevators.contains(elevator)==false) 
                    {
                        availableElevators.add(elevator);
                    }
            }
        }
        for (Elevator elevator : availableElevators) {
            int newClosest = Math.abs(elevator.getCurrentFloor()-floor);
            if(newClosest < currentClosest) {
                currentClosest = newClosest;
                returnElevator = elevator;
            }
        }
        if(availableElevators.isEmpty()){
            floorQueue.put(floor, direction);
            //System.out.println("queued elevator");
            return null;
        }
        //System.out.println("available: "+availableElevators);
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
    public static void notifyFreeElevator(){
        ElevatorHandler handler = new ElevatorHandler();
        System.out.println(floorQueue);
        if(!floorQueue.isEmpty()){
            Entry<Integer, Direction> floor = floorQueue.entrySet().iterator().next();
            handler.freeElevator(floor.getKey(), floor.getValue());
        }
        
    }
}

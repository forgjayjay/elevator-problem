package com.forg;

import java.util.ArrayList;
import java.util.Collections;

public class Elevator {
    
    boolean active = false;
    int currentFloor = 0;
    Direction currentDirection = Direction.UP;
    ArrayList<Integer> floorQueue = new ArrayList<>();
    int floor;
    String name;
    public Elevator(String name){
        this.name = "Elevator"+name;
    }

    public void addToQueue(int floor){
        floorQueue.add(floor);
        Collections.sort(floorQueue);
        this.floor = floorQueue.get(0);
        move();
    }
    public boolean move(){
        boolean moving = false;
        if(active){
            System.out.println(name + " requested to move to floor:  " + floorQueue.get(1) + " but is already active and moving to floor: " + floor);
            return moving;
        }
        active = true;
        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(name+" is moving to new floor: " + floor);
                    int difference = floor-currentFloor;
                    if(difference>=0){
                        currentDirection = Direction.UP;
                        for (int i = 0; i < difference; i++) {
                            Thread.sleep(500);
                            currentFloor++;
                        }
                    }else {
                        currentDirection = Direction.DOWN;
                        for (int i = Math.abs(difference); i > 0; i--) {
                            Thread.sleep(500);
                            currentFloor--;
                        }
                    }
                    Thread.sleep(200);
                    currentFloor = floor;
                    Thread.sleep(600);
                    removeFromQueue();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();
        return moving;
    }
    private void removeFromQueue(){
        active = false;
        //System.out.println(name+" before check: "+floorQueue);
        floorQueue.remove(0);
        try{
            if(floorQueue.iterator().hasNext()){
                //System.out.println(name+" after check: "+floorQueue);
                this.floor = floorQueue.get(0);
                move();
            } else{
                //System.out.println("Handler notified: " + name);
                ElevatorHandler.notifyFreeElevator();
            }
        }catch(IndexOutOfBoundsException e){
            //System.out.println("Handler notified with exception: " + name);
            ElevatorHandler.notifyFreeElevator();
        }
    }
    // public synchronized boolean move(int floor, Direction direction) throws InterruptedException{
    //     final int difference = Math.abs(floor-currentFloor);
        
    //     Iterator<Integer> iter = floorQueue.iterator();
    //     boolean moving = false;
    //     System.out.println(floorQueue);
    //     while(iter.hasNext()){
    //         floor = iter.next();
    //         if(currentFloor != floor){
    //             currentFloor=floorQueue.get(0);
    //             moving = true;
    //             active = true;
    //             Thread thread = new Thread() {
    //                 @Override
    //                 public void run() {
    //                     try {
    //                         //System.out.println(ElevatorHandler.elevators);
    //                         Thread.sleep(500*difference);
    //                         active = false;
    //                     } catch (InterruptedException e) {
    //                         System.out.println(e.getMessage());
    //                     }
    //                 }
    //             };
    //             thread.start();
    //         }
    //     }
    //     return moving; 
    // }
        public String getName() {
            return name;
        }
    public int getCurrentFloor() {
        return currentFloor;
    }
    public boolean getActive(){
        return active;
    }
    public Direction getCurrentDirection() {
        return currentDirection;
    }
    @Override
    public String toString() {
        return "[ Elevator: "+name + "; Active: " + active+ "; Current floor: " + currentFloor + "; " + currentDirection +  " ]";
    }
    @Override
    public boolean equals(Object obj) {
        return name.equals(((Elevator) obj).getName());
    }
}

package com.forg;

public class Elevator {
    
    boolean active = false;
    int currentFloor = 0;
    
    public Elevator(){

    }

    public synchronized boolean move(int floor) throws InterruptedException{
        final int difference = Math.abs(floor-currentFloor);
        boolean moving = false;
        if(currentFloor!= floor){
            currentFloor=floor;
            moving = true;
            active = true;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        //System.out.println(ElevatorHandler.elevators);
                        Thread.sleep(500*difference);
                        active = false;
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            };
            thread.start();
        }
        return moving; 
    }

    public int getCurrentFloor() {
        return currentFloor;
    }
    public boolean getActive(){
        return active;
    }

    @Override
    public String toString() {
        return "[ Active: " + active+ "; Current floor: " + currentFloor + " ]";
    }
}

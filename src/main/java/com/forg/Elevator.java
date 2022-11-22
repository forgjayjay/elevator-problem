package com.forg;

import java.util.ArrayList;
import java.util.Collections;

public class Elevator {
    
    boolean active = false;
    int currentFloor = 0;
    Direction currentDirection = Direction.UP;
    ArrayList<Integer> floorQueue = new ArrayList<>();
    int floor;
    public Elevator(){

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
            try {
                Thread.sleep(500*Math.abs(floor-currentFloor)+10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    active = true;
                    int difference = floor-currentFloor;
                    if(difference>0){
                        currentDirection = Direction.UP;
                        for (int i = 0; i < difference; i++) {
                            Thread.sleep(500);
                            currentFloor++;
                            System.out.println(currentFloor);
                        }
                    }else {
                        currentDirection = Direction.DOWN;
                        for (int i = Math.abs(difference); i > 0; i--) {
                            Thread.sleep(500);
                            currentFloor--;
                            System.out.println(currentFloor);
                        }
                    }
                    currentFloor = floor;
                    active = false;
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
        System.out.println(floorQueue);
        try{
                if(floorQueue.iterator().hasNext()){
                floorQueue.remove(0);
                this.floor = floorQueue.get(0);
                move();
            }
        }catch(IndexOutOfBoundsException e){

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
        return "[ Active: " + active+ "; Current floor: " + currentFloor + " ]";
    }
}

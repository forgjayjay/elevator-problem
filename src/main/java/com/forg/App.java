package com.forg;

public class App 
{
    public static void main( String[] args )
    {
        //Building building = new Building(10);
        ElevatorHandler handler = new ElevatorHandler();
        for (int i = 0; i < 3; i++) {
            ElevatorHandler.elevators.add(new Elevator(Integer.toString(i)));
        }
        try {
            new Thread(){
                public void run() {
                    while(true){
                        try {
                            System.out.println(ElevatorHandler.elevators);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                
            }.start();
            handler.freeElevator(2, Direction.UP);
            //elev.addToQueue(2);
            handler.freeElevator(10, Direction.UP);
            //elev.addToQueue(10);
            handler.freeElevator(5, Direction.DOWN);
            //elev.addToQueue(5);
            // elev = handler.freeElevator(5, Direction.UP);
            //// elev.addToQueue(5);
            // Thread.sleep(5000);
            // System.out.println(ElevatorHandler.elevators);
            // // elev = System.out.println(handler.freeElevator(5, Direction.DOWN).move());
            // // elev = System.out.println(handler.freeElevator(5, Direction.DOWN));
            // elev = handler.freeElevator(5, Direction.DOWN);
            //// elev.addToQueue(5);
            // System.out.println(ElevatorHandler.elevators);
            // Thread.sleep(2000);
            // System.out.println(ElevatorHandler.elevators);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

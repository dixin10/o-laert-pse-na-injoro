package Control;

import BoardObjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {

    private Paddle user;
    private Boolean isRunning;
    private List<Building> buildings;
    private List<Person> persons;

    private int ApartmentHeight, ApartmentWidth, MedicalHeight, MedicalWidth;


    public GameBoard() {
        this.user = new Paddle((GameUI.canvasWidth - 200) / 2, 780);
        isRunning = false;
        buildings = new ArrayList<>();
        persons =new ArrayList<>();

        ApartmentHeight = 30;
        ApartmentWidth = 30;
        MedicalHeight = 30;
        MedicalWidth = 30;
        addBuildings();
        addPersons();
    }

    public void addBuildings() {
        for (double i = 0.5; i < 5; i++) {
            buildings.add(new MedicalFacility((int) (i * 140 + 35), 100, ApartmentWidth, ApartmentWidth));
        }
        for (int i = 0; i < 6; i++) {
            buildings.add(new Home(i * 140 + 35, 200, MedicalHeight, MedicalHeight));
        }
    }


    public void addPersons(){
        for (int i = 0; i < 20; i++) {
            persons.add(new Person(300, 400, new Random().nextInt() % 2));
        }
    }

    public void update(int maxX,int maxY){
        for (Person p:persons) {
            p.move(maxX,maxY);
        }

        for (Person x:persons) {
            if(!x.isOutside())continue;

            for(Building b: buildings){
               Collision collision=new Collision(b,x);
               if(collision.isCollision()) {
                   b.evaluate(x);
               }
            }
            Collision collision=new Collision(user,x);
            if(collision.isCollision()) {
                user.evaluate(x);
            }

        }
    }




    public List<Person> getPersons() {
        return persons;
    }

    public List<Building> getBuildings(){
        return buildings;
    }

    public Paddle getPaddle(){
        return user;
    }

    public void start(){
        isRunning=true;
    }

    public void stop(){
        isRunning=false;
    }

    public Boolean isRunning() {
        return isRunning;
    }
}

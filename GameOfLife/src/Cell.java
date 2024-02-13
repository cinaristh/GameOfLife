public class Cell {
    private Boolean alive;
    private Boolean nextAlive;


    public Cell(Boolean a) {
        alive = a;
        nextAlive=true;

    }

    public Boolean isAlive(){
        return alive;
    }

    public Integer getAlive(){
        if (alive ==true){
            return 1;}
        else{return 0;

        }

    }
    public Boolean Alive(){return alive;}

    public void setAlive(Boolean a){
        alive = a;
    }

    public void setNextAlive(Boolean b){nextAlive=b;}

    public void update(){alive=nextAlive;}

}




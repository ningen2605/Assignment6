package townmap.assignment6;

import java.util.ArrayList;

public class Town implements Comparable <Town>{
    private String name;
    private ArrayList<Town> adjTowns;

    /**
     * constructor creates name of town
     * @param name name to assign to field name
     */
    public Town(String name) {
        this.name = name;
        adjTowns = new ArrayList<Town>();

    }
    /**
     * copy constructor
     * @param templateTown instance of Town
     */
    public Town(Town templateTown) {
        this(templateTown.getName());
        setAdjacentTowns(templateTown.getAdjacentTowns());
    }
    /**
     * adds a town in the list of adjacent towns
     * @param town town to be added to the list
     */
    public void addAdjacentTowns(Town town) {
        adjTowns.add(town);
    }
    /**
     * @return reference to list of adjacent towns of a town
     */
    public  ArrayList<Town> getAdjacentTowns() {
        return adjTowns;
    }

    /**
     * sets the list of adjacent towns
     * @param towns arrayList of adjacent towns
     */
    public void setAdjacentTowns(ArrayList<Town> towns) {

        for(int i=0;i<towns.size();i++) {
            adjTowns.add(towns.get(i));
        }
    }

    /**
     * sets name of town
     * @param name name of town
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     *
     * @return name of town
     */
    public String getName() {
        return name;
    }

    /**
     * compares 2 towns. returns 0 if they have the same name, a negative number or positive number if they are not equal
     */
    @Override
    public int compareTo(Town town) {
        if(town.getName().equalsIgnoreCase(name)) {
            return 0;
        }
        else {
            return name.compareToIgnoreCase(town.getName());
        }
    }
    /**
     * @return hashCode of name of town
     */
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * @return information on a town in a string format
     */
    public String toString() {
        String town="";
        for(int i=0; i<adjTowns.size(); i++) {
            town += adjTowns.get(i).getName()+" ";
        }
        return "Name of Town: "+name +"\t Adjacent Towns: "+ town+"\n";
    }

}

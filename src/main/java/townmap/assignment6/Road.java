package townmap.assignment6;

public class Road implements Comparable<Road> {
    private Town town1;
    private Town town2;
    private int weight;
    private String name;

    /**
     * constructor creating a new road, which is an edge
     * @param  source town
     * @param destination second town
     * @param weight the cost/distance from one town to another
     * @param roadName the name of the road
     */
    public Road(Town source, Town destination, int weight, String roadName) {
    this.town1 = source;
    this.town2 = destination;
    this.weight = weight;
    this.name = roadName;
    }

    /**
     * set default weight of 1 constructor
     * @param  source town
     * @param destination second town
     * @param roadName the name of the road
     */
    public Road(Town source, Town destination,  String roadName) {
        this.town1 = source;
        this.town2 = destination;
        this.weight = 1;
        this.name = roadName;
    }
    /**
     * sets first town of road
     * @param town1 first town
     */
    public void setSource(Town town1)
    {
        this.town1 = town1;
    }
    /**
     * returns first town on the road
     * @return first town
     */
    public Town getSource()
    {
        return town1;
    }

    /**
     * sets the weight of road
     * @param distance weight of road
     */
    public void setDistance(int distance){
        this.weight = distance;
    }
    /**
     * sets second town of road
     * @param town2 second town
     */
    public void setDestination(Town town2) {
        this.town2 = town2;
    }
    /**
     * returns second town
     * @return second Town
     */
    public Town getDestination() {
        return town2;
    }
    /**
     * returns the weight(distance) of road
     * @return weight
     */
    public int getDistance(){
        return weight;
    }
    /**
     * sets name of road
     * @param name name of road
     */
    public String setName(String name){
        return this.name = name;
    }
    /**
     * returns name of road
     * @return name of road
     */
    public String getName() {
        return name;

    }

    /**
     * Returns true only if the edge contains the given town
     * @param town vertex of the graph
     * @return true only if the edge contains the given vertex
     */
    public boolean contains(Town town) {
        return ((town.getName().equalsIgnoreCase(town1.getName()))
                || (town.getName().equalsIgnoreCase(town2.getName())));
    }

    /**
     * @return 0 if the road names are the same, a positive or negative number if the road names are not the same
     */
    @Override
    public int compareTo(Road r) {
        return name.compareToIgnoreCase(r.getName());
    }
    /**
     * Returns true if each of the ends of the road r is the same as the ends of this road.
     * Remember that a road that goes from point A to point B is the same as a road that goes from point B to point A.
     * @param r road object to compare it to
     * @return true if each of the ends of the road r is the same as the ends of this road
     */
    @Override
    public boolean equals(Object r) {
        if(!(r instanceof Road)) {
            return false;
        }
        else {
            String rTown1=((Road) r).getSource().getName();
            String rTown2=((Road) r).getDestination().getName();
            if((town1.getName().equalsIgnoreCase(rTown1)||
                    town1.getName().equalsIgnoreCase(rTown2))&&
                    (town2.getName().equalsIgnoreCase(rTown1)||
                            town2.getName().equalsIgnoreCase(rTown2))) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}

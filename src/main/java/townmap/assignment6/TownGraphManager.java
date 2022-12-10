package townmap.assignment6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TownGraphManager implements TownGraphManagerInterface {
    Graph graph;

    public TownGraphManager() {
        graph = new Graph();
    }

    /**
     * Adds a road with 2 towns and a road name
     *
     * @param town1    name of town 1 (lastname, firstname)
     * @param town2    name of town 2 (lastname, firstname)
     * @param weight
     * @param roadName name of road
     * @return
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Set<Town> towns = graph.vertexSet();
        Town town = null;
        Town town0 = null;
        for (Town t : towns) {
            if (t.getName().equalsIgnoreCase(town1)) {
                town = t;
            }
            if (t.getName().equalsIgnoreCase(town2)) {
                town0 = t;
            }
            if (town != null && town0 != null) {
                break;
            }
        }
        Road r = graph.addEdge(town, town0, weight, roadName);
        if (r == null) {
            return false;
        }
        return true;
    }

    /**
     * Returns the name of the road that both towns are connected through
     *
     * @param town1 name of town 1
     * @param town2 name of town 2
     * @return name of road if town 1 and town2 are in the same road, returns null if not
     */
    @Override
    public String getRoad(String town1, String town2) {
        Set<Town> towns = graph.vertexSet();
        Town town = null;
        Town town0 = null;
        for (Town t : towns) {
            if (t.getName().equalsIgnoreCase(town1)) {
                town = t;
            }
            if (t.getName().equalsIgnoreCase(town2)) {
                town0 = t;
            }
            if (town != null && town0 != null) {
                break;
            }
        }
        Road r = graph.getEdge(town, town0);
        if (r == null) {
            return null;
        }
        return r.getName();
    }

    /**
     * Adds a town to the graph
     *
     * @param v the town's name
     * @return true if the town was successfully added, false if not
     */
    @Override
    public boolean addTown(String v) {
        Town town = new Town(v);
        return graph.addVertex(town);
    }

    /**
     * Gets a town with a given name
     *
     * @param name the town's name
     * @return the Town specified by the name, or null if town does not exist
     */
    @Override
    public Town getTown(String name) {
        Set<Town> towns = new HashSet<Town>(graph.vertexSet());
        Iterator<Town> it = towns.iterator();
        while (it.hasNext()) {
            Town t = it.next();
            if (t.getName().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Determines if a town is already in the graph
     *
     * @param v the town's name
     * @return true if the town is in the graph, false if not
     */
    @Override
    public boolean containsTown(String v) {
        Town t = new Town(v);
        return graph.containsVertex(t);
    }

    /**
     * Determines if a road is in the graph
     *
     * @param town1 name of town 1
     * @param town2 name of town 2
     * @return true if the road is in the graph, false if not
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        Town town = new Town(town1);
        Town town0 = new Town(town2);
        return graph.containsEdge(town, town0);
    }

    /**
     * Creates an arraylist of all road titles in sorted order by road name
     *
     * @return an arraylist of all road titles in sorted order by road name
     */
    @Override
    public ArrayList<String> allRoads() {
        Set<Road> roads = graph.edgeSet();
        ArrayList<String> allRoads = new ArrayList<String>();
        for (Road road : roads) {
            allRoads.add(road.getName());
        }
        Collections.sort(allRoads);
        return allRoads;
    }

    /**
     * Deletes a road from the graph
     *
     * @param town1 name of town 1
     * @param town2 name of town 2
     * @param road  the road name
     * @return true if the road was successfully deleted, false if not
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        Road roadY;
        Town town = new Town(town1);
        Town town0 = new Town(town2);
        Road roadX = graph.getEdge(town, town0);
        roadY = graph.removeEdge(town, town0, roadX.getDistance(), road);
        if (roadY != null) {
            return true;
        }
        return false;
    }

    /**
     * Deletes a town from the graph
     *
     * @param v name of town
     * @return true if the town was successfully deleted, false if not
     */
    @Override
    public boolean deleteTown(String v) {
        Town town = new Town(v);
        return graph.removeVertex(town);
    }

    /**
     * Creates an arraylist of all towns in alphabetical order
     *
     * @return an arraylist of all towns in alphabetical order
     */
    @Override
    public ArrayList<String> allTowns() {
        Set<Town> towns = graph.vertexSet();
        ArrayList<String> allTowns = new ArrayList<String>();
        for (Town town : towns) {
            allTowns.add(town.getName());
        }
        Collections.sort(allTowns);
        return allTowns;
    }

    /**
     * Returns the shortest path from town 1 to town 2
     *
     * @param town1 name of town 1
     * @param town2 name of town 2
     * @return an Arraylist of roads connecting the two towns together, null if the
     * towns have no path to connect them.
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        Set<Town> towns = graph.vertexSet();
        Set<Road> roads = graph.edgeSet();
        boolean foundSource = false;
        boolean foundDest = false;
        Town town = null;
        Town town0 = null;
        ArrayList<String> path = new ArrayList<String>();
        try {
            for (Town t : towns) {
                if (t.getName().equalsIgnoreCase(town1)) {
                    town = t;
                }
                if (t.getName().equalsIgnoreCase(town2)) {
                    town0 = t;
                }
                if (town != null && town0 != null) {
                    break;
                }
            }

            for (Road r : roads) {
                if (r.getSource().compareTo(town) == 0 || r.getDestination().compareTo(town) == 0) {
                    foundSource = true;
                }
                if (r.getSource().compareTo(town0) == 0 || r.getDestination().compareTo(town0) == 0) {
                    foundDest = true;
                }
                if (foundSource == true && foundDest == true) {
                    path = graph.shortestPath(town, town0);
                    break;
                }
            }
        } catch (NullPointerException n) {

        }

        return path;
    }

    /**
     * reads list of towns from a file and add to graph
     *
     * @param selectedFile file to read from
     * @throws FileNotFoundException thrown when no file if found
     * @throws IOException otherwise
     */
    public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(selectedFile);
        Town town1, town2;
        String line = "";
        String[] splitLine;
        while (in.hasNext()) {
            line = in.nextLine();
            splitLine = line.split("[,;]");
            town2 = new Town(splitLine[3]);
            town1 = new Town(splitLine[2]);
            graph.addVertex(town1);
            graph.addVertex(town2);
            graph.addEdge(town1, town2, Integer.parseInt(splitLine[1]), splitLine[0]);

        }
    }
}

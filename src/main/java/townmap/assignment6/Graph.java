package townmap.assignment6;

import java.util.*;

public class Graph implements GraphInterface<Town, Road> {
    ArrayList<Town> towns;
    static ArrayList<LinkedList> edges;
    Map<Town,Town> previousNode = new HashMap<>();

    /**
     * default constructor to create an object of graph
     */
    public Graph() {
        towns = new ArrayList<Town>();
        edges = new ArrayList<LinkedList>();
    }
    /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null returns null
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @return an edge connecting source vertex to target vertex.
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        Road road = new Road(sourceVertex, destinationVertex, "r");
        int sourceIndex = 0, destinationIndex = 0;
        boolean foundSource= false, foundDestination = false;
        if(sourceVertex == null || destinationVertex == null) {
            return null;
        }

        for(int i=0;i<towns.size();i++) {
            if(towns.get(i).compareTo(sourceVertex)==0) {
                sourceIndex=i;
                foundSource=true;
            }
            if(towns.get(i).compareTo(destinationVertex)==0) {
                destinationIndex = i;
                foundDestination = true;
            }
            if(foundSource==true && foundDestination==true) {
                break;
            }
        }

        ListIterator<Road> roadListIterator = edges.get(sourceIndex).listIterator(0);
        while(roadListIterator.hasNext()) {
            Road r = roadListIterator.next();
            if(road.equals(r)) {
                return r;
            }
        }
        return null;
    }
    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge.
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        int sourceIndex=0, destinationIndex=0;
        boolean foundSource=false, foundDest=false;

        if(sourceVertex==null || destinationVertex==null) {
            throw new NullPointerException();
        }
        Road edge=new Road(sourceVertex, destinationVertex,weight,description);

        for(int i=0;i<towns.size();i++) {
            if(towns.get(i).compareTo(sourceVertex) == 0) {
                sourceIndex = i;
                foundSource=true;
            }
            if(towns.get(i).compareTo(destinationVertex) == 0) {
                destinationIndex = i;
                foundDest = true;
            }
            if(foundSource == true&&foundDest == true) {
                break;
            }
        }

        if(foundSource==false||foundDest==false) {
            throw new IllegalArgumentException();
        }
        else {
            if(!edges.get(destinationIndex).contains(edge)&& !edges.get(sourceIndex).contains(edge)) {
                edges.get(sourceIndex).add(edge);
                edges.get(destinationIndex).add(edge);
                sourceVertex.addAdjacentTowns(destinationVertex);
                destinationVertex.addAdjacentTowns(sourceVertex);
                return edge;
            }

        }
        return null;
    }

    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false.
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
    @Override
    public boolean addVertex(Town v) {
        for(int i = 0;i < towns.size(); i++) {
            if(towns.get(i).compareTo(v) == 0) {
                return false;
            }
        }
        towns.add(v);
        if(edges.size()==0) {
            edges.add(new LinkedList<Road>());
        }
        else {
            edges.add(new LinkedList<Road>());
        }
        return true;
    }

    /**
     * Returns true if the graph contains an edge
     * going from source vertex to target vertex
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        int sourceIndex=0, destinationIndex=0;
        boolean foundSource=false, foundDest=false;
        for(int i=0;i<towns.size();i++) {
            if(towns.get(i).compareTo(sourceVertex)==0) {
                sourceIndex=i;
                foundSource=true;
            }
            if(towns.get(i).compareTo(destinationVertex)==0) {
                destinationIndex=i;
                foundDest=true;
            }
            if(foundSource==true&&foundDest==true) {
                break;
            }
        }

        if(foundSource==true&&foundDest==true) {
            ListIterator<Road> it=edges.get(sourceIndex).listIterator(0);
            while(it.hasNext()) {
                Road r =it.next();
                if(r.contains(sourceVertex)&&r.contains(destinationVertex)) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Returns true if this graph contains the specified vertex
     * @param town vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex
     */
    @Override
    public boolean containsVertex(Town town) {
        for(int i=0;i<towns.size();i++) {
            if(towns.get(i).compareTo(town)==0) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set.
     *
     * @return a set of the edges contained in this graph.
     */
    @Override
    public Set<Road> edgeSet() {
        Set<Road> roads=new HashSet<Road>();
        for(int i=0;i<edges.size();i++) {
            ListIterator<Road> it=edges.get(i).listIterator(0);
            while(it.hasNext()) {
                Road r=it.next();
                if(!roads.contains(r)) {
                    roads.add(r);
                }
            }
        }
        return roads;
    }
    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
    @Override
    public Set<Road> edgesOf(Town vertex) {
        int index = 0;
        Set<Road> roads = new HashSet<Road>();
        for(int i=0;i<towns.size();i++) {
            if(towns.get(i).compareTo(vertex)==0) {
                index=i;
            }
        }
        ListIterator<Road> it = edges.get(index).listIterator(0);
        while(it.hasNext()) {
            Road r=it.next();
            if(!roads.contains(r)) {
                roads.add(r);
            }
        }
        return roads;
    }
    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph.
     *
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        int sourceIndex=0, destinationIndex=0;
        Road road=new Road(sourceVertex, destinationVertex,weight,description);
        if(containsEdge(sourceVertex,destinationVertex)) {
            for(int i=0;i<towns.size();i++) {
                boolean foundSource = false;
                if(towns.get(i).compareTo(sourceVertex)==0) {
                    sourceIndex=i;
                    foundSource=true;
                }
                boolean foundDest = false;
                if(towns.get(i).compareTo(destinationVertex)==0) {
                    destinationIndex=i;
                    foundDest=true;
                }
                if(foundSource==true&&foundDest==true) {
                    break;
                }
            }

            ListIterator<Road> it=edges.get(sourceIndex).listIterator(0);
            ListIterator<Road> it2=edges.get(destinationIndex).listIterator(0);
            while(it.hasNext()) {
                Road road2=it.next();
                if(road2.equals(road)) {
                    if(edges.get(sourceIndex).remove(road2)) {
                        break;
                    }
                }
            }

            while(it2.hasNext()) {
                Road road2=it2.next();
                if(road2.equals(road)) {
                    if(edges.get(destinationIndex).remove(road2)) {
                        break;
                    }
                }
            }

            return road;
        }

        return null;
    }

    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present.
     * If the specified vertex is null returns false.
     *
     * @param town vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
    @Override
    public boolean removeVertex(Town town) {
        int townIndex=0;
        if(containsVertex(town)) {
            for(int i=0;i<towns.size();i++) {
                if(towns.get(i).compareTo(town)==0) {
                    townIndex=i;
                    break;
                }
            }
            for(int i=0;i<town.getAdjacentTowns().size();i++) {
                Road r=getEdge(town.getAdjacentTowns().get(i),town);
                if(containsEdge(town.getAdjacentTowns().get(i),town)) {
                    removeEdge(town.getAdjacentTowns().get(i),town,r.getDistance(),r.getName());
                }
            }
            towns.remove(townIndex);
            edges.remove(townIndex);
            return true;
        }
        return false;
    }
    /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set.
     *
     * @return a set view of the vertices contained in this graph.
     */
    @Override
    public Set<Town> vertexSet() {
        Set<Town> town=new HashSet<Town>();
        for(int i=0;i<towns.size();i++) {
            if(!town.contains(towns.get(i))) {
                town.add(towns.get(i));
            }
        }
        return town;
    }
    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     */
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        ArrayList<String> path=new ArrayList<String>();
        String edgeName;
        String path1="";
        dijkstraShortestPath(sourceVertex);
        Town currentTown = destinationVertex;
        Town previousTown=previousNode.get(destinationVertex);
        while(currentTown.compareTo(sourceVertex)!=0) {
            edgeName=getEdge(currentTown,previousTown).getName();
            path1=previousTown.getName()+" via "+edgeName+" to "+ currentTown.getName()+ " "+getEdge(currentTown,previousTown).getDistance()+" mi";
            path.add(0,path1);
            currentTown=previousTown;
            previousTown=previousNode.get(currentTown);

        }
        return path;
    }

    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        Map<Town, Integer>pathWeight=new HashMap<>();
        Set<Town> visitedTown=new HashSet<>();
        Comparator<Road> comparator=new Comparator<Road>() {
            @Override
            public int compare(Road r1, Road r2) {
                return r1.getDistance()-r2.getDistance();
            }
        };
        PriorityQueue<Road> minPQ=new PriorityQueue<>(comparator);
        int edgeDist,newDist;

        minPQ.addAll(sortEdges(sourceVertex)); //add all edges connected to sourceVertex to Priority Queue
        visitedTown.add(sourceVertex); // add starting vertex to visited town list
        pathWeight.put(sourceVertex, 0);

        while(!minPQ.isEmpty()) {
            Town town1,town2;
            Road smallR= minPQ.remove();
            town1=smallR.getSource();
            town2=smallR.getDestination();
            if(!visitedTown.contains(town1)) {
                visitedTown.add(town1);
                for(Road r:sortEdges(town1)) {
                    if(!minPQ.contains(r)) {
                        minPQ.add(r);
                    }
                }
                previousNode.put(town1, town2);
                edgeDist=pathWeight.get(town2);
                newDist=edgeDist+smallR.getDistance();
                pathWeight.put(town1, newDist);
            }
            if(!visitedTown.contains(town2)){
                visitedTown.add(town2);
                for(Road r:sortEdges(town2)) {
                    if(!minPQ.contains(r)) {
                        minPQ.add(r);
                    }
                }
                previousNode.put(town2, town1);
                edgeDist=pathWeight.get(town1);
                newDist=edgeDist+smallR.getDistance();
                pathWeight.put(town2, newDist);

            }
            if(visitedTown.contains(town1)) {
                edgeDist=pathWeight.get(town1);
                newDist=pathWeight.get(town2)+smallR.getDistance();
                if(newDist<edgeDist) {
                    pathWeight.put(town1, newDist);
                    previousNode.put(town1, town2);
                }

            }
            if(visitedTown.contains(town2)) {
                edgeDist=pathWeight.get(town2);
                newDist=pathWeight.get(town1)+smallR.getDistance();
                if(newDist<edgeDist) {
                    pathWeight.put(town2, newDist);
                    previousNode.put(town2, town1);
                }

            }
            else {
                continue;
            }
        }
    }

    public ArrayList<Road> sortEdges(Town sourceVertex){
        ArrayList<Road> sort=new ArrayList<Road>();
        Road r = null;
        int townIndex = 0;
        for(int i=0;i<towns.size();i++) {
            if(towns.get(i).compareTo(sourceVertex)==0) {
                townIndex = i;
                break;
            }
        }
        Iterator<Road> it=edges.get(townIndex).iterator();
        while(it.hasNext()) {
            if(sort.size()==0) {
                sort.add(it.next());
            }
            else {
                r=it.next();
                sort.add(r);
            }
        }
        return sort;
    }
    }


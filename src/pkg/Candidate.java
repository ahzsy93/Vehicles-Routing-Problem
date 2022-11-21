package pkg;

import java.io.Serializable;
import java.util.ArrayList;


class Route implements Serializable{

    public ArrayList<Integer> route;
    public double routeFitness;

    public Route() {
        route = new ArrayList<>();
    }
}

class Candidate implements Comparable<Candidate>, Serializable {

    ArrayList<Integer> chromosome;
    double fitness;
    int rank;
    ArrayList<Route> routeTable;

    public Candidate() {
        chromosome = new ArrayList<>();
        routeTable = new ArrayList<>();
    }


    @Override
    public String toString() {
        int[] tmp = new int[chromosome.size()];
        String ret = "";
        for (Integer i : chromosome) {
            ret += (i + " ");
        }
        return ret;
    }

    @Override
    public int compareTo(Candidate tmp) {

        if(this.fitness < tmp.fitness) {
            return -1;
        } else if(this.fitness == tmp.fitness) {
            return 0;
        } else {
            return 1;
        } 
        
    }
}
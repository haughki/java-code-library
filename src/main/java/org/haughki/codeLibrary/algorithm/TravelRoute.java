package org.haughki.codeLibrary.algorithm;


import java.util.ArrayList;
import java.util.List;

class Step {
    Step(String _firstCity, String _secondCity, int _distance){
        firstCity = _firstCity;
        secondCity = _secondCity;
        distance = _distance;
    }

    String firstCity;
    String secondCity;
    int distance = -1;

}

class RealStep {
    RealStep(Step _step, String _start){
        step = _step;
        start = _start;
    }
    Step step;
    String start;
    String end;
    private void setStart(String newStart){
        // TODO: validate incoming start
        start = newStart;
        if (start.equals(step.firstCity))
            end = step.secondCity;
        else
            end = step.firstCity;
    }    
}

class Route {
    List<RealStep> realSteps = new ArrayList<>();
    int totalDistance = 0;
}

public class TravelRoute {
    
    private static void buildSteps(List<Step> steps) {
        steps.add(new Step("Alabaster", "Birmingham", 24));
        steps.add(new Step("Alabaster", "Montgomery", 71));
        steps.add(new Step("Birmingham", "Huntsville", 103));
        steps.add(new Step("Birmingham", "Tuscaloosa", 59));
        steps.add(new Step("Demopolis", "Mobile", 141));
        steps.add(new Step("Demopolis", "Montgomery", 101));
        steps.add(new Step("Demopolis", "Tuscaloosa", 65));
        steps.add(new Step("Mobile", "Montgomery", 169));
        steps.add(new Step("Montgomery", "Tuscaloosa", 134));
    }

    private static void findRoutes(String start, String end, Route partialRoute, List<Step> steps){
        for(Step step: steps){
            // if you find a start in either city
            // if the other city equals the end city
            // we found a terminus: add real step to partial route, create new route and add to routes
            // return

            // create real step and set start on it
            // add real step to partial route -- recalculate total
            // copy steps, removing the current step -- sub method
            // recurse -- end of this step becomes the new start; pass truncated steps

            // we completed the loop and haven't added a new start to the partial route.
            // it's a dead-end: do nothing, let the function return
        }
    }
    
    public static void main(String[] args) {
        List<Step> steps = new ArrayList<>();
        buildSteps(steps);
        List<Route> routes = new ArrayList<>();
        
        
    }
}

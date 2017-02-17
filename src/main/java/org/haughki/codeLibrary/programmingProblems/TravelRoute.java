package org.haughki.codeLibrary.programmingProblems;


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
        setStart(start);
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
    Route(){}
    Route(Route route){
        realSteps = new ArrayList<>(route.realSteps);
        totalDistance = route.totalDistance;
    }
    List<RealStep> realSteps = new ArrayList<>();
    int totalDistance = 0;
    void addRealStep(RealStep realStep) {
        realSteps.add(realStep);
        totalDistance += realStep.step.distance;
    }
    @Override
    public String toString(){
        StringBuilder routeAndTotal = new StringBuilder();
        for (RealStep step: realSteps) {
            routeAndTotal.append(step.start).append("-").append(step.end).append(" > ");
        }
        
        routeAndTotal.append(totalDistance);
        return routeAndTotal.toString();
    }
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

    private static void findRoutes(String start, Route partialRoute, List<Step> steps){
        for(Step step: steps){
            RealStep realStep = null;
            boolean foundFinal = false;
            if(start.equals(step.firstCity)){
                realStep = new RealStep(step, step.firstCity);
                if (isFinalDestination(realStep, partialRoute))
                    foundFinal = true;
            } else if (start.equals(step.secondCity)){
                realStep = new RealStep(step, step.secondCity);
                if (isFinalDestination(realStep, partialRoute))
                    foundFinal = true;
            }
            if (realStep != null && !foundFinal) { // we've found a new step in the route -- route already updated
                
                // copy steps, removing the current step -- sub method
                List<Step> stepsSubSet = buildNewSteps(steps, realStep);
                // recurse -- end of this step becomes the new start; pass truncated steps
                Route newPartial = new Route(partialRoute);
                newPartial.addRealStep(realStep);
                findRoutes(realStep.end, newPartial, stepsSubSet);
            }
        }
        // we completed the loop and haven't added a new start to the partial route.
        // it's a dead-end: do nothing, let the function return
    }

    private static List<Step> buildNewSteps(List<Step> steps, RealStep stepToRemove) {
        List<Step> newSteps = new ArrayList<>();
        // in a route, you can never return to the same city
        for (Step step: steps) {
            if (!step.firstCity.equals(stepToRemove.start) && !step.secondCity.equals(stepToRemove.start)) {
                newSteps.add(step);
            }
        }
        return newSteps;
        //return steps.stream().filter(step -> !step.equals(stepToRemove)).collect(Collectors.toList());
    }

    private static boolean isFinalDestination(RealStep realStep, Route partialRoute){
        if (realStep.end.equals(finalDestination)){
            // we found a terminus: add real step to partial route, create new route and add to routes
            Route actualRoute = new Route(partialRoute);
            actualRoute.addRealStep(realStep);
            routes.add(actualRoute);
//            partialRoute.realSteps.clear();
//            partialRoute.totalDistance = 0;
            return true;
        }
        return false;
    }


    private static List<Route> routes = new ArrayList<>();
    private final static String finalDestination;
    public static void main(String[] args) {
        final List<Step> steps = new ArrayList<>();
        buildSteps(steps);
        
        findRoutes("Mobile", new Route(), steps);
        
        for (Route route: routes) {
            System.out.println(route.toString());
        }
    }
    
    static {
        finalDestination = "Huntsville";
    }
}

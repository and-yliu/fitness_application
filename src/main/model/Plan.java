package model;

import java.util.ArrayList;

//Represent a plan of exercise you are planing to do 
public class Plan extends ListOfExercise {
    private ArrayList<Exercise> completedExercise;

    // EFFECT: create a empty plan of exercises and an empty completed list
    public Plan() {
        super();
        completedExercise = new ArrayList<>();
    }

    // REQUIRES: index > 0;
    // MODIFIES: this
    // EFFECTS: add a rest to a given index in the list of exercises;
    public void addRest(int index) {
        Exercise rest = new Exercise("Rest", "Take a Break", "None", 30);
        super.getExercises().add(index, rest);
    }

    // EFFECTS: return the total number of seconds the whole plan is going to take
    public int getTotalDuration() {
        int time = 0;

        for (Exercise e : super.getExercises()) {
            time += e.getDuration();
        }
        return time;
    }

    // MODIFIES: this
    // EFFECTS: Remove the given exercise from the list of ongoing exercise
    // and add it to the list of completed exercise
    public void completeExercise(Exercise exercise) {
        super.getExercises().remove(exercise);
        completedExercise.add(exercise);
    }

    // EFFECTS: return the list of completed exercise
    public ArrayList<Exercise> getCompletedExercise() {
        return completedExercise;
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: add an exercise to the plan to the given index and log it
    @Override
    public void addExercise(int index, Exercise exercise) {
        super.addExercise(index, exercise);
        EventLog.getInstance().logEvent(new Event("Exercise added to plan: " + exercise.getName()));
    }

    // REQUIRE: index >= 0
    // MODIFIES: this
    // EFFECTS: remove an exercise from the plan at given index and log it
    @Override
    public void removeExercise(int index) {
        EventLog.getInstance()
                .logEvent(new Event("Exercise removed from plan: " + super.getExercises().get(index).getName()));
        super.removeExercise(index);
    }
}

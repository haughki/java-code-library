package org.haughki.codeLibrary.generics;

import org.haughki.codeLibrary.aacommon.hierarchy.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pecs {
    public static void main(String[] args) {

        // Producer
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                new Whale(),
                new AquaticMammal(),
                new HumpbackWhale()
        ));
        producer(animals);

        List<Whale> whales = new ArrayList<Whale>(Arrays.asList(
                new Whale(),
                new HumpbackWhale()
        ));
        producer(whales);


        // Consumer
        List<Animal> moreAnimals = new ArrayList<>();
        consumer(moreAnimals);
        Animal anAnimal = moreAnimals.get(0);
        if (anAnimal.IsAnimate()) {
            anAnimal.Die();
        }

        List<RealThing> things = new ArrayList<>();
        consumer(things);
        things.get(0).Exist();
    }

    public static void producer(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            animal.Live();
        }
    }

    public static void consumer(List<? super AquaticMammal> existers) {
        existers.add(new AquaticMammal());
        existers.add(new Whale());
        existers.add(new HumpbackWhale());
    }
}

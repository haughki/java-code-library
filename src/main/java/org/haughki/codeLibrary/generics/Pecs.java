package org.haughki.codeLibrary.generics;

import org.haughki.codeLibrary.aacommon.hierarchy.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

        List<HumpbackWhale> humpbacks = new ArrayList<>();
        // ERROR:  consumer(humpbacks); -- HumpbackWhale not Whale or a super of Whale
    }

    // Can take a Collection of Animal or any sub-type of Animal.  Can
    // extract (produce) items from the collection and be sure that they support the
    // Animal interface -- polymorphism.
    public static void producer(Collection<? extends Animal> animals) {
        for (Animal animal : animals) {
            animal.Live();
        }
    }

    // Can take a Collection of Whale or any super-type of Whale.  Can add (consume) Whale or any
    // sub-type of Whale to the collection. Wants to make sure it can _add_ a certain type, and ensure the caller
    // isn't trying to use an unsupported interface; i.e., an interface not supported by the declared type.
    // Restricts to super-types to protect the user: if the user were allowed to pass a coll parameterized to
    // a sub-type of Whale, he/she might think he/she could use any public member from the sub-type's
    // interface, even those un-supported by Whale.
    public static void consumer(Collection<? super Whale> existers) {
        // ERROR:  existers.add(new AquaticMammal());  -- AquaticMammal not a Whale or sub of Whale
        existers.add(new Whale());
        existers.add(new HumpbackWhale());
    }
}

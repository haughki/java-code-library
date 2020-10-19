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

        List<Whale> whales = new ArrayList<>(Arrays.asList(
                new Whale(),
                new HumpbackWhale()
        ));
        // whales.add(new AquaticMammal()); -- ERROR: AquaticMammal is a parent of Whale -- breaks polymorphism
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

//        A little test I did to confirm a question on stack overflow
//        List<Object> objs = new ArrayList<>();
//        adder(new Integer(5), objs);
//        System.out.println(objs.get(0).toString());
//        
//        List<Number> nums = new ArrayList<>();
//        adder2(new Integer(7), objs);
//        System.out.println(nums.get(0).toString());
    }

    // Can take a Collection of Animal or any sub-type of Animal.  Can
    // extract (produce) items from the collection and be sure that they support the
    // Animal interface -- polymorphism.
    public static void producer(Collection<? extends Animal> animals) {
        animals.forEach(Animal::Live);
    }
    
    // This method can take a Collection typed to Whale or any parent (super) of Whale. Whale is the "lowest" possible
    // type that the Collection can be, and so the method body has to assume that, "at worst", it could get a 
    // Collection of Whales; i.e., the method body has to act as if the passed Collection is Collection<Whale>. 
    // A Collection of Whales can only add Whales and subtypes of Whale (polymorphism), and 
    // so, that’s the only thing you can add to the “existers” parameter. A Collection typed to Whale or any parent
    // of Whale will always be able to accept Whales and its sub-types -- this is what "allows" the passed Collection's
    // generic type to be <? super Whale>.
    public static void consumer(Collection<? super Whale> existers) {
        //existers.add(new AquaticMammal()); // ERROR -- AquaticMammal not a Whale or sub of Whale (".add cannot be applied to AquaticMammal")
        existers.add(new Whale());
        existers.add(new HumpbackWhale());
    }

    
    public static <T extends Number> void adder(T elem, Collection<? super Number> numberSuper) {
        numberSuper.add(elem);
    }

    public static <T extends Number> void adder2(T elem, Collection<Number> numberSuper) {
        numberSuper.add(elem);
    }
}

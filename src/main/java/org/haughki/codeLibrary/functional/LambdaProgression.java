package org.haughki.codeLibrary.functional;

import org.haughki.codeLibrary.aacommon.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/*
The code in this class shows multiple different ways to do the same thing.  The progression is
from a for-loop approach, to a functional approach.
 */
public class LambdaProgression {
    public static void main(String[] args) {
        
        List<Person> roster = new ArrayList<>(Arrays.asList(
                new Person("Jacob", (byte) 45, Person.Sex.MALE, "cobbletoes@gorflingle.org"),
                new Person("Sugey", (byte) 22, Person.Sex.MALE, "sugey@gorflingle.org"),
                new Person("Farrah", (byte) 19, Person.Sex.FEMALE, "farrah@gorflingle.org")
        ));

        printEmailsOfMenWithinAgeRange(roster, 18, 25);

        processPersonsWithFunction(roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        processElements(roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        processElementsStream(roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        // actually, no method needed
        roster.stream().filter(
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25)
                .map(p -> p.getEmailAddress())
                .forEach(email -> System.out.println(email));

        // with method references
        roster.stream().filter(
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25)
                .map(Person::getEmailAddress)
                .forEach(System.out::println);
    }

    public static void printEmailsOfMenWithinAgeRange(List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high && p.getGender() == Person.Sex.MALE) {
                System.out.println(p.getEmailAddress());
            }
        }
    }

    public static void processPersonsWithFunction(List<Person> roster,
                                                  Predicate<Person> tester,
                                                  Function<Person, String> mapper,
                                                  Consumer<String> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public static <X, Y> void processElements(Iterable<X> source,
                                              Predicate<X> tester,
                                              Function<X, Y> mapper,
                                              Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public static <X, Y> void processElementsStream(List<X> source, Predicate<X> tester,
                                                    Function<X, Y> mapper, Consumer<Y> block) {
        source.stream().filter(p -> tester.test(p))
                .map(p -> mapper.apply(p)).forEach(s -> block.accept(s));
    }
}

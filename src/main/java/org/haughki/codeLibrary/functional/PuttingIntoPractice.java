package org.haughki.codeLibrary.functional;

import org.haughki.codeLibrary.aacommon.Trader;
import org.haughki.codeLibrary.aacommon.Transaction;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;


public class PuttingIntoPractice{
    public static void main(String ...args){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        PuttingIntoPractice p = new PuttingIntoPractice();
        p.doAggregates(transactions);
        
    }
    
    private void doAggregates(List<Transaction> transactions) {
        transactions.stream()
                .filter(t -> t.getYear() > 2011)
                .sorted(comparing(Transaction::getValue))
                .forEach(System.out::println);

        System.out.println();
        
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        System.out.println();
        
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println();

        System.out.println(transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining()));

        System.out.println();

        System.out.println(transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(t -> t.getCity().equals("Milan")));

        System.out.println();
        
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println();

        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);

        System.out.println();
        
        transactions.stream()
                .min(comparing(Transaction::getValue))
                .ifPresent(System.out::println);
        
    }
}

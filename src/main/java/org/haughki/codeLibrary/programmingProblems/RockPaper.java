package org.haughki.codeLibrary.programmingProblems;


import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Comparator;


class ThrowNames {
    static final char NULL_THROW = 'Z';
    static final char ROCK = 'R';
    static final char PAPER = 'P';
    static final char SCISSORS = 'S';
}


class ThrowFactory {
    static OneThrow createOneThrow(final char shortLetter) {
        switch (shortLetter) {
            case ThrowNames.ROCK:
                return new Rock();
            case ThrowNames.PAPER:
                return new Paper();
            case ThrowNames.SCISSORS:
                return new Scissors();
            default:
                throw new IllegalStateException("Invalid throw letter.");
        }
    }
}

interface OneThrow {
    String getName();
    char getShort();
}

class NullThrow implements OneThrow {
    private final String name = "Null Throw";
    private final char SHORT_LETTER = ThrowNames.NULL_THROW;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getShort() {
        return SHORT_LETTER;
    }
}

class Rock implements OneThrow {
    private final String name = "rock";
    private final char SHORT_LETTER = ThrowNames.ROCK;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getShort() {
        return SHORT_LETTER;
    }
}

class Paper implements OneThrow {
    private final String name = "paper";
    private final char SHORT_LETTER = ThrowNames.PAPER;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getShort() {
        return SHORT_LETTER;
    }
}

class Scissors implements OneThrow {
    private final String name = "scissors";
    private final char SHORT_LETTER = ThrowNames.SCISSORS;
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getShort() {
        return SHORT_LETTER;
    }
}

class ThrowComparator implements Comparator<OneThrow> {
    @Override
    public int compare(OneThrow o1, OneThrow o2) {
        final char throwOne = o1.getShort();
        final char throwTwo = o2.getShort();

        if (throwOne == throwTwo)
            return 0;

        switch (throwOne) {
            case ThrowNames.ROCK:
                if (throwTwo == ThrowNames.SCISSORS)
                    return 1;
                else if (throwTwo == ThrowNames.PAPER)
                    return -1;
            case ThrowNames.PAPER:
                if (throwTwo == ThrowNames.ROCK)
                    return 1;
                else if (throwTwo == ThrowNames.SCISSORS)
                    return -1;
            case ThrowNames.SCISSORS:
                if (throwTwo == ThrowNames.PAPER)
                    return 1;
                else if (throwTwo == ThrowNames.ROCK)
                    return -1;
            default:
                throw new IllegalStateException("throwOne is: " + throwOne + ", but should be one of R, P, or S");
        }
    }
}

class Game {
    private final OneThrow firstThrow;
    private final OneThrow secondThrow;
    private final ThrowComparator throwComparator = new ThrowComparator();
    
    Game(String _gameString) {
        if (_gameString.length() != 2)
            throw new IllegalStateException("must be two characters");
        firstThrow = ThrowFactory.createOneThrow(_gameString.charAt(0));
        secondThrow = ThrowFactory.createOneThrow(_gameString.charAt(1));
    }

    OneThrow winner() {
        int comparisonResult = throwComparator.compare(firstThrow, secondThrow);
        switch (comparisonResult) {
            case 0:
                return new NullThrow();
            case 1:
                return firstThrow;
            case -1:
                return secondThrow;
            default:
                throw new IllegalStateException("comparisonResult: <" + comparisonResult + "> is invalid.");
        }        
    }    
    
    @Override
    public String toString() {
        return firstThrow.getName() + ":" + secondThrow.getName();
    }
}

public class RockPaper {
    public static void main(String[] args) throws OperationNotSupportedException {
        final String matchResults = "RRRSSRSPPPPSRP";
        if(matchResults.length() % 2 != 0)
            throw new IllegalArgumentException();
        ArrayList<Game> games = new ArrayList<>();
        for (int i = 0; i < matchResults.length(); i++) {
            games.add(new Game(matchResults.substring(i, i+2)));
            i++;
        }
        games.forEach(game -> {
            System.out.println("Throws: " + game.toString());
            System.out.println("Winner: " + game.winner().getName());
            System.out.println();
        });
    }
}

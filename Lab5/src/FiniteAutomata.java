package Lab5.src;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FiniteAutomata {
    private List<String> states;
    private List<String> alphabet;
    private Map<Lab4.src.Entry<String, String>, Set<String>> transitions; // pair of 2 strings associated to a set of strings
                                // as value, a set is used to permit both FA's and DFA's
    private String initial_state;
    private List<String> final_states;
    private boolean isDFA; // deterministic finite automata

    public FiniteAutomata(String filename)
    {
        this.transitions = new HashMap<>(); // associate a pair with something from the alphabet
        this.read("Lab4/src/Input/" + filename);
    }

    public List<String> getStates() {
        return states;
    }

    public List<String> getFinal_states() {
        return final_states;
    }

    public String getInitial_state() {
        return initial_state;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public Map<Lab4.src.Entry<String, String>, Set<String>> getTransitions() {
        return transitions;
    }
    public boolean isDFA()
    {
        //  returns true if the size of the set is less than or equal to 1 (all sets from each transition)
        return this.transitions.values().stream().allMatch(list -> list.size() <= 1);
    }


    public void printMenu()
    {
        System.out.println("Finite Automata Menu\n");
        System.out.println("1) the set of states\n2) the alphabet\n3) all transitions\n4) initial state\n5) final states");
        System.out.println("6) check if the automata accepts a sequence");
        System.out.println("0) exit");
    }

    public void menu()
    {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int option = 1;
        while(option != 0)
        {
            printMenu();
            option = scanner.nextInt();
            if(option == 1)
            {
                // set of states
                System.out.println(this.states);
            }
            else if (option == 2)
            {
                // alphabet
                System.out.println(this.alphabet);
            }
            else if (option == 3)
            {
                // transitions
                StringBuilder builder = new StringBuilder();
                builder.append("Transitions: \n");
                transitions.forEach((K, V) -> {
                    builder.append("(").append(K.getKey()).append(", ").append(K.getValue()).append(", ").append(V).append(")\n");
                });
                System.out.println(builder);
            }
            else if (option == 4)
            {
                //initial state
                System.out.println(this.initial_state);
            }
            else if (option == 5)
            {
                //final states
                System.out.println(this.final_states);
            }
            else if (option == 6)
            {
                System.out.println("sequence : ");
                String new_sequence = scanner.next();
                this.isSequenceAccepted(new_sequence);
                System.out.println(new_sequence);
            }
            else
            {
                if(option != 0)
                {
                    System.out.println("Incorrect option! Try again!");
                }
            }
            System.out.println("\n\n");
        }
    }

    private void read(String filePath)
    {
        try (java.util.Scanner scanner = new java.util.Scanner(new File(filePath)))
        {
            String line = scanner.nextLine();
            this.states = new ArrayList<>(List.of(line.split(",")));

            line = scanner.nextLine();
            this.alphabet = new ArrayList<>(List.of(line.split(",")));

            this.initial_state = scanner.nextLine();

            line = scanner.nextLine();
            this.final_states = new ArrayList<>(List.of(line.split(",")));

            while(scanner.hasNext())
            {
                line = scanner.nextLine().trim();
                String[] transition = line.split(",");

                if (this.alphabet.contains(transition[1]))
                {
                    transitions.put(new Lab4.src.Entry<String, String>(transition[0], transition[1]), Collections.singleton(transition[2]));
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error while reading file for FA!");
        }

        this.isDFA = this.isDFA();
    }

    public boolean isSequenceAccepted(String sequence) {
        if (!this.isDFA)
        {
            System.out.println("This is not a deterministic finite automata!");
            return false;
        }

        boolean result;

        // if the FA is deterministic

        // if the sequence is empty we check if the initial state is also the final state
        if(sequence.length() == 0)
        {
            result = this.final_states.contains(initial_state);
            if(result)
            {
                System.out.println("accepted");
            }
            else
            {
                System.out.println("rejected");
            }
            return result;
        }

        String state = this.initial_state; // the current state
        int length = sequence.length();

        for (int i = 0; i < length; i++)
        {
            String symbol = sequence.substring(i, i + 1); // current symbol, a substring of 1 character at 'i' position
            Lab4.src.Entry<String, String> transition = new Lab4.src.Entry<String, String>(state, symbol); // creating a pair of 2 strings, the state with the associated position
            if (!this.transitions.containsKey(transition)) // checks if the sequence is part of the transitions
            {
                System.out.println("rejected");
                return false;
            }
            else
            {
                state = this.transitions.get(transition).iterator().next(); // then we move to the next state, which will be used
                                                                            // to create a new transition to check if it exists in our list
            }
        }

        // checking if the final state we obtained in the iteration is also part of the FA's final states
        result = this.final_states.contains(state);
        if(result)
        {
            System.out.println("accepted");
        }
        else
        {
            System.out.println("rejected");
        }
        return result;
    }
}

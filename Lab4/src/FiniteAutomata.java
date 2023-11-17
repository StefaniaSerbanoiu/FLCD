package Lab4.src;


import Lab4.src.Data_Structures.EntryForHashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FiniteAutomata {
    private List<String> states;
    private List<String> alphabet;
    private Map<EntryForHashTable<String, String>, Set<String>> transitions;
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

    public Map<EntryForHashTable<String, String>, Set<String>> getTransitions() {
        return transitions;
    }
    public boolean isDFA()
    {
        return this.transitions.values().stream().allMatch(list -> list.size() <= 1);
    }


    public void printMenu()
    {
        System.out.println("Finite Automata Menu\n");
        System.out.println("1) the set of states\n2) the alphabet\n3) all transitions\n4) initial state\n5) final states\n\n");
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
        try (java.util.Scanner scanner = new java.util.Scanner(new File(filePath))) //TODO
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

                if (this.alphabet.contains(transition[2]))
                {
                    transitions.put(new EntryForHashTable<>(transition[0], transition[1]), Collections.singleton(transition[2]));
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error while reading file for FA!");
        }

        this.isDFA = this.isDFA();
    }
}

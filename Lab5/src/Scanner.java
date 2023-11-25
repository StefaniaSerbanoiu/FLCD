package Lab5.src;
import Lab5.src.EntryForProgramInternalForm;
import Lab5.src.ProgramInternalForm;
import Lab5.src.SymbolTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Scanner
{
    private SymbolTable symbol_table; // requirement 1a
    private String filename; // the name of the file from where the program is read
    private ProgramInternalForm pif;

    // some constructors
    public Scanner(String name_of_file , int capacity_for_symbol_table)
    {
        this.symbol_table = new SymbolTable(capacity_for_symbol_table);
        this.pif = new ProgramInternalForm();
        this.filename = name_of_file;
    }

    public Scanner(String name_of_file)
    {
        this.symbol_table = new SymbolTable();
        this.pif = new ProgramInternalForm();
        this.filename = name_of_file;
    }

    public ProgramInternalForm getProgramInternalForm() { return this.pif; }

    public SymbolTable getSymbol_table() { return symbol_table; }

    private String readFile() throws IOException // reads the file and returns a string with the program from it, or throws an exception
    {
        String full_path = "Lab5/src/Input/" + filename;
        Path path = Paths.get(full_path); // get the path to the file
        return new String(Files.readAllBytes(path));
    }


    public boolean isSeparator(String input)
    {
        if(input.equals("{") || input.equals("}") || input.equals("(") || input.equals(")") || input.equals("[") || input.equals("]") ||
         input.equals("\n") || input.equals(";") || input.equals(" ") || input.equals("?") || input.equals("\""))
        {
            return true;
        }
        return false;
    }

    public boolean isOperator(String input)
    {
        if (input.equals("=") || input.equals("!=") || input.equals("==") || input.equals("<=") || input.equals("<") ||
                input.equals(">=") || input.equals(">") || input.equals("+") || input.equals("-") || input.equals("*") ||
                input.equals("/") || input.equals("%"))
        {
            return true;
        }
        return false;
    }

    public boolean isKeyword(String input)
    {
        if(Objects.equals(input, "string") || Objects.equals(input, "int") || Objects.equals(input, "while") || Objects.equals(input, "array") || Objects.equals(input, "read") || Objects.equals(input, "print") || Objects.equals(input, "if") || Objects.equals(input, "else"))
        {
            return true;
        }
        return false;
    }

    public boolean isIdentifier(String input)
    {
        boolean is_identifier = input.matches("[a-zA-Z][a-zA-Z0-9]*");
        return is_identifier; // true or false
    }

    public boolean isStringConstant(String input)
    {
        if (input.matches("[a-zA-Z0-9_.]"))
        {
            return true;
        }
        return false;
    }

    public boolean isIntegerConstant(String input)
    {
        // integer
        if (input.matches("[+-]?([1-9][0-9]*|0)"))
        {
            return true;
        }
        // positive integer
        return input.matches("[+]?(?:[1-9][0-9]*)");
    }

    public List<String> tokens()
    {
        List<String> tokensList = new ArrayList<>();
        String program_string = "??????";
        try
        {
            program_string = readFile();
            
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
            System.out.println("Error!!! File not found!!");
        }

        // adding to the list all tokens from the program
        StringTokenizer tokenizer = new StringTokenizer(program_string, "\t\r ;?[]{}()");
        while (tokenizer.hasMoreTokens())
        {
            tokensList.add(tokenizer.nextToken());
        }
        return tokensList;
    }

    public void scan()
    {
        List<String> tokens = this.tokens();
        int lineCount = 1; // line counter begins from 1 (line 1)

        for (String token : tokens)
        {
            if (this.isSeparator(token) || this.isOperator(token) || this.isKeyword(token))
            {
                this.pif.add(new EntryForProgramInternalForm<>(token, -1));
            }
            else
            {
                if (this.isIdentifier(token))
                {
                    int position = symbol_table.position(token);
                    this.pif.add(new EntryForProgramInternalForm<>(token, position));
                }
                else
                {
                    // check integer constant or string constant
                    if (this.isIntegerConstant(token) || this.isStringConstant(token))
                    {
                        int position = symbol_table.position(token);
                        this.pif.add(new EntryForProgramInternalForm<>(token, position));
                    }
                    else
                    {
                        System.out.println("Lexical error at line " + lineCount);
                        return;
                    }
                }
            }
            // increment line counter for each token
            if (token.contains("\n"))
            {
                lineCount++;
            }
        }

        System.out.println("Lexically correct");
    }

}
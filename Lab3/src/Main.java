import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main
{
    public static void main(String[] args)
    {
        String p1 = "p1.txt";
        String p2 = "p2.txt";
        String p3 = "p1.txt";
        String p_err = "programWithErrors.txt";
        ProgramInternalForm pif = new ProgramInternalForm();
        Scanner scanner = new Scanner(p1, 20);
        scanner.scan();
        //System.out.println(scanner.getSymbol_table().toString());
        //System.out.println(scanner.getProgramInternalForm().toString());

        // get the files with the content of pif and st
        try {
            // folder path
            String outputFolderPath = "Lab3/src/Output/";

            // st.out
            PrintStream stOut = new PrintStream(new FileOutputStream(outputFolderPath + "ST.out"));
            System.setOut(stOut);
            System.out.println(scanner.getSymbol_table().toString());
            stOut.close();

            // PIF.out
            PrintStream pifOut = new PrintStream(new FileOutputStream(outputFolderPath + "PIF.out"));
            System.setOut(pifOut);
            System.out.println(scanner.getProgramInternalForm().toString());
            pifOut.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
package Lab4.src;

public class EntryForProgramInternalForm<String, Integer>
{
    String token; // what is it in the symbol table : operator, constant, etc
                 // for variable names, the token will be named 'id' (identifier)
    Integer st_pos; // position in symbol table
                    // variables and constants have positions > -1, but the rest have the position -1 (bc they don't appear in the symbol table)
                    // that number will actually indicate the key of the associated token in the symbol table

    public EntryForProgramInternalForm(String new_token, Integer pos)
    {
        this.token = new_token;
        this.st_pos = pos;
    }

    public Integer getSt_pos() {
        return st_pos;
    }

    public String getToken() {
        return token;
    }
}

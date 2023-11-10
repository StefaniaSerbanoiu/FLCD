import Data_Structures.EntryForProgramInternalForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProgramInternalForm
{
    private List<EntryForProgramInternalForm<String, Object>> entries;

    public ProgramInternalForm()
    {
        this.entries = new ArrayList<>();
    }

    public int getSize() { return entries.size(); }

    public void add(EntryForProgramInternalForm<String, Object> new_entry)
    {
        this.entries.add(new_entry);
    }

    @Override
    public String toString()
    {
        StringBuilder pif = new StringBuilder();
        int length = this.entries.size();
        for(int i = 0; i < length; i++)
        {
            if(!this.entries.get(i).getToken().equals("\n") )
            {
                pif.append("token : ").append(this.entries.get(i).getToken());
                pif.append(" ; st_pos : ").append(this.entries.get(i).getSt_pos().toString()).append("\n");
            }
        }
        return pif.toString();
    }
}

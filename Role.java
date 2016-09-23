public class Role
{
    private boolean bad, merlin;
    
    public Role (boolean b, boolean m)
    {
        bad = b;
        merlin = m;
    }
    
    public boolean isBad()
    {
        return bad;
    }
    
    public boolean isMerlin()
    {
        return merlin;
    }
    
    public void setBad(boolean b)
    {
       bad = b;
    }
    
    public void setMerlin(boolean m)
    {
        merlin = m;
    }
 
    
    public String toString()
    {
        if (bad)
        {
            return "Role: Bad";
        }
        else
        {
            return "Role: Good";
        }
    }
}
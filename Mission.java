import java.util.ArrayList;

public class Mission
{
    private int[] missionResults;
    
    public Mission(int n)
    {
        missionResults = new int[n];
    }
    
    public void setMissionResult(int position, int result)
    {
        missionResults[position] = result;
    }
    
    public int[] results()
    {
        return missionResults;
    }
    
    public boolean missionPass()
    {
        for (int i = 0; i < missionResults.length; i++)
        {
            if (missionResults[i] == 0)
            {
                return false;
            }
        }
        return true;
    }
}
    
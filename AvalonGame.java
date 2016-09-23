import java.util.ArrayList;
import java.util.Scanner;

public class AvalonGame
{
    private Player[] players;
    private int goodWinsCount, badWinsCount, missionLeaderNum;
    private boolean goodGuysWon = false;

    public AvalonGame(int numPlayers,String[] names, Role[] roles)
    {
        players = new Player[numPlayers];
        for (int i = 0; i < players.length; i++)
        {
            players[i] = new Player(names[i],roles[i]);
        }
        goodWinsCount = 0;
        badWinsCount = 0;
        missionLeaderNum = (int)(Math.random()*players.length);;
    }

    public void displayRoles()
    {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < players.length; i++)
        {
            System.out.println(players[i].getName() + ", make sure you are the only one viewing this, press Enter when you are ready to view your role");
            scan.nextLine();
            if(players[i].getRole().isBad())
            {
                String str = "Your role is bad guy";
                for (int j = 0; j < players.length; j++)
                {
                    if (players[j].getRole().isBad())
                    {
                        str += "\n" + players[j].getName() + " is a bad guy";
                    }
                    else
                    {
                        str += "\n" + players[j].getName() + " is a good guy";
                    }
                }
                System.out.println(str);
            }
            else if (players[i].getRole().isMerlin())
            {
                String str = "You are Merlin";
                for (int j = 0; j < players.length; j++)
                {
                    if (players[j].getRole().isBad())
                    {
                        str += "\n" + players[j].getName() + " is a bad guy";
                    }
                    else
                    {
                        str += "\n" + players[j].getName() + " is a good guy";
                    }
                }
                System.out.println(str);
            }

            else
            {
                System.out.println("You are a good guy");
            }
            if (i < players.length-1)
            {
                System.out.println("Press enter and pass this to " + players[i+1].getName());
            }
            scan.nextLine();
            System.out.print('\u000C');
        }
    }

    public void doMission(int n, ArrayList<Player> p)
    {
        Scanner scan = new Scanner(System.in);
        Mission m = new Mission(n);
        int failCount = 0;
        int responseCount = 0;
        while(responseCount < n)
        {
            System.out.println(p.get(responseCount).getName() + ", do you pass this mission? (Answer \"yes\" or \"no\")");
            String response = scan.nextLine();
            if (response.equals("yes"))
            {
                m.setMissionResult(responseCount,1);
                responseCount++;
                System.out.print('\u000C');
            }
            else if (response.equals("no"))
            {
                m.setMissionResult(responseCount,0);
                responseCount++;
                failCount++;
                System.out.print('\u000C');
            }
            else
            {
                System.out.println("Invalid Response. Enter either \"yes\" or \"no\")");
            }
        }
        System.out.print('\u000C');
        if (m.missionPass())
        {
            System.out.println("The mission passed.");
            goodWinsCount++;
        }
        else
        {
            System.out.println("The mission failed with " + failCount + " vote(s) against.");
            badWinsCount++;
        }
    }

    public void displayMissionLeader()
    {
        int count = 0;
        if (count == 0)
        {
            System.out.println(players[missionLeaderNum].getName() + " has been selected as the first mission leader.");
        }
        else
        {
            System.out.println(players[missionLeaderNum].getName() + " is now the mission leader.");
        }
        count++;
        missionLeaderNum++;
        if (missionLeaderNum >= players.length)
        {
            missionLeaderNum = 0;
        }
    }

    public ArrayList<Player> chooseMissionMembers(int n)
    {
        ArrayList<Player> missionMembers = new ArrayList<Player>();
        Scanner scan = new Scanner(System.in);
        int count = 0;
        boolean invalidResponse;
        while (count < n)
        {
            System.out.println("Select a member for the mission:");
            String input = scan.nextLine();
            invalidResponse = true;
            for (int i = 0; i < players.length; i++)
            {
                if (input.equals(players[i].getName()))
                {
                    missionMembers.add(players[i]);
                    count++;
                    invalidResponse = false;
                }
            }
            if (invalidResponse)
            {
                System.out.println("Member not found, type in the exact name of the member.");
            }
        }
        System.out.print('\u000C');
        return missionMembers;
    }

    public boolean checkForEndgame()
    {
        return goodWinsCount == 3 || badWinsCount == 3;
    }

    public void gameResults()
    {
        if (goodWinsCount == 3)
        {
            System.out.println("Congratulations, the good guys win, however the bad guys may guess Merlin's identity.");
            goodGuysWon = true;
        }
        else
        {
            System.out.println("Congratulations, the bad guys win");
        }
    }

    public void postGame()
    {
        if (goodGuysWon)
        {
            Scanner scan = new Scanner(System.in);
            boolean invalid = true;
            Player merlin = new Player("merlin" , new Role (false, true));
            for (int j = 0; j < players.length; j++)
            {
                if (players[j].getRole().isMerlin())
                {
                    merlin = players[j];
                }
            }
            while (invalid)
            {
                System.out.println("Bad guys, guess Merlin's identity");
                String guess = scan.nextLine();

                for (int i = 0; i < players.length; i++)
                {
                    if (guess.equals(players[i].getName()))
                    {
                        if (players[i].getRole().isMerlin())
                        {
                            System.out.println("That is correct, " + players[i].getName() + " was Merlin, the bad guys win.");
                            invalid = false;
                        }
                        else
                        {
                            System.out.println("That is wrong, " + merlin.getName() + " was Merlin, the good guys still win.");
                            invalid = false;
                        }
                    }

                }
                if (invalid)
                {
                    System.out.println("Member not found, type in the exact name of the member.");
                }
            }

        }
    }
}


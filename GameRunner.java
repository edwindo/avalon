import java.util.Scanner;
import java.util.ArrayList;

public class GameRunner
{
    public static void main()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("How many players are playing?");
        String num = scan.nextLine();
        int numPlayers = Integer.parseInt(num);
        String[] names = new String[numPlayers];
        for (int i = 0; i < numPlayers; i++)
        {
            System.out.println("Enter name for Player " + (i+1) + ":");
            names[i] = scan.nextLine();
        }
        Role[] roles = generateRandomRoles(numPlayers);
        AvalonGame game = new AvalonGame(numPlayers, names, roles);
        System.out.print('\u000C');
        game.displayRoles();
        while(!game.checkForEndgame())
        {
            game.displayMissionLeader();
            boolean invalid = true;
            while(invalid)
            {
                System.out.println("Are the selected members agreed upon?");
                String decision = scan.nextLine();
                if (decision.equals("yes"))
                {
                    System.out.println("How many members are going on a mission?");
                    int number = missionValidator(numPlayers,scan.nextInt());
                    System.out.print('\u000C');
                    ArrayList<Player> missionGoers = game.chooseMissionMembers(number);
                    game.doMission(number,missionGoers);
                    scan.nextLine();
                    scan.nextLine();
                    System.out.print('\u000C');
                    invalid = false;
                }
                else if (decision.equals("no"))
                {
                    invalid = false;
                }
                else
                {
                    System.out.println("Invalid Response. Enter either \"yes\" or \"no\")");
                }
            }
        }
        game.gameResults();
        game.postGame();
    }

    private static Role[] generateRandomRoles(int n)
    {
        ArrayList<Role> availableRoles = new ArrayList<Role>();
        Role[] roles = new Role[n];
        int numGood = n*2/3-1;
        int numBad = n - numGood - 1;
        for (int i = 0; i < numGood; i++)
        {
            availableRoles.add(new Role(false, false));
        }
        for (int i = 0; i < numBad; i++)
        {
            availableRoles.add(new Role(true, false));
        }
        availableRoles.add(new Role(false, true));
        for (int i = 0; i < n; i++)
        {
            int randy = (int)(Math.random()*availableRoles.size());
            System.out.println(randy);
            roles[i] = availableRoles.get(randy);
            availableRoles.remove(randy);
        }
        return roles;
    }

    private static int missionValidator(int numPlayers, int mission)
    {
        Scanner scan = new Scanner(System.in);
        boolean invalid = true;
        int newNum = mission;
        while(invalid)
        {
            if (newNum <= numPlayers && newNum > 0)
            {
                invalid = false;
            }
            else
            {
                System.out.println("That is not a valid number of people to go on the mission, enter a new value");
                String input = scan.nextLine();
                newNum = Integer.parseInt(input);
            }
        }
        return newNum;
    }
}

        
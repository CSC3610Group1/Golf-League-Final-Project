//Main golf league class creates teams with players in the league

package group1;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class GolfLeague {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String firstName;
        String lastName;
        int playerScore;
        int playerRank;
        int handicap;
        int timesPlayed;
        int playerAverage;
        String teamName;
        int teamScore;
        int teamRank = 0;

        //test player
        Player player1 = new Player("John", null, 1, 1, -1, -1, 3);

        //validates player object
        List<String> invalidPlayerVars = Validator.validatePlayer(player1).getAllInvalid();
        if (!invalidPlayerVars.isEmpty()) {
            System.err.println("Error:\n -" + invalidPlayerVars.stream()
                    .collect(Collectors.joining("\n -")));
        }
        //System.out.println(player1.toString());

        //Array of 4 players for a team
        Player[] players = new Player[4];

        //ArrayList to hold teams in the league
        ArrayList<Team> league = new ArrayList<>();

        //Creates an arrayList for a team to have 4 players put in
        List<Player> teamMembers = new ArrayList<>();

        //Users enter data for 4 players
        for (int index = 0; index < 4; index++) {
            System.out.println("Enter player's first name: ");
            firstName = input.next();
            System.out.println("Enter player's last name: ");
            lastName = input.next();
            System.out.println("Enter player's score: ");
            playerScore = input.nextInt();
            System.out.println("Enter player's rank: ");
            playerRank = input.nextInt();
            System.out.println("Enter player's handicap: ");
            handicap = input.nextInt();
            System.out.println("Enter player's times played: ");
            timesPlayed = input.nextInt();
            System.out.println("Enter player's average: ");
            playerAverage = input.nextInt();

            players[index] = new Player(firstName, lastName, playerScore, playerRank, handicap, timesPlayed, playerAverage);
        }

        teamMembers.addAll(Arrays.asList(players));

        Collections.sort(teamMembers, Player.playerRankComparator);

        //Iterator used to walk through teamMembers list; displayed using while loop
        for (Object teamMember1 : teamMembers) {
            System.out.print("\nTeam members sorted by rank: " + teamMember1);
        }

        Collections.sort(teamMembers, Player.playerRankComparator);

        //Iterator used to walk through teamMembers list; displayed using while loop
        for (Object teamMember : teamMembers) {
            System.out.print("\nTeam members sorted by rank: " + teamMember);

        }

        //Loop to add teams to a stack
        for (int index = 0; index < 5; index++) {
            System.out.println("Enter team name: ");
            teamName = input.nextLine();
            System.out.println("Enter team score: ");
            teamScore = input.nextInt();

            if (teamName.equals("quit")) {
                break;
            }


            try {
              //  pushTeamData(new Team(teamName, teamScore));
            } catch (Exception e) {
                e.printStackTrace();
            }
            league.add(new Team(teamName, teamScore));

        }


    }


}

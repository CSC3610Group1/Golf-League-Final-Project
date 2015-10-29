package group1;

import java.util.*;


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
        int teamRank;

        List<Team> teamArrayList = new ArrayList<>();

        teamArrayList.add(new Team("G", 3));
        teamArrayList.add(new Team("I", 6));
        teamArrayList.add(new Team("B", 9));
        teamArrayList.add(new Team("H", 0));
        teamArrayList.add(new Team("A", 2));
        teamArrayList.add(new Team("D", 8));
        teamArrayList.add(new Team("F", 5));
        teamArrayList.add(new Team("J", 1));
        teamArrayList.add(new Team("C", 4));
        teamArrayList.add(new Team("E", 7));

        System.out.println("Unsorted Team Scores: ");
        teamArrayList.forEach(System.out::println);
       // Team.sortByScore((teamArrayList));
        System.out.println("Sorted Team Scores: ");
        teamArrayList.forEach(System.out::println);

        System.out.println("Unsorted Team Names: ");
        teamArrayList.forEach(System.out::println);
        Team.sortByName((teamArrayList));
        System.out.println("Sorted Team Names: ");
        teamArrayList.forEach(System.out::println);


        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("A", "A", 1, 1, 1, 1, 1));
        playerList.add(new Player("A", "A", 3, 3, 3, 3, 3));
        playerList.add(new Player("A", "A", 0, 0, 0, 0, 0));
        playerList.add(new Player("A", "A", 2, 2, 2, 2, 2));

        System.out.println("Unsorted Player List: ");
        playerList.forEach(System.out::println);
        Player.sortByScore(playerList);
        System.out.println("Sorted Player List: ");
        playerList.forEach(System.out::println);



        //hashmap for players, uses string for keys and player object for values
        HashMap<String, Player> playersMap = new HashMap();
        //Array of 4 players for a team
        Player[] players = new Player[4];

        //Stack to hold teams in the league
        Stack league = new Stack();


        //Creates an arrayList for a team to have 4 players put in
        List teamMembers = new ArrayList<Player>();

        //Users enter data for 4 players
        /*for(int index = 0; index < 4; index++){
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
			//Add players to map
			playersMap.put(lastName, new Player(firstName, lastName, playerScore, playerRank, handicap, timesPlayed, playerAverage));
		}*/

        //Foreach loop to push player object data to the local DB
        /*for (Object player : playersMap.values()) {
            try {
                getPlayerData.pushData((Player) player);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ArrayList<Player> playersFromDB = getPlayerData.getPlayers();

        for (Player p : playersFromDB) {
            System.out.println("Player name: " + p.getFirstName() + " " + " " + p.getLastName() + "\nHandicap: " + p.getHandicap());

        }
        teamMembers.add(Arrays.asList(players));
*/

        Collections.sort(teamMembers, Player.PlayerRankComparator);

        //Iterator used to walk through teamMembers list; displayed using while loop
        Iterator iterator = teamMembers.iterator();
        while (iterator.hasNext()) {
            System.out.print("\nTeam members sorted by rank: " + iterator.next());
        }

        Collections.sort(teamMembers, Player.PlayerRankComparator);

        //Iterator used to walk through teamMembers list; displayed using while loop
        Iterator it = teamMembers.iterator();
        while (it.hasNext()) {
            System.out.print("\nTeam members sorted by rank: " + it.next());

        }

        //Loop to add teams to a stack
       /* for (int index = 0; index < 5; index++) {
            System.out.println("Enter team name: ");
            teamName = input.nextLine();

            if (teamName == "quit") {
                break;
            }

            teamScore = 0;
            teamRank = 0;

            league.push(new Team(teamName, teamScore, teamRank));


        }*/

        // System.out.println("Most recent team added to league: " + league.peek());

    }


}

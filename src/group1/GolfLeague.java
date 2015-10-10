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

		//hashmap for players, uses string for keys and player object for values
		HashMap<String, Player> playersMap = new HashMap();
		//Array of 4 players for a team
		Player[] players = new Player[4];
		
		//Stack to hold teams in the league
		Stack league = new Stack();

		
		//Creates an arrayList for a team to have 4 players put in
		List teamMembers = new ArrayList<Player>();

		//Users enter data for 4 players
		for(int index = 0; index < 4; index++){
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
			playersMap.put(lastName, new Player(firstName, lastName, playerScore, playerRank, handicap, timesPlayed, playerAverage));
		}

		//Foreach loop to push player object data to the local DB
		for(Object player: playersMap.values()){
			getPlayerData.pushData((Player) player);
		}
		teamMembers.add(Arrays.asList(players));
		
		Collections.sort(teamMembers, Player.PlayerRankComparator);
		
		//Iterator used to walk through teamMembers list; displayed using while loop
		Iterator iterator = teamMembers.iterator();
			while (iterator.hasNext()){
				System.out.print("\nTeam members sorted by rank: " + iterator.next());
			}
		
		Collections.sort(teamMembers, Player.PlayerRankComparator);

		//Iterator used to walk through teamMembers list; displayed using while loop
		Iterator it = teamMembers.iterator();
			while (it.hasNext()){
				System.out.print("\nTeam members sorted by rank: " + it.next());

			}

		//Loop to add teams to a stack
		for(int index = 0; index < 5; index++){
			System.out.println("Enter team name: ");
			teamName = input.nextLine();
		
				if(teamName == "quit"){
					break;
				}
			
			teamScore = 0;
			teamRank = 0;
			
			league.push(new Team(teamName, teamScore, teamRank));



		}

		System.out.println("Most recent team added to league: "  +league.peek());

	}


}

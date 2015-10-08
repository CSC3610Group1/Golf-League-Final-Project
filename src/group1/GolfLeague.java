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
		Date date = new Date();
		System.out.println(date);
		//Array of 4 players for a team
		Player[] players = new Player[4];
		


		Object[][] topTeams = new Object[10][2];
		
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

			
			players[index] = new Player(firstName, lastName, playerScore, playerRank, handicap, timesPlayed, playerAverage,date);
			System.out.println("Player entered...");
		}
		
		teamMembers.add(Arrays.asList(players));
		Stack league = new Stack();
		for(int index = 0; index < 3; index++){
			System.out.println("Enter team name: ");
			teamName = input.next();
		
				if(teamName == "quit"){
					break;
				}
			
			System.out.println("Enter team score: ");
			teamScore = input.nextInt();
			System.out.println("Enter team rank: ");
			teamRank = input.nextInt();
			
			league.push( new Team(teamName, teamScore, teamRank));


		}
		System.out.println("Most recent team added to league: " + league.peek());
	}

}

package group1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class GolfLeague {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		String firstName;
		String lastName;
		int playerScore;
		int playerRank;
		int handicap;
		int timesPlayed;
		double playerAverage;
		String teamName;
		int teamScore;
		int teamRank;
		
		//Array of 4 players for a team
		Player[] players = new Player[4];
		
		//Array of 10 teams in league
		Team[] league = new Team[10];
		
		//Creates an arrayList for a team to have 4 players put in
		List teamMembers = new ArrayList<Player>();
		
		//Users enter data for 4 players
		for(int index = 0; index < 4; index++){
			System.out.println("Enter player's first name: ");
			firstName = input.nextLine();
			System.out.println("Enter player's last name: ");
			lastName = input.nextLine();
			System.out.println("Enter player's score: ");
			playerScore = input.nextInt();
			System.out.println("Enter player's rank: ");
			playerRank = input.nextInt();
			System.out.println("Enter player's handicap: ");
			handicap = input.nextInt();
			System.out.println("Enter player's times played: ");
			timesPlayed = input.nextInt();
			System.out.println("Enter player's average: ");
			playerAverage = input.nextDouble();
			
			players[index] = new Player(firstName, lastName, playerScore, playerRank, handicap, timesPlayed, playerAverage);
		}
		
		teamMembers.add(Arrays.asList(players));

		for(int index = 0; index < 10; index++){
			System.out.println("Enter team name: ");
			teamName = input.nextLine();
		
				if(teamName == "quit"){
					break;
				}
			
			System.out.println("Enter team score: ");
			teamScore = input.nextInt();
			System.out.println("Enter team rank: ");
			teamRank = input.nextInt();
			
			league[index] = new Team(teamName, teamScore, teamRank);

		}
		
	}

}

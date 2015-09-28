package group1;

import java.util.Hashtable;
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
		
		Hashtable<String, Player> players = new Hashtable();
		Hashtable<String, Team> teams = new Hashtable();
		
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
			
			players.put(lastName, new Player(firstName, lastName, playerScore, playerRank, handicap, timesPlayed, playerAverage));
		}
		
		while(true){
			System.out.println("Enter team name: ");
			teamName = input.nextLine();
		
				if(teamName == "quit"){
					break;
				}
	
			
			System.out.println("Enter team score: ");
			teamScore = input.nextInt();
			System.out.println("Enter team rank: ");
			teamRank = input.nextInt();
			
			teams.put(teamName, new Team(teamName, teamScore, teamRank));
		}
		
	}

}

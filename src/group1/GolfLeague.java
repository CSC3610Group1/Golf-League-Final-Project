package group1;

import java.util.ArrayList;
import java.util.Arrays;
<<<<<<< Updated upstream
=======
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
>>>>>>> Stashed changes
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

public class GolfLeague extends getPlayerData {

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

		Object[][] topTeams = new Object[10][2];
		
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
			
			//players[index] = new Player(firstName, lastName, playerScore, playerRank, handicap, timesPlayed, playerAverage, joinedTeam);
		}
		
		teamMembers.add(Arrays.asList(players));
		
		Collections.sort(teamMembers, Player.PlayerRankComparator);
		
		//Iterator used to walk through teamMembers list; displayed using while loop
		Iterator iterator = teamMembers.iterator();
			while (iterator.hasNext()){
				System.out.print("\nTeam members sorted by rank: " + iterator.next());
			}

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

			topTeams[teamRank - 1][0] = teamName;
			topTeams[teamRank - 1][1] = teamScore;

		}

		for(int i = 0; i < topTeams.length; i ++){


				System.out.println("Rank: " + (i+1) + " Team: " + topTeams[i][0] + " Score: " + topTeams[i][1]);

		}

	}


}

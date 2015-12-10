/** Validates Player and Team objects and returns a PlayerValidationFlags
 * or TeamValidationFlags object with the errors that occurred
 */

package group1;

import java.util.regex.Pattern;

public final class Validator {

    public Validator() {
    }

    //validates player object and then returns a PlayerValidationFlags object with all errors
    public static PlayerValidationFlags validatePlayer(Player player) {
        boolean validFirstName;
        boolean validLastName;
        boolean validPlayerScore;
        boolean validPlayerRank;
        boolean validHandicap;
        boolean validTimesPlayed;

        String firstName = player.getFirstName();
        String lastName = player.getLastName();
        int playerScore = player.getPlayerScore();
        int playerRank = player.getPlayerRank();
        int handicap = player.getHandicap();
        int timesPlayed = player.getTimesPlayed();

        validFirstName = (((isLetters(firstName)) && isNotNullOrEmpty(firstName)));

        validLastName = (((isLetters(lastName)) && isNotNullOrEmpty(lastName)));

        validPlayerScore = isPositive(playerScore);

        validPlayerRank = (playerRank >= 1) && (playerRank <= 4);

        validHandicap = isPositive(handicap);

        validTimesPlayed = isPositive(timesPlayed);

        return new PlayerValidationFlags(validFirstName, validLastName, validPlayerScore,
                validPlayerRank, validHandicap, validTimesPlayed);
    }

   public static boolean validateTeam(Team team) {
       if (isLetters(team.getTeamName())){
           return true;
       }
        return false;
    }

    public static String isScoreValid(String string){
      if (isNumbersOnly(string) && isNotNullOrEmpty(string) && isPositive(Integer.parseInt(string))){
          return "";
      }else{ return "Score must only contain positive numbers valid ver.";
    }}
    //checks if string is a letter based on Unicode specifications
    public static boolean isLetters(String string) {
        return isNotNullOrEmpty(string) && string.chars().allMatch(Character::isLetter);
    }

    //checks if int is positive
    private static boolean isPositive(int number) {
        return number > 0;
    }

    //checks if the string is null or empty
    private static boolean isNotNullOrEmpty(String string) {

        return !(string == null || string.isEmpty());
    }

    public static Boolean isNumbersOnly(String string){

        Pattern p = Pattern.compile("\\d+");

        return p.matcher(string).find();

    }

    public static Boolean validateTeamNameLength(String string){

        if(string.length() >= 2 && string.length() <= 20){
            return true;
        }
        else {
            return false;
        }
    }
}

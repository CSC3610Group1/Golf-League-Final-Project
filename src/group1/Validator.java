package group1;

/**
 * Class CSC3610
 * Programmer: T.J. Stankus
 * Date: 10/12/2015
 * Purpose:
 */
public final class Validator {

    private Validator() {
    }

    static PlayerValidationFlags validatePlayer(Player player) {
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

    static boolean validateTeam(Team team) {
        return false;
    }

    //checks if string is a letter based on Unicode specifications
    private static boolean isLetters(String string) {
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
}

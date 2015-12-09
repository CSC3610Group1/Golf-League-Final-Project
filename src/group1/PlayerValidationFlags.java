package group1;

import java.util.ArrayList;
import java.util.List;

/**
 * Class CSC3610
 * Programmer: T.J. Stankus
 * Date: 10/12/2015
 * Purpose: Creates a PlayerValidationFlags objects than returns an ArrayList with all errors
 */
public final class PlayerValidationFlags {
    private final boolean validFirstName;
    private final boolean validLastName;
    private final boolean validPlayerScore;
    private final boolean validPlayerRank;
    private final boolean validHandicap;
    private final boolean validTimesPlayed;


    public PlayerValidationFlags(boolean validFirstName, boolean validLastName,
                                 boolean validPlayerScore, boolean validPlayerRank,
                                 boolean validHandicap, boolean validTimesPlayed) {
        this.validFirstName = validFirstName;
        this.validLastName = validLastName;
        this.validPlayerScore = validPlayerScore;
        this.validPlayerRank = validPlayerRank;
        this.validHandicap = validHandicap;
        this.validTimesPlayed = validTimesPlayed;
    }


    public boolean isValidFirstName() {
        return validFirstName;
    }

    public boolean isValidLastName() {
        return validLastName;
    }

    public boolean isValidPlayerScore() {
        return validPlayerScore;
    }

    public boolean isValidPlayerRank() {
        return validPlayerRank;
    }

    public boolean isValidHandicap() {
        return validHandicap;
    }

    public boolean isValidTimesPlayed() {
        return validTimesPlayed;
    }

    //adds all errors to an ArrayList
    public List<String> getAllInvalid() {
        List<String> invalidVars = new ArrayList<>();
        if (!validFirstName) {
            invalidVars.add("First name must contain only letters");
        }
        if (!validLastName) {
            invalidVars.add("Last name must contain only letters");
        }
       /* if (!validPlayerScore){
            invalidVars.add("Player score must be a positive number");
        }*/
       /* if (!validPlayerRank){
            invalidVars.add("Player rank must be between 1 and 4");
        }*/
        if (!validHandicap) {
            invalidVars.add("Handicap must be a positive number");
        }
        /*if (!validTimesPlayed){
            invalidVars.add("Times Played must be a positive number");
        }*/
        return invalidVars;
    }

    public List<String> getInvalidNames() {
        List<String> invalidVars = new ArrayList<>();
        if (!validFirstName) {
            invalidVars.add("First name must contain only letters");
        }
        if (!validLastName) {
            invalidVars.add("Last name must contain only letters");
        }
        return invalidVars;
    }
    public String getInvalidScore() {

        String invalidVars = "";

        if (!validPlayerScore)
            invalidVars = "Player score must be a positive number";
        return invalidVars;
    }
}
package group1;

/**
 * Class CSC3610
 * Programmer: T.J. Stankus
 * Date: 10/12/2015
 * Purpose: Static methods used to validate user input
 */
public final class Validate {

    //private constructor to prevent instantiation
    private Validate() {
    }

    //validates that string contains only letters as specified by Unicode Standard
    public static boolean isLetterValid(String input){
        return input.chars().allMatch(Character::isLetter);
    }


}

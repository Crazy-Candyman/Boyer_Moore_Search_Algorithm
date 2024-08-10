import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // This method prints the menu options
    public static void displayMenu() {
        System.out.println("\n\tBoyer Moore Algorithm Searcher");
        System.out.println("---------------------------------");
        System.out.println("[1] Display all 50 states of United States");
        System.out.println("[2] Search");
        System.out.println("[3] Quit Application");
        System.out.print("Enter a menu selection: ");
    }

    /**
     * Takes a string of characters and uses the boyer moore algorithm to
     * create a bad character table for by taking each char in the string
     * and assigning whichever value is greater,
     * (patternLength - indexOfChar - 1) or (1).
     *
     * @param pattern a sequence of characters that make a substring.
     * @param patternLength amount of characters in the pattern.
     *
     * @return a map table with character as a key and integer as a value.
     */
    public static Map<Character, Integer> createBadCharTable (String pattern, int patternLength) {
        Map <Character, Integer> badCharTable = new HashMap<>();
        // Loop through pattern and compute algorithm
        for (int i = 0; i < patternLength; i++ ) {
            char currentChar = pattern.charAt(i);
            int maxShift = Math.max(1,  patternLength - i - 1 );
            badCharTable.put(currentChar, maxShift);
        }

        return badCharTable;
    } // End createBadCharTable Method


    // This method searches the text to match the pattern

    /**
     * This method matches your pattern by searching through the text provided for a match
     * and displays all indices of occurrences the pattern is matched with in the text.
     *
     * @param text the main text you wish to search through.
     * @param pattern the specified pattern of characters you wish to find within the actual text.
     * @param patternLength amount of characters in the pattern text.
     * @param textLength amount of characters in the text field.
     * @param badCharTable a map table of keys (character) and values (computed integer).
     */
    public static void patternToTextMatcher(String text, String pattern, int patternLength,
     int textLength, Map<Character, Integer> badCharTable) {
        int shifts;
        boolean foundPattern = false;

        for (int i = 0; i <= textLength - patternLength; i += shifts) {
            shifts = 0;
            // Bypass all inner loops  & continue this loop if character matches
            for (int j = patternLength - 1; j >= 0; j--) {
                // If characters do not match
                if (pattern.charAt(j) != text.charAt(i + j)) {
                    // If non-matching character is in shift table then retrieve key value
                    // If not in shift table, than retrieve default value
                    shifts = badCharTable.getOrDefault(text.charAt(i + j), patternLength - 1);
                    break;
                // If pattern and text matched all the characters.
                } else if (j == 0) {
                    System.out.println("\nstart of occurrence at index: " + i);
                    System.out.println("end of occurrence at index: " + (i + (patternLength - 1)));
                    foundPattern = true;
                    shifts = 1;
                    break;
                }
            } //  End Inner Loop
        } // End Outer Loop

        // If no occurrences were found.
        if (foundPattern == false) {
            System.out.println("\nCannot find any occurrence with the specified pattern.");
        }
    } // End patternToTextMatcher Method


    // Driver
    public static void main(String[] args) {
        Map <Character, Integer> badCharTable = new HashMap<>();
        Scanner userInput = new Scanner(System.in);
        String menuSelection;
        String pattern;
        String text = "Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware, Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana, Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana, Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina, North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina, South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia, Wisconsin, Wyoming";
        int textLength = text.length();


        // Loop until user quits application
        do {
            displayMenu();
            menuSelection = userInput.nextLine();
            // Match user input to selections
            switch (menuSelection) {
                case "1": // Display text of all 50 States of America
                    System.out.println("\n" + text);
                    break;
                case "2": // Input pattern and match to text
                    System.out.print("\n---> Enter the text data you want to find: ");
                    pattern = userInput.nextLine();
                    int patternLength = pattern.length();
                    // Make sure bad character table is clear
                    badCharTable.clear();
                    // Create new bad character table
                    badCharTable = createBadCharTable(pattern, patternLength);
                    // This method searches text to match the pattern
                    patternToTextMatcher(text, pattern, patternLength, textLength, badCharTable);
                    break;
                case "3": // End application
                    break;
                default:
                    System.out.println("\nPlease enter a valid selection.");
            }
        } while (!menuSelection.equals("3"));

    } // End Driver
} // End Main
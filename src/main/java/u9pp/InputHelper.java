package u9pp;

import java.util.*;

public class InputHelper {

    private static List<List<String>> validYesNoInputs = null; 


    public static List<List<String>> getValidYesNoInputs() {
        if(InputHelper.validYesNoInputs == null) {
            InputHelper.validYesNoInputs = new ArrayList<>(); 
            List<String> validYesInputs = Arrays.asList("Y", "ye", "yes", "yess", "ya", "yea");
            List<String> validNoInputs = Arrays.asList("N", "no", "na", "nah");
            InputHelper.validYesNoInputs.add(validYesInputs);
            InputHelper.validYesNoInputs.add(validNoInputs);
        }
        return InputHelper.validYesNoInputs; 
    }

    public static String getValidInput(Scanner scanner, String prompt, List<List<String>> validInputs) {
        while(true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            
            // if input is found in any of the 2d arraylist, 
            // return the .toUpperCase value of the first element of the arraylist it's in
            for(List<String> possibleInputs : validInputs) {
                for(String possibleInput : possibleInputs ) {
                    if(possibleInput.trim().toUpperCase().equals(input)) {
                        return possibleInputs.get(0).trim().toUpperCase();
                    }
                }
            }
            
            System.out.println("Invalid Input. Please try again.");
        }
    }
    
    public static int getValidNumberInput(Scanner scanner, String prompt, int min, int max) {
        while(true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if(input == null || input.length() == 0) {
                System.out.println("No input. Please Try again.");
                continue;
            }
            String numbersOnly = input.replaceAll("[^0-9]", "").trim();
            if(numbersOnly.length() == 0) {
                System.out.println("Please enter a number using the digits 0-9.");
                continue;
            }
            int x = Integer.parseInt(numbersOnly);
            if(x >= min && x <= max) {
                return x;
            } else{
                System.out.println("That number is out of bounds. Please Try again.");
            }
        }
    }
}

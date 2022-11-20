import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import Models.Item;
import Models.MessageResponse;
import Utils.NetworkManager;

public class App {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Wellcome To Our Shop!");

        while (!exit) {
            System.out.println("Please Select Item From The List:");
            System.out.println("==================================");
            System.out.println("ID\tDescription");
            System.out.println("==================================");

            // Get All The Items That Have In Stock From Server
            List<Item> stockItems = NetworkManager.getStock();
            if (stockItems == null) {
                break;
            }
            for (Item item : stockItems) {
                System.out.println(item.getId() + "\t" + item.getDescription());
            }
            System.out.println("==================================");
            System.out.print("Enter Item ID (-1 to exit): ");
            int b = scanner.nextInt();

            if (b == -1) {
                exit = true;
                System.out.println("Thank You For Shopping With Us!");
            } else {
                System.out.println("Getting latest price for item " + b + "...");
                // Get The Price Of The Item From Server
                Double price = NetworkManager.getPrice(b);
                if (price == null) {
                    break;
                }
                if (price > 0) {
                    System.out.println("The price of item " + b + " is " + price);
                    System.out.println("Do you want to buy it? (y/n)");
                    String c = scanner.next();
                    if (c.equals("y")) {
                        System.out.println("Buying item " + b + "...");
                        // Buy The Item From Server
                        MessageResponse result = NetworkManager.buyItem(b);
                        if (result == null) {
                            break;
                        }
                        // If The Item Was Bought Successfully Show Success Message
                        if (result.getStatus().equals("1")) {
                            System.out.println("Item " + b + " bought successfully!");
                            System.out.println("Thank You For Shopping With Us!");
                            System.out.println("Let's Shop Again!");
                            System.out.println("==================================");

                        } else {
                            System.out.println("Item " + b + " could not be bought!");
                            System.out.println("Reason: " + result.getMessage());
                        }

                    } else {
                        System.out.println("Ok, Back to the list");
                    }

                } else {
                    System.out.println("Item " + b + " is not available");
                }
            }

        }
        scanner.close();
        // Exercise 2 Tests
        System.out.println(isValidBracketsString("()"));
        System.out.println(isValidBracketsString("()[]{}"));
        System.out.println(isValidBracketsString("(]"));
        System.out.println(isValidBracketsString("([)]"));
        System.out.println(isValidBracketsString("{[]}"));

    }

    public static boolean isValidBracketsString(String str) {
        char[] arrOfChars = str.toCharArray();
        Stack<Character> charStack = new Stack<>();
        char[] allowedChars = { '(', ')', '[', ']', '{', '}', '<', '>' };

        // Check if first and last char is valid bracket
        if (!(new String(allowedChars).contains(arrOfChars[0] + "")
                && new String(allowedChars).contains(arrOfChars[arrOfChars.length - 1] + ""))) {
            return false;
        }

        for (char x : arrOfChars) {
            if (x == '(' || x == '[' || x == '{' || x == '<') {
                // Push the opening bracket to the stack
                charStack.push(x);
            } else {
                if (charStack.isEmpty()) {
                    // If the stack is empty and the current char is a closing bracket return false
                    return false;
                }
                // Get the last element from the stack and check if the current char is the
                // matching opening bracket
                char lastChar = charStack.pop();
                if (x == ')' && lastChar != '(') {
                    return false;
                } else if (x == ']' && lastChar != '[') {
                    return false;
                } else if (x == '}' && lastChar != '{') {
                    return false;
                } else if (x == '>' && lastChar != '<') {
                    return false;
                }
            }

        }
        // If the stack is empty then the string is valid
        return true;
    }
}

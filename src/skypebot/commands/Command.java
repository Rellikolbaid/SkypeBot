package skypebot.commands;

//import com.skype.*;
import com.skype.ChatMessage;
import com.skype.Chat;
import com.skype.SkypeException;
import java.util.Random;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

public class Command {
    private final Chat group;
    private final String input;
    private final String[] inputArray;
    private final ChatMessage received;
    private final String sender;
    private String foundCommand;
    // Defines list of recognizable commands
    private static final String[] commandList = {
        "!commands", "!roll", "!cointoss", "!google", "!youtube"};
    
    public Command(ChatMessage received, Chat group) throws SkypeException  {
        this.received = received;
        this.group = group;
        // Converts received chat message into string.
        input = received.getContent();
        // Converts received chat message into array of strings for later use
        inputArray = this.input.split("\\s+");
        sender = received.getSenderId();

    }

    public void handleCommands() throws SkypeException {
        if (commandInString()) {
            switch (foundCommand) {
                case "!commands":
                    for (String command : commandList) {
                        group.send("-" + command+ "-");
                    }   break;
                case "!roll":
                    {
                        Random rand = new Random();
                        int randInt = rand.nextInt(99);
                        group.send("SkypeBot: Rolled " + randInt + ". (0-99)");
                        break;
                    }
                case "!cointoss":
                    {
                        Random rand = new Random();
                        int randInt = rand.nextInt(99);
                        if (randInt < 50) {
                            group.send("SkypeBot: Coin toss came up HEADS.");
                        } else {
                            group.send("SkypeBot: Coin toss came up TAILS.");
                        }
                        break;
                    }
                case "!google":
                    {
                        // Cuts the "!google" out of the string so its not in the search.
                        String search = input.substring(7);
                        Google google = new Google();
                        // Makes sure !google is the first thing in the string to avoid problems.
                        if (foundCommand.equals(inputArray[0]))
                            try {
                                google.search(search, group);
                            } catch (UnsupportedEncodingException e) {
                            } catch (IOException e) {
                        }
                        break;
                    }
                case "!youtube":
                    {
                        String search = input.substring(9);
                        YouTube yt = new YouTube();
                        if (foundCommand.equals(inputArray[0])) {
                            try {
                                yt.search(search, group);
                            } catch (UnsupportedEncodingException e) {
                            } catch (IOException e) {
                            }
                        }
                    }
            }
        }
    }
    
    /**
     * Checks last message for !commands. If specified command is 
     * found, return true.
     * @param command
     * @return 
     */
    private boolean commandInString() {
        for (String word : inputArray) {
            for (String command : commandList) {
                if (word.equals(command)) {
                    foundCommand = command;
                    return true;
                }
            }
        }
        return false;
    }
    
    
}

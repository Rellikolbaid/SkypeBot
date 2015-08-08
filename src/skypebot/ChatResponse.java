package skypebot;

import com.skype.Chat;
import com.skype.ChatMessage;
import com.skype.SkypeException;


/**
 *
 * @author Rellikolbaid
 */
public class ChatResponse {
    ChatMessage input;
    String inputString;
    Chat group;
    
    public ChatResponse(ChatMessage cm, Chat group) throws SkypeException {
        input = cm;
        inputString = cm.getContent();
        group = group;
    }
    
    public void handleResponse() throws SkypeException {
        if (inputString.contains("trigger")) {
            group.send("[[TRIGGER WARNING]] " 
                    + input.getSenderDisplayName().toUpperCase() 
                    + " MAY BE TRIGGERED!!");
            
        } else if (inputString.contains("week ago")) {
            group.send("SkypeBot: WEEK AGO");
        }
    }
    
}

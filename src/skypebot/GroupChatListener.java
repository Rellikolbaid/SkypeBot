package skypebot;

import skypebot.commands.Command;
import com.skype.Chat;
import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.SkypeException;

public class GroupChatListener implements ChatMessageListener {
    private final Chat group;
    
    public GroupChatListener(Chat group) {
        this.group = group;
    }

    /**
     * Runs whenever a message is received in Skype. 
     * @param received
     * @throws SkypeException 
     */
    @Override
    public void chatMessageReceived(ChatMessage received) throws SkypeException {
        Command command = new Command(received, group);
        command.handleCommands();
        //TODO: Move this to it's own class
        ChatResponse cr = new ChatResponse(received, group);
        cr.handleResponse();
    }

    /**
     * Runs whenever a message is sent in Skype.
     * @param sent
     * @throws SkypeException 
     */
    @Override
    public void chatMessageSent(ChatMessage sent) throws SkypeException {
        Command command = new Command(sent, group);
        command.handleCommands();
        
        ChatResponse cr = new ChatResponse(sent, group); 
        cr.handleResponse();
    }
    
}

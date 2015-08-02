package skypebot;

//import com.skype.*;

import com.skype.*;

public class GroupChatListener implements ChatMessageListener {
    private int numMembers = 0;
    private final Chat group;
    
    public GroupChatListener(Chat group) {
        this.group = group;
        
        try {
            numMembers = group.getAllMembers().length;
        } catch (SkypeException e) {
        }
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
    }

    /**
     * Runs whenever a message is sent in Skype.
     * @param sent
     * @throws SkypeException 
     */
    @Override
    public void chatMessageSent(ChatMessage sent) throws SkypeException {
        if (!sent.getChat().equals(group))
            return;
    }
    
}

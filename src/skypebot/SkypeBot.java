package skypebot;

/**
 * @author apolywka@gmail.com
 */

import com.skype.SkypeException;
import com.skype.Chat;
import com.skype.Skype;

public class SkypeBot {
    
    public static final String version = "SkypeBot Beta v0.4";

    public static void main(String[] args) throws SkypeException {
        GroupSelector sg = new GroupSelector();
        Chat group = sg.getGroup();
        
        Skype.addChatMessageListener(new GroupChatListener(group));
        
        Skype.setDaemon(false);

        group.send(" === "+ version + "  ===");

        SkypeBotGUI gui = new SkypeBotGUI();
        gui.run();
    }
}

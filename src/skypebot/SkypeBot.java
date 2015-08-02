package skypebot;

/**
 * @author apolywka@gmail.com
 */

import com.skype.SkypeException;
import com.skype.Chat;
import com.skype.Skype;

public class SkypeBot {

    public static void main(String[] args) throws SkypeException {
        SkypeGroup skypeGroup = new SkypeGroup();
        Chat group = skypeGroup.getBookmarked();
        
        Skype.addChatMessageListener(new GroupChatListener(group));

        Skype.setDaemon(false);

        group.send(" === SKYPEBOT BETA v0.2 ====");
        group.send("=== Author: Rellikolbaid ===");
        group.send("=== apolywka@gmail.com ===");

        SkypeBotGUI gui = new SkypeBotGUI();
        gui.run();
    }
    
}

package skypebot;

import com.skype.Chat;
import com.skype.SkypeException;
import com.skype.Skype;


public class SkypeGroup {
    Chat group = null;
    
    // Retrieves list of favorited conversations. 
    public Chat getBookmarked() {
        try {
            for (Chat c : Skype.getAllBookmarkedChats() ) {
                group = c;
            }
        } catch (SkypeException e) {
        }
        return group;
    }
}


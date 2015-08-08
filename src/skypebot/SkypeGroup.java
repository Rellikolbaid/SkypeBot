package skypebot;

import com.skype.Chat;
import com.skype.SkypeException;
import com.skype.Skype;


public class SkypeGroup {
    
    // Retrieves list of favorited conversations. 
    private Chat getBookmarked() {
        Chat group = null;
        try {
            for (Chat c : Skype.getAllBookmarkedChats() ) {
                group = c;
            }
        } catch (SkypeException e) {
        }
        return group;
    }
    
    public Chat getGroup() {
        Chat group = getBookmarked();
        return group;
    }
}


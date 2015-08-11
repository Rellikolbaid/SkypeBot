package skypebot.commands;


import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import com.skype.Chat;
import com.skype.SkypeException;


public class YouTubeSearch {
    private static final String youtube = "http://www.youtube.com/results?search_query=";
    String search;
    String charset = "UTF-8";
    String userAgent = "SkypeBot";
    /**
     * Hacked together youtube search crawler
     * @param search
     * @param group
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    public void search(String search, Chat group) throws UnsupportedEncodingException, IOException {
        String selection = "div#results>ol>li>ol>li>div>div>div.yt-lockup-content>h3>a"; // For jsoup selector
        Elements links = Jsoup.connect(youtube + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(selection);
        
        int count = 0;
        for (Element link : links) {
            count++;
            // limit search from going beyond 4 results
            if (count > 4) {
                break;
            }

            String title = link.text();
            // Builds URL using youtube video ID 
            String url = "Http://youtube.com/watch?v=" + link.absUrl("href");
            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1), "UTF-8");
            
            try {
                group.send(title);
                group.send(url);
            } catch (SkypeException e) {
            }

        }
    }
}

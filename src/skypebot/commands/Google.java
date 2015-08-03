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


public class Google {
    private static final String google = "http://www.google.com/search?q=";
    String search;
    String charset = "UTF-8";
    String userAgent = "SkypeBot";
    
    /**
     * @param search
     * @param group
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    public void search(String search, Chat group) throws UnsupportedEncodingException, IOException {
        Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select("li.g>h3>a");
        int count = 0;
        for (Element link : links) {
            count++;
            // limit search from going beyond 4 results.
            if (count > 4) {
                break;
            }
            
            String title = link.text();
            String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

            // This block filters some unwanted urls
            if (!url.startsWith("http")) {
                continue;
            } else if (!url.contains(".")) {
                continue; 
            }
            
            
            try {
                group.send(title);
                group.send(url);
            } catch (SkypeException e) {
            }
        }
            
    }
    
    
    
}

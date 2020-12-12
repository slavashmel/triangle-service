package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseUtil {
    public static WebDriver driver;

    public static WebDriverWait wait;

    //Server settings
    public static String plmSever = "http://qaapp333.netcracker.com:6330";

    public static String quoteId;
    public static String quoteUrl;

    //Random values
    public static String randomRelationId;
    public static String randomCustomerId;
    public static String randomQuoteName;
    public static String correlationId;

    public long generateRandomLong(int count) {
        long leftLimit = (long) Math.pow(10, count-1);
        long rightLimit = (long) Math.pow(10, count)-1;
        return (leftLimit + (long) (Math.random() * (rightLimit - leftLimit)));
    }

    public String OSDetector () {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "Windows";
        } else if (os.contains("nux") || os.contains("nix")) {
            return "Linux";
        }else if (os.contains("mac")) {
            return "Mac";
        }else if (os.contains("sunos")) {
            return "Solaris";
        }else {
            return "Other";
        }
    }
}

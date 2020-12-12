package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseUtil {
    public static WebDriver driver;
    public static WebDriverWait wait;

    //Server settings
    public static String baseURI = "https://qa-quiz.natera.com";

    public static String triangleId;
    public static float firstSide;
    public static float secondSide;
    public static float thirdSide;

    //Auth
    public static String token = "66058bd0-4783-4f97-9509-ec3f7466ffb2";

    //Random values
    public static String randomRelationId;
    public static String randomCustomerId;
    public static String randomQuoteName;

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

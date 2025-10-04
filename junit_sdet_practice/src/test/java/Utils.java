import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Utils
{
    //if no need to create the object then mathod ke static banaiDibo....
    public static void scroll(WebDriver driverObj, int px)
    {
        //for scroll down to click  the button
        JavascriptExecutor jsScroll  = (JavascriptExecutor) driverObj;
        jsScroll.executeScript("window.scrollTo(0,"+px+")");

    }
}

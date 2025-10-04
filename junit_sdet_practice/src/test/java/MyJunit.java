import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

//JUnit লাইফসাইকেল: @BeforeAll → ড্রাইভার সেটআপ, @Test → টেস্ট চালানো, @AfterAll → ড্রাইভার বন্ধ।
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJunit
{
    /*
    অন্য মেথডে (vIsitUrl) থেকে driverObj এক্সেস করা যাবে না।
     সমাধান: এটাকে class-level variable বানাতে হবে। (WebDriver )) ke global char ono ono mathod pabe nah...
     */

    WebDriver driverObj;

    //যেসব method-এ @BeforeAll লেখা থাকে, সেটা সব test শুরুর আগে একবারই execute হয়।
    //annotation

    @BeforeAll

    //declare a method
    public void setup()
    {
        // using Selenium package import
        driverObj = new ChromeDriver(); //this is overRidding concept OOP....
        //LEFT SIDE PARENT CLASS AND RIGHT SIDE CHILD CLASS
        //ChromeDriver() COMES FROM WebDriver..
        //Backend e charomdriver extend form webdriver
        //WebDriver a onk gulo driver ache like chromdriver and firefox driver ,edge etc
        //amra akhon child ke niya kaj krtasi ChromeDriver(); -> holo WebDriver ar e akta child
        //Left side parent  calss = right side child Class()
        // so overall overRiding hoche aykhne...

        driverObj.manage().window().maximize(); // for automation purpose browser maximize..
        driverObj.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Using Implicit water..

        /*
        So Selenium has 3 main waits in Java:
                Implicit Wait = Tells Selenium to wait for all elements before throwing error.
                Explicit Wait = Waits for a specific element/condition (e.g., visible, clickable). for test case manu show(after login)
                Fluent Wait   = Like explicit wait but with custom polling and exception handling.Custom retry for locator
         */
        // Now something's about Locators
        /*
            How Waits + Locators work together:
            Locators (By.id, By.name, By.xpath, etc.) → tell Selenium what element to find.
            locators particular webpage modhe webElements.like tikanah kon jaigai ase......address
            Waits → tell Selenium how long and under what condition to wait before failing if that element isn’t ready.
         */

        //SOME INTERVIEW QUESTION
        /*
            ❓ প্রশ্ন
        যদি Explicit Wait এর সময় Implicit Wait থেকে কম দেওয়া হয় → সমস্যা হবে কি?
        যদি Implicit Wait কে default timer ধরি, তাহলে পরবর্তীতে Explicit Wait কেন কম দেওয়া যাবে না?
        কেন আমি 31s দিতে পারি, কিন্তু 29s বা 30s দিতে পারি না, যদি Implicit 30s fixed থাকে?

        ✅ উত্তর (সংক্ষেপে)
        Conflict হয়
        Implicit Wait globally apply হয় → সব element find করার সময় minimum ওই সময় পর্যন্ত wait করবে।
        তাই Explicit Wait কম হলে → mismatch হয়ে unexpected timeout আসতে পারে।

        কেন Explicit বড় হতে পারে, ছোট না হতে পারে
        Implicit 30s মানে → driver সর্বনিম্ন 30s পর্যন্ত element খুঁজতে চেষ্টা করবে।
        Explicit যদি 31s হয় → চলবে (কারণ implicit এর পরেও explicit condition check করতে পারবে)।
        কিন্তু Explicit যদি 29s বা 30s হয় → implicit এর 30s delay override হয়ে যাবে না, তাই কার্যকরভাবে explicit এর কোনো কাজ হবে না।

        Best Practice
        Implicit Wait = ছোট/শূন্য (অften 0 বা 2s max)।
        Explicit/Fluent Wait = সব control condition এর জন্য ব্যবহার করতে হবে।
        👉 মনে রাখার লাইন:
        “Implicit = base delay, Explicit = smart delay. Explicit ছোট দিলে কাজ হবে না, বড় দিলে কাজ হবে।”
         */

    }

        @Test
        //Declare Mathod to visiting a side..
        public void vIsitUrl()
        {
            driverObj.get("https://demoqa.com/");
            String titleActual  =  driverObj.getTitle();
            String tileExpected = "DEMOQA";
            Assertions.assertEquals(tileExpected,titleActual);
        }

/*
 এক কথায় মনে রাখা:
    @BeforeAll = Start
    @BeforeEach = Setup
    @Test = Test (মূল চেক)
    @AfterEach = Cleanup
    @AfterAll = End
🔹 পুরো JUnit Lifecycle (এক লাইনে)
   @BeforeAll → @BeforeEach → @Test → @AfterEach → @AfterAll
*/

/*
Selenium-এ মোট 6 টি প্রধান locator আছে:
locator :
Css Selector/Locator = faster
    id
    name
    class
    tag
    LinkText
    attribute
    what is type???
    type = attribute

xpath: 2 types
    relative xpath
    absolute xpath (NOT RECOMMENDED)
absolute xpath = exact location
/html/body/div/div[1]/div/
*/

    //using ID
    @Test
    public void creatUser()
    {
        // visited everytime
        driverObj.get("https://demoqa.com/text-box");
        driverObj.findElement(By.id("userName")).sendKeys("MD Salahin");

    }

    //using Class Name
    @Test
    public void createEmail()
    {
        driverObj.get("https://demoqa.com/text-box");
        driverObj.findElements(By.className("form-control")).get(0).sendKeys("MD SALAHIN Class Name 0 index");
        driverObj.findElements(By.className("form-control")).get(1).sendKeys("salahin1999@gmail.com");
        driverObj.findElements(By.className("form-control")).get(2).sendKeys("MIRPUR Current Address");
        driverObj.findElements(By.className("form-control")).get(3).sendKeys("MIRPUR DHAKA Parmanent Address");

    }


    @DisplayName("Verify that admin can create new user")
    // Now For Standard Practice
    @Test
    public void StandardPractice()
    {
        driverObj.get("https://demoqa.com/text-box");
        //can we use loop here?? and how ??
        List<WebElement> UserFromObject  =   driverObj.findElements(By.className("form-control"));
        UserFromObject.get(0).sendKeys("MD SALAHIN Array List");
        UserFromObject.get(1).sendKeys("salahin1999@gmail.com");
        UserFromObject.get(2).sendKeys("MIRPUR Current Array List");
        UserFromObject.get(3).sendKeys("MIRPUR DHAKA Parmanent Address Array List");

        //for scroll down comoes from utils for ReuseAble
        Utils.scroll(driverObj,500);

        //for button
        driverObj.findElements(By.cssSelector("[type = button]")).get(1).click(); //using type
        //if button access to id
        //driverObj.findElement(By.id("submit")).click();
        //for tagname
        driverObj.findElements(By.tagName("button")).get(1).click();

        //now adding asertion i mean value read after click the button part..........
        List<WebElement> userInfo =   driverObj.findElements(By.tagName("p"));
        String userNameActual  =  userInfo.get(0).getText();
        String userEmailActual  =  userInfo.get(1).getText();
        String userCurrentAddressActual  =  userInfo.get(2).getText();
        String userPermanentAddressActual  =  userInfo.get(3).getText();

        String userNameExpected  = "MD SALAHIN Array List";
        String userEmailExpected  = "salahin1999@gmail.com";
        String userCurrentAddressExpected  = "MIRPUR Current Array List";
        String userPermanentAddressExpected  = "MIRPUR DHAKA Parmanent Address Array List";

        Assertions.assertEquals(userNameActual.contains(userNameExpected),true);
        Assertions.assertEquals(userEmailActual.contains(userEmailExpected),true);
        Assertions.assertEquals(userCurrentAddressActual.contains(userCurrentAddressExpected),true);
        Assertions.assertEquals(userPermanentAddressActual.contains(userPermanentAddressExpected),true);



    }
   // what is array list??
 /*
    aykhen elements aykhne ArrayList Return kortase
    akta webpage modhe jae kisu thakbe seta hoche webElemnets...
    weblements akta NON Primitive data types..
  */




    @AfterAll
    public void quitDriver()
    {
        driverObj.quit(); //driver kill
        // driverObj.close(); //only browser take close kore dibe..here backend threade active
    }










}

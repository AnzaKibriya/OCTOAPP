package Pages;

import appiumSupport.AppiumBaseClass;
import org.openqa.selenium.support.PageFactory;

public class Pages_Handle {

    public static HandlePermissons HandlePermissons() {
        HandlePermissons pg = new HandlePermissons(AppiumBaseClass.driver);
        PageFactory.initElements(AppiumBaseClass.driver, pg);
        return pg;
    }

}

package mp;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * @author wufeng
 * @date 2022/1/5 10:27
 */
public class QuestionManage extends LoginPortal {

    static WebDriver driver;

    //搜索
    public static void searchQ(){

    }


    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {//校验是否跳转成功
                    driver.get("http://app.test.pdmiryun.com/mp/question/manage");//打开媒体号共享页面
                    Thread.sleep(2000);
                } else break;
            }

            if (!driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a")).getText().contains("爱富县")) {
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a"))).perform();//悬浮到站点名，打开站点图层
                Thread.sleep(500);
                driver.findElement(By.linkText("爱富县")).click();//选择切换到爱富县
                Thread.sleep(2000);
                driver.get("http://app.test.pdmiryun.com/mp/question/manage");//再次切换到媒体号共享
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

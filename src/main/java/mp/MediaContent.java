package mp;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2021/12/29 15:21
 */

public class MediaContent extends LoginPortal {

    static WebDriver driver;

    //内容下线
    public static void offline() throws InterruptedException {

        searchAuto("吴枫");//搜索测试稿件

        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@class='layui-table my-table cms-float-pic']/tbody/tr"))) {//判断是否有数据
            List<WebElement> acticles = driver.findElements(By.xpath("//table[@class='layui-table my-table cms-float-pic']/tbody/tr"));//获取数据列表
            for (int i = 0; i < acticles.size(); i++) {
                if (acticles.get(i).findElement(By.xpath("td[6]")).getText().equals("已发布")) {//判断状态是否是已发布的
                    acticles.get(i).findElement(By.xpath("td[1]/a/i")).click();//选中已发布的稿件
                    Thread.sleep(100);
                    driver.findElement(By.xpath("//div[@class='search-right']/a[@id='offline']")).click();//点击下线
                    System.out.println("~~~ offline()，内容下线，执行成功 ~~~");
                    Thread.sleep(3000);
                    break;
                }
            }
        } else System.out.println("没有测试数据");
    }

    //内容发布
    public static void publish() throws InterruptedException {
        searchAuto("吴枫");//搜索测试稿件

        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@class='layui-table my-table cms-float-pic']/tbody/tr"))) {//判断是否有返回测试数据
            List<WebElement> acticles = driver.findElements(By.xpath("//table[@class='layui-table my-table cms-float-pic']/tbody/tr"));//获取测试数据列表
            for (int i = 0; i < acticles.size(); i++) {
                if (!acticles.get(i).findElement(By.xpath("td[6]")).getText().equals("已发布")) {//判断状态是否不是已发布的
                    acticles.get(i).findElement(By.xpath("td[1]/a/i")).click();//选择未发布的数据
                    Thread.sleep(200);
                    driver.findElement(By.xpath("//div[@class='search-right']/a[@id='publish']")).click();//点击发布
                    System.out.println("~~~ publish()，内容发布，执行成功 ~~~");
                    Thread.sleep(3000);
                    break;
                }
            }
        } else System.out.println("没有测试数据");
    }

    //内容删除
    public static void delete() throws InterruptedException {
        searchAuto("吴枫");//搜索测试稿件

        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@class='layui-table my-table cms-float-pic']/tbody/tr"))) {//判断是否有返回测试数据
            List<WebElement> acticles = driver.findElements(By.xpath("//table[@class='layui-table my-table cms-float-pic']/tbody/tr"));//获取测试数据列表
            acticles.get(0).findElement(By.xpath("td/a/i")).click();//选择第一条数据
            Thread.sleep(200);
            driver.findElement(By.xpath("//div[@class='search-right']/a[@id='delete']")).click();//点击删除
            Thread.sleep(500);
            driver.findElement(By.className("layui-layer-btn0")).click();//确认删除
            System.out.println("~~~ delete()，内容删除，执行成功 ~~~");
            Thread.sleep(3000);
        } else System.out.println("没有测试数据");
    }

    //选签
    public static void push() throws InterruptedException {
        searchAuto("吴枫");//搜索自动化测试用的稿件数据
        String acticleName = "";//初始化签发稿件名称
        Boolean getTestChannel = false;//是否签发到测试成功
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@class='layui-table my-table cms-float-pic']/tbody/tr"))) {//校验是否有数据
            List<WebElement> acticles = driver.findElements(By.xpath("//table[@class='layui-table my-table cms-float-pic']/tbody/tr"));//获取测试数据列表
            for (int i = 0; i < acticles.size(); i++) {
                if (acticles.get(i).findElement(By.xpath("td[6]")).getText().equals("已发布")) {//判断状态是否是已发布的
                    acticleName = acticles.get(i).findElement(By.xpath("td[3]/a")).getText();//被签发稿件名称
                    acticles.get(i).findElement(By.xpath("td/a/i")).click();//选择第一条数据
                    Thread.sleep(500);
                    driver.findElement(By.xpath("//div[@class='search-right']/a[@id='push']")).click();//点击选签
                    Thread.sleep(500);
                    driver.findElement(By.cssSelector("input.layui-input.myKeyword2")).sendKeys("auto");//自动化要签发的频道搜索关键词
                    driver.findElement(By.cssSelector("button.layui-btn.layui-btn-primary.search2")).click();//点击搜索
                    Thread.sleep(500);
                    getTestChannel = CommonMethod.getTestChannel(driver);//选择测试频道

                    if (getTestChannel) break;//已选中签发频道则跳出
                }
            }
            if (getTestChannel) {//如果签发成功了
                driver.findElement(By.cssSelector("button.ll-btn.btn-yes.fl")).click();//点击确定签发
                System.out.println("~~~ push()，内容选签，执行成功。稿件“" + acticleName + "”签发成功 ~~~");
            } else System.out.println("没有找到auto签发频道");
        }
        Thread.sleep(3000);
    }


    //搜索测试内容
    public static void searchAuto(String key) throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='manus-tab']/span[2]")).click();//切换到内容管理
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='search-right']/input")).clear();//清空搜索框
        driver.findElement(By.xpath("//div[@class='search-right']/input")).sendKeys(key);//录入搜索关键词
        driver.findElement(By.cssSelector("i.searBtn2.layui-icon.layui-icon-search2")).click();//搜索
        Thread.sleep(3000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {//校验是否跳转成功
                    driver.get("http://app.test.pdmiryun.com/mp/mpContent/contentList");
                    Thread.sleep(2000);
                } else break;
            }

            if (!driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a")).getText().contains("爱富县")) {
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a"))).perform();
                Thread.sleep(500);
                driver.findElement(By.linkText("爱富县")).click();
                Thread.sleep(2000);
                driver.get("http://app.test.pdmiryun.com/mp/mpContent/contentList");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

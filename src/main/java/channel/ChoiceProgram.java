package channel;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


/**
 * @author wufeng
 * @date 2021/12/21 9:31
 */
public class ChoiceProgram extends LoginPortal {

    static WebDriver driver;

    //添加分类
    public static void addClass() throws InterruptedException {
        driver.findElement(By.cssSelector("button.layui-btn.layui-btn-primary.add-classify-btn")).click();//点击添加分类
        Thread.sleep(500);
        driver.findElement(By.name("title")).sendKeys("autoTest");//录入分类名称
        Thread.sleep(500);
        driver.findElement(By.className("layui-layer-btn0")).click();//保存分类
        Thread.sleep(2000);

        if (CommonMethod.isJudgingElement(driver,By.cssSelector("div.layui-layer.layui-layer-page.redSkin")))//判断是否添加失败
            driver.findElement(By.className("layui-layer-btn1")).click();//添加失败则点击取消按钮
        Thread.sleep(3000);
    }

    //删除分类
    public static void delClass() throws InterruptedException {
        String className;//分类名称
        Boolean isDel = false;//是否被删除了
        Actions actions = new Actions(driver);
        List<WebElement> classes = driver.findElements(By.xpath("//ul[@id='classifyList']/li"));//获取分类列表
        if (classes.size() > 0) {//有分类数据
            for (int i = 0; i < classes.size(); i++) {
                className = classes.get(i).getText();//获取各分类的分类名
                if (className.contains("autoTest")) {//获取名称包含“autoTest”的分类
                    actions.moveToElement(classes.get(i)).perform();//光标悬浮
                    Thread.sleep(500);
                    classes.get(i).findElement(By.className("classify-del")).click();//点击删除
                    Thread.sleep(500);
                    driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
                    Thread.sleep(2000);
                    System.out.println(className + " 分类已被删除");
                    isDel = true;
                    break;
                }
            }
            if (!isDel) System.out.println("没有autoTest分类");
        } else System.out.println("没有分类");
        Thread.sleep(3000);
    }

    //设置分类
    public static void setClass() throws InterruptedException {
        Actions actions = new Actions(driver);
        String pname;//节目名称
        int type = 0;
        List<WebElement> classesL, classesR;//设置分类图层的左侧的分类、右侧的分类
        List<WebElement> elements = driver.findElements(By.xpath("//ul[@class='image-list clear-fix']/li"));//获取节目列表
        if (elements.size() > 0) {//有节目数据
            pname = elements.get(0).findElement(By.xpath("div/div[@class='img-info']/h3")).getText();//获取节目名称
            elements.get(0).findElement(By.xpath("//div[@class='img-content']/div[@class='img-opers']/a[@class='setCategory']")).click();//对第一各节目进行设置分类
            Thread.sleep(500);
            classesL = driver.findElements(By.xpath("//ul[@class='category-list clear-fix']/li"));//设置分类图层，左侧分类list
            classesR = driver.findElements(By.xpath("//div[@class='category-right']/ul/li"));//设置分类图层，右侧分类list
            if (classesR.size() > 0) {//右侧已添加的分类有数据
                for (int i = 0; i < classesR.size(); i++) {
                    if (classesR.get(i).getText().contains("auto")) {//取分类名称有auto的分类
                        actions.moveToElement(classesR.get(i)).perform();//光标悬浮在该分类
                        classesR.get(i).findElement(By.className("classify-del")).click();//点击删除
                        Thread.sleep(500);
                        type = 2;//标识已执行了取消分类
                        System.out.println(pname + "取消auto分类");
                        Thread.sleep(2000);
                        break;
                    }
                }
            }
            if (classesL.size() > 0 && type != 2) {//左侧分类数据非空，并且未执行取消分类
                for (int i = 0; i < classesL.size(); i++) {
                    if (classesL.get(i).getText().contains("auto")) {//获取左侧图层中名称有auto的分类
                        classesL.get(i).click();//点击该分类
                        Thread.sleep(500);
                        type = 1;//标识已执行了添加分类
                        System.out.println(pname + "添加auto分类");
                        Thread.sleep(2000);
                        break;
                    }
                }
            }
            if (type != 0)//已执行过取消分类或者添加分类
                driver.findElement(By.className("layui-layer-btn0")).click();//进行保存
            else
                System.out.println("没有auto分类可设置");
        } else System.out.println("没有精选节目");
        Thread.sleep(3000);
    }

    //取消精选
    public static void cancelChoice() throws InterruptedException {
        String pname;//节目名称
        Boolean type = false;//是否已执行过取消精选
        List<WebElement> elements = driver.findElements(By.xpath("//ul[@class='image-list clear-fix']/li"));//获取节目list
        if (elements.size() > 0) {//节目非空
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).findElement(By.xpath("div/div[@class='img-info']/p")).getText().contains("auto")) {//节目名称中包含auto的数据
                    pname = elements.get(i).findElement(By.xpath("div/div[@class='img-info']/h3")).getText();//获取该节目名称
                    elements.get(i).findElement(By.xpath("//div[@class='img-content']/div[@class='img-opers']/a[@class='delSelect']")).click();//点击取消精选
                    Thread.sleep(500);
                    driver.findElement(By.className("layui-layer-btn0")).click();//确定
                    type = true;
                    System.out.println(pname + "被取消精选");
                    Thread.sleep(2000);
                    break;
                }
            }
            if (!type) System.out.println("没有来源是自动化auto分类的数据可取消精选");
        } else System.out.println("没有精选节目");
        Thread.sleep(3000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver,By.tagName("header"))) {
                    driver.get("http://app.test.pdmiryun.com/rft/channel/choiceprogram");
                    Thread.sleep(2000);
                } else break;
            }

            if (!driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a")).getText().contains("爱富县")) {
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.className("nav-right"))).perform();
                Thread.sleep(500);
                driver.findElement(By.linkText("爱富县")).click();
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

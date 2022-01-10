package channel;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.List;

/**
 * @author wufeng
 * @date 2021/12/22 11:26
 */
public class Proguid extends LoginPortal {

    static WebDriver driver;

    //节目单添加节目
    public static void addProgram() throws InterruptedException {
        List<WebElement> channels = driver.findElements(By.xpath("//ul[@class='ll-card-body channelTV']/li"));
        WebElement tdEdit;
        Actions actions = new Actions(driver);
        int num;
        if (channels.size() > 0)
            for (int i = 0; i < channels.size(); i++) {
                if (channels.get(i).getText().contains("auto")) {
                    channels.get(i).click();
                    Thread.sleep(500);

                    if (CommonMethod.isJudgingElement(driver,By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody"))) {
                        num = driver.findElements(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr")).size();
                    } else num = 0;
                    actions.contextClick(driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']"))).perform();
                    Thread.sleep(500);
                    driver.findElement(By.className("add")).click();
                    //录入标题
                    tdEdit = driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + (num + 1) + "]/td[@data-field='title']"));
                    tdEdit.click();
                    Thread.sleep(100);
                    tdEdit.findElement(By.xpath("textarea")).sendKeys("autoTest" + System.currentTimeMillis());
                    //录入开始时间
                    tdEdit = driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + (num + 1) + "]/td[@data-field='startTimeStr']"));
                    tdEdit.click();
                    Thread.sleep(100);
                    tdEdit.findElement(By.xpath("input")).sendKeys("08:10");
                    //录入结束时间
                    tdEdit = driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + (num + 1) + "]/td[@data-field='endTimeStr']"));
                    tdEdit.click();
                    Thread.sleep(100);
                    tdEdit.findElement(By.xpath("input")).sendKeys("10:20");
                    Thread.sleep(500);

                    driver.findElement(By.id("saveAllData")).click();
                    System.out.println("~~~ addProgram() 添加节目单，执行成功~~~");
                    Thread.sleep(2000);
                    break;
                }
            }
        else System.out.println("没有电视频道数据");
        Thread.sleep(3000);
    }

    //节目单删除节目
    public static void delProgram() throws InterruptedException {
        List<WebElement> channels = driver.findElements(By.xpath("//ul[@class='ll-card-body channelTV']/li"));
        if (channels.size() > 0)
            for (int i = 0; i < channels.size(); i++) {
                if (channels.get(i).getText().contains("auto")) {
                    channels.get(i).click();
                    Thread.sleep(500);
                    if (CommonMethod.isJudgingElement(driver,By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody"))) {
                        driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[1]/td/div/div/i")).click();
                        Thread.sleep(500);
                        driver.findElement(By.cssSelector("button.fr.shanc-btn.layui-btn.layui-btn-primary")).click();
                        Thread.sleep(500);
                        System.out.println("~~~delProgram()，删除节目单成功~~~");
                        break;
                    } else System.out.println("节目单为空");
                }
            }
        else System.out.println("没有电视频道");
        Thread.sleep(3000);
    }

    //复用到第二天
    public static void multiplexing() throws InterruptedException {
        List<WebElement> channels = driver.findElements(By.xpath("//ul[@class='ll-card-body channelTV']/li"));//获取频道list
        List<WebElement> weeks, dates;//日历控件，获取周list、每周日期list
        Boolean status = false;
        if (channels.size() > 0)
            for (int i = 0; i < channels.size(); i++) {
                if (channels.get(i).getText().contains("auto")) {
                    channels.get(i).click();
                    Thread.sleep(500);
                    if (CommonMethod.isJudgingElement(driver,By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr"))) {
                        driver.findElement(By.xpath("//div[@class='layui-table-header']/table/thead/tr/th/div/div/i")).click();
                        driver.findElement(By.cssSelector("button.fr.multiplexing-btn.layui-btn.layui-btn-primary")).click();
                        Thread.sleep(500);
                        driver.findElement(By.name("toDate")).click();
                        Thread.sleep(500);
                        //复用到第二天
                        weeks = driver.findElements(By.xpath("//div[@class='layui-laydate-content']/table/tbody/tr"));
                        for (int j = 0; j < weeks.size(); j++) {
                            if (!status) {
                                dates = weeks.get(j).findElements(By.xpath("td"));
                                for (int d = 0; d < dates.size(); d++) {
                                    if (dates.get(d).getAttribute("class").equals("layui-this")) {
                                        System.out.println("~~~multiplexing()，复制节目单，执行成功。复用到" + dates.get(d + 1).getText() + "日~~~");
                                        dates.get(d + 1).click();
                                        status = true;
                                        break;
                                    }
                                }
                            } else break;
                        }
                        Thread.sleep(500);
                        driver.findElement(By.className("layui-layer-btn0")).click();
                        Thread.sleep(2000);
                    }
                    break;
                }
            }
        else System.out.println("没有频道数据");
        Thread.sleep(3000);
    }

    //上传视频
    public static void upload() throws InterruptedException, IOException {
        List<WebElement> channels = driver.findElements(By.xpath("//ul[@class='ll-card-body channelTV']/li"));//获取频道list
        Boolean needUP = false;//是否需要上传视频
        int trNo = 1;//默认对第一条进行上传

        if (channels.size() > 0)//判断是否有频道数据
            for (int i = 0; i < channels.size(); i++) {
                if (channels.get(i).getText().contains("auto")) {//判断是否有auto名称的自动化频道
                    channels.get(i).click();//激活自动化频道
                    Thread.sleep(500);
                    if (CommonMethod.isJudgingElement(driver,By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr"))) {//判断是否有节目单数据
                        List<WebElement> trs = driver.findElements(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr"));//获取节目单数据
                        for (int r = 1; r < trs.size() + 1; r++) {
                            if (CommonMethod.isJudgingElement(driver,By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + r + "]/td[@data-field='coverImg']/div/span")))//判断节目单是否需要上传视频
                            {
                                needUP = true;//需要上传
                                trNo = r;//需要对哪一条进行上传
                                break;
                            }
                        }
                    }
                    if (!needUP) addProgram();//添加节目单，新添加的节目默认显示在第一条
                    Thread.sleep(3000);
                    driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + trNo + "]/td[@data-field='coverImg']/div/span")).click();//对有“上传”操作的点击上传

                    //上传视频
                    Thread.sleep(1000);
                    for (int j = 1; j < 20; j++) {//上传视频iframe图层参数
                        if (CommonMethod.isJudgingElement(driver,By.id("layui-layer-iframe" + j))) {//判断有效的上传视频图层的iframe名称，j是动态的
                            driver.switchTo().frame("layui-layer-iframe" + j);//切换到上传视频图层iframe页面
                            //上传封面图
                            driver.findElement(By.cssSelector("i.layui-icon.layui-icon-add-1")).click();//点击添加封面
                            Thread.sleep(1000);
                            driver.switchTo().defaultContent();//切换回默认页面
                            Thread.sleep(1000);
//                            CommonMethod.getImg(driver);//在线资源库方式，目前行不通T_T
                            CommonMethod.uploadImg(driver);//本地上传方式上传图片

                            Thread.sleep(2000);
                            driver.switchTo().frame("layui-layer-iframe" + j);//切换到上传视频图层iframe页面

                            //上传视频-在线资源库
                            driver.findElement(By.cssSelector("input.layui-btn.base-btn-search.layui-btn-primary.btn_getSource")).click();//点击视频的在线资源库
                            Thread.sleep(500);
                            driver.switchTo().defaultContent();//切换返回到默认页面
                            Thread.sleep(500);
                            getVideo(j + 2);//在线资源库上传视频，传参根据页面实际iframe动态获取：如在线资源库方式上传封面图，则传参j+1；如本地上传封面图，则j+2
                            Thread.sleep(2000);

                            driver.findElement(By.className("layui-layer-btn0")).click();//保存，完成
                            System.out.println("~~~upload()，上传视频，执行成功~~~");
                            Thread.sleep(2000);
                            break;
                        }
                    }
                    break;
                }
            }
        Thread.sleep(3000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver,By.tagName("header"))) {
                    driver.get("http://app.test.pdmiryun.com/rft/channel/proguid");
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

    //获取视频
    public static void getVideo(int iframeNum) throws InterruptedException {
        Thread.sleep(1000);

        WebElement iframe = driver.findElement(By.id("layui-layer-iframe" + iframeNum));
        driver.switchTo().frame(iframe);//切换到资源库frame进行操作

        Thread.sleep(2000);
        List<WebElement> videos = driver.findElements(By.xpath("//*[@id='cont_1']/div[@class='mtl_dataBox']/div/ul/li"));

        //判断是否有音频素材，无素材则结束；有素材>则选择第1个图片
        if (videos.size() > 0) {
            driver.findElement(By.xpath("//*[@id='cont_1']/div[@class='mtl_dataBox']/div/ul/li[1]/div")).click();//选择第一张图片
            driver.findElement(By.cssSelector("button.mtl_btn.yes")).click();//融媒页确认添加图片返回
        } else {
            System.out.println("没有可用素材！");
            driver.findElement(By.cssSelector("button.mtl_btn.cancel")).click();//融媒页关闭返回
        }
        driver.switchTo().parentFrame();//退出当前iframe
        Thread.sleep(500);

    }

}

package channel;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Calendar;
import java.util.List;

/**
 * @author wufeng
 * @date 2021/12/14 17:20
 */
public class TvProgram extends LoginPortal {

    static WebDriver driver;

    //创建电视频道
    public static void createTV() throws InterruptedException {
        driver.findElement(By.cssSelector("button.fl.add-channel.ll-btn-item.layui-btn.layui-btn-primary.layui-btn-sm")).click();//点击新建频道
        Thread.sleep(500);
        driver.findElement(By.name("channelName")).sendKeys("autotest" + Calendar.getInstance().getTimeInMillis());//标题autotest+时间戳
        driver.findElement(By.name("copyrightParty")).sendKeys("中央电视台");
        driver.findElement(By.name("code")).sendKeys("at" + Calendar.getInstance().getTimeInMillis());//code唯一，加时间戳
        driver.findElement(By.cssSelector("div.addimg.addimg-btn")).click();//在线资源库
        Thread.sleep(500);
        CommonMethod.getImg(driver);//获取在线资源库图片
        driver.findElement(By.name("url")).sendKeys("http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8");//设置url
        Thread.sleep(500);
        driver.findElement(By.className("layui-layer-btn0")).click();//保存
        System.out.println("~~~ createTV() 创建电视频道，执行成功~~~");
        Thread.sleep(3000);
    }

    //编辑电视频道
    public static void editTV() throws InterruptedException {
        WebElement li = getAutoData();//获取自动化建的测试频道
        Thread.sleep(500);

        if (li != null) {//判断是否有自动化创建的频道
            li.click();//激活自动化建的测试频道
            driver.findElement(By.cssSelector("button.fl.edit-channel.ll-btn-item.layui-btn.layui-btn-primary.layui-btn-sm")).click();//点击编辑
            Thread.sleep(500);
            driver.findElement(By.name("channelName")).sendKeys("update");//修改名称，增加update后缀
            driver.findElement(By.className("layui-layer-btn0")).click();//点击保存
            System.out.println("~~~ editTV() 编辑电视频道，执行成功~~~");
        } else System.out.println("没有自动化创建的电视频道");//未找到有自动化创建的频道
        Thread.sleep(3000);
    }

    //删除电视频道，系统有验证码，不可执行！！！2021-12-15
    public static void delTV() throws InterruptedException {
        WebElement li = getAutoData();//获取自动化建的测试频道
        Thread.sleep(500);

        if (li != null) {//判断是否有自动化创建的频道
            li.click();//激活自动化建的测试频道
            driver.findElement(By.cssSelector("button.fl.del-channel.ll-btn-item.layui-btn.layui-btn-primary.layui-btn-sm")).click();//点击删除
            Thread.sleep(500);
            //前一步有验证码录入，顾自动化无法执行
            driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
        } else System.out.println("没有自动化创建的电视频道");
        Thread.sleep(3000);
    }

    //电视频道进行上线或下线
    public static void turnOnOrOffTV() throws InterruptedException {
        WebElement li = getAutoData();//获取自动化建的测试频道
        Boolean isOnline = true;//是否在线
        Thread.sleep(500);

        if (li != null) {//判断是否有自动化创建的频道
            String title = li.findElement(By.className("ll-channel-tit")).getText();//获取频道名称
            if (CommonMethod.isJudgingElement(driver,By.xpath(li + "/div/img[@class='offline-icon']"))) {//判断是否在线
                isOnline = false;//存在“offline-icon”标签则已下线
            }
            li.click();//激活自动化创建的频道
            driver.findElement(By.cssSelector("button.fl.onOrOff-channel-pd.ll-btn-item.layui-btn.layui-btn-primary.layui-btn-sm")).click();//点击上线/下线
            if (isOnline) System.out.println("~~~ turnOnOrOffTV()， " + title + " 下线成功~~~");//原在线的则提示下线成功
            else System.out.println("~~~ turnOnOrOffTV()， " + title + " 上线成功~~~");//原下线的则提示上线成功

        } else System.out.println("没有自动化创建的电视频道");
        Thread.sleep(3000);
    }

    //测试频道添加节目
    public static void addProgram() throws InterruptedException {
        WebElement li = getAutoData();//获取自动化建的测试频道
        Thread.sleep(1000);

        if (li != null) {//判断是否有自动化创建的频道
            li.click();//激活自动化创建的频道
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("button.fr.add-program.ll-btn-item.layui-btn.layui-btn-primary.ll-btn")).click();//点击添加节目
            Thread.sleep(500);
            driver.findElement(By.name("programName")).sendKeys("autotest节目" + Calendar.getInstance().getTimeInMillis());//设置节目名称
            driver.findElement(By.xpath("//div[@id='addprogram']/div[4]/div/input")).sendKeys("at" + Calendar.getInstance().getTimeInMillis());//设置节目编码
            driver.findElement(By.xpath("//div[@id='addprogram']/div[5]/div/div")).click();//设置节目封面图
            Thread.sleep(1000);
            CommonMethod.getImg(driver);//获取资源库图片
            if (CommonMethod.isJudgingElement(driver,By.className("upload-img-loading"))) Thread.sleep(2000);
            driver.findElement(By.className("layui-layer-btn0")).click();//保存
            System.out.println("~~~ addProgram() 创建节目，执行成功~~~");
        } else System.out.println("没有自动化创建的电视频道");
        Thread.sleep(3000);
    }

    //测试频道编辑节目
    public static void editProgram() throws InterruptedException {
        WebElement li = getAutoData();//获取自动化建的测试频道
        Thread.sleep(500);

        if (li != null) {//判断是否有自动化创建的频道
            li.click();//激活自动化创建的频道
            Thread.sleep(500);

            List<WebElement> pros = driver.findElements(By.xpath("//ul[@class='clearfix act-cont']/li"));//获取节目列表
            if (pros.size() > 0) {//判断是否有节目
                pros.get(0).click();//点击第一个节目
                driver.findElement(By.cssSelector("button.fr.edit-program.ll-btn-item.layui-btn.layui-btn-primary.ll-btn")).click();//点击编辑
                driver.findElement(By.name("programName")).sendKeys("update");//修改名称，增加后缀“update”
                driver.findElement(By.className("layui-layer-btn0")).click();//保存
                System.out.println("~~~ editProgram() 编辑节目，执行成功~~~");
            } else System.out.println("测试频道下没有节目");//没有节目
        } else System.out.println("没有自动化创建的电视频道");
        Thread.sleep(2000);
    }

    //删除节目
    public static void delProgram() throws InterruptedException {
        WebElement li = getAutoData();//获取自动化建的测试频道
        Thread.sleep(500);

        if (li != null) {//判断是否有自动化创建的频道
            li.click();//激活自动化创建的频道
            Thread.sleep(500);

            List<WebElement> pros = driver.findElements(By.xpath("//ul[@class='clearfix act-cont']/li"));//获取节目列表
            if (pros.size() > 0) {//判断是否有节目
                pros.get(0).click();//获取第一个节目
                driver.findElement(By.cssSelector("button.fr.del-program.ll-btn-item.layui-btn.layui-btn-primary.ll-btn")).click();//点击删除节目
                driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
                System.out.println("~~~ delProgram() 删除节目，执行成功~~~");
            } else System.out.println("测试频道下没有节目");
        } else System.out.println("没有自动化创建的电视频道");
        Thread.sleep(2000);
    }

    //节目展示上线下线
    public static void showProgram() throws InterruptedException {
        WebElement li = getAutoData();//获取自动化建的测试频道
        Actions actions = new Actions(driver);//光标悬浮创建对象
        List<WebElement> drag1, drag2;//节目展现图层，drag1左侧节目列表对象；drag2右侧节目列表对象
        String programName;//节目名称对象
        Thread.sleep(500);

        if (li != null) {
            li.click();//激活自动化创建的频道
            Thread.sleep(1000);

            List<WebElement> pros = driver.findElements(By.xpath("//ul[@class='clearfix act-cont']/li"));//获取节目
            if (pros.size() > 0) {
                driver.findElement(By.cssSelector("button.fr.seeAll.ll-btn-item.layui-btn.layui-btn-primary.ll-btn")).click();//点击节目展现
                Thread.sleep(1000);
                if (CommonMethod.isJudgingElement(driver,By.xpath("//ul[@class='ll-items drag1']/li"))) {//判断左侧已上线节目列表是否有数据
                    drag1 = driver.findElements(By.xpath("//ul[@class='ll-items drag1']/li"));
                    for (int i = 1; i < drag1.size() + 1; i++) {
                        programName = driver.findElement(By.xpath("//ul[@class='ll-items drag1']/li[1]/p")).getText();//被操作的节目名称
                        if (i == 1)
                            actions.moveToElement(drag1.get(0)).perform();//光标悬浮该节目
                        Thread.sleep(500);
                        driver.findElement(By.xpath("//ul[@class='ll-items drag1']/li[1]/div/p[@class='offline3']")).click();//点击下线
                        System.out.println(programName + "下线");
                        Thread.sleep(1000);
                    }
                } else System.out.println("没有已上线的节目");
                if (CommonMethod.isJudgingElement(driver,By.xpath("//ul[@class='ll-items drag2']/li"))) {//判断右侧已下线节目列表是否有数据
                    drag2 = driver.findElements(By.xpath("//ul[@class='ll-items drag2']/li"));
                    for (int i = 1; i < drag2.size() + 1; i++) {
                        programName = driver.findElement(By.xpath("//ul[@class='ll-items drag2']/li[1]/p")).getText();//被操作的节目名称
                        if (i == 1)
                            actions.moveToElement(drag2.get(0)).perform();//光标悬浮该节目
                        Thread.sleep(500);
                        driver.findElement(By.xpath("//ul[@class='ll-items drag2']/li[1]/div/p[@class='getline3']")).click();//点击上线
                        System.out.println(programName + "上线");
                        Thread.sleep(1000);
                    }
                } else System.out.println("没有已下线的节目");
                driver.findElement(By.className("layui-layer-btn0")).click();//保存
            } else System.out.println("测试频道没有节目");
        } else System.out.println("没有自动化创建的电视频道");
        Thread.sleep(2000);
    }

    //设置精选节目，非精华则设置为精华，已精华则取消精华
    public static void setChoice() throws InterruptedException {
        WebElement li = getAutoData();//获取自动化建的测试频道
        Thread.sleep(500);

        if (li != null) {//判断是否有自动化创建的频道
            li.click();//激活自动化创建的频道
            Thread.sleep(1500);

            List<WebElement> pros = driver.findElements(By.xpath("//ul[@class='clearfix act-cont']/li"));//获取节目列表
            if (pros.size() > 0) {//判断是否有节目
                if (!CommonMethod.isJudgingElement(driver,By.xpath("//ul[@class='clearfix act-cont']/li[1]/div/p/span"))) {//判断是否已被设置为精选，不存在该标签则为非精选
                    driver.findElement(By.cssSelector("button.fr.setchoice.ll-btn-item.layui-btn.layui-btn-primary.ll-btn")).click();//点击设置为精选
                    System.out.println("~~~ setChoice() 节目设置精选，执行成功~~~");
                } else {//存在该标签则已为精选
                    driver.findElement(By.cssSelector("button.fr.setchoiceNo.ll-btn-item.layui-btn.layui-btn-primary.ll-btn")).click();//点击取消精选
                    System.out.println("~~~ setChoice() 节目取消精选，执行成功~~~");
                }
            } else System.out.println("测试频道下没有节目");
        } else System.out.println("没有自动化创建的电视频道");
        Thread.sleep(2000);
    }

    //停播节目，未停播的设置为停播；已停播的设置为恢复
    public static void closeProgram() throws InterruptedException {
        WebElement li = getAutoData();//获取自动化建的测试频道
        Thread.sleep(500);

        if (li != null) {//判断是否有自动化创建的频道
            li.click();//激活自动化创建的频道
            Thread.sleep(1500);

            List<WebElement> pros = driver.findElements(By.xpath("//ul[@class='clearfix act-cont']/li"));//获取节目列表
            if (pros.size() > 0) {//判断是否有节目
                if (!CommonMethod.isJudgingElement(driver,By.xpath("//ul[@class='clearfix act-cont']/li[1]/div/p[2]/span"))) {//判断是否已被停播，不存在该标签则为正常
                    driver.findElement(By.cssSelector("button.fr.closeProgram.ll-btn-item.layui-btn.layui-btn-primary.ll-btn")).click();//点击停播
                    System.out.println("~~~ closeProgram() 节目停播，执行成功~~~");
                } else {//存在该标签则为已停播
                    driver.findElement(By.cssSelector("button.fr.closeProgramNo.ll-btn-item.layui-btn.layui-btn-primary.ll-btn")).click();//点击恢复
                    System.out.println("~~~ closeProgram() 节目恢复播放，执行成功~~~");
                }
            } else System.out.println("测试频道下没有节目");
        } else System.out.println("没有自动化创建的电视频道");
        Thread.sleep(2000);
    }

    //上传视频
    public static void addVideo() throws InterruptedException {
        WebElement li = getAutoData();//获取自动化创建的测试频道
        Thread.sleep(500);
        Boolean canAdd = false;

        if (li != null) {
            li.click();//激活要操作的测试频道
            Thread.sleep(1000);

            List<WebElement> pros = driver.findElements(By.xpath("//ul[@class='clearfix act-cont']/li"));//获取节目列表
            if (pros.size() > 0) {//判断是否有节目
                for (int i = 1; i < pros.size() + 1; i++) {
                    if (!CommonMethod.isJudgingElement(driver,By.xpath("//ul[@class='clearfix act-cont']/li[" + i + "]/div/p[2]/span"))) {//获取没有停播的节目
                        pros.get(i - 1).click();
                        driver.findElement(By.cssSelector("button.fr.multiplexing-btn.ll-btn-item.layui-btn.layui-btn-primary.ll-btn")).click();//点击上传视频
                        Thread.sleep(1000);
                        for (int j = 1; j < 50; j++) {//上传视频iframe图层参数
                            if (CommonMethod.isJudgingElement(driver,By.id("layui-layer-iframe" + j))) {//判断有效的上传视频图层的iframe名称，j是动态的
                                driver.switchTo().frame("layui-layer-iframe" + j);//切换到上传视频图层iframe页面

                                driver.findElement(By.name("title")).sendKeys("autotest视频" + Calendar.getInstance().getTimeInMillis());//录入标题
                                driver.findElement(By.cssSelector("input.layui-btn.base-btn-search.layui-btn-primary.btn_getSource")).click();//点击在线资源库
                                driver.switchTo().defaultContent();//退出当前iframe

                                driver.switchTo().frame("layui-layer-iframe" + (j + 1));//切换到资源库frame进行操作
                                Thread.sleep(1500);

                                if (!CommonMethod.isJudgingElement(driver,By.xpath("//ul[@class='mtl_videoList clearfix']/li")))
                                    Thread.sleep(2000);
                                List<WebElement> videos = driver.findElements(By.xpath("//ul[@class='mtl_videoList clearfix']/li"));//获取素材数据列表

                                if (videos.size() > 0) {
                                    driver.findElement(By.xpath("//ul[@class='mtl_videoList clearfix']/li[1]/div")).click();//选择第一张视频
                                    driver.findElement(By.cssSelector("button.mtl_btn.yes")).click();//融媒页确认添加视频返回
                                    driver.switchTo().parentFrame();//退出当前iframe
                                    Thread.sleep(3000);

                                    driver.switchTo().frame("layui-layer-iframe" + j);//切换到上传视频图层iframe进行操作
                                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");//下拉滚动条到最底部

                                    driver.findElement(By.className("sy-tools_setImg")).click();//设置封面图

                                    driver.switchTo().parentFrame();//退出当前iframe

                                    Thread.sleep(500);

                                    driver.findElement(By.cssSelector("button.cropSet-button.ok.save")).click();//点击保存封面图
                                    Thread.sleep(1000);

                                    if (CommonMethod.isJudgingElement(driver,By.cssSelector("button.cropSet-button.ok.save")))
                                        driver.findElement(By.cssSelector("button.cropSet-button.cancel")).click();
                                    Thread.sleep(500);

                                    driver.switchTo().frame("layui-layer-iframe" + j);//切换到上传视频图层iframe图层
                                    driver.findElement(By.name("summary")).sendKeys("这里是视频简介" + Calendar.getInstance().getTimeInMillis());//录入简介信息
                                    driver.switchTo().parentFrame();//退出当前iframe

                                    driver.findElement(By.className("layui-layer-btn0")).click();//点击保存视频
                                    System.out.println("~~~ multiplexing() 上传视频，执行成功~~~");
                                } else {
                                    System.out.println("没有可用视频素材！");
                                    driver.findElement(By.cssSelector("button.mtl_btn.cancel")).click();//融媒页关闭返回
                                }
                                break;
                            }
                        }
                        canAdd = true;
                        break;
                    }
                }
                if (!canAdd) System.out.println("没有可上传视频的节目，可能节目均被停播");
            } else System.out.println("测试频道下没有节目");
        } else System.out.println("没有自动化创建的电视频道");
        Thread.sleep(2000);
    }

    //审核视频
    public static void push() throws InterruptedException {
        WebElement li = getAutoData();//获取自动化创建的测试频道
        List<WebElement> pros, videos;
        Boolean status = false;
        String proName;

        if (li != null) {
            li.click();//激活要操作的频道
            Thread.sleep(5000);

            pros = driver.findElements(By.xpath("//ul[@class='clearfix act-cont']/li"));//获取节目列表
            if (pros.size() > 0) {//判断是否有节目
                for (int i = 1; i < pros.size() + 1; i++) {
                    if (!status) {
                        pros.get(i - 1).click();//激活要操作的节目
                        proName = driver.findElement(By.xpath("//ul[@class='clearfix act-cont']/li[" + i + "]/div/p[1]/i")).getText();
                        Thread.sleep(5000);

                        videos = driver.findElements(By.xpath("//ul[@class='menu-list clearfix']/li"));//获取素材数据列表
                        if (videos.size() > 0) {
                            for (int j = 1; j < videos.size() + 1; j++) {
                                if (!CommonMethod.isJudgingElement(driver,By.xpath("//ul[@class='menu-list clearfix']/li[" + j + "]/div[@class='ll-menu']/div/div/span[@class='fr ll-select isPublishColor']"))) {
                                    driver.findElement(By.xpath("//ul[@class='menu-list clearfix']/li[" + j + "]/div[@class='ll-menu']/p[@class='ll-check']")).click();
                                    driver.findElement(By.cssSelector("button.fr.ispush.ll-btn-item.layui-btn.layui-btn-primary.ll-btn")).click();
                                    System.out.println("~~~ push() ，节目“" + proName + "”,视频审核，执行成功 ~~~");
                                    status = true;
                                    Thread.sleep(8000);
                                    break;
                                }
                            }
                            if (!status) System.out.println("节目“" + proName + "”，没有可审核的视频");
                        } else System.out.println("节目“" + proName + "”，没有可审核的视频");
                    }
                }
            } else System.out.println("没有节目");
        } else System.out.println("没有测试频道");
        Thread.sleep(2000);
    }

    //下线视频
    public static void offline() throws InterruptedException {
        WebElement li = getAutoData();//获取自动化创建的测试频道
        List<WebElement> pros, videos;
        Boolean status = false;
        String proName;

        if (li != null) {
            li.click();//激活要操作的频道
            Thread.sleep(5000);

            pros = driver.findElements(By.xpath("//ul[@class='clearfix act-cont']/li"));//获取节目列表
            if (pros.size() > 0) {//判断是否有节目
                for (int i = 1; i < pros.size() + 1; i++) {
                    if (!status) {
                        pros.get(i - 1).click();//激活要操作的节目
                        proName = driver.findElement(By.xpath("//ul[@class='clearfix act-cont']/li[" + i + "]/div/p[1]/i")).getText();
                        Thread.sleep(5000);

                        videos = driver.findElements(By.xpath("//ul[@class='menu-list clearfix']/li"));//获取素材数据列表
                        if (videos.size() > 0) {
                            for (int j = 1; j < videos.size() + 1; j++) {
                                if (CommonMethod.isJudgingElement(driver,By.xpath("//ul[@class='menu-list clearfix']/li[" + j + "]/div[@class='ll-menu']/div/div/span[@class='fr ll-select isPublishColor']"))) {
                                    driver.findElement(By.xpath("//ul[@class='menu-list clearfix']/li[" + j + "]/div[@class='ll-menu']/p[@class='ll-check']")).click();
                                    driver.findElement(By.cssSelector("button.fr.isoffline.ll-btn-item.layui-btn.layui-btn-primary.ll-btn")).click();
                                    System.out.println("~~~ offline() ，节目“" + proName + "”,视频下线，执行成功 ~~~");
                                    status = true;
                                    Thread.sleep(8000);
                                    break;
                                }
                            }
                            if (!status) System.out.println("节目“" + proName + "”，没有可下线的视频");
                        } else System.out.println("节目“" + proName + "”，没有可下线的视频");
                    }
                }
            } else System.out.println("没有节目");
        } else System.out.println("没有测试频道");
        Thread.sleep(2000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver,By.tagName("header"))) {
                    driver.get("http://app.test.pdmiryun.com/rft/channel/tvprogram");
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

    //获取自动化创建的数据
    public static WebElement getAutoData() {
        List<WebElement> webs = driver.findElements(By.xpath("//ul[@id='channel-list']/li"));//获取频道列表数据
        WebElement autoData = null;
        if (webs.size() > 0) {//有频道数据
            for (int i = 1; i < webs.size() + 1; i++) {
                if (driver.findElement(By.xpath("//ul[@id='channel-list']/li[" + i + "]/div/p")).getText().contains("auto")) {//判断标题中是否有“auto”关键词
                    autoData = driver.findElement(By.xpath("//ul[@id='channel-list']/li[" + i + "]"));//获取该元素对象
                    return autoData;//返回该元素对象
                }
            }
        }
        return autoData;//没有该元素对象，则返回null
    }

}

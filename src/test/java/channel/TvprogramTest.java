package channel;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @name 电视管理
 * @author wufeng
 * @date 2021/12/15 9:18
 */
public class TvprogramTest {
//    @Test(priority = 1)//创建电视频道，删除有验证码，不能自动化删除，顾不做创建避免产生大量自动化数据
//    public void testCreateTV() throws InterruptedException {
//        TvProgram.createTV();
//    }

    @Test(priority = 2)//编辑电视频道
    public void testEditTV() throws InterruptedException {
        TvProgram.editTV();
    }

    @Test(priority = 3)//上线或下线电视频道
    public void testTurnOnOrOff() throws InterruptedException {
        TvProgram.turnOnOrOffTV();
    }

    @Test(priority = 4)//添加节目
    public void testAddProgram() throws InterruptedException {
        TvProgram.addProgram();
    }

    @Test(priority = 5)//编辑节目
    public void testEditProgram() throws InterruptedException {
        TvProgram.editProgram();
    }

    @Test(priority = 12)//删除节目
    public void testDelProgram() throws InterruptedException {
        TvProgram.delProgram();
    }

    @Test(priority = 6)//节目展示
    public void testShowProgram() throws InterruptedException {
        TvProgram.showProgram();
    }

    @Test(priority = 7)//设置或取消精选
    public void testSetChoice() throws InterruptedException {
        TvProgram.setChoice();
    }

    @Test(priority = 11)//停播或恢复节目
    public void testCloseProgram() throws InterruptedException {
        TvProgram.closeProgram();
    }

    @Test(priority = 8)//节目添加视频
    public void testAddVideo() throws InterruptedException {
        TvProgram.addVideo();
    }

    @Test(priority = 9)//审核视频
    public void testPush() throws InterruptedException {
        TvProgram.push();
    }

    @Test(priority = 10)//下线视频
    public void testOffLine() throws InterruptedException {
        TvProgram.offline();
    }

    @BeforeMethod
    public void testStart(Method method) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + method.getName());
    }

    @AfterMethod
    public void testEnd(Method method){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< Test End!\n");
    }
}
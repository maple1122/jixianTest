package channel;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author wufeng
 * @date 2021/12/17 9:40
 */
public class RadioProgramTest {
    @Test(priority = 1)//创建广播频道
    public void testAddRadio() throws InterruptedException, IOException {
        RadioProgram.createRadio();
    }

    @Test(priority = 2)//编辑广播频道
    public void testEditRadio() throws InterruptedException {
        RadioProgram.editRadio();
    }

    @Test(priority = 3)//上线或下线广播频道
    public void testTurnOnOrOffRadio() throws InterruptedException {
        RadioProgram.turnOnOrOffRadio();
    }

    @Test(priority = 4)//添加节目
    public void testAddProgram() throws InterruptedException {
        RadioProgram.addProgram();
        RadioProgram.addProgram();
    }

    @Test(priority = 5)//编辑节目
    public void testEditProgram() throws InterruptedException {
        RadioProgram.editProgram();
    }

    @Test(priority = 12)//删除节目
    public void testDelProgram() throws InterruptedException {
        RadioProgram.delProgram();
    }

    @Test(priority = 6)//节目展示
    public void testShowProgram() throws InterruptedException {
        RadioProgram.showProgram();
    }

    @Test(priority = 7)//设置或取消精选
    public void testSetChoice() throws InterruptedException {
        RadioProgram.setChoice();
    }

    @Test(priority = 11)//停播或恢复节目
    public void testCloseProgram() throws InterruptedException {
        RadioProgram.closeProgram();
    }

    @Test(priority = 8)//节目添加音频
    public void testAddAudio() throws InterruptedException, IOException {
        RadioProgram.addAudio();
    }

    @Test(priority = 9)//审核视频
    public void testPush() throws InterruptedException, IOException {
        RadioProgram.push();
    }

    @Test(priority = 10)//下线视频
    public void testOffLine() throws InterruptedException, IOException {
        RadioProgram.offline();
    }
}
package mp;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2021/12/30 11:21
 */
public class LiveManageTest {

    @Test(priority = 1)//开启或停用达人直播
    public void testOpen0rClose() throws InterruptedException {
        LiveManage.openOrClose();
    }

    @Test(priority = 4)//下线
    public void testUnpublish() throws InterruptedException {
        LiveManage.unpublish();
    }

    @Test(priority = 2)//发布
    public void testPublish() throws InterruptedException {
        LiveManage.publish();
    }

    @Test(priority = 3)//签发
    public void testPush() throws InterruptedException {
        LiveManage.push();
    }
}
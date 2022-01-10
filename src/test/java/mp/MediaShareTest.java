package mp;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2021/12/29 10:54
 */
public class MediaShareTest {

    @Test(priority = 1)//我要共享-媒体号设置或取消共享
    public void testSetting() throws InterruptedException {
        MediaShare.setting();
    }

    @Test(priority = 2)//共享媒体号-引入共享媒体号
    public void testIntroduce() throws InterruptedException {
        MediaShare.introduce();
    }

    @Test(priority = 3)//引入媒体号-删除引入媒体号
    public void testDelIntroduce() throws InterruptedException {
        MediaShare.delIntroduce();
    }
}
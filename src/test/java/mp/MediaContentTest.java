package mp;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2021/12/29 15:45
 */
public class MediaContentTest {

    @Test(priority = 3)//内容下线
    public void testOffline() throws InterruptedException {
        MediaContent.offline();
    }

    @Test(priority = 1)//内容发布
    public void testPublish() throws InterruptedException {
        MediaContent.publish();
    }

    @Test(priority = 4)//内容删除
    public void testDelete() throws InterruptedException {
        MediaContent.delete();
    }

    @Test(priority = 2)//内容选签
    public void testPush() throws InterruptedException {
        MediaContent.push();
    }
}
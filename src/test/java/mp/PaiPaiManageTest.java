package mp;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static mp.PaiPaiManage.*;

/**
 * @author wufeng
 * @date 2022/1/4 11:05
 */

public class PaiPaiManageTest {

    @Test(priority = 1)//拍拍下线
    public void testUpPublish() throws InterruptedException {
        uppublish();
    }

    @Test(priority = 2)//拍拍发布
    public void testPublish() throws InterruptedException {
        publish();
    }

//    @Test(priority = 3)//拍拍审核，可审核的数据不多，顾不做审核
//    public void testAuditing() throws InterruptedException {
//        auditing();
//    }

    @Test(priority = 4)//拍拍签发
    public void testPush() throws InterruptedException {
        push();
    }

//    @Test(priority = 10)//拍拍删除，可供删除的数据不多，顾不做执行
//    public void testDel() throws InterruptedException {
//        del();
//    }

    @Test(priority = 5)//拍拍解除违规
    public void testRelieve() throws InterruptedException {
        relieve();
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
package channel;

import org.testng.annotations.Test;


/**
 * @name 精选节目管理
 * @author wufeng
 * @date 2021/12/21 9:49
 */
public class ChoiceprogramTest {

    @Test(priority = 1)//添加分类
    public void testAddClass() throws InterruptedException {
        ChoiceProgram.addClass();
    }

    @Test(priority = 4)//删除分类
    public void testDelClass() throws InterruptedException {
        ChoiceProgram.delClass();
    }

    @Test(priority = 2)//设置分类
    public void testSetClass() throws InterruptedException {
        ChoiceProgram.setClass();
    }

    @Test(priority = 3)//取消精选
    public void testCancelChoice() throws InterruptedException {
        ChoiceProgram.cancelChoice();
    }
}
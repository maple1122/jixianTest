package channel;

import org.testng.annotations.Test;


/**
 * @author wufeng
 * @date 2021/12/21 9:49
 */
public class ChoiceprogramTest {

    @Test(priority = 1)
    public void testAddClass() throws InterruptedException {
        ChoiceProgram.addClass();
    }

    @Test(priority = 4)
    public void testDelClass() throws InterruptedException {
        ChoiceProgram.delClass();
    }

    @Test(priority = 2)
    public void testSetClass() throws InterruptedException {
        ChoiceProgram.setClass();
    }

    @Test(priority = 3)
    public void testCancelChoice() throws InterruptedException {
        ChoiceProgram.cancelChoice();
    }
}
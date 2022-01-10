package channel;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author wufeng
 * @date 2021/12/22 13:59
 */
public class ProguidTest {

    @Test
    public void testAddProgram() throws InterruptedException {
        Proguid.addProgram();
        Proguid.addProgram();
    }

    @Test
    public void testDelProgram() throws InterruptedException {
        Proguid.delProgram();
    }

    @Test
    public void testMultiplexing() throws InterruptedException {
        Proguid.multiplexing();
    }

    @Test
    public void testUpload() throws InterruptedException, IOException {
        Proguid.upload();
    }
}
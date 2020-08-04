package cpm.ren.rpc.server;

/**
 * @program: http-rpc
 * @Date: 2020/7/27 11:02
 * @Author: Mrs.Ren
 * @Description:
 */
public class TestServiceImpl implements ITestService {
    @Override
    public void hello() {
        System.out.println("hello");
    }
}

package cpm.ren.rpc.server;

import com.ren.rpc.protocol.Request;
import com.ren.rpc.protocol.ServerDescriptor;
import com.ren.rpc.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;


public class ServerManagerTest {
    ServerManager sm;


    @Before
    public void init() {
        sm = new ServerManager();
        ITestService testServiceBean = new TestServiceImpl();
        sm.register(ITestService.class, testServiceBean);
    }

   /* @Test
    public void register() {
        ITestService testServiceBean = new TestServiceImpl();
        sm.register(ITestService.class, testServiceBean); //cpm.ren.rpc.server.ServerManager - register service hello{} cpm.ren.rpc.server.ITestService
    }*/

    @Test
    public void lookUp() {
        Request request = new Request();
        Method publicMethod = ReflectionUtils.getPublicMethods(ITestService.class)[0];
        ServerDescriptor descriptor = ServerDescriptor.from(ITestService.class, publicMethod);
        request.setServerDescriptor(descriptor);
        ServerInstance serverInstance = sm.lookUp(request);
        System.out.println(serverInstance);//ServerInstance(targect=cpm.ren.rpc.server.TestServiceImpl@42f30e0a, method=public abstract void cpm.ren.rpc.server.ITestService.hello())
    }
}
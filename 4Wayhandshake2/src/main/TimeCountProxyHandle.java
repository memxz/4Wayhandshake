package main;  
  
  
import java.lang.reflect.InvocationHandler;  
import java.lang.reflect.Method;  
  
/** 
 * 
 * @author 
 * 
 */  
public class TimeCountProxyHandle implements InvocationHandler {  
  
    private Object proxied;  
  
    public TimeCountProxyHandle(Object obj) {  
        proxied = obj;  
    }  
  
    @Override  
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {  
        long begin = System.currentTimeMillis();  
        Object result = method.invoke(proxied, args);  
        long end = System.currentTimeMillis();  
        System.out.println("Time Usage For " + method.getName() + " is :" + (end - begin) + "ms");  
        return result;  
    }  
}  

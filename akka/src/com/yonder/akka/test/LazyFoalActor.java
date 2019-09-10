package com.yonder.akka.test;  
  
import akka.actor.UntypedActor;  
  
/** 
 * @author cyd 
 * @date 2015-3-24 
 */  
  
public class LazyFoalActor extends UntypedActor {  
  
    @Override  
    public void onReceive(Object message) throws Exception {  
        System.out.println("LazyFoalActor receive message : " + message);  
    }  
}  
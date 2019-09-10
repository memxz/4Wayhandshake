package com.yonder.akka.test;  
  
import akka.actor.UntypedActor;  
  
/** 
 * @author cyd 
 * @date 2015-3-24 
 */  
public class AngryFoalActor extends UntypedActor {  
  
    public void onReceive(Object message) throws Exception {  
        System.out.println("AngryFoalActor receive message : " + message);  
        getSender().tell("hello! I am  AngryFoalActor!", getSelf());  
    }  
}  
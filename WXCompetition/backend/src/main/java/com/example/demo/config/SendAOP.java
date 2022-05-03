package com.example.demo.config;

import com.example.demo.until.TimerUntil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
//
//@Component
//@Aspect
public class SendAOP {
    //动态代理方式实现
//    private WantSendService wantSendService;
//    public SendAOP(WantSendService wantSendService){
//        this.wantSendService = wantSendService;
//    }
//    //AOP创建倒计时
//    //JDK动态代理
//    public WantSendService sendaop(){
//        WantSendService proxy = null;
//        //获取对应的类加载器
//        ClassLoader classLoader = wantSendService.getClass().getClassLoader();
//        //代理对象的类型，以及其中有哪些方法
//        Class [] insetfaces = new Class[]{WantSendController.class};
//        //执行代理对象类中方法时，会执行的代码块
//        InvocationHandler h = new InvocationHandler() {
//            @Override
//            /*
//            proxy:正在返回的调用对象，一般情况中，invoke方法中不怎么会用该方法
//            method:正在被调用的方法
//            args:调用方法时，传入的参数
//             */
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                method.invoke(wantSendService,args);
//                TimerUntil timerUntil = new TimerUntil((String) args[0]);
//                timerUntil.TimerStart();
//                return null;
//            }
//        };
//        proxy = (WantSendService)Proxy.newProxyInstance(classLoader,insetfaces,h);
//        return proxy;
//    }
//    @Pointcut("within(com.example.demo.Service.WantSendService)")
//    public void timerStart(){}
//    @After("timerStart()")
//    public void TimeStart(JoinPoint joinPoint){
//        System.out.println("切面开始运行");
//        List<Object> args = Arrays.asList(joinPoint.getArgs());
//        TimerUntil timerUntil = new TimerUntil((String) args.get(0));
//        timerUntil.TimerStart();
//    }

}

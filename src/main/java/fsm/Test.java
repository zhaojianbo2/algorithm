package fsm;

import java.util.Timer;

import com.google.common.eventbus.EventBus;

import fsm.aidata.AiData;
import fsm.aidata.MonsterAiData;
import fsm.bean.Monster;
import fsm.bean.Person;
import fsm.controller.MonsterBehaviorController;
import fsm.event.PersonBeenKilledEvent;
import fsm.machine.PersonMachine;
import fsm.stateType.MonsterAIStateType;
import fsm.timer.FsmAITimer;

public class Test {

    public static void main(String[] args) throws InterruptedException {
	Person monster = new Monster();
	//状态实例监听
	EventBus eventBus = new EventBus();
	//控制器需要的数据
	AiData aiData = new MonsterAiData(monster);
	//有限状态机
	PersonMachine personMachine = new PersonMachine(MonsterAIStateType.IDLE);
	//状态机控制器
	MonsterBehaviorController ctl = new MonsterBehaviorController(aiData,personMachine);
	// 自定义注册
	ctl.registerState(eventBus);
	// 运行
	Timer t = new Timer();
	t.schedule(new FsmAITimer(ctl), 0, 1000);
	
	//测试
	Thread.sleep(1000l);
	PersonBeenKilledEvent killEvent = new PersonBeenKilledEvent();
	eventBus.post(killEvent);
	//强制转换
	ctl.turnToType(MonsterAIStateType.ATTACK);
	
    }
}

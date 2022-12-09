package fsm.controller;

import com.google.common.eventbus.EventBus;

import fsm.aidata.AiData;
import fsm.behavior.AttackBehavior;
import fsm.behavior.IdleBehavior;
import fsm.behavior.ReturnBehavior;
import fsm.machine.BaseMachine;
import fsm.stateType.MonsterAIStateType;

/**
 * 怪物行为AI控制器,封装状态机,状态机只能内部运行
 * 
 * @author WinkeyZhao
 * @note
 *
 */
public class MonsterBehaviorController extends PersonAiController {

    public MonsterBehaviorController(AiData aiData, BaseMachine fsmMachine) {
	super(aiData, fsmMachine);
    }

    public void defaultRegit() {
	// 默认注册全部
	registerState(new AttackBehavior());
	registerState(new IdleBehavior());
	registerState(new ReturnBehavior());
    }

    /**
     * 自定义注册
     */
    @Override
    public void registerState(EventBus eventBus) {

	if (eventBus != null) {
	    //空闲监听被攻击事件
	    registerState(new IdleBehavior(eventBus));
	}
	registerState(new AttackBehavior());
	registerState(new ReturnBehavior());
    }
    
    /**
     * 强制转换状态
     * @param monsterAIStateType
     */
    public void turnToType(MonsterAIStateType monsterAIStateType) {
	fsmMachine.turnToType(monsterAIStateType);
    }
}

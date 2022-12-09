package fsm.behavior;

import com.google.common.eventbus.EventBus;

import fsm.stateType.FSMType;
import fsm.stateType.MonsterAIStateType;

public class AttackBehavior extends StateCommon {

    public AttackBehavior() {
    }
    
    public AttackBehavior(EventBus eventBus) {
	super(eventBus);
    }

    @Override
    public FSMType getType() {
	return MonsterAIStateType.ATTACK;
    }

    @Override
    public void enter() {
    }

    @Override
    public void exit() {

    }

    @Override
    public void update() {
	System.out.println("AttackBehavior");
    }

    @Override
    public FSMType checkTransation() {
	return MonsterAIStateType.ATTACK;
    }

}

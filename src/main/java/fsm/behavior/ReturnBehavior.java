package fsm.behavior;

import com.google.common.eventbus.EventBus;

import fsm.stateType.FSMType;
import fsm.stateType.MonsterAIStateType;

public class ReturnBehavior extends StateCommon {

    public ReturnBehavior() {
    }

    public ReturnBehavior(EventBus eventBus) {
	super(eventBus);
    }

    @Override
    public FSMType getType() {
	return MonsterAIStateType.RETURN;
    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void update() {

	System.out.println("ReturnBehavior");
    }

    @Override
    public FSMType checkTransation() {
	return MonsterAIStateType.RETURN;
    }

}

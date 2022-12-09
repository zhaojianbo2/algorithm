package fsm.behavior;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import fsm.event.PersonBeenKilledEvent;
import fsm.stateType.FSMType;
import fsm.stateType.MonsterAIStateType;

/**
 * 
 * @author WinkeyZhao
 * @note
 *
 */
public class IdleBehavior extends StateCommon {

    public IdleBehavior() {
    }

    public IdleBehavior(EventBus eventBus) {
	super(eventBus);
    }

    @Override
    public FSMType getType() {
	return MonsterAIStateType.IDLE;
    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void update() {
	System.out.println("IdleBehavior");
    }

    @Override
    public FSMType checkTransation() {
	return MonsterAIStateType.IDLE;
    }

    @Subscribe
    public void onPersonbeenAttack(PersonBeenKilledEvent e) {
	System.out.println("onPersonbeenAttack");
    }

}

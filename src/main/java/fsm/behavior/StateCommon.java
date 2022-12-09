package fsm.behavior;

import com.google.common.eventbus.EventBus;

import fsm.stateType.FSMType;

/**
 * 状态逻辑实现接口
 * 
 * @author WinkeyZhao
 * @note
 *
 */
public abstract class StateCommon {

    public abstract void enter();

    public abstract void exit();

    public abstract void update();

    public abstract FSMType checkTransation();

    public abstract FSMType getType();

    public EventBus eventBus;

    public StateCommon() {
    }
    
    public StateCommon(EventBus eventBus) {
	this.eventBus = eventBus;
    }

}

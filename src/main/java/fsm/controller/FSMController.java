package fsm.controller;


import com.google.common.eventbus.EventBus;

import fsm.behavior.StateCommon;
import fsm.stateType.FSMType;

/**
 * 
 * @author WinkeyZhao
 * @note
 *
 */
public interface FSMController {

    public void registerState(EventBus eventBus);
    public void update();
    public StateCommon getStateByType(FSMType fsmType);
    
}


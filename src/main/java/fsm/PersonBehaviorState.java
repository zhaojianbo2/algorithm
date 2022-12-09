package fsm;

import fsm.controller.FSMController;
import fsm.stateType.FSMType;

/**
 * 状态包装 通过传递的FSMType确定具体实例
 * 
 * @author WinkeyZhao
 * @note
 *
 */
public class PersonBehaviorState extends FSMState {

    public PersonBehaviorState(FSMType aiState, FSMController controller) {
	super(aiState, controller);
    }

    @Override
    public void onFSMCtlRemove() {

    }

}

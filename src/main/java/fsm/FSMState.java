package fsm;

import fsm.controller.FSMController;
import fsm.stateType.FSMType;

/**
 * 状态包装类
 * @author WinkeyZhao
 * @note
 *
 */
public abstract class FSMState {
    private FSMController controller;
    protected FSMType stateType;
    // 改变状态类型，优先检查该类型，如果和默认类型不一致，则切换到新状态
    private FSMType changeType;

    public FSMState(FSMType stateType, FSMController controller) {
	this.stateType = stateType;
	this.controller = controller;
	setChangeType(stateType);
    }

    public FSMType getChangeType() {
	return changeType;
    }

    public void setChangeType(FSMType changeType) {
	this.changeType = changeType;
    }

    public FSMController getController() {
	return controller;
    }

    public FSMType getType() {
	return stateType;
    }

    public abstract void onFSMCtlRemove();
}

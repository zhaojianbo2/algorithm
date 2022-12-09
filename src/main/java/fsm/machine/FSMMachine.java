package fsm.machine;

import fsm.behavior.StateCommon;
import fsm.stateType.FSMType;

/**
 * 优先状态机
 * @author WinkeyZhao
 * @note
 *
 */
public abstract class FSMMachine extends BaseMachine {
    protected StateCommon currentState;
    // 外部强制状态改变标识
    protected FSMType changeType;

    /**
     * 单状态机update
     */
    @Override
    public void update() {

	if (currentState == null) {
	    stateChange(changeType);
	    return;
	}
	// 强制改变不checkTransation
	if (changeType != currentState.getType()) {
	    stateChange(changeType);
	    changeType = currentState.getType();
	    return;
	}

	// 状态检查
	FSMType resultType = currentState.checkTransation();
	if (resultType == null) {
	    resultType = changeType;
	}
	if (resultType != currentState.getType()) {
	    stateChange(resultType);
	}
	currentState.update();
    }

    @Override
    public void turnToType(FSMType fsmType) {
	this.changeType = fsmType;
    }

    private void stateChange(FSMType resultType) {
	// 老状态退出
	if (currentState != null) {
	    changeType = resultType;
	    // 卸载监听
	    if (currentState.eventBus != null) {
		currentState.eventBus.unregister(currentState);
	    }
	    currentState.exit();
	}
	// 新状态进入
	currentState = stateMap.get(resultType);
	if (currentState != null) {
	    // 注册监听
	    if (currentState.eventBus != null) {
		currentState.eventBus.register(currentState);
	    }
	    currentState.enter();
	}
    }
}

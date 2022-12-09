package fsm.controller;


import fsm.aidata.AiData;
import fsm.behavior.StateCommon;
import fsm.machine.BaseMachine;
import fsm.stateType.FSMType;

/**
 *  AI控制器
 * @author WinkeyZhao
 * @note
 *
 */
public abstract class PersonAiController implements FSMController{
    protected final AiData aiData;//
    protected final BaseMachine fsmMachine;//状态机实例
    
    public PersonAiController(AiData aiData,BaseMachine fsmMachine) {
	this.aiData = aiData;
	this.fsmMachine = fsmMachine;
    }
    protected void registerState(StateCommon state) {
	fsmMachine.stateMap.put(state.getType(), state);
    }

    @Override
    public StateCommon getStateByType(FSMType fsmType) {
	return fsmMachine.stateMap.get(fsmType);
    }
    @Override
    public void update() {
	fsmMachine.update();
    }
}

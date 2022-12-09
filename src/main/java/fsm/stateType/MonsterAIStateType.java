package fsm.stateType;

import fsm.behavior.AttackBehavior;
import fsm.behavior.IdleBehavior;
import fsm.behavior.ReturnBehavior;
import fsm.behavior.StateCommon;

public enum MonsterAIStateType implements FSMType{
	NULL(null),
	
	IDLE(new IdleBehavior()),
	
	ATTACK(new AttackBehavior()),
	
	RETURN(new ReturnBehavior()),
	
	;
	private StateCommon behaviorScript;
	
	MonsterAIStateType(StateCommon behaviorScript){
	    this.behaviorScript = behaviorScript;
	}
	
	public StateCommon getBehaviorStateScript() {
	    return behaviorScript;
	}
}

package fsm.machine;

import fsm.stateType.FSMType;

public class PersonMachine extends FSMMachine{
    
    public PersonMachine(FSMType defaultType) {
	changeType = defaultType;
    }

}

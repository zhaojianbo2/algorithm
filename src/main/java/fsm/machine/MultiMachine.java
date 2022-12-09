package fsm.machine;

import java.util.List;


/**
 * 
 * @author WinkeyZhao
 * @note
 *
 */
public abstract class MultiMachine extends BaseMachine {

    protected List<FSMMachine> fsmList;

    /**
     * 多层状态机 update
     */
    @Override
    public void update() {
	for (FSMMachine fsm : fsmList) {
	    fsm.update();
	}
    }
}

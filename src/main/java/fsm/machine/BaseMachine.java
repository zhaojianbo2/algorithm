package fsm.machine;

import java.util.HashMap;
import java.util.Map;

import fsm.behavior.StateCommon;
import fsm.stateType.FSMType;

/**
 * 
 * @author WinkeyZhao
 * @note
 *
 */
public abstract class BaseMachine {

    public Map<FSMType, StateCommon> stateMap = new HashMap<>();

    public abstract void update();
    
    /**
     * 受外部影响 强制转换到指定状态
     * @param fsmType
     */
    public abstract void turnToType(FSMType fsmType);
}

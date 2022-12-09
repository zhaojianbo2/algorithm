package fsm.timer;

import java.util.TimerTask;

import fsm.controller.FSMController;

/**
 * 状态机运行timer
 * @author WinkeyZhao
 * @note
 *
 */
public class FsmAITimer extends TimerTask {

    private final FSMController ctl;

    public FsmAITimer(FSMController ctl) {
	this.ctl = ctl;
    }

    @Override
    public void run() {
	//运行状态机
	ctl.update();
    }

}

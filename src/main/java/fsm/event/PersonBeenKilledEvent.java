package fsm.event;

import fsm.bean.Person;

public class PersonBeenKilledEvent extends AiListenEvent{

    public Person target;
}

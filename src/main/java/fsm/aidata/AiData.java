package fsm.aidata;


import fsm.bean.Person;

public abstract class AiData {
    protected final Person person;//person对象
    public AiData(Person person) {
	this.person = person;
    }
}

package bubble.test.ex18;

public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	//default: 인터페이스도 몸체가 있는 메서드를  만들 수 있다. (다중 상속이 안되는 것이 많기에..)
	//그래서 adapter 패턴보다는 default를 사용하는 것이 좋음.
	default public void down() {};
	default public void attack() {};
}

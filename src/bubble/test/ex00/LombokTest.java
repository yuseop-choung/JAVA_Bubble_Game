package bubble.test.ex00;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
class Dog{
	private String name;
}

public class LombokTest {

	public static void main(String[] args) {
		Dog d = new Dog();
		d.setName("George");
		System.out.println("Dog's name: " + d.getName());

	}

}

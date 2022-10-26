package bubble.test.ex18;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

//메인스레드 바븜 - 키보드 이벤트를 처리하기 바쁨
//백그라운드에서 계속 관찰
public class BackgroundEnemyService implements Runnable {
	
	private BufferedImage image;
	private Enemy enemy;
	
	//player, bubble
	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(enemy.getState() == 0) {
			//색상 확인
			//PlayerL의 경우: (x - 5, y + 25) 를 체크
			//PlayerR의 경우: (x + 65, y + 25) 를 체크
			Color leftColor = new Color(image.getRGB(enemy.getX() - 7, enemy.getY() + 25));
			Color rightColor = new Color(image.getRGB(enemy.getX() + 65, enemy.getY() + 25));
			//왼쪽 하단, 오른쪽 하단 모두 흰색(-1)이 아니면 착지하도록. 즉 bottomColor != -2
			int bottomColor = image.getRGB(enemy.getX() + 10, enemy.getY() + 55)
					+ image.getRGB(enemy.getX() + 40, enemy.getY() + 55);
			//System.out.println("하단 색상: " + bottomColor);

			//바닥 충돌 확인
			if(bottomColor != -2) {
				//System.out.println("바닥에 충돌");
				enemy.setDown(false);
			} else {
				if(!enemy.isUp() && !enemy.isDown()) {
					enemy.down();
				}
			}
			
			//외벽 충돌 확인
			if(leftColor.getRed() == 255 && leftColor.getGreen()==0 && leftColor.getBlue() == 0) {
				//System.out.println("왼쪽 벽에 충돌");
				enemy.setLeft(false);	//충돌했기에 더이상 움직이지않도록
				if(!enemy.isRight())
					enemy.right();
			} else if(rightColor.getRed() == 255 && rightColor.getGreen()==0 && rightColor.getBlue() == 0) {
				//System.out.println("오른쪽 벽에 충돌");
				enemy.setRight(false);	//충돌했기에 더이상 움직이지 않도록
				if(!enemy.isLeft())
					enemy.left();
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

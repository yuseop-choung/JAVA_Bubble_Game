package bubble.test.ex17;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

//메인스레드 바븜 - 키보드 이벤트를 처리하기 바쁨
//백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable {
	
	private BufferedImage image;
	private Player player;
	private List<Bubble> bubbleList;
	
	//player, bubble
	public BackgroundPlayerService(Player player) {
		this.player = player;
		this.bubbleList = player.getBubbleList();
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(true) {
			//1. 버블 충돌 체크
			for(int i = 0; i < bubbleList.size(); i++) {
				Bubble bubble  = bubbleList.get(i);
				if(bubble.getState() == 1) {
					if((Math.abs(player.getX() - bubble.getX()) < 10) &&
							(Math.abs(player.getY() - bubble.getY()) > 0 &&
									Math.abs(player.getY() - bubble.getY()) < 50)) {
						System.out.println("적군 사살 완료");
						bubble.clearBubbled();
						break;
					}
				}
			}
			
			
			//2. 벽 충돌 체크
			//색상 확인
			//PlayerL의 경우: (x - 5, y + 25) 를 체크
			//PlayerR의 경우: (x + 65, y + 25) 를 체크
			Color leftColor = new Color(image.getRGB(player.getX() - 7, player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 65, player.getY() + 25));
			//왼쪽 하단, 오른쪽 하단 모두 흰색(-1)이 아니면 착지하도록. 즉 bottomColor != -2
			int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 55)
					+ image.getRGB(player.getX() + 40, player.getY() + 55);
			//System.out.println("하단 색상: " + bottomColor);

			//바닥 충돌 확인
			if(bottomColor != -2) {
				//System.out.println("바닥에 충돌");
				player.setDown(false);
			} else {
				if(!player.isUp() && !player.isDown()) {
					player.down();
				}
			}
			
			//외벽 충돌 확인
			if(leftColor.getRed() == 255 && leftColor.getGreen()==0 && leftColor.getBlue() == 0) {
				//System.out.println("왼쪽 벽에 충돌");
				player.setLeftWallCrash(true);
				player.setLeft(false);	//충돌했기에 더이상 움직이지않도록
			} else if(rightColor.getRed() == 255 && rightColor.getGreen()==0 && rightColor.getBlue() == 0) {
				//System.out.println("오른쪽 벽에 충돌");
				player.setRightWallCrash(true);
				player.setRight(false);	//충돌했기에 더이상 움직이지 않도록
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

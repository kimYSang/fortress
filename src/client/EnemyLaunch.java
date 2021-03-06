package client;

public class EnemyLaunch extends Thread{
	GameView view;
	UserInfo user,myUser;
	Status status;
	double r;

	EnemyLaunch(GameView view,UserInfo user,Status status,UserInfo myUser){
		this.view = view;
		this.user = user;
		this.status = status;
		this.myUser = myUser;
	}

	public void run(){
		System.out.println("enemyRun");
		user.power *= 100;
		user.angle *= 10;

		r = user.angle * Math.PI / 180;
		int t = 0;
		int x,y =0;
		while(y>=0){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			y = (int)(user.power * Math.sin(r) * t - 4.9 * t * t);
			x = (int)(user.power * Math.cos(r) * t);
			view.y = 350 -  y/10;
			view.x = 765  - x/10;
			System.out.println("x = " + view.x + "y = " + view.y);
			view.repaint();
			t++;	

			if(view.x >= 120 && view.x < 170 && view.y <= 400 && view.y>=350){			
				status.MinusMyHP();
				myUser.MinusHP();
				if(myUser.getHP() <= 0 ){
					Finish finish = new Finish(myUser);
				}

				view.hit = true;
				view.repaint();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.hit = false;
				view.repaint();

				status.repaint();
				break;
			}			
		}
		view.x = 0;
		view.y = 0;
		user.setAngle(0);
		user.setPower(0);
		view.isLaunch = false;
		view.repaint();
	}
}

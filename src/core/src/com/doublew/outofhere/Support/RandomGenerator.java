package com.doublew.outofhere.Support;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {
	private final int amount;
	private Random r;
	private ArrayList<Integer> stack1;
	private ArrayList<Integer> stack2;
	private ArrayList<Boolean> walls;
	
	public RandomGenerator(int amount) {
		this.amount = amount;
		r = new Random();
		stack1 = new ArrayList<Integer>();
		stack2 = new ArrayList<Integer>();
		walls = new ArrayList<Boolean>();
	}
	
	public void generateWays() {
		stack1.clear();
		stack2.clear();
		
		for(int i = 0; i < amount; ++i){
			stack1.add(new Integer(i));
		}
		
		for(int i = 0; i < amount; ++i){
			int getter = r.nextInt(stack1.size());
			stack2.add(new Integer(stack1.get(getter)));
			stack1.remove(getter);
		}
	}
	
	public int getWay(int number){
		return stack2.get(number);
	}
	
	public void generateWalls(int closed) {
		walls.clear();
		//true - closed
		int howManyClosed = r.nextInt(3);
		for(int i = 0; i < amount; ++i) {
			walls.add(false);
		}
		switch (closed) {
			case 0:
				walls.set(2, true);
				break;
			case 1:
				walls.set(1, true);
				break;
			case 2:
				walls.set(0, true);
				break;
			case 3:
				walls.set(3, true);
				break;		
		}
		
		int closedWay1 = 0;
		int closedWay2 = 0;
		switch(howManyClosed) {
			case 0: 
				break;
			case 1:
				closedWay1 = r.nextInt(3);
				if(closedWay1 == closed){
					if(r.nextInt(2) == 0){
						if(closedWay1 == 0) closedWay1 = 3;
						else closedWay1--;
					}else{
						if(closedWay1 == 3) closedWay1 = 0;
						else closedWay1++;
					}
				}
				walls.set(closedWay1, true);
				break;
			case 2:
				closedWay1 = r.nextInt(3);
				if(closedWay1 == closed){
					if(r.nextInt(2) == 0){
						if(closedWay1 == 0) closedWay1 = 3;
						else closedWay1--;
					}else{
						if(closedWay1 == 3) closedWay1 = 0;
						else closedWay1++;
					}
				}
				walls.set(closedWay1, true);
				
				int last1 = 10;
				int last2 = 10;
				for(int i = 0; i < amount; ++i) {
					if(!walls.get(i)) {
						if(last1 == 10){
							last1 = i;
						}else {
							last2 = i;
						}
					}
				}
				if(r.nextInt(2) == 0){
					closedWay2 = last1;
				}else {
					closedWay2 = last2;
				}
				walls.set(closedWay2, true);
				break;
		}
	}
	
	public boolean getWall(int number) {
		return walls.get(number);
	}
}

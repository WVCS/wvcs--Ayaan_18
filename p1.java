import java.awt.print.Printable;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class p1 {

	int rows, cols, rooms;
	char[][] coordMap = new char[rows * rooms * cols][3];

	// ArrayDeque enlo;

	static Queue<Integer> enLo = new LinkedList<>();
	static ArrayList<Integer> deLo = new ArrayList<Integer>();
	static Stack<Integer> enStack = new Stack<Integer>();  
	static Stack<Integer> deStack = new Stack<Integer>(); 
	static int kX, kY;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double run = System.currentTimeMillis();
		Scanner scanner;
		Scanner scanner2;
		Scanner scanner3;
		Scanner scanner4;
		Scanner scanner5;
		Scanner scanner6;
		File f = new File("aliMap1.txt");
		File f2 = new File("aliCoordinateMap1.txt");

		try {
			// code that might throw a special error
			scanner = new Scanner(f);
			scanner2 = new Scanner(f);
			scanner3 = new Scanner(f);
			scanner4 = new Scanner(f2);
			scanner5 = new Scanner(f);
			scanner6 = new Scanner(f);
			
			boolean cakeChecker = false; 

			char[][] tMap = templateBased(scanner3);
			
			for (int i = 0; i < tMap.length; i++) {
				for (int j = 0; j < tMap[0].length; j++) {
					if((tMap[i][j] + "").equals("C")) {
						cakeChecker = true; 
					}
				}
			}
			
			if(cakeChecker == false) {
				System.out.println("The Cake is a Lie."); 
				return;
			}
			

			for (int i = 0; i < tMap.length; i++) {
				for (int j = 0; j < tMap[0].length; j++) {
					if ((tMap[i][j] + "").equals("K")) {
						kX = i;
						kY = j;
						System.out.println(" " + kX + " " + kY);

					}
				}

			}
			// use next methods to grab first 3 numbers form the file
			// with your map info
			int rows = scanner.nextInt();
			System.out.println(rows);
			int cols = scanner.nextInt();
			System.out.println(cols);
			int rooms = scanner.nextInt();
			System.out.println(rooms);
			char[][] map = new char[rows * rooms][cols];
			boolean[][] booleanMap = new boolean[rows * rooms][cols];
			int cX = 0;
			int cR = 0;

			for (int i = 0; i < booleanMap.length; i++) {
				for (int j = 0; j < booleanMap[0].length; j++) {
					booleanMap[i][j] = false;
				}
			}

			scanner.nextLine();

			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();

				// use charAt to grab the elements of the map for a given row

				// System.out.println(line);

				for (int j = 0; j < line.length(); j++) {

					map[cX][j] = line.charAt(j);

				}

				cX++;
			}

			for (int i = 0; i < rows * rooms; i++) {
				for (int j = 0; j < cols; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}

			coordinateBased(scanner2);

			// coordinateBased2(scanner3);

			//char[][] cMap = coordinateBased2(scanner3);

			//System.out.print(cMap);
			

			

			// ENQUEING KIRBY POSITION
			//enLo.add(temp);
			enLo.add(kX);
			enLo.add(kY); 
			// System.out.println(enLo.remove());
			deLo.add(enLo.remove());
			deLo.add(enLo.remove());
			
			// ENSTACKING KIRBY POSITION
			enStack.push(kX);
			deStack.push(enStack.pop()); 
			enStack.push(kY); 
			deStack.push(enStack.pop()); 

			templateBased(scanner6);
			
			// COMMAND LINE ARGUMENT CODE
			for (String s : args) {		
				boolean help = false;
				if(s.equals("--Help")) {
					help = true;
					int h = 1;
					switch(h) {
						case 1: if(help == true) {
							System.out.println("This program is designed to take in Kirby Maze maps."); 
							System.out.println("The program will find optimal paths for Kirby towards the target (a Cake).");
							System.out.println("Switches will be used to indicate the specific approach to solve the problem.");
							System.out.println("They will also indicate what output will be produced."); 
	
									
							break;
						}
						
						case 2: System.exit(0);
					}
							
				}
				
				// CHECKING TO SEE IF PATH IS WANTED
				if (s.equals("--Stack") || s.equals("--Queue") || s.equals("--Opt")) {
					// DECLARING VARS FOR SWITCH TO DECIDE WHICH PATH ALGORITHM TO USE
					
					
					int genAlg = 1;
					boolean stack = false;
					boolean queue = false;
					boolean opt = false;
					boolean time = false; 
					boolean coord = false;
					boolean template = false;
					 
					
					//CHECKING TO SEE WHICH OUTPUT IS NEEDED
					for(String z : args) {
						if(z.equals("--Incoordinate")) {
							coord = true;
						}
						if(z.equals("--Outcoordinate")) {
							template = true;
						}
					}

					for (String p : args) {
						if(p.equals("--Time")) {
							time = true;
						}
						if (p.equals("--Stack")) {
							stack = true;
						} else if (p.equals("--Queue")) {
							queue = true;

						} else if (p.equals("--Opt")) {
							opt = true;
						}
					}

					// SWITCH TO DECIDE WHICH PATH FINDER TO USE
					switch (genAlg) {

					case 1:
						if (stack == true && time == true) {
							stackCake(scanner4, scanner5, booleanMap, kX, kY, coord, template);
							System.out.println("Total Runtime: " + ((System.currentTimeMillis() - run)) + " milliseconds");
							break;
						}
					case 2:
						if (stack == true && time == false) {
							stackCake(scanner4, scanner5, booleanMap, kX, kY, coord, template);
							break;
						}
					case 3:
						if (queue == true && time == true) {
							queueCake(scanner4, scanner5, booleanMap, kX, kY, coord, template);
							System.out.println("Total Runtime: " + ((System.currentTimeMillis() - run)) + " milliseconds");
							break;
						}
					case 4:
						if (queue == true && time == false) {
							queueCake(scanner4, scanner5, booleanMap, kX, kY, coord, template);
							break;
						}

					case 5:
						if (opt == true && time == true) {
							optimalCake(scanner4, scanner5, booleanMap, kX, kY, coord, template);
							System.out.println("Total Runtime: " + ((System.currentTimeMillis() - run)) + " milliseconds");
							break;
						}
					case 6:
						if (opt == true && time == false) {
							optimalCake(scanner4, scanner5, booleanMap, kX, kY, coord, template);
							break;
						}

					}
				}
				
				System.out.println(kX + " " + kY);
			}
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static char[][] templateBased(Scanner scan) {
		int rows = scan.nextInt();
		System.out.println(rows);
		int cols = scan.nextInt();
		System.out.println(cols);
		int rooms = scan.nextInt();
		System.out.println(rooms);
		char[][] map = new char[rows * rooms][cols];
		int cX = 0;
		int cR = 0;

		scan.nextLine();

		while (scan.hasNextLine()) {

			String line = scan.nextLine();

			// use charAt to grab the elements of the map for a given row

			// System.out.println(line);

			for (int j = 0; j < line.length(); j++) {

				map[cX][j] = line.charAt(j);

			}

			cX++;
		}

		for (int i = 0; i < rows * rooms; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

		return map;

	}

	public static char[][] coordinateBased(Scanner scan) {
		int rows = scan.nextInt();
		int cols = scan.nextInt();
		int rooms = scan.nextInt();
		char[][] normMap = new char[rows * rooms][cols];
		char[][] coordMap = new char[rows * rooms * cols][3];
		int count = 0;
		int c = 0;

		scan.nextLine();
		while (scan.hasNextLine()) {

			String line = scan.nextLine();

			// use charAt to grab the elements of the map for a given row
			for (int i = 0; i < line.length(); i++) {
				coordMap[c][0] = line.charAt(i);
				coordMap[c][1] = ("" + count).charAt(0);
				coordMap[c][2] = ("" + i).charAt(0);

				c++;
			}
			count++;
			if (count > rows - 1) {
				count = 0;
			}
		}

		for (int i = 0; i < coordMap.length; i++) {
			for (int j = 0; j < coordMap[0].length; j++) {
				System.out.print(coordMap[i][j]);
			}
			System.out.println();
		}

		return coordMap;

	}
	
	public static char[][] coordinateBased2(Scanner scan) {
		int rows = scan.nextInt();
		System.out.println(rows);
		int cols = scan.nextInt();
		System.out.println(cols);
		int rooms = scan.nextInt();
		System.out.println(rooms);
		char[][] map = new char[rows * rooms * cols][3];
		int cX = 0;

		scan.nextLine();

		while (scan.hasNextLine()) {
			String line = scan.nextLine(); 

			// use charAt to grab the elements of the map for a given row
			for (int j = 0; j < line.length()-1; j++) {
				map[cX][j] = ("" + line).charAt(j);
			}
			cX++;
		}
		
		return map;
 
	}
	
	public static String[][] coordinateBasedConversion(String[][] map) {
		int rows = map.length * map[0].length; 
		int cols = 3; 
		String[][] cMapConversion = new String[rows][cols];
		int c = 0;
		
		while(c < rows) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					cMapConversion[c][0] = map[i][j];
					cMapConversion[c][1] = i + "";
					cMapConversion[c][2] = j + "";
					
					c++; 
				}
			}	
		}
		

		return cMapConversion; 
	}

	public static void queueCake(Scanner scan, Scanner scan2, boolean[][] map, int x, int y, boolean coord, boolean template) {

		char[][] cMap = coordinateBased2(scan);
		char[][] tMap = templateBased(scan2); // TEMPLATE MAP
		int rows = tMap.length;
		int cols = tMap[0].length;
		boolean[][] tMapB = map; // BOOLEAN MAP TO CHECK IF TILES ARE ALREADY CHECKED
		int xPos = x; // OFFICIAL X POSITION VAR
		int yPos = y; // OFFICIAL Y POSITION VAR
		int tX = x; // HOLDING TEMPORARY X POS ON BOARD
		int tY = y; // HOLDING TEMPORARY Y POS ON BOARD
		int cX = -1; // STORING X CAKE POSITION
		int cY = -1; // STORING Y CAKE POSITION

		// testing vals for enqueing method

		System.out.println(xPos);
		System.out.println(yPos);
		System.out.println(enLo);
		System.out.println(deLo);

		// SINCE KIRBY ALREADY DEQUEUED DECLARE SIZE VAR AT 1

		int size = 1;
		

		// ENQUEING ALL WALKABLE TILES
		while (size >= 0) {

			// GET RID OF INITIAL SIZE THAT'S PLACEHOLDER FOR KIRBY DEQUEUE
			if (size == 1 && enLo.size() == 0) {
				size-=2;
			}

			// NORTH
			if (xPos > 0 && tMapB[xPos - 1][yPos] == false) {
				if ((tMap[xPos - 1][yPos] + "").equals("C")) {
					cX = xPos - 1;
					cY = yPos;
					enLo.add(cX);
					enLo.add(cY);
					break;
				} else if ((tMap[xPos - 1][yPos] + "").equals(".")) {
					tX = xPos - 1;
					tY = yPos;
					tMapB[tX][tY] = true;

					enLo.add(tX);
					enLo.add(tY);
					//System.out.println(enLo);
				}
			}

			// SOUTH

			if (xPos < rows - 1 && tMapB[xPos + 1][yPos] == false) {
				if ((tMap[xPos + 1][yPos] + "").equals("C")) {
					cX = xPos + 1;
					cY = yPos;
					enLo.add(cX);
					enLo.add(cY);
					break;
				} else if ((tMap[xPos + 1][yPos] + "").equals(".")) {

					tX = xPos + 1;
					tY = yPos;
					tMapB[tX][tY] = true;

					enLo.add(tX);
					enLo.add(tY);
					//System.out.println(enLo);
				}
			}

			// EAST
			if (yPos < cols - 1 && tMapB[xPos][yPos + 1] == false) {
				if ((tMap[xPos][yPos + 1] + "").equals("C")) {
					cX = xPos;
					cY = yPos + 1;
					enLo.add(cX);
					enLo.add(cY);
					break;
				} else if ((tMap[xPos][yPos + 1] + "").equals(".")) {
					tX = xPos;
					tY = yPos + 1;
					tMapB[tX][tY] = true;

					enLo.add(tX);
					enLo.add(tY);
					//System.out.println(enLo);
				}
			}

			// WEST
			if (yPos > 0 && tMapB[xPos][yPos - 1] == false) {
				if ((tMap[xPos][yPos - 1] + "").equals("C")) {
					cX = xPos;
					cY = yPos - 1;
					enLo.add(cX);
					enLo.add(cY);
					break;
				} else if ((tMap[xPos][yPos - 1] + "").equals(".")) {
					tX = xPos;
					tY = yPos - 1;
					tMapB[tX][tY] = true;

					enLo.add(tX);
					enLo.add(tY);
					//System.out.println(enLo);
				}
				
			}
			System.out.println("enLo: " + enLo); 
			// DEQUEING TO NEXT LOCATION
			xPos = enLo.peek();
			//System.out.println(xPos);
			deLo.add(enLo.remove());
			//System.out.println(deLo);
			yPos = enLo.peek();
			//System.out.println(yPos);
			deLo.add(enLo.remove());
			System.out.println("deLo: " + deLo);
			System.out.println("enLo: " + enLo); 
			// UPDATING SIZE
			size = enLo.size();
			
		
		}

		// PATH FINDING

		
		// CONFIRMING CAKE LOCATION
		// System.out.println(cX);
		// System.out.println(cY);

		// FINDING IDEAL PATH WITH DEQUEUE

		int currX, currY;
		int prevX, prevY;

		int pos = deLo.size();
		System.out.println(pos);
		
		//CONVERTING TEMPLATE MAP AND COORDINATE MAP TO STRING FOR EASY MANIPULATION
				String[][] tMapConvert = new String[tMap.length][tMap[0].length];

				for (int i = 0; i < tMapConvert.length; i++) {
					for (int j = 0; j < tMapConvert[0].length; j++) {
						if((tMap[i][j]+"").equals("C")) {
							cX = i;
							cY = j; 
						}
						tMapConvert[i][j] = String.valueOf(tMap[i][j]);
					}

				}

		currX = cX;
		currY = cY;

		System.out.println(cX + " " + cY);
		
		
		

		while (pos >= 2) {

			prevX = deLo.get(pos - 2);
			prevY = deLo.get(pos - 1);
			//System.out.println(prevX);
			//System.out.println(prevY);
			
			//CHECKING IF DELO ELEMENT WITHIN 1 OF PREVIOUS LOCATION
			if ((currX == prevX && currY == prevY - 1) || (currX == prevX && currY == prevY + 1)
					|| (currX == prevX + 1 && currY == prevY) || (currX == prevX - 1 && currY == prevY)) {

				currX = prevX;
				currY = prevY;

				tMapConvert[prevX][prevY] = "+";
				tMapConvert[kX][kY] = "K";

			} else {
				//MOVING ON TO NEXT STOP FOR ITERATION
				deLo.remove(pos - 1);
				deLo.remove(pos - 2);
			}
			
			pos -= 2;

		}
		
		
		//PRINTING OUT DELO ONLY CONTAINING MOVES
		System.out.println(deLo);
		
		//CREATING NEW COORDINATE MAP
		
		String[][] cMapConvert = coordinateBasedConversion(tMapConvert); 
		
		//PRINTING COMPLETED TEMPLATE QUEUE PATH
		/*for (int i = 0; i < tMapConvert.length; i++) {
			for (int j = 0; j < tMapConvert[0].length; j++) {
				System.out.print(tMapConvert[i][j]);
			}
			System.out.println();
		}
		*/
		
		int printMap = 1; 

		
		switch (printMap) {
		
		case 1: if(coord == true && template == true) {
			for (int i = 0; i < cMapConvert.length; i++) {
				for (int j = 0; j < cMapConvert[0].length; j++) {
					System.out.print(cMapConvert[i][j]);
				}
				System.out.println();
			}
			for (int i = 0; i < tMapConvert.length; i++) {
				for (int j = 0; j < tMapConvert[0].length; j++) {
					System.out.print(tMapConvert[i][j]);
				}
				System.out.println();
			}
			break;
		}
		
		case 2: 
			if (coord == true) {
				for (int i = 0; i < cMapConvert.length; i++) {
					for (int j = 0; j < cMapConvert[0].length; j++) {
						System.out.print(cMapConvert[i][j]);
					}
					System.out.println();
				}
				break;
			}
			

		case 3:
			if (template == true) {
				for (int i = 0; i < tMapConvert.length; i++) {
					for (int j = 0; j < tMapConvert[0].length; j++) {
						System.out.print(tMapConvert[i][j]);
					}
					System.out.println();
				}
				break;
			}
			
		case 4: 
				for (int i = 0; i < tMapConvert.length; i++) {
					for (int j = 0; j < tMapConvert[0].length; j++) {
						System.out.print(tMapConvert[i][j]);
					}
					System.out.println();
				}
				break;
		}
		
		
		//PRINTING COMPLETED COORDINATE QUEUE PATH
		/*for (int i = 0; i < cMapConvert.length; i++) {
			for (int j = 0; j < cMapConvert[0].length; j++) {
				System.out.print(cMapConvert[i][j]);
			}
			System.out.println();
		}
		*/

	}
	
	public static void stackCake(Scanner scan, Scanner scan2, boolean[][] map, int x, int y, boolean coord, boolean template) {
		char[][] cMap = coordinateBased2(scan);
		char[][] tMap = templateBased(scan2); // TEMPLATE MAP
		int rows = tMap.length;
		int cols = tMap[0].length;
		boolean[][] tMapB = map; // BOOLEAN MAP TO CHECK IF TILES ARE ALREADY CHECKED
		int xPos = x; // OFFICIAL X POSITION VAR
		int yPos = y; // OFFICIAL Y POSITION VAR
		int tX = x; // HOLDING TEMPORARY X POS ON BOARD
		int tY = y; // HOLDING TEMPORARY Y POS ON BOARD
		int cX = -1; // STORING X CAKE POSITION
		int cY = -1; // STORING Y CAKE POSITION

		// testing vals for PUSHING method

		System.out.println(xPos);
		System.out.println(yPos);
		System.out.println(enStack);
		System.out.println(deStack);

		// SINCE KIRBY ALREADY IN POP STACK DECLARE SIZE VAR AT 1

		int size = 1;

		// ENQUEING ALL WALKABLE TILES
		while (size >= 0) {

			// GET RID OF INITIAL SIZE THAT'S PLACEHOLDER FOR KIRBY POP STACK
			if (size == 1 && enStack.size() == 0) {
				size-=2;
			}

			// NORTH
			if (xPos > 0 && tMapB[xPos - 1][yPos] == false) {
				if ((tMap[xPos - 1][yPos] + "").equals("C")) {
					cX = xPos - 1;
					cY = yPos;
					enStack.push(cX);
					enStack.push(cY);
					break;
				} else if ((tMap[xPos - 1][yPos] + "").equals(".")) {
					tX = xPos - 1;
					tY = yPos;
					tMapB[tX][tY] = true;

					enStack.push(tX);
					enStack.push(tY);
					System.out.println(enStack);
				}
			}

			// SOUTH

			if (xPos < rows - 1 && tMapB[xPos + 1][yPos] == false) {
				if ((tMap[xPos + 1][yPos] + "").equals("C")) {
					cX = xPos + 1;
					cY = yPos;
					enStack.push(cX);
					enStack.push(cY);
					break;
				} else if ((tMap[xPos + 1][yPos] + "").equals(".")) {

					tX = xPos + 1;
					tY = yPos;
					tMapB[tX][tY] = true;

					enStack.push(tX);
					enStack.push(tY);
					System.out.println(enStack);
				}
			}

			// EAST
			if (yPos < cols - 1 && tMapB[xPos][yPos + 1] == false) {
				if ((tMap[xPos][yPos + 1] + "").equals("C")) {
					cX = xPos;
					cY = yPos + 1;
					enStack.push(cX);
					enStack.push(cY);
					break;
				} else if ((tMap[xPos][yPos + 1] + "").equals(".")) {
					tX = xPos;
					tY = yPos + 1;
					tMapB[tX][tY] = true;

					enStack.push(tX);
					enStack.push(tY);
					System.out.println(enStack);
				}
			}

			// WEST
			if (yPos > 0 && tMapB[xPos][yPos - 1] == false) {
				if ((tMap[xPos][yPos - 1] + "").equals("C")) {
					cX = xPos;
					cY = yPos - 1;
					enStack.push(cX);
					enStack.push(cY);
					break;
				} else if ((tMap[xPos][yPos - 1] + "").equals(".")) {
					tX = xPos;
					tY = yPos - 1;
					tMapB[tX][tY] = true;

					enStack.push(tX);
					enStack.push(tY);
					System.out.println(enStack);
				}
			}

			// POPPING TO NEXT LOCATION
			yPos = enStack.peek();
			System.out.println(yPos);
			enStack.pop();
			xPos = enStack.peek();
			System.out.println(xPos);
			enStack.pop();
			
			deStack.push(xPos);
			System.out.println(deStack);
			deStack.push(yPos);
			System.out.println(deStack);
			

			// UPDATING SIZE
			size = enStack.size();
			

		}
		System.out.println(enStack); 
		System.out.println(deStack); 


		
		
		// PATH FINDING

		
		// CONFIRMING CAKE LOCATION
		// System.out.println(cX);
		// System.out.println(cY);

		// FINDING IDEAL PATH WITH DESTACK

		int currX, currY;
		int prevX, prevY;

		int pos = deStack.size();
		System.out.println(pos);

		//CONVERTING TEMPLATE MAP AND COORDINATE MAP TO STRING FOR EASY MANIPULATION
		String[][] tMapConvert = new String[tMap.length][tMap[0].length];

		for (int i = 0; i < tMapConvert.length; i++) {
			for (int j = 0; j < tMapConvert[0].length; j++) {
				if((tMap[i][j]+"").equals("C")) {
					cX = i;
					cY = j; 
				}
				tMapConvert[i][j] = String.valueOf(tMap[i][j]);
			}

		}

		currX = cX;
		currY = cY;
		

		
		while (pos >= 2) {

			prevX = deStack.get(pos - 2);
			prevY = deStack.get(pos - 1);
			System.out.println(prevX);
			System.out.println(prevY);
			
			//CHECKING IF POP STACK ELEMENT WITHIN 1 OF PREVIOUS LOCATION
			if ((currX == prevX && currY == prevY - 1) || (currX == prevX && currY == prevY + 1)
					|| (currX == prevX + 1 && currY == prevY) || (currX == prevX - 1 && currY == prevY)) {

				currX = prevX;
				currY = prevY;

				tMapConvert[prevX][prevY] = "+";
				tMapConvert[kX][kY] = "K";

			} else {
				//MOVING ON TO NEXT STOP FOR ITERATION
				deStack.remove(pos - 1);
				deStack.remove(pos - 2);
			}
			
			pos -= 2;

		}
		
		//PRINTING OUT POP STACK ONLY CONTAINING MOVES
		System.out.println(deLo);
		
		String[][] cMapConvert = coordinateBasedConversion(tMapConvert); 
		
		int printMap = 1; 

		
		switch (printMap) {
		
		case 1: if(coord == true && template == true) {
			for (int i = 0; i < cMapConvert.length; i++) {
				for (int j = 0; j < cMapConvert[0].length; j++) {
					System.out.print(cMapConvert[i][j]);
				}
				System.out.println();
			}
			for (int i = 0; i < tMapConvert.length; i++) {
				for (int j = 0; j < tMapConvert[0].length; j++) {
					System.out.print(tMapConvert[i][j]);
				}
				System.out.println();
			}
			break;
		}
		
		case 2: 
			if (coord == true) {
				for (int i = 0; i < cMapConvert.length; i++) {
					for (int j = 0; j < cMapConvert[0].length; j++) {
						System.out.print(cMapConvert[i][j]);
					}
					System.out.println();
				}
				break;
			}
			

		case 3:
			if (template == true) {
				for (int i = 0; i < tMapConvert.length; i++) {
					for (int j = 0; j < tMapConvert[0].length; j++) {
						System.out.print(tMapConvert[i][j]);
					}
					System.out.println();
				}
				break;
			}
			
		case 4: 
				for (int i = 0; i < tMapConvert.length; i++) {
					for (int j = 0; j < tMapConvert[0].length; j++) {
						System.out.print(tMapConvert[i][j]);
					}
					System.out.println();
				}
				break;
		}

	}
	
	 public static void optimalCake(Scanner scan, Scanner scan2, boolean[][] map, int x, int y, boolean coordinate, boolean template) {
		
		 
		 /*BECAUSE KIRBY MAPS ARE CHARACTERIZED BY SHORT AND ROUGH TURNS
		  * AND BORDERS, A QUEUE BASED APPROACH WOULD WORK BEST
		  * SINCE QUEUE ALGORITHM ALREADY PROVIDES OPTIMAL PATH 
		  * CAN REUSE QUEUE
		  */
		 
		 queueCake(scan, scan2, map, x, y, coordinate, template); 
		 
		 /*Scanner scanCoordinate = scan.reset();
		Scanner scanTemplate = scan2.reset(); 
		char[][] coordMap = coordinateBased2(scan);
		char[][] tempMap = templateBased(scan2); // TEMPLATE MAP
		int cX = -1; // STORING X CAKE POSITION
		int cY = -1; // STORING Y CAKE POSITION
		
		for(int i = 0; i < tempMap.length; i++) {
			for(int j = 0; j < tempMap[0].length; j++) {
				if((tempMap[i][j] + "").equals("C")) {
					cX = i;
					cY = j; 
				}
			}
			
			System.out.println(cX + " " + cY);
			
		}
		
		if(cX == 0 || cX == tempMap.length-1 || cY == 0 || cY == tempMap[0].length-1) {
			System.out.println("Hi"); 
			stackCake(scanCoordinate, scanTemplate, map, kX, kY, coordinate, template); 
			System.out.println("Hello");
		}
		else {
			queueCake(scanCoordinate, scanTemplate, map, kX, kY, coordinate, template); 
		}*/
		
		
	}
	
	
	
}
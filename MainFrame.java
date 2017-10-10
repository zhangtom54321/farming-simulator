/***
 * Tom Zhang
 * MainFrame.java
 * StartPanel.java
 * InstructionPanel.java
 * GamePanel.java
 * 
*/

// import statements
import java.awt.event.ActionListener;	
import java.awt.event.ActionEvent;
import javax.swing.JFrame;	
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Font;
import java.awt.Color;		
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class MainFrame extends JFrame // class header
{ // main frame that contains all panels and uses CardLayout to display panels 
	// Field Variables
	StartPanel sp;
	InstructionPanel ip;
	GamePanel gp;
	private CardLayout cards;
	private Image logo, background;
	JPanel mainPanel;
	
	public int highScore;
	
	public static void main(String[] args) // main method
	{
		new MainFrame(); // create instance of MainFrame
	}
	
	public MainFrame()
	{
		super("Farming Simulator"); // set title of frame
		
		// set size, close operator(EXIT_ON_CLOSE), location, resizable (false), visibility
		setLocation(50, 25);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1800, 1000);
		
		// create MainPanel
		mainPanel = new JPanel();
		
		// set layout to CardLayout
		cards = new CardLayout();
		mainPanel.setLayout(cards);
		
		// create instances of the three content panels (StartPanel, InstructionPanel, GamePanel)
		sp = new StartPanel(mainPanel);
		ip = new InstructionPanel(mainPanel);
		gp = new GamePanel(0);
		
		// add instances to frame with CardLayout
		mainPanel.add(sp, "start");
		mainPanel.add(ip, "instructions");
		mainPanel.add(gp, "game");
		
		add(mainPanel);
		
		setVisible(true);
	}
	
	class StartPanel extends JPanel implements ActionListener// class header
	{ // frame that the user sees when the game first loads and contains buttons for navigating to the game / instructions
		// declare field variables
		private JButton playButton, instructionButton;
		
		JPanel tempMF;
		
		public StartPanel(JPanel mf)
		{
			
			tempMF = mf;
			// initialize buttons
			playButton =  new JButton("Play");
			playButton.setFont(new Font("Serif", Font.BOLD, 90));
			
			instructionButton = new JButton("Instructions");
			instructionButton.setFont(new Font("Serif", Font.BOLD, 90));
			
			// set up image
			try
			{
				logo = ImageIO.read(new File("gamelogo.png"));
				background = ImageIO.read(new File("countryside.jpg"));
			}
			catch (IOException e)
			{
				System.err.println("\n\ngamelogo or countryside can't be found \n\n");
				e.printStackTrace();
			}
			
			setLayout(new GridLayout(3, 1, 100, 100)); // set layout as GridLayout
			
			// add actionLIsteners
			playButton.addActionListener(this);
			instructionButton.addActionListener(this);
			
			// add JLabels and JButtons to Panel
			add(new JLabel(""));
			add(playButton);
			add(instructionButton);
		}
		public void paintComponent(Graphics g) // for the sole purpose of painting images
		{
			super.paintComponent(g);
			g.drawImage(background, 0, 0, 1800, 1200, this); // background countryside picture
			g.drawImage(logo, 0, 0, 1800, 400, this); // logo
		}
		public void actionPerformed(ActionEvent e)
		{
			// manipulates cards when a button is pressed
			if (e.getSource() == playButton){
				cards.show(tempMF, "game");
			}
			if (e.getSource() == instructionButton){
				cards.show(tempMF, "instructions");
			}
		}
	}
	
	class InstructionPanel extends JPanel implements ActionListener // class header
	{ // panel that contains instructions for how to play the game
		// declare field variables
		private JButton backButton;
		JPanel tempMF;
		
		public InstructionPanel(JPanel mf) // constructor
		{
			tempMF = mf; // for manipulating cards in CardLayout
			
			// instructions for the game
			// manually setting the font of each label and adding it to the panel
			JLabel j1 = new JLabel("<html><br>Welcome to Farming Simulator. Balance factors and earn money to keep your farm in business for as long as possible.</html>");
			j1.setFont(new Font("Serif", Font.PLAIN, 30));
			add(j1);
			
			JLabel j2 = new JLabel("<html><br>Factors such as water, sunlight, pesticides, etc. all cost money. Planting more trees also cost more money, but will generate<br>more revenue as well.</html>");
			j2.setFont(new Font("Serif", Font.PLAIN, 30));
			add(j2);
			
			JLabel j3 = new JLabel("<html><br>Balance water, sunlight, CO2, and pesticides based on the number of trees. Don't spend enough, and you will generate less money<br> from your trees. Spend too much, and you are wasting money because the trees only need a set amount of these factors to produce<br>the optimal amount of fruit. The plant's optimal amount of each factor is randomly generated each game from 1-10.<br>Learn each year to try to guess the correct amount. </html>");
			j3.setFont(new Font("Serif", Font.PLAIN, 30));
			add(j3);
			
			JLabel j4 = new JLabel("<html><br>Each tree's starting price is 25 dollars, and eight units of each factor starts off at one dollar. As the game progresses,<br>the prices will increase. Every ten years is a new level. Higher levels have higher costs<br>and a greater likelyhood of a natural disaster. <br><br>Natural Disasters occur occasionally and destroy all of your crops. The more trees you plant, the greater your losses<br>will be.</html>");
			j4.setFont(new Font("Serif", Font.BOLD, 30));
			j4.setForeground(Color.RED);
			add(j4);
			
			JLabel j5 = new JLabel("<html><br>Over time, if you don't spent enough on pesticides to kill all the pests, they will develop pesticide resistance. <br>You will have to spend more money to keep the pests under control, or they will destroy all your trees!</html>");
			j5.setFont(new Font("Serif", Font.BOLD, 30));
			j5.setForeground(Color.RED);
			add(j5);
			
			JLabel j6 = new JLabel("<html><br>Good luck farming!<br>__________________________________________________________________</html>");
			j6.setFont(new Font("Serif", Font.BOLD, 30));
			j6.setForeground(Color.RED);
			add(j6);
			
			
			backButton = new JButton("Back");
			backButton.addActionListener(this);
			add(backButton);
		}
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == backButton)
			{
				cards.show(tempMF, "start");
			}
		}
		public void paintComponent(Graphics g) // for the sole purpose of painting images
		{
			super.paintComponent(g);
			g.drawImage(background, 0, 0, 1800, 1200, this); // background countryside picture
		}
	}
	
	class GamePanel extends JPanel
	{ // panel that contains actual game components
		
		// declare field variables
		private Image tree, dirtBackground, skyBackground;
		public int numTrees, amountWater, amountSunlight, amountCO2, amountPesticide;
		ImagePanel imageP;
		
		
		
		public GamePanel(int HighScore) // constructor
		{
			highScore = HighScore;
			setLayout(new BorderLayout()); // set layout to BorderLayout
			
			numTrees = 1;
			
			imageP = new ImagePanel();
			ControlPanel controlP = new ControlPanel();
			ManagePanel manageP = new ManagePanel(numTrees);
			
			add(imageP, BorderLayout.CENTER);
			add(controlP, BorderLayout.NORTH);
			add(manageP, BorderLayout.WEST);
			
			
		}
		
		// panels in the GamePanel
		
		class ImagePanel extends JPanel
		{ // draws trees
			public ImagePanel()
			{
				setPreferredSize(new Dimension(900, 500)); // setting size
				
				// getting image for tree
				try
				{
					tree = ImageIO.read(new File("tree.png"));
					dirtBackground = ImageIO.read(new File("dirtbackground.jpg"));
				}
				catch (IOException e)
				{
					System.err.println("\n\nCan't be found \n\n");
					e.printStackTrace();
				}
			}
			public void paintComponent(Graphics g) // paints the trees
			{
				super.paintComponent(g);
				
				// drawing the dirt
				g.drawImage(dirtBackground, 0, 0, getWidth(), getHeight(), this);
				
				// drawing trees based on the number of total trees
				// not exact number; shows the largest square number that is less than the # of trees
				int numRowsColumns = (int)Math.sqrt(numTrees);
				for (int w = 0; w < numRowsColumns; w++)
				{
					for (int h = 0; h < numRowsColumns; h++)
					{
						g.drawImage(tree,  w*getWidth()/numRowsColumns, h*getHeight()/numRowsColumns, getWidth()/numRowsColumns, getHeight()/numRowsColumns, this);
					}
				}
			}
		}
		class ControlPanel extends JPanel
		{ // contains sliders for the user to toggle factor levels
			public ControlPanel()
			{
				setLayout(new GridLayout(5, 1));
				setPreferredSize(new Dimension(1800, 500));
				
				// water slider
				WaterSliderPanel wsp = new WaterSliderPanel();
				add(wsp);
				
				// sunlight slider
				SunlightSliderPanel ssp = new SunlightSliderPanel();
				add(ssp);
				
				// CO2 slider
				CO2SliderPanel csp = new CO2SliderPanel();
				add(csp);
				
				// pesticide slider
				PesticideSliderPanel psp = new PesticideSliderPanel();
				add(psp);
				
				// tree slider
				TreeSliderPanel tsp = new TreeSliderPanel();
				add(tsp);
			}
			
			class WaterSliderPanel extends JPanel implements ChangeListener
			{ // slider that controls the amount of water
				JSlider waterSlider;
				JLabel numWaterText;
				
				public WaterSliderPanel()
				{
					waterSlider = new JSlider(JSlider.HORIZONTAL, 0, 4000, 1);
					waterSlider.setPreferredSize(new Dimension(1400, 100));
					waterSlider.setMajorTickSpacing(500);
					waterSlider.setMinorTickSpacing(100);
					waterSlider.setPaintTicks(true);
					waterSlider.setPaintLabels(true);
					waterSlider.addChangeListener(this);
					add(waterSlider);
					
					numWaterText = new JLabel("\t\tAmount of Water: 0");
					add(numWaterText);
				}
				public void stateChanged(ChangeEvent e)
				{
					amountWater = waterSlider.getValue();
					numWaterText.setText("\t\tAmount of Water: " + amountWater);
				}
			}
			
			class SunlightSliderPanel extends JPanel implements ChangeListener
			{ // slider that controls the amount of sunlight
				JSlider sunlightSlider;
				JLabel numSunlightText;
				
				public SunlightSliderPanel()
				{
					sunlightSlider = new JSlider(JSlider.HORIZONTAL, 0, 4000, 1);
					sunlightSlider.setPreferredSize(new Dimension(1400, 100));
					sunlightSlider.setMajorTickSpacing(500);
					sunlightSlider.setMinorTickSpacing(100);
					sunlightSlider.setPaintTicks(true);
					sunlightSlider.setPaintLabels(true);
					sunlightSlider.addChangeListener(this);
					add(sunlightSlider);
					
					numSunlightText = new JLabel("\t\tAmount of Sunlight: 0");
					add(numSunlightText);
				}
				public void stateChanged(ChangeEvent e)
				{
					amountSunlight = sunlightSlider.getValue();
					numSunlightText.setText("\t\tAmount of Sunlight: " + amountSunlight);
				}
			}
			
			class CO2SliderPanel extends JPanel implements ChangeListener
			{ // slider that controls the amount of CO2
				JSlider co2Slider;
				JLabel numCO2Text;
				
				public CO2SliderPanel()
				{
					co2Slider = new JSlider(JSlider.HORIZONTAL, 0, 4000, 1);
					co2Slider.setPreferredSize(new Dimension(1400, 100));
					co2Slider.setMajorTickSpacing(500);
					co2Slider.setMinorTickSpacing(100);
					co2Slider.setPaintTicks(true);
					co2Slider.setPaintLabels(true);
					co2Slider.addChangeListener(this);
					add(co2Slider);
					
					numCO2Text = new JLabel("\t\tAmount of CO2: 0");
					add(numCO2Text);
				}
				public void stateChanged(ChangeEvent e)
				{
					amountCO2 = co2Slider.getValue();
					numCO2Text.setText("\t\tAmount of CO2: " + amountCO2);
				}
			}
			
			class PesticideSliderPanel extends JPanel implements ChangeListener
			{ // slider that controls the amount of pesticides
				JSlider pesticideSlider;
				JLabel numPesticideText;
				
				public PesticideSliderPanel()
				{
					pesticideSlider = new JSlider(JSlider.HORIZONTAL, 0, 4000, 1);
					pesticideSlider.setPreferredSize(new Dimension(1400, 100));
					pesticideSlider.setMajorTickSpacing(500);
					pesticideSlider.setMinorTickSpacing(100);
					pesticideSlider.setPaintTicks(true);
					pesticideSlider.setPaintLabels(true);
					pesticideSlider.addChangeListener(this);
					add(pesticideSlider);
					
					numPesticideText = new JLabel("\t\tAmount of Pesticide: 0");
					add(numPesticideText);
				}
				public void stateChanged(ChangeEvent e)
				{
					amountPesticide = pesticideSlider.getValue();
					numPesticideText.setText("\t\tAmount of Pesticide: " + amountPesticide);
				}
			}
			
			class TreeSliderPanel extends JPanel implements ChangeListener
			{ // slider that controls the number of trees
				JSlider treeSlider;
				JLabel numTreesText;
				
				public TreeSliderPanel()
				{
					treeSlider = new JSlider(JSlider.HORIZONTAL, 0, 400, 1);
					treeSlider.setPreferredSize(new Dimension(1400, 100));
					treeSlider.setMajorTickSpacing(50);
					treeSlider.setMinorTickSpacing(10);
					treeSlider.setPaintTicks(true);
				    treeSlider.setPaintLabels(true);
				    treeSlider.addChangeListener(this);
					add(treeSlider);
					
					numTreesText = new JLabel("\t\tNumber of Trees: 1");
					add(numTreesText);
				}
				public void stateChanged(ChangeEvent e)
				{
					numTrees = treeSlider.getValue();
					numTreesText.setText("\t\tNumber of Trees: " + numTrees);
					imageP.repaint();
				}
			}
		}
		class ManagePanel extends JPanel implements ActionListener
		{ // panel that shows the user info about their current game as well as buttons
			
			int cost, profit, revenue, bankAccount, score;
			JButton nextButton;
			boolean statsHidden;
			
			JButton quitButton;
			
			JLabel costLabel, profitLabel, revenueLabel, bankAccountLabel, scoreLabel, highScoreLabel;
			
			int bestWater, bestSunlight, bestCO2, bestPesticides;
			
			boolean gameOver = false;
			
			public ManagePanel(int NumTrees)
			{
				numTrees = NumTrees;
				
				setBackground(Color.GREEN);
				setPreferredSize(new Dimension(900, 500)); // setting size
				setLayout(new GridLayout(8,1, 20, 20));
				
				// calculating bests
				Random rand = new Random();
				
				bestWater = rand.nextInt((10 - 1) + 1) + 1;
				bestSunlight = rand.nextInt((10 - 1) + 1) + 1;
				bestCO2 = rand.nextInt((10 - 1) + 1) + 1;
				bestPesticides = rand.nextInt((10 - 1) + 1) + 1;
				
				// total amount of best water
				
				
				System.out.println(bestWater + " " + bestSunlight + " " + bestCO2 + " " + bestPesticides);
				
				
				// cost label
				cost = 25;
				costLabel = new JLabel("Cost: ?");
				costLabel.setHorizontalAlignment(SwingConstants.CENTER);
				costLabel.setFont(new Font("Serif", Font.PLAIN, 40));
				add(costLabel);
				
				// profit label
				profitLabel = new JLabel("Money from Selling Apples: ?");
				profitLabel.setHorizontalAlignment(SwingConstants.CENTER);
				profitLabel.setFont(new Font("Serif", Font.PLAIN, 40));
				add(profitLabel);
				
				// revenue label
				revenueLabel = new JLabel("Money Gained: ?");
				revenueLabel.setHorizontalAlignment(SwingConstants.CENTER);
				revenueLabel.setFont(new Font("Serif", Font.PLAIN, 40));
				add(revenueLabel);
				
				
				
				// bank account label
				bankAccount = 100;
				bankAccountLabel = new JLabel("Bank Account: $" + bankAccount);
				bankAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
				bankAccountLabel.setFont(new Font("Serif", Font.PLAIN, 40));
				add(bankAccountLabel);
				
				// score label
				score = 0;
				scoreLabel = new JLabel("Score: " + score);
				scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
				scoreLabel.setFont(new Font("Serif", Font.PLAIN, 40));
				add(scoreLabel);
				
				// high score label
				highScoreLabel = new JLabel("High Score: " + highScore);
				highScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
				highScoreLabel.setFont(new Font("Serif", Font.PLAIN, 40));
				add(highScoreLabel);
				
				// game proceed button
				nextButton = new JButton("Click to Find Profit and Revenue");
				add(nextButton);
				nextButton.addActionListener(this);
				
				// quit button
				quitButton = new JButton("Quit");
				quitButton.addActionListener(this);
				add(quitButton);
				
				statsHidden = true;
			}
			public void actionPerformed(ActionEvent e) // if the nextButton or quitButton are pressed
			{
				//System.out.println(statsHidden);
				if (e.getSource() == nextButton && statsHidden == true)
				{
					//System.out.println("triggered");
					
					statsHidden = false;
					score++;
					nextButton.setText("Year " + (score+1));
					scoreLabel.setText("Score: " + score);
					
					// calculating values
					
					double levelDivisor = 8;
					if (score > 10)
						levelDivisor = 4;
					if (score > 20)
						levelDivisor = 2;
					if (score > 30)
						levelDivisor = 1;
					if (score > 40)
						levelDivisor = 0.5;
					
					int priceOfTree = 25;
					if (score > 10)
						priceOfTree = 30;
					if (score > 20)
						priceOfTree = 35;
					if (score > 30)
						priceOfTree = 45;
					if (score > 40)
						priceOfTree = 60;
					
					
					// finding cost
					cost = (int)(numTrees*priceOfTree + (amountWater + amountSunlight + amountCO2 + amountPesticide)/levelDivisor);
					costLabel.setText("Cost: " + cost);
					
					// finding profit
					//profit = numTrees*55 - (Math.abs(bestWater*numTrees - amountWater) + Math.abs(bestSunlight*numTrees - amountSunlight) + Math.abs(bestCO2*numTrees - amountCO2) + Math.abs(bestPesticides*numTrees - amountPesticide));
					int differencePerTree = (int)(Math.abs(bestWater - 1.0*amountWater/numTrees) + Math.abs(bestSunlight - 1.0*amountSunlight/numTrees) + Math.abs(bestCO2 - 1.0*amountCO2/numTrees) + Math.abs(bestPesticides - 1.0*amountPesticide/numTrees));
					
					profit = 60*numTrees;
					if (differencePerTree >= 5)
						profit = 55*numTrees;
					if (differencePerTree >= 10)
						profit = 50*numTrees;
					if (differencePerTree >= 20)
						profit = 45*numTrees;
					if (differencePerTree >= 30)
						profit = 35*numTrees;
					if (differencePerTree >= 40)
						profit = 25*numTrees;
					if (differencePerTree >= 40)
						profit = 0;
					
					
					// random natural disasters
					Random rand = new Random();
					int maxRandInt  = 10; // starts off as 1/10
					if (score > 10)
						maxRandInt = 9;
					if (score > 20)
						maxRandInt = 7;
					if (score > 30)
						maxRandInt = 5;
					if (score > 40)
						maxRandInt = 2;
					
					int disasterNum = rand.nextInt((maxRandInt - 1) + 1) + 1;
					
					if (disasterNum == 5)
					{
						JOptionPane.showMessageDialog(null, "Oh no! A natural disaster has occured. All your trees have been destroyed. Your trees will not generate any money this year.");
						profit = 0;
					}
					profitLabel.setText("Money from Selling Apples: " + profit);
					
					// revenue
					
					revenue = profit - cost;
					revenueLabel.setText("Money Gained: " + revenue);
					
					bankAccount += revenue;
					bankAccountLabel.setText("Bank Account: " + bankAccount);
					
					repaint();
				}
				else if (e.getSource() == nextButton && statsHidden == false)
				{
					statsHidden = true;
					costLabel.setText("Cost: ?");
					profitLabel.setText("Money from Selling Apples: ?");
					revenueLabel.setText("Money Gained: ?");
					nextButton.setText("Click to Find Profit and Revenue");
					
					if (bankAccount <= 0)
					{
						JOptionPane.showMessageDialog(null, "Sorry! Your game is over. Way to get a score of " + score + " though! If you need help, look at the instructions.");
						gameOver = true;
					}
					
					repaint();
				}
				if (e.getSource() == quitButton || gameOver == true) // if quiting
				{
					//System.out.println("quitting");
					
					if (score > highScore)
						highScore = score;
					System.out.println(highScore);
					
					mainPanel.remove(gp);
					gp = new GamePanel(highScore);
					mainPanel.add(gp, "game");
				}
			}
		}
	}
}









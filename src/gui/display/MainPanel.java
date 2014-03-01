package gui.display;

import gui.buildings.BuildingsFrame;
import gui.buildings.GraveyardFrame;
import gui.buildings.LaboratoryFrame;
import gui.units.UnitsFrame;
import gui.units.ZombieFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.Engine;
import control.GlobalSettings;

import models.buildings.Graveyard;
import models.buildings.Laboratory;
import models.map.MapSquare;
import models.units.Zombie;

public class MainPanel extends JPanel implements ActionListener, MouseListener, KeyListener, WindowListener
{
	private static final long serialVersionUID = -8438576029794021570L;
	
	private Engine engine = new Engine();

	private BoxLayout bl;
	private DrawPanel dp = new DrawPanel(engine.getMap());
	private ButtonPanel bp = new ButtonPanel();

	//external frames
	private JFrame eventFrame = new JFrame();
	private EventsPanel ep = new EventsPanel();
	private BuildingsFrame buildings = null;
	private UnitsFrame units = null;

	public MainPanel()
	{
		//set the layout and add components
		bl = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(bl);

		dp.setFocusable(true);
		dp.requestFocus();
		this.add(dp);
		this.add(bp);

		this.setListeners();

		//TODO: put this in EventsPanel
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		eventFrame.setVisible(true);
		eventFrame.setSize((int)d.getWidth()/5 - 25, (int)d.getHeight()*8/10);
		eventFrame.setLocation((int)(d.getWidth() - d.getWidth()/5), (int)d.getWidth()/20);
		eventFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		eventFrame.add(ep);
	}

	/**
	 * Add listeners to components. You have to get the panel where the component is located.
	 * MainPanel is the listener for all components.
	 */
	private void setListeners()
	{
		bp.getExit().addActionListener(this);
		bp.getEndTurn().addActionListener(this);

		dp.addKeyListener(this);
		dp.addMouseListener(this);
	}

	/**
	 * use adws for movement keys
	 */
	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_D)
		{
			dp.moveCenter(GlobalSettings.EAST);
		}
		else if(ke.getKeyCode() == KeyEvent.VK_A)
		{
			dp.moveCenter(GlobalSettings.WEST);
		}
		else if(ke.getKeyCode() == KeyEvent.VK_W)
		{
			dp.moveCenter(GlobalSettings.NORTH);
		}
		else if(ke.getKeyCode() == KeyEvent.VK_S)
		{
			dp.moveCenter(GlobalSettings.SOUTH);
		}
		else if(ke.getKeyCode() == KeyEvent.VK_SPACE)
		{
			this.openWindow(dp.getCenter());
		}
	}


	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == bp.getExit())
		{
			System.exit(0);
		}
		if(ae.getSource() == bp.getFight())
		{
		}
		if(ae.getSource() == bp.getEndTurn())
		{
			engine.endTurn();
			System.out.println("Turn Ending ...");
			ep.addText("\nCash on hand: " + engine.getTaxes());
		}
	}

	public void mouseClicked(MouseEvent me)
	{
		//first get the map square
		MapSquare ms = this.getMapSquare(me.getX(), me.getY()); 
		//then figure out which type of building and/or unit is there
		// and open the window
		this.openWindow(ms);
	}

	private MapSquare getMapSquare(int x, int y)
	{
		ArrayList<MapSquare> d_map = dp.getDisplay();
		for (int i = 0; i < d_map.size(); i++)
		{
			//find which square was clicked
			if(d_map.get(i).getDisplayX() * GlobalSettings.SQUARE_SIZE <= x &&
					d_map.get(i).getDisplayX() * GlobalSettings.SQUARE_SIZE + GlobalSettings.SQUARE_SIZE >= x &&
					d_map.get(i).getDisplayY() * GlobalSettings.SQUARE_SIZE <= y &&
					d_map.get(i).getDisplayY() * GlobalSettings.SQUARE_SIZE + GlobalSettings.SQUARE_SIZE >= y)
			{
				return d_map.get(i);
			}
		}

		return null;
	}

	private void openWindow(MapSquare ms)
	{
		if(ms.getBuilding() instanceof Laboratory)
		{
			if(buildings != null && !(buildings instanceof LaboratoryFrame))
			{
				buildings = null;
			}
			if(buildings == null)
			{
				buildings = new LaboratoryFrame();
				buildings.addWindowListener(this);
			}
		}
		if(ms.getBuilding() instanceof Graveyard)
		{
			if(buildings != null && !(buildings instanceof GraveyardFrame))
			{
				buildings = null;
			}
			if(buildings == null)
			{
				buildings = new GraveyardFrame();
				buildings.addWindowListener(this);
			}
		}
		if(ms.getUnit() instanceof Zombie)
		{
			if(units != null && !(units instanceof ZombieFrame))
			{
				units = null;
			}
			if(units == null)
			{
				units = new ZombieFrame();
				units.addWindowListener(this);
			}
		}
	}

	public void windowClosed(WindowEvent we)
	{
		if(we.getSource() == buildings)
		{
			buildings = null;
		}
	}

	public void keyReleased(KeyEvent ke)
	{
	}
	public void keyTyped(KeyEvent ke)
	{
	}
	public void mouseEntered(MouseEvent me)
	{
	}
	public void mouseExited(MouseEvent me)
	{
	}
	public void mousePressed(MouseEvent me)
	{
	}
	public void mouseReleased(MouseEvent me)
	{
	}
	public void windowClosing(WindowEvent we)
	{
	}
	public void windowDeactivated(WindowEvent we)
	{
	}
	public void windowDeiconified(WindowEvent we)
	{
	}
	public void windowIconified(WindowEvent we)
	{
	}
	public void windowOpened(WindowEvent we)
	{
	}
	public void windowActivated(WindowEvent we)
	{
	}
}

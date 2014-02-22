package org.bh.app.timer.gui;

import bht.tools.comps.BHMessagePanel;
import bht.tools.comps.BHSelectableLabel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.*;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.logging.Logger;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import org.bh.app.timer.Main;
import org.bh.app.timer.gui.timer.BHTimerPlugin;
import org.bh.app.timer.gui.timer.BHTimerPluginPane;
import org.bh.app.timer.gui.evt.TimerPluginAdapter;

/**
 * BHTimerApplet, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-13
 */
public class BHTimerApplet extends JApplet
{
	public BHTimerApplet(BHTimerFrame initParent) throws HeadlessException
	{
		initGUI();
	}

	private JMenuBar menuBar;
	private JMenu
		appMenu,
		fileMenu,
		toolsMenu,
		helpMenu;
	private JMenuItem
		exitMenuItem;
	private JTabbedPane timerTabbedPane;
	private BHMessagePanel notificationPanel;
	private void initGUI()
	{
		setLayout(new GridBagLayout());
		//<editor-fold defaultstate="collapsed" desc="menuBar">
		{
			menuBar = getJMenuBar();
			if (menuBar == null)
				menuBar = new JMenuBar();
			if (menuBar.getMenuCount() != 0)
				Logger.getGlobal().info("The menu bar might look strange");
			{
				appMenu = new JMenu(Main.APP_ABBR); // I had to :C
				{
				exitMenuItem = new JMenuItem("Exit");
				exitMenuItem.setAccelerator(
						KeyStroke.getKeyStroke(
								'Q',
							InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK
						)
				);
				exitMenuItem.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						System.exit(0);
					}
				});
				appMenu.add(exitMenuItem);
			}
				menuBar.add(appMenu);
			}
			/*{
			fileMenu = new JMenu("File");
			menuBar.add(fileMenu);
			}
			{
			toolsMenu = new JMenu("Tools");
			menuBar.add(toolsMenu);
			}
			{
			helpMenu = new JMenu("Help");
			menuBar.add(helpMenu);
			}*/
			setJMenuBar(menuBar);
		}
		//</editor-fold>
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = BOTH;
		gbc.gridx = gbc.gridy = 1;
		gbc.weightx = gbc.weighty = 1;
		//<editor-fold defaultstate="collapsed" desc="timerTabbedPane">
		{
			timerTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
			add(timerTabbedPane, gbc);
		}
		//</editor-fold>
		//<editor-fold defaultstate="collapsed" desc="notificationPanel">
		{
			notificationPanel = new BHMessagePanel();
			gbc.gridy = RELATIVE;
			gbc.weighty = 0;
			add(notificationPanel, gbc);
		}
//</editor-fold>
	}
	public void registerPlugin(BHTimerPlugin newPlugin)
	{
		timerTabbedPane.addTab(newPlugin.getName()+"", newPlugin.asComponent());
	}

	public BHTimerPlugin getCurrentTimerPlugin()
	{
		Component selection = timerTabbedPane.getSelectedComponent();
		return
				selection instanceof BHTimerPlugin
					? (BHTimerPlugin)selection
					: selection instanceof BHTimerPluginPane
						? ((BHTimerPluginPane)selection).asPlugin()
						: null;
	}
}

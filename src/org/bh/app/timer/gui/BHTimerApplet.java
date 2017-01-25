package org.bh.app.timer.gui;

import bht.resources.Icons;
import bht.tools.comps.BHMessagePanel;
import bht.tools.fx.LookAndFeelChanger;
import bht.tools.util.ArrayPP;
import bht.tools.util.StringPP;
import java.awt.Component;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.*;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JApplet;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import org.bh.app.timer.Language;
import org.bh.app.timer.Main;
import org.bh.app.timer.BHTimerPlugin;
import org.bh.app.timer.evt.MenuActionEvent;
import org.bh.app.timer.evt.MenuActionListener;
import org.bh.app.timer.gui.timer.BHTimerPluginPane;

/**
 * BHTimerApplet, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-13
 */
public class BHTimerApplet extends JApplet
{
	private Language language = Language.getInstance();
	private ArrayPP<MenuActionListener> menuActionListeners = new ArrayPP<MenuActionListener>();
	
	public BHTimerApplet(BHTimerFrame initParent) throws HeadlessException
	{
		initGUI();
	}

	private JMenuBar menuBar;
	private JMenu
		appMenu,
		viewMenu,
		toolsMenu,
		helpMenu;
	private JMenuItem
		exitMenuItem,
		settingsMenuItem;
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
			//<editor-fold defaultstate="collapsed" desc="appMenu">
			{
				appMenu = new JMenu(Main.APP_ABBR); // I had to use static stuff :C
				//<editor-fold defaultstate="collapsed" desc="exitMenuItem">
				{
					exitMenuItem = new JMenuItem(new StringPP(language.exit.getState()).toTitleCase()+"", Icons.getIcon(Icons.APP_EXIT));
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
				//</editor-fold>
				menuBar.add(appMenu);
			}
			//</editor-fold>
			//<editor-fold defaultstate="collapsed" desc="viewMenu">
			{
				viewMenu = new JMenu(new StringPP(language.view.getState()).toTitleCase()+"");
				viewMenu.add(new LookAndFeelChanger.LookAndFeelMenu(new StringPP(language.looksAndFeels.getState()).toTitleCase()+""));
				menuBar.add(viewMenu);
			}
			//</editor-fold>
			//<editor-fold defaultstate="collapsed" desc="toolsMenu">
			{
				toolsMenu = new JMenu(new StringPP(language.tools.getState()).toTitleCase()+"");
				//<editor-fold defaultstate="collapsed" desc="settigsMenuItem">
				{
					settingsMenuItem = new JMenuItem(new AbstractAction(new StringPP(language.settings.getState()).toTitleCase()+"", Icons.getIcon(Icons.SETTINGS_16))
					{
						
						@Override
						public void actionPerformed(ActionEvent e)
						{
							MenuActionEvent evt = new MenuActionEvent(BHTimerApplet.this);
							for (MenuActionListener menuActionListener : menuActionListeners)
							{
								menuActionListener.settingsItemClicked(evt);
							}
						}
					});
					settingsMenuItem.setAccelerator(KeyStroke.getKeyStroke(',', InputEvent.CTRL_DOWN_MASK));
				}
				//</editor-fold>
				toolsMenu.add(settingsMenuItem);
				menuBar.add(toolsMenu);
			}
			//</editor-fold>
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
			//add(notificationPanel, gbc);
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

	public void addMenuActionListener(MenuActionListener mal)
	{
		menuActionListeners.add(mal);
	}
}
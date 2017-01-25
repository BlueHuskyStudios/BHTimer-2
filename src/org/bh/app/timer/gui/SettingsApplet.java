package org.bh.app.timer.gui;

import bht.tools.util.StringPP;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JApplet;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import org.bh.app.timer.Language;
import org.bh.app.timer.Main;
import org.bh.app.timer.delegation.MiniTimerDelegate;

/**
 * SettingsApplet, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-03-03
 */
public class SettingsApplet extends JApplet
{
	private Language language = Language.getInstance();
	
	public SettingsApplet() throws HeadlessException
	{
		initGUI();
	}

	private JTabbedPane settingsTabbedPane;
	private JPanel
		generalSettingsPane,
		miniTimerSettingsPane,
			miniTimerPositionPane,
			miniTimerBehaviorPane;
	private JComboBox<String> miniTimerPositionComboBox;
	private JCheckBox
		miniTimerOnCloseCheckBox,
		miniTimerOnMinimizeCheckBox;
	private void initGUI()
	{
		settingsTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
		//<editor-fold defaultstate="collapsed" desc="generalSettingsPane">
		{
			generalSettingsPane = new JPanel();
			generalSettingsPane.add(new JLabel("Sorry, no settings here yet!"));
			
			settingsTabbedPane.addTab(
					new StringPP(language.generalSettings.getState()).toTitleCase()+"",
				null,
				generalSettingsPane,
				new StringPP(language.generalSettingsTip.getState()).toSentenceCase()+""
			);
		}
		//</editor-fold>
		//<editor-fold defaultstate="collapsed" desc="miniTimerSettingsPane">
		{
			miniTimerSettingsPane = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = gbc.gridy = 0;
			
			//<editor-fold defaultstate="collapsed" desc="miniTimerPositionPane">
			{
				miniTimerPositionPane = new JPanel();
				miniTimerPositionPane.setBorder(new TitledBorder("Position"));
				//<editor-fold defaultstate="collapsed" desc="miniTimerPositionComboBox">
				{
					miniTimerPositionComboBox = new JComboBox<>(
						new String[]{
							"Top-Left",
							"Top-Right",
							"Bottom-Left",
							"Bottom-Right"
						}
					);
					byte selection =
						Main.manager == null
							? MiniTimerDelegate.POS_BOTTOM_RIGHT
							: Main.manager.getMiniTimerPosition();
					switch (selection)
					{
						case MiniTimerDelegate.POS_TOP_LEFT:
							selection = 0;
							break;
						case MiniTimerDelegate.POS_TOP_RIGHT:
							selection = 1;
							break;
						case MiniTimerDelegate.POS_BOTTOM_LEFT:
							selection = 2;
							break;
						default:
						case MiniTimerDelegate.POS_BOTTOM_RIGHT:
							selection = 3;
							break;
					}
					miniTimerPositionComboBox.setSelectedIndex(selection);
					miniTimerPositionComboBox.addItemListener(new ItemListener()
					{
						@Override
						public void itemStateChanged(ItemEvent e)
						{
							switch (miniTimerPositionComboBox.getSelectedIndex())
							{
								case 0:
									Main.manager.setMiniTimerPosition(MiniTimerDelegate.POS_TOP_LEFT);
									break;
								case 1:
									Main.manager.setMiniTimerPosition(MiniTimerDelegate.POS_TOP_RIGHT);
									break;
								case 2:
									Main.manager.setMiniTimerPosition(MiniTimerDelegate.POS_BOTTOM_LEFT);
									break;
								case 3:
									Main.manager.setMiniTimerPosition(MiniTimerDelegate.POS_BOTTOM_RIGHT);
									break;
								default:
									Logger.getGlobal().log(Level.WARNING, "You shouldn't have been able to choose option " + miniTimerPositionComboBox.getSelectedIndex() + " (" + miniTimerPositionComboBox.getSelectedItem() + ")");
							}
						}
					});
					miniTimerPositionPane.add(miniTimerPositionComboBox);
				}
				//</editor-fold>
				miniTimerSettingsPane.add(miniTimerPositionPane, gbc);
			}
			//</editor-fold>
			//<editor-fold defaultstate="collapsed" desc="miniTimerBehaviorPane">
			{
				miniTimerBehaviorPane = new JPanel(new GridBagLayout());
				GridBagConstraints mtucgbc = new GridBagConstraints();
				mtucgbc.gridx = mtucgbc.gridy = 0;
				miniTimerBehaviorPane.setBorder(new TitledBorder("Behavior"));
				{
					miniTimerOnCloseCheckBox = new JCheckBox(new AbstractAction("Use on close")
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							byte useCase = Main.manager.getMiniTimerUseCase();
							if (miniTimerOnCloseCheckBox.isSelected())
								useCase |= MiniTimerDelegate.USE_ON_CLOSE;
							else
								useCase &= ~MiniTimerDelegate.USE_ON_CLOSE;
							Main.manager.setMiniTimerUseCase(useCase);
						}
					});
					miniTimerOnCloseCheckBox.setSelected((Main.manager.getMiniTimerUseCase() & MiniTimerDelegate.USE_ON_CLOSE) != 0);
					miniTimerBehaviorPane.add(miniTimerOnCloseCheckBox, mtucgbc);
				}
				{
					miniTimerOnMinimizeCheckBox = new JCheckBox(new AbstractAction("Use on minimize")
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							byte useCase = Main.manager.getMiniTimerUseCase();
							if (miniTimerOnMinimizeCheckBox.isSelected())
								useCase |= MiniTimerDelegate.USE_ON_MINIMIZE;
							else
								useCase &= ~MiniTimerDelegate.USE_ON_MINIMIZE;
							Main.manager.setMiniTimerUseCase(useCase);
						}
					});
					miniTimerOnMinimizeCheckBox.setSelected((Main.manager.getMiniTimerUseCase() & MiniTimerDelegate.USE_ON_MINIMIZE) != 0);
					mtucgbc.gridy++;
					miniTimerBehaviorPane.add(miniTimerOnMinimizeCheckBox, mtucgbc);
				}
				gbc.gridy++;
				miniTimerSettingsPane.add(miniTimerBehaviorPane, gbc);
			}
			//</editor-fold>
			
			
			
			settingsTabbedPane.addTab(
					new StringPP(language.miniTimerSettings.getState()).toTitleCase()+"",
				null,
				miniTimerSettingsPane,
				new StringPP(language.miniTimerSettingsTip.getState()).toSentenceCase()+""
			);
		}
		//</editor-fold>
		setContentPane(settingsTabbedPane);
	}
	
}

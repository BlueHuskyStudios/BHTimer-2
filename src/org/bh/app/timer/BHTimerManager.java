package org.bh.app.timer;

import bht.test.tools.fx.CompAction5;
import bht.test.tools.fx.ani.ResizeAnimation;
import bht.tools.util.BHTimer;
import bht.tools.util.dynamics.Bézier2D;
import bht.tools.util.dynamics.ProgressingValue;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;
import org.bh.app.timer.Settings;
import org.bh.app.timer.gui.MorphAnimation;
import org.bh.app.timer.gui.delegation.BHTimerDelegate;
import org.bh.app.timer.gui.delegation.MiniTimerDelegate;
import static org.bh.app.timer.gui.delegation.MiniTimerDelegate.POS_BOTTOM_LEFT;
import static org.bh.app.timer.gui.delegation.MiniTimerDelegate.POS_BOTTOM_RIGHT;
import static org.bh.app.timer.gui.delegation.MiniTimerDelegate.POS_TOP_LEFT;
import static org.bh.app.timer.gui.delegation.MiniTimerDelegate.POS_TOP_RIGHT;
import org.bh.app.timer.gui.evt.MiniTimerAdapter;
import org.bh.app.timer.gui.evt.MiniTimerEvent;
import org.bh.app.timer.gui.timer.BHTimerPlugin;

/**
 * BHTimerManager, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * Manages the entire application while and after the Main Method runs. 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-06
 */
public class BHTimerManager
{
	public static final byte
			STATE_ONLY_BIG_TIMER = 0b0000_0001,
			STATE_ONLY_MINI_TIMER = 0b0000_0010,
			STATE_BOTH_MINI_AND_BIG_TIMERS = STATE_ONLY_BIG_TIMER | STATE_ONLY_MINI_TIMER;

	private BHTimerDelegate bigTimer;
	private MiniTimerDelegate miniTimer;
	private Settings settings;
	private MorphAnimation morphAnimation;
	private ResizeAnimation resizeAnimation;
	private CompAction5 animator;
	private ProgressingValue
		animationCurve = Bézier2D.DEFAULT,
		reverseAnimationCurve = Bézier2D.getReverse(Bézier2D.DEFAULT);
	private ArrayList<BHTimerPlugin> plugins = new ArrayList<BHTimerPlugin>();
	private final BHTimer coreTimer;

	public BHTimerManager()
	{
		settings = new Settings();
		animator = new CompAction5(settings.animationDuration.getState(), settings.animationFPS.getState());
		bigTimer = new BHTimerDelegate(this);
		{
			miniTimer = new MiniTimerDelegate(bigTimer, this);
			miniTimer.addMiniTimerListener(new MiniTimerAdapter()
			{
				@Override
				public void expandButtonPressed(MiniTimerEvent evt)
				{
					morphToBigTimer();
				}
			});
			plugins.add(miniTimer);
			setMiniTimerPosition(settings.miniTimerPosition.getState());
		}
		
		coreTimer = new BHTimer(new Runnable()
		{
			boolean isGoing, isPaused;
			@Override
			public void run()
			{
				isGoing = false;
				isPaused = true;
				for (BHTimerPlugin plugin : plugins)
				{
					if (isGoing |= plugin.isGoing())
						plugin.update();
					isPaused &= plugin.isPaused();
				}
				coreTimer.setDelay(isGoing && !isPaused ? 1 : 100);
			}
		}, 100l);
		coreTimer.start();
		
		new BHTimer(new Runnable() // Core timer monitor
		{
			@Override
			public void run()
			{
				System.out.println("Core timer delay: " + coreTimer.getDelay());
			}
		}, 1000).start();
	}

	public void setMainWindowVisible(boolean visibility)
	{
		bigTimer.setVisible(visibility);
	}

	public boolean isMiniTimerActive()
	{
		return settings.miniTimerUseCase.getState() != MiniTimerDelegate.USE_NEVER;
	}

	public byte getMiniTimerUseCase()
	{
		return settings.miniTimerUseCase.getState();
	}

	public void setDisplayState(byte newDisplayState)
	{
		settings.displayState.setState(newDisplayState);
		validate();
	}

	private void validate()
	{
		switch (settings.displayState.getState())
		{
			case STATE_ONLY_MINI_TIMER:
				morphToMiniTimer();
				break;
			case STATE_ONLY_BIG_TIMER:
				morphToBigTimer();
				break;
			case STATE_BOTH_MINI_AND_BIG_TIMERS:
				miniTimer.setVisible(true);
				miniTimer.validate();
				bigTimer.setVisible(true);
				bigTimer.validate();
		}
	}

	public void morphToMiniTimer()
	{
		if (miniTimer.isVisible())
			return;
		
		morph(true);
	}

	public void morphToBigTimer()
	{
		if (bigTimer.isVisible())
			return;
		
		morph(false);
	}

	/**
	 * Morphs to or from the miniTimer
	 * @param toMiniTimer iff {@code true}, morphs to the mini timer. Else, morphs to the big timer.
	 */
	public void morph(boolean toMiniTimer)
	{
		checkAnimator();
		if (morphAnimation != null && morphAnimation.isPlaying())
			return;
		
		if (toMiniTimer)
			miniTimer.setCurrentTimerPlugin(bigTimer.getCurrentTimerPlugin());
		
		morphAnimation = new MorphAnimation(
				(toMiniTimer ? bigTimer : miniTimer).getComponent(),
				(toMiniTimer ? miniTimer : bigTimer).getComponent(),
				animationCurve,
				animator);
		miniTimer.validate();
		morphAnimation.morph();
	}

	public void setMiniTimerPosition(byte miniTimerPosition)
	{
		checkAnimator();
		Rectangle
			screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds(),
			miniTimerBounds = miniTimer.getBounds(),
			endLocation = new Rectangle(0, 0, miniTimerBounds.width, miniTimerBounds.height);
		switch (miniTimerPosition)
		{
			case MiniTimerDelegate.POS_TOP_LEFT:
			case MiniTimerDelegate.POS_TOP_RIGHT:
				endLocation.y = screenBounds.y;
				break;
			case MiniTimerDelegate.POS_BOTTOM_LEFT:
			case MiniTimerDelegate.POS_BOTTOM_RIGHT:
				endLocation.y = screenBounds.height - miniTimerBounds.height;
				break;
			default:
				throw new AssertionError("Position must be " + POS_TOP_LEFT + ", " + POS_TOP_RIGHT + ", " + POS_BOTTOM_RIGHT + ", or " + POS_BOTTOM_LEFT);
		}
		switch (miniTimerPosition)
		{
			case MiniTimerDelegate.POS_BOTTOM_LEFT:
			case MiniTimerDelegate.POS_TOP_LEFT:
				endLocation.x = screenBounds.x;
				break;
			case MiniTimerDelegate.POS_BOTTOM_RIGHT:
			case MiniTimerDelegate.POS_TOP_RIGHT:
				endLocation.x = screenBounds.width - miniTimerBounds.width;
				break;
			default:
				throw new AssertionError("Position must be " + POS_TOP_LEFT + ", " + POS_TOP_RIGHT + ", " + POS_BOTTOM_RIGHT + ", or " + POS_BOTTOM_LEFT);
		}
		resizeAnimation = new ResizeAnimation(animationCurve, miniTimer.getComponent().getBounds(), endLocation);
		animator.animate(miniTimer.getComponent(), resizeAnimation, null);
	}

	private void checkAnimator()
	{
		if (animator != null && animator.isAnimating())
		{
			animator.stop();
			final long TIMEOUT = 1000 * 30, START = System.currentTimeMillis();
			while (animator.isAnimating())
			{
				if (System.currentTimeMillis() - START > TIMEOUT)
					throw new IllegalStateException("Animator is too busy to handle setting MiniTimer position. Try again later.");
			}
		}
	}

	public Settings getSettings()
	{
		return settings;
	}
	
	public BHTimerManager registerPlugin(BHTimerPlugin newPlugin)
	{
		plugins.add(newPlugin);
		bigTimer.registerPlugin(newPlugin);
		return this;
	}
}

package org.bh.app.timer.gui;

import bht.test.tools.fx.CompAction5;
import bht.test.tools.fx.ani.AnimationAdapter;
import bht.test.tools.fx.ani.AnimationEvent;
import bht.test.tools.fx.ani.DefCompActionAnimation;
import bht.tools.comps.BHCompUtilities;
import bht.tools.util.dynamics.ProgressingValue;
import bht.tools.util.math.Numbers;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * MorphAnimation, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-11
 */
public class MorphAnimation extends DefCompActionAnimation
{
	private Component comp1, comp2;
	private CompAction5 animator;
	public MorphAnimation(
			Component component1, Component component2,
			ProgressingValue movmentCurve,
			CompAction5 initAnimator)
	{
		super(movmentCurve, true, component1.getBounds(), component2.getBounds(), true, 1, 0);
		comp1 = component1;
		comp2 = component2;
		animator = initAnimator;
	}

	public void morph()
	{
		if (isPlaying())
			return;
		/*animator1.animate(
			comp1,
			this,
			new AnimationAdapter()
			{
				Rectangle originalBounds = comp1.getBounds();
				@Override
				public void animationStarting(AnimationEvent e)
				{
					comp1.setVisible(true);
				}

				@Override public void animationCompleted(AnimationEvent e)
				{
					comp1.addComponentListener(new ComponentAdapter()
					{
						@Override
						public void componentHidden(ComponentEvent e)
						{
							comp1.setBounds(originalBounds);
							comp1.removeComponentListener(this);
						}
					});
					comp1.setVisible(false);
				}
			}
		);
		animator2.animate(
			comp2,
			this,
			new AnimationAdapter()
			{
				@Override public void animationStarting(AnimationEvent e)
				{
					comp2.setVisible(true);
				}

				@Override public void animationCompleted(AnimationEvent e)
				{
					comp2.setVisible(true);
				}
			}
		);*/
		
		animator.animate(
			new Window(null) // Make it a Window because Windows can be semi-opaque
			{
				private final boolean
					FADES_1 = BHCompUtilities.detectCanFade(comp1),
					FADES_2 = BHCompUtilities.detectCanFade(comp2);
				@Override
				public void setBounds(int x, int y, int width, int height)
				{
					comp1.setBounds(x, y, width, height);
					comp2.setBounds(x, y, width, height);
				}
				
				@Override
				public void setOpacity(float opacity)
				{
					if (FADES_1)
						((Window)comp1).setOpacity(Numbers.between(1 - opacity, 0, 1));
					if (FADES_2)
						((Window)comp2).setOpacity(Numbers.between(opacity, 0, 1));
				}

				@Override
				public String toString()
				{
					return comp1 + " & " + comp2;
				}
			},
			this,
			new AnimationAdapter()
			{
				Rectangle originalBounds = comp1.getBounds();
				@Override
				public void animationStarting(AnimationEvent e)
				{
					comp1.setVisible(true);
					comp2.setVisible(true);
				}

				@Override public void animationCompleted(AnimationEvent e)
				{
					comp1.addComponentListener(new ComponentAdapter()
					{
						@Override
						public void componentHidden(ComponentEvent e)
						{
							comp1.setBounds(originalBounds);
							comp1.removeComponentListener(this);
						}
					});
					comp1.setVisible(false);
					comp2.setVisible(true);
				}
			}
		);
	}

	public boolean isPlaying()
	{
		return animator.isAnimating();
	}
}

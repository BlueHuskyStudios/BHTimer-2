package org.bh.app.timer.gui.delegation;

import java.awt.Component;
import java.awt.Rectangle;

/**
 * ComponentDelegate, made for BH Timer 2 Try 5, is copyright Blue Husky Programming ©2014 GPLv3<HR/>
 * 
 * @param <T> the type of component being represented
 * 
 * @author Kyli Rouge of Blue Husky Programming
 * @version 1.0.0
 * @since 2014-02-11
 */
public class ComponentDelegate<T extends Component>
{
	protected T basis;
	
	/**
	 * ONLY USE THIS IF YOU ARE GOING TO INITIALIZE THE BASIS COMPONENT LATER! Not initializing the basis will cause {@link NullPointerException}s to be thrown.
	 * This constructor creates a new Component Delegate, with no basis.
	 */
	public ComponentDelegate()
	{
		this(null);
	}
	/**
	 * Creates a new Component Delegate, as a delegate for the given component
	 * @param initBasis the basis for this delegate
	 */
	public ComponentDelegate(T initBasis)
	{
		basis = initBasis;
	}
	
	/**
	 * Returns {@code true} iff the basis is visible.
	 * @return {@code true} iff the basis is visible.
	 */
	public boolean isVisible()
	{
		return basis.isVisible();
	}
	
	/**
	 * Sets the visibility of the basis to the given value
	 * @param newVisibility the new visibility of the basis
	 */
	public void setVisible(boolean newVisibility)
	{
		basis.setVisible(newVisibility);
	}
	
	/**
	 * NOT RECOMMENDED! This returns the basis.
	 * @return the basis
	 * @deprecated when possible, use the delegate instead.
	 */
	public T getComponent()
	{
		return basis;
	}
	
	/**
	 * Returns the boundaries of the basis
	 * @return the boundaries of the basis
	 */
	public Rectangle getBounds()
	{
		return basis.getBounds();
	}
	
	/**
	 * Sets the boundaries of the basis
	 * @param newBounds the new boundaries of the basis
	 */
	public void setBounds(Rectangle newBounds)
	{
		basis.setBounds(newBounds);
	}
	
	/**
	 * Calls the {@link Component#validate()} method of the basis
	 */
	public void validate()
	{
		basis.validate();
	}
}

package com.example.myvaadinapp;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;

@SuppressWarnings("serial")
public final class NavBarMenu extends CustomComponent {

	public NavBarMenu() {
		
		setId("navBar");
		setStyleName("navbar");
		setWidth("100%");
		setSizeUndefined();
        setCompositionRoot(buildContent());
	}
	/**
	 * cette fonction pour initiliser le contenu de la composante sideBarMenu
	 * 
	 */
	private Component buildContent() {
		
		HorizontalLayout nav = new HorizontalLayout();
		nav.setPrimaryStyleName("navbar2");
		nav.setWidth("100%");
		MenuBar mb = new MenuBar();
		nav.addComponent(mb);
		mb.setPrimaryStyleName("navbarmenu");
		mb.addItem("Profile", null, null);
		mb.addItem("Messages", null, null);
		mb.addItem("Settings", null, null);
		return nav;
	}
	
	
}

package com.example.myvaadinapp;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public final class SideBarMenu extends CustomComponent {

	public SideBarMenu() {
		    addStyleName("valo-menu");
	        setId("menu");
	        setSizeUndefined();
	        setCompositionRoot(buildContent());
	}
	
	/**
	 * cette fonction pour initiliser le contenu de la composante sideBarMenu
	 * 
	 */
	public Component buildContent(){
		final CssLayout sidebar = new CssLayout();
		sidebar.addStyleName("sidebar");
		sidebar.setHeight("100%");
		sidebar.setWidth(null);
		sidebar.addComponent(buildTitle());
		sidebar.addComponent(buildMenu());
		sidebar.addComponent(buildInfo());
		
		return sidebar;
	}

	/**
	 * cette fonction pour initiliser la partie info de la composante sideBarMenu
	 * 
	 */
	private Component buildInfo() {
		Label info = new Label();
		return info;
	}

	/**
	 * cette fonction pour initiliser la partie menu de la composante sideBarMenu
	 * 
	 */
	private Component buildMenu() {
		CssLayout menu = new CssLayout();
		menu.setStyleName("valo-menuitems");
		
		Button home =  new Button("Acceuil");
		home.setPrimaryStyleName("valo-menu-item");
		menu.addComponent(home);
		Button report =  new Button("Rapport");
		report.setPrimaryStyleName("valo-menu-item");
		menu.addComponent(report);
		Button logout =  new Button("logout");
		logout.setPrimaryStyleName("valo-menu-item");
		menu.addComponent(logout);
		
		return menu;
	}

	/**
	 * cette fonction pour initiliser le titre de la composante sideBarMenu
	 * 
	 */
	private Component buildTitle() {
		Label title = new Label("<h1>Administration</h1>",ContentMode.HTML);
		HorizontalLayout logo = new HorizontalLayout(title); 
		logo.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		logo.setStyleName("valo-menu-title");
		logo.addStyleName("bg-warning");
		return logo;
	}
	
	
	
}

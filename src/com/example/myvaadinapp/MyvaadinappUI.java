package com.example.myvaadinapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.exemple.entities.Task;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("myvaadinapp")
public class MyvaadinappUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyvaadinappUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	private  Table taskList = new Table();
	private  List<Task> tasks = new ArrayList<Task>();
	private  BeanItemContainer<Task> bc = new BeanItemContainer<Task>(Task.class);
	private  final VerticalLayout TableLayout = new VerticalLayout();
	private  final FormLayout formLayout = new FormLayout();
	private  HorizontalLayout splitPanel = new HorizontalLayout();
	private  HorizontalLayout viewContent = new HorizontalLayout();
	private  VerticalLayout body = new VerticalLayout();
	private  TextField id = new TextField("ID");
	private  final TextField title = new TextField("Titre");
	private  final TextArea task = new TextArea("Tache");
	private  DateField date = new DateField("Date");
	private  Button button = new Button("Ajouter");
	private  Button cancel = new Button("Annuler");
	private  Button update = new Button("Valider");
	private  Button remove = new Button("supprimer");
	
	@Override
	protected void init(VaadinRequest request) {
		
		setSizeFull();
		setContent(splitPanel);
		
		/*Splitpanel = body + sidebar*/
		splitPanel.setWidth("100%");
		splitPanel.setSizeFull();
		splitPanel.addComponent(new SideBarMenu());
		splitPanel.addComponent(body);
		splitPanel.setExpandRatio(body, 1.0f);
		
		
		/*body = viewcontent + navbar*/
		NavBarMenu nav = new NavBarMenu();
		body.setSizeFull();
		body.addComponent(nav);
		body.setComponentAlignment(nav, Alignment.MIDDLE_RIGHT);
		body.addComponent(viewContent);
		body.setExpandRatio(viewContent, 1.0f);
		
		/*viewcontent = formLayout + TableLayout*/
		viewContent.addComponent(formLayout);
		viewContent.addComponent(TableLayout);
		viewContent.setSizeFull();
		viewContent.setWidth("100%");
		viewContent.setStyleName("view-content");
		viewContent.setExpandRatio(TableLayout, 1.0f);
		
		TableLayout.setMargin(true);
		TableLayout.setHeight("100%");
		TableLayout.addComponent(taskList);
		
		initTaskList();
		initFormLayout();
		
	}
	
	/**
	 * cette fonction pour initialiser le contenu du Layout Formulaire
	 * 
	 */
	public void initFormLayout(){
		
		formLayout.setStyleName("form");
		formLayout.setMargin(true);
		formLayout.setSizeFull();
		formLayout.setWidth("400px");
		HorizontalLayout buttons =new HorizontalLayout();
		
		id.setWidth("100%");
		title.setWidth("100%");
		task.setWidth("100%");
		date.setWidth("100%");
		
		formLayout.addComponent(id);
		formLayout.addComponent(title);
		formLayout.addComponent(task);
		formLayout.addComponent(date);
		
		

		remove.addStyleName("bg-danger");
		update.addStyleName("bg-primary");
		cancel.addStyleName("bg-warning");
		button.addStyleName("bg-success");

		formLayout.addComponent(buttons);
		buttons.addComponent(button);
		buttons.addComponent(cancel);
		buttons.addComponent(update);
		buttons.addComponent(remove);
		buttons.setSpacing(true);
		setAddMode();
		
		button.addClickListener(e->newTask());
		
		cancel.addClickListener(e->setAddMode());
		
		update.addClickListener(e->updateTask());
		
		remove.addClickListener(e->removeTask());
		
	}

	/**
	 * cette fonction pour initiliser le layout de la table
	 * on ajoutant une liste de donnees a la table 
	 * 
	 */
	public void initTaskList(){
		
		
		tasks.add(new Task(1, "Task 1", "Comparaison entre Java8 et Java 7", new Date()));
		tasks.add(new Task(2, "Task 2", "Comparaison entre Framework MVC", new Date()));
		tasks.add(new Task(3, "Task 3", "Exemple app avec Vaadin", new Date()));
		tasks.add(new Task(4, "Task 4", "new task", new Date()));
		tasks.add(new Task(5, "Task 5", "new task 2", new Date()));
		
		
		updateTableData();
		taskList.setColumnHeader("id","ID");
		taskList.setColumnHeader("task","Tache");
		taskList.setColumnHeader("taskDate","Date");
		taskList.setColumnHeader("title","Titre");
		taskList.setSizeFull();
		taskList.setCaption("Voila la list des taches");
		taskList.setSelectable(true);
		taskList.setMultiSelect(false);
		taskList.setImmediate(true);
		taskList.setColumnReorderingAllowed(true);
		taskList.setColumnCollapsingAllowed(true);
		taskList.setPageLength(taskList.size());
		//taskList.setWidth("100%");
		taskList.addValueChangeListener(e->onListSelection());
		
	}
	
	/**
	 * cette fonction s'execute l'hors de la selection d'une ligne de la table
	 * elle permet de passer au mode de modification de la ligne selectionner
	 * 
	 */
	public void onListSelection() {
		if (taskList.getValue()!=null) {
			Task t = (Task) taskList.getValue();
			task.setValue(t.getTask());
			title.setValue(t.getTitle());
			id.setValue(t.getId()+"");
			date.setValue(t.getTaskDate());
			setUpdateMode();
		}else System.out.println("Nooooooooooooooooo WAY");
		
	}

	/**
	 * cette fonction permet de passer au mode de Modification 
	 * 
	 */
	public void setUpdateMode() {
		id.setVisible(true);
		id.setEnabled(false);
		remove.setVisible(true);
		cancel.setVisible(true);
		update.setVisible(true);
		button.setVisible(false);
	}
	
	/**
	 * cette fonction permet de passer au mode d'Ajout 
	 * 
	 */
	public void setAddMode(){
		
		id.setVisible(false);;
		id.setValue("");
		task.setValue("");
		title.setValue("");
		date.setValue(new Date());
		remove.setVisible(false);
		cancel.setVisible(false);
		update.setVisible(false);
		button.setVisible(true);
	}


	/**
	 * cette fonction permet de recharger la table 
	 * 
	 */
	public void updateTableData(){
		bc.removeAllItems();
		bc.addAll(tasks);
		taskList.setContainerDataSource(bc);
	}
	
	
	/**
	 * cette fonction permet d'ajouter une nouvelle tache
	 * 
	 */
	private void newTask() {
		Task t = new Task(tasks.get(tasks.size()-1).getId()+1, title.getValue(), task.getValue(), date.getValue());
		tasks.add(t);
		bc.addBean(tasks.get(tasks.size()-1));
		Notification.show("Bien Ajouter", Type.TRAY_NOTIFICATION);
		setAddMode();
		
	}
	
	/**
	 * cette fonction permet de modifier une tache
	 * 
	 */
	private void updateTask() {
		Task t = (Task) taskList.getValue();
		
		tasks.forEach(tk ->{
							if (tk.getId()==t.getId()) 
							{
							tk.setTask(task.getValue());
							tk.setTitle(title.getValue());
							tk.setTaskDate(date.getValue());
							}
						}
					  );
		Notification.show("la tache avec l'id : "+t.getId()+" a ete bien modifier", Type.TRAY_NOTIFICATION);
		updateTableData();
		setAddMode();
		
	}
	
	/**
	 * cette fonction permet de supprimer une tache
	 * 
	 */
	private void removeTask() {
		Object t = taskList.getValue();
		taskList.getContainerDataSource().removeItem(t);
		Notification.show("Suppression avec succès", Type.TRAY_NOTIFICATION);
		setAddMode();
		
	}
}
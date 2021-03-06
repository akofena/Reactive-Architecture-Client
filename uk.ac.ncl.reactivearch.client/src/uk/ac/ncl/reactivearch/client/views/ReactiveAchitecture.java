package uk.ac.ncl.reactivearch.client.views;


import javax.swing.JOptionPane;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ReactiveAchitecture extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "uk.ac.ncl.reactivearch.client.views.Reactive Achitecture Class";

	private TableViewer viewer;
	private Action action1;
	private Action action2;
	private Action action3;
	private Action action4;
	private Action doubleClickAction;

	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	 
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
			return new String[] { "Register", "Create_Project_Team", "Upload_Artefact(s)", "Download_Artefact(s)"};
		}
	}
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().
					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}
	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public ReactiveAchitecture() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "uk.ac.ncl.reactivearch.client.viewer");
		getSite().setSelectionProvider(viewer);
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ReactiveAchitecture.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
		manager.add(new Separator());
		manager.add(action3);
		manager.add(new Separator());
		manager.add(action4);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		manager.add(action3);
		manager.add(action4);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
		manager.add(action3);
		manager.add(action4);
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				//showMessage("Registeration (Action 1) executed");
				
				RegisterDeveloper.display();
				
				//ReactiveArchitectureMenu.main(null);
			}
		};
		action1.setText("Register");
		action1.setToolTipText("Registration");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		action2 = new Action() {
			public void run() {
				if(RegisterDeveloper.getDevID().equals(null)){
					TeamCreation.display();
					JOptionPane.showConfirmDialog(null, "You need to Register first!", "Teamwork", JOptionPane.PLAIN_MESSAGE);
				}
				else{
				TeamCreation.display();
				}
				//showMessage("Team Creation (Action 2) executed");
			}
		};
		action2.setText("Create Team");
		action2.setToolTipText("Team Creation");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		action3 = new Action() {
			public void run() {
				//showMessage("Artefact Upload (Action 3) executed");
				if(RegisterDeveloper.getDevID().equals(null)){
					ArtefactsUpload.artUpload();
					JOptionPane.showConfirmDialog(null, "You need to Register first!", "Teamwork", JOptionPane.PLAIN_MESSAGE);
				}
				else{
				ArtefactsUpload.artUpload();
				}
			}
		};
		action3.setText("Upload Artefact(s)");
		action3.setToolTipText("Registration");
		action3.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		action4 = new Action() {
			public void run() {
				//showMessage("Artefact Download (Action 4) executed");
				//Object[] o;
				//if()
				if(RegisterDeveloper.getDevID().equals(null)){
					ArtefactsDownload.display();
					JOptionPane.showConfirmDialog(null, "You need to Register first!", "Teamwork", JOptionPane.PLAIN_MESSAGE);
				}
				else{
				ArtefactsDownload.display();
				}
			}
		};
		action4.setText("Download Artefact(s)");
		action4.setToolTipText("Download Artefact(s)");
		action4.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				
				//showMessage("Double-click detected on "+obj.toString());
				
				final String value = obj.toString();
				
				switch(value) {
				    case "Register":
				    	action1 = new Action() {
							public void run() {
								//showMessage("["+value+"] Registeration (Action 1) executed");
								RegisterDeveloper.display();
							}
						};
						action1.setText("Register");
						action1.setToolTipText("Registration");
						action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
							getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
				        break;
				    case "Create_Project_Team":
				    	action2 = new Action() {
							public void run() {
								//showMessage("["+value+"] Team Creation (Action 2) executed");
								if(RegisterDeveloper.getDevID().equals(null)){
									TeamCreation.display();
									JOptionPane.showConfirmDialog(null, "You need to Register first!", "Teamwork", JOptionPane.PLAIN_MESSAGE);
								}
								else{
									TeamCreation.display();
								}
							}
						};
						action2.setText("Create Team");
						action2.setToolTipText("Team Creation");
						action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
								getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
				        break;
				    case "Upload_Artefact(s)":
				    	action3 = new Action() {
							public void run() {
								//showMessage("["+value+"] Artefact Upload (Action 3) executed");
								if(RegisterDeveloper.getDevID().equals(null)){
									ArtefactsUpload.artUpload(); 
									JOptionPane.showConfirmDialog(null, "You need to Register first!", "Teamwork", JOptionPane.PLAIN_MESSAGE);
								}
								else{
								ArtefactsUpload.artUpload(); 
								}
							}
						};
						action3.setText("Upload Artefact(s)");
						action3.setToolTipText("Registration");
						action3.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
							getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
				        break;
				    case "Download_Artefact(s)":
				    	action4 = new Action() {
							public void run() {
								//showMessage("["+value+"] Artefact Download (Action 4) executed");
								if(RegisterDeveloper.getDevID().equals(null)){
									ArtefactsDownload.display();
									JOptionPane.showConfirmDialog(null, "You need to Register first!", "Teamwork", JOptionPane.PLAIN_MESSAGE);
								}
								else{
								ArtefactsDownload.display();
								}
							}
						};
						action4.setText("Download Artefact(s)");
						action4.setToolTipText("Download Artefact(s)");
						action4.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
								getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
				        break;
				    // etc...
				}
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	@SuppressWarnings("unused")
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"Reactive Achitecture Client Operations",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}

package com.accenture.assets.tree;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreeUtilities {

	public static DefaultMutableTreeNode obtenerNodo(DefaultTreeModel tree, String target){
	
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)tree.getRoot();
	
		DefaultMutableTreeNode node = null;
	
		if (root != null)
		    for (Enumeration e = root.breadthFirstEnumeration(); e.hasMoreElements();)
		    {
		    	DefaultMutableTreeNode current = (DefaultMutableTreeNode)e.nextElement();
	
		        if (target.equals(current.getUserObject()))
		        {
		            node = current;
		            break;
		        }
		    }
	
		return node;
	}
}

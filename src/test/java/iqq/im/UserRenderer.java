package iqq.im;

import iqq.im.bean.QQBuddy;
import iqq.im.bean.QQGroup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

public class UserRenderer extends JPanel implements TreeCellRenderer, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label = new JLabel();
	private JLabel expandLabel = new JLabel();
	private TreePath oldTreePath;
	private boolean listenerAdded;
	private DefaultMutableTreeNode overNode;
	private int width = 400;

	public UserRenderer() {
		super();
		// setOpaque(false);
		expandLabel.setPreferredSize(new Dimension(20, 20));
		expandLabel.setVerticalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.red));
		setLayout(new BorderLayout(10, 0));
	}

	public UserRenderer(Icon open, Icon close, Icon leaf) {
		super();
		this.setLayout(new BorderLayout());
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		if (!listenerAdded && tree != null) {
			tree.addMouseMotionListener(this);
			listenerAdded = true;
		}
		if (value instanceof DefaultMutableTreeNode) {

//			width = tree.getWidth();
			if (leaf) {
				expandLabel.setIcon(null);
				setPreferredSize(new Dimension(width, 54));

			} else if (expanded) {
//				expandLabel.setIcon(openIcon);
				setPreferredSize(new Dimension(width , 30));
			} else {
//				expandLabel.setIcon(closeIcon);
				setPreferredSize(new Dimension(width, 30));
			}
			add(expandLabel, BorderLayout.WEST);

			DefaultMutableTreeNode dt = (DefaultMutableTreeNode) value;
			
			Object userObject = dt.getUserObject() ;
			if (overNode == dt&&!(userObject instanceof String)) {
				setBackground(new Color(250, 242, 198));
			}else{
				setBackground(Color.white);
			}
			if(userObject instanceof QQBuddy){
				QQBuddy buddy = (QQBuddy)userObject;
				label.setText(buddy.getNickname());
			}else if(userObject instanceof QQGroup){
				QQGroup group = (QQGroup)userObject;
				label.setText(group.getName()+"("+group.getGin()+")");
			}else{
				label.setText(userObject+"");
			}
			
			// label.setPreferredSize(new Dimension(100, 140));
			add(label, BorderLayout.CENTER);

		}
		return this;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		JTree tree = (JTree) e.getSource();
		TreePath overPath = tree.getPathForLocation(e.getX(), e.getY());
		//第一次进去
		if (overPath != null && oldTreePath == null) {
			overNode = (DefaultMutableTreeNode) overPath.getLastPathComponent();
			Rectangle overBounds = tree.getPathBounds(overPath);
			tree.repaint(overBounds);
			oldTreePath = overPath;
		}
		//移动
		if (overPath != null && oldTreePath != null) {
			overNode = (DefaultMutableTreeNode) overPath.getLastPathComponent();
			DefaultMutableTreeNode oldNode = (DefaultMutableTreeNode) oldTreePath.getLastPathComponent();
			if (oldNode != overNode) {
				Rectangle overBounds = tree.getPathBounds(overPath);
				tree.repaint(overBounds);

				Rectangle oldBounds = tree.getPathBounds(oldTreePath);
				tree.repaint(oldBounds);
				oldTreePath = overPath;
			}
			;
		}

		//移出
		if (overPath == null && oldTreePath != null) {
			overNode = null;
			
			Rectangle oldBounds = tree.getPathBounds(oldTreePath);
			tree.repaint(oldBounds);
			oldTreePath = null;
		}
	}
}
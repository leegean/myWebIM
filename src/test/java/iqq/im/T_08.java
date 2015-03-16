package iqq.im;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JTree.DynamicUtilTreeNode;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.PanelUI;
import javax.swing.plaf.TreeUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class T_08 extends JFrame {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// Set System L&F
//		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		T_08 win = new T_08();

		win.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

			}
		});
		win.setSize(600, 400);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setVisible(true);

	}

	private JTree tree;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode rootNode;
	protected DynamicUtilTreeNode node;
	private DefaultTreeCellRenderer renderer;
	private ImageIcon openIcon;
	private ImageIcon closeIcon;
	private BasicTreeUI treeUI;

	public T_08() {
		try {
			openIcon = new ImageIcon(ImageIO.read(new File("a.png")));
			closeIcon = new ImageIcon(ImageIO.read(new File("b.png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		setLayout(new FlowLayout());

		tree = new JTree();
		treeUI = ((BasicTreeUI) tree.getUI());
		treeUI.setLeftChildIndent(0);
		treeUI.setRightChildIndent(0);
		tree.setRootVisible(false);
		tree.setOpaque(false);
		tree.setCellRenderer(new UserRenderer());
		tree.putClientProperty("JTree.lineStyle", "None");
		tree.setExpandsSelectedPaths(true);
		tree.setToggleClickCount(1);

		rootNode = new DefaultMutableTreeNode("root");
		treeModel = new DefaultTreeModel(rootNode);
		tree.setModel(treeModel);

		JScrollPane jsp = new JScrollPane(tree);
		jsp.setOpaque(false);
		jsp.setBorder(null);
		jsp.setPreferredSize(new Dimension(300, 400));
		MyPanelUI pui = new MyPanelUI();
		JPanel treePanel = new JPanel();
		treePanel.setUI(pui);
		treePanel.add(jsp);
		add(treePanel);
		add(new JButton(new AbstractAction("insert") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Hashtable<String, Integer[]> map = new Hashtable<String, Integer[]>();
				map.put("55", new Integer[] { 51, 52, 53 });
				String[] data = new String[] { "aa", "bb", "cc" };
				DynamicUtilTreeNode.createChildren(rootNode, map);

				treeModel.reload(rootNode);
				expandAllNode(tree, new TreePath(rootNode), true);
			}
		}));

	}

	private void expandAllNode(JTree tree, TreePath parent, boolean expand) {
		// Traverse children
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAllNode(tree, path, expand);
			}
		}

		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	}

	class UserRenderer extends JPanel implements TreeCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JLabel label = new JLabel();
		private JLabel expandLabel = new JLabel();

		public UserRenderer() {
			super();
			// setOpaque(false);
			setPreferredSize(new Dimension(200, 40));
			expandLabel.setPreferredSize(new Dimension(40, 40));
		}

		public UserRenderer(Icon open, Icon close, Icon leaf) {
			super();
			this.setLayout(new BorderLayout());
		}

		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			System.out.println(value.getClass());
			if (value instanceof DefaultMutableTreeNode) {

				if (leaf) {
					expandLabel.setIcon(null);
				} else if (expanded) {
					expandLabel.setIcon(openIcon);
				} else {
					expandLabel.setIcon(closeIcon);
				}
				add(expandLabel);
				DefaultMutableTreeNode dt = (DefaultMutableTreeNode) value;
				String oo = dt.getUserObject() + "";
				label.setForeground(Color.red);
				label.setText(oo);
				// label.setPreferredSize(new Dimension(100, 140));
				add(label);

			}
			return this;
		}
	}
}

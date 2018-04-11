import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import javax.swing.filechooser.FileNameExtensionFilter;

class CANCEL extends JPanel
{

}

class SETTING extends JPanel implements ActionListener
{
	JPanel jp2;
	JLabel jl1, jl2, jl3, jl4, jl5;
	JTextField jt1, jt3;
	JPasswordField jt2, jt4, jt5;
	JButton rep;
	Font f1 = new Font("Arial", Font.BOLD, 15);

	SETTING()
	{
		jp2 = new JPanel();
		setLayout(null);
		setFont(f1);

		jl1 = new JLabel("CURRENT USERNAME:");
		jl2 = new JLabel("CURRENT PASSWORD:");
		jl3 = new JLabel("NEW USERNAME:");
		jl4 = new JLabel("NEW PASSWORD:");
		jl5 = new JLabel("CONFIRM PASSWORD:");

		jt1 = new JTextField();
		jt2 = new JPasswordField();
		jt3 = new JTextField();
		jt4 = new JPasswordField();
		jt5 = new JPasswordField();
		jt2.setEchoChar('*');
		jt4.setEchoChar('*');
		jt5.setEchoChar('*');
		rep = new JButton("REPLACE");

		jl1.setBounds(50, 100, 150, 30);
		jl2.setBounds(50, 150, 150, 30);
		jl3.setBounds(50, 200, 150, 30);
		jl4.setBounds(50, 250, 150, 30);
		jl5.setBounds(50, 300, 150, 30);

		jt1.setBounds(200, 100, 150, 30);
		jt2.setBounds(200, 150, 150, 30);
		jt3.setBounds(200, 200, 150, 30);
		jt4.setBounds(200, 250, 150, 30);
		jt5.setBounds(200, 300, 150, 30);

		rep.setBounds(400, 350, 100, 30);

		jl1.setForeground(Color.WHITE);
		jl2.setForeground(Color.WHITE);
		jl3.setForeground(Color.WHITE);
		jl4.setForeground(Color.WHITE);
		jl5.setForeground(Color.WHITE);
		add(jl1);
		add(jl2);
		add(jl3);
		add(jl4);
		add(jl5);

		add(jt1);
		add(jt2);
		add(jt3);
		add(jt4);
		add(jt5);
		add(rep);
		rep.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String a, c, usrn = "", p = "", curpsw = "", newpsw = "", conpsw = "";
		int flag = 0;

		a = jt1.getText();
		c = jt3.getText();
		char[] b = jt2.getPassword();
		char[] d = jt4.getPassword();
		char[] f = jt5.getPassword();

		for (int z = 0; z < b.length; z++)
		{
			curpsw = curpsw + b[z];
		}
		System.out.print(b);
		for (int z = 0; z < d.length; z++)
		{
			newpsw = newpsw + d[z];
		}
		for (int z = 0; z < f.length; z++)
		{
			conpsw = conpsw + f[z];
		}
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			ResultSet r1 = st.executeQuery("select uname,psw from LOGIN where uname='" + a + "'");
			while (r1.next())
			{
				usrn = r1.getString("una");
				p = r1.getString("p");
			}
		}

		catch (Exception ee)
		{
			ee.printStackTrace();
		}
		if (a.equals("") || curpsw.equals("") || c.equals("") || newpsw.equals("") || conpsw.equals(""))
		{
			JOptionPane.showMessageDialog(jp1, "INSERT AS REQUIRE", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else if (a.equals(usrn) && curpsw.equals(p))
		{
			if (newpsw.equals(conpsw))
			{
				System.out.println("DDDDDD:::::");
				JOptionPane.showMessageDialog(jp2, "PASSWORD CHANGED", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
				flag = 1;
			}

			else
			{
				JOptionPane.showMessageDialog(jp1, "PASSWORD DON'T MATCH", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(jp3, "ENTER CORRECT USER AND PASSWORD", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		if (flag == 1)
		{
			try
			{
				Class.forName("oracle.jdbc.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
				Statement st = con.createStatement();
				ResultSet r1 = st.executeQuery("update LOGIN set name='" + c + "' ,psw='" + newpsw + "' where uno=1");
			}

			catch (Exception aa)
			{
				aa.printStackTrace();
			}
		}
		jt1.setText("");
		jt2.setText("");
		jt3.setText("");
		jt4.setText("");
		jt5.setText("");
	}

}

class CollectDaily extends JPanel implements ActionListener, ItemListener
{
	JLabel jl1, jl2, jl3, tit;
	JComboBox jb1, jb2;
	JTextField amt;
	JButton coll;
	Font f = new Font("Arial", Font.BOLD, 15);

	CollectDaily()
	{
		setLayout(null);
		tit = new JLabel(new ImageIcon("dac.jpg"));
		jl1 = new JLabel("SELECT MOVIE NAME:");
		jl2 = new JLabel("SELECT DATE:");
		jl3 = new JLabel("TOTAL AMOUNT:");
		jb1 = new JComboBox();
		jb2 = new JComboBox();
		amt = new JTextField();
		coll = new JButton("Collection");
		coll.setFont(new Font("Felix Titling", Font.BOLD, 20));
		coll.setActionCommand("COLLECTION");
		jl1.setFont(f);
		jl2.setFont(f);
		jb1.setFont(f);
		jb2.setFont(f);
		// coll.setFont(f);
		amt.setFont(f);
		jl3.setFont(f);

		tit.setBounds(0, 0, 845, 630);
		jl1.setBounds(50, 100, 200, 30);
		jl2.setBounds(500, 100, 150, 30);
		jb1.setBounds(50, 150, 200, 25);
		jb2.setBounds(500, 150, 150, 25);
		coll.setBounds(300, 250, 150, 30);
		amt.setBounds(400, 450, 150, 30);
		jl3.setBounds(400, 400, 150, 50);

		add(tit);
		tit.add(jl1);
		tit.add(jl2);
		tit.add(jl3);
		tit.add(jb1);
		tit.add(jb2);
		tit.add(amt);
		tit.add(coll);

		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select distinct(mname) from MOVIE");
			while (rs.next())
			{
				String a = rs.getString("MNAME");
				jb1.addItem(a);
			}
		}
		catch (Exception a)
		{
			a.printStackTrace();
		}

		coll.addActionListener(this);

		jb1.addItemListener(this);
	}

	public void getdate1()
	{
		jb2.removeAllItems();
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select distinct(to_char(mdate,'')) from show,movie where movie.mnumber=show.movienumber and moviename='" + jb1.getSelectedItem() + "'");
			while (rs.next())
			{
				String a = rs.getString("(TO_CHAR(MDATE,'DD-MON-YY'))");
				jb2.addItem(a);
			}
		}
		catch (Exception a)
		{
			a.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if (s.equals("COLLECTION"))
		{

			try
			{
				Class.forName("oracle.jdbc.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select sum(amt) from BOOKING where mnumber in(select mnumber from MOVIE where mname='" + jb1.getSelectedItem() + "' )and shonumber in(select shownumber from SHOW where mdate='" + jb2.getSelectedItem() + "')");
				while (rs.next())
				{
					String a = rs.getString("SUM(AMT)");
					amt.setText(a);
				}
			}
			catch (Exception a)
			{
				a.printStackTrace();
			}
		}
	}

	public void itemStateChanged(ItemEvent ie)
	{
		if (ie.getStateChange() == 1)
		{
			getdate1();
		}
	}
}

class CollectMonth extends JPanel implements ActionListener, ItemListener
{
	JLabel jl1, jl2, jl3, tit;
	JComboBox jb1, jb2;
	JTextField amt;
	JButton coll;
	Font f = new Font("Arial", Font.BOLD, 15);

	CollectMonth()
	{
		setLayout(null);
		tit = new JLabel(new ImageIcon("c:\\proj\\mac.jpg"));
		jl1 = new JLabel("SELECT MOVIE NAME:");
		jl2 = new JLabel("SELECT  MONTH:");
		jl3 = new JLabel("TOTAL AMOUNT:");
		jb1 = new JComboBox();
		jb2 = new JComboBox();
		amt = new JTextField();

		coll = new JButton("Collection");
		coll.setFont(new Font("Felix Titling", Font.BOLD, 20));
		coll.setActionCommand("COLLECTION");

		jl1.setFont(f);
		jl2.setFont(f);
		jb1.setFont(f);
		jb2.setFont(f);
		// coll.setFont(f);
		amt.setFont(f);
		jl3.setFont(f);

		tit.setBounds(0, 0, 845, 630);
		jl1.setBounds(50, 100, 200, 30);
		jl2.setBounds(500, 100, 150, 30);
		jb1.setBounds(50, 150, 200, 25);
		jb2.setBounds(500, 150, 150, 25);
		coll.setBounds(300, 250, 150, 30);
		amt.setBounds(400, 450, 150, 30);
		jl3.setBounds(400, 400, 150, 50);

		add(tit);
		tit.add(jl1);
		tit.add(jl2);
		tit.add(jl3);
		tit.add(jb1);
		tit.add(jb2);
		tit.add(amt);
		tit.add(coll);

		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select distinct(mname) from movie");
			while (rs.next())
			{
				String a = rs.getString("MNAME");
				jb1.addItem(a);
			}
		}
		catch (Exception a)
		{
			a.printStackTrace();
		}

		coll.addActionListener(this);

		jb1.addItemListener(this);
	}

	public void getmonth1()
	{
		jb2.removeAllItems();
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select distinct(to_char(movdate,'mon')) from show S,movie M where movie.mnumber=show.mvnumber and mname='" + jb1.getSelectedItem() + "'");
			while (rs.next())
			{
				String a = rs.getString("(TO_CHAR(MDATE,'MON'))");
				jb2.addItem(a);
			}
			ResultSet rs1 = st.executeQuery("select sum(amt) from BOOKING where to_char(upper(sdate)) like '" + jb2.getSelectedItem() + "'");
			// ResultSet rs1=st.executeQuery("select sum(amt) from BOOKING where mnumber in(select mnumber from MOVIE where mname='"+jb1.getSelectedItem()+"' )and shnumber in(select shnumber from SHOW where mdate like'"+jb2.getSelectedItem()+"')");
			while (rs1.next())
			{
				String b = rs.getString("SUM(AMT)");
				amt.setText(b);

			}

		}
		catch (Exception a)
		{
			// JPanel jp2=new JPanel();
			// JOptionPane.showMessageDialog(jp2,"There Is No Collection Available Try For Other","No Amount",JOptionPane.WARNING_MESSAGE);
			a.printStackTrace();
			System.out.print(a);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if (s.equals("COLLECTION"))
		{

			try
			{
				Class.forName("oracle.jdbc.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
				Statement st = con.createStatement();
				System.out.print(jb2.getSelectedItem());
				ResultSet rs = st.executeQuery("select sum(amt) from BOOKING where mnumber in(select mnumber from MOVIE where mname='" + jb1.getSelectedItem() + "') and shnumber in (select shnumber from SHOW where to_char(mdate,'mon')='" + jb2.getSelectedItem() + "')");
				while (rs.next())
				{
					String b = rs.getString("SUM(AMT)");
					amt.setText(b);

				}
			}
			catch (Exception a)
			{
				a.printStackTrace();
			}
		}
	}

	public void itemStateChanged(ItemEvent ie)
	{
		if (ie.getStateChange() == 1)
		{
			getmonth1();
		}
	}
}

class TABLE extends JPanel implements ActionListener
{
	JPanel jp2;
	JPanel jp;

	JScrollPane jsp;
	JButton de, re, sdate, sno;
	JLabel tit, mov1, dat1, tim1, srt;
	JTextField mov, dat, tim;
	JComboBox dad, dam, day, tihh, timm, tiss, tiap;
	Font f = new Font("Arial", Font.BOLD, 15);

	HOME h = new HOME();

	String head[] = {"MOVIE NO.", "MOVIE NAME", "SHOW NO.", "SHOW DATE", "PRICE FOR GOLD", "PRICE FOR SILVER"};
	String data[][] = new String[50][6];
	JTable table = new JTable(data, head);

	TABLE()
	{
		super();
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			setBorder(null);
		}
		catch (Exception eee)
		{
		}

		setLayout(null);
		// jp.setLayout(null);
		// System.out.print("ANISH");
		tableshow();
		// table=new JTable(data,head);
		tit = new JLabel(new ImageIcon("c:\\proj\\table.jpg"));
		tit.setBounds(0, 0, 845, 630);

		jsp = new JScrollPane(table);
		jp = new JPanel();
		table.setFont(f);
		table.setSelectionBackground(Color.GREEN);
		table.setBackground(Color.WHITE);
		// table.setBounds(100,100,600,300);
		table.setSize(500, 100);
		table.setRowHeight(25);
		table.setEnabled(false);
		table.setGridColor(Color.BLACK);
		table.setBackground(new Color(56, 21, 71));
		table.setForeground(Color.WHITE);

		// table.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
		re = new JButton(new ImageIcon("c:\\proj\\tref.jpg"));
		de = new JButton(new ImageIcon("c:\\proj\\tdel.jpg"));
		mov1 = new JLabel("ENTER SHOW NO");
		mov = new JTextField();
		srt = new JLabel("SORT BY :");
		sdate = new JButton(new ImageIcon("c:\\proj\\tdat.jpg"));
		sno = new JButton(new ImageIcon("c:\\proj\\tsho.jpg"));

		re.setActionCommand("REFRESH");
		de.setActionCommand("DELETE");
		sdate.setActionCommand("DATE");
		sno.setActionCommand("SHOW NO");
		de.setBounds(500, 300, 120, 30);
		re.setBounds(500, 450, 120, 30);
		srt.setBounds(100, 400, 120, 30);
		mov1.setBounds(50, 300, 200, 30);
		mov.setBounds(220, 300, 80, 30);
		sdate.setBounds(200, 400, 120, 30);
		sno.setBounds(200, 450, 120, 30);

		re.setFont(f);
		de.setFont(f);
		srt.setFont(f);
		mov1.setFont(f);
		mov.setFont(f);
		sdate.setFont(f);
		sno.setFont(f);

		add(tit);
		// jp.add(table);
		tit.add(re);
		tit.add(de);
		tit.add(srt);
		tit.add(mov1);
		tit.add(mov);
		tit.add(sdate);
		tit.add(sno);
		// h.jtp.add(jp);

		de.addActionListener(this);
		re.addActionListener(this);
		sdate.addActionListener(this);
		sno.addActionListener(this);

		tit.add(jsp);
		jsp.setBounds(23, 70, 800, 225);

		// add(table);
	}

	void tableshow()
	{
		try
		{
			table.setVisible(true);
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			ResultSet rs2 = st.executeQuery("select movie.mnumber,mname,show.shnumber,to_char(mtime,'dd-mon-yy hh:mi:ss a.m.'),pgold,psilver from MOVIE,SHOW,PRICE where MOVIE.mnumber=SHOW.mnumber and movie.mnumber=price.mnumber and show.shnumber=price.shnumber");
			// String head[]={"MOVIE NO.","MOVIE NAME","SHOW NO.","SHOW DATE","PRICE FOR GOLD","PRICE FOR SILVER"};
			// String data[][]=new String[50][6];
			// table=new JTable(data,head);
			int i = 0;
			while (rs2.next())
			{
				data[i][0] = rs2.getString("movnumber");
				data[i][1] = rs2.getString("movname");
				data[i][2] = rs2.getString("shnumber");
				data[i][3] = rs2.getString("to_char(MTIME,'dd-mon-yyhh:mi:ss')");
				// data[i][3]=rs2.getString("STIME");
				data[i][4] = rs2.getString("PRICEGOLD");
				data[i][5] = rs2.getString("PRICESILVER");
				// data[i][6]=rs2.getString("IMG");
				i++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.print(e);
			// String a=e.toString();
			// String aa=a.substring(33);
			// JOptionPane.showMessageDialog(jp2,aa,"",JOptionPane.WARNING_MESSAGE);
		}

	}

	void sortshow()
	{
		try
		{
			table.setVisible(true);
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			ResultSet rs2 = st.executeQuery("select movie.mnumber,mname,show.shnumber,to_char(mtime,'dd-mon-yy hh:mi:ss'),pgold,psilver from MOVIE,SHOW,PRICE where MOVIE.mnumber=SHOW.mnumber and movie.mnumber=price.mnumber and show.shnumber=price.shnumber order by shnumber");
			// String head[]={"MOVIE NO.","MOVIE NAME","SHOW NO.","SHOW DATE","PRICE FOR GOLD","PRICE FOR SILVER"};
			// String data[][]=new String[50][6];
			// table=new JTable(data,head);
			int i = 0;
			while (rs2.next())
			{
				data[i][0] = rs2.getString("mnumber");
				data[i][1] = rs2.getString("mname");
				data[i][2] = rs2.getString("shnumber");
				data[i][3] = rs2.getString("to_char(MTIME,'dd-mon-yyhh:mi:ss')");
				// data[i][3]=rs2.getString("STIME");
				data[i][4] = rs2.getString("PGOLD");
				data[i][5] = rs2.getString("PSILVER");
				// data[i][6]=rs2.getString("IMG");
				i++;
			}
		}
		catch (Exception e)
		{
			System.out.print(e);
			// String a=e.toString();
			// String aa=a.substring(33);
			// JOptionPane.showMessageDialog(jp2,aa,"",JOptionPane.WARNING_MESSAGE);
		}

	}

	void sortdate()
	{
		try
		{
			table.setVisible(true);
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			ResultSet rs2 = st.executeQuery("select movie.mnumber,mname,show.shnumber,to_char(mtime,'dd-mon-yy hh:mi:ss a.m.'),pgold,psilver from MOVIE,SHOW,PRICE where MOVIE.mnumber=SHOW.mnumber and movie.mnumber=price.mnumber and show.shnumber=price.shnumber order by mtime");
			// String head[]={"MOVIE NO.","MOVIE NAME","SHOW NO.","SHOW DATE","PRICE FOR GOLD","PRICE FOR SILVER"};
			// String data[][]=new String[50][6];
			// table=new JTable(data,head);
			int i = 0;
			while (rs2.next())
			{
				data[i][0] = rs2.getString("mnumber");
				data[i][1] = rs2.getString("mname");
				data[i][2] = rs2.getString("shnumber");
				data[i][3] = rs2.getString("to_char(MTIME,'dd-mon-yyhh:mi:ssa.m.')");
				// data[i][3]=rs2.getString("STIME");
				data[i][4] = rs2.getString("PGOLD");
				data[i][5] = rs2.getString("PSILVER");
				// data[i][6]=rs2.getString("IMG");
				i++;
			}
		}
		catch (Exception e)
		{
			System.out.print(e);
			// String a=e.toString();
			// String aa=a.substring(33);
			// JOptionPane.showMessageDialog(jp2,aa,"",JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		String s = ae.getActionCommand();
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			if (s.equals("DELETE"))
			{
				ResultSet rs2 = st.executeQuery("delete from SHOW where shnumber=" + mov.getText());
				mov.setText("");
				System.out.println("DELETE");
				table.setVisible(false);
				tableshow();
			}
			else if (s.equals("DATE"))
			{

				System.out.println("DATE");
				table.setVisible(false);
				sortdate();

			}
			else if (s.equals("SHOW NO"))
			{
				table.setVisible(false);
				System.out.println("SHOW NO");
				sortshow();
				// table.setVisible(true);
			}
			else if (s.equals("REFRESH"))
			{
				ResultSet rs2 = st.executeQuery("select movie.mnumber,mname,show.shnumber,to_char(mtime,'dd-mon-yy hh:mi:ss a.m.'),pgold,psilver from MOVIE,SHOW,PRICE where MOVIE.mnumber=SHOW.mnumber and movie.mnumber=price.mnumber and show.shnumber=price.shnumber");
				System.out.println("REFRESH");
				table.setVisible(false);
				tableshow();
			}

		}
		catch (Exception e)
		{
			String msg = e.toString();
			String msg1 = msg.substring(23, 32);
			System.out.print(msg1);
			if (msg1.equals("ORA-02292"))
			{
				JOptionPane.showMessageDialog(jp2, "You Can Delete Only Empty Show", "DELETION ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(jp2, "CONNECTIN FAIL,Please Press OK or [X] For Exit The System", "CONNECTION FAIL", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			System.out.println(e);
		}
	}
}

class MANAGEMOVIE extends JPanel implements ActionListener
{
	public String fld = "", fold;
	JFileChooser fd;
	JPanel jp1;
	JLabel mon, mona, moi, tg, ts, title;
	JTextField mon1, mona1, moi1, tg1, ts1;
	JButton se, br;
	Font f1 = new Font("Arial", Font.BOLD, 15);
	Font f2 = new Font("Arial", Font.BOLD, 25);

	MANAGEMOVIE()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception eee)
		{
		}

		setLayout(null);
		title = new JLabel(new ImageIcon("c:\\proj\\mamov.jpg"));
		title.setBounds(0, 0, 845, 630);
		mon = new JLabel("MOVIE NUMBER :");
		mona = new JLabel("MOVIE NAME :");
		moi = new JLabel("SELECT MOVIE IMAGE :");
		tg = new JLabel("TOTAL GOLD SEAT");
		ts = new JLabel("TOTAL SILVER SEAT");
		mon1 = new JTextField();
		mona1 = new JTextField();
		moi1 = new JTextField("SELECT IMAGE FROM BROWSE..");
		tg1 = new JTextField("42");
		ts1 = new JTextField("98");
		br = new JButton("Browse..");
		se = new JButton("SET MOVIE");

		mon.setBounds(50, 100, 150, 30);
		mona.setBounds(50, 150, 150, 30);
		moi.setBounds(50, 200, 300, 30);
		mon1.setBounds(250, 100, 100, 30);
		mona1.setBounds(250, 150, 500, 30);
		moi1.setBounds(250, 200, 500, 30);

		tg.setBounds(50, 300, 150, 30);
		tg1.setBounds(250, 300, 100, 30);
		ts.setBounds(50, 350, 150, 30);
		ts1.setBounds(250, 350, 100, 30);

		br.setBounds(250, 250, 175, 30);
		se.setBounds(250, 400, 175, 30);

		mon.setFont(f1);
		mona.setFont(f1);
		moi.setFont(f1);
		tg.setFont(f1);
		ts.setFont(f1);
		mon1.setFont(f1);
		mona1.setFont(f1);
		moi1.setFont(f1);
		tg1.setFont(f1);
		ts1.setFont(f1);
		br.setFont(f1);
		se.setFont(f1);
		moi1.setDisabledTextColor(Color.BLACK);
		moi1.setEnabled(false);
		tg1.setDisabledTextColor(Color.BLACK);
		tg1.setEnabled(false);
		ts1.setDisabledTextColor(Color.BLACK);
		ts1.setEnabled(false);
		add(title);

		title.add(mon);
		title.add(mona);
		title.add(moi);
		title.add(tg);
		title.add(ts);
		title.add(mon1);
		title.add(mona1);
		title.add(moi1);
		title.add(tg1);
		title.add(ts1);
		title.add(br);
		title.add(se);

		br.addActionListener(this);
		se.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{

		String s = ae.getActionCommand();

		String mon2 = mon1.getText();
		String mona2 = mona1.getText();
		String moi2 = moi1.getText();
		String tg2 = tg1.getText();
		String ts2 = ts1.getText();

		if (s.equals("SET MOVIE"))
		{
			if (mon2.equals("") || mona2.equals("") || moi2.equals("") || tg2.equals("") || ts2.equals(""))
			{
				JOptionPane.showMessageDialog(jp1, "please insert all values as per requirement", "", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				try
				{
					Class.forName("oracle.jdbc.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select count(*) from tab where TNAME='MOVIE'");
					rs.next();
					int g = rs.getInt("count(*)");
					if (g == 0)
					{
						st.execute("create table MOVIE(mnumber number(8) constraint pk_mnu primary key,mname varchar2(30) constraint nn_mna not null,img varchar2(200),tgold number(3) constraint nn_tg not null,tsilver number(3) constraint nn_ts not null)");
					}
					st.executeUpdate("insert into MOVIE values(" + mon1.getText() + ",'" + mona1.getText() + "','" + moi1.getText() + "'," + tg1.getText() + "," + ts1.getText() + ")");
					mon1.setText("");
					mona1.setText("");
					moi1.setText("");
				}
				catch (Exception e)
				{
					String a = e.toString();
					String aa = a.substring(31);
					JOptionPane.showMessageDialog(jp1, aa, "", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		else if (s.equals("Browse.."))
		{
			try
			{
				// HOME h=new HOME("ANISH");
				fd = new JFileChooser();
				FileNameExtensionFilter fn = new FileNameExtensionFilter("ONLY IMAGE FILE", "jpg", "gif");
				fd.setFileFilter(fn);
				int as = fd.showOpenDialog(this);
				if (as == JFileChooser.APPROVE_OPTION)
				{
					fold = "";
					fold = fd.getCurrentDirectory().toString();
					StringTokenizer st = new StringTokenizer(fold, "\\");
					while (st.hasMoreTokens())
					{
						String f = st.nextToken();
						fld = fld + f + "\\\\";
					}
					String fi = fd.getSelectedFile().getName();
					fold = fld + fi;
					fld = "";
					moi1.setText(fold);
					moi1.setDisabledTextColor(Color.BLACK);
					moi1.setEnabled(false);
				}
				if (as == JFileChooser.CANCEL_OPTION)
				{
					fold = "";
				}
			}
			catch (Exception e)
			{
				System.out.print(e);
			}

		}
	}
}

class MANAGESHOW extends JPanel implements ActionListener
{
	JPanel jp1;
	JLabel tit, shn, da, ti, mon, go, si, mov;
	JTextField shn1, da1, ti1, mon1, go1, si1, mv;
	JButton se, chk;
	Font f1 = new Font("Arial", Font.BOLD, 15);
	Font f2 = new Font("Arial", Font.BOLD, 25);

	MANAGESHOW()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception eee)
		{
		}
		setLayout(null);

		tit = new JLabel(new ImageIcon("C:\\proj\\masho.jpg"));
		shn = new JLabel("SHOW NUMBER");
		da = new JLabel("ENTER DATE [ e.g dd-mon-yy ]");
		ti = new JLabel("TIME [hh.mi:ss a.m./p.m.]");
		mon = new JLabel("ENTER MOVIE NUMBER");
		go = new JLabel("ENTER PRICE FOR GOLD");
		si = new JLabel("ENTER PRICE FOR SILVER");
		mov = new JLabel("YOUR MOVIE");
		chk = new JButton("CHECK");

		tit.setFont(f2);
		shn.setFont(f1);
		da.setFont(f1);
		ti.setFont(f1);
		mon.setFont(f1);
		go.setFont(f1);
		si.setFont(f1);
		chk.setFont(f1);
		mov.setFont(f1);

		tit.setBounds(0, 0, 845, 630);
		shn.setBounds(20, 100, 250, 30);
		da.setBounds(20, 150, 250, 30);
		ti.setBounds(20, 200, 200, 30);
		mon.setBounds(20, 250, 200, 30);
		go.setBounds(20, 300, 300, 30);
		si.setBounds(20, 350, 300, 30);
		chk.setBounds(320, 250, 120, 30);
		// mov.setBounds(400,300,100,30);

		shn1 = new JTextField();
		da1 = new JTextField();
		ti1 = new JTextField();
		mon1 = new JTextField();
		go1 = new JTextField();
		si1 = new JTextField();
		mv = new JTextField();

		shn1.setFont(f1);
		da1.setFont(f1);
		ti1.setFont(f1);
		mon1.setFont(f1);
		go1.setFont(f1);
		si1.setFont(f1);
		shn1.setDisabledTextColor(Color.BLACK);
		shn1.setEnabled(false);

		shn1.setBounds(250, 100, 100, 30);
		da1.setBounds(250, 150, 100, 30);
		ti1.setBounds(250, 200, 100, 30);
		mon1.setBounds(250, 250, 50, 30);
		go1.setBounds(250, 300, 50, 30);
		si1.setBounds(250, 350, 50, 30);
		mv.setBounds(460, 250, 350, 30);
		se = new JButton("SET SHOW");
		se.setFont(f1);
		se.setBounds(400, 450, 150, 30);
		// LABLE
		add(tit);
		tit.add(shn);
		tit.add(da);
		tit.add(ti);
		tit.add(mon);
		tit.add(go);
		tit.add(si);
		// add(mov);
		tit.add(mv);
		// TEXT BOX
		tit.add(shn1);
		tit.add(da1);
		tit.add(ti1);
		tit.add(mon1);
		tit.add(go1);
		tit.add(si1);
		// BUTTON
		tit.add(se);
		tit.add(chk);

		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select count(*) from tab where TNAME='SHOW'");
			rs.next();
			int g = rs.getInt("count(*)");
			if (g == 0)
			{
				st.execute("create table SHOW(shnumber number(8) constraint pk_shn primary key,mnumber number(8),mdate date,mtime date,constraint fk_mnu foreign key(mnumber) references MOVIE(mnumber) on delete cascade)");
				shn1.setText("1");
			}
			else
			{
				ResultSet rs1 = st.executeQuery("select nvl(max(shnumber),0)+1 from SHOW");
				rs1.next();
				String aaa = rs1.getString("NVL(MAX(SHNUMBER),0)+1");
				System.out.print(aaa);

				shn1.setText(aaa);
			}
		}
		catch (Exception ee)
		{
		}

		se.addActionListener(this);
		chk.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		String s = ae.getActionCommand();
		// String shn2=shn1.getText();
		String da2 = da1.getText();
		String ti2 = ti1.getText();
		String mon2 = mon1.getText();
		String go2 = go1.getText();
		String si2 = si1.getText();

		if (s.equals("SET SHOW"))
		{
			if (shn1.getText().equals("") || da2.equals("") || ti2.equals("") || mon2.equals("") || go2.equals("") || si2.equals(""))
			{
				JOptionPane.showMessageDialog(jp1, "please insert all values as per requirement", "", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				try
				{
					Class.forName("oracle.jdbc.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select count(*) from tab where TNAME='SHOW'");
					rs.next();
					int g = rs.getInt("count(*)");
					if (g == 0)
					{
						st.execute("create table SHOW(shnumber number(8) constraint pk_shn primary key,mnumber number(8),mdate date,mtime date,constraint fk_mnu foreign key(mnumber) references MOVIE(mnumber) on delete cascade)");
						shn1.setText("1");
					}
					st.executeUpdate("insert into SHOW(shnumber,mnumber,mdate,mtime) values (" + shn1.getText() + "," + mon1.getText() + ",'" + da1.getText() + "',to_date('" + da1.getText() + " " + ti1.getText() + "','dd-mon-yy hh:mi:ss a.m.'))");
					st.executeUpdate("insert into PRICE values(" + mon1.getText() + "," + shn1.getText() + "," + go1.getText() + "," + si1.getText() + ")");
					ResultSet rs1 = st.executeQuery("select max(shnumber)+1 from SHOW");
					rs1.next();
					String aaa = rs1.getString("MAX(SHNUMBER)+1");
					System.out.print(aaa);
					shn1.setText(aaa);
					da1.setText("");
					ti1.setText("");
					mon1.setText("");
					go1.setText("");
					si1.setText("");
				}
				catch (Exception e)
				{
					String a = e.toString();
					System.out.print(a);
					String aa = a.substring(31);
					JOptionPane.showMessageDialog(jp1, aa, "", JOptionPane.WARNING_MESSAGE);

				}
			}
		}
		else if (s.equals(("CHECK")))
		{
			try
			{
				Class.forName("oracle.jdbc.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select distinct(MNAME) from MOVIE where MNUMBER=" + mon2 + "");
				while (rs.next())
				{
					String a = rs.getString("MNAME");
					mv.setText(a);
					mv.setFont(f1);
					mv.setDisabledTextColor(Color.BLACK);
					mv.setEnabled(false);
				}
			}

			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}

class SEAT extends JPanel implements ActionListener
{
	JPanel jp1;
	HOME hhh;
	JLabel jtf, seat;
	JButton yes, can1, app, conf;
	JLabel[] jl = new JLabel[10];
	JToggleButton[][] jb = new JToggleButton[10][14];
	JDialog jld;
	int i, j, count, amt, se7, no, shn111, mn111;;
	Font ff1 = new Font("Arial", Font.BOLD, 15);
	Font ff2 = new Font("Arial", Font.BOLD, 25);
	Font ff3 = new Font("Arial", Font.BOLD, 15);
	JLabel sel, mo, da, ti, se, cl, tot, title, seat1, bid;
	String a, mo7, da7, cl7, ti7, shn11, mn11;
	JTabbedPane jtp;

	SEAT()
	{
		super();
		try
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch (Exception eee)
		{
		}

		mo7 = Project.mo6;
		da7 = Project.da6;
		ti7 = Project.ti6;
		cl7 = Project.cl6;
		se7 = Integer.parseInt(Project.se6);

		setLayout(null);
		// hhh=h;
		yes = new JButton(new ImageIcon("c:\\proj\\yes.jpg"));
		yes.setActionCommand("YES,I'VE SELECT MY SEAT");

		title = new JLabel(new ImageIcon("C:\\proj\\seat2.jpg"));
		title.setBounds(0, 0, 845, 630);
		add(title);
		yes.setBounds(140, 510, 300, 30);
		yes.addActionListener(this);

		int k = 30, m = 50;
		char n = 65;
		for (i = 0; i < 10; i++)
		{
			jl[i] = new JLabel("" + n + "");
			for (j = 0; j < 14; j++)
			{

				jb[i][j] = new JToggleButton("" + n + "" + (j + 1));
				Font f = new Font("Arial", Font.BOLD, 8);
				jb[i][j].setFont(f);
				// jb[i][j].setBorder(null);

				jb[i][j].setBounds(k, m, 50, 25);
				if (j == 3 || j == 9)
				{
					k = k + 40;
				}
				k = k + 50;

				title.add(jb[i][j]);
				jb[i][j].addActionListener(this);

			}

			jl[i].setBounds(10, m, 50, 30);
			title.add(jl[i]);
			m = m + 45;
			n++;
			k = 30;
		}

		StringTokenizer data1 = new StringTokenizer(Project.data);
		String mm = "";

		System.out.println("DHAVAL::::");
		while (data1.hasMoreTokens())
		{
			mm = data1.nextToken();
			for (i = 0; i < 10; i++)
			{
				for (j = 0; j < 14; j++)
				{
					if (jb[i][j].getText().equals(mm))
					{
						jb[i][j].setBackground(Color.red);
						jb[i][j].setEnabled(false);
					}
				}
			}

		}
		String cl66 = cl7.substring(0, 1);
		StringTokenizer b = new StringTokenizer(cl7, "()");
		String e = "";

		while (b.hasMoreTokens())
		{
			e = b.nextToken();
		}
		System.out.println(e);
		int a = Integer.parseInt(e);
		amt = a * se7;
		System.out.println(" TOTAL AMOUNT:" + amt);
		if (cl66.equals("S"))
		{
			for (i = 7; i < 10; i++)
			{
				for (j = 0; j < 14; j++)
				{
					jb[i][j].setEnabled(false);
					jb[i][j].setBackground(Color.gray);
				}
			}
		}
		else
		{
			for (i = 0; i < 7; i++)
			{
				for (j = 0; j < 14; j++)
				{
					jb[i][j].setEnabled(false);
					jb[i][j].setBackground(Color.gray);
				}
			}
		}
		title.add(yes);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{

		String s = e.getActionCommand();

		System.out.print(s);

		try
		{
			for (i = 0; i < 10; i++)
			{
				for (j = 0; j < 10; j++)
				{
					if (jb[i][j].isSelected())
					{
						jb[i][j].setForeground(Color.green);
					}
					else
					{
						jb[i][j].setForeground(null);
					}
				}
			}
		}
		catch (Exception ee)
		{
		}

		a = "";
		if (s.equals("YES,I'VE SELECT MY SEAT"))
		{
			count = 0;
			int se77 = Integer.parseInt(Project.se6);
			for (i = 0; i < 10; i++)
			{
				for (j = 0; j < 14; j++)
				{
					if (jb[i][j].getForeground().equals(Color.green))
					{
						a = a + jb[i][j].getText() + " ";
						count++;
						System.out.print(count);
						System.out.println(a);
					}
				}
			}
			if (count != se77)
			{
				String asd = "  " + count + " Seat Is Selected " + " Please Select " + se77 + " Seat ";
				JOptionPane.showMessageDialog(jp1, asd, "", JOptionPane.ERROR_MESSAGE);
			}
			try
			{
				Class.forName("oracle.jdbc.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
				Statement st = con.createStatement();
				ResultSet rs1 = st.executeQuery("select * from BOOKING");
				rs1.next();
				int g = rs1.getInt("COUNT(*)");
				if (g == 0)
				{
					no = 1;
				}
				else
				{
					ResultSet rs = st.executeQuery("select max(bid)+1 from BOOKING");
					rs.next();
					String a = rs.getString("MAX(BID)+1");
					no = Integer.parseInt(a);
					System.out.println("" + no);
				}
			}
			catch (Exception exc)
			{
				exc.printStackTrace();
			}
			if (count == se77)
			{
				jld = new JDialog();
				jld.setVisible(true);
				jld.setBounds(0, 0, 845, 630);
				jld.setLayout(null);
				jld.setBackground(Color.white);
				sel = new JLabel(new ImageIcon("C:\\proj\\jld.jpg"));
				mo = new JLabel("YOUR MOVIE           :   " + mo7);
				da = new JLabel("MOVIE'S DATE         :   " + da7);
				ti = new JLabel("TIME'S OF MOVIE      :   " + ti7);
				se = new JLabel("SELECTED SEAT       :    ");
				cl = new JLabel("CLASS OF SEAT        :   " + cl7);
				bid = new JLabel("BOOKING ID::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
				tot = new JLabel("TOTAL AMOUNT        :" + amt);
				tot.setFont(ff1);
				tot.setBounds(50, 350, 400, 50);
				// tot=new JLabel("TOTAL AMOUNT :");
				// app=new JButton("SELECTION APPROVEL & PRINT TICKET");
				conf = new JButton(new ImageIcon("c:\\proj\\conf.jpg"));
				conf.setActionCommand("CONFIRM");
				sel.setFont(ff2);
				sel.setBounds(0, 0, 845, 630);
				mo.setFont(ff1);
				da.setFont(ff1);
				ti.setFont(ff1);
				cl.setFont(ff1);
				se.setFont(ff1);
				bid.setFont(ff1);
				// tot.setFont(ff1);
				// app.setFont(ff1);
				mo.setBounds(50, 100, 400, 50);
				da.setBounds(50, 150, 400, 50);
				ti.setBounds(50, 200, 400, 50);
				cl.setBounds(50, 250, 400, 50);
				se.setBounds(50, 300, 200, 50);
				bid.setBounds(50, 500, 100, 50);
				// tot.setBounds(50,350,400,50);

				// app.setBounds(150,450,500,50);

				yes.setFont(ff1);

				conf.setBounds(150, 500, 175, 30);
				can1 = new JButton(new ImageIcon("c:\\proj\\back.jpg"));
				can1.setActionCommand("BACK");
				jld.add(can1);
				can1.setBounds(400, 500, 175, 30);
				conf.addActionListener(this);
				can1.addActionListener(this);
				jtf = new JLabel();
				jld.add(jtf);
				jtf.setText(a);
				jtf.setBounds(230, 300, 150, 40);
				try
				{
					mo7 = Project.mo6;
					da7 = Project.da6;
					ti7 = Project.ti6;
					cl7 = Project.cl6;
					se7 = Integer.parseInt(Project.se6);
				}
				catch (Exception ee)
				{
					ee.printStackTrace();
				}

				jld.add(mo);
				jld.add(da);
				jld.add(cl);
				jld.add(ti);
				jld.add(se);
				jld.add(tot);
				jld.add(conf);
				// jld.add(app);
				jld.add(bid);
				jld.add(sel);
			}
		}

		if (s.equals("CONFIRM"))
		{
			System.out.println("Confirm");

			try
			{
				System.out.println("EFSEFS");
				Class.forName("oracle.jdbc.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
				Statement st = con.createStatement();
				ResultSet rs0 = st.executeQuery("select shnumber from SHOW where to_char(mtime,'dd-mon-yyhh:mi:ss a.m.')='" + Project.da6 + "" + Project.ti6 + "'");
				rs0.next();
				shn11 = rs0.getString("SHNUMBER");
				System.out.println("SHN:" + shn11);
				shn111 = Integer.parseInt(shn11);
				System.out.println("SHN:" + shn111);

				ResultSet rs1 = st.executeQuery("select mnumber from SHOW where to_char(movtime,'dd-mon-yyhh:mi:ss')='" + Project.da6 + "" + Project.ti6 + "'");
				rs1.next();

				mn11 = rs1.getString("MNUMBER");
				System.out.println("SHN:" + mn11);
				mn111 = Integer.parseInt(mn11);
				System.out.println("MN:" + mn111);

				System.out.println(":" + no + ":" + shn111 + ":" + mn111 + ":" + cl7 + ":" + se7 + ":" + amt);
				st.executeUpdate("insert into BOOKING values(" + no + "," + shn111 + "," + mn111 + ",'" + cl7 + "'," + se7 + "," + amt + ")");
				System.out.println("AB");

				String cl66 = cl7.substring(0, 1);
				if (cl66.equals("G"))
				{
					ResultSet rs3 = st.executeQuery("select noval(ASG,0) from SHOW where shnumber=" + shn111 + "and mnumber=" + mn111 + " ");
					rs3.next();
					String asg1 = rs3.getString("NVL(ASG,0)");
					int asg11 = Integer.parseInt(asg1);
					int asg01 = se7 + asg11;
					st.executeUpdate("update show set BDD=" + asg01 + "where shnumber=" + shn111 + "and mnumber=" + mn111 + " ");
					System.out.println(asg01);
					// st.executeUpdate("");
				}
				else if (cl66.equals("S"))
				{
					ResultSet rs4 = st.executeQuery("select noval(ASS,0) from SHOW where shnumber=" + 123 + "and mnumber=" + mn111 + " ");
					rs4.next();
					String ass1 = rs4.getString("NVL(ASS,0)");
					int ass11 = Integer.parseInt(ass1);
					int ass01 = se7 + ass11;
					st.executeUpdate("update show set BSS=" + ass01 + "where shnumber=" + 456 + "and mnumber=" + 777 + " ");
					System.out.println(ass01);
					// st.executeUpdate("");
				}

				ResultSet rs2 = st.executeQuery("insert into SEATNAME values (" + no + ",'" + jtf.getText() + "')");
				jld.setVisible(false);
			}
			catch (Exception a)
			{
				a.printStackTrace();
			}
		}
		else if (s.equals("BACK"))
			jld.setVisible(false);
	}

}

class HOME extends JFrame implements ActionListener, Runnable
{
	// JFrame jf;
	JPanel jp1, jp2, jp4, jp5, jp6, jp;
	// JLable sh;
	JLabel mn1, sd1, st1, nos1, cla1, date, time;
	JLabel title, mo1, da1, ti1, cl1, se1, im1, ys, mn, sd, st, nos, cla, jp4t, homepage;
	JComboBox mo, da, ti, cl, se;
	JTabbedPane jtp;
	JButton ok, lo, logo, ma, sh, vi, hm, daly, moly, can, sett, ext;
	Container con1;
	JTable jt;
	JScrollPane jsp;
	JRadioButton go, si;
	ButtonGroup bg;
	String ti33;
	String[] moviename;
	int i = 0, yy;
	String d, t, dd, mm, hr, mni, sc;
	Thread ta;
	Calendar cal;
	Font date1 = new Font("Segoe Script", Font.BOLD, 20);

	HOME()
	{
	}

	HOME(String s)
	{
		super(s);
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception eee)
		{
		}
		setShape(null);
		setLayout(null);
		con1 = getContentPane();

		ta = new Thread(this);

		date = new JLabel("date");
		date.setBounds(1045, 20, 425, 30);
		date.setForeground(Color.BLACK);
		date.setFont(date1);

		time = new JLabel("time");
		time.setBounds(1045, 60, 425, 30);
		time.setForeground(Color.BLACK);
		time.setFont(date1);

		ta.start();

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();

		jp1.setLayout(null);
		jp2.setLayout(null);
		jp4.setLayout(null);
		jp5.setLayout(null);
		jp6.setLayout(null);

		jp1.setBounds(0, 0, 1370, 100);
		jp2.setBounds(175, 100, 845, 630);
		jp4.setBounds(0, 100, 175, 630);
		jp5.setBounds(1000, 100, 350, 250);
		jp6.setBounds(1000, 350, 350, 380);

		jp1.setBackground(Color.MAGENTA);
		jp2.setBackground(Color.white);

		// jp4.setBackground(new Color(84,61,94));
		// jp5.setBackground(new Color(84,61,94));
		jp6.setBackground(Color.white);

		con1.add(jp1);
		con1.add(jp2);
		con1.add(jp4);
		con1.add(jp5);
		con1.add(jp6);

		// JScrollBar jsb=new JScrollBar();
		// jsb.setBounds(0, 0, 100, 500);

		jtp = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		ys = new JLabel(new ImageIcon("c:\\proj\\jp511.jpg"));
		ys.setBounds(0, 0, 350, 380);

		homepage = new JLabel(new ImageIcon("c:\\proj\\homepage.jpg"));
		homepage.setBounds(0, 0, 845, 630);
		jp4t = new JLabel(new ImageIcon("c:\\proj\\jp41.jpg"));
		jp4t.setBounds(0, 0, 175, 630);

		mn1 = new JLabel();
		mn1.setBounds(70, 50, 500, 25);
		sd1 = new JLabel();
		sd1.setBounds(70, 120, 100, 25);
		st1 = new JLabel();
		st1.setBounds(70, 190, 100, 25);
		cla1 = new JLabel();
		cla1.setBounds(70, 255, 100, 25);
		nos1 = new JLabel();
		nos1.setBounds(70, 315, 30, 25);

		mn1.setForeground(new Color(255, 255, 255));
		sd1.setForeground(new Color(255, 255, 255));
		st1.setForeground(new Color(255, 255, 255));
		nos1.setForeground(new Color(255, 255, 255));
		cla1.setForeground(new Color(255, 255, 255));
		Font jf = new Font("Vegabond", Font.BOLD, 14);
		mn1.setFont(jf);
		sd1.setFont(jf);
		st1.setFont(jf);
		cla1.setFont(jf);
		nos1.setFont(jf);

		mn1.setBorder(null);
		sd1.setBorder(null);
		st1.setBorder(null);
		nos1.setBorder(null);
		cla1.setBorder(null);

		title = new JLabel(new ImageIcon("c:\\proj\\12345.jpg"));
		mo1 = new JLabel("Select Movie");
		da1 = new JLabel("Date");
		ti1 = new JLabel("Time");
		cl1 = new JLabel("Class");
		se1 = new JLabel("Number Of Seat");

		title.setBounds(0, 0, 1370, 100);
		mo1.setBounds(100, 100, 250, 25);
		da1.setBounds(100, 200, 250, 25);
		ti1.setBounds(100, 300, 250, 25);
		cl1.setBounds(500, 100, 250, 25);
		se1.setBounds(500, 200, 250, 25);
		im1 = new JLabel(new ImageIcon("c:\\proj\\preview.jpg"));
		im1.setFont(jf);

		mo = new JComboBox();
		da = new JComboBox();
		ti = new JComboBox();
		cl = new JComboBox();
		se = new JComboBox();

		mo.setBounds(100, 130, 150, 25);
		da.setBounds(100, 230, 150, 25);
		ti.setBounds(100, 330, 150, 25);
		cl.setBounds(500, 130, 150, 25);
		se.setBounds(500, 230, 150, 25);

		se.addItem("1");
		se.addItem("2");
		se.addItem("3");
		se.addItem("4");
		se.addItem("5");

		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select count(*) from tab where TNAME='SHOW'");
			rs.next();
			int g = rs.getInt("count(*)");
			if (g == 0)
			{
				mo.addItem("MOVIE");
				da.addItem("DATE");
				ti.addItem("TIME");
				cl.addItem("CLASS");
				se.addItem("SEAT");
			}

			ResultSet rs1 = st.executeQuery("select distinct(mname) from MOVIE where mnumber in(select mnumber from SHOW)");
			while (rs1.next())
			{
				String mo2 = rs1.getString("mname");
				mo.addItem(mo2);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		ok = new JButton("Proceed");
		ok.setActionCommand("PROCED");
		ok.setBounds(600, 450, 175, 30);

		String asdasd = new String("ANISH");

		// jp4 group
		hm = new JButton("Home");
		hm.setActionCommand("Home");
		hm.setBounds(0, 50, 175, 30);

		vi = new JButton("View List");
		vi.setActionCommand("VIEW MOVIE LIST");
		vi.setBounds(0, 100, 175, 30);

		ma = new JButton("Manage Movie");
		ma.setActionCommand("MANAGE MOVIE");
		ma.setBounds(0, 150, 175, 30);

		sh = new JButton("Manage Show");
		sh.setActionCommand("MANAGE SHOW");
		sh.setBounds(0, 200, 175, 30);

		daly = new JButton("Daily Collection");
		daly.setActionCommand("DAILY");
		daly.setBounds(0, 250, 175, 30);

		moly = new JButton("Monthly Collection");
		moly.setActionCommand("MONTHLY");
		moly.setBounds(0, 300, 175, 30);

		can = new JButton("Cancle Booking");
		can.setActionCommand("CANCEL");
		can.setBounds(0, 350, 175, 30);

		sett = new JButton("Setting");
		sett.setActionCommand("SETTING");
		sett.setBounds(0, 400, 175, 30);

		lo = new JButton("Logout");
		lo.setActionCommand("LOG OUT");
		lo.setBounds(0, 450, 175, 30);

		ext = new JButton("Exit");
		ext.setActionCommand("EXIT");
		ext.setBounds(0, 500, 175, 30);

		hm.setFont(new Font("Felix Titling", Font.PLAIN, 15));
		vi.setFont(new Font("Felix Titling", Font.PLAIN, 15));
		ma.setFont(new Font("Felix Titling", Font.PLAIN, 15));
		sh.setFont(new Font("Felix Titling", Font.PLAIN, 15));
		daly.setFont(new Font("Felix Titling", Font.PLAIN, 15));
		moly.setFont(new Font("Felix Titling", Font.PLAIN, 14));
		can.setFont(new Font("Felix Titling", Font.PLAIN, 14));
		sett.setFont(new Font("Felix Titling", Font.PLAIN, 15));
		lo.setFont(new Font("Felix Titling", Font.PLAIN, 15));
		ext.setFont(new Font("Felix Titling", Font.PLAIN, 15));

		hm.setBorder(null);
		vi.setBorder(null);
		ma.setBorder(null);
		sh.setBorder(null);
		daly.setBorder(null);
		moly.setBorder(null);
		can.setBorder(null);
		sett.setBorder(null);
		lo.setBorder(null);
		ext.setBorder(null);

		jp4.add(hm);
		jp4.add(vi);
		jp4.add(ma);
		jp4.add(sh);
		jp4.add(daly);
		jp4.add(moly);
		jp4.add(can);
		jp4.add(sett);
		jp4.add(lo);
		jp4.add(ext);
		jp4.add(jp4t);

		im1.setBounds(0, 0, 350, 250);

		jp2.add(ok);

		jp5.add(im1);

		jp1.add(title);

		jp2.add(mo);
		jp2.add(da);
		jp2.add(ti);
		jp2.add(cl);
		jp2.add(se);

		title.add(date);
		title.add(time);

		jp6.add(ys);
		ys.add(mn1);
		ys.add(sd1);
		ys.add(st1);
		ys.add(nos1);
		ys.add(se1);
		ys.add(cla1);

		Font ff = new Font("Arial", Font.BOLD, 12);
		Font fff = new Font("Arial", Font.BOLD, 20);
		Font ffff = new Font("Bleeding Cowboys", Font.BOLD, 50);

		ok.setFont(new Font("Felix Titling", Font.BOLD, 20));

		title.setFont(ffff);

		jtp.setFont(fff);
		jtp.setBackground(Color.red);

		// mo1.setForeground(Color.WHITE);
		// da1.setForeground(Color.WHITE);
		// ti1.setForeground(Color.WHITE);
		// cl1.setForeground(Color.WHITE);
		// se1.setForeground(Color.WHITE);

		mo1.setFont(fff);
		da1.setFont(fff);
		ti1.setFont(fff);
		cl1.setFont(fff);
		se1.setFont(fff);
		// im1.setFont(fff);

		jp2.add(mo1);
		jp2.add(da1);
		jp2.add(ti1);
		jp2.add(cl1);
		jp2.add(se1);
		jp2.add(jtp);
		jp2.add(homepage);

		mo.addActionListener(this);
		ok.addActionListener(this);
		lo.addActionListener(this);
		ma.addActionListener(this);
		sh.addActionListener(this);
		vi.addActionListener(this);
		hm.addActionListener(this);
		ext.addActionListener(this);
		daly.addActionListener(this);
		moly.addActionListener(this);
		can.addActionListener(this);
		sett.addActionListener(this);

		// mo.addItemListener(new ItemListener()
		// {
		// @Override
		// public void itemStateChanged(ItemEvent ie)
		// {
		// if(ie.getStateChange()==1)
		// { getdate(); }
		// }
		// });
		mo.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				getdate();

			}
		});

		da.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent ie)
			{
				if (ie.getStateChange() == 1)
				{
					gettime();
				}
			}
		});

		ti.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent ie)
			{
				if (ie.getStateChange() == 1)
				{
					getclass();
				}
			}
		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public int check(String tab_title)
	{
		jtp.removeAll();
		// stem.out.print("\n #########)))))))))"+rr);
		int n = jtp.getTabCount();
		
		for (int j = 0; j < n; j++)
		{
			if (tab_title.equals(jtp.getTitleAt(j)))
			{
				
				System.out.println(jtp.getTitleAt(j));
			}
		}
		return 1;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{

		String s = ae.getActionCommand();
		HOME h = new HOME("HOME");
		SIGN p = new SIGN("LOGIN PAGE");
		System.out.print(s);
		if (s.equals("Home"))
		{
			System.out.print("\n anishsfdsafas");
			setVisible(false);
			h.setVisible(true);
			h.setSize(1370, 730);
		}

		if (s.equals("PROCED"))
		{
			int m1 = da.getSelectedIndex();
			System.out.println("\n anish patek ))))))))" + m1);
			setForeground(Color.red);
			int ff = check("SEAT");
			if (ff == 1)
			{
				ok.setVisible(false);
				mo.setVisible(false);
				da.setVisible(false);
				ti.setVisible(false);
				cl.setVisible(false);
				se.setVisible(true);
				ok.setVisible(true);
				mo1.setVisible(false);
				da1.setVisible(true);
				ti1.setVisible(false);
				cl1.setVisible(false);
				se1.setVisible(false);
				date.setVisible(false);
				time.setVisible(false);
				Connection con;
				ResultSet r, rs0, rs01, rsg, rss;
				try
				{
					Class.forName("oracle.jdbc.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
					Statement st = con.createStatement();

					String ti3 = (String) ti.getSelectedItem();
					String da3 = (String) da.getSelectedItem();
					String mo3 = (String) mo.getSelectedItem();
					String abc = "";
					ImageIcon ii = new ImageIcon("c:\\proj\\manage.jpg");
					r = st.executeQuery("select seatid from SEATNAME where bid in(select bid from BOOKING where shnumber in(select shnumber from SHOW where mnumber in(select mnumber from MOVIE where mname='" + mo.getSelectedItem() + "')and to_char(mtime,'dd-mon-yyhh:mi:ss a.m.')='" + da.getSelectedItem() + "" + ti.getSelectedItem() + "'))");
					while (r.next())
					{
						abc = abc + r.getString("SEATID");
					}
					Project.data = abc;
					System.out.print("\n!!!!!!!!!!!!!");
					System.out.print(Project.data);
					String se66 = cl.getSelectedItem().toString();
					String se667 = se66.substring(0, 1);

					if (se667.equals("G"))
					{
						rsg = st.executeQuery("select * from SHOW where shnumber in(select shnumber from SHOW where to_char(mtime,'dd-mon-yy hh:mi:ss a.m.')='" + da.getSelectedItem().toString() + " " + ti.getSelectedItem().toString() + "') and mnumber in(select mnumber from MOVIE where mname='" + mo.getSelectedItem().toString() + "')");
						rsg.next();
						int alg = rsg.getInt("ASG");
						int se444 = Integer.parseInt(se.getSelectedItem().toString());
						System.out.print(":::::" + se444);
						int avg = 42 - alg;
						String avg111 = Integer.toString(avg) + " Seat Is Available For This Show Try Other Show";
						if (avg < se444)
						{

							JOptionPane.showMessageDialog(jp2, avg111, "CAN'T SELECT", JOptionPane.ERROR_MESSAGE);
							setVisible(false);
							HOME h1 = new HOME("HOME");
							h1.setVisible(true);
							h1.setSize(1370, 730);
							System.out.print("\n &&&&&&&&&&&&&");
						}
					}
					if (se667.equals("S"))
					{
						rss = st.executeQuery("select ASS from SHOW where shnumber in(select shnumber from SHOW where to_char(mtime,'dd-mon-yy hh:mi:ss a.m.')='" + da.getSelectedItem().toString() + " " + ti.getSelectedItem().toString() + "') and mnumber in(select mnumber from MOVIE where mname='" + mo.getSelectedItem().toString() + "')");
						rss.next();
						int als = rss.getInt("ASS");
						int se4444 = Integer.parseInt(se.getSelectedItem().toString());
						System.out.print(":::::" + se4444);
						int avs = 98 - als;
						String avs111 = Integer.toString(avs) + " Seat Is Available For This Show Try Other Show";
						if (avs < se4444)
						{

							JOptionPane.showMessageDialog(jp2, avs111, "CAN'T SELECT", JOptionPane.ERROR_MESSAGE);
							setVisible(false);
							HOME h1 = new HOME("HOME");
							h1.setVisible(true);
							h1.setSize(1370, 730);
							System.out.print("\n &&&&&&&&&&&&&");
						}
					}

					st.close();
					con.close();

					Project.mo6 = mo.getSelectedItem().toString();
					Project.da6 = da.getSelectedItem().toString();
					Project.ti6 = ti.getSelectedItem().toString();
					Project.cl6 = cl.getSelectedItem().toString();
					Project.se6 = se.getSelectedItem().toString();

					mn1.setText(Project.mo6);
					sd1.setText(Project.da6);
					st1.setText(Project.ti6);
					nos1.setText(Project.se6);
					cla1.setText(Project.cl6);
					jtp.add("SEAT", new SEAT());
					jtp.setBounds(0, 0, 845, 630);
					// System.out.println("\n OK");

				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(jp2, "Please Select All Item As Per Reqirement", "SELECTION ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(false);
					HOME h1 = new HOME("HOME");
					h1.setVisible(true);
					h1.setSize(1370, 730);
					// e.printStackTrace();
					// System.out.println(e);
				}
			}
		}

		else if (s.equals("LOG OUT"))
		{
			setVisible(false);
			p.setVisible(true);
			p.setSize(1370, 730);
		}
		else if (s.equals("MANAGE SHOW"))
		{
			int ff = check("MANAGE SHOW");
			if (ff == 1)
			{

				ok.setVisible(false);
				mo.setVisible(false);
				da.setVisible(false);
				ti.setVisible(false);
				cl.setVisible(false);
				se.setVisible(false);

				mo1.setVisible(false);
				da1.setVisible(false);
				ti1.setVisible(false);
				cl1.setVisible(false);
				se1.setVisible(false);
				// date.setVisible(false);
				// time.setVisible(false);
				jtp.add("MANAGE SHOW", new MANAGESHOW());
				jtp.setBounds(0, 0, 1100, 900);
			}
		}

		else if (s.equals("MANAGE MOVIE"))
		{
			int ff = check("MANAGE MOVIE");
			if (ff == 1)
			{
				ok.setVisible(false);
				mo.setVisible(false);
				da.setVisible(false);
				ti.setVisible(false);
				cl.setVisible(false);
				se.setVisible(false);

				mo1.setVisible(false);
				da1.setVisible(false);
				ti1.setVisible(false);
				cl1.setVisible(false);
				se1.setVisible(false);
				// date.setVisible(false);
				// time.setVisible(false);

				jtp.add("MANAGE MOVIE", new MANAGEMOVIE());
				jtp.setBounds(0, 0, 1100, 900);
			}
		}
		else if (s.equals("VIEW MOVIE LIST"))
		{
			int ff = check("TABLE");
			if (ff == 1)
			{

				ok.setVisible(false);
				mo.setVisible(false);
				da.setVisible(false);
				ti.setVisible(false);
				cl.setVisible(false);
				se.setVisible(false);
				// ok.setVisible(false);
				mo1.setVisible(false);
				da1.setVisible(false);
				ti1.setVisible(false);
				cl1.setVisible(false);
				se1.setVisible(false);
				// date.setVisible(false);
				// time.setVisible(false);

				// jp=new JPanel();
				jtp.setBounds(0, 0, 845, 630);
				jtp.add("TABLE", new TABLE());
				jtp.setBounds(0, 0, 845, 630);
			}

		}

		else if (s.equals("DAILY"))
		{
			System.out.println("JJJAAAAAAAAA");
			int ff = check("DAILY");
			if (ff == 1)
			{

				date.setVisible(false);
				time.setVisible(false);
				ok.setVisible(false);
				mo.setVisible(false);
				da.setVisible(false);
				ti.setVisible(false);
				cl.setVisible(false);
				se.setVisible(false);
				ok.setVisible(false);
				mo1.setVisible(false);
				da1.setVisible(false);
				ti1.setVisible(false);
				cl1.setVisible(false);
				se1.setVisible(false);

				jtp.add("DAILY", new CollectDaily());
				jtp.setBounds(0, 0, 845, 630);
			}

		}
		else if (s.equals("MONTHLY"))
		{
			int a = check("MONTH");
			if (a == 1)
			{

				// date.setVisible(false);
				// time.setVisible(false);
				ok.setVisible(false);
				mo.setVisible(false);
				da.setVisible(false);
				ti.setVisible(false);
				cl.setVisible(false);
				se.setVisible(false);
				// ok.setVisible(false);
				mo1.setVisible(false);
				da1.setVisible(false);
				ti1.setVisible(false);
				cl1.setVisible(false);
				se1.setVisible(false);

				jtp.add("MONTH", new CollectMonth());
				jtp.setBounds(0, 0, 845, 630);
			}
		}
		else if (s.equals("EXIT"))
		{
			System.exit(0);
		}
		else if (s.equals("CANCEL"))
		{
			int a = check("CANCELBOOK");
			if (a == 1)
			{
				ok.setVisible(false);
				mo.setVisible(false);
				da.setVisible(false);
				ti.setVisible(false);
				cl.setVisible(false);
				se.setVisible(false);
				// ok.setVisible(false);
				mo1.setVisible(false);
				da1.setVisible(false);
				ti1.setVisible(false);
				cl1.setVisible(false);
				se1.setVisible(false);

				jtp.add("CANCEL BOOKING", new CANCEL());
				jtp.setBounds(0, 0, 845, 630);
			}
		}
		else if (s.equals("SETTING"))
		{
			int a = check("SETTING_PAGE");
			if (a == 1)
			{

				ok.setVisible(false);
				mo.setVisible(false);
				da.setVisible(false);
				ti.setVisible(false);
				cl.setVisible(false);
				se.setVisible(false);
				// ok.setVisible(false);
				mo1.setVisible(false);
				da1.setVisible(false);
				ti1.setVisible(false);
				cl1.setVisible(false);
				se1.setVisible(false);

				jtp.add("SETTING", new SETTING());
				jtp.setBounds(0, 0, 845, 630);
			}
		}
	}

	public void getdate()
	{
		ImageIcon ii = null;
		String img3 = "";
		String mo3 = (String) mo.getSelectedItem();
		System.out.println(mo3);
		Connection con;
		ResultSet r, r1;
		try
		{
			con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			r = st.executeQuery("select distinct(to_char(mdate,'dd-mon-yy')) from SHOW where mnumber in(select mnumber from MOVIE where mname='" + mo3 + "') and mdate>=sysdate");
			da.removeAllItems();
			ti.removeAllItems();
			cl.removeAllItems();
			while (r.next())
			{
				da.addItem(r.getString("(TO_CHAR(MDATE,'DD-MON-YY'))"));
			}
			st.close();
			// con=null;
			con.close();
		}
		catch (Exception e)
		{
			System.out.println("in getdate   ");
			e.printStackTrace();
		}
		try

		{

			con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			r1 = st.executeQuery("select * from MOVIE where mname='" + mo3 + "'");
			while (r1.next())
			{
				img3 = r1.getString("image");
				System.out.print(r1.getString("image"));
				ii = new ImageIcon(img3);
				im1.setIcon(ii);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void gettime()
	{
		String mo3 = (String) mo.getSelectedItem();
		String da3 = (String) da.getSelectedItem();
		System.out.println(da3);
		Connection con;
		ResultSet r;
		try
		{
			con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			r = st.executeQuery("select to_char(mtime,'hh:mi:ss a.m.') from SHOW where mnumber in(select mnumber from MOVIE where mname='" + mo3 + "')and mdate='" + da3 + "'");
			ti.removeAllItems();
			int mm = 0;
			while (r.next())
			{
				ti.addItem(r.getString("TO_CHAR(MTIME,'HH:MI:SSA.M.')"));
			}
			st.close();
			// con=null;
			con.close();
		}
		catch (Exception e)
		{
			System.out.println("in subtime");
			e.printStackTrace();
		}

	}

	public void getclass()
	{
		String ti3 = (String) ti.getSelectedItem();
		String da3 = (String) da.getSelectedItem();
		String mo3 = (String) mo.getSelectedItem();

		System.out.println(ti3);
		Connection con;
		ResultSet r;
		try
		{
			con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			r = st.executeQuery("select pricegold,pricesilver from PRICE where shnumber in(select shnumber from SHOW where to_char(mtime,'dd-mon-yyhh:mi:ss a.m.')='" + da3 + "" + ti3 + "')and mnumber in(select mnumber from MOVIE where mname='" + mo3 + "')");
			cl.removeAllItems();
			while (r.next())
			{
				String cl3 = r.getString("pricegold");
				String cl33 = r.getString("pricesilver");
				cl.addItem("GOLD(" + cl3 + ")");
				cl.addItem("SILVER(" + cl33 + ")");
			}
			st.close();
			con.close();
		}
		catch (Exception e)
		{
			System.out.println("in subclass");
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		String month[] = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
		String num[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
				"41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
				"51", "52", "53", "54", "55", "56", "57", "58", "59", "60"};
		while (true)
		{

			cal = new GregorianCalendar();
			dd = num[cal.get(Calendar.DATE)];
			mm = month[cal.get(Calendar.MONTH)];
			yy = cal.get(Calendar.YEAR);
			hr = num[cal.get(Calendar.HOUR)];
			mni = num[cal.get(Calendar.MINUTE)];
			sc = num[cal.get(Calendar.SECOND)];

			d = "DATE : " + dd + " - " + mm + " - " + yy;
			t = "TIME : " + hr + " : " + mni + " : " + sc;

			date.setText(d);
			time.setText(t);

			try
			{
				Thread.sleep(1000);
			}
			catch (Exception ee)
			{
				ee.printStackTrace();
				;
				System.out.print("in thread ");
				System.out.print(ee);
			}
		}
	}

	void repa()
	{
		date.setText(null);
		time.setText(null);
		date.setText(d);
		time.setText(t);
	}

}

class SIGN extends JFrame implements ActionListener
{
	JPanel jp2;
	JButton jbb1, ex;
	JTextField jt1;
	JPasswordField jt2;
	JLabel jb1, jb2, tit, img, msg;
	JDialog jd;

	SIGN(String s)
	{
		super(s);
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception eee)
		{
		}

		setLayout(null);

		jbb1 = new JButton(new ImageIcon("c:\\proj\\login1.jpg"));
		ex = new JButton(new ImageIcon("c:\\proj\\exit.jpg"));
		tit = new JLabel("ADMIN LOGIN");
		img = new JLabel(new ImageIcon("c:\\proj\\Vista Wallpaper (111).jpg"));
		jt1 = new JTextField();
		jt2 = new JPasswordField();

		jp2 = new JPanel();

		jbb1.setActionCommand("Submit");
		ex.setActionCommand("EXIT");
		jb1 = new JLabel("USERNAME   :");
		jb2 = new JLabel("PASSWORD   :");

		jbb1.setBounds(700, 500, 175, 30);
		ex.setBounds(700, 600, 175, 30);
		jb1.setBounds(100, 300, 200, 30);
		jb2.setBounds(100, 400, 200, 30);
		tit.setBounds(50, 50, 600, 150);
		img.setBounds(0, 0, 1370, 730);
		jt1.setBounds(220, 300, 200, 30);
		jt2.setBounds(220, 400, 200, 30);
		jt2.setEchoChar('*');

		jt1.setBackground(Color.GRAY);
		jt1.setForeground(Color.WHITE);
		jt2.setBackground(Color.GRAY);
		jt2.setForeground(Color.WHITE);

		Font f1 = new Font("Trajan Pro", Font.BOLD, 15);
		Font f2 = new Font("Arial", Font.BOLD, 30);

		jb1.setFont(f1);
		jb2.setFont(f1);
		tit.setFont(f2);
		jt1.setFont(f1);
		jt2.setFont(f1);
		jbb1.setFont(f1);

		add(img);
		img.add(jb1);
		img.add(jb2);
		img.add(tit);
		img.add(jt1);
		img.add(jt2);
		img.add(jbb1);
		img.add(ex);

		jbb1.addActionListener(this);
		ex.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{

		String s = ae.getActionCommand();
		if (s.equals("Submit"))
		{

			String usr = jt1.getText();
			String usrn = "";
			char[] psw = jt2.getPassword();
			String pswd = "";
			String p = "";

			for (int z = 0; z < psw.length; z++)
			{
				pswd = pswd + psw[z];
			}
			System.out.print(pswd);

			try
			{
				Class.forName("oracle.jdbc.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
				Statement st = con.createStatement();
				ResultSet r1 = st.executeQuery("select uname,psw from LOGIN where uname='" + usr + "'");
				while (r1.next())
				{
					usrn = r1.getString("uname");
					p = r1.getString("psw");
				}

				if (usr.equals("") && pswd.equals(""))
					JOptionPane.showMessageDialog(jp2, "INSERT AS REQUIRE", "ERROR", JOptionPane.ERROR_MESSAGE);

				else if (usr.equals(usrn))
				{
					if (pswd.equals(p))
					{
						HOME h = new HOME("HOME");
						setVisible(false);
						h.setVisible(true);
						h.setSize(1370, 730);
					}
					else
						JOptionPane.showMessageDialog(jp2, "ENTER CORRECT PASSWORD", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(jp2, "ENTER CORRECT USERNAME", "ERROR", JOptionPane.ERROR_MESSAGE);

			}
			catch (Exception es)
			{
				System.out.println(es);
			}
		}
		else if (s.equals("EXIT"))
			System.exit(0);
	}
}

public class Project
{
	static public String mo6, da6, ti6, cl6, se6, data;

	static
	{

		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:XE", "project1", "project1");
			Statement st = con.createStatement();
			ResultSet r1 = st.executeQuery("select count(*) from tab where TNAME='MOVIE'");
			r1.next();
			int g = r1.getInt("count(*)");
			if (g == 0)
			{
				st.execute("create table MOVIE ( mnumber number(8) constraint pk_mnu primary key,mname varchar2(30) constraint nn_mna not null,img varchar2(200),tgold number(3) constraint nn_tg not null,tsilver number(3) constraint nn_ts not null)");
				System.out.print("\n create table MOVIE");
			}

			ResultSet r2 = st.executeQuery("select count(*) from tab where TNAME='SHOW'");
			r2.next();
			int k = r2.getInt("count(*)");
			if (k == 0)
			{
				st.execute("create table SHOW(shnumber number(8) constraint pk_shn primary key,mnumber number(8),mdate date,mtime date,ASG number(3),ASS number(3),constraint fk_mnu foreign key(mnumber) references MOVIE(mnumber) on delete cascade)");
				System.out.print("\n create table SHOW");
			}
			ResultSet r = st.executeQuery("select count(*) from tab where TNAME='PRICE'");
			r.next();
			int h = r.getInt("count(*)");
			if (h == 0)
			{
				st.execute("create table PRICE(mnumber number(8),shnumber number(8),pgold number(3) constraint nn_pgg not null,psilver number(3)constraint nn_pss not null,constraint fk_mnuu foreign key(mnumber) references MOVIE(mnumber) on delete cascade,constraint fk_shnuu foreign key(shnumber) references SHOW(shnumber) on delete cascade)");
				System.out.print("\n create table PRICE");
			}
			ResultSet r3 = st.executeQuery("select count(*) from tab where TNAME='BOOKING'");
			r3.next();
			int ii = r3.getInt("count(*)");
			if (ii == 0)
			{
				st.execute("create table BOOKING(bid number constraint pk_bkn primary key,shnumber number(3),mnumber number(3),class varchar2(20),noseat number(3),amt number(7),constraint fk_shn2 foreign key(shnumber) references SHOW(shnumber) on delete cascade ,constraint fk_mnu2 foreign key(mnumber) references MOVIE(mnumber) on delete cascade)");
				System.out.print("\n create table BOOKING");
			}
			ResultSet r4 = st.executeQuery("select count(*) from tab where TNAME='SEATNAME'");
			r4.next();
			int kk = r4.getInt("count(*)");
			if (kk == 0)
			{
				st.execute("create table SEATNAME(bid number(3),seatid varchar2(100),constraint fk_bid1 foreign key(bid) references BOOKING(bid))");
				System.out.print("\n create table SEATNAME");
			}
		}
		catch (Exception ee)
		{
			ee.printStackTrace();
			System.out.print("\n aishfisdhfksjd");
		}
	}

	public static void main(String[] st) throws Exception
	{
		HOME h1 = new HOME("thf");
		SIGN p = new SIGN("LOGIN PAGE");

		p.setVisible(true);
		p.setSize(1370, 730);
	}
}

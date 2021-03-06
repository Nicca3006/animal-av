package animal.vhdl.editor.graphics;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import translator.AnimalTranslator;
import translator.TranslatableGUIElement;
import animal.editor.Editor;
import animal.editor.graphics.meta.FillablePrimitiveEditor;
import animal.graphics.PTGraphicObject;
import animal.gui.DrawCanvas;
import animal.main.Animation;
import animal.misc.EditPoint;
import animal.misc.EditableObject;
import animal.misc.MSMath;
import animal.misc.XProperties;
import animal.vhdl.graphics.PTD;
import animal.vhdl.logic.LogicD;
import animal.vhdl.logic.LogicVHDL;

/**
 * Editor for a D flip flop
 * 
 * @author p_li
 */
public class DEditor extends FillablePrimitiveEditor implements ItemListener,
		ActionListener, PropertyChangeListener, KeyListener {

	private JCheckBox syn;
	private JCheckBox asynSR;

	private JTextField setText;
	private JComboBox<Character> setValueComboBox;

	private JTextField qText;

	private JTextField clkText;
	private JComboBox<Character> clkValueComboBox;

	private JTextField ceText;
	private JComboBox<Character> ceValueComboBox;

	private JTextField SDText;
	private JComboBox<Character> SDValueComboBox;

	private JTextField RDText;
	private JComboBox<Character> RDValueComboBox;

	public DEditor() {
		super();
	}

	protected void buildGUI() {
		TranslatableGUIElement generator = AnimalTranslator.getGUIBuilder();

		// input & output box
		Box IOBox = new Box(BoxLayout.PAGE_AXIS);
		IOBox.setBorder(new TitledBorder(null, "in/output settings",
				TitledBorder.LEADING, TitledBorder.TOP));
		{
			// input box: set and reset
			Box inputBox = new Box(BoxLayout.PAGE_AXIS);
			{
				// set box
				Box setBox = new Box(BoxLayout.LINE_AXIS);
				{
					// set name label
					JLabel setLabel = new JLabel("D pin name: ");
					setBox.add(setLabel);
					// set name text
					setText = new JTextField("inD");
					setText.addKeyListener(this);
					setBox.add(setText);
					// set value label
					JLabel setValueLabel = new JLabel(" value: ");
					setBox.add(setValueLabel);
					// set value combined box
					Character[] setValues = LogicVHDL.LOGIC_VALUES;
					setValueComboBox = new JComboBox<Character>(setValues);
					setValueComboBox.addItemListener(this);
					setBox.add(setValueComboBox);
				}
				// set box is finish
				inputBox.add(setBox);
			}
			// input box is finish
			IOBox.add(inputBox);

			// output box: Q and ^Q
			Box outputBox = new Box(BoxLayout.PAGE_AXIS);
			{
				// Q box
				Box qBox = new Box(BoxLayout.LINE_AXIS);
				{
					// q name label
					JLabel qLabel = new JLabel("  output Q pin name: ");
					qBox.add(qLabel);
					// q name text
					qText = new JTextField("outQ");
					qText.addKeyListener(this);
					qBox.add(qText);
				}
				// q box is finish
				outputBox.add(qBox);

			}
			// output box is finish
			IOBox.add(outputBox);
		}
		// io box is finish
		addBox(IOBox);

		// synchronous control: syn, Clk and CE
		Box synControlBox = new Box(BoxLayout.PAGE_AXIS);
		synControlBox.setBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP));
		{
			// syn
			syn = new JCheckBox("Clock and RD");
			syn.addItemListener(this);
			synControlBox.add(syn);
			// clk box
			Box clkBox = new Box(BoxLayout.LINE_AXIS);
			{
				// clk name label
				JLabel clkLabel = new JLabel("  clock pin name: ");
				clkBox.add(clkLabel);
				// clk name text
				clkText = new JTextField("cClk");
				clkText.addKeyListener(this);
				clkBox.add(clkText);
				// clk value label
				JLabel clkValueLabel = new JLabel(" value: ");
				clkBox.add(clkValueLabel);
				// clk value combined box
				Character[] clkValues = LogicVHDL.LOGIC_VALUES;
				clkValueComboBox = new JComboBox<Character>(clkValues);
				clkValueComboBox.addItemListener(this);
				clkBox.add(clkValueComboBox);

			}
			// clkBox is finish
			synControlBox.add(clkBox);
			clkBox.setEnabled(syn.isSelected());

			// ce box
			Box ceBox = new Box(BoxLayout.LINE_AXIS);
			{
				// ce name label
				JLabel ceLabel = new JLabel("  RD pin name: ");
				ceBox.add(ceLabel);
				// ce name text
				ceText = new JTextField("cRD");
				ceText.addKeyListener(this);
				ceBox.add(ceText);
				// ce value label
				JLabel ceValueLabel = new JLabel(" value: ");
				ceBox.add(ceValueLabel);
				// ce value combined box
				Character[] ceValues = LogicVHDL.LOGIC_VALUES;
				ceValueComboBox = new JComboBox<Character>(ceValues);
				ceValueComboBox.addItemListener(this);
				ceBox.add(ceValueComboBox);
			}
			// ceBox is finish
			synControlBox.add(ceBox);
			ceBox.setEnabled(syn.isSelected());
		}
		// synchronous control box is finish
		addBox(synControlBox);

		// asynchronous control: asynSR, SD and RD
		Box asynSRControlBox = new Box(BoxLayout.PAGE_AXIS);
		asynSRControlBox
				.setBorder(new TitledBorder(null, "",
						TitledBorder.LEADING, TitledBorder.TOP));
		{
			// asynSR
			asynSR = new JCheckBox("ce and SD");
			asynSR.addItemListener(this);
			asynSRControlBox.add(asynSR);
			// SD box
			Box SDBox = new Box(BoxLayout.LINE_AXIS);
			{
				// SD name label
				JLabel SDLabel = new JLabel(" SD pin name: ");
				SDBox.add(SDLabel);
				// SD name text
				SDText = new JTextField("cSD");
				SDText.addKeyListener(this);
				SDBox.add(SDText);
				// SD value label
				JLabel SDValueLabel = new JLabel(" value: ");
				SDBox.add(SDValueLabel);
				// SD value combined box
				Character[] SDValues = LogicVHDL.LOGIC_VALUES;
				SDValueComboBox = new JComboBox<Character>(SDValues);
				SDValueComboBox.addItemListener(this);
				SDBox.add(SDValueComboBox);

			}
			// SDBox is finish
			asynSRControlBox.add(SDBox);
			SDBox.setEnabled(asynSR.isSelected());

			// RD box
			Box RDBox = new Box(BoxLayout.LINE_AXIS);
			{
				// RD name label
				JLabel RDLabel = new JLabel(" ce pin name: ");
				RDBox.add(RDLabel);
				// RD name text
				RDText = new JTextField("cCE");
				RDText.addKeyListener(this);
				RDBox.add(RDText);
				// RD value label
				JLabel RDValueLabel = new JLabel(" value: ");
				RDBox.add(RDValueLabel);
				// RD value combined box
				Character[] RDValues = LogicVHDL.LOGIC_VALUES;
				RDValueComboBox = new JComboBox<Character>(RDValues);
				RDValueComboBox.addItemListener(this);
				RDBox.add(RDValueComboBox);
			}
			// RDBox is finish
			asynSRControlBox.add(RDBox);
			RDBox.setEnabled(asynSR.isSelected());
		}
		// asynSRchronous control box is finish
		addBox(asynSRControlBox);

		// create shared entries (color, fill color)
		Box contentBox = createCommonElements(generator);

		// create type-specific stuff
		filledCB = generator.generateJCheckBox("GenericEditor.filled", null,
				this);
		filledCB.addItemListener(this);
		contentBox.add(filledCB);

		// finish the boxes
		finishBoxes();

	}

	public int pointsNeeded() {
		return 2;
	}

	public boolean nextPoint(int num, Point p) {
		PTD ff = (PTD) getCurrentObject();
		if (num == 1)
			ff.setStartNode(p.x, p.y);
		if (num == 2) {
			Point firstNode = ff.getStartNode();
			ff.setWidth(p.x - firstNode.x);
			ff.setHeight(p.y - firstNode.y);
		}
		return true;
	} // nextPoint;

	public int getMinDist(PTGraphicObject go, Point p) {
		PTD ff = (PTD) go;
		Point a = new Point(ff.getStartNode().x, ff.getStartNode().y);
		Rectangle boundingBox = ff.getBoundingBox();
		// if point is inside, there is not much of distance ;-)
		if (boundingBox.contains(p.x, p.y))
			return 0;

		// (ULC, URC)
		Point b = new Point(a.x + ff.getWidth(), a.y);
		int minDist = Integer.MAX_VALUE;
		int newDist = MSMath.dist(p, a, b);
		if (newDist < minDist)
			minDist = newDist;

		// (URC, LRC)
		b.translate(0, ff.getHeight());
		newDist = MSMath.dist(p, a, b);
		if (newDist < minDist)
			minDist = newDist;

		// (LRC, LLC)
		a.translate(ff.getWidth(), ff.getHeight());
		newDist = MSMath.dist(p, a, b);
		if (newDist < minDist)
			minDist = newDist;

		newDist = MSMath.dist(p, a, ff.getStartNode());
		if (newDist < minDist)
			minDist = newDist;
		return minDist;
	}

	public EditPoint[] getEditPoints(PTGraphicObject go) {
		PTD ff = (PTD) go;
		// int pSize = 2;
		int width = ff.getWidth();
		int height = ff.getHeight();
		// int i;
		// add change points(nodes)
		EditPoint[] result = new EditPoint[5];
		Point helper = ff.getStartNode();
		// result[5] = new EditPoint(1, helper);
		helper = new Point(helper.x + width, helper.y + height);
		result[0] = new EditPoint(2, helper);

		int x = ff.getStartNode().x;
		int y = ff.getStartNode().y;
		result[1] = new EditPoint(-2, new Point(x + (width / 2), y));
		result[2] = new EditPoint(-3, new Point(x + width, y + (height / 2)));
		result[3] = new EditPoint(-4, new Point(x + (width / 2), y + height));
		result[4] = new EditPoint(-5, new Point(x, y + (height / 2)));

		return result;
	} // getEditPoints

	public void setProperties(XProperties props) {
		colorChooser.setColor(props.getColorProperty(PTD.D_FLIPFLOP_TYPE_LABEL + ".color",
				Color.black));
		depthBox.setSelectedItem(props.getProperty(PTD.D_FLIPFLOP_TYPE_LABEL + ".depth",
				"16"));
		fillColorChooser.setColor(props.getColorProperty(PTD.D_FLIPFLOP_TYPE_LABEL
				+ ".color", Color.black));
		filledCB.setSelected(props.getBoolProperty(PTD.D_FLIPFLOP_TYPE_LABEL + ".filled"));
	}

	public void getProperties(XProperties props) {
		props.put(PTD.D_FLIPFLOP_TYPE_LABEL + ".color", colorChooser.getColor());
		props.put(PTD.D_FLIPFLOP_TYPE_LABEL + ".depth", depthBox.getSelectedItem());
		props.put(PTD.D_FLIPFLOP_TYPE_LABEL + ".fillColor", fillColorChooser.getColor());
		props.put(PTD.D_FLIPFLOP_TYPE_LABEL + ".filled", filledCB.isSelected());
	}

	public EditableObject createObject() {
		PTD pg = new PTD();
		storeAttributesInto(pg);
		return pg;
	}

	protected void storeAttributesInto(EditableObject eo) {
		super.storeAttributesInto(eo);
		PTD p = (PTD) eo;
		p.setColor(colorChooser.getColor());
		p.setFilled(filledCB.isSelected());
		p.setFillColor(fillColorChooser.getColor());
		p.setSynControl(syn.isSelected());
		p.setAsynSR(asynSR.isSelected());
		p.getInputPins().get(0).setPinName(setText.getText());
		p.getInputPins().get(0).setPinValue(
				(Character) setValueComboBox.getSelectedItem());
		p.getOutputPins().get(0).setPinName(qText.getText());
		p.getControlPins().get(0).setPinName(SDText.getText());
		p.getControlPins().get(0).setPinValue(
				(Character) SDValueComboBox.getSelectedItem());
		p.getControlPins().get(1).setPinName(RDText.getText());
		p.getControlPins().get(1).setPinValue(
				(Character) RDValueComboBox.getSelectedItem());
		p.getControlPins().get(2).setPinName(clkText.getText());
		p.getControlPins().get(2).setPinValue(
				(Character) clkValueComboBox.getSelectedItem());
		p.getControlPins().get(3).setPinName(ceText.getText());
		p.getControlPins().get(3).setPinValue(
				(Character) ceValueComboBox.getSelectedItem());
	}

	protected void extractAttributesFrom(EditableObject eo) {
		super.extractAttributesFrom(eo);
		PTD p = (PTD) eo;
		colorChooser.setColor(p.getColor());
		filledCB.setEnabled(true);
		filledCB.setSelected(p.isFilled());
		fillColorChooser.setColor(p.getFillColor());
		syn.setSelected(p.hasSynControl());
		asynSR.setSelected(p.hasAsynSR());
		setText.setText(p.getInputPins().get(0).getPinName());
		setValueComboBox.setSelectedItem(p.getInputPins().get(0).getPinValue());
		qText.setText(p.getOutputPins().get(0).getPinName());
		SDText.setText(p.getControlPins().get(0).getPinName());
		SDValueComboBox.setSelectedItem(p.getControlPins().get(0).getPinValue());
		RDText.setText(p.getControlPins().get(1).getPinName());
		RDValueComboBox.setSelectedItem(p.getControlPins().get(1).getPinValue());
		clkText.setText(p.getControlPins().get(2).getPinName());
		clkValueComboBox
				.setSelectedItem(p.getControlPins().get(2).getPinValue());
		ceText.setText(p.getControlPins().get(3).getPinName());
		ceValueComboBox.setSelectedItem(p.getControlPins().get(3).getPinValue());
	}

	public Editor getSecondaryEditor(EditableObject go) {
		DEditor result = new DEditor();
		// important! result must be of type RectangleEditor (or cast)
		// and the parameter passed must be of type PTRectangle.
		// Otherwise, not all attributes are copied!
		result.extractAttributesFrom(go);
		return result;
	}

	public String getStatusLineMsg() {
		return AnimalTranslator.translateMessage("DEditor.statusLine",
				new Object[] { DrawCanvas.translateDrawButton(),
						DrawCanvas.translateFinishButton(),
						DrawCanvas.translateCancelButton() });
	}

	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		PTD p = (PTD) getCurrentObject();

		if (p != null) {
			if (Animation.get() != null)
				Animation.get().doChange();
			repaintNow();
		}
	}

	public void propertyChange(PropertyChangeEvent event) {
		PTD poly = (PTD) getCurrentObject();
		String eventName = event.getPropertyName();
		if ("color".equals(eventName))
			poly.setColor((Color) event.getNewValue());
		else if ("fillColor".equals(eventName))
			poly.setFillColor((Color) event.getNewValue());
		if (!event.getOldValue().equals(event.getNewValue())) {
			repaintNow();
			if (Animation.get() != null)
				Animation.get().doChange();
		}
	}

	public String getBasicType() {
		return PTD.D_FLIPFLOP_TYPE_LABEL;
	}

	public void itemStateChanged(ItemEvent e) {
		PTD p = (PTD) getCurrentObject();
		Object es = e.getSource();

		if (p == null)
			return;
		whichOne: {
			if (es == filledCB) {
				p.setFilled(filledCB.isSelected());
				break whichOne;
			}
			setOutput: {
				if (es == syn) {
					p.setSynControl(syn.isSelected());
					break setOutput;
				}
				if (es == asynSR) {
					p.setAsynSR(asynSR.isSelected());
					break setOutput;
				}
				if (es == setValueComboBox) {
					p.getInputPins().get(0).setPinValue(
							(Character) setValueComboBox.getSelectedItem());
					break setOutput;
				}
				if (es == SDValueComboBox) {
					p.getControlPins().get(0).setPinValue(
							(Character) SDValueComboBox.getSelectedItem());
					break setOutput;
				}
				if (es == RDValueComboBox) {
					p.getControlPins().get(1).setPinValue(
							(Character) RDValueComboBox.getSelectedItem());
					break setOutput;
				}
				if (es == clkValueComboBox) {
					p.getControlPins().get(2).setPinValue(
							(Character) clkValueComboBox.getSelectedItem());
					break setOutput;
				}
				if (es == ceValueComboBox) {
					p.getControlPins().get(3).setPinValue(
							(Character) ceValueComboBox.getSelectedItem());
					break setOutput;
				}
			}
			setOutputValueOf(p);
		}
		Animation.get().doChange();
		repaintNow();
	}// itemStateChanged

	private void setOutputValueOf(PTD ff) {

		char[] inputListC = new char[8];
		for (int i = 0; i < ff.getInputPins().size(); i++) {
			inputListC[i] = ff.getInputPins().get(i).getPinValue();
		}

		inputListC[2] = ff.hasAsynSR() ? ff.getControlPins().get(0)
				.getPinValue() : '0';
		inputListC[3] = ff.hasAsynSR() ? ff.getControlPins().get(1)
				.getPinValue() : '0';

		inputListC[4] = ff.hasSynControl() ? ff.getControlPins().get(2)
				.getPinValue() : '1';
		inputListC[5] = ff.hasSynControl() ? ff.getControlPins().get(3)
				.getPinValue() : '1';

		for (int i = 0; i < ff.getOutputPins().size(); i++) {
			inputListC[i + 6] = ff.getOutputPins().get(i).getPinValue();
		}
		boolean inputList[] = new boolean[8];
		for (int i = 0; i < inputList.length; i++)
			inputList[i] = charToBoolean(inputListC[i]);

		LogicD lD = new LogicD(inputList);
		char qv = booleanToChar(lD.getQValue());
		ff.getOutputPins().get(0).setPinValue(qv);

	}

	private boolean charToBoolean(char c) {
		return (c == '1') ? true : false;
	}

	private char booleanToChar(boolean value) {
		return (value) ? '1' : '0';
	}

	public void keyPressed(KeyEvent arg0) {
	  // ignore
	}

	public void keyReleased(KeyEvent ke) {
		PTD p = (PTD) getCurrentObject();
		if (p != null) {
			Object es = ke.getSource();

			if (es == setText)
				p.getInputPins().get(0).setPinName(setText.getText());
			if (es == qText)
				p.getOutputPins().get(0).setPinName(qText.getText());
			if (es == SDText)
				p.getControlPins().get(0).setPinName(SDText.getText());
			if (es == RDText)
				p.getControlPins().get(1).setPinName(RDText.getText());
			if (es == clkText)
				p.getControlPins().get(2).setPinName(clkText.getText());
			if (es == ceText)
				p.getControlPins().get(3).setPinName(ceText.getText());
		}
		repaintNow();

	}

	public void keyTyped(KeyEvent arg0) {
		// ignore

	}
} // DEditor


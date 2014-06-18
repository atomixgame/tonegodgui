/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.core.layouts;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author t0neg0d
 */
public class LayoutParam {
	public static enum ParamType {
		clip,
		pad,
		margin,
		min,
		max,
		pref
	}
	public static enum Unit {
		Boolean,
		Float,
		Integer,
		Align,
		VAlign
	}
	public static enum SizeUnit {
		absolute,
		percent,
		fill
	}
	
	public ParamType type = null;
	public Map<String,Value> values = new HashMap();
	
	public LayoutParam(String param) {
		StringTokenizer st = new StringTokenizer(param, " ");
		String name = st.nextToken().toLowerCase();
		
		getHintType(name);
		parseHintValues(st);
	}
	
	private void getHintType(String name) {
		if (name.indexOf("pad") != -1 || name.indexOf("inset") != -1)
			type = ParamType.pad;
		else if (name.indexOf("clip") != -1)
			type = ParamType.clip;
		else if (name.indexOf("margin") != -1)
			type = ParamType.margin;
		else if (name.indexOf("min") != -1)
			type = ParamType.min;
		else if (name.indexOf("max") != -1)
			type = ParamType.max;
		else if (name.indexOf("pref") != -1)
			type = ParamType.pref;
		else
			type = ParamType.valueOf(name);
	}
	
	private void parseHintValues(StringTokenizer st) {
		if (type != null) {
			switch(type) {
				case clip:
					values.put("clip",new Value(Unit.Boolean, true));
					break;
				case pad:
					values.put("left",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					values.put("right",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					values.put("top",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					values.put("bottom",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					break;
				case margin:
					values.put("left",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					values.put("right",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					values.put("top",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					values.put("bottom",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					break;
				case min:
					values.put("x",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					values.put("y",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					break;
				case max:
					values.put("x",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					values.put("y",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					break;
				case pref:
					values.put("x",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					values.put("y",new Value(Unit.Float, SizeUnit.absolute, Float.parseFloat(st.nextToken())));
					break;
				default:
					while (st.hasMoreTokens()) {
						values.put(type.name(),new Value(Unit.Float, Float.valueOf(st.nextToken())));
					}
					break;
			}
		}
	}
	
	public ParamType getType() { return this.type; }
	public Map<String,Value> getValues() { return this.values; }
	
	public class Value<T> {
		public Unit unit;
		public SizeUnit sizeUnit = null;
		public T value;
		public Value(Unit unit, T value) {
			this(unit, null, value);
		}
		public Value(Unit unit, SizeUnit sizeUnit, T value) {
			this.unit = unit;
			this.sizeUnit = sizeUnit;
			this.value = value;
		}
		
		public T getValue() { return this.value; }
		public SizeUnit getSizeUnit() { return this.sizeUnit; }
	}
}

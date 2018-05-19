package DCFX.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Path {
	private final StringProperty name;
	private final StringProperty ip;

	private final StringProperty path;
	private final BooleanProperty pattern;
	private final DoubleProperty emptyRate;
	private final DoubleProperty usedRate;
	private final IntegerProperty days;

	public Path(){
		this(null,null);
	}

	public Path(String name, String ip) {
		this.name=new SimpleStringProperty(name);
		this.ip=new SimpleStringProperty(ip);

		this.path=new SimpleStringProperty("路径");
		this.pattern=new SimpleBooleanProperty(false);
		this.emptyRate=new SimpleDoubleProperty(0.3);
		this.usedRate=new SimpleDoubleProperty(0.7);
		this.days=new SimpleIntegerProperty(13);

	}

	public String setName(){
		return name.get();
	}

	public void setName(String name){
		this.name.set(name);
	}

	public StringProperty nameProperty(){
		return name;
	}

	public String setIp(){
		return ip.get();
	}

	public void setIp(String ip){
		this.ip.set(ip);
	}

	public StringProperty ipProperty(){
		return ip;
	}

	public String setPath(){
		return name.get();
	}

	public void setPath(String name){
		this.name.set(name);
	}

	public StringProperty pathProperty(){
		return name;
	}

	public Boolean setPattern(){
		return pattern.get();
	}

	public void setPattern(Boolean pattern){
		this.pattern.set(pattern);
	}

	public BooleanProperty patternProperty(){
		return pattern;
	}

	public Double setEmptyRate(){
		return emptyRate.get();
	}

	public void setEmptyRate(Double emptyRate){
		this.emptyRate.set(emptyRate);
	}

	public DoubleProperty emptyRateProperty(){
		return emptyRate;
	}

	public Double setUsedRate(){
		return usedRate.get();
	}

	public void setUsedRate(Double usedRate){
		this.usedRate.set(usedRate);
	}

	public DoubleProperty usedRateProperty(){
		return usedRate;
	}

	public Integer setDays(){
		return days.get();
	}

	public void setEmptyRate(Integer days){
		this.days.set(days);
	}

	public IntegerProperty daysProperty(){
		return days;
	}
}

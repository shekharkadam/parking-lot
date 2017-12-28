package com.example.core;

public class Car {

	private String regNumber;
	private String color;

	public Car(String regNumber, String color) {
		super();
		this.regNumber = regNumber;
		this.color = color;
	}

	public String getCarRegNumber() {
		return regNumber;
	}

	public void setCarRegNumber(String carRegNumber) {
		this.regNumber = carRegNumber;
	}

	public String getCarColor() {
		return color;
	}

	public void setCarColor(String carColor) {
		this.color = carColor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((regNumber == null) ? 0 : regNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (regNumber == null) {
			if (other.regNumber != null)
				return false;
		} else if (!regNumber.equals(other.regNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return regNumber + "		" + color + "\n";
	}

}

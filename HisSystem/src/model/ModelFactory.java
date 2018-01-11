package model;

import model.modelImpl.MainModelImpl;

public class ModelFactory {
	public static MainModel createMainModel() {
		return new MainModelImpl();
	}
}

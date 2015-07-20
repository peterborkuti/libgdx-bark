package hu.bp.bark;

public enum Distance {
	CLOSE,NEAR,BORDER,FAR;

	public static final int size = Distance.values().length - 1;

	public static Distance get(int distance, int maxDistance) {
		//HC SR-04 measures 0 if object is too far from the sensor
		if (distance == 0) {
			return FAR;
		}

		Distance values[] = Distance.values();

		int index = distance / (maxDistance / size);

		if (index <= 0) {
			return CLOSE;
		}
		else if (index >= size) {
			return FAR;
		}

		return values[index];
	}
}

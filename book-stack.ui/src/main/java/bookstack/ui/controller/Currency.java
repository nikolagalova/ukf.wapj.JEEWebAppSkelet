package bookstack.ui.controller;

public enum Currency {
	DOLLAR(1.127f),
	EURO(1f),
	LIBRA(0.86f);
	
	private Currency(float exchange) {
		setExchange(exchange);
	}
	
	private float exchange;

	public float getExchange() {
		return exchange;
	}

	public void setExchange(float exchange) {
		this.exchange = exchange;
	}

}

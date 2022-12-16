package com.sm.exceptionhandler.model;

public final class ErrorDetail {
	private String errorCode;
	private String layer;
	private String errorShortText;
	private String errorDesc;
	private Object object;
	
	private ErrorDetail(final String errorCode, final String layer, final String errorShortText,
			final String errorDesc,  final Object object) {
		
		this.errorCode = errorCode;
		this.layer = layer;
		this.errorShortText = errorShortText;
		this.errorDesc = errorDesc;
		this.object = object;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorShortText() {
		return errorShortText;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public Object getObject() {
		return object;
	}
	
	public String getLayer() {
		return layer;
	}
	
	public String getformatedMessage() {
		return this.getErrorCode()+"-"+this.getLayer()+"--"+this.getErrorDesc();
	}

	public static class ErrorDetailsBuilder {

		private String errorCode;
		private String layer;
		private String errorShortText;
		private String errorDesc;
		private Object object;
		
		public ErrorDetailsBuilder () {
		}
		public ErrorDetailsBuilder setErrorCode(String errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		public ErrorDetailsBuilder setErrorShortText(String errorShortText) {
			this.errorShortText = errorShortText;
			return this;
		}

		public ErrorDetailsBuilder setErrorDesc(String errorDesc) {
			this.errorDesc = errorDesc;
			return this;
		}

		public ErrorDetailsBuilder setObject(Object object) {
			this.object = object;
			return this;
		}

		public ErrorDetailsBuilder setLayer(String layer) {
			this.layer = layer;
			return this;
		}

		public ErrorDetail build() {
			return new ErrorDetail(this.errorCode, this.layer, this.errorShortText, this.errorDesc, this.object );
			
		}
	}
}
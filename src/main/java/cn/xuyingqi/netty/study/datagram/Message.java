package cn.xuyingqi.netty.study.datagram;

public final class Message {

	private Header header;
	private Body body;

	public final Header getHeader() {
		return header;
	}

	public final void setHeader(Header header) {
		this.header = header;
	}

	public final Body getBody() {
		return body;
	}

	public final void setBody(Body body) {
		this.body = body;
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("Message [\n");
		sb.append("Header=");
		sb.append(this.header);
		sb.append("\n");
		sb.append("Body=");
		sb.append(this.body);
		sb.append("\n");
		sb.append("]");

		return sb.toString();
	}
}

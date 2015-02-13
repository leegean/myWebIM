package iqq.im.bean.content;

import iqq.im.event.future.ProcActionFuture;

import java.awt.image.BufferedImage;

public class WbVerifyImage {

	private String src;
	private String dataPcid;
	private String code;
	private BufferedImage image;
	private ProcActionFuture future;
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDataPcid() {
		return dataPcid;
	}
	public void setDataPcid(String dataPcid) {
		this.dataPcid = dataPcid;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ProcActionFuture getFuture() {
		return future;
	}
	public void setFuture(ProcActionFuture future) {
		this.future = future;
	}

}

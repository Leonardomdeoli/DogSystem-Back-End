package org.dogsystem.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.dogsystem.utils.BaseEntity;

@Entity
@Table(name = "tb_image")
@AttributeOverride(name = "id", column = @Column(name = "cod_image"))
public class ImageEntity extends BaseEntity<Long>{
	
	private static final long serialVersionUID = 1L;
	
	private Integer filesize;
	
	private String filetype;
	
	private String filename;
	
	public ImageEntity() {
	}
	
	public ImageEntity(Integer filesize, String filetype, String filename, byte[] base64) {
		this.filesize = filesize;
		this.filetype = filetype;
		this.filename = filename;
		this.base64 = base64;
	}

	@Column(columnDefinition="LONGBLOB")
    private byte[] base64;
    
	public Integer getFilesize() {
		return filesize;
	}
	public void setFilesize(Integer filesize) {
		this.filesize = filesize;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getBase64() {
		return base64;
	}
	public void setBase64(byte[] base64) {
		this.base64 = base64;
	}
}

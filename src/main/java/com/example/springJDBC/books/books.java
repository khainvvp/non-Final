package com.example.springJDBC.books;

import java.sql.Blob;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import org.springframework.web.multipart.MultipartFile;

public class books {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookcode;
	private String tieude, tacgia, theloai, ngayphathanh, mota, feedback;
	private int sotrang;
	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	private String img;
	private MultipartFile imageFile;
	private String imageURL;
	public books(int bookcode, String tieude, String tacgia, String theloai, String ngayphathanh, int sotrang, String mota, String img, String feedback) {
		super();
		this.bookcode=bookcode;
		this.tieude=tieude;
		this.tacgia=tacgia;
		this.theloai=theloai;
		this.ngayphathanh=ngayphathanh;
		this.sotrang=sotrang;
		this.mota=mota;
		this.img=img;
		this.feedback=feedback;
	}
	
	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public books() {
		
	}

	public String getTieude() {
		return tieude;
	}

	public void setTieude(String tieude) {
		this.tieude = tieude;
	}

	public String getTacgia() {
		return tacgia;
	}

	public void setTacgia(String tacgia) {
		this.tacgia = tacgia;
	}

	public String getTheloai() {
		return theloai;
	}

	public void setTheloai(String theloai) {
		this.theloai = theloai;
	}

	public String getNgayphathanh() {
		return ngayphathanh;
	}

	public void setNgayphathanh(String ngayphathanh) {
		this.ngayphathanh = ngayphathanh;
	}

	public int getSotrang() {
		return sotrang;
	}

	public void setSotrang(int sotrang) {
		this.sotrang = sotrang;
	}

	public int getBookcode() {
		return bookcode;
	}

	public void setBookcode(int bookcode) {
		this.bookcode = bookcode;
	}

	@Override
	public String toString() {
		return "books [tieude=" + tieude + ", tacgia=" + tacgia + ", theloai=" + theloai + ", ngayphathanh="
				+ ngayphathanh + ", mota=" + mota + ", sotrang=" + sotrang + ", bookcode=" + bookcode + ", img=" + img
				+ "]";
	}

	
	
}

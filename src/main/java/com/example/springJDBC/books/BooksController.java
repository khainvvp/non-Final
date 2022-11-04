package com.example.springJDBC.books;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BooksController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	@GetMapping("/books")
	public String getBooks (Model model) throws IOException{
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		List<books> books=new ArrayList<books>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "khair00t");
			statement=connection.createStatement();
			resultSet=statement.executeQuery("select * from book");
			while(resultSet.next()) {
				int bookcode=resultSet.getInt("bookcode");
				String tieude=resultSet.getString("tieude");
				String tacgia=resultSet.getString("tacgia");
				String theloai=resultSet.getString("theloai");
				String ngayphathanh=resultSet.getString("ngayphathanh");
				int sotrang=resultSet.getInt("sotrang");
				String mota=resultSet.getString("mota");
				String img=resultSet.getString("img");
				books.add(new books(bookcode, tieude, tacgia, theloai, ngayphathanh, sotrang, mota, img));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("books", books);
		return "books";
	}
	
	
	
//	@GetMapping("/signin")
//	public String login() {
//		return "signin";
//	}
//	
//	@GetMapping("/signup")
//	public String createAcc() {
//		return "signup";
//	}
	
	@GetMapping("/book/{bookcode}")
	public String getBook(Model model, @PathVariable String bookcode) {
		model.addAttribute("bookcode", bookcode);
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet result= null;
		books book=new books();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "khair00t");
			ps=connection.prepareStatement("select * from book where bookcode=?");
			ps.setInt(1, Integer.valueOf(bookcode));
			result=ps.executeQuery();
			while (result.next()) {
				book.setBookcode(result.getInt("bookcode"));
				book.setTieude(result.getString("tieude"));
				book.setTacgia(result.getString("tacgia"));
				book.setTheloai(result.getString("theloai"));
				book.setNgayphathanh(result.getString("ngayphathanh"));
				book.setSotrang(result.getInt("sotrang"));
				book.setMota(result.getString("mota"));
				book.setImg(result.getString("img"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("book", book);
		return "book-detail";
	}
	
	@PostMapping("/book/save/{bookcode}")
	public String addBook(books book, @PathVariable String bookcode, @RequestParam("fileImage") MultipartFile multipartFile) {
		Connection connection=null;
		PreparedStatement ps=null;
		int result=0;
		//System.out.println(CURRENT_FOLDER);
		try {
	        Path staticPath = Paths.get("static");
	        Path imagePath = Paths.get("images");
	        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
	            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
	        }
	        Path file = CURRENT_FOLDER.resolve(staticPath)
	                .resolve(imagePath).resolve(multipartFile.getOriginalFilename());
	        //System.out.println(file.toString());
	        try (OutputStream os = Files.newOutputStream(file)) {
	            os.write(multipartFile.getBytes());
	        }
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "khair00t");
			ps=connection.prepareStatement("INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, Integer.valueOf(book.getBookcode()));
			ps.setString(2, book.getTieude());
			ps.setString(3, book.getTacgia());
			ps.setString(4, book.getTheloai());
			ps.setString(5, book.getNgayphathanh());
			ps.setInt(6, Integer.valueOf(book.getSotrang()));
			ps.setString(7, book.getMota());
			ps.setString(8, multipartFile.getOriginalFilename());
			result=ps.executeUpdate();
			ps.close();
			connection.close();
			
			return "redirect:/books";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "error";
	}
	
	@GetMapping("/book/delete/{bookcode}")
	public String delete(@PathVariable String bookcode) {
		Connection connection=null;
		PreparedStatement ps=null;
		int result=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "khair00t");
			ps=connection.prepareStatement("delete from book where bookcode = ?");
			ps.setInt(1, Integer.valueOf(bookcode));
			ps.executeUpdate();
			ps.close();
			connection.close();
			return "redirect:/books";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "error";
	}
	
	@PutMapping("/book/save/{bookcode}")
	public String updateBook(books book, @PathVariable String bookcode, @RequestParam("fileImage") MultipartFile multipartFile) {
		Connection connection=null;
		PreparedStatement ps=null;
		int result=0;
		
		try {
			Path staticPath = Paths.get("static");
	        Path imagePath = Paths.get("images");
	        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
	            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
	        }
	        Path file = CURRENT_FOLDER.resolve(staticPath)
	                .resolve(imagePath).resolve(multipartFile.getOriginalFilename());
	        //System.out.println(file.toString());
	        try (OutputStream os = Files.newOutputStream(file)) {
	            os.write(multipartFile.getBytes());
	        }
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "khair00t");
			ps=connection.prepareStatement("UPDATE book SET tieude=?,tacgia=?,theloai=?,ngayphathanh=?,sotrang=?, mota=?, img=? WHERE bookcode=?");
			ps.setString(1, book.getTieude());
			ps.setString(2, book.getTacgia());
			ps.setString(3, book.getTheloai());
			ps.setString(4, book.getNgayphathanh());
			ps.setInt(5, Integer.valueOf(book.getSotrang()));
			ps.setString(6, book.getMota());
			ps.setString(7, multipartFile.getOriginalFilename());
			ps.setInt(8, Integer.valueOf(book.getBookcode()));
			result=ps.executeUpdate();
			
			ps.close();
			connection.close();
			
			return "redirect:/books";
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "error";
	}

}


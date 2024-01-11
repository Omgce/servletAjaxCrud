package com.nifipayments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nifipayments.been.StudentBeen;
import com.nifipayments.database.Database;

public class StudentDao {

	public boolean save(StudentBeen sb) {
		Connection conn = Database.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into student(name,mobile,email) values(?,?,?)");
			ps.setString(1, sb.getName());
			ps.setString(2, sb.getMobile());
			ps.setString(3, sb.getEmail());
			int i = ps.executeUpdate();
			if (i == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public List<StudentBeen> getData() {
		List<StudentBeen> students = new ArrayList<StudentBeen>();
		try {
			PreparedStatement pst = Database.getConnection().prepareStatement("select * from student");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				StudentBeen sb = new StudentBeen(rs.getInt("id"));
				sb.setName(rs.getString("name"));
				sb.setMobile(rs.getString("mobile"));
				sb.setEmail(rs.getString("email"));
				students.add(sb);
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public boolean delete(int id) {

//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		Connection conn = Database.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("delete from student where id=?");

			pst.setInt(1, id);
			if (pst.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public StudentBeen getStudent(int stu_id) {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Connection conn = Database.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from student where id=?");
			pst.setInt(1, stu_id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				StudentBeen been = new StudentBeen(rs.getInt("id"), rs.getString("name"), rs.getString("mobile"),
						rs.getString("email"));
				return been;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	public boolean update(StudentBeen sb) {
		Connection conn = Database.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE student set name=?,mobile=?,email=? where id=? ");

			pst.setString(1, sb.getName());
			pst.setString(2, sb.getMobile());
			pst.setString(3, sb.getEmail());
			pst.setInt(4, sb.getId());
			if (pst.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}

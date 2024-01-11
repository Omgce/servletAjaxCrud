package com.nifipayments.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nifipayments.been.StudentBeen;
import com.nifipayments.dao.StudentDao;

@MultipartConfig
@WebServlet("/student")
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Student() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		StudentDao sd = new StudentDao();

		if (request.getParameter("id") == null) {

			List<StudentBeen> students = sd.getData();
//			System.err.println(students.size());
			if (students.size() == 0) {
				response.setContentType("application/json");
				response.setStatus(404);
				out.print("Record not found");
			} else {

				response.setContentType("application/json");
				response.setStatus(200);
				out.println(new Gson().toJson(students));
//				Gson gson=new Gson();
//				String json = gson.toJson(students);
//				out.println(json);
//				String html = "";
//				for (StudentBeen sb : students) {
//					html += "<tr>" + "<td>" + sb.getName() + "</td>" + "<td>+" + sb.getMobile() + "</td>" + "<td>"
//							+ sb.getEmail() + "</td>" + "<td>"
//							+ "<div class='btn-group border' role='group' style='border-radius: 50%;'>" + "<button onclick='deleteStudent(this)' id='"
//							+ sb.getId()
//							+ "' style='background-color: white; box-shadow: none;' class='btn'><i class='fas fa-trash text-danger' style='font-size: 20px;'></i></button>"
//							+ "	<button onclick='editStudent(this)' id='" + sb.getId()
//							+ "' style='background-color: white; box-shadow: none;' class='btn'><i class='fas fa-pen text-primary' style='font-size: 20px;'></i></button>"
//							+ "</div>" + "</td>" + "</tr>";
//				}
//				out.println(html);
			}
		} else {
			int stu_id = Integer.parseInt(request.getParameter("id"));
			StudentBeen student = sd.getStudent(stu_id);
			if (student != null) {
				response.setStatus(200);
				out.println(new Gson().toJson(student));
			} else {
				response.setStatus(404);
				out.println("Student not found.");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		int update_id = Integer.parseInt(id);

		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");

		if (update_id == 0) {
			StudentBeen sb = new StudentBeen(0, name, mobile, email);
			StudentDao sd = new StudentDao();
			boolean isSaved = sd.save(sb);
			if (isSaved) {
				response.setStatus(200);
				out.print("Done");
			} else {
				response.setStatus(500);
				out.print("Failed");
			}
		} else {
			// update(request, response);
			StudentBeen sb = new StudentBeen(update_id, name, mobile, email);
			StudentDao sd = new StudentDao();
			boolean isUpdate = sd.update(sb);
			if (isUpdate) {
				response.setStatus(200);
				out.print("update");
			} else {
				response.setStatus(500);
				out.print("updated failed");
			}
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		// out.print(req.getParameter("id"));
		String user_id = req.getParameter("id");
		int id = Integer.parseInt(user_id);
		StudentDao dao = new StudentDao();
		boolean isDelete = dao.delete(id);
		if (isDelete) {
			// resp.setContentType("application/json");
			resp.setStatus(200);
			out.print("done");
		} else {
			// resp.setContentType("application/json");
			resp.setStatus(500);
			out.print("error");
		}
	}

}
